package com.longxingyu.reggie.dto;

import com.longxingyu.reggie.pojo.Dish;
import com.longxingyu.reggie.pojo.DishFlavor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * {@code @Create:}  2023-06-08  19 : 26
 * {@code @Author:} Cherry
 * {@code @ToUser:} Be Happy EveryDay
 * ------------------------------------
 * {@code @note:}
 */

@SuppressWarnings({"all"})
@Data
public class DishDTO extends Dish {
    private List<DishFlavor> flavors = new ArrayList<>();
    private String categoryName;
    private Integer copies;
}
