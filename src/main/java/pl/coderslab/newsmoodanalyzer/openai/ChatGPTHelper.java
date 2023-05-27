package pl.coderslab.newsmoodanalyzer.openai;

import com.theokanning.openai.completion.chat.ChatCompletionChoice;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.service.OpenAiService;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;

import static pl.coderslab.newsmoodanalyzer.openai.OpenAiApiKey.API_KEY;

@Service
public class ChatGPTHelper {

    OpenAiService service;

    public ChatGPTHelper() {

        service = new OpenAiService(API_KEY, Duration.ofSeconds(30));
    }

    public String getAnswer(String textToAnalyze) {
        String question = "Oceń występowanie poniższych emocji w tekście w skali od 0 do 100%.\n" +
                "\n" +
                "Lista emocji:\n" +
                "\n" +
                "1. Radość\n" +
                "2. Smutek\n" +
                "3. Strach\n" +
                "4. Złość\n" +
                "5. Zaskoczenie\n" +
                "6. Obojętność\n" +
                "7. Zadowolenie\n" +
                "8. Zaufanie\n" +
                "9. Zazdrość\n" +
                "10. Wstręt\n" +
                "11. Pobudzenie\n" +
                "12. Sentyment pozytywny\n" +
                "13. Sentyment negatywny\n" +
                "\n" + textToAnalyze +
                "\n Odpowiedź podaj w formacie JSON";

        String anyQuestion = "";

        ChatCompletionRequest completionRequest = ChatCompletionRequest.builder()
                .messages(List.of(new ChatMessage("user", question)))
                .model("gpt-3.5-turbo")
                .build();
        List<ChatCompletionChoice> choices = service.createChatCompletion(completionRequest).getChoices();

        StringBuilder stringBuilder = new StringBuilder();

        choices.stream()
                .map(ChatCompletionChoice::getMessage)
                .map(ChatMessage::getContent)
                .forEach(stringBuilder::append);

        return stringBuilder.toString();
    }


}

