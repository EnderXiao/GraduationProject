package top.ender.miniapp.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.io.Serializable;

/**
 * (User)实体类
 *
 * @author makejava
 * @since 2021-02-12 17:11:27
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    private static final long serialVersionUID = 876498795448572512L;

    private String uId;
    /**
     * 学生姓名
     */
    private String username;
    /**
     * 密码，6位数字或字母
     */
    private String password;
    /**
     * 班级id，外键来自classes表
     */
    private Integer classesId;
    /**
     * 最后一次修改时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastEditTime;
    /**
     * 用户类型，0表示冻结，1表示学生，2表示管理员
     */
    private Integer userStatus;

    /**
     * 用户所在班级
     */
    private Classes classes;

}
