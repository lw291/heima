package com.baidu.health.dao;

import com.baidu.health.pojo.CheckGroup;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CheckGroupDao {
    /*
    * 添加检查组
    * */
    void add(CheckGroup checkGroup);
/*
* 增加检查组与检查项的关系
* */
    void addCheckGroupCheckItem(@Param("checkgroupId") Integer checkgroupId, @Param("checkitemId") Integer checkitemId);
/*
* 分页条件查询
* */
    Page<CheckGroup> findByCondition(String queryString);

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

     * @return
     */
    void update(CheckGroup checkgroup);

    /**
     * 删除旧关系
     * @param id
     */
    void deleteCheckGroupCheckItem(Integer id);

    /**
     * 通过id统计使用的个数
     * @param id
     * @return
     */
    int findCountByCheckGroupId(int id);

    /**
     * 删除检查组
     * @param id
     */
    void deleteById(int id);
}
