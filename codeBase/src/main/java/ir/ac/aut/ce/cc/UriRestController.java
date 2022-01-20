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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sun.istack.NotNull;

@RestController
@RequestMapping("/")
public class UriRestController {

	@Autowired
	private UrlService service;

	/***
	 * 
	 * @param request includes a url field.
	 * @return code of the URL and its expire date.
	 * 
	 *         usage: you can use cURL: `curl --location --request POST
	 *         'http://localhost:8081/doit' --header 'Content-Type:
	 *         application/json' --data '{"url":"https://01d.ir/test"}'`
	 */
	@PostMapping(value = "/doit", produces = MediaType.APPLICATION_JSON_VALUE)
	public PostResponse shorten(@RequestBody PostRequest request) {

		try {
			ShortenedUrl shortened = service.shorten(request.getUrl());
			return new PostResponse(
					String.format("Code: %s, expires at: %s", shortened.getId(), shortened.getExpire().toString()));
		} catch (Exception e) {
			return new PostResponse(e.getMessage());
		}
	}

	/**
	 * 
	 * @param code
	 * @return Redirects to the URL
	 * 
	 *         usage: open a url like: http://localhost:8081/{code}
	 */

	@GetMapping(value = "/{code}")
	public ResponseEntity<Void> gotoUrl(@PathVariable @NotNull Long code) {

		try {
			String url = service.getUrl(code);
			return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(url)).build();

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}

	}

}
