package org.sid.springairag;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.Media;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;
import org.springframework.ai.openai.OpenAiImageModel;
import org.springframework.ai.openai.OpenAiImageOptions;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.ai.openai.api.OpenAiImageApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.Objects;


@RestController
public class ChatRestController {
    private ChatClient chatClient;
    @Value("classpath:/prompt/system-message.st")
    private Resource systemMessageResource;


    public ChatRestController(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }
    @GetMapping(value = "/chat" ,produces = MediaType.TEXT_PLAIN_VALUE)
    public String chat(String question){
     return chatClient.prompt().user(question).call().content();
    }

    @GetMapping(value = "/chat2" ,produces = MediaType.TEXT_PLAIN_VALUE)
    public Flux<String> chat2(String question){
        return chatClient.prompt().user(question).stream().content();
    }

    @PostMapping(value = "/sentiment")
    public Sentiment sentiment(String review){
     return chatClient.prompt()
             .system(systemMessageResource)
             .user(review).call().entity(Sentiment.class);
    }
    @GetMapping("/describe")
    public Depenses test() throws IOException {
        byte[] data = new ClassPathResource("test.jpeg").getContentAsByteArray();
                String systemMessageText = """
                Ton role de faire la reconnaissance optique du texte qui se trouve dans l'image fournie.
                """;
        UserMessage userMessage =   new UserMessage(systemMessageText, List.of(new Media(MimeTypeUtils.IMAGE_JPEG, data)));
        return chatClient.prompt().messages(userMessage).call().entity(Depenses.class);
    }
    @GetMapping(path = "/generateImage", produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] generateImageDALLE() throws IOException{
        OpenAiImageApi openAiImageApi = new OpenAiImageApi("API");
        OpenAiImageModel openAiImageModel = new OpenAiImageModel(openAiImageApi);
        ImageResponse response = openAiImageModel.call(
                new ImagePrompt("un chat avec un costume dans une fete avec un cafe dans sa main.",
                        OpenAiImageOptions.builder()
                                .withModel("dall-e-3")
                                .withQuality("hd")
                                .withN(1)
                                .withResponseFormat("b64_json")
                                .withHeight(1024)
                                .withWidth(1024).build()
                )

        );
        String image = response.getResult().getOutput().getB64Json();
        return Base64.getDecoder().decode(image);
    }
    @GetMapping(path = "/generateImageSD", produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] generateImageSD(){
        RestClient restClient = RestClient.create();
        Map<String, Object> result = restClient.post()
                .uri("http://127.0.0.1:7860/sdapi/v1/txt2img")
                .body(Map.of("prompt", "Un chat avec un costume lors d'une fête, tenant une tasse de café dans sa main."))
                .retrieve()
                .body(Map.class);
        String imageBase64 = ((List<String>) result.get("images")).get(0);
        return Base64.getDecoder().decode(imageBase64);
    }
}
