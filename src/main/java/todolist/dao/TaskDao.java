package todolist.dao;

import todolist.model.TaskModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by wz on 01.03.17.
 */
@Repository
public class TaskDao {

    @Autowired
    private DbWorker dbWorker;

    public long createTask(TaskModel taskModel){
        return (long) dbWorker.create(taskModel);
    }

    public TaskModel updateTask(TaskModel taskModel){
        return dbWorker.update(taskModel);
    }

    public void deleteTask(long id) {
        TaskModel taskModel = new TaskModel();
        taskModel.setId(id);
        dbWorker.delete(taskModel);
    }

    public TaskModel getTask(long id){
        return dbWorker.fetchById(id, TaskModel.class);
    }

    public List<TaskModel> getAllTasks(){
        return dbWorker.fetchAll(TaskModel.class);
    }

    public int getSizeWithFilter(String filter) {
        filter = setSQLFilter(filter);
        return dbWorker.getSizeWithFilter(TaskModel.class, filter);
    }

    public List<TaskModel> getPageWithFilter(String filter, int offset, int pageSize){
        filter = setSQLFilter(filter);
        return dbWorker.fetchPageWithFilter(TaskModel.class, filter, offset, pageSize);
    }

    private String setSQLFilter(String filter){
        switch (filter){
            case "done":
                filter =" WHERE done=" + true;
                break;
            case "undone":
                filter = " WHERE done=" + false;
                break;
            default:
                filter = "";
        }
        return filter;
    }
}
