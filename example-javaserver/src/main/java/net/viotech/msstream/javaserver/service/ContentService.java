package net.viotech.msstream.javaserver.service;

import net.viotech.msstream.javaserver.model.Content;

public interface ContentService {

	/**
	 * Create a Content object 
	 * 
	 */
	public abstract Content randomContent(String videoName);
	
}
