package com.w08e.data.pub.controller;


import com.w08e.data.pub.dto.CityDto;
import com.w08e.data.pub.dto.CityFilterDto;
import com.w08e.data.pub.service.CitySyncManager;
import com.w08e.data.pub.service.impl.CityServiceImpl;
import com.w08e.data.pub.util.Result;
import com.w08e.data.pub.vo.CityTreeVo;
import com.w08e.data.pub.vo.CityVo;
import com.w08e.data.pub.vo.QueryResult;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;



/**
 * 省市区
 * @author W08E
 */
@RestController
@AllArgsConstructor
@RequestMapping("/city")
public class CityController {

    private final CityServiceImpl cityService;

    @Resource
    private CitySyncManager citySyncManager;

    /**
     * 省市区 列表
     */
    @GetMapping
    public Result<QueryResult<CityVo>> list(CityFilterDto filter) {
        QueryResult<CityVo> list = cityService.listEs(filter);
        return Result.success(list);
    }

    @GetMapping("/es")
    public Result<QueryResult<CityVo>> listEs(CityFilterDto filter) {
        QueryResult<CityVo> list = cityService.listEs(filter);
        return Result.success(list);
    }


    /**
     * 省市区 树
     */
    @CrossOrigin(origins  ="*",allowCredentials = "true")
    @GetMapping("/tree")
    public Result<List<CityTreeVo>> tree(@RequestParam(required = false) List<Integer> topCode, @RequestParam(required = false) Integer maxDeep) {
//        return Result.ok(cityService.tree(topCode, maxDeep));
        return Result.success(cityService.tree(topCode, maxDeep));
    }
    /**
     * 省市区 树
     */
    @CrossOrigin(origins  ="*",allowCredentials = "true")
    @GetMapping("/tree-es")
    public Result<List<CityTreeVo>> treeEs(@RequestParam(required = false) List<Integer> topCode, @RequestParam(required = false) Integer maxDeep) {
        return Result.success(cityService.treeEs(topCode, maxDeep));
    }

    /**
     * 数据同步
     */
    @PostMapping("/sync")
    public void sync(@RequestBody CityDto cityDto) {
        citySyncManager.asyncWrite(cityDto);
    }


    /**
     * 存量数据同步
     */
    @GetMapping("/sync-es")
    public void tree() {
        cityService.syncCity2Es();
    }


}
