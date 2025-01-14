package ru.javabegin.micro.planner.utils.rest.resttemplate

import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import ru.javabegin.micro.planner.entity.User

@Component // спец. класс для вызова микросервисов пользователей
class UserRestBuilder {

    companion object {
        private const val baseUrl = "http://localhost:8765/planner-users/user/"
    }

    // проверка - существует ли пользователь
    fun userExists(userId: Long): Boolean {
        // для примера - как использовать RestTemplate (но он уже deprecated)

        val restTemplate = RestTemplate()
        val request: HttpEntity<Long> = HttpEntity<Long>(userId)

        var response: ResponseEntity<User?>? = null

        // если нужно получить объект - просто вызываете response.getBody() и произойдет автоматическая конвертация из JSON в POJO
        // в текущем вызове нам не нужен объект, т.к. мы просто проверяем, есть ли такой пользователь
        try {
            // вызов сервисы

            response = restTemplate.exchange(
                baseUrl + "/id", HttpMethod.POST, request,
                User::class.java
            )

            if (response.statusCode === HttpStatus.OK) { // если статус был 200
                return true
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return false // если статус не был 200
    }

}
