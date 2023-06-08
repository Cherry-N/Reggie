package com.longxingyu.reggie.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 菜品管理
 *
 * @TableName dish
 */
@TableName(value = "dish")
@Data
public class Dish implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    @TableId(value = "id")
    private Long id;
    /**
     * 菜品名称
     */
    @TableField(value = "name")
    private String name;
    /**
     * 菜品分类id
     */
    @TableField(value = "category_id")
    private Long categoryId;
    /**
     * 菜品价格
     */
    @TableField(value = "price")
    private BigDecimal price;
    /**
     * 商品码
     */
    @TableField(value = "code")
    private String code;
    /**
     * 图片
     */
    @TableField(value = "image")
    private String image;
    /**
     * 描述信息
     */
    @TableField(value = "description")
    private String description;
    /**
     * 0 停售 1 起售
     */
    @TableField(value = "status")
    private Integer status;
    /**
     * 顺序
     */
    @TableField(value = "sort")
    private Integer sort;
    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    /**
     * 更新时间
     */
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
    /**
     * 创建人
     */
    @TableField(value = "create_user", fill = FieldFill.INSERT)
    private Long createUser;
    /**
     * 修改人
     */
    @TableField(value = "update_user", fill = FieldFill.INSERT_UPDATE)
    private Long updateUser;
    /**
     * 是否删除
     */
    @TableField(value = "is_deleted")
    private Integer isDeleted;
}