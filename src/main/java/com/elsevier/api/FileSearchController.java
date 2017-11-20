package com.elsevier.api;

import java.io.IOException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.elsevier.model.FileSearchBO;
import com.elsevier.service.FileSearchService;

/**
 * Provides uri to search for a text in files and return matching files.
 * @author vchitti
 *
 */
@Controller
@Path("/v1/search")
public class FileSearchController {

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	private FileSearchService textSearchService;

	public FileSearchController(@Autowired FileSearchService textSearchService) {
		this.textSearchService = textSearchService;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getMatchingFiles(@QueryParam("text") String query) throws FileSearchException {
		LOGGER.debug("Searching for text : {}", query);
		FileSearchBO sq = null;
		try {
			sq = textSearchService.getResults(query);
		} catch (IOException e) {
			throw new FileSearchException("Exception occured while reading/closing file");
		}
		
		return Response.ok(sq).type(MediaType.APPLICATION_JSON).build();
	}
	
}
