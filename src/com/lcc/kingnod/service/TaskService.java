package com.lcc.kingnod.service;

import com.lcc.kingnod.dao.TaskDao;
import com.lcc.kingnod.entity.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by asus on 2017/3/21.
 */
@Service
@Transactional(readOnly = true)
public class TaskService {

    @Autowired
    private TaskDao taskDao;

    public Task save(Task task){
        return taskDao.save(task);
    }
}
