package taskmanager;

import taskmanager.model.Task;
import taskmanager.model.TaskStatus;
import taskmanager.model.User;
import taskmanager.service.ReportService;
import taskmanager.service.TaskService;
import taskmanager.service.UserService;
import taskmanager.util.InputValidator;
import taskmanager.exception.TaskManagerException;

import java.util.Scanner;
import java.util.List;

public class Main {
    private static UserService userService = new UserService();
    private static TaskService taskService = new TaskService();
    private static ReportService reportService = new ReportService(taskService);
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- Task Manager ---");
            System.out.println("1. Add User");
            System.out.println("2. List Users");
            System.out.println("3. Add Task");
            System.out.println("4. List Tasks");
            System.out.println("5. Update Task Status");
            System.out.println("6. Generate Report");
            System.out.println("7. Exit");
            System.out.print("Select an option: ");

            String choice = scanner.nextLine();

            try {
                switch (choice) {
                    case "1":
                        addUser();
                        break;
                    case "2":
                        listUsers();
                        break;
                    case "3":
                        addTask();
                        break;
                    case "4":
                        listTasks();
                        break;
                    case "5":
                        updateTaskStatus();
                        break;
                    case "6":
                        generateReport();
                        break;
                    case "7":
                        System.out.println("Exiting...");
                        return;
                    default:
                        System.out.println("Invalid option.");
                }
            } catch (TaskManagerException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
            }
        }
    }

    private static void addUser() throws TaskManagerException {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        if (InputValidator.isValidString(username) && InputValidator.isValidEmail(email)) {
            userService.addUser(username, email);
            System.out.println("User added successfully.");
        } else {
            System.out.println("Invalid input.");
        }
    }

    private static void listUsers() throws TaskManagerException {
        List<User> users = userService.getAllUsers();
        if (users.isEmpty()) {
            System.out.println("No users found.");
        } else {
            for (User user : users) {
                System.out.println(user);
            }
        }
    }

    private static void addTask() throws TaskManagerException {
        System.out.print("Enter title: ");
        String title = scanner.nextLine();
        System.out.print("Enter description: ");
        String description = scanner.nextLine();
        System.out.print("Enter assigned user ID: ");
        String userId = scanner.nextLine();

        if (InputValidator.isValidString(title) && InputValidator.isValidString(userId)) {
            taskService.addTask(title, description, userId);
            System.out.println("Task added successfully.");
        } else {
            System.out.println("Invalid input.");
        }
    }

    private static void listTasks() throws TaskManagerException {
        List<Task> tasks = taskService.getAllTasks();
        if (tasks.isEmpty()) {
            System.out.println("No tasks found.");
        } else {
            for (Task task : tasks) {
                System.out.println(task);
            }
        }
    }

    private static void updateTaskStatus() throws TaskManagerException {
        System.out.print("Enter task ID: ");
        String taskId = scanner.nextLine();
        System.out.println("Select status: 1. PENDING, 2. IN_PROGRESS, 3. COMPLETED");
        String statusChoice = scanner.nextLine();
        
        TaskStatus status = null;
        switch (statusChoice) {
            case "1": status = TaskStatus.PENDING; break;
            case "2": status = TaskStatus.IN_PROGRESS; break;
            case "3": status = TaskStatus.COMPLETED; break;
            default: System.out.println("Invalid status."); return;
        }

        taskService.updateTaskStatus(taskId, status);
        System.out.println("Task status updated.");
    }

    private static void generateReport() throws TaskManagerException {
        System.out.println(reportService.generateTaskStatusReport());
    }
}
