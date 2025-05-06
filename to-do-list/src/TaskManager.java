import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.*;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class TaskManager {
    private static ObjectMapper objectMapper=new ObjectMapper();
    private static JsonNode rootNode;

    static {
        try {
            rootNode = parse();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static ArrayNode taskNode= (ArrayNode) rootNode.get("tasks");

    public static void addTask(Task task) throws IOException {
        if (taskNode.isEmpty()) {
            task.id=0;
        }
        else {
            task.id=taskNode.get(taskNode.size()-1).get("id").asInt()+1;
        }
        taskNode.add(covertToJsoNode(task));
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File("jsonFile/jsonTask.json"), rootNode);
    }

    public static void showAllTask(){
        for (var task:taskNode){
            System.out.printf("Имя задачи: %s, Описание задачи: %s, Статус: %s, id: %s\n", task.get("title"), task.get("description"), task.get("isCompleted"), task.get("id"));
        }
    }

     public static void completeTask(int id) throws IOException {

         findTask(id).put("isCompleted", "Выполненa");
         objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File("jsonFile/jsonTask.json"), rootNode);
         System.out.println("Изменение сохранено");
     }

     public static void delTask(int id) throws IOException {
        removeTask(id);
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File("jsonFile/jsonTask.json"), rootNode);
     }

     public static void showTaskComplite(String stat){
        for (var item:taskNode){
            if(item.get("isCompleted").asText().equals(stat)){
                System.out.printf("Имя задачи: %s, Описание задачи: %s, Статус: %s, id: %s\n", item.get("title"), item.get("description"), item.get("isCompleted"), item.get("id"));
            }
        }
     }

    private static JsonNode covertToJsoNode(Task task) {
        return objectMapper.valueToTree(task);
    }

    private static JsonNode parse() throws IOException {
        return objectMapper.readTree(new File("jsonFile/jsonTask.json"));
    }

    private static ObjectNode findTask(int id){
        for (var item:taskNode){
            if(item.get("id").asInt()==id){
                var taskObject=(ObjectNode)item;
                return taskObject;
            }

        }
        return null;
    }
    private static void removeTask(int id) throws IOException {
        for (var item:taskNode){
            if(item.get("id").asInt()==id){
                taskNode.remove(id);
                objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File("jsonFile/jsonTask.json"), rootNode);
                break;
            }

        }
    }
}
