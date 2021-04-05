package top.ender.miniapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * (Course)实体类
 *
 * @author makejava
 * @since 2021-02-12 11:02:55
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course implements Serializable {
    private static final long serialVersionUID = -49105459755770547L;

    private Integer courseId;
    /**
     * 课程名
     */
    private String courseName;
    /**
     * 上课班级，来自classes表的外键
     */
    private Integer classesId;
    /**
     * 上课周次
     */
    private Integer weekNo;

    /**
     * 结束周次
     */
    private Integer weekEnd;
    /**
     * 课程在周几
     */
    private Integer weekDay;
    /**
     * 第几节课
     */
    private Integer courseNo;
    /**
     * 上课教室，来自classroom表的外键
     */
    private Integer classRoomId;

    private Classroom classroom;
}
