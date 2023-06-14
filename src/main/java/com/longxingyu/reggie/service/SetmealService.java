package com.longxingyu.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.longxingyu.reggie.dto.SetmealDto;
import com.longxingyu.reggie.pojo.Setmeal;

import java.util.List;

/**
 * @author Administrator
 * @description 针对表【setmeal(套餐)】的数据库操作Service
 * @createDate 2023-06-07 20:56:01
 */
public interface SetmealService extends IService<Setmeal> {
    /**
     * 新增套餐同时需要保存套餐和菜品的关联信息
     *
     * @param setmealDto
     */
    void saveWithDish(SetmealDto setmealDto);

    /**
     * 删除套餐同时需要删除套餐和菜品的关联信息
     *
     * @param ids
     */
    void removeWithDish(List<Long> ids);

    SetmealDto getByIdWithDish(Long id);

    void updateWithDish(SetmealDto setmealDto);
}
