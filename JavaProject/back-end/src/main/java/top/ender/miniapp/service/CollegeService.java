package top.ender.miniapp.service;

import top.ender.miniapp.entity.College;

import java.util.List;

/**
 * (College)表服务接口
 *
 * @author makejava
 * @since 2021-02-12 10:53:57
 */
public interface CollegeService {

    /**
     * 通过ID查询单条数据
     *
     * @param collegeId 主键
     * @return 实例对象
     */
    College queryById(Integer collegeId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<College> queryAllByLimit(int offset, int limit);

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
     * @return 实例对象
     */
    College insert(College college);

    /**
     * 修改数据
     *
     * @param college 实例对象
     * @return 实例对象
     */
    College update(College college);

    /**
     * 通过主键删除数据
     *
     * @param collegeId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer collegeId);

}
