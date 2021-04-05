package top.ender.miniapp.service.impl;

import top.ender.miniapp.entity.Classes;
import top.ender.miniapp.dao.ClassesDao;
import top.ender.miniapp.entity.College;
import top.ender.miniapp.service.ClassesService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Classes)表服务实现类
 *
 * @author makejava
 * @since 2021-02-12 10:54:43
 */
@Service("classesService")
public class ClassesServiceImpl implements ClassesService {
    @Resource
    private ClassesDao classesDao;

    /**
     * 通过ID查询单条数据
     *
     * @param classesId 主键
     * @return 实例对象
     */
    @Override
    public Classes queryById(Integer classesId) {
        return this.classesDao.queryById(classesId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<Classes> queryAllByLimit(int offset, int limit) {
        return this.classesDao.queryAllByLimit(offset, limit);
    }

    /**
     * 通过实体作为筛选条件查询
     *
     * @param classes 实例对象
     * @return 对象列表
     */
    @Override
    public List<Classes> queryAll(Classes classes){
        return this.classesDao.queryAll(classes);
    }

    /**
     * 新增数据
     *
     * @param classes 实例对象
     * @return 实例对象
     */
    @Override
    public Classes insert(Classes classes) {
        this.classesDao.insert(classes);
        return classes;
    }

    /**
     * 修改数据
     *
     * @param classes 实例对象
     * @return 实例对象
     */
    @Override
    public Classes update(Classes classes) {
        this.classesDao.update(classes);
        return this.queryById(classes.getClassesId());
    }

    /**
     * 通过主键删除数据
     *
     * @param classesId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer classesId) {
        return this.classesDao.deleteById(classesId) > 0;
    }
}
