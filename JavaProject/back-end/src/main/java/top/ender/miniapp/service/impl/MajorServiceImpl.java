package top.ender.miniapp.service.impl;

import top.ender.miniapp.entity.College;
import top.ender.miniapp.entity.Major;
import top.ender.miniapp.dao.MajorDao;
import top.ender.miniapp.service.MajorService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Major)表服务实现类
 *
 * @author makejava
 * @since 2021-02-12 10:54:07
 */
@Service("majorService")
public class MajorServiceImpl implements MajorService {
    @Resource
    private MajorDao majorDao;

    /**
     * 通过ID查询单条数据
     *
     * @param majorId 主键
     * @return 实例对象
     */
    @Override
    public Major queryById(Integer majorId) {
        return this.majorDao.queryById(majorId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<Major> queryAllByLimit(int offset, int limit) {
        return this.majorDao.queryAllByLimit(offset, limit);
    }

    /**
     * 通过实体作为筛选条件查询
     *
     * @param major 实例对象
     * @return 对象列表
     */
    @Override
    public List<Major> queryAll(Major major){
        return this.majorDao.queryAll(major);
    }

    /**
     * 新增数据
     *
     * @param major 实例对象
     * @return 实例对象
     */
    @Override
    public Major insert(Major major) {
        this.majorDao.insert(major);
        return major;
    }

    /**
     * 修改数据
     *
     * @param major 实例对象
     * @return 实例对象
     */
    @Override
    public Major update(Major major) {
        this.majorDao.update(major);
        return this.queryById(major.getMajorId());
    }

    /**
     * 通过主键删除数据
     *
     * @param majorId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer majorId) {
        return this.majorDao.deleteById(majorId) > 0;
    }
}
