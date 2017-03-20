package com.lcc.kingnod.dao;

/**
 * Created by asus on 2017/3/21.
 */
import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import com.lcc.kingnod.entity.Classes;
import com.lcc.kingnod.entity.Task;
import com.lcc.kingnod.entity.JcnUser;

public interface TaskDao extends PagingAndSortingRepository<Task,Long>, JpaSpecificationExecutor<Task> {
    /**
     * 通过关联ID,获取附件
     * @param sourceId 来源ID(关联ID)
     * @param type 附件类型
     * @param sourceType 关联来源类型
     * @return
     */
    //@Query("select a from Attachments a where a.sourceId=:sourceId and a.attrachmentType=:type and a.sourceType=:sourceType and a.disableFlag=:disableFlag")
    //public List<Attachments> queryAttachmentsBySourceId(@Param("sourceId") Long sourceId,@Param("type") String type,@Param("sourceType") String sourceType,@Param("disableFlag") String disableFlag);

    /**
     * 通过关联ID,获取附件
     * @param sourceId 来源ID(关联ID)
     * @param type 附件类型
     * @param sourceType 关联来源类型
     * @return
     */
    //@Query("select a from Attachments a where a.sourceId=:sourceId and a.sourceType=:sourceType and a.disableFlag=:disableFlag")
    //public List<Attachments> queryAttachmentsBySourceId(@Param("sourceId") Long sourceId,@Param("sourceType") String sourceType,@Param("disableFlag") String disableFlag);

    /**
     * 通过附件ID 按修改日期倒叙查询
     */
    //@Query("select a from Attachments a where a.sourceId=:sourceId and a.attrachmentType=:type and a.sourceType=:sourceType and a.disableFlag=:disableFlag order by a.lastUpdatedDate desc")
    //public List<Attachments> queryBySourceIdOrderByLastUpdatedDate(@Param("sourceId") Long sourceId,@Param("type") String type,@Param("sourceType") String sourceType,@Param("disableFlag") String disableFlag);
    @Query("select a from com.lcc.kingnod.entity.JcnUser a where a.id<:id")
    public List<JcnUser> findAllUser(@Param(value="id")Long id);
    public List<Task> findAll();
}