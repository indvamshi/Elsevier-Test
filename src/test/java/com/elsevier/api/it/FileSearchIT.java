package com.elsevier.api.it;

import java.io.IOException;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;

import com.elsevier.FileSearchApplication;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@ContextConfiguration(classes = {FileSearchApplication.class})
public class FileSearchIT<T> {
	
	private TestRestTemplate restTemplate = new TestRestTemplate();
	
	public ResponseEntity<T> executeGet(String url, Class<T> clazz) throws IOException {
		return restTemplate.getForEntity(url, clazz);
	}

}
