package com.baidu.health.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.baidu.health.pojo.CheckGroup;
import com.baidu.health.service.CheckGroupService;
import com.itheima.health.constant.MessageConstant;
import com.itheima.health.entity.Result;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/checkgroup")
public class CheckGroupController {
    @Reference
    private CheckGroupService checkGroupService;
/*
* 添加检查组
* */
    @RequestMapping("add")
    public Result add(@RequestBody CheckGroup checkGroup,Integer[] checkitemIds){
        checkGroupService.add(checkGroup,checkitemIds);
         return new Result(true, MessageConstant.ADD_CHECKGROUP_SUCCESS);

    }

}
