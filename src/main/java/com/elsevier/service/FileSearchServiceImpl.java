package com.elsevier.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.elsevier.model.FileSearchBO;

@Component
public class FileSearchServiceImpl implements FileSearchService {

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	@Value(value = "classpath:searchDirectory/")
	private Resource resources;

	@Override
	public FileSearchBO getResults(final String query) throws IOException {
		Path path= Paths.get(resources.getURI());
		 final List<File> files=new ArrayList<>();
		 try {
		    Files.walkFileTree(path, new SimpleFileVisitor<Path>(){
		     @Override
		     public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
		          if(!attrs.isDirectory()){
		               files.add(file.toFile());
		          }
		          return FileVisitResult.CONTINUE;
		      }
		     });
		 } catch (IOException exp) {
		     LOGGER.error("Error occured while walking through source directory ", exp);
		 }
		return getFiles(files , query);
	}
	
	/*
	 * Iterates each of the file, reads each line and checks for the text if it matches.
	 * If the text is present in a line then file name is added to the collection and 
	 * reads the next file. It continues till all the files have been read.
	 */
	private FileSearchBO getFiles(final List<File> filePaths, final String query) throws IOException {
		FileSearchBO sq = new FileSearchBO();
		sq.setQuery(query);
		int count = 0;
		for(File file : filePaths) {
			LineIterator it = FileUtils.lineIterator(file, "UTF-8");
			try {
			    while (it.hasNext()) {
			        String line = it.nextLine();
			        boolean isPresent =  Optional.of(line).filter(f -> isContain(f, query)).isPresent();
			        if (isPresent) {
			        	count++;
			        	sq.getFileNames().add(file.getAbsolutePath());
			        	sq.setCount(count);
			        	
			        	break;
			        }
			    }
			} finally {
			    LineIterator.closeQuietly(it);
			}

		}
		return sq;
	}
	
	/*
	 * Checks for exact text match
	 */
	 private static boolean isContain(String source, String subItem){
         String pattern = "\\b"+subItem+"\\b";
         Pattern p=Pattern.compile(pattern);
         Matcher m=p.matcher(source);
         return m.find();
    }

}
