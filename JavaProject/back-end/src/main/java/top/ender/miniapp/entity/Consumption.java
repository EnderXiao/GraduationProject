package top.ender.miniapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.io.Serializable;

/**
 * (Consumption)实体类
 *
 * @author makejava
 * @since 2021-02-16 22:08:26
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Consumption implements Serializable {
    private static final long serialVersionUID = -65269930696505020L;

    private Integer cspId;
    /**
     * 消费金额
     */
    private Double cspMoney;
    /**
     * 来自store表的外键
     */
    private Integer storeId;
    /**
     * 消费日期
     */
    private Date cspDate;
    /**
     * 消费用户，来自user表的外键
     */
    private Integer uId;
    /**
     * 0表示出账1表示入账
     */
    private Integer cspStatus;
    /**
     * 消费时间
     */
    private Date cspTime;
    /**
     * 多表查询，查询store表
     */
    private Store store;
}
