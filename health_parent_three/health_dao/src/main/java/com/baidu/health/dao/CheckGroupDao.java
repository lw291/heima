package com.baidu.health.dao;

import com.baidu.health.pojo.CheckGroup;
import org.apache.ibatis.annotations.Param;

public interface CheckGroupDao {
    /*
    * 添加检查组
    * */
    void add(CheckGroup checkGroup);
/*
* 增加检查组与检查项的关系
* */
    void addCheckGroupCheckItem(@Param("checkgroupId") Integer checkGroupId, @Param("checkitemId") Integer checkitemId);
}
