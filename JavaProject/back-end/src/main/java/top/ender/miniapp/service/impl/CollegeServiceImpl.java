package top.ender.miniapp.service.impl;

import top.ender.miniapp.entity.College;
import top.ender.miniapp.dao.CollegeDao;
import top.ender.miniapp.service.CollegeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (College)表服务实现类
 *
 * @author makejava
 * @since 2021-02-12 10:53:57
 */
@Service("collegeService")
public class CollegeServiceImpl implements CollegeService {
    @Resource
    private CollegeDao collegeDao;

    /**
     * 通过ID查询单条数据
     *
     * @param collegeId 主键
     * @return 实例对象
     */
    @Override
    public College queryById(Integer collegeId) {
        return this.collegeDao.queryById(collegeId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<College> queryAllByLimit(int offset, int limit) {
        return this.collegeDao.queryAllByLimit(offset, limit);
    }

    /**
     * 通过实体作为筛选条件查询
     *
     * @param college 实例对象
     * @return 对象列表
     */
    @Override
    public List<College> queryAll(College college){
        return collegeDao.queryAll(college);
    }

    /**
     * 新增数据
     *
     * @param college 实例对象
     * @return 实例对象
     */
    @Override
    public College insert(College college) {
        this.collegeDao.insert(college);
        return college;
    }

    /**
     * 修改数据
     *
     * @param college 实例对象
     * @return 实例对象
     */
    @Override
    public College update(College college) {
        this.collegeDao.update(college);
        return this.queryById(college.getCollegeId());
    }

    /**
     * 通过主键删除数据
     *
     * @param collegeId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer collegeId) {
        return this.collegeDao.deleteById(collegeId) > 0;
    }
}
