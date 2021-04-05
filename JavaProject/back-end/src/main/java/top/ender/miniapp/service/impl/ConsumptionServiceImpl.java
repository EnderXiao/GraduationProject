package top.ender.miniapp.service.impl;

import top.ender.miniapp.entity.Consumption;
import top.ender.miniapp.dao.ConsumptionDao;
import top.ender.miniapp.service.ConsumptionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Consumption)表服务实现类
 *
 * @author makejava
 * @since 2021-02-16 22:08:27
 */
@Service("consumptionService")
public class ConsumptionServiceImpl implements ConsumptionService {
    @Resource
    private ConsumptionDao consumptionDao;

    /**
     * 通过ID查询单条数据
     *
     * @param cspId 主键
     * @return 实例对象
     */
    @Override
    public Consumption queryById(Integer cspId) {
        return this.consumptionDao.queryById(cspId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<Consumption> queryAllByLimit(int offset, int limit) {
        return this.consumptionDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param consumption 实例对象
     * @return 实例对象
     */
    @Override
    public Consumption insert(Consumption consumption) {
        this.consumptionDao.insert(consumption);
        return consumption;
    }

    /**
     * 修改数据
     *
     * @param consumption 实例对象
     * @return 实例对象
     */
    @Override
    public Consumption update(Consumption consumption) {
        this.consumptionDao.update(consumption);
        return this.queryById(consumption.getCspId());
    }

    /**
     * 通过主键删除数据
     *
     * @param cspId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer cspId) {
        return this.consumptionDao.deleteById(cspId) > 0;
    }

    @Override
    public List<Consumption> queryByTime(String time, String uId) {
        return this.consumptionDao.queryByTime(time,uId);
    }
}
