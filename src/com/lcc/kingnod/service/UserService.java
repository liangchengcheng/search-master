package com.lcc.kingnod.service;

import com.lcc.kingnod.dao.UserDao;
import com.lcc.kingnod.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by asus on 2017/3/21.
 */
@Service
@Transactional(readOnly = false)
public class UserService {

    @Autowired
    private UserDao userDao;

    public User findOne(Long id){
        return userDao.findOne(id);
    }
}
