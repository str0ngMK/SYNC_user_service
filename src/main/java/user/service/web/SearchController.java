package user.service.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/search")
@RequiredArgsConstructor
public class SearchController {

//	@GetMapping("/test")
//	public ResponseEntity<ResponseMessage> search(@RequestParam(name = "keyword") String keyword) {
//		return ResponseEntity.ok().body(searchService.search(keyword));
//
//	}
}
