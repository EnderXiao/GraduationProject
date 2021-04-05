package top.ender.miniapp.service;

import top.ender.miniapp.entity.Consumption;

import java.util.List;

/**
 * (Consumption)表服务接口
 *
 * @author makejava
 * @since 2021-02-16 22:08:27
 */
public interface ConsumptionService {

    /**
     * 通过ID查询单条数据
     *
     * @param cspId 主键
     * @return 实例对象
     */
    Consumption queryById(Integer cspId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Consumption> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param consumption 实例对象
     * @return 实例对象
     */
    Consumption insert(Consumption consumption);

    /**
     * 修改数据
     *
     * @param consumption 实例对象
     * @return 实例对象
     */
    Consumption update(Consumption consumption);

    /**
     * 通过主键删除数据
     *
     * @param cspId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer cspId);

    /**
     * 通过时间查找消费记录
     *
     * @param time 消费时间
     * @param uId   用户ID
     * @return 对象列表
     */
    List<Consumption> queryByTime(String time, String uId);

}
