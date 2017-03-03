package todolist.controller;

import todolist.model.TaskModel;
import todolist.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by wz on 01.03.17.
 */
@Controller
public class TaskController {

    private static final int PAGE_SIZE = 10;

    @Autowired
    private TaskService taskService;

    @RequestMapping("createTask")
    public ModelAndView createTask(@ModelAttribute TaskModel taskModel) {
        return new ModelAndView("taskForm", "taskObject", taskModel);
    }

    @RequestMapping("editTask")
    public ModelAndView editTask(@RequestParam long id, @ModelAttribute TaskModel taskModel){
        taskModel = taskService.getTask(id);
        return new ModelAndView("taskForm", "taskObject", taskModel);
    }

    @RequestMapping("saveTask")
    public ModelAndView saveTask(@ModelAttribute TaskModel taskModel){
        if (taskModel.getId() == 0){
            taskService.createTask(taskModel);
        } else{
            taskService.updateTask(taskModel);
        }
        return new ModelAndView("redirect:getAllTasks");
    }

    @RequestMapping("deleteTask")
    public ModelAndView deleteTask(@RequestParam long id) {
        taskService.deleteTask(id);
        return new ModelAndView("redirect:getAllTasks");
    }



    @RequestMapping(value = {"getAllTasks", "/"})
    public ModelAndView getAllTasks(@RequestParam(value = "done", defaultValue = "all") String filter,
                                    @RequestParam(value = "page", defaultValue = "1") String page) {

        int offset = (Integer.parseInt(page) - 1) * PAGE_SIZE;
        List<TaskModel> taskModels = taskService.getPageWithFilter(filter, offset, PAGE_SIZE);
        int size = taskService.getSizeWithFilter(filter);
        int total = size / PAGE_SIZE;
        if (size % PAGE_SIZE > 0) total++;
        List<Integer> pageList = new ArrayList<>();
        for (int i = 1; i <= total; i++) {
            pageList.add(i);
        }
        ModelAndView mav = new ModelAndView("taskView", "tasksList", taskModels);
        mav.addObject("pageList", pageList);
        mav.addObject("filter", filter);
        mav.addObject("curPage", Integer.parseInt(page));
        return mav;
    }

    @RequestMapping("addTestTasks")
    public ModelAndView addTestTasks(){
        for (int i = 0; i < 50; i++) {
            TaskModel taskModel = new TaskModel();

            taskModel.setName("Test " + i);
            taskModel.setDescription("Just for test.");
            taskModel.setSchedule(new Date());
            taskModel.setDone(Math.random() > 0.5);
            taskService.createTask(taskModel);
        }
        return new ModelAndView("redirect:getAllTasks?done=all?page=1");
    }
}