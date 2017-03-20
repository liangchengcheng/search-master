package com.lcc.kingnod.dao;

import java.util.List;

import com.lcc.kingnod.entity.Chat;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface ChatDao extends PagingAndSortingRepository<Chat,Long>, JpaSpecificationExecutor<Chat> {

    @Query("select a from com.lcc.kingnod.entity.Chat a where a.readFlag=:readFlag order by a.sendDate asc")
    List<Chat> findNotReadChat(@Param("readFlag")String readFlag);
}