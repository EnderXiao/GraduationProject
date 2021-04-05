package top.ender.miniapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * (Major)实体类
 *
 * @author makejava
 * @since 2021-02-12 10:54:06
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Major implements Serializable {
    private static final long serialVersionUID = -66870409010527814L;

    private Integer majorId;

    private String majorName;
    /**
     * 专业人数
     */
    private Integer majorStudentNum;
    /**
     * 所属学院，来自college表的外键
     */
    private Integer collegeId;
    /**
     * 所属学院
     */
    private College college;
}
