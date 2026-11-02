
package ma.emsi.sabri;

import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.embedding.onnx.bgesmallenv15.BgeSmallEnV15EmbeddingModel;
import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.store.embedding.CosineSimilarity;

public class Test4Embeddings {


    public static void main(String[] args) {

        EmbeddingModel model = new BgeSmallEnV15EmbeddingModel();

        String[][] couples = {
                {"I love cats", "I really like cats"},
                {"I love cats", "I hate cats"},
                {"The weather is beautiful today", "It is sunny outside"},
                {"The weather is beautiful today", "I am learning Java programming"},
                {"The car is very fast", "The vehicle moves quickly"},
                {"She is happy", "She is very sad"}
        };

        for (int i = 0; i < couples.length; i++) {

            String phrase1 = couples[i][0];
            String phrase2 = couples[i][1];

            Embedding embedding1 = model.embed(phrase1).content();
            Embedding embedding2 = model.embed(phrase2).content();

            double similarite = CosineSimilarity.between(
                    embedding1, embedding2
            );

            System.out.println("\n--- Couple " + (i + 1) + " ---");
            System.out.println("Phrase 1 : " + phrase1);
            System.out.println("Phrase 2 : " + phrase2);
            System.out.println("Similarité : " + similarite);
        }
    }

}
