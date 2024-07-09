package org.sid.springairag;

import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
//import org.springframework.ai.ollama.OllamaChatModel;
//import org.springframework.ai.ollama.api.OllamaApi;
//import org.springframework.ai.ollama.api.OllamaOptions;


import java.util.List;

public class Test1 {
    public static void main(String[] args){
//        OllamaApi ollamaApi = new OllamaApi();
//        OllamaChatModel ollamaChatModel = new OllamaChatModel(ollamaApi,
//                OllamaOptions.create()
//                        .withTemperature(0F)
//                        .withModel("mistral")
//        );
//
//        String systemMessageText = """
//            Vous êtes un assistant spécialisé dans le domaine de l'analyse des sentiments.
//            Votre tâche est d'extraire à partir d'un commentaire le sentiment des différents aspects
//            des ordinateurs achetés par des clients. Les aspects qui nous intéressent sont :
//            l'écran, la souris et le clavier. Le sentiment peut être : positif, négatif ou neutre.
//            Le résultat attendu sera au format JSON avec les champs suivants :
//            - clavier : le sentiment relatif au clavier
//            - souris : le sentiment relatif à la souris
//            - ecran : le sentiment relatif à l'écran
//            """;
//        SystemMessage systemMessage = new SystemMessage(systemMessageText);
//
//        String userInputText = """
//            Je ne suis pas satisfait par la qualité de l'écran,
//            mais le clavier est mauvais alors que pour la souris la qualité est plutôt assez bonne.
//            Par ailleurs, je pense que cet ordinateur consomme beaucoup d'énergie.
//            """;
//        UserMessage userMessage = new UserMessage(userInputText);
//
//        String userInputText1 = """
//            Je suis satisfait par la qualité de l'écran,
//            mais le clavier est mauvais alors que pour la souris la qualité est plutôt moyenne.
//            Par ailleurs, je pense que cet ordinateur consomme beaucoup d'énergie.
//            """;
//        UserMessage userMessage1 = new UserMessage(userInputText1);
//
//        String response = """
//            {
//            "clavier":"négatif",
//            "souris":"neutre",
//            "ecran":"positif"
//            }
//            """;
//        AssistantMessage assistantMessage1 = new AssistantMessage(response);
//
//        Prompt prompt = new Prompt(List.of(systemMessage, userMessage));
//        ChatResponse chatResponse = ollamaChatModel.call(prompt);
//        System.out.println(chatResponse.getResult().getOutput().getContent());
    }

}
