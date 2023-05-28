package pl.coderslab.newsmoodanalyzer.openai;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.theokanning.openai.completion.chat.ChatCompletionChoice;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.service.OpenAiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.newsmoodanalyzer.dto.EvaluationDTO;
import pl.coderslab.newsmoodanalyzer.model.Evaluation;
import pl.coderslab.newsmoodanalyzer.model.Post;
import pl.coderslab.newsmoodanalyzer.service.EvaluationService;

import java.time.Duration;
import java.util.List;

import static pl.coderslab.newsmoodanalyzer.openai.OpenAiApiKey.API_KEY;

@Service
public class ChatGPTHelper {

    @Autowired
    private EvaluationService evaluationService;

    private OpenAiService openAiService;
    private final String model = "gpt-3.5-turbo";

    public ChatGPTHelper() {

        openAiService = new OpenAiService(API_KEY, Duration.ofSeconds(30));
    }

    public void analyzePostContent(Post post) {
        String question = "Oceń występowanie poniższych emocji w tekście w skali od 0 do 100%.\n" +
                "\n" +
                "Lista emocji:\n" +
                "\n" +
                "1. joy\n" +
                "2. sadness\n" +
                "3. fear\n" +
                "4. anger\n" +
                "5. surprise\n" +
                "6. neutrality\n" +
                "7. satisfaction\n" +
                "8. trust\n" +
                "9. envy\n" +
                "10. disgust\n" +
                "11. excitement\n" +
                "12. positive sentiment\n" +
                "13. negative sentiment\n" +
                "\n" + post.getContent() +
                "\n Odpowiedź podaj w formacie JSON";

        ChatCompletionRequest completionRequest = ChatCompletionRequest.builder()
                .messages(List.of(new ChatMessage("user", question)))
                .model(model)
                .build();

        List<ChatCompletionChoice> choices = openAiService.createChatCompletion(completionRequest).getChoices();

        if (!choices.isEmpty()) {
            String content = choices.get(0).getMessage().getContent();
            EvaluationDTO evaluationDTO = extractEvaluationFromContent(content);
            Evaluation evaluation = mapDtoToEvaluation(evaluationDTO);
            evaluation.setPost(post);
            evaluationService.createEvaluation(evaluation);
        }
    }

    public EvaluationDTO extractEvaluationFromContent(String content) {

        JsonObject jsonObject = JsonParser.parseString(content).getAsJsonObject();

        // Extract the evaluation values from the JSON object
        double joy = jsonObject.get("joy").getAsDouble();
        double sadness = jsonObject.get("sadness").getAsDouble();
        double fear = jsonObject.get("fear").getAsDouble();
        double anger = jsonObject.get("anger").getAsDouble();
        double surprise = jsonObject.get("surprise").getAsDouble();
        double neutrality = jsonObject.get("neutrality").getAsDouble();
        double satisfaction = jsonObject.get("satisfaction").getAsDouble();
        double trust = jsonObject.get("trust").getAsDouble();
        double envy = jsonObject.get("envy").getAsDouble();
        double disgust = jsonObject.get("disgust").getAsDouble();
        double excitement = jsonObject.get("excitement").getAsDouble();
        double positiveSentiment = jsonObject.get("positive sentiment").getAsDouble();
        double negativeSentiment = jsonObject.get("negative sentiment").getAsDouble();

        // Create an EvaluationDTO object with the extracted values
        EvaluationDTO evaluationDTO = new EvaluationDTO();
        evaluationDTO.setJoy(joy);
        evaluationDTO.setSadness(sadness);
        evaluationDTO.setFear(fear);
        evaluationDTO.setAnger(anger);
        evaluationDTO.setSurprise(surprise);
        evaluationDTO.setNeutrality(neutrality);
        evaluationDTO.setSatisfaction(satisfaction);
        evaluationDTO.setTrust(trust);
        evaluationDTO.setEnvy(envy);
        evaluationDTO.setDisgust(disgust);
        evaluationDTO.setExcitement(excitement);
        evaluationDTO.setPositiveSentiment(positiveSentiment);
        evaluationDTO.setNegativeSentiment(negativeSentiment);

        return evaluationDTO;
    }


    private Evaluation mapDtoToEvaluation(EvaluationDTO evaluationDTO) {
        Evaluation evaluation = new Evaluation();
        evaluation.setJoy(evaluationDTO.getJoy());
        evaluation.setSadness(evaluationDTO.getSadness());
        evaluation.setFear(evaluationDTO.getFear());
        evaluation.setAnger(evaluationDTO.getAnger());
        evaluation.setSurprise(evaluationDTO.getSurprise());
        evaluation.setNeutrality(evaluationDTO.getNeutrality());
        evaluation.setSatisfaction(evaluationDTO.getSatisfaction());
        evaluation.setTrust(evaluationDTO.getTrust());
        evaluation.setEnvy(evaluationDTO.getEnvy());
        evaluation.setDisgust(evaluationDTO.getDisgust());
        evaluation.setExcitement(evaluationDTO.getExcitement());
        evaluation.setPositiveSentiment(evaluationDTO.getPositiveSentiment());
        evaluation.setNegativeSentiment(evaluationDTO.getNegativeSentiment());
        return evaluation;
    }
}

