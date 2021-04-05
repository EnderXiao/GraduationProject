package top.ender.miniapp.controller;

import top.ender.miniapp.entity.Message;
import top.ender.miniapp.entity.VirtualCard;
import top.ender.miniapp.service.VirtualCardService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * (VirtualCard)表控制层
 *
 * @author makejava
 * @since 2021-02-12 10:58:11
 */
@RestController
@RequestMapping("virtualCard")
public class VirtualCardController {
    /**
     * 服务对象
     */
    @Resource
    private VirtualCardService virtualCardService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public VirtualCard selectOne(Integer id) {
        return this.virtualCardService.queryById(id);
    }

    /**
     * 通过用户名查找校园卡信息
     *
     * @param uId 用户Id
     * @return 实列对象
     */
    @PostMapping("findYourVc")
    public Message<VirtualCard> findYourVc(String uId){
        System.out.println(uId);
        VirtualCard virtualCard = this.virtualCardService.queryByUserId(uId);
        Message<VirtualCard> message = new Message<>();
        if(virtualCard != null){
            message.setCode(1);
            message.setMsg("获取成功");
            message.setData(virtualCard);
        }else{
            message.setCode(0);
            message.setMsg("获取失败");
        }
        System.out.println(message);
        return message;
    }

    @RequestMapping("charge")
    public Message<Integer> charge(String uId,Integer money,String time,String password) throws ParseException {
        Message<Integer> integerMessage = new Message<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        Date lastEditTimeDate = dateFormat.parse(time);
        VirtualCard virtualCard = this.virtualCardService.queryByUserId(uId);
        if(virtualCard.getVcPassword().equals(password)){
            VirtualCard virtualCardNew = new VirtualCard();
            virtualCardNew.setVcId(virtualCard.getVcId());
            virtualCardNew.setLastEditTime(lastEditTimeDate);
            virtualCardNew.setRestMoney(virtualCard.getRestMoney() + money);
            VirtualCard update = this.virtualCardService.update(virtualCardNew);
            if(update != null){
                integerMessage.setCode(1);
                integerMessage.setData(1);
                integerMessage.setMsg("充值提交成功, 已将请求发送至服务器，请稍后返回主页下拉刷新查看。");
            }else{
                integerMessage.setCode(0);
                integerMessage.setData(0);
                integerMessage.setMsg("充值失败,请联系管理员检查系统");
            }
        }else{
            integerMessage.setCode(0);
            integerMessage.setData(0);
            integerMessage.setMsg("密码错误, 请检查后重试");
        }
        return integerMessage;
    }

    @RequestMapping("reportLoss")
    public Message<Integer> reportLoss(String time,String uId,String password) throws ParseException {
        Message<Integer> integerMessage = new Message<>();
        VirtualCard virtualCard = this.virtualCardService.queryByUserId(uId);
        VirtualCard virtualCardNew = new VirtualCard();
        virtualCardNew.setVcId(virtualCard.getVcId());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        Date lastEditTimeDate = dateFormat.parse(time);
        virtualCardNew.setLastEditTime(lastEditTimeDate);
        if(password.equals(virtualCard.getVcPassword())){
            Integer cardStatus = virtualCard.getCardStatus();
            if(cardStatus.equals(1)){
                cardStatus = 0;
                integerMessage.setMsg("校园卡冻结成功！");
                integerMessage.setData(0);

            }
            else{
                cardStatus = 1;
                integerMessage.setMsg("校园卡激活成功！");
                integerMessage.setData(1);
            }
            virtualCardNew.setCardStatus(cardStatus);
            VirtualCard update = virtualCardService.update(virtualCardNew);
            if(update != null){
                integerMessage.setCode(1);
            }else{
                integerMessage.setCode(0);
                integerMessage.setData(0);
                integerMessage.setMsg("设置状态失败，请联系管理员");
            }
        }
        else{
            integerMessage.setCode(0);
            integerMessage.setData(0);
            integerMessage.setMsg("密码错误，请检查后重新输入");
        }
        return integerMessage;
    }
}
