package top.ender.miniapp.controller;

import top.ender.miniapp.entity.College;
import top.ender.miniapp.entity.Message;
import top.ender.miniapp.entity.User;
import top.ender.miniapp.service.CollegeService;
import org.springframework.web.bind.annotation.*;
import top.ender.miniapp.service.UserService;

import javax.annotation.Resource;
import java.util.List;

/**
 * (College)表控制层
 *
 * @author makejava
 * @since 2021-02-12 10:53:57
 */
@RestController
@RequestMapping("college")
public class CollegeController {
    /**
     * 服务对象
     */
    @Resource
    private CollegeService collegeService;

    @Resource
    private UserService userService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public College selectOne(Integer id) {
        return this.collegeService.queryById(id);
    }

    /**
     * 通过实体作为筛选条件查询
     *
     * @param uId 账号ID，用于身份验证
     * @return 对象列表
     */
    @RequestMapping("selectAll")
    public Message<List<College>> selectAll(String uId){
        System.out.println(uId);
        User user = userService.queryById(uId);
        System.out.println(user);
        Message<List<College>> listMessage = new Message<>();
        if(user == null){
            listMessage.setCode(0);
            listMessage.setData(null);
            listMessage.setMsg("账号失效，请联系管理员！");
        }else{
            if(user.getUserStatus().equals(1)){
                listMessage.setCode(0);
                listMessage.setData(null);
                listMessage.setMsg("账号失效，请联系管理员！");
            }else{
                List<College> colleges = collegeService.queryAll(null);
                for (College college : colleges) {
                    System.out.println(college);
                }
                listMessage.setCode(1);
                listMessage.setData(colleges);
                listMessage.setMsg("查询成功");
            }
        }
        return listMessage;
    }

}
