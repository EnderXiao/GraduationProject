package top.ender.miniapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.io.Serializable;

/**
 * (VirtualCard)实体类
 *
 * @author makejava
 * @since 2021-02-12 10:58:10
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VirtualCard implements Serializable {
    private static final long serialVersionUID = -97939290120547456L;

    private Integer vcId;
    /**
     * 外键，来自user表
     */
    private String uId;

    /**
     * 账户余额
     */
    private Double restMoney;
    /**
     * 0表示未激活1表示激活
     */
    private Integer cardStatus;
    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date creatTime;
    /**
     * 最后一次更新时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastEditTime;

    /**
     * 支付密码
     */
    private String vcPassword;

    /**
     * 绑定银行名称
     */
    private String bankName;

    /**
     * 绑定银行卡卡号
     */
    private String bankCard;

}
