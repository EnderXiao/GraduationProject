package top.ender.miniapp.service.impl;

import top.ender.miniapp.dao.VirtualCardDao;
import top.ender.miniapp.entity.VirtualCard;
import top.ender.miniapp.service.VirtualCardService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (VirtualCard)表服务实现类
 *
 * @author makejava
 * @since 2021-02-12 10:58:11
 */
@Service("virtualcardService")
public class VirtualCardServiceImpl implements VirtualCardService {
    @Resource
    private VirtualCardDao virtualcardDao;

    /**
     * 通过ID查询单条数据
     *
     * @param vcId 主键
     * @return 实例对象
     */
    @Override
    public VirtualCard queryById(Integer vcId) {
        return this.virtualcardDao.queryById(vcId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<VirtualCard> queryAllByLimit(int offset, int limit) {
        return this.virtualcardDao.queryAllByLimit(offset, limit);
    }

    /**
     * 按用户Id查找
     *
     * @param uId 用户Id
     * @return 实列对象
     */
    @Override
    public VirtualCard queryByUserId(String uId) {
        return this.virtualcardDao.queryByUserId(uId);
    }

    /**
     * 新增数据
     *
     * @param virtualcard 实例对象
     * @return 实例对象
     */
    @Override
    public VirtualCard insert(VirtualCard virtualcard) {
        this.virtualcardDao.insert(virtualcard);
        return virtualcard;
    }

    /**
     * 修改数据
     *
     * @param virtualcard 实例对象
     * @return 实例对象
     */
    @Override
    public VirtualCard update(VirtualCard virtualcard) {
        this.virtualcardDao.update(virtualcard);
        return this.queryById(virtualcard.getVcId());
    }

    /**
     * 通过主键删除数据
     *
     * @param vcId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer vcId) {
        return this.virtualcardDao.deleteById(vcId) > 0;
    }

    /**
     * 通过用户ID删除数据
     *
     * @param uId 用户ID
     * @return 是否成功
     */
    @Override
    public boolean deleteByUserId(String uId) {
        return this.virtualcardDao.deleteByUserId(uId) > 0;
    }
}
