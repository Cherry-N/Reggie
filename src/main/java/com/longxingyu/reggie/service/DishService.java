package com.longxingyu.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.longxingyu.reggie.dto.DishDto;
import com.longxingyu.reggie.pojo.Dish;

/**
 * @author Administrator
 * @description 针对表【dish(菜品管理)】的数据库操作Service
 * @createDate 2023-06-07 20:55:56
 */
public interface DishService extends IService<Dish> {
    void saveWithFlavor(DishDto dishDto);
}
