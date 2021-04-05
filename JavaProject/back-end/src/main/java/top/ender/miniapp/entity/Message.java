package top.ender.miniapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Ender-PC
 * @date 2021/2/14
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message<T> {
    /**
     * 结果编号
     */
    private Integer code;
    /**
     * 结果信息
     */
    private String msg;

    /**
     * 返回数据
     */
    T data;
}
