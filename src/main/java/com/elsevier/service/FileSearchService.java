package com.elsevier.service;

import java.io.IOException;

import com.elsevier.model.FileSearchBO;

/**
 * Service to return all file names if the search query exists.
 * @author vchitti
 *
 */
public interface FileSearchService {

	/**
	 * Checks for files in all directories under searchDirectory folder.
	 * Recursively checks for files and stores the file paths in a collection.
	 * The response is encapsulated into {@link FileSearchBO}
	 * @param query to search in all files
	 * @return {@link FileSearchBO} which has the response
	 * @throws {@link IOException} if the service unable to read/close streams
	 */
	FileSearchBO getResults(String query) throws IOException;
}
