package com.lcc.kingnod.controller;

import com.lcc.kingnod.entity.JcnUser;
import com.lcc.kingnod.entity.Task;
import com.lcc.kingnod.service.TaskService;
import com.lcc.kingnod.tool.Setting;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.Date;

/**
 * Created by lcc on 2017/3/22.
 */
@Controller
@RequestMapping("task")
public class TaskController {

    private Logger logger = org.slf4j.LoggerFactory.getLogger(TaskController.class);

    @Autowired
    private TaskService taskService;

    /**
     * 跳转到任务创建页面
     */
    @RequestMapping(value="create",method= RequestMethod.GET)
    public ModelAndView create(ModelAndView mv){
        mv.addObject("dep", Task.DepType.values());
        mv.addObject("action", Setting.ActionType.create );
        mv.setViewName("view/task/TaskForm");
        return mv;
    }

    /**
     * 保存任务
     */
    @RequestMapping(value="create",method=RequestMethod.POST)
    public ModelAndView create(ModelAndView mv,Task task,RedirectAttributes redirectAttributes){
        task.setLastUpdateDate(new Date());
        JcnUser user = new JcnUser();
        user.setId(4L);
        task.setUser(user);
        taskService.save(task);
        redirectAttributes.addFlashAttribute("message", "创建任务成功");
        mv.setViewName("view/task/TaskForm");
        return mv;
    }
}
