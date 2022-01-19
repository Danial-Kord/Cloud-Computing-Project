package ir.ac.aut.ce.cc;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sun.istack.NotNull;

@RestController
public class UriRest {

	@Autowired
	private UrlService service;

	@PostMapping(value = "/doit", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public PostResponse shorten(@RequestBody PostRequest request) {

		try {
			ShortenedUrl shortened = service.shorten(request.getUrl());
			return new PostResponse(
					String.format("Code: %s, expires at: %s", shortened.getId(), shortened.getExpire().toString()));
		} catch (Exception e) {
			return new PostResponse(e.getMessage());
		}
	}

	@GetMapping(value = "/{code}")
	public ResponseEntity<Void> gotoUrl(@PathVariable @NotNull Long code) {

		try {
			String url = service.getUrl(code);
			return ResponseEntity.status(HttpStatus.FOUND).location(URI.create("http://www.yahoo.com")).build();

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}

	}

}
