
package ma.emsi.sabri;

// Assistant conversationnel
public interface AssistantMeteo {

    // Prend un message de l'utilisateur et retourne une réponse du LLM.
    String chat(String userMessage);
}
