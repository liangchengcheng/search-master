package com.lcc.kingnod.entity;

/**
 * Created by asus on 2017/3/21.
 */
import java.util.Date;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

public class EntityListener{

    @PrePersist
    public void prePersist(CommenEntity ce){
        ce.setCreateDate(new Date());
        ce.setLastUpdateDate(new Date());
    }

    @PreUpdate
    public void preUpdate(CommenEntity ce){
        ce.setLastUpdateDate(new Date());
    }
}