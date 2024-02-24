package org.shop.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.shop.entity.OrderInfoEntity;
import org.shop.mapper.OrderInfoMapper;
import org.shop.service.OrderInfoService;
import org.springframework.stereotype.Service;

@Service
public class OrderInfoServiceImpl extends ServiceImpl<OrderInfoMapper, OrderInfoEntity> implements OrderInfoService {

}
