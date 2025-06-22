package com.example.taskmanager;

import com.example.taskmanager.model.Task;
import com.example.taskmanager.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class TaskmanagerApplication implements CommandLineRunner {

	private final TaskService taskService;

	@Autowired
	public TaskmanagerApplication(TaskService taskService) {
		this.taskService = taskService;
	}

	public static void main(String[] args) {
		SpringApplication.run(TaskmanagerApplication.class, args);
	}

	@Override
	public void run(String... args) {
		Scanner scanner = new Scanner(System.in);
		boolean exit = false;

		System.out.println(" ברוכה הבאה למערכת ניהול משימות");

		while (!exit) {
			System.out.println("\nבחרי פעולה:");
			System.out.println("1 - הוספת משימה");
			System.out.println("2 - הצגת כל המשימות");
			System.out.println("3 - סימון משימה כהושלמה");
			System.out.println("4 - מחיקת משימה");
			System.out.println("5 - יציאה");

			System.out.print("הזיני את מספר הפעולה: ");
			int choice = scanner.nextInt();
			scanner.nextLine();

			switch (choice) {
				case 1:


					System.out.print("הזיני שם משימה: ");
					String name = scanner.nextLine();

					System.out.print("הזיני תיאור משימה: ");
					String description = scanner.nextLine();

					taskService.addTask(new Task(0, name, description, false));
					System.out.println(" המשימה נוספה בהצלחה.");
					break;

				case 2:
					System.out.println("\nרשימת משימות:");
					taskService.getAllTasks().forEach(System.out::println);
					break;

				case 3:
					System.out.print("הזיני ID של משימה לסימון כהושלמה: ");
					int completeId = scanner.nextInt();
					scanner.nextLine();
					if (taskService.markTaskAsCompleted(completeId)) {
						System.out.println(" המשימה סומנה כהושלמה.");
					} else {
						System.out.println(" לא נמצאה משימה עם ID זה.");
					}
					break;

				case 4:
					System.out.print("הזיני ID של משימה למחיקה: ");
					int deleteId = scanner.nextInt();
					scanner.nextLine();
					if (taskService.deleteTask(deleteId)) {
						System.out.println("המשימה נמחקה.");
					} else {
						System.out.println(" לא נמצאה משימה עם ID זה.");
					}
					break;

				case 5:
					System.out.println("יציאה מהמערכת...");
					exit = true;
					break;

				default:
					System.out.println("בחירה לא חוקית. נסי שוב.");
			}
		}

		scanner.close();
	}
}
