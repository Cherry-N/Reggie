package com.longxingyu.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.longxingyu.reggie.dto.SetmealDto;
import com.longxingyu.reggie.pojo.Setmeal;

/**
 * @author Administrator
 * @description 针对表【setmeal(套餐)】的数据库操作Service
 * @createDate 2023-06-07 20:56:01
 */
public interface SetmealService extends IService<Setmeal> {
    void saveWithDish(SetmealDto setmealDto);
}
