package bpm.listener;


import org.flowable.engine.TaskService;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.ExecutionListener;
import org.flowable.engine.delegate.TaskListener;
import org.flowable.form.api.FormInfo;
import org.flowable.task.service.delegate.DelegateTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("myListener")
public class MyListener implements ExecutionListener, TaskListener{

    @Autowired
    private TaskService taskService;

    /**
     * ExecutionListener类的实现
     */
    @Override
    public void notify(DelegateExecution execution) {
        String eventName = execution.getEventName();
//start
        if ("start".equals(eventName)) {
            System.out.println("start=========");
        }else if ("end".equals(eventName)) {
            System.out.println("end=========");
        }
        else if ("take".equals(eventName)) {
            System.out.println("take=========");
        }
    }


    /**
     * TaskListener类的实现
     */
    @Override
    public void notify(DelegateTask delegateTask) {
        String eventName = delegateTask.getEventName();
        if ("create".endsWith(eventName)) {
            System.out.println("create=========");

        }else if ("assignment".endsWith(eventName)) {
            FormInfo formInfo = taskService.getTaskFormModel(delegateTask.getId());

            System.out.println("办理人设定为：");
        }else if ("complete".endsWith(eventName)) {
            System.out.println("complete===========");
        }else if ("delete".endsWith(eventName)) {
            System.out.println("delete=============");
        }
    }
}

