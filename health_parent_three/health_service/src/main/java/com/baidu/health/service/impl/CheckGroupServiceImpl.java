package com.baidu.health.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baidu.health.dao.CheckGroupDao;
import com.baidu.health.pojo.CheckGroup;
import com.baidu.health.service.CheckGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
@Service(interfaceClass = CheckGroupService.class)
public class CheckGroupServiceImpl implements CheckGroupService {
    @Autowired
    private CheckGroupDao checkGroupDao;
    /*
    * 添加检查组
    * */
    @Override
    @Transactional
    public void add(CheckGroup checkGroup, Integer[] checkitemIds) {
//        添加检查组
        checkGroupDao.add(checkGroup);
//        获取检查组的id
        Integer checkgroupId = checkGroup.getId();
        if (null!=checkitemIds) {
            for (Integer checkitemId : checkitemIds) {
//                添加检查组与检查项的关系
                checkGroupDao.addCheckGroupCheckItem(checkgroupId, checkitemId);

            }


        }

    }
}
