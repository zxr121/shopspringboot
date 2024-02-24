package org.shop.controller;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.shop.entity.OrderInfoEntity;
import org.shop.entity.ResultData;
import org.shop.service.OrderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.xml.transform.Result;
import java.util.List;


@RestController
@RequestMapping("/order")
@Api(tags = "订单管理")
public class OrderController {
    @Autowired
    private OrderInfoService orderInfoService;

    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping(value = "list", method = RequestMethod.POST)
    @ApiOperation(value = "返回订单列表")
    public ResultData<List<OrderInfoEntity>> readOrderList() {
        String key = "1";
        if(redisTemplate.hasKey(key)){
            Object o = redisTemplate.opsForValue().get(key);
            if(!StrUtil.isEmptyIfStr(o)){
                List<OrderInfoEntity> list = JSONObject.parseArray(String.valueOf(o),OrderInfoEntity.class);
                return new ResultData<>(0,"缓存中读取的数据",list);
            }
        }
        List<OrderInfoEntity> list = orderInfoService.list();
        String l = JSONObject.toJSONString(list);
        redisTemplate.opsForValue().set(key,l);
        return new ResultData<>(0,"数据库中读取的数据",list);
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ApiOperation(value = "新增订单")
    public ResultData addOrderInfo(@RequestBody OrderInfoEntity order) {
        boolean isSave = orderInfoService.save(order);
        if (isSave) {
            return new ResultData(0,"创建成功");
        }
        return new ResultData(1,"创建失败");
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    @ApiOperation(value = "更新订单")
    public ResultData updateOrderInfo(@RequestBody OrderInfoEntity order) {
        boolean isUpDate = orderInfoService.updateById(order);
        if (isUpDate) {
            return new ResultData<>(0,"更新成功");
        }
        return new ResultData(1,"更新失败");
    }

    @RequestMapping(value = "del", method = RequestMethod.POST)
    @ApiOperation(value = "删除订单")
    public ResultData delOrderInfo(@RequestBody String id) {
        boolean isDel = orderInfoService.removeById(id);
        if (isDel) {
            return new ResultData(0,"删除成功");
        }
        return new ResultData(1,"更新失败");
    }
}
