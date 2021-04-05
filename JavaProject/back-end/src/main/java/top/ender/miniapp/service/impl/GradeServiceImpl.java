package top.ender.miniapp.service.impl;

import top.ender.miniapp.entity.Grade;
import top.ender.miniapp.dao.GradeDao;
import top.ender.miniapp.entity.User;
import top.ender.miniapp.service.GradeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Grade)表服务实现类
 *
 * @author makejava
 * @since 2021-02-21 09:36:36
 */
@Service("gradeService")
public class GradeServiceImpl implements GradeService {
    @Resource
    private GradeDao gradeDao;

    /**
     * 通过ID查询单条数据
     *
     * @param gradeId 主键
     * @return 实例对象
     */
    @Override
    public Grade queryById(Integer gradeId) {
        return this.gradeDao.queryById(gradeId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<Grade> queryAllByLimit(int offset, int limit) {
        return this.gradeDao.queryAllByLimit(offset, limit);
    }

    /**
     * 通过实体作为筛选条件查询
     *
     * @param grade 实例对象
     * @return 对象列表
     */
    @Override
    public List<Grade> queryAll(Grade grade){
        return this.gradeDao.queryAll(grade);
    }

    /**
     * 新增数据
     *
     * @param grade 实例对象
     * @return 实例对象
     */
    @Override
    public Grade insert(Grade grade) {
        this.gradeDao.insert(grade);
        return grade;
    }

    /**
     * 修改数据
     *
     * @param grade 实例对象
     * @return 实例对象
     */
    @Override
    public Grade update(Grade grade) {
        this.gradeDao.update(grade);
        return this.queryById(grade.getGradeId());
    }

    /**
     * 通过主键删除数据
     *
     * @param gradeId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer gradeId) {
        return this.gradeDao.deleteById(gradeId) > 0;
    }

    @Override
    public List<Grade> queryGradeByUidAndTime(String uId, String gradeYear) {
        return this.gradeDao.queryByUidAndTime(uId,gradeYear);
    }

}
