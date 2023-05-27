package pl.coderslab.newsmoodanalyzer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import pl.coderslab.newsmoodanalyzer.controller.AuthorController;
import pl.coderslab.newsmoodanalyzer.model.Author;
import pl.coderslab.newsmoodanalyzer.service.AuthorService;

import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@DataJpaTest
@AutoConfigureMockMvc
public class AuthorControllerTest {

    @MockBean
    private AuthorService authorService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getAllAuthors_ShouldReturnListOfAuthors() throws Exception {
        // Arrange

        authorService.createAuthor(Author.builder().name("John Doe").build());
        authorService.createAuthor(Author.builder().name("Jane Smith").build());


        when(authorService.getAllAuthors()).thenReturn(Arrays.asList(
                Author.builder().id(1L).name("John Doe").build(),
                Author.builder().id(2L).name("Jane Smith").build()
        ));

        // Act & Assert
        mockMvc.perform(get("/api/authors")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("John Doe"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].name").value("Jane Smith"));
    }
}
