package com.longxingyu.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.longxingyu.reggie.common.R;
import com.longxingyu.reggie.dto.DishDto;
import com.longxingyu.reggie.pojo.Dish;
import com.longxingyu.reggie.service.DishService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * {@code @Create:}  2023-06-08  18 : 19
 * {@code @Author:} Cherry
 * {@code @ToUser:} Be Happy EveryDay
 * ------------------------------------
 * {@code @note:}
 */

/**
 * 菜品管理
 */
@SuppressWarnings({"all"})
@RestController
@Slf4j
@RequestMapping("/dish")
public class DishController {
    @Autowired
    private DishService dishService;

    /**
     * 新增菜品
     *
     * @param dishDto
     * @return
     */
    @PostMapping
    public R<String> save(@RequestBody DishDto dishDto) {
        log.info(dishDto.toString());
        dishService.saveWithFlavor(dishDto);
        return R.success("新增菜品成功");
    }

    /**
     * 菜品的分页查询
     *
     * @param page
     * @param pageSize
     * @param name
     * @return
     */
    @GetMapping("/page")
    public R<Page> page(int page, int pageSize, String name) {
        //构造分页构造器
        Page<Dish> dishPage = new Page<>(page, pageSize);
        Page<DishDto> dishDtoPage = new Page<>();
        //构造查询构造器
        LambdaQueryWrapper<Dish> dishLambdaQueryWrapper = new LambdaQueryWrapper<>();
        //添加过滤条件，排序规则
        dishLambdaQueryWrapper.like(StringUtils.isNotEmpty(name), Dish::getName, name).orderByDesc(Dish::getUpdateTime);
        //执行分页查询
        dishService.page(dishPage, dishLambdaQueryWrapper);
        return R.success(dishPage);
    }
}
