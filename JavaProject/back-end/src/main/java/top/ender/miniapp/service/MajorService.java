package top.ender.miniapp.service;

import top.ender.miniapp.entity.College;
import top.ender.miniapp.entity.Major;

import java.util.List;

/**
 * (Major)表服务接口
 *
 * @author makejava
 * @since 2021-02-12 10:54:07
 */
public interface MajorService {

    /**
     * 通过ID查询单条数据
     *
     * @param majorId 主键
     * @return 实例对象
     */
    Major queryById(Integer majorId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Major> queryAllByLimit(int offset, int limit);

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
     * @return 实例对象
     */
    Major insert(Major major);

    /**
     * 修改数据
     *
     * @param major 实例对象
     * @return 实例对象
     */
    Major update(Major major);

    /**
     * 通过主键删除数据
     *
     * @param majorId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer majorId);

}
