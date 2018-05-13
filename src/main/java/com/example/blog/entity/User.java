package com.example.blog.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Author: changle
 * Date: 2018/3/16
 * Time: 16:53
 */
@ApiModel(value = "用户表实体类")
@Entity
@Table(name = "user")
public class User extends BaseEntity implements UserDetails {

    @ApiModelProperty(value = "用户名称")
    @Column(name = "username")
    private String username;

    @ApiModelProperty(value = "角色密码")
    @Column(name = "password")
    private String password;

    @ApiModelProperty(value = "用户描述")
    @Column(name = "description", length = 1000)
    private String description;

    @ApiModelProperty(value = "用户邮箱")
    @Column(name = "email")
    private String email;

    @ApiModelProperty(value = "用户电话")
    @Column(name = "phone_number")
    private String phoneNumber;

    @ApiModelProperty(value = "用户头像存放路径")
    @Column(name = "photo_path")
    private String photoPath;

    @ApiModelProperty(value = "用户对应的角色")
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private List<Role> roleList;

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = new BCryptPasswordEncoder().encode(password);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<Permission> permissionList = new ArrayList<>();
        roleList.forEach(role -> permissionList.addAll(role.getPermissionList()));
        return permissionList;
    }

}
