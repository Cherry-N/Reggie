package com.longxingyu.reggie.dto;

import com.longxingyu.reggie.pojo.Setmeal;
import com.longxingyu.reggie.pojo.SetmealDish;
import lombok.Data;

import java.util.List;

/**
 * {@code @Create:}  2023-06-12  19 : 57
 * {@code @Author:} Cherry
 * {@code @ToUser:} Be Happy EveryDay
 * ------------------------------------
 * {@code @note:}
 */

@SuppressWarnings({"all"})
@Data
public class SetmealDTO extends Setmeal {
    private List<SetmealDish> setmealDishes;

    private String categoryName;
}
