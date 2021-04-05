package top.ender.miniapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.io.Serializable;

/**
 * (Store)实体类
 *
 * @author makejava
 * @since 2021-02-12 11:01:48
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Store implements Serializable {
    private static final long serialVersionUID = -18469750456466931L;

    private Integer storeId;

    private String storeName;
    /**
     * 创建时间
     */
    private Date creatTime;

}
