package com.longxingyu.reggie.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 员工信息
 *
 * @author Administrator
 * {@code @TableName} employee
 */
@TableName(value = "employee")
@Data
public class Employee implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    @TableId(value = "id")
    private Long id;
    /**
     * 姓名
     */
    @TableField(value = "name")
    private String name;
    /**
     * 用户名
     */
    @TableField(value = "username")
    private String username;
    /**
     * 密码
     */
    @TableField(value = "password")
    private String password;
    /**
     * 手机号
     */
    @TableField(value = "phone")
    private String phone;
    /**
     * 性别
     */
    @TableField(value = "sex")
    private String sex;
    /**
     * 身份证号
     */
    @TableField(value = "id_number")
    private String idNumber;
    /**
     * 状态 0:禁用，1:正常
     */
    @TableField(value = "status")
    private Integer status;
    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)    //插入时填充字段
    private LocalDateTime createTime;
    /**
     * 更新时间
     */
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)   //插入和更新时填充字段
    private LocalDateTime updateTime;
    /**
     * 创建人
     */
    @TableField(value = "create_user", fill = FieldFill.INSERT)    //插入时填充字段
    private Long createUser;
    /**
     * 修改人
     */
    @TableField(value = "update_user", fill = FieldFill.INSERT_UPDATE)   //插入和更新时填充字段
    private Long updateUser;

}