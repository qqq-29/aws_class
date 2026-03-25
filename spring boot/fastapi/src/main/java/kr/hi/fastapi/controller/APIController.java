package kr.hi.fastapi.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class APIController {

	private final WebClient webClient;
	
	@PostMapping("/proxy/text")
	public ResponseEntity<?> proxyText(@RequestParam("msg")String msg){
		MultipartBodyBuilder bodyBuild = new MultipartBodyBuilder();
		bodyBuild.part("msg", msg);
		String result = webClient.post().uri("/text")
				// 첨부파일일때 필요
				.contentType(MediaType.MULTIPART_FORM_DATA)
				// 위에서 bodyBuild에 추가한 데이터를 묶어서 멀티파트데이터형식으로 변환
				// 해서 추가
				.body(BodyInserters.fromMultipartData(bodyBuild.build()))
				// 서버에 요청을 보내고 응답을 받음
				.retrieve()
				//받아온 데이터를 지정한 타입으로 변환
				.bodyToMono(String.class)
				// 응답이 올때까지 기다림
				.block();
		return ResponseEntity.ok(result);
	}
}
