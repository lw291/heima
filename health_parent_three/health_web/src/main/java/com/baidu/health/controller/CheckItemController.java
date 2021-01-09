package com.baidu.health.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.QueryPageBean;
import com.baidu.health.pojo.CheckItem;
import com.baidu.health.service.CheckItemService;
import com.itheima.health.constant.MessageConstant;
import com.itheima.health.entity.Result;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/checkitem")
public class CheckItemController {
    /*
    * 查询所有
    * */
    @Reference
    private CheckItemService checkItemService;
    @GetMapping("/findAll")
    public Result finAll(){
        List<CheckItem> list=checkItemService.findAll();
        return new Result(true , MessageConstant.QUERY_CHECKGROUP_SUCCESS,list);
    }
    /*
     * 添加检查项
     * */
    @PostMapping("/add")
    public Result add(@RequestBody CheckItem checkItem){
        checkItemService.add(checkItem);
        return new Result(true,MessageConstant.ADD_CHECKITEM_FAIL);
    }
    /*
     * 检查项的分页查询
     * */
    @PostMapping("/findPage")
    public Result findPage (@RequestBody QueryPageBean queryPageBean){
        // 调用业务来分页，获取分页结果
        PageResult<CheckItem> pageResult = checkItemService.findPage(queryPageBean);
        return new Result(true,MessageConstant.QUERY_CHECKITEM_SUCCESS,pageResult);
    }

    /*
     * 编辑回显
     * */
    @GetMapping("/findById")
    public  Result findById(int id){
     CheckItem checkItem=checkItemService.findById(id);

        return new Result(true,MessageConstant.QUERY_CHECKITEM_SUCCESS,checkItem);
    }
    /*
   更新
     */
    @PostMapping("/update")
    public Result update (@RequestBody CheckItem checkItem ){
        checkItemService.update(checkItem);
        return new Result(true,MessageConstant.EDIT_CHECKITEM_SUCCESS);
    }
    @PostMapping("deleteById")
    public  Result deleteById (int id){
        checkItemService.deleteById(id);
        return new Result(true,MessageConstant.DELETE_CHECKITEM_SUCCESS);
    }
}
