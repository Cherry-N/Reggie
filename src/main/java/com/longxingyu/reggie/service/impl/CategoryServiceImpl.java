package com.longxingyu.reggie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.longxingyu.reggie.common.CustomException;
import com.longxingyu.reggie.mapper.CategoryMapper;
import com.longxingyu.reggie.pojo.CateGory;
import com.longxingyu.reggie.pojo.Dish;
import com.longxingyu.reggie.pojo.Setmeal;
import com.longxingyu.reggie.service.CategoryService;
import com.longxingyu.reggie.service.DishService;
import com.longxingyu.reggie.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Administrator
 * @description 针对表【category(菜品及套餐分类)】的数据库操作Service实现
 * @createDate 2023-06-07 19:47:06
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, CateGory>
        implements CategoryService {
    @Autowired
    private DishService dishService;
    @Autowired
    private SetmealService setmealService;

    /**
     * 根据id删除分类，删除前需要进行判断
     *
     * @param id
     */
    @Override
    public void remove(Long id) {
        LambdaQueryWrapper<Dish> dishLambdaQueryWrapper = new LambdaQueryWrapper<>();
        //添加查询条件，根据分类id进行查询
        dishLambdaQueryWrapper.eq(Dish::getCategoryId, id);
        int count1 = dishService.count(dishLambdaQueryWrapper);
        //查询当前分类是否关联菜品,如果已经关联，抛出业务异常
        if (count1 > 0) {
            //已经关联菜品，抛出一个业务异常
            throw new CustomException("当前分类下关联了菜品，不能删除");
        }

        LambdaQueryWrapper<Setmeal> setmealLambdaQueryWrapper = new LambdaQueryWrapper<>();
        //添加查询条件，根据分类id进行查询
        setmealLambdaQueryWrapper.eq(Setmeal::getCategoryId, id);
        int count2 = setmealService.count(setmealLambdaQueryWrapper);
        //查询当前分类是否关联了套餐，如果已经关联，抛出业务异常
        if (count2 > 0) {
            //已经关联菜品，抛出一个业务异常
            throw new CustomException("当前分类下关联了套餐   ，不能删除");
        }
        //正常删除分类
        removeById(id);
    }
}




