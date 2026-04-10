//package kr.hi.backend.controller;
//
//import org.springframework.http.client.MultipartBodyBuilder;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.reactive.function.BodyInserters;
//import org.springframework.web.reactive.function.client.WebClient;
//
//import lombok.AllArgsConstructor;
//
//@RestController
//@AllArgsConstructor 
//@RequestMapping("/api/v1")
//public class ProxyController {
//
//    private final WebClient webClient;
//
//    @GetMapping("/test")
//    public String test(@RequestParam("msg") String msg) {
//    	MultipartBodyBuilder bodyBuilder = new MultipartBodyBuilder();
//        bodyBuilder.part("msg", msg);
//        String result = webClient.post().uri("/test")
//          .body(BodyInserters.fromMultipartData(bodyBuilder.build()))
//          .retrieve()
//          .bodyToMono(String.class)
//          .block();
//          return result;
//      }
//}