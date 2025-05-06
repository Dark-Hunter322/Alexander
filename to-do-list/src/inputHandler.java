import java.io.IOException;
import java.util.Scanner;
public class inputHandler {
    static Scanner sc=new Scanner(System.in);
    public static void start() throws IOException {
        while (true) {
            String input = sc.nextLine();
            if (input.equals("1")) {
                System.out.println("Введите имя задачи: ");
                String taskName= sc.nextLine();
                System.out.println("Введите описание задачи: ");
                String taskDescription= sc.nextLine();
                Task task=new Task(taskName, taskDescription);
                TaskManager.addTask(task);
                System.out.println("Выполнено");
                Menu.show();
            }
            else if (input.equals("2")) {
                TaskManager.showAllTask();
                Menu.show();
            }
            else if (input.equals("3")) {
                System.out.println("Введите задачу, которую вы хоте изменить");
                int id= sc.nextInt();
                TaskManager.completeTask(id);
                Menu.show();
            }
            else if (input.equals("4")) {
                System.out.println("Введите задачу, которую вы хоте удалить");
                int id= sc.nextInt();
                TaskManager.delTask(id);
                Menu.show();
            }
            else if (input.equals("5")) {
                TaskManager.showTaskComplite("Выполненa");
                Menu.show();
            }
            else if (input.equals("6")) {
                TaskManager.showTaskComplite("Не выполнено");
                Menu.show();
            }
            else if (input.equals("7")) {
                break;
            }

        }

    }
}
