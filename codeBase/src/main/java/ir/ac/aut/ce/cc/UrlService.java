package ir.ac.aut.ce.cc;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class UrlService {

	@Autowired
	private UrlRepository repository;

	@Autowired
	private Environment environment;

	public ShortenedUrl shorten(String url) throws Exception {

		if (url == null || url.isBlank()) {
			throw new Exception("Empty URL!");
		}
		LocalDateTime expireTime = LocalDateTime.now()
				.plusDays(Long.parseLong(environment.getProperty("app.config.expire")));

		ShortenedUrl newURL = new ShortenedUrl(url, Date.from(expireTime.toInstant(ZoneOffset.UTC)));

		return repository.save(newURL);

	}

	public String getUrl(Long code) throws Exception {

		if (code == null) {
			throw new Exception("Empty Code!");
		}

		Optional<ShortenedUrl> requestedURL = repository.findById(code);

		if (requestedURL.isEmpty()) {
			throw new Exception("Not Found");
		}
		if (requestedURL.get().getExpire().before(Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC)))) {
			throw new Exception("Expired !");
		}

		return requestedURL.get().getUrl();
	}
}
