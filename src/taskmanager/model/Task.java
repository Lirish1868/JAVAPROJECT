package taskmanager.model;

public class Task {
    private String id;
    private String title;
    private String description;
    private String assignedUserId;
    private TaskStatus status;

    public Task(String id, String title, String description, String assignedUserId, TaskStatus status) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.assignedUserId = assignedUserId;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getAssignedUserId() {
        return assignedUserId;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return id + "," + title + "," + description + "," + assignedUserId + "," + status;
    }

    public static Task fromString(String line) {
        String[] parts = line.split(",");
        return new Task(parts[0], parts[1], parts[2], parts[3], TaskStatus.valueOf(parts[4]));
    }
}
