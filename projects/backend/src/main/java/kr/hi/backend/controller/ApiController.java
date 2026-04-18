package kr.hi.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import lombok.AllArgsConstructor;
import lombok.Data;

@RestController
@AllArgsConstructor 
@RequestMapping("/api/v1")
@CrossOrigin(origins = "[http://localhost:3000](http://localhost:3000/)")
public class ApiController {
    private final WebClient webClient;
	@GetMapping("/test")
	@ResponseBody
	public String test(@RequestParam("msg") String msg) {
    	MultipartBodyBuilder bodyBuilder = new MultipartBodyBuilder();
        bodyBuilder.part("msg", msg);
        String result = webClient.post().uri("/test")
          .body(BodyInserters.fromMultipartData(bodyBuilder.build()))
          .retrieve()
          .bodyToMono(String.class)
          .block();
          return result;
      }
	
	@PostMapping("/test")
	public Test testPost(@RequestBody Test dto) {
		return dto;
	}
	
//	@PostMapping("/chat")
//	public String chatBot(@RequestParam("msg")String msg) {
//		MultipartBodyBuilder bodyBuilder = new MultipartBodyBuilder();
//		// 보낼 데이터를 추가
//		bodyBuilder.part("msg", msg);
//		
//		return webClient.post().uri("/chat")
//				.contentType(MediaType.MULTIPART_FORM_DATA)
//				.body(BodyInserters
//						.fromMultipartData(bodyBuilder.build()))//이미지가 없어서 body없어도 괜찮습
//				.retrieve()
//				.bodyToMono(String.class)
//				.block();
//	}
	@PostMapping("/chat")
	public ResponseEntity<String> chat(@RequestBody Test dto){
	try {
		String result =
				webClient.post()
					.uri("/chat")
					.bodyValue(dto)
					.retrieve()
					.bodyToMono(String.class)
					.block();
		
		return ResponseEntity.ok(result);
	}catch (WebClientResponseException e) {
        // 에러가 나면 AI 서버가 뭐라고 하는지 상세히 출력해봅니다.
        System.err.println("AI 서버 응답 에러: " + e.getResponseBodyAsString());
        return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
	}
	}
};
@Data
class TestDTO{
	String msg;
}
record Test(String msg) {}