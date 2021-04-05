package top.ender.miniapp.service.impl;

import top.ender.miniapp.entity.Store;
import top.ender.miniapp.dao.StoreDao;
import top.ender.miniapp.service.StoreService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Store)表服务实现类
 *
 * @author makejava
 * @since 2021-02-12 11:01:49
 */
@Service("storeService")
public class StoreServiceImpl implements StoreService {
    @Resource
    private StoreDao storeDao;

    /**
     * 通过ID查询单条数据
     *
     * @param storeId 主键
     * @return 实例对象
     */
    @Override
    public Store queryById(Integer storeId) {
        return this.storeDao.queryById(storeId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<Store> queryAllByLimit(int offset, int limit) {
        return this.storeDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param store 实例对象
     * @return 实例对象
     */
    @Override
    public Store insert(Store store) {
        this.storeDao.insert(store);
        return store;
    }

    /**
     * 修改数据
     *
     * @param store 实例对象
     * @return 实例对象
     */
    @Override
    public Store update(Store store) {
        this.storeDao.update(store);
        return this.queryById(store.getStoreId());
    }

    /**
     * 通过主键删除数据
     *
     * @param storeId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer storeId) {
        return this.storeDao.deleteById(storeId) > 0;
    }
}
