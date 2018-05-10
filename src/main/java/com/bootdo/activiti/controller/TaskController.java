package com.bootdo.activiti.controller;

import com.bootdo.activiti.vo.TaskVO;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * Create by li_hongqing1
 * Date: 2018/5/10 16:27
 */

@RequestMapping("activiti/task")
@RestController
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/todo")
    public ModelAndView todo() {
        return new ModelAndView("act/task/todoTask");
    }

    @GetMapping("/todoList")
    List<TaskVO> todoList(){
        TaskQuery taskQuery = taskService.createTaskQuery();
        TaskQuery query = taskQuery.taskAssignee("admin");
        List<Task> tasks = query.list();
        List<TaskVO> taskVOS =  new ArrayList<>();
        for(Task task : tasks){
            TaskVO taskVO = new TaskVO(task);
            taskVOS.add(taskVO);
        }
        return taskVOS;
    }
}
