package com.baidu.health.dao;

import com.baidu.health.pojo.CheckItem;
import com.github.pagehelper.Page;

import java.util.List;

public interface CheckItemDao {

/*
* 查询所有
*/
    List<CheckItem> findAll();
/*
* 添加检查项
* */
    void add(CheckItem checkItem);
/*
* 分页条件查询
* */
    Page<CheckItem> findByCondition(String queryString);


    /*
     * 编辑回显
     * */
    CheckItem findById(int id);
/*
* 编辑更新
* */
    void update(CheckItem checkItem);
/*
* 统计使用这个id的个数
* */
    int findCountByCheckItemId(int id);

    void deleteById(int id);
}
