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
 * 套餐菜品关系
 *
 * @TableName setmeal_dish
 */
@TableName(value = "setmeal_dish")
@Data
public class SetmealDish implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    @TableId(value = "id")
    private Long id;
    /**
     * 套餐id
     */
    @TableField(value = "setmeal_id")
    private Long setmealId;
    /**
     * 菜品id
     */
    @TableField(value = "dish_id")
    private String dishId;
    /**
     * 菜品名称 （冗余字段）
     */
    @TableField(value = "name")
    private String name;
    /**
     * 菜品原价（冗余字段）
     */
    @TableField(value = "price")
    private BigDecimal price;
    /**
     * 份数
     */
    @TableField(value = "copies")
    private Integer copies;
    /**
     * 排序
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