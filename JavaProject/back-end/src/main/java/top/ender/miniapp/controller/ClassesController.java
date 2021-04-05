package top.ender.miniapp.controller;

import top.ender.miniapp.entity.Classes;
import top.ender.miniapp.entity.Major;
import top.ender.miniapp.entity.Message;
import top.ender.miniapp.entity.User;
import top.ender.miniapp.service.ClassesService;
import org.springframework.web.bind.annotation.*;
import top.ender.miniapp.service.UserService;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Classes)表控制层
 *
 * @author makejava
 * @since 2021-02-12 10:54:43
 */
@RestController
@RequestMapping("classes")
public class ClassesController {
    /**
     * 服务对象
     */
    @Resource
    private ClassesService classesService;
    @Resource
    private UserService userService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @RequestMapping("selectOne")
    public Classes selectOne(Integer id) {
        System.out.println(id);
        Classes classes = this.classesService.queryById(id);
        System.out.println(classes);
        return classes;
    }
    @RequestMapping("selectAll")
    public Message<List<Classes>> selectAll(String uId,Integer majorId){
        User user = userService.queryById(uId);
        System.out.println(majorId);
        System.out.println(uId);
        Message<List<Classes>> listMessage = new Message<>();
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
                Classes classesSearch = new Classes();
                classesSearch.setMajorId(majorId);
                List<Classes> classes = this.classesService.queryAll(classesSearch);
                for (Classes classNow : classes) {
                    System.out.println(classNow);
                }
                listMessage.setCode(1);
                listMessage.setData(classes);
                listMessage.setMsg("查询成功");
            }
        }
        return listMessage;
    }


}
