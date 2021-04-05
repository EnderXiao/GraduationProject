package top.ender.miniapp.controller;

import top.ender.miniapp.entity.Course;
import top.ender.miniapp.entity.Grade;
import top.ender.miniapp.entity.Message;
import top.ender.miniapp.entity.User;
import top.ender.miniapp.service.CourseService;
import org.springframework.web.bind.annotation.*;
import top.ender.miniapp.service.UserService;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * (Course)表控制层
 *
 * @author makejava
 * @since 2021-02-12 11:02:56
 */
@RestController
@RequestMapping("course")
public class CourseController {
    /**
     * 服务对象
     */
    @Resource
    private CourseService courseService;
    @Resource
    private UserService userService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Course selectOne(Integer id) {
        return this.courseService.queryById(id);
    }

    /**
     * 通过班级Id查询课程
     *
     * @param classId 课程ID
     * @return 对象列表
     */
    @RequestMapping("selectByClass")
    public Course[][] selectByClass(Integer classId,Integer week){
        System.out.println(classId);
        System.out.println(week);
        List<Course> courses = this.courseService.queryByClass(classId,week);
        Course[][] lists = new Course[5][7];
        for (Course course : courses) {
            lists[course.getCourseNo() - 1][course.getWeekDay() - 1] = course;
        }
        return lists;
    }

    /**
     * 分页获取所有课程
     *
     * @param uId 用户名
     * @param start 起始位置
     * @param length 结束位置
     * @return 对象列表
     */
    @RequestMapping("selectAll")
    public Message<List<Course>> selectAll(String uId, Integer start, Integer length){
        User user = this.userService.queryById(uId);
        System.out.println(user);
        Message<List<Course>> listMessage = new Message<>();
        if(!user.getUserStatus().equals(1)){
            List<Course> courses = this.courseService.queryAllByLimit(start, length);
            for (Course courseNow : courses) {
                System.out.println(courseNow);
            }
            listMessage.setData(courses);
            listMessage.setCode(1);
            listMessage.setMsg("查找成功！");
        }else{
            listMessage.setData(null);
            listMessage.setCode(0);
            listMessage.setMsg("账号不是管理员，无法获取，请联系管理员！");
        }
        return listMessage;
    }

    @RequestMapping("addCourse")
    public Message<Integer> addCourse(Course course) {
        System.out.println(course);
        Message<Integer> integerMessage = new Message<>();
        Course courseSearch1 = new Course();
        courseSearch1.setWeekDay(course.getWeekDay());
        courseSearch1.setWeekNo(course.getWeekNo());
        courseSearch1.setCourseNo(course.getCourseNo());
        courseSearch1.setClassesId(course.getClassesId());
        List<Course> courses1 = this.courseService.queryAll(courseSearch1);
        Course courseSearch2 = new Course();
        courseSearch2.setWeekDay(course.getWeekDay());
        courseSearch2.setWeekEnd(course.getWeekEnd());
        courseSearch2.setWeekNo(course.getWeekNo());
        courseSearch2.setCourseNo(course.getCourseNo());
        courseSearch2.setClassRoomId(course.getClassRoomId());
        List<Course> courses2 = this.courseService.queryAll(courseSearch2);
        System.out.println(courses1);
        System.out.println(courses2);
        if(courses1.size() != 0 || courses2.size() != 0){
            integerMessage.setCode(0);
            integerMessage.setData(0);
            integerMessage.setMsg("课程冲突或教室已被占用");
            return integerMessage;
        }
        Course insert = this.courseService.insert(course);
        if(insert == null){
            integerMessage.setCode(0);
            integerMessage.setData(0);
            integerMessage.setMsg("添加失败，请联系管理员");
        }else{
            integerMessage.setCode(1);
            integerMessage.setData(1);
            integerMessage.setMsg("添加成功！");
        }
        return integerMessage;
    }
    /**
     * 通过实体作为筛选条件查询
     *
     * @param course 实例对象
     * @return 对象列表
     */
    @RequestMapping("findCourse")
    public Message<List<Course>> findCourse(Course course){
        System.out.println(course);
        List<Course> courses = this.courseService.queryAll(course);
        System.out.println(courses);
        Message<List<Course>> courseMessage = new Message<>();
        if(courses.size() == 0){
            courseMessage.setCode(0);
            courseMessage.setMsg("没有信息");
        }else{
            courseMessage.setCode(1);
            courseMessage.setMsg("查询成功");
            courseMessage.setData(courses);
        }
        System.out.println(courseMessage);
        return courseMessage;
    }
    @RequestMapping("delete")
    public Message<Integer> delete(@RequestParam("courseIds") List<Integer> courseIds){
        Message<Integer> integerMessage = new Message<>();
        int length = courseIds.size();
        int successNo = 0;
        for (Integer courseId : courseIds) {
            boolean result = this.courseService.deleteById(courseId);
            if(result){
                successNo++;
            }
        }
        if(successNo == 0){
            integerMessage.setCode(0);
            integerMessage.setData(0);
            integerMessage.setMsg("删除失败，请联系管理员");
        }
        else {
            integerMessage.setCode(1);
            integerMessage.setData(successNo);
            integerMessage.setMsg("选中" + length + "个," + "成功删除" + successNo + "个");
        }
        return integerMessage;
    }
}
