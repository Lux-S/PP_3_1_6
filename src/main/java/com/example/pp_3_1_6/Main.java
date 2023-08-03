package com.example.pp_3_1_6;

import org.springframework.http.ResponseEntity;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        ApiRequestManager apiRequestManager = new ApiRequestManager();

        List<User> users = apiRequestManager.getUsers();
        String sessionId = apiRequestManager.getSessionId();
        System.out.println("Session ID: " + sessionId);

// Выводим список пользователей
        for (User user : users) {
            System.out.println("User: " + user);
        }

        // Создаем пользователя
        String jsonData = "{ \"id\": \"3\", \"name\": \"James\", \"lastName\": \"Brown\", \"age\": 33 }";
        ResponseEntity<String> responsePost = apiRequestManager.createUser(jsonData);
        String responseBodyPost = responsePost.getBody();
        System.out.println("Response from POST request: " + responseBodyPost);

        // Изменяем пользователя
        jsonData = "{ \"id\": \"3\", \"name\": \"Thomas\", \"lastName\": \"Shelby\", \"age\": 33 }";
        ResponseEntity<String> responsePut = apiRequestManager.updateUser(jsonData);
        String responseBodyPut = responsePut.getBody();
        System.out.println("Response from PUT request: " + responseBodyPut);

        // Удаляем пользователя
        ResponseEntity<String> responseDelete = apiRequestManager.deleteUser("3");
        String responseBodyDelete = responseDelete.getBody();
        System.out.println("Response from DELETE request: " + responseBodyDelete);

        // Конкатенируем части кода и выводим итоговый код
        String finalCode = responseBodyPost + responseBodyPut + responseBodyDelete;
        System.out.println("Final code: " + finalCode);
    }
}
