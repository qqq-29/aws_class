package kr.hi.backend.controller;

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
}
@Data
class TestDTO{
	String msg;
}
record Test(String msg) {}