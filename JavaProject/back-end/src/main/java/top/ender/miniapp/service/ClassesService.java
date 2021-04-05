package top.ender.miniapp.service;

import top.ender.miniapp.entity.Classes;
import top.ender.miniapp.entity.College;

import java.util.List;

/**
 * (Classes)表服务接口
 *
 * @author makejava
 * @since 2021-02-12 10:54:43
 */
public interface ClassesService {

    /**
     * 通过ID查询单条数据
     *
     * @param classesId 主键
     * @return 实例对象
     */
    Classes queryById(Integer classesId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Classes> queryAllByLimit(int offset, int limit);

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
     * @return 实例对象
     */
    Classes insert(Classes classes);

    /**
     * 修改数据
     *
     * @param classes 实例对象
     * @return 实例对象
     */
    Classes update(Classes classes);

    /**
     * 通过主键删除数据
     *
     * @param classesId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer classesId);

}
