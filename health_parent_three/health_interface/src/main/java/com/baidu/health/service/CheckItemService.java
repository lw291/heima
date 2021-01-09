package com.baidu.health.service;

import com.baidu.health.pojo.CheckItem;
import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.exception.MyException;

import java.util.List;

public interface CheckItemService {
/*
* 查询所有
* */
    List<CheckItem> findAll();
/*
* 添加检查项
* */
    void add(CheckItem checkItem);

/*
* 检查项的分页查询
* */
    PageResult<CheckItem> findPage(QueryPageBean queryPageBean);


    /*
     * 编辑回显
     * */
    CheckItem findById(int id);
/*
* 修改检查项
* */
    void update(CheckItem checkItem);
/*
* 删除检查项
* */

    void deleteById(int id)throws MyException;
}
