package top.ender.miniapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * (Classes)实体类
 *
 * @author makejava
 * @since 2021-02-12 10:54:42
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Classes implements Serializable {
    private static final long serialVersionUID = 723843952858839642L;

    private Integer classesId;
    /**
     * 来自major_id的外键
     */
    private Integer majorId;

    private Integer schoolYear;

    private Integer classesNo;

    private Major major;

}
