package com.baidu.health.service.impl;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.config.annotation.Service;
import com.baidu.health.dao.CheckItemDao;
import com.baidu.health.pojo.CheckItem;
import com.baidu.health.service.CheckItemService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.exception.MyException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service(interfaceClass = CheckItemService.class)
public class CheckItemServiceImpl implements CheckItemService {
    @Autowired
    private CheckItemDao checkItemDao;

    /*
     * 查询所有
     * */
    @Override
    public List<CheckItem> findAll() {
        return checkItemDao.findAll();

    }
    /*
    * 添加检查项
    * */
    @Override
    public void add(CheckItem checkItem) {
        checkItemDao.add(checkItem);
    }
/*
* 检查项的 分页查询
* */
    @Override
    public PageResult<CheckItem> findPage(QueryPageBean queryPageBean) {
        PageHelper.startPage(queryPageBean.getCurrentPage(), queryPageBean.getPageSize());
//       判断查询条件是否为空
        if (StringUtils.isNotEmpty(queryPageBean.getQueryString())){
//            模糊查询
            queryPageBean.setQueryString("%"+queryPageBean.getQueryString()+"%");
            }
        // 紧接着的查询语句会被分页
        Page<CheckItem>page=checkItemDao.findByCondition(queryPageBean.getQueryString());
//        查询结果封装到对象中
        PageResult<CheckItem> pageResult=new PageResult<CheckItem>(page.getTotal(),page.getResult());

        return pageResult;
    }
/*
* 编辑回显
* */
    @Override
    public CheckItem findById(int id) {

        return checkItemDao.findById(id);
    }

    /*
    * 编辑更新
    * */
    @Override
    public void update(CheckItem checkItem) {
        checkItemDao.update(checkItem);
    }
/*
* 通过id删除
* */
    @Override
    public void deleteById(int id) throws MyException {
//        统计id被使用个数
     int count= checkItemDao.findCountByCheckItemId(id);
     if(count>0){
         throw new MyException("该检查项被使用了，不能删除!");
     }
        // 删除
        checkItemDao.deleteById(id);

    }
}
