package com.lcc.kingnod.util;

import com.lcc.kingnod.shiro.ShiroDbRealm;
import org.apache.shiro.SecurityUtils;
import com.google.common.base.Objects;
import java.io.Serializable;

public class Users {

    public static Long userId() {
        ShiroDbRealm.ShiroUser shiroUser = (ShiroDbRealm.ShiroUser) SecurityUtils.getSubject().getPrincipal();
        return shiroUser.id;
    }

}