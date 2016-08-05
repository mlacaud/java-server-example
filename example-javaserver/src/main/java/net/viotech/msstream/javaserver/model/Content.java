package net.viotech.msstream.javaserver.model;

import java.util.Date;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "content")
@Entity
public class Content {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id; //Auto-generated
	
	private Date created; //Auto-generated the first time the object is put in the DB
	
	private Date updated; //Auto-generated when the object is updated
	
	private String videoName;
	
	private String uuid;
	
	public Content(){}
	
	@PrePersist
	protected void onCreate() {
		setCreated(new Date());
	}

	@PreUpdate
	protected void onUpdate() {
		setUpdated(new Date());
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public String getVideoName() {
		return videoName;
	}

	public void setVideoName(String videoName) {
		this.videoName = videoName;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}




	
}
