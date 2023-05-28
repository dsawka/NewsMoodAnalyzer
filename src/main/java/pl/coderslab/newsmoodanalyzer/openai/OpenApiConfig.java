package pl.coderslab.newsmoodanalyzer.openai;

import com.theokanning.openai.service.OpenAiService;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;
import static pl.coderslab.newsmoodanalyzer.openai.OpenAiApiKey.API_KEY;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(new Info().title("Mood Analyzer API").description(
                                "This is a sample application with Spring Boot and OpenAPI")
                        .version("1.0.1"));
    }

    @Bean
    public OpenAiService openAiService() {
        Duration timeout = Duration.ofSeconds(30);
        return new OpenAiService(API_KEY, timeout);
    }

}
