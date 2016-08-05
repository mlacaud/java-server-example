package net.viotech.msstream.javaserver.repository;

import java.util.List;

import net.viotech.msstream.javaserver.model.Content;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

public interface ContentRepository extends CrudRepository<Content, Long>{
	
	Content findOneByUuid(String Uuid);
	
	List<Content> findContentByVideoName(String videoName);
	
}
