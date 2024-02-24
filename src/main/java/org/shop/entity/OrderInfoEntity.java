package org.shop.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("ORDER_INFO")
@ApiModel(value = "订单详情")
public class OrderInfoEntity implements Serializable {
    @TableId(type = IdType.INPUT)
    @ApiModelProperty(value = "订单编号")
    private String orderId;
    @ApiModelProperty(value = "商品名称")
    private String orderName;
    @ApiModelProperty(value = "商品价格")
    private Double orderPrice;
    @ApiModelProperty(value = "商品数量")
    private Long orderNum;
    @ApiModelProperty(value = "购买用户id")
    private String orderUser;
    @ApiModelProperty(value = "购买用户名")
    private String userName;
    @ApiModelProperty(value = "购买日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date orderDate;
    @ApiModelProperty(value = "支付金额")
    private Double userPayNum;
    @ApiModelProperty(value = "支付日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date userPayDate;
    @ApiModelProperty(value = "更新日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateDate;
    @ApiModelProperty(value = "创建日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createDate;
    @ApiModelProperty(value = "更新用户")
    private String userUpdate;
    @ApiModelProperty(value = "创建用户")
    private String userCreate;
}
