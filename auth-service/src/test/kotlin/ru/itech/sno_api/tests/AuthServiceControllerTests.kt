package ru.itech.sno_api.tests

import com.nhaarman.mockitokotlin2.whenever
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import ru.itech.sno_api.controller.AuthServiceController
import ru.itech.sno_api.core.domain.request.user.SignInRequest
import ru.itech.sno_api.core.domain.request.user.SignUpRequest
import ru.itech.sno_api.core.util.AuthTokenResponse
import ru.itech.sno_api.service.AuthService

@SpringBootTest
@AutoConfigureMockMvc
class AuthServiceControllerTests {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var authService: AuthService

    @Test
    fun `authenticate should return token response`() {
        val request = SignInRequest(login = "user@example.com", password = "password123")
        val response = AuthTokenResponse("access-token", "refresh-token")

        whenever(authService.authenticate(request)).thenReturn(response)

        mockMvc.perform(post("/api/auth/login")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"login\":\"user@example.com\",\"password\":\"password123\"}"))
            .andExpect(status().isOk)
            .andExpect(content().json("{\"accessToken\":\"access-token\",\"refreshToken\":\"refresh-token\"}"))
    }

    @Test
    fun `registerUser should return token response`() {
        val request = SignUpRequest(login = "JohnDoe", email = "newuser@example.com", password = "password123")
        val response = AuthTokenResponse("access-token", "refresh-token")

        whenever(authService.registerUser(request)).thenReturn(response)

        mockMvc.perform(post("/api/auth/register")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"login\":\"JohnDoe\",\"email\":\"newuser@example.com\",\"password\":\"password123\"}"))
            .andExpect(status().isOk)
            .andExpect(content().json("{\"accessToken\":\"access-token\",\"refreshToken\":\"refresh-token\"}"))
    }
}
