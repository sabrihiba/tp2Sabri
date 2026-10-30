
package ma.emsi.sabri;

import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.response.ChatResponse;
import dev.langchain4j.model.google.genai.GoogleGenAiChatModel;
import dev.langchain4j.data.message.UserMessage;

public class Test2Couts {

    public static void main(String[] args) {

        String apiKey = System.getenv("GEMINI_KEY");

        ChatModel model = GoogleGenAiChatModel.builder()
                .apiKey(apiKey)
                .modelName("gemini-3.5-flash-lite")
                .temperature(0.7)
                .build();

        String question = "Quelle est la capitale de la France ?";

        ChatResponse response = model.chat(
                UserMessage.from(question)
        );

        System.out.println("Question : " + question);
        System.out.println("Réponse : " + response.aiMessage().text());

        System.out.println("Tokens entrée : "
                + response.tokenUsage().inputTokenCount());

        System.out.println("Tokens sortie : "
                + response.tokenUsage().outputTokenCount());

        System.out.println("Tokens total : "
                + response.tokenUsage().totalTokenCount());

        // Calcul du coût de la requête

        int inputTokens = response.tokenUsage().inputTokenCount();
        int totalTokens = response.tokenUsage().totalTokenCount();

        // Estimation des tokens de sortie facturables
        // (incluant les éventuels tokens de raisonnement)
        int billableOutputTokens = totalTokens - inputTokens;

        // Tarifs du modèle gemini-3.5-flash-lite
// Prix en dollars pour 1 million de tokens
        double inputPrice = 0.30;
        double outputPrice = 2.50;

        double inputCost = inputTokens * inputPrice / 1_000_000;
        double outputCost = billableOutputTokens * outputPrice / 1_000_000;

        double totalCost = inputCost + outputCost;

        double requestsForOneDollar = 1.0 / totalCost;

        System.out.println("\n--- Calcul du coût ---");

        System.out.println("Tokens sortie facturables estimés : "
                + billableOutputTokens);

        System.out.printf("Coût entrée : %.10f $%n", inputCost);
        System.out.printf("Coût sortie estimé : %.10f $%n", outputCost);
        System.out.printf("Coût total estimé : %.10f $%n", totalCost);

        System.out.printf("Nombre de requêtes pour 1 dollar : %.0f%n",
                Math.ceil(requestsForOneDollar));
    }
}
