package com.lcc.kingnod.service;

import com.lcc.kingnod.dao.RoleDao;
import com.lcc.kingnod.dao.UserDao;
import com.lcc.kingnod.entity.Role;
import com.lcc.kingnod.entity.User;
import com.lcc.kingnod.shiro.ShiroDbRealm;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springside.modules.persistence.Hibernates;
import org.springside.modules.security.utils.Digests;
import org.springside.modules.utils.Encodes;

import java.util.List;

/**
 * Created by asus on 2017/3/21.
 */
@Component
@Transactional
public class ResourceService {
    public static final String HASH_ALGORITHM = "SHA-1";
    public static final int HASH_INTERATIONS = 1024;
    private static final int SALT_SIZE = 8;

    private static Logger logger = LoggerFactory.getLogger(ResourceService.class);


    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private CacheManager cacheManager;

    /**
     * 根据id查找用户
     */
    public User getUser(Long id){
        return userDao.findOne(id);
    }

    /**
     * 获取全部的用户,并在返回前对用户的延迟加载关联角色进行初始化
     */
    public List<User> getAllUserInitialized(){
        List<User> result = (List<User>) userDao.findAll();
        for (User user : result){
            Hibernates.initLazyProperty(user.getRoleList());
        }
        return result;
    }

    /**
     * 按登录名查询用户.
     */
    public User findUserByLoginName(String loginName) {
        return userDao.findByLoginName(loginName);
    }

    /**
     * 按名称查询用户, 并在返回前对用户的延迟加载关联角色进行初始化.
     */
    public User findUserByNameInitialized(String name) {
        User user = userDao.findByName(name);
        if (user != null) {
            Hibernates.initLazyProperty(user.getRoleList());
        }
        return user;
    }

    /**
     * 获取当前用户数量.
     */
    public Long getUserCount() {
        return userDao.count();
    }

    /**
     * 判断是否超级管理员.
     */
    private boolean isSupervisor(User user) {
        return ((user.getId() != null) && (user.getId() == 1L));
    }

    /**
     * 设定安全的密码，生成随机的salt并经过1024次 sha-1 hash
     */
    private void entryptPassword(User user) {
        byte[] salt = Digests.generateSalt(SALT_SIZE);
        user.setSalt(Encodes.encodeHex(salt));

        byte[] hashPassword = Digests.sha1(user.getPlainPassword().getBytes(), salt, HASH_INTERATIONS);
        user.setPassword(Encodes.encodeHex(hashPassword));
    }

    /**
     * 取出Shiro中的当前用户LoginName.
     */
    private String getCurrentUserName() {
        ShiroDbRealm.ShiroUser user = (ShiroDbRealm.ShiroUser) SecurityUtils.getSubject().getPrincipal();
        return user.loginName;
    }

    // -----------------
    // Role Management
    // -----------------
    public List<Role> getAllRole() {
        return (List<Role>) roleDao.findAll();
    }

    // -----------------
    // Setter methods
    // -----------------
    public void addAuthCode(String authCode,String username){
        cacheManager.getCache("oauth-code-cache").put(authCode,username);
    }

    public void addAccessToken(String accessToken,String username){
        cacheManager.getCache("oauth-code-cache").put(accessToken,username);
    }

    public boolean checkAuthCode(String authCode){
        return cacheManager.getCache("oauth-code-cache").get(authCode)!=null;
    }

    public String getUsernameByAuthCode(String authCode){
        return (String)cacheManager.getCache("oauth-code-cache").get(authCode).get();
    }

    public long getExpireIn(){
        return 3600L;
    }

}
