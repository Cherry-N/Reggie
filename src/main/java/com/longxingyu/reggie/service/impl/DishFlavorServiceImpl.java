package com.longxingyu.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.longxingyu.reggie.pojo.DishFlavor;
import com.longxingyu.reggie.service.DishFlavorService;
import com.longxingyu.reggie.mapper.DishFlavorMapper;
import org.springframework.stereotype.Service;

/**
* @author Administrator
* @description 针对表【dish_flavor(菜品口味关系表)】的数据库操作Service实现
* @createDate 2023-06-08 18:08:04
*/
@Service
public class DishFlavorServiceImpl extends ServiceImpl<DishFlavorMapper, DishFlavor>
    implements DishFlavorService{

}




