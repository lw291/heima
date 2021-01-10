package com.baidu.health.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.baidu.health.pojo.CheckGroup;
import com.baidu.health.service.CheckGroupService;
import com.itheima.health.constant.MessageConstant;
import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.entity.Result;
import com.sun.org.apache.regexp.internal.RE;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/checkgroup")
public class CheckGroupController {
    @Reference
    private CheckGroupService checkGroupService;
/*
* 添加检查组
* */
    @RequestMapping("/add")
    public Result add(@RequestBody CheckGroup checkgroup,Integer[] checkitemIds){
        checkGroupService.add(checkgroup,checkitemIds);
         return new Result(true, MessageConstant.ADD_CHECKGROUP_SUCCESS);

    }
    /*
    * 分页条件查询
    * */
    @PostMapping("/findPage")
    public Result findPage(@RequestBody QueryPageBean queryPageBean){
        PageResult<CheckGroup> pageResult=  checkGroupService.findPage(queryPageBean);
        return new Result(true,MessageConstant.QUERY_CHECKGROUP_SUCCESS,pageResult);
    }

    /**
     * 根据id查询检查组
     * @param id 检查组id
     * @return
     */
    @GetMapping("/findById")
    public Result findById (int id){
        CheckGroup checkGroup=checkGroupService.findById(id);
        return new Result(true, MessageConstant.QUERY_CHECKGROUP_SUCCESS,checkGroup);
    }

    /**
     * 通过id查询选中的检查项id
     * @param id 检查组id
     * @return
     */
 @GetMapping("/findCheckItemIdsByCheckGroupId")
    public Result findCheckItemIdsByCheckGroupId(int id){
     List<Integer>checkItemIds= checkGroupService.findCheckItemIdsByCheckGroupId(id);
     return new Result(true, MessageConstant.QUERY_CHECKITEM_SUCCESS,checkItemIds);
 }

    /**
     * 编辑更新检查组
     * @param checkgroup
     * @param checkitemIds
     * @return
     */
  @PostMapping("/update")
    public Result update (@RequestBody CheckGroup checkgroup,Integer[] checkitemIds){
     checkGroupService.update(checkgroup,checkitemIds);
      // 响应结果
      return new Result(true, MessageConstant.EDIT_CHECKGROUP_SUCCESS);
  }

    /**
     * 删除检查组
     * @param id
     * @return
     */
    @PostMapping("/deleteById")
    public Result deleteById(int id){
        //调用服务删除
        checkGroupService.deleteById(id);
        return new Result(true, MessageConstant.DELETE_CHECKGROUP_SUCCESS);
    }

}
