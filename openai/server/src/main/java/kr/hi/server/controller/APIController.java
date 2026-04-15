package kr.hi.server.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
	public String adcopy(@RequestBody AdcopyDTO dto){
		String result =webClient.get()
		  .uri(uriBuilder-> uriBuilder
					.path("/chat")
					.queryParam("product", dto.product)
					.build())
//		  .bodyValue(dto)
		  
		  .retrieve()
		  .bodyToMono(String.class)
		  .block();
		
		return result;
		
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