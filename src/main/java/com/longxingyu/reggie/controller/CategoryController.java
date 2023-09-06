package com.longxingyu.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.longxingyu.reggie.common.R;
import com.longxingyu.reggie.pojo.CateGory;
import com.longxingyu.reggie.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * {@code @Create:}  2023-06-07  20 : 02
 * {@code @Author:} Cherry
 * {@code @ToUser:} Be Happy EveryDay
 * ------------------------------------
 * {@code @note:}
 */

@SuppressWarnings({"all"})
@RestController
@Slf4j
@RequestMapping("/category")
/**
 * 分类管理
 */
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    /**
     * 新增分类
     *
     * @param category
     * @return
     */
    @PostMapping
    public R<String> save(@RequestBody CateGory category) {
        log.info("category:{}", category);
        categoryService.save(category);
        return R.success("新增分类成功");
    }

    /**
     * 分页查询
     *
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping("/page")
    public R<Page> page(int page, int pageSize) {
        //分页构造器
        Page<CateGory> pageInfo = new Page<>(page, pageSize);
        //条件构造器
        LambdaQueryWrapper<CateGory> objectLambdaQueryWrapper = new LambdaQueryWrapper<>();
        //添加排序添加，根据sort进行排序
        objectLambdaQueryWrapper.orderByAsc(CateGory::getSort);
        //进行分页查询
        categoryService.page(pageInfo, objectLambdaQueryWrapper);

        return R.success(pageInfo);
    }

    /**
     * 根据id删除分类
     *
     * @param id
     * @return
     */
    @DeleteMapping
    public R<String> delete(Long id) {
        log.info("删除分类，id为：{}", id);
        categoryService.remove(id);
        return R.success("分类信息删除成功");
    }

    /**
     * 根据id修改分类信息
     *
     * @param category
     * @return
     */
    @PutMapping
    public R<String> update(@RequestBody CateGory category) {
        log.info("修改分类信息：{}", category);
        categoryService.updateById(category);
        return R.success("修改分类信息成功");


    }

    /**
     * 根据条件查询分类数据
     *
     * @param category
     * @return
     */
    @GetMapping("/list")
    public R<List<CateGory>> list(CateGory category) {
        //条件构造器
        LambdaQueryWrapper<CateGory> categoryLambdaQueryWrapper = new LambdaQueryWrapper<>();
        categoryLambdaQueryWrapper.eq(category.getType() != null, CateGory::getType, category.getType())
                .orderByAsc(CateGory::getSort)
                .orderByDesc(CateGory::getUpdateTime);
        List<CateGory> list = categoryService.list(categoryLambdaQueryWrapper);
        return R.success(list);
    }
}
