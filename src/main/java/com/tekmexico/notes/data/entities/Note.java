package com.tekmexico.notes.data.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="note")
public class Note {

	@Id
	@Column(name="note_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer noteId;
	
	@Column(name="created")
	private Date created;
	
	@Column(name="modified")
	private Date modified;
	
	@Column(name="content")
	private String content;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="users_user_id")
	private User userId;
	
	@Column(name="title")
	private String title;
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getNoteId() {
		return noteId;
	}

	public void setNoteId(Integer noteId) {
		this.noteId = noteId;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getModified() {
		return modified;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@JsonIgnore
	public User getUserId() {
		return userId;
	}

	public void setUserId(User userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "Note [noteId=" + noteId + ", created=" + created + ", modified=" + modified + ", content=" + content
				+ ", userId=" + userId + ", title=" + title + "]";
	}
	
}
