package top.ender.miniapp.service;

import top.ender.miniapp.entity.VirtualCard;

import java.util.List;

/**
 * (VirtualCard)表服务接口
 *
 * @author makejava
 * @since 2021-02-12 10:58:11
 */
public interface VirtualCardService {

    /**
     * 通过ID查询单条数据
     *
     * @param vcId 主键
     * @return 实例对象
     */
    VirtualCard queryById(Integer vcId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<VirtualCard> queryAllByLimit(int offset, int limit);

    /**
     * 按用户Id查找
     *
     * @param uId 用户Id
     * @return 实列对象
     */
    VirtualCard queryByUserId(String uId);

    /**
     * 新增数据
     *
     * @param virtualcard 实例对象
     * @return 实例对象
     */
    VirtualCard insert(VirtualCard virtualcard);

    /**
     * 修改数据
     *
     * @param virtualcard 实例对象
     * @return 实例对象
     */
    VirtualCard update(VirtualCard virtualcard);

    /**
     * 通过主键删除数据
     *
     * @param vcId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer vcId);

    /**
     * 通过用户ID删除数据
     *
     * @param uId 用户ID
     * @return 是否成功
     */
    boolean deleteByUserId(String uId);

}
