package com.tekmexico.notes.controllers;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tekmexico.notes.data.entities.Note;
import com.tekmexico.notes.data.entities.User;
import com.tekmexico.notes.data.repositories.NoteRepository;

@Controller
public class NoteApiController {
	
	@Autowired
	private NoteRepository noteRepo;

	@RequestMapping(value="/api/notes", method =  RequestMethod.POST)
	@ResponseBody
	public Note addNote(@RequestBody Note note, HttpServletRequest request) {
		
		note.setUserId(new User((Integer)request.getAttribute("userId")));
		
		// Setting the Note Dates
		if(note.getCreated() == null)
			note.setCreated(new Date());
		if(note.getModified() == null)
			note.setModified(new Date());
		
		System.out.println("Creating/Updating new note");
		Note _note = this.noteRepo.save(note);
		System.out.println("Id of iserted/updated note:" + _note.getNoteId());
		return _note;
		
	}
	
	@RequestMapping(value="/api/notes", method =  RequestMethod.DELETE)
	@ResponseBody
	public void deleteNote(@RequestParam("noteId") Integer noteId, HttpServletRequest request) {
		
		System.out.println("Deleting note");
		
		Note note = new Note();
		
		note.setNoteId(noteId);
		
		// Setting User Id from the Authorization token
		note.setUserId(new User((Integer)request.getAttribute("userId")));
		
		System.out.println("Deleting note: " + note);
		
		this.noteRepo.delete(note);
	}
}
