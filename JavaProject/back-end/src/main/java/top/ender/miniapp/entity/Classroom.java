package top.ender.miniapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * (Classroom)实体类
 *
 * @author makejava
 * @since 2021-02-12 11:02:14
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Classroom implements Serializable {
    private static final long serialVersionUID = -74101382310214650L;

    private Integer classRoomId;
    /**
     * 教室编号
     */
    private Integer classRoomNo;
    /**
     * 所属教学楼
     */
    private String classBuilding;
    /**
     * 所属校区
     */
    private String areaName;

}
