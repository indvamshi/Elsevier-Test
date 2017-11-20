package com.elsevier.test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.io.IOException;

import javax.ws.rs.core.Response;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.elsevier.api.FileSearchController;
import com.elsevier.api.FileSearchException;
import com.elsevier.service.FileSearchService;

@RunWith(SpringJUnit4ClassRunner.class)
public class FileSearchControllerTest {

	@InjectMocks
	private FileSearchController underTest;
	
	@Mock
	private FileSearchService textSearchService;
	
	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void shouldReturn200() throws IOException, FileSearchException {
		Mockito.when(textSearchService.getResults(Mockito.any())).thenReturn(Mockito.any());
		Response response = underTest.getMatchingFiles("Mocked Test");
		
		assertThat(response.getStatus(), is(200));
	}
	
	@SuppressWarnings("unchecked")
	@Test(expected = FileSearchException.class)
	public void shouldThrowErrorResponse() throws IOException, FileSearchException {
		Mockito.when(textSearchService.getResults(Mockito.any())).thenThrow(IOException.class);
		underTest.getMatchingFiles("Mocked Test");
	}
}
