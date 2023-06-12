package com.longxingyu.reggie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.longxingyu.reggie.dto.DishDto;
import com.longxingyu.reggie.mapper.DishMapper;
import com.longxingyu.reggie.pojo.Dish;
import com.longxingyu.reggie.pojo.DishFlavor;
import com.longxingyu.reggie.service.DishFlavorService;
import com.longxingyu.reggie.service.DishService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Administrator
 * @description 针对表【dish(菜品管理)】的数据库操作Service实现
 * @createDate 2023-06-07 20:55:56
 */
@Service
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish>
        implements DishService {
    @Autowired
    private DishFlavorService dishFlavorService;

    /**
     * 新增菜品人同时保存对应的口味数据
     *
     * @param dishDto
     */
    @Transactional
    @Override
    public void saveWithFlavor(DishDto dishDto) {
        //保存菜品的基本信息 到菜品表
        save(dishDto);

        //菜品ID
        Long dishId = dishDto.getId();

        //菜品口味
        List<DishFlavor> flavors = dishDto.getFlavors();

        //第一种写法
//        flavors = flavors.stream().map((item) -> {
//            item.setDish_id(dishId);
//            return item;
//        }).collect(Collectors.toList());

        //第二种写法 代替第一种写法
        flavors = flavors.stream().peek((item) -> item.setDish_id(dishId)).collect(Collectors.toList());

        //保存菜品口味数据到菜品口味表dish_flavors中
        dishFlavorService.saveBatch(flavors);
    }

    @Override
    public DishDto getDishWithFlavors(Long id) {
        //查询菜品
        Dish dish = this.getById(id);

        DishDto dishDto = new DishDto();
        BeanUtils.copyProperties(dish, dishDto);
        //查询菜品对应的口味信息 从dish_flavors表查询

        LambdaQueryWrapper<DishFlavor> dishFlavorLambdaQueryWrapper = new LambdaQueryWrapper<>();
        dishFlavorLambdaQueryWrapper.eq(DishFlavor::getDish_id, dish.getId());
        List<DishFlavor> flavors = dishFlavorService.list(dishFlavorLambdaQueryWrapper);

        //设置dishDto的flavors属性
        dishDto.setFlavors(flavors);
        return dishDto;
    }

    @Override
    @Transactional
    public void updateWithFlavors(DishDto dishDto) {
        //更新dish表普通信息
        this.updateById(dishDto);

        //更新dish_flavor表信息delete操作
        LambdaQueryWrapper<DishFlavor> dishFlavorLambdaQueryWrapper = new LambdaQueryWrapper<>();
        dishFlavorLambdaQueryWrapper.eq(DishFlavor::getDish_id, dishDto.getId());
        dishFlavorService.remove(dishFlavorLambdaQueryWrapper);

        //更新dish_flavor表信息insert操作
        List<DishFlavor> flavors = dishDto.getFlavors();
        flavors.stream().map(item -> {
            item.setDish_id(dishDto.getId());
            return item;
        }).collect(Collectors.toList());

        dishFlavorService.saveBatch(flavors);
    }
}




