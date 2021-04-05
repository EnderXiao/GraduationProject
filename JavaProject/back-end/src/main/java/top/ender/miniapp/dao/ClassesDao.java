package top.ender.miniapp.dao;

import top.ender.miniapp.entity.Classes;
import org.apache.ibatis.annotations.Param;
import top.ender.miniapp.entity.College;
import top.ender.miniapp.entity.Major;

import java.util.List;

/**
 * (Classes)表数据库访问层
 *
 * @author makejava
 * @since 2021-02-12 10:54:42
 */
public interface ClassesDao {

    /**
     * 通过ID查询单条数据
     *
     * @param classesId 主键
     * @return 实例对象
     */
    Classes queryById(Integer classesId);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Classes> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param classes 实例对象
     * @return 对象列表
     */
    List<Classes> queryAll(Classes classes);

    /**
     * 新增数据
     *
     * @param classes 实例对象
     * @return 影响行数
     */
    int insert(Classes classes);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Classes> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Classes> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Classes> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<Classes> entities);

    /**
     * 修改数据
     *
     * @param classes 实例对象
     * @return 影响行数
     */
    int update(Classes classes);

    /**
     * 通过主键删除数据
     *
     * @param classesId 主键
     * @return 影响行数
     */
    int deleteById(Integer classesId);

}

