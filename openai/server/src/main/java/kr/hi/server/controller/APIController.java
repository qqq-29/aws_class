package kr.hi.server.controller;

import java.util.Map;

import org.jspecify.annotations.Nullable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class APIController {

	private final WebClient webClient;

	@PostMapping("/ask")
	public String aiAsk(@RequestBody Map<String, String> map) {
	    String msg = map.get("msg");
	    MultipartBodyBuilder bodyBuilder = new MultipartBodyBuilder();
		// 보낼 데이터를 추가
		bodyBuilder.part("msg", msg);
	    return webClient.post().uri("/ask")
	    		.queryParam("prompt", msg)
	    		.build())
				
				.retrieve()
				.bodyToMono(String.class)
				.map(res -> res.get("answer").toString())
				.block();
	}

}
