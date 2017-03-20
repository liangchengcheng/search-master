package com.lcc.kingnod.entity;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import com.google.common.collect.ImmutableList;

/**
 * 角色.
 */
@Entity
@Table(name = "sys_role")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Role extends CommenEntity {

    private String name;
    private String code;
    private String description;

    @Transient
    private String permissions;

	/*private Long id;

	public Role() {
	}

	public Role(Long id) {
		this.id = id;
	}*/

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPermissions() {
        return permissions;
    }

    public void setPermissions(String permissions) {
        this.permissions = permissions;
    }

    public List<String> getPermissionList() {
        return ImmutableList.copyOf(StringUtils.split(permissions, ","));
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
