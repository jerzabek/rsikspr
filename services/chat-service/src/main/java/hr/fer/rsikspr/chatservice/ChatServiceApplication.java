package hr.fer.rsikspr.chatservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ChatServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(ChatServiceApplication.class, args);
  }

  @Bean
  public RestTemplate restTemplate() {
    RestTemplate restTemplate = new RestTemplate();

    HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
    requestFactory.setConnectTimeout(1000);
    restTemplate.setRequestFactory(requestFactory);

    return restTemplate;
  }

}
