package top.ender.miniapp.controller;

import top.ender.miniapp.entity.Consumption;
import top.ender.miniapp.entity.Message;
import top.ender.miniapp.service.ConsumptionService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * (Consumption)表控制层
 *
 * @author makejava
 * @since 2021-02-16 22:08:28
 */
@RestController
@RequestMapping("consumption")
public class ConsumptionController {
    /**
     * 服务对象
     */
    @Resource
    private ConsumptionService consumptionService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Consumption selectOne(Integer id) {
        return this.consumptionService.queryById(id);
    }

    /**
     * 按时间查找某用户的消费记录
     *
     * @param time 开始时间
     * @param uId 用户ID
     * @return 对象列表
     */
    @RequestMapping("selectByTime")
    public Message selectByTime(String time, String uId){
        List<Consumption> consumptions = this.consumptionService.queryByTime(time, uId);
        for (Consumption consumption : consumptions) {
            System.out.println(consumption);
        }
        Message<List<Consumption>> listMessage = new Message<>();
        listMessage.setMsg("获取成功");
        listMessage.setCode(1);
        listMessage.setData(consumptions);
        return listMessage;
    }

}
