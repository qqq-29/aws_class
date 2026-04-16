package kr.hi.server.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.AllArgsConstructor;
import lombok.Data;

@RestController
@RequestMapping("/api/v1/ai")
@AllArgsConstructor
public class APIController {

	private final WebClient webClient;

	@GetMapping("/ask")
	public String ask(@RequestParam ("prompt")String prompt) {	
	    String result = webClient.get()
	      .uri(uriBuilder-> uriBuilder
	    		  .path("/ask")//main.py하고연결?
	      		  .queryParam("prompt", prompt)
	      		  .build())
	      .retrieve()
	      .bodyToMono(String.class)
	      .block();
	      return result;
	  }
	
	@GetMapping("/translate")
	public String translate(@RequestParam ("text")String text,
					@RequestParam ("style")String style) {	
	    String result = webClient.get()
	      .uri(uriBuilder-> uriBuilder
	    		  .path("/translate")
	      		  .queryParam("text", text)
	      		  .queryParam("style", style)
	      		  .build())
	      .retrieve()
	      .bodyToMono(String.class)
	      .block();
	      return result;
	  }
	
	@GetMapping("/ad-copy")
	public String adcopy(AdcopyDTO dto){
		System.out.println(dto);
		String result =webClient.get()
		  .uri(uriBuilder-> uriBuilder
					.path("/ad-copy")
					.queryParam("product", dto.getProduct())
					.queryParam("feature", dto.getFeature())
					.queryParam("target", dto.getTarget())
					.queryParam("temp", dto.getTemp())
					.queryParam("count", dto.getCount())
					.build())
//		  .bodyValue(dto)
		  
		  .retrieve()
		  .bodyToMono(String.class)
		  .block();
		
		return result;
		
	}
	
	@PostMapping("/summarize")
	public ResponseEntity<String> summarize(@RequestBody SummaryDTO dto){
		System.out.println(dto);
		return ResponseEntity.ok("ok");
	}
}
@Data
class AdcopyDTO{
	String product;
	String feature;
	String target;
	float temp;
	int count;
}

@Data
class SummaryDTO{
	String text;
	String target_lan;
	int max_sentence;
}