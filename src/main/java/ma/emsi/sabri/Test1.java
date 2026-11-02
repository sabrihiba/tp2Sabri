package ma.emsi.sabri;

import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.google.genai.GoogleGenAiChatModel;

public class Test1 {

    public static void main(String[] args) {

        System.out.println("1. Programme démarré");

        String apiKey = System.getenv("GEMINI_KEY");

        if (apiKey == null || apiKey.isBlank()) {
            System.err.println("ERREUR : GEMINI_KEY introuvable !");
            return;
        }

        System.out.println("2. Clé API trouvée");

        try {

            ChatModel model = GoogleGenAiChatModel.builder()
                    .apiKey(apiKey)
                    .modelName("gemini-3.5-flash-lite")
                    .temperature(0.7)
                    .build();

            System.out.println("3. Modèle créé");

            //String question = "Quelle est la capitale de la France ?";
            //String question = "Quelle heure est-il actuellement ?";
            //String question1 = "Bonjour, je m'appelle Hiba Sabri.";
            //String reponse1 = model.chat(question1);

            String question = "Comment s'appelle le chat de Pierre ?";

            System.out.println("4. Envoi de la question à Gemini...");

            String reponse = model.chat(question);

            System.out.println("5. Réponse reçue !");

            System.out.println("Question  : " + question);
            System.out.println("Réponse  : " + reponse);

            //String question2 = "Comment je m'appelle ?";
            //String reponse2 = model.chat(question2);

            //System.out.println("Question 2 : " + question2);
            //System.out.println("Réponse 2 : " + reponse2);

            //String reponse = model.chat(question);

            //System.out.println("Question : " + question);
            //System.out.println("Réponse : " + reponse);

        } catch (Exception e) {
            System.err.println("ERREUR pendant l'appel à Gemini :");
            e.printStackTrace();
        }
    }
}