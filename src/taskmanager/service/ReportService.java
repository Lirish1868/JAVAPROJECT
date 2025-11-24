package taskmanager.service;

import taskmanager.model.Task;
import taskmanager.model.TaskStatus;
import taskmanager.exception.TaskManagerException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ReportService {
    private TaskService taskService;

    public ReportService(TaskService taskService) {
        this.taskService = taskService;
    }

    public String generateTaskStatusReport() throws TaskManagerException {
        List<Task> tasks = taskService.getAllTasks();
        Map<TaskStatus, Long> counts = tasks.stream()
                .collect(Collectors.groupingBy(Task::getStatus, Collectors.counting()));
        
        StringBuilder sb = new StringBuilder();
        sb.append("Task Status Report:\n");
        for (TaskStatus status : TaskStatus.values()) {
            sb.append(status).append(": ").append(counts.getOrDefault(status, 0L)).append("\n");
        }
        return sb.toString();
    }
}
