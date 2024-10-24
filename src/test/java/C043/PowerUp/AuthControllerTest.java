package C043.PowerUp;

import C043.PowerUp.entities.User;
import C043.PowerUp.payloads.AuthDTO;
import C043.PowerUp.payloads.NewUserDTO;
import C043.PowerUp.services.AuthService;
import C043.PowerUp.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerTest {
    @MockBean
    private User user;

    @MockBean
    private UserService userService;

    @MockBean
    private AuthService authService;

    @Autowired
    private MockMvc mockMvc;

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void registerTest() throws Exception {
        NewUserDTO newUserTest = new NewUserDTO("Ultramaggot",
                "Mario",
                "Fragnito",
                "mariofragnitoph@gmail.com",
                "12345678");

        Mockito.when(userService.registerUser(Mockito.any()))
                .thenReturn(new User());

        mockMvc.perform(post("/auth/register")
                        .content(asJsonString(newUserTest))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void loginTest() throws Exception {
        AuthDTO loginUser = new AuthDTO("mariofragnitoph@gmail.com",
                "12345678");
       
        mockMvc.perform(post("/auth/login")
                        .content(asJsonString(loginUser))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
