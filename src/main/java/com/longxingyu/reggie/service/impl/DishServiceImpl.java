package com.longxingyu.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.longxingyu.reggie.pojo.Dish;
import com.longxingyu.reggie.service.DishService;
import com.longxingyu.reggie.mapper.DishMapper;
import org.springframework.stereotype.Service;

/**
* @author Administrator
* @description 针对表【dish(菜品管理)】的数据库操作Service实现
* @createDate 2023-06-07 20:55:56
*/
@Service
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish>
    implements DishService{

}




