package com.company.NoteApp.services;

import com.company.NoteApp.models.Note;
import com.company.NoteApp.repos.NotesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class NotesService {

    private final NotesRepository notesRepository;

    @Autowired
    public NotesService(NotesRepository notesRepository) {
        this.notesRepository = notesRepository;
    }

    @Transactional
    public void save(Note note) {
        note.setCreatedAt(new Date());

        notesRepository.save(note);
    }

    @Transactional
    public void update(int id, Note updatedNote) {
        updatedNote.setId(id);
        notesRepository.save(updatedNote);
    }

    @Transactional
    public void delete(int id) {
        notesRepository.deleteById(id);
    }

    public List<Note> findAll() {
        return notesRepository.findAll();
    }

    public Note findOne(int id) {
        Optional<Note> foundPerson = notesRepository.findById(id);
        return foundPerson.orElse(null);
    }

//    public List<Note> findByNoteName(String noteName) {
//        return notesRepository.findByNoteName(noteName);
//    }
//
//    public List<Note> findByOwner(User owner) {
//        return notesRepository.findByOwner(owner);
//    }



}
