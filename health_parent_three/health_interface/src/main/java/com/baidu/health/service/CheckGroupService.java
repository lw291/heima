package com.baidu.health.service;

import com.baidu.health.pojo.CheckGroup;

public interface CheckGroupService {
    /*
    * 添加检查组
    * */
    void add(CheckGroup checkGroup, Integer[] checkitemIds);

}
