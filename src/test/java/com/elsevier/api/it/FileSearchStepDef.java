package com.elsevier.api.it;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.elsevier.model.FileSearchBO;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class FileSearchStepDef extends FileSearchIT<FileSearchBO> {

	private String textToSearch;
	
	private ResponseEntity<FileSearchBO> executeGet = null;

	@Given("^uri \"([^\"]*)\"$")
	public void uri(String uri) throws Throwable {
		executeGet = executeGet(uri, FileSearchBO.class);
	}

	@Then("^a response is returned with statusCode (\\d+)$")
	public void a_response_is_returned_with_statusCode(int statusCode) throws Throwable {
		assertThat(HttpStatus.valueOf(statusCode), equalTo(executeGet.getStatusCode()));
	}

	@Given("^a text \"([^\"]*)\" to search$")
	public void a_text_to_search(String query) throws Throwable {
		textToSearch = query;
	}
	
	@When("^fileSearch api is called$")
	public void filesearch_api_is_called() throws Throwable {
		 executeGet = executeGet("http://localhost:8080/elsevier/v1/search?text="+textToSearch, FileSearchBO.class);
	}

	@Then("^a valid response is returned with statusCode (\\d+)$")
	public void a_valid_response_is_returned(int statusCode) throws Throwable {
		assertThat(HttpStatus.OK, equalTo(executeGet.getStatusCode()));
	}
	
	@Then("^the number of occurrences is (\\d+)$")
	public void the_file_count_is(int count) throws Throwable {
		FileSearchBO body = executeGet.getBody();
		assertThat(body.getCount(), equalTo(count));
	}

}
