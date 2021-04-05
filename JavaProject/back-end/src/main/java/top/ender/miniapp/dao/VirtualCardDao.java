package top.ender.miniapp.dao;

import org.apache.ibatis.annotations.Param;
import top.ender.miniapp.entity.VirtualCard;

import java.util.List;

/**
 * (VirtualCard)表数据库访问层
 *
 * @author makejava
 * @since 2021-02-12 10:58:10
 */
public interface VirtualCardDao {

    /**
     * 通过ID查询单条数据
     *
     * @param vcId 主键
     * @return 实例对象
     */
    VirtualCard queryById(Integer vcId);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<VirtualCard> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);

    /**
     * 按用户Id查找
     *
     * @param uId 用户Id
     * @return 实列对象
     */
    VirtualCard queryByUserId(String uId);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param virtualcard 实例对象
     * @return 对象列表
     */
    List<VirtualCard> queryAll(VirtualCard virtualcard);

    /**
     * 新增数据
     *
     * @param virtualcard 实例对象
     * @return 影响行数
     */
    int insert(VirtualCard virtualcard);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<VirtualCard> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<VirtualCard> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<VirtualCard> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<VirtualCard> entities);

    /**
     * 修改数据
     *
     * @param virtualcard 实例对象
     * @return 影响行数
     */
    int update(VirtualCard virtualcard);

    /**
     * 通过主键删除数据
     *
     * @param vcId 主键
     * @return 影响行数
     */
    int deleteById(Integer vcId);

    /**
     * 通过用户ID删除数据
     *
     * @param uId 用户Id
     * @return 影响行数
     */
    int deleteByUserId(String uId);

}

