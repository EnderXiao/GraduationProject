package top.ender.miniapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * (College)实体类
 *
 * @author makejava
 * @since 2021-02-12 10:53:56
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class College implements Serializable {
    private static final long serialVersionUID = 490242822600089951L;

    private Integer collegeId;
    /**
     * 学院名
     */
    private String collegeName;
    /**
     * 学院总人数
     */
    private Integer collegeStudentNum;

}
