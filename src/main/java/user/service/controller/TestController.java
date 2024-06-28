package user.service.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/user/api/v1")
@RequiredArgsConstructor
@Slf4j
public class TestController {
    private final WebClient.Builder webClientBuilder;
    int loadBalanceCount = 0;
    // existing code...
    @GetMapping("/test")
    public Mono<ResponseEntity<String>> responseTest() {
        loadBalanceCount += 1;
        log.info("loadBalanceCount : " + loadBalanceCount);

        String url = "http://129.213.161.199:31585/project/api/v1/test";
        return webClientBuilder.build()
                .get()
                .uri(url)
                .retrieve()
                .bodyToMono(String.class)
                .map(response -> {
                    log.info("Response from external API: " + response);
                    return ResponseEntity.ok(response + " from user service");
                });
    }
}
