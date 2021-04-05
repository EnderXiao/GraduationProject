package top.ender.miniapp.dao;

import top.ender.miniapp.entity.Major;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (Major)表数据库访问层
 *
 * @author makejava
 * @since 2021-02-12 10:54:06
 */
public interface MajorDao {

    /**
     * 通过ID查询单条数据
     *
     * @param majorId 主键
     * @return 实例对象
     */
    Major queryById(Integer majorId);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Major> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param major 实例对象
     * @return 对象列表
     */
    List<Major> queryAll(Major major);

    /**
     * 新增数据
     *
     * @param major 实例对象
     * @return 影响行数
     */
    int insert(Major major);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Major> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Major> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Major> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<Major> entities);

    /**
     * 修改数据
     *
     * @param major 实例对象
     * @return 影响行数
     */
    int update(Major major);

    /**
     * 通过主键删除数据
     *
     * @param majorId 主键
     * @return 影响行数
     */
    int deleteById(Integer majorId);

}

