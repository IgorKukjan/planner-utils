package ru.javabegin.micro.planner.utils.rest.webclient;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import ru.javabegin.micro.planner.entity.User;

@Component

// спец. класс для вызова микросервисов пользователей
public class UserWebClientBuilder {

    private static final String baseUrl = "http://localhost:8765/planner-users/user/";
    private static final String baseUrlData = "http://localhost:8765/planner-todo/data/";

    // проверка - существует ли пользователь
    public boolean userExists(Long userId) {

        try {

            User user = WebClient.create(baseUrl)
                    .post()
                    .uri("id")
                    .bodyValue(userId)
                    .retrieve()
                    .bodyToFlux(User.class)
                    .blockFirst();

            if (user != null) {
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // проверка - существует ли пользователь
    public Flux<User> userExistsAsync(Long userId) {

        Flux<User> fluxUser = WebClient.create(baseUrl)
                .post()
                .uri("id")
                .bodyValue(userId)
                .retrieve()
                .bodyToFlux(User.class);

        return fluxUser;
    }

    //иниц. начальных данных
    public Flux<Boolean> initUserData(Long userId){
        Flux<Boolean> fluxUser = WebClient.create(baseUrlData)
                .post()
                .uri("init")
                .bodyValue(userId)
                .retrieve()
                .bodyToFlux(Boolean.class);

        return fluxUser;
    }
}
