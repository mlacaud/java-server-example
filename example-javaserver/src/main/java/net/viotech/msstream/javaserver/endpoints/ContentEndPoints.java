package net.viotech.msstream.javaserver.endpoints;


import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import net.viotech.msstream.javaserver.model.Content;
import net.viotech.msstream.javaserver.repository.ContentRepository;
import net.viotech.msstream.javaserver.service.ContentService;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Component
@Path("content")
public class ContentEndPoints {
	
	/**
	 * Object which manage the content table in the database
	 * 
	 */
	@Inject
	ContentRepository contentRepository;
	
	@Inject
	ContentService contentService;
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON_VALUE)
	@Path("")
	public Response getContent(){
		List<Content> contents = (List<Content>) contentRepository.findAll();
		return Response.ok(contents).build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON_VALUE)
	@Path("{uuid}")
	public Response getContentByUuid(@PathParam("uuid") String uuid){
		Content content = contentRepository.findOneByUuid(uuid);
		return Response.ok(content).build();
	}
	
	@POST
	@Consumes({MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	@Path("")
	public Response postContent(Content content){
		contentRepository.save(content);
		return Response.accepted().build();
	}
	
	@POST
	@Path("{videoname}")
	public Response postContentWithName(@PathParam("videoname") String videoName){
		Content content = contentService.randomContent(videoName);
		contentRepository.save(content);
		return Response.accepted().build();
	}
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON_VALUE)
	@Consumes({MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	@Path("")
	public Response modifyContent(Content content){
		Content contentInDB = contentRepository.findOneByUuid(content.getUuid());
		contentInDB.setVideoName(content.getVideoName());
		contentRepository.save(contentInDB);
		return Response.accepted(contentInDB).build();
	}
	
	@DELETE
	@Path("all")
	public Response removeAllContent(){
		contentRepository.deleteAll();
		return Response.ok().build();
	}
	
	@DELETE
	@Consumes({MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	@Path("")
	public Response removeContent(Content content){
		Content contentToDelete = contentRepository.findOneByUuid(content.getUuid());
		contentRepository.delete(contentToDelete);
		return Response.ok().build();
	}
	
	@DELETE
	@Consumes({MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	@Path("{uuid}")
	public Response removeContent(@PathParam("uuid") String uuid){
		Content contentToDelete = contentRepository.findOneByUuid(uuid);
        contentRepository.delete(contentToDelete);
		return Response.ok().build();
	}
}
