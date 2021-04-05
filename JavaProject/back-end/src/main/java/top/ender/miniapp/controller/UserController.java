package top.ender.miniapp.controller;

import top.ender.miniapp.entity.Message;
import top.ender.miniapp.entity.User;
import top.ender.miniapp.entity.VirtualCard;
import top.ender.miniapp.service.UserService;
import org.springframework.web.bind.annotation.*;
import top.ender.miniapp.service.VirtualCardService;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * (User)表控制层
 *
 * @author makejava
 * @since 2021-02-12 17:11:28
 */
@RestController
@RequestMapping("user")
public class UserController {
    /**
     * 服务对象
     */
    @Resource
    private UserService userService;
    @Resource
    private VirtualCardService virtualcardService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public User selectOne(String id) {
        return this.userService.queryById(id);
    }

    /**
     * 通过实体作为筛选条件查询
     *
     * @param user 实例对象
     * @return 对象列表
     */
    @RequestMapping("findYou")
    public Message<List<User>> findYou(User user){
        System.out.println(user);
        List<User> users = this.userService.queryAll(user);
        Message<List<User>> userMessage = new Message<>();
        if(users.size() == 0){
            userMessage.setCode(0);
            userMessage.setMsg("没有信息");
        }else{
            userMessage.setCode(1);
            userMessage.setMsg("查询成功");
            userMessage.setData(users);
        }
        System.out.println(userMessage);
        return userMessage;
    }

    /**
     * 登录方法
     *
     * @param uId Integer
     * @param password String
     * @return 实例对象
     */
    @RequestMapping("logIn")
    public Message<User> logIn(String uId,String password,String lastEditTime) throws ParseException {
        System.out.println(uId);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        Date lastEditTimeDate = dateFormat.parse(lastEditTime);
        User user = this.userService.logIn(uId, password,lastEditTimeDate);
        Message<User> message = new Message<>();
        if(user != null){
            message.setCode(1);
            message.setMsg("登录成功");
            message.setData(user);
        }else{
            message.setCode(0);
            message.setMsg("用户名或密码错误");
        }
        return message;
    }

    /**
     * 更改密码
     *
     * @param oldPassword 老密码
     * @param newPassword 新密码
     * @param type 更改密码类型
     * @return Message<Integer>
     */
    @RequestMapping("changePassword")
    public Message<Integer> changePassword(String time,String uId,String oldPassword, String newPassword, Integer type) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        Date lastEditTimeDate = dateFormat.parse(time);
        Message<Integer> integerMessage = new Message<>();
        if(type.equals(1)){
            User user = this.userService.queryById(uId);
            if(user.getPassword().equals(oldPassword)){
                User userNew = new User();
                userNew.setUId(user.getUId());
                userNew.setPassword(newPassword);
                userNew.setLastEditTime(lastEditTimeDate);
                User update = this.userService.update(userNew);
                if(update != null){
                    System.out.println(update);
                    integerMessage.setCode(1);
                    integerMessage.setData(1);
                    integerMessage.setMsg("修改密码成功！请重新登录");
                }
                else{
                    integerMessage.setCode(0);
                    integerMessage.setData(0);
                    integerMessage.setMsg("修改密码失败，请联系管理员");
                }
            }
            else{
                integerMessage.setCode(0);
                integerMessage.setData(0);
                integerMessage.setMsg("旧密码错误，请确认后再次输入");
            }
        }else{
            VirtualCard virtualCard = this.virtualcardService.queryByUserId(uId);
            if(virtualCard.getVcPassword().equals(oldPassword)){
                VirtualCard virtualCardNew = new VirtualCard();
                virtualCardNew.setVcId(virtualCard.getVcId());
                virtualCardNew.setVcPassword(newPassword);
                virtualCardNew.setLastEditTime(lastEditTimeDate);
                VirtualCard update = this.virtualcardService.update(virtualCardNew);
                if(update != null){
                    integerMessage.setCode(1);
                    integerMessage.setData(1);
                    integerMessage.setMsg("修改账户密码成功！请重新登录");
                }
                else{
                    integerMessage.setCode(0);
                    integerMessage.setData(0);
                    integerMessage.setMsg("修改账户密码失败，请联系管理员");
                }
            }
            else{
                integerMessage.setCode(0);
                integerMessage.setData(0);
                integerMessage.setMsg("旧密码错误，请确认后再次输入");
            }
        }
        return integerMessage;
    }

    /**
     * 分页获取所有用户
     *
     * @param uId 用户名
     * @param start 起始位置
     * @param length 结束位置
     * @return 对象列表
     */
    @RequestMapping("getAllUers")
    public Message<List<User>> getAllUsers(String uId,Integer start,Integer length){
        User user = this.userService.queryById(uId);
        System.out.println(start);
        System.out.println(length);
        Message<List<User>> listMessage = new Message<>();
        if(!user.getUserStatus().equals(1)){
            List<User> users = this.userService.queryAllByLimit(start, length);
            for (User userNow : users) {
                System.out.println(userNow);
            }
            listMessage.setData(users);
            listMessage.setCode(1);
            listMessage.setMsg("查找成功！");
        }else{
            listMessage.setData(null);
            listMessage.setCode(0);
            listMessage.setMsg("账号不是管理员，无法获取，请联系管理员！");
        }
        return listMessage;
    }

    @RequestMapping("addUser")
    public Message<Integer> addUser(String uId,String username,Integer classId, Integer userStatus, String time,String bankCard) throws ParseException {
        System.out.println(uId);
        System.out.println(username);
        System.out.println(classId);
        System.out.println(userStatus);
        System.out.println(time);
        System.out.println(bankCard);
        Message<Integer> integerMessage = new Message<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        Date lastEditTimeDate = dateFormat.parse(time);
        User user1 = this.userService.queryById(uId);
        if(user1 != null){
            integerMessage.setCode(0);
            integerMessage.setData(0);
            integerMessage.setMsg("用户已存在");
            return integerMessage;
        }
        User user = new User();
        user.setPassword("888888");
        user.setUId(uId);
        user.setUsername(username);
        user.setUserStatus(userStatus);
        user.setClassesId(classId);
        user.setLastEditTime(lastEditTimeDate);
        System.out.println(user);
        User insert = userService.insert(user);
        if(insert == null){
            integerMessage.setCode(0);
            integerMessage.setData(0);
            integerMessage.setMsg("添加失败，请联系管理员");
        }else{
            VirtualCard virtualCard = new VirtualCard();
            virtualCard.setCardStatus(1);
            virtualCard.setRestMoney(0.00);
            virtualCard.setVcPassword("888888");
            virtualCard.setUId(uId);
            virtualCard.setLastEditTime(lastEditTimeDate);
            virtualCard.setCreatTime(lastEditTimeDate);
            virtualCard.setBankCard(bankCard);
            virtualCard.setBankName("建设银行");
            VirtualCard insert1 = this.virtualcardService.insert(virtualCard);
            if (insert1 == null){
                integerMessage.setCode(0);
                integerMessage.setData(0);
                integerMessage.setMsg("添加校园卡时出错,请联系管理员!");
            }else {
                integerMessage.setCode(1);
                integerMessage.setData(1);
                integerMessage.setMsg("添加成功！密码已设为初始密码");
            }
        }
        return integerMessage;
    }

    /**
     * 删除用户
     *
     * @param uIds 学号列表
     * @return Message<Integer>
     */
    @RequestMapping("delete")
    public Message<Integer> delete(@RequestParam("uIds") List<String> uIds){
        Message<Integer> integerMessage = new Message<>();
        int successNo = 0;
        int length = uIds.size();
        for (String uId : uIds) {
            boolean result = userService.deleteById(uId);
            boolean resultVirtualCard = virtualcardService.deleteByUserId(uId);
            if(result && resultVirtualCard){
                successNo++;
            }
        }
        if(successNo == 0){
            integerMessage.setCode(0);
            integerMessage.setData(0);
            integerMessage.setMsg("删除失败，请联系管理员");
        }
        else {
            integerMessage.setCode(1);
            integerMessage.setData(successNo);
            integerMessage.setMsg("选中" + length + "个," + "成功删除" + successNo + "个");
        }
        return integerMessage;
    }

    /**
     * 重置密码
     *
     * @param uIds 学号列表
     * @return Message<Integer> 重置成功数
     */
    @RequestMapping("resetPassword")
    public Message<Integer> resetPassword(@RequestParam("uIds") List<String> uIds){
        Message<Integer> integerMessage = new Message<>();
        int successNo = 0;
        int length = uIds.size();
        for (String uId : uIds) {
            User userNow = new User();
            VirtualCard virtualCard = new VirtualCard();
            userNow.setUId(uId);
            userNow.setPassword("888888");
            virtualCard.setUId(uId);
            virtualCard.setVcPassword("888888");
            VirtualCard virtualCardOld = this.virtualcardService.queryByUserId(uId);
            virtualCard.setVcId(virtualCardOld.getVcId());
            virtualCard.setCardStatus(1);
            User update = userService.update(userNow);
            VirtualCard updateVirtualCard = virtualcardService.update(virtualCard);
            if(update != null && updateVirtualCard!= null){
                successNo++;
            }
        }
        if(successNo == 0){
            integerMessage.setCode(0);
            integerMessage.setData(0);
            integerMessage.setMsg("重置失败，请联系管理员");
        }
        else {
            integerMessage.setCode(1);
            integerMessage.setData(successNo);
            integerMessage.setMsg("选中" + length + "个," + "成功重置" + successNo + "个");
        }
        return integerMessage;
    }
}
