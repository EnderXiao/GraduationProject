package top.ender.miniapp.dao;

import top.ender.miniapp.entity.Consumption;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (Consumption)表数据库访问层
 *
 * @author makejava
 * @since 2021-02-16 22:08:27
 */
public interface ConsumptionDao {

    /**
     * 通过ID查询单条数据
     *
     * @param cspId 主键
     * @return 实例对象
     */
    Consumption queryById(Integer cspId);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Consumption> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param consumption 实例对象
     * @return 对象列表
     */
    List<Consumption> queryAll(Consumption consumption);

    /**
     * 通过时间查找
     *
     * @param time 消费时间
     * @param uId 用户ID
     * @return 对象列表
     */
    List<Consumption> queryByTime(@Param("time") String time,@Param("uId") String uId);

    /**
     * 新增数据
     *
     * @param consumption 实例对象
     * @return 影响行数
     */
    int insert(Consumption consumption);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Consumption> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Consumption> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Consumption> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<Consumption> entities);

    /**
     * 修改数据
     *
     * @param consumption 实例对象
     * @return 影响行数
     */
    int update(Consumption consumption);

    /**
     * 通过主键删除数据
     *
     * @param cspId 主键
     * @return 影响行数
     */
    int deleteById(Integer cspId);

}

