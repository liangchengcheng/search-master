package com.lcc.kingnod.dao;

import com.lcc.kingnod.entity.JcnUser;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by asus on 2017/3/21.
 */
public interface JcnUserDao extends PagingAndSortingRepository<JcnUser,Long> ,JpaSpecificationExecutor<JcnUser>{

    @Query("select a from com.lcc.kingnod.entity.JcnUser a where a.id<:id")
    public List<JcnUser> findAllJcnUser(@Param(value="id")Long id);
}
