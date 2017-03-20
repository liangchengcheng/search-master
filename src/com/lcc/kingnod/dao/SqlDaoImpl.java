package com.lcc.kingnod.dao;

import com.lcc.kingnod.tool.SqlDao;
import org.hibernate.Session;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by asus on 2017/3/21.
 */
@Service
public class SqlDaoImpl implements SqlDao{

    @PersistenceContext
    private EntityManager em;

    @SuppressWarnings("unchecked")
    @Override
    public List<Object[]> findAll(String sql) {
        EntityManager target = null;
        target = em.getEntityManagerFactory().createEntityManager();
        Session session = target.unwrap(Session.class);
        List<Object[]> list = null;
        try {
            list = session.createSQLQuery(sql).list();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(target!=null) {
                target.close();
            }
        }
        return list;
    }
}
