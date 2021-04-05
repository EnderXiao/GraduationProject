package top.ender.miniapp.service.impl;

import top.ender.miniapp.entity.User;
import top.ender.miniapp.dao.UserDao;
import top.ender.miniapp.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * (User)表服务实现类
 *
 * @author makejava
 * @since 2021-02-12 17:11:28
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    /**
     * 通过ID查询单条数据
     *
     * @param uId 主键
     * @return 实例对象
     */
    @Override
    public User queryById(String uId) {
        return this.userDao.queryById(uId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<User> queryAllByLimit(int offset, int limit) {
        return this.userDao.queryAllByLimit(offset, limit);
    }

    /**
     * 通过实体作为筛选条件查询
     *
     * @param user 实例对象
     * @return 对象列表
     */
    @Override
    public List<User> queryAll(User user){
        return this.userDao.queryAll(user);
    }

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    public User insert(User user) {
        this.userDao.insert(user);
        return user;
    }

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    public User update(User user) {
        this.userDao.update(user);
        return this.queryById(user.getUId());
    }

    /**
     * 通过主键删除数据
     *
     * @param uId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String uId) {
        return this.userDao.deleteById(uId) > 0;
    }

    /**
     * 登录事件
     *
     * @param uId Integer
     * @param password String
     * @param lastEditTime Date
     * @return 实列对象
     */
    @Override
    public User logIn(String uId, String password, Date lastEditTime){
        User user = this.userDao.queryById(uId);
        if(user != null){
            boolean equals = user.getPassword().equals(password);
            if(equals)
            {
                user.setLastEditTime(lastEditTime);
                int update = this.userDao.update(user);
                if(update == 0) {
                    return null;
                }
                return user;
            }
        }
        return null;
    }
}
