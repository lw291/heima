package com.baidu.health.service.impl;


import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.config.annotation.Service;
import com.baidu.health.dao.SetmealDao;
import com.baidu.health.pojo.Setmeal;
import com.baidu.health.service.SetmealService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.exception.MyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service(interfaceClass = SetmealService.class)
public class SetmealServiceImpl implements SetmealService {
    @Autowired
    private SetmealDao setmealDao;

    /**
     * 添加套餐
     * @param setmeal
     * @param checkgroupIds
     */
    @Override
    public void add(Setmeal setmeal, Integer[] checkgroupIds) {
//        添加套餐
        setmealDao.add(setmeal);
//        获取套餐id
        Integer setmealId = setmeal.getId();
//        遍历数组
        if (null!=checkgroupIds){
            for (Integer checkgroupId : checkgroupIds) {
//                添加套餐与检查组关系
                setmealDao.addSetmealCheckGroup(setmealId,checkgroupId);
            }
        }
    }
    /**
     * 分页查询
     * @param queryPageBean
     * @return
     */
    @Override
    public PageResult<Setmeal> findPage(QueryPageBean queryPageBean) {
        PageHelper.startPage(queryPageBean.getCurrentPage(),queryPageBean.getPageSize());
//        判断条件查询
        if(StringUtils.isNotEmpty(queryPageBean.getQueryString())){
//            模糊查询
            queryPageBean.setQueryString("%"+queryPageBean.getQueryString()+"%");
        }
        Page<Setmeal>setmealPage= setmealDao.findByCondition(queryPageBean.getQueryString());
        return new PageResult<Setmeal>(setmealPage.getTotal(),setmealPage.getResult());
    }
    /**
     * 通过id查询
     * @param id
     * @return
     */
    @Override
    public Setmeal findById(int id) {
        return setmealDao.findById(id);
    }

    /**
     * 查询选中的检查组的集合
     * @param id
     * @return
     */
    @Override
    public List<Integer> findCheckGroupIdsBySetmealId(int id) {

        return setmealDao.findCheckGroupIdsBySetmealId(id);
    }

    /**
     * 更新套餐
     * @param setmeal
     * @param checkgroupIds
     */
    @Override
    @Transactional
    public void update(Setmeal setmeal, Integer[] checkgroupIds) {
//        更新套餐
        setmealDao.update(setmeal);
//        删除旧关系
        setmealDao.deleteSetmealCheckGroup(setmeal.getId());
        if (null!=checkgroupIds) {
            for (Integer checkgroupId : checkgroupIds) {
                setmealDao.addSetmealCheckGroup(setmeal.getId(),checkgroupId);
            }
        }

    }

    @Override
    public void deleteById(int id) {
//        先判断该套餐是否被引用了
        int count=setmealDao.findCountBySetmealId(id);
        if (count>0) {
            throw new MyException("该套餐被订单使用了，不能删除");
        }
//        如果没有使用,就先删除检查组与套餐的关系
        setmealDao.deleteSetmealCheckGroup(id);
//        最后再删除套餐
        setmealDao.deleteById(id);
    }
}
