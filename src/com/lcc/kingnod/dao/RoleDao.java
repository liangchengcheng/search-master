package com.lcc.kingnod.dao;

/**
 * Created by asus on 2017/3/21.
 */

import org.springframework.data.repository.PagingAndSortingRepository;

import com.lcc.kingnod.entity.Role;

public interface RoleDao extends PagingAndSortingRepository<Role, Long> {

}

