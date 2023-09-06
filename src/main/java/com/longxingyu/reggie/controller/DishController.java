package com.longxingyu.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.longxingyu.reggie.common.R;
import com.longxingyu.reggie.dto.DishDTO;
import com.longxingyu.reggie.pojo.CateGory;
import com.longxingyu.reggie.pojo.Dish;
import com.longxingyu.reggie.service.CategoryService;
import com.longxingyu.reggie.service.DishService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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
    @Autowired
    private CategoryService categoryService;

    /**
     * 新增菜品
     *
     * @param dishDto
     * @return
     */
    @PostMapping
    public R<String> save(@RequestBody DishDTO dishDto) {
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
        Page<Dish> pageInfo = new Page<>(page, pageSize);

        Page<DishDTO> dishDtoPage = new Page<>();

        //构造条件构造器
        LambdaQueryWrapper<Dish> queryWrapper = new LambdaQueryWrapper<>();

        //添加过滤条件
        queryWrapper.like(!StringUtils.isEmpty(name), Dish::getName, name);

        //添加排序条件
        queryWrapper.orderByDesc(Dish::getUpdateTime);

        //进行分页查询
        dishService.page(pageInfo, queryWrapper);

        //对象拷贝
        BeanUtils.copyProperties(pageInfo, dishDtoPage, "records");

        List<Dish> records = pageInfo.getRecords();
        List<DishDTO> list = records.stream().map((item) -> {
            DishDTO dishDto = new DishDTO();

            BeanUtils.copyProperties(item, dishDto);
            Long categoryId = item.getCategoryId();
            //根据id查分类对象
            CateGory category = categoryService.getById(categoryId);
            if (category != null) {
                String categoryName = category.getName();
                dishDto.setCategoryName(categoryName);
            }
            return dishDto;
        }).collect(Collectors.toList());

        dishDtoPage.setRecords(list);

        return R.success(dishDtoPage);
    }

    /**
     * 根据id查询商品信息和对应的口味信息
     *
     * @param id
     * @return
     */
    @GetMapping("{id}")
    public R<DishDTO> getdishDto(@PathVariable Long id) {
        DishDTO dishDto = dishService.getDishWithFlavors(id);
        return R.success(dishDto);
    }

    /**
     * 更新菜品
     *
     * @param dishDto
     * @return
     */
    @PutMapping
    public R<String> updateDishDto(@RequestBody DishDTO dishDto) {
        log.info(dishDto.toString());

        dishService.updateWithFlavors(dishDto);

        return R.success("修改商品成功");
    }

    /**
     * 停售起售菜品
     *
     * @return
     */
    @PostMapping("/status/{status}")
    public R<String> sale(@PathVariable int status, String[] ids) {
        for (String id : ids) {
            Dish dish = dishService.getById(id);
            dish.setStatus(status);
            dishService.updateById(dish);
        }
        return R.success("修改成功");
    }

    /**
     * 删除菜品
     *
     * @param ids
     * @return
     */
    @DeleteMapping
    public R<String> delete(String[] ids) {
        for (String id : ids) {
            dishService.removeById(id);
        }
        return R.success("删除成功");
    }

    /**
     * //根据条件查询对应菜品数据
     *
     * @param dish
     * @return
     */
    @GetMapping("/list")
    public R<List<Dish>> get(Dish dish) {
        //条件查询器
        LambdaQueryWrapper<Dish> dishLambdaQueryWrapper = new LambdaQueryWrapper<>();
        //根据传过来的id进行查询
        dishLambdaQueryWrapper.eq(dish.getCategoryId() != null, Dish::getCategoryId, dish.getCategoryId());
        //只查询状态为1的菜品（启售菜品）
        dishLambdaQueryWrapper.eq(Dish::getStatus, 1);
        //简单排下序，其实也没啥太大作用
        dishLambdaQueryWrapper.orderByAsc(Dish::getSort).orderByDesc(Dish::getUpdateTime);

        List<Dish> list = dishService.list(dishLambdaQueryWrapper);
        return R.success(list);
    }
}
