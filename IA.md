# IA.md — Utilisation de l'intelligence artificielle

## TP2 — LangChain4j : Tests

**Outil utilisé :** ChatGPT (OpenAI)

**Objectif :** Documenter l'utilisation de l'intelligence artificielle pendant la réalisation des tests du TP2 LangChain4j, conformément aux consignes du professeur.

L'IA a été utilisée pour obtenir des explications, comprendre certaines erreurs, adapter des exemples de code et analyser les résultats des expérimentations.

---

## 1. Configuration du projet Maven et de Gemini

**Problème rencontré :**

Lors de la configuration du projet Maven dans IntelliJ IDEA, certaines classes de LangChain4j, notamment `GoogleGenAiChatModel`, n'étaient pas reconnues. Il fallait également comprendre comment configurer le modèle Gemini et gérer la clé API.

**Raison de l'utilisation de l'IA :**

Comprendre l'origine des erreurs de dépendances et vérifier la configuration du projet.

**Prompt utilisé (extrait) :**

« Cannot resolve symbol GoogleGenAiChatModel »

**Réponse de l'IA (résumé) :**

L'IA a expliqué qu'une classe non reconnue pouvait provenir d'une dépendance Maven manquante ou non téléchargée. Elle a proposé de vérifier le fichier `pom.xml`, de recharger les dépendances Maven et de vérifier les imports Java.

Elle a également expliqué comment récupérer la clé Gemini avec `System.getenv("GEMINI_KEY")`, sans l'écrire directement dans le code source.

**Ce que j'ai appris (à reformuler avec mes propres mots) :**

J'ai compris que les bibliothèques Java externes doivent être déclarées dans le fichier `pom.xml` et que Maven se charge de leur téléchargement. J'ai également compris l'intérêt des variables d'environnement pour éviter d'exposer les clés API dans le code ou sur GitHub.

---

## 2. Premiers appels au modèle Gemini et mémoire conversationnelle

**Problème rencontré :**

Comprendre le fonctionnement des appels au LLM et expliquer pourquoi Gemini ne conservait pas automatiquement les informations transmises lors des requêtes précédentes.

**Raison de l'utilisation de l'IA :**

Interpréter les réponses du modèle et comprendre la différence entre des requêtes indépendantes et une conversation avec mémoire.

**Prompt utilisé (extrait des échanges) :**

« Quelle est la capitale de la France ? »

« Bonjour, je m'appelle Hiba Sabri. »

« Comment je m'appelle ? »

**Réponse de l'IA (résumé de l'aide reçue) :**

L'IA a expliqué que les appels classiques à l'API sont indépendants et que le modèle ne conserve pas automatiquement les messages précédents.

Pour maintenir le contexte, il faut renvoyer l'historique des échanges ou utiliser un mécanisme de mémoire conversationnelle, par exemple `MessageWindowChatMemory`.

**Ce que j'ai appris (à reformuler avec mes propres mots) :**

J'ai compris qu'un LLM n'est pas automatiquement capable de se souvenir des échanges précédents. La mémoire conversationnelle est gérée par l'application qui utilise le modèle.

---

## 3. Compréhension des tokens et du coût des requêtes

**Problème rencontré :**

Comprendre les informations retournées par LangChain4j concernant les tokens consommés et calculer le coût d'une requête.

**Raison de l'utilisation de l'IA :**

Vérifier les calculs et comprendre la différence entre les tokens d'entrée et les tokens de sortie.

**Prompt utilisé (extrait) :**

« Comment avoir l'usage des tokens ? »

**Réponse de l'IA (résumé) :**

L'IA a expliqué comment récupérer les informations d'utilisation d'une réponse LangChain4j et distinguer les tokens d'entrée, les tokens de sortie et leur total.

Elle a également présenté le principe du calcul du coût à partir du nombre de tokens consommés et des tarifs associés au modèle.

**Ce que j'ai appris (à reformuler avec mes propres mots) :**

J'ai compris que la consommation d'un modèle dépend notamment de la longueur du prompt, du contexte transmis et de la réponse générée. Les tokens d'entrée et de sortie peuvent être facturés à des tarifs différents.

---

## 4. Utilisation de `Result<T>` et des services IA

**Problème rencontré :**

Comprendre le rôle de `Result<T>` dans LangChain4j et la différence entre une réponse textuelle simple et une réponse contenant des informations supplémentaires.

**Raison de l'utilisation de l'IA :**

Mieux comprendre les types de retour des méthodes Java utilisées pour communiquer avec le modèle.

**Prompt utilisé :**

« C'est quoi result<T> ? »

**Réponse de l'IA (résumé) :**

L'IA a expliqué que `Result<T>` est un type générique qui permet de récupérer le résultat d'un service IA avec des informations supplémentaires, notamment des métadonnées liées à l'exécution.

Elle a également expliqué le rôle des interfaces Java utilisées avec `AiServices`, dont l'implémentation est générée automatiquement par LangChain4j.

**Ce que j'ai appris (à reformuler avec mes propres mots) :**

J'ai compris la différence entre récupérer uniquement un `String` et utiliser un type de retour plus riche pour accéder aux informations associées à la réponse.

---

## 5. Compréhension des embeddings et de la similarité cosinus

**Problème rencontré :**

Interpréter les résultats de similarité entre plusieurs phrases, notamment lorsque deux phrases de sens opposé obtenaient un score relativement élevé.

**Raison de l'utilisation de l'IA :**

Comprendre comment les modèles d'embeddings représentent les phrases et comment interpréter les scores.

**Prompt utilisé (formulation représentative à remplacer par la question exacte si disponible) :**

« Pourquoi deux phrases de sens opposé peuvent-elles avoir une similarité cosinus élevée ? »

**Réponse de l'IA (résumé) :**

L'IA a expliqué que les embeddings représentent des caractéristiques sémantiques des textes sous forme de vecteurs.

Deux phrases peuvent partager un même sujet, un vocabulaire proche ou une structure similaire tout en exprimant des opinions opposées. Leur similarité vectorielle peut donc rester élevée.

**Ce que j'ai appris (à reformuler avec mes propres mots) :**

J'ai compris que la similarité cosinus mesure la proximité de deux vecteurs, mais ne permet pas à elle seule de déterminer si deux phrases se contredisent.

---

## 6. Compréhension du RAG et de l'ingestion documentaire

**Problème rencontré :**

Comprendre pourquoi les documents doivent être découpés en segments et comment LangChain4j utilise les documents récupérés pour construire une réponse.

**Raison de l'utilisation de l'IA :**

Comprendre le fonctionnement du pipeline RAG et le rôle des différentes étapes.

**Prompt utilisé :**

« Pourquoi les documents externes sont presque toujours découpés en segments pendant la phase d'ingestion du RAG ? »

**Réponse de l'IA (résumé) :**

L'IA a expliqué que le découpage en segments facilite l'indexation et la recherche des passages pertinents.

Les segments peuvent être transformés en embeddings, enregistrés dans un magasin vectoriel et récupérés selon leur proximité avec la question de l'utilisateur.

L'IA a également expliqué l'intérêt du chevauchement (*overlap*) entre segments pour limiter la perte d'informations aux frontières des passages.

**Ce que j'ai appris (à reformuler avec mes propres mots) :**

J'ai compris que le RAG permet d'enrichir les réponses du LLM avec des informations provenant de documents externes, sans avoir à transmettre l'intégralité du corpus à chaque question.

---

## 7. Utilisation des outils Java par le LLM

**Problème rencontré :**

Comprendre comment Gemini décide d'utiliser une méthode Java et comment les paramètres sont transmis à cette méthode.

**Raison de l'utilisation de l'IA :**

Comprendre les interactions entre le modèle, LangChain4j et les outils Java utilisés dans le TP météo.

**Prompt utilisé :**

« Quelles sont les deux requêtes pour utiliser un outil pour agent IA ? »

**Réponse de l'IA (résumé) :**

L'IA a expliqué que l'utilisation d'un outil implique plusieurs échanges : le modèle demande l'exécution d'un outil avec certains paramètres, l'application Java exécute la méthode correspondante, puis transmet le résultat au modèle.

Le modèle peut ensuite produire une réponse finale ou demander l'exécution d'un autre outil.

**Ce que j'ai appris (à reformuler avec mes propres mots) :**

J'ai compris que le LLM ne lance pas directement les méthodes Java. Il produit une demande d'exécution que LangChain4j interprète et transmet au programme Java.

---

## 8. Analyse des logs et optimisation des appels d'outils

**Problème rencontré :**

Comprendre les échanges enregistrés dans les logs après l'activation de `.logRequestsAndResponses(true)` et expliquer pourquoi plusieurs appels à Gemini étaient nécessaires pour répondre à une seule question.

**Raison de l'utilisation de l'IA :**

Analyser les requêtes, les réponses, les appels d'outils et la consommation de tokens afin de préparer le compte rendu demandé.

**Prompt utilisé (formulation représentative à vérifier dans l'historique) :**

« Comment expliquer les échanges entre Gemini et Java lorsqu'on utilise les outils getCoordonnees et isPluiePrevue ? »

**Réponse de l'IA (résumé) :**

L'IA a aidé à distinguer les trois étapes observées dans les logs :

1. Gemini demande les coordonnées de la ville.
2. Après réception des coordonnées, Gemini demande les prévisions météorologiques.
3. Après réception du résultat météo, Gemini formule sa réponse finale.

Elle a également expliqué qu'un outil Java combinant la géolocalisation et la consultation météo pourrait réduire le nombre d'échanges avec le modèle.

**Ce que j'ai appris (à reformuler avec mes propres mots) :**

J'ai compris qu'une question utilisateur peut entraîner plusieurs appels à l'API du LLM et que chaque échange peut augmenter la consommation de tokens. Le regroupement de certaines opérations dans un outil Java peut réduire cette consommation.

---

## 9. Préparation du compte rendu des tests

**Problème rencontré :**

Présenter les nombreux résultats expérimentaux, les explications et les extraits de logs dans un email lisible, sans utiliser de tableaux.

**Raison de l'utilisation de l'IA :**

Organiser les résultats des tests et améliorer la clarté du compte rendu destiné au professeur.

**Prompt utilisé :**

« Mais je peux pas envoyer des tableaux dans mon mail, ça doit être un paragraphe. »

**Réponse de l'IA (résumé) :**

L'IA a proposé de reformuler le compte rendu sous forme de paragraphes structurés, en conservant les valeurs numériques, les questions/réponses et les extraits pertinents des logs.

**Ce que j'ai appris (à reformuler avec mes propres mots) :**

J'ai compris l'importance de présenter des résultats techniques de manière claire et de distinguer les observations expérimentales des explications théoriques.

---

## Conclusion

L'utilisation de ChatGPT m'a apporté une aide pour comprendre certaines notions de LangChain4j, interpréter les résultats expérimentaux et résoudre des difficultés de configuration et de programmation.

Les tests ont été exécutés dans mon environnement Java et les résultats ont été analysés à partir des sorties obtenues.

Les explications et les propositions de code fournies par l'IA ont été utilisées comme support de compréhension et de réalisation du TP.