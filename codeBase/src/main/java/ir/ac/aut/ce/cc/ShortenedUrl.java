package ir.ac.aut.ce.cc;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name = "CC_URLS")
public class ShortenedUrl {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id; // we use this as redirect "code" too.
	private String url;

	@Temporal(TemporalType.TIMESTAMP)
	private Timestamp expire;

	public ShortenedUrl() {
		// default
	}

	public ShortenedUrl(String url, Timestamp expireDate) {
		this.url = url;
		this.expire = expireDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Timestamp getExpire() {
		return expire;
	}

	public void setExpire(Timestamp expire) {
		this.expire = expire;
	}

}
