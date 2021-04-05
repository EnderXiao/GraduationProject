package top.ender.miniapp.controller;

import top.ender.miniapp.entity.Store;
import top.ender.miniapp.service.StoreService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Store)表控制层
 *
 * @author makejava
 * @since 2021-02-12 11:01:49
 */
@RestController
@RequestMapping("store")
public class StoreController {
    /**
     * 服务对象
     */
    @Resource
    private StoreService storeService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Store selectOne(Integer id) {
        return this.storeService.queryById(id);
    }

}
