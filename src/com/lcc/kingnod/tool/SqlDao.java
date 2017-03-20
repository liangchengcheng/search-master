package com.lcc.kingnod.tool;

import com.lcc.kingnod.entity.CommenEntity;
import org.springframework.data.repository.Repository;

import java.util.List;

/**
 * Created by asus on 2017/3/21.
 */
public interface SqlDao extends Repository<CommenEntity, Long> {

    /**
     * 查询原生sql查询
     */
    public List<Object[]> findAll(String sql);

    /**
     * 根据主键查询
     */
    //public JcnUser findOne(Long Id);
}
