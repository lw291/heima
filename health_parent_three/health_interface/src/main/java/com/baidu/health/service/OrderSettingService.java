package com.baidu.health.service;

import com.baidu.health.pojo.OrderSetting;

import java.util.List;

public interface OrderSettingService {
    void addBatch(List<OrderSetting> orderSettingList);
}
