package com.elsevier.test;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import org.hamcrest.collection.IsEmptyCollection;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.core.io.Resource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.util.ReflectionTestUtils;

import com.elsevier.model.FileSearchBO;
import com.elsevier.service.FileSearchServiceImpl;

/**
 * The directory should exist in test-classes.
 * 
 * @author vchitti
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class FileSearchServiceImplTest {

	@InjectMocks
	private FileSearchServiceImpl underTest;

	@Mock
	private Resource resources;

	@Before
	public void setup() {
		ReflectionTestUtils.setField(underTest, "resources", resources);
	}

	@Test
	public void shouldReturnFilesForValidMatch() throws IOException,
			URISyntaxException {
		ClassLoader classLoader = getClass().getClassLoader();
		URL resource = classLoader.getResource("testDirectory");

		if (resource != null) {
			Mockito.when(resources.getURI()).thenReturn(resource.toURI());
			FileSearchBO results = underTest.getResults("This is a spring boot");

			assertNotNull(results);
			assertThat(results.getFileNames(), hasSize(1));			
		}
	}

	@Test
	public void shouldNotReturnAnyFilesForInvalidMatch() throws IOException,
			URISyntaxException {
		ClassLoader classLoader = getClass().getClassLoader();
		URL resource = classLoader.getResource("testDirectory");
		
		if (resource != null) {
			Mockito.when(resources.getURI()).thenReturn(resource.toURI());
			FileSearchBO results = underTest.getResults("restApi test");

			assertNotNull(results);
			assertThat(results.getFileNames(), IsEmptyCollection.empty());		
		}
	}

	@Test
	public void shouldReturnEmptyWhenDirectoryIsEmpty() throws IOException,
			URISyntaxException {
		ClassLoader classLoader = getClass().getClassLoader();
		URL resource = classLoader.getResource("subDirectory");
		
		if (resource != null) {
			Mockito.when(resources.getURI()).thenReturn(resource.toURI());
			FileSearchBO results = underTest.getResults("used for Junit test");

			assertNotNull(results);
			assertThat(results.getFileNames(), IsEmptyCollection.empty());	
		}

	}

}
