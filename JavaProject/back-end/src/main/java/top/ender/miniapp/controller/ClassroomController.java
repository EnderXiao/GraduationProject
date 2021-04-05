package top.ender.miniapp.controller;

import top.ender.miniapp.entity.Classes;
import top.ender.miniapp.entity.Classroom;
import top.ender.miniapp.entity.Message;
import top.ender.miniapp.entity.User;
import top.ender.miniapp.service.ClassroomService;
import org.springframework.web.bind.annotation.*;
import top.ender.miniapp.service.UserService;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Classroom)表控制层
 *
 * @author makejava
 * @since 2021-02-12 11:02:15
 */
@RestController
@RequestMapping("classroom")
public class ClassroomController {
    /**
     * 服务对象
     */
    @Resource
    private ClassroomService classroomService;
    @Resource
    private UserService userService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Classroom selectOne(Integer id) {
        return this.classroomService.queryById(id);
    }

    /**
     * 获得所有教室信息
     *
     * @param uId 管理员ID
     * @return 对象列表
     */
    @RequestMapping("selectAll")
    public Message<List<Classroom>> selectAll(String uId){
        User user = userService.queryById(uId);
        System.out.println(uId);
        Message<List<Classroom>> listMessage = new Message<>();
        if(user == null){
            listMessage.setCode(0);
            listMessage.setData(null);
            listMessage.setMsg("账号失效，请联系管理员！");
        }else {
            if (user.getUserStatus().equals(1)) {
                listMessage.setCode(0);
                listMessage.setData(null);
                listMessage.setMsg("账号不是管理员，无法获取，请联系管理员！");
            } else {
                List<Classroom> classrooms = this.classroomService.queryAll(null);
                for (Classroom classroom : classrooms) {
                    System.out.println(classroom);
                }
                listMessage.setCode(1);
                listMessage.setData(classrooms);
                listMessage.setMsg("查询成功");
            }
        }
        return listMessage;
    }

}
