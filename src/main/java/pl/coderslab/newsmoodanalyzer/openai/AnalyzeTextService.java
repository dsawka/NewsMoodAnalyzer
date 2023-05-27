package pl.coderslab.newsmoodanalyzer.openai;


public class AnalyzeTextService {

    private static ChatGPTHelper openAiService;

    public static void main(String[] args) {

        ChatGPTHelper chatGPTHelper = new ChatGPTHelper();
        String result = chatGPTHelper.getAnswer("Siergiej Szojgu grozi, że podobne ataki do tych, które miały miejsce w obwodzie biełgorodzkim, będą się spotykać z ostrą reakcją.\n" +
                "\n" +
                "— Podczas operacji antyterrorystycznej poprzez naloty, ostrzał artyleryjski i aktywne działania osłaniające granicę państwową jednostki Zachodniego Okręgu Wojskowego zablokowały i pokonały formacje nacjonalistyczne. Resztki nacjonalistów zostały zepchnięte z powrotem na terytorium Ukrainy, gdzie nadal były atakowane, aż do całkowitego wyeliminowania — powiedział Szojgu, cytowany przez TASS.");
        System.out.println(result);
    }
}
