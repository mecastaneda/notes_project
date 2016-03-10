package com.tekmexico.notes.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tekmexico.notes.data.entities.Note;

@Repository
public interface NoteRepository extends JpaRepository<Note, Integer> {

}
