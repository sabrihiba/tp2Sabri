
package ma.emsi.sabri;

import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.embedding.onnx.bgesmallenv15.BgeSmallEnV15EmbeddingModel;
import dev.langchain4j.model.google.genai.GoogleGenAiEmbeddingModel;
import dev.langchain4j.model.google.genai.GoogleGenAiEmbeddingModel.TaskTypeEnum;
import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.store.embedding.CosineSimilarity;

import java.time.Duration;

public class Test4GoogleEmbeddings {

    public static void main(String[] args) {

        String apiKey = System.getenv("GEMINI_KEY");

        // Modèle local
        EmbeddingModel modeleLocal =
                new BgeSmallEnV15EmbeddingModel();

        // Modèle Google
        EmbeddingModel modeleGoogle =
                GoogleGenAiEmbeddingModel.builder()
                        .apiKey(apiKey)
                        .modelName("gemini-embedding-001")
                        .taskType(TaskTypeEnum.SEMANTIC_SIMILARITY)
                        .timeout(Duration.ofSeconds(30))
                        .build();

        // Six couples de phrases en français
        String[][] couples = {
                {"J'aime les chats", "J'adore les félins"},
                {"J'aime les chats", "Je déteste les chats"},
                {"Il fait très beau aujourd'hui", "Le soleil brille dehors"},
                {"Il fait très beau aujourd'hui", "J'apprends la programmation Java"},
                {"Cette voiture roule très vite", "Ce véhicule est rapide"},
                {"Elle est heureuse", "Elle est très triste"}
        };

        for (int i = 0; i < couples.length; i++) {

            String phrase1 = couples[i][0];
            String phrase2 = couples[i][1];

            // Embeddings avec BGE
            Embedding local1 = modeleLocal.embed(phrase1).content();
            Embedding local2 = modeleLocal.embed(phrase2).content();

            double scoreLocal =
                    CosineSimilarity.between(local1, local2);

            // Embeddings avec Google
            Embedding google1 = modeleGoogle.embed(phrase1).content();
            Embedding google2 = modeleGoogle.embed(phrase2).content();

            double scoreGoogle =
                    CosineSimilarity.between(google1, google2);

            System.out.println("\n--- Couple " + (i + 1) + " ---");
            System.out.println("Phrase 1 : " + phrase1);
            System.out.println("Phrase 2 : " + phrase2);
            System.out.println("BGE local : " + scoreLocal);
            System.out.println("Google : " + scoreGoogle);
        }
    }
}
