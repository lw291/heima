package com.baidu.health.service.impl;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.config.annotation.Service;
import com.baidu.health.dao.CheckGroupDao;
import com.baidu.health.pojo.CheckGroup;
import com.baidu.health.service.CheckGroupService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.exception.MyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service(interfaceClass = CheckGroupService.class)
public class CheckGroupServiceImpl implements CheckGroupService {
    @Autowired
    private CheckGroupDao checkGroupDao;
    /*
    * 添加检查组
    * */
    @Override
    @Transactional
    public void add(CheckGroup checkgroup, Integer[] checkitemIds) {
//        添加检查组
        checkGroupDao.add(checkgroup);
//        获取检查组的id
        Integer checkgroupId = checkgroup.getId();
        if (null!=checkitemIds) {
            for (Integer checkitemId : checkitemIds) {
//                添加检查组与检查项的关系
                checkGroupDao.addCheckGroupCheckItem(checkgroupId, checkitemId);

            }


        }

    }

    /*
     * 分页条件查询
     * */
    @Override
    public PageResult<CheckGroup> findPage(QueryPageBean queryPageBean) {
        // 使用PageHelper.startPage
        PageHelper.startPage(queryPageBean.getCurrentPage(), queryPageBean.getPageSize());
        if(StringUtils.isNotEmpty(queryPageBean.getQueryString())){
            // 拼接%
            queryPageBean.setQueryString("%" + queryPageBean.getQueryString()+ "%");
        }
        // 紧接着的查询会被分页
        Page<CheckGroup> page = checkGroupDao.findByCondition(queryPageBean.getQueryString());
        return new PageResult<CheckGroup>(page.getTotal(), page.getResult());
    }
    /**
     * 根据id查询检查组
     * @param id 检查组id
     * @return
     */
    @Override
    public CheckGroup findById(int id) {

        return checkGroupDao.findById(id);
    }

    /**
     * 通过id查询选中的检查项id
     * @param id 检查组id
     * @return
     */
    @Override
    public List<Integer> findCheckItemIdsByCheckGroupId(int id) {

        return checkGroupDao.findCheckItemIdsByCheckGroupId(id);
    }
    /**
     * 编辑更新检查组
     * @param checkgroup
     * @param checkitemIds
     * @return
     */
    @Override
    public void update(CheckGroup checkgroup, Integer[] checkitemIds) {
        checkGroupDao.update(checkgroup);
        //- 先删除旧关系
        checkGroupDao.deleteCheckGroupCheckItem(checkgroup.getId());
        if (null!=checkitemIds) {
            for (Integer checkitemId : checkitemIds) {
                //- 添加检查组与检查项的关系
                checkGroupDao.addCheckGroupCheckItem(checkgroup.getId(), checkitemId);
            }
        }
    }
    /**
     * 删除检查组
     * @param id
     * @return
     */
    @Override
    @Transactional
    public void deleteById(int id) {
//        检查id是否被使用
        int count = checkGroupDao.findCountByCheckGroupId(id);
        if(count > 0){
            throw new MyException("该检查组被套餐使用了，不能删除");
        }
        // 如果没使用，就删除关系
        checkGroupDao.deleteCheckGroupCheckItem(id);
        // 删除检查组
        checkGroupDao.deleteById(id);

    }
    /**
     * 查询所有检查组
     * @return
     */
    @Override
    public List<CheckGroup> findAll() {

        return checkGroupDao.findAll();
    }
}
