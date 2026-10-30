
package ma.emsi.sabri;

import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.google.genai.GoogleGenAiChatModel;
import dev.langchain4j.model.input.Prompt;
import dev.langchain4j.model.input.PromptTemplate;

import java.util.Map;

public class Test3Traducteur {

    public static void main(String[] args) {

        String apiKey = System.getenv("GEMINI_KEY");

        ChatModel model = GoogleGenAiChatModel.builder()
                .apiKey(apiKey)
                .modelName("gemini-3.5-flash-lite")
                .temperature(0.7)
                .build();

        // PARTIE 1 : Template avec une seule variable
        System.out.println("--- Traduction avec une variable ---");

        PromptTemplate template = PromptTemplate.from(
                "Traduis le texte suivant en anglais : {{texte}}"
        );

        Prompt prompt = template.apply(
                Map.of("texte", "Bonjour tout le monde")
        );

        System.out.println("Prompt : " + prompt.text());

        String reponse = model.chat(prompt.text());

        System.out.println("Traduction : " + reponse);


        // PARTIE 2 : Template avec deux variables
        System.out.println("\n--- Traduction avec deux variables ---");

        PromptTemplate template2 = PromptTemplate.from(
                "Traduis le texte suivant en {{langue}} : {{texte}}"
        );

        Prompt prompt2 = template2.apply(
                Map.of(
                        "langue", "espagnol",
                        "texte", "Je suis étudiante en intelligence artificielle."
                )
        );

        System.out.println("Prompt : " + prompt2.text());

        String reponse2 = model.chat(prompt2.text());

        System.out.println("Traduction : " + reponse2);
    }
}
