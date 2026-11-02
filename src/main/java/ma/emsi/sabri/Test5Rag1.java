
package ma.emsi.sabri;

import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.loader.FileSystemDocumentLoader;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.google.genai.GoogleGenAiChatModel;
import dev.langchain4j.rag.content.retriever.EmbeddingStoreContentRetriever;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.EmbeddingStoreIngestor;
import dev.langchain4j.store.embedding.inmemory.InMemoryEmbeddingStore;

/**
 * Le RAG facile !
 */
public class Test5Rag1 {

    // Assistant conversationnel
    interface Assistant {
        // Prend un message de l'utilisateur et retourne une réponse du LLM.
        String chat(String userMessage);
    }

    public static void main(String[] args) {

        String llmKey = System.getenv("GEMINI_KEY");

        if (llmKey == null) {
            System.out.println("La variable d'environnement GEMINI_KEY n'est pas définie.");
            return;
        }

        // Température inférieure ou égale à 0,3
        ChatModel model = GoogleGenAiChatModel.builder()
                .apiKey(llmKey)
                .modelName("gemini-3.5-flash-lite")
                .temperature(0.3)
                .build();

        // Chargement du document
        String nomDocument = "infos.txt";
        Document document = FileSystemDocumentLoader.loadDocument(nomDocument);

        // Base vectorielle en mémoire
        EmbeddingStore<TextSegment> embeddingStore =
                new InMemoryEmbeddingStore<>();

        // Phase 1 : ingestion du document
        EmbeddingStoreIngestor.ingest(document, embeddingStore);

        // Création de l'assistant conversationnel
        Assistant assistant =
                AiServices.builder(Assistant.class)
                        .chatModel(model)
                        .chatMemory(MessageWindowChatMemory.withMaxMessages(10))
                        .contentRetriever(
                                EmbeddingStoreContentRetriever.from(embeddingStore)
                        )
                        .build();

        // Phase 2 : question avec RAG
        //String question = "Comment s'appelle le chat de Pierre ?";
        //String question = "Pierre appelle son chat. Qu'est-ce qu'il pourrait dire ?";
        String question = "Quelle est la capitale de la France ?";

        String reponse = assistant.chat(question);

        System.out.println("Question : " + question);
        System.out.println("Réponse : " + reponse);
    }
}
