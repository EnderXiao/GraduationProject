package top.ender.miniapp.dao;

import top.ender.miniapp.entity.College;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (College)表数据库访问层
 *
 * @author makejava
 * @since 2021-02-12 10:53:57
 */
public interface CollegeDao {

    /**
     * 通过ID查询单条数据
     *
     * @param collegeId 主键
     * @return 实例对象
     */
    College queryById(Integer collegeId);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<College> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param college 实例对象
     * @return 对象列表
     */
    List<College> queryAll(College college);

    /**
     * 新增数据
     *
     * @param college 实例对象
     * @return 影响行数
     */
    int insert(College college);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<College> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<College> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<College> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<College> entities);

    /**
     * 修改数据
     *
     * @param college 实例对象
     * @return 影响行数
     */
    int update(College college);

    /**
     * 通过主键删除数据
     *
     * @param collegeId 主键
     * @return 影响行数
     */
    int deleteById(Integer collegeId);

}

