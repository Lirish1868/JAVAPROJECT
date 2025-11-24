package taskmanager.service;

import taskmanager.model.Task;
import taskmanager.model.TaskStatus;
import taskmanager.util.FileStorage;
import taskmanager.exception.TaskManagerException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TaskService {
    private static final String FILE_PATH = "data/tasks.txt";

    public void addTask(String title, String description, String assignedUserId) throws TaskManagerException {
        List<Task> tasks = getAllTasks();
        String id = String.valueOf(tasks.size() + 1);
        Task newTask = new Task(id, title, description, assignedUserId, TaskStatus.PENDING);
        FileStorage.appendLine(FILE_PATH, newTask.toString());
    }

    public List<Task> getAllTasks() throws TaskManagerException {
        List<String> lines = FileStorage.readLines(FILE_PATH);
        List<Task> tasks = new ArrayList<>();
        for (String line : lines) {
            tasks.add(Task.fromString(line));
        }
        return tasks;
    }

    public void updateTaskStatus(String taskId, TaskStatus status) throws TaskManagerException {
        List<Task> tasks = getAllTasks();
        boolean found = false;
        List<String> lines = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getId().equals(taskId)) {
                task.setStatus(status);
                found = true;
            }
            lines.add(task.toString());
        }
        if (found) {
            FileStorage.writeLines(FILE_PATH, lines);
        } else {
            throw new TaskManagerException("Task not found: " + taskId);
        }
    }
    
    public List<Task> getTasksByUserId(String userId) throws TaskManagerException {
        return getAllTasks().stream()
                .filter(t -> t.getAssignedUserId().equals(userId))
                .collect(Collectors.toList());
    }
}
