package todolist.service;

import todolist.dao.TaskDao;
import todolist.model.TaskModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by wz on 01.03.17.
 */
@Service
@Transactional
public class TaskService {

    @Autowired
    private TaskDao taskDao;

    public long createTask(TaskModel taskModel){
        return taskDao.createTask(taskModel);
    }

    public TaskModel updateTask(TaskModel taskModel){
        return taskDao.updateTask(taskModel);
    }

    public void deleteTask(long id){
        taskDao.deleteTask(id);
    }

    public List<TaskModel> getAllTasks() {
        return taskDao.getAllTasks();
    }

    public int getSizeWithFilter(String filter){
        return taskDao.getSizeWithFilter(filter);
    }

    public List<TaskModel> getPageWithFilter(String filter, int offset, int pageSize){
        return taskDao.getPageWithFilter(filter, offset, pageSize);
    }

    public TaskModel getTask(long id){
        return taskDao.getTask(id);
    }
}
