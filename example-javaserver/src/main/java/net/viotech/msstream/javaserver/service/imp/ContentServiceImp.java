package net.viotech.msstream.javaserver.service.imp;

import java.util.UUID;

import org.springframework.stereotype.Service;

import net.viotech.msstream.javaserver.model.Content;
import net.viotech.msstream.javaserver.service.ContentService;

@Service
public class ContentServiceImp implements ContentService {

	@Override
	public Content randomContent(String videoName) {
		Content content = new Content();
		content.setVideoName(videoName);
		content.setUuid(UUID.randomUUID().toString());
		return content;
	}
	
}
