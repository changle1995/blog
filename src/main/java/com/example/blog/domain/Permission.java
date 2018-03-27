package com.example.blog.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.List;

/**
 * Author: changle
 * Date: 2018/3/22
 * Time: 0:47
 */
@Entity
@Table(name = "permission")
public class Permission extends BaseEntity implements GrantedAuthority {

    @Column(name = "name")
    private String name;

    @Column(name = "description", length = 1000)
    private String description;

    @Column(name = "url")
    private String url;

    @JsonIgnore
    @ManyToMany(mappedBy = "permissionList", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Role> roleList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    @Override
    public String getAuthority() {
        return name;
    }
}
