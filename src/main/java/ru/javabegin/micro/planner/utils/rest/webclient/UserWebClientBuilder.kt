package ru.javabegin.micro.planner.utils.rest.webclient

import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Flux
import ru.javabegin.micro.planner.entity.User

@Component // спец. класс для вызова микросервисов пользователей
class UserWebClientBuilder {

    companion object {
        private const val baseUrl = "http://localhost:8765/planner-users/user/"
        private const val baseUrlData = "http://localhost:8765/planner-todo/data/"
    }

    // проверка - существует ли пользователь
    fun userExists(userId: Long): Boolean {
        try {
            val user = WebClient.create(baseUrl)
                .post()
                .uri("id")
                .bodyValue(userId)
                .retrieve()
                .bodyToFlux(User::class.java)
                .blockFirst()

            if (user != null) {
                return true
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return false
    }

    // проверка - существует ли пользователь
    fun userExistsAsync(userId: Long): Flux<User> {
        val fluxUser = WebClient.create(baseUrl)
            .post()
            .uri("id")
            .bodyValue(userId)
            .retrieve()
            .bodyToFlux(User::class.java)

        return fluxUser
    }

    //иниц. начальных данных
    fun initUserData(userId: Long): Flux<Boolean> {
        val fluxUser = WebClient.create(baseUrlData)
            .post()
            .uri("init")
            .bodyValue(userId)
            .retrieve()
            .bodyToFlux(Boolean::class.java)

        return fluxUser
    }

}
