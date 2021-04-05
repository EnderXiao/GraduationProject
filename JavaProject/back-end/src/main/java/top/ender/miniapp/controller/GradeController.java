package top.ender.miniapp.controller;

import top.ender.miniapp.entity.Grade;
import top.ender.miniapp.entity.Message;
import top.ender.miniapp.entity.User;
import top.ender.miniapp.entity.VirtualCard;
import top.ender.miniapp.service.GradeService;
import org.springframework.web.bind.annotation.*;
import top.ender.miniapp.service.UserService;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * (Grade)表控制层
 *
 * @author makejava
 * @since 2021-02-21 09:36:36
 */
@RestController
@RequestMapping("grade")
public class GradeController {
    /**
     * 服务对象
     */
    @Resource
    private GradeService gradeService;
    @Resource
    private UserService userService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @RequestMapping("selectById")
    public Grade selectById(Integer id) {
        System.out.println(id);
        return this.gradeService.queryById(id);
    }

    /**
     * 通过用户Id和考试时间查询该生所有成绩
     *
     * @param uId 用户ID
     * @param gradeYear 考试时间
     * @return 数据列表
     */
    @RequestMapping("selectByIdAndTime")
    public List<Grade> selectByIdAndTime(String uId, String gradeYear){
        System.out.println(uId);
        System.out.println(gradeYear);
        List<Grade> grades = this.gradeService.queryGradeByUidAndTime(uId, gradeYear);
        for (Grade grade : grades) {
            System.out.println(grade);
        }
        return grades;
    }

    /**
     * 通过实体作为筛选条件查询
     *
     * @param grade 实例对象
     * @return 对象列表
     */
    @RequestMapping("findGradeByLimit")
    public Message<List<Grade>> findGradeByLimit(Grade grade){
        System.out.println(grade);
        List<Grade> grades = this.gradeService.queryAll(grade);
        System.out.println(grades);
        Message<List<Grade>> gradeMessage = new Message<>();
        if(grades.size() == 0){
            gradeMessage.setCode(0);
            gradeMessage.setMsg("没有信息");
        }else{
            gradeMessage.setCode(1);
            gradeMessage.setMsg("查询成功");
            gradeMessage.setData(grades);
        }
        System.out.println(gradeMessage);
        return gradeMessage;
    }

    @RequestMapping("selectAll")
    public Message<List<Grade>> selectAll(String uId,Integer start, Integer length){
        Message<List<Grade>> listMessage = new Message<>();
        User user = this.userService.queryById(uId);
        listMessage.setCode(0);
        listMessage.setData(null);
        listMessage.setMsg("查找失败！");
        if(!user.getUserStatus().equals(1)){
            List<Grade> grades = this.gradeService.queryAllByLimit(start, length);
            for (Grade grade : grades) {
                System.out.println(grade);
            }
            listMessage.setData(grades);
            listMessage.setCode(1);
            listMessage.setMsg("查找成功！");
        }else{
            listMessage.setData(null);
            listMessage.setCode(0);
            listMessage.setMsg("账号不是管理员，无法获取，请联系管理员！");
        }
        return listMessage;
    }

    @RequestMapping("addGrade")
    public Message<Integer> addGrade(Grade grade) throws ParseException {
        System.out.println(grade);
        Message<Integer> integerMessage = new Message<>();
        List<Grade> grades = this.gradeService.queryAll(grade);
        if(grades.size() != 0){
            integerMessage.setCode(0);
            integerMessage.setData(0);
            integerMessage.setMsg("成绩已存在");
            return integerMessage;
        }
        double gradeSum = grade.getDailyGrade() * 0.3 + grade.getExamGrade() * 0.7;
        grade.setGradeSum(gradeSum);
        Grade insert = this.gradeService.insert(grade);
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
     * 删除成绩
     *
     * @param gradeIds ID列表
     * @return Message<Integer>
     */
    @RequestMapping("delete")
    public Message<Integer> delete(@RequestParam("gradeIds") List<Integer> gradeIds){
        Message<Integer> integerMessage = new Message<>();
        int successNo = 0;
        int length = gradeIds.size();
        for (Integer gradeId : gradeIds) {
            boolean b = this.gradeService.deleteById(gradeId);
            if(b){
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
