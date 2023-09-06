package com.longxingyu.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.longxingyu.reggie.pojo.CateGory;

/**
 * @author Administrator
 * @description 针对表【category(菜品及套餐分类)】的数据库操作Service
 * @createDate 2023-06-07 19:47:06
 */
public interface CategoryService extends IService<CateGory> {
    void remove(Long id);
}
