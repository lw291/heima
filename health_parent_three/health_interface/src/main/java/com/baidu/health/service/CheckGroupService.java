package com.baidu.health.service;

import com.baidu.health.pojo.CheckGroup;
import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.exception.MyException;

import java.util.List;

public interface CheckGroupService {
    /*
    * 添加检查组
    * */
    void add(CheckGroup checkgroup, Integer[] checkitemIds);
    /*
     * 分页条件查询
     * */
    PageResult<CheckGroup> findPage(QueryPageBean queryPageBean);
    /**
     * 根据id查询检查组
     * @param id 检查组id
     * @return
     */
    CheckGroup findById(int id);

    /**
     * 通过id查询选中的检查项id
     * @param id 检查组id
     * @return
     */
    List<Integer> findCheckItemIdsByCheckGroupId(int id);
    /**
     * 编辑更新检查组
     * @param checkgroup
     * @param checkitemIds
     * @return
     */
    void update(CheckGroup checkgroup, Integer[] checkitemIds);

    /**
     * 删除检查组
     * @param id
     */
    void deleteById(int id)throws MyException;;
    /**
     * 查询所有检查组
     * @return
     */
    List<CheckGroup> findAll();

}
