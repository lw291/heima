package com.baidu.health.service;

import com.baidu.health.pojo.Setmeal;
import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.QueryPageBean;

import java.util.List;

public interface SetmealService {
    /**
     * 添加套餐
     * @param setmeal
     * @param checkgroupIds
     */
    void add(Setmeal setmeal, Integer[] checkgroupIds);

    /**
     * 分页查询
     * @param queryPageBean
     * @return
     */
    PageResult<Setmeal> findPage(QueryPageBean queryPageBean);

    /**
     * 通过id查询
     * @param id
     * @return
     */
    Setmeal findById(int id);
    /**
     * 查询选中的检查组的集合
     * @param id
     * @return
     */
    List<Integer> findCheckGroupIdsBySetmealId(int id);

    void update(Setmeal setmeal, Integer[] checkgroupIds);

    /**
     * 通过id删除套餐
     * @param id
     */
    void deleteById(int id);
}
