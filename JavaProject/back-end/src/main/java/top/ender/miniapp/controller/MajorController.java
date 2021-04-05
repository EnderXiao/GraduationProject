package top.ender.miniapp.controller;

import top.ender.miniapp.entity.College;
import top.ender.miniapp.entity.Major;
import top.ender.miniapp.entity.Message;
import top.ender.miniapp.entity.User;
import top.ender.miniapp.service.MajorService;
import org.springframework.web.bind.annotation.*;
import top.ender.miniapp.service.UserService;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Major)表控制层
 *
 * @author makejava
 * @since 2021-02-12 10:54:07
 */
@RestController
@RequestMapping("major")
public class MajorController {
    /**
     * 服务对象
     */
    @Resource
    private MajorService majorService;
    @Resource
    private UserService userService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Major selectOne(Integer id) {
        return this.majorService.queryById(id);
    }

    /**
     * 通过学院Id查找所有所属专业
     *
     * @param collegeId 学院Id
     * @Param uId 用户Id，用于身份验证
     * @return 对象列表
     */
    @RequestMapping("selectAll")
    public Message<List<Major>> selectAll(String uId,Integer collegeId){
        User user = userService.queryById(uId);
        System.out.println(collegeId);
        System.out.println(uId);
        Message<List<Major>> listMessage = new Message<>();
        if(user == null){
            listMessage.setCode(0);
            listMessage.setData(null);
            listMessage.setMsg("账号失效，请联系管理员！");
        }else {
            if (user.getUserStatus().equals(1)) {
                listMessage.setCode(0);
                listMessage.setData(null);
                listMessage.setMsg("账号失效，请联系管理员！");
            } else {
                Major majorSearch = new Major();
                majorSearch.setCollegeId(collegeId);
                List<Major> majors = majorService.queryAll(majorSearch);
                for (Major major : majors) {
                    System.out.println(major);
                }
                listMessage.setCode(1);
                listMessage.setData(majors);
                listMessage.setMsg("查询成功");
            }
        }
        return listMessage;
    }

}
