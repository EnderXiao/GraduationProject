package top.ender.miniapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * (Grade)实体类
 *
 * @author makejava
 * @since 2021-02-21 09:36:35
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Grade implements Serializable {
    private static final long serialVersionUID = -51869644030004683L;

    private Integer gradeId;
    /**
     * 外键，来自user表
     */
    private Integer uId;
    /**
     * 总分
     */
    private Double gradeSum;
    /**
     * 平时分
     */
    private Double dailyGrade;
    /**
     * 考试分数
     */
    private Double examGrade;
    /**
     * 考试类型
     */
    private String examType;
    /**
     * 课程名
     */
    private String gradeName;
    /**
     * 成绩学年
     */
    private String gradeYear;

    /**
     * 课程类型
     */
    private String classType;
    /**
     * 学分
     */
    private Integer classCredit;

}
