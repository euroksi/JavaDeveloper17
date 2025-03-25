package com.example.todolist.service;

import com.example.todolist.model.Note;
import com.example.todolist.repository.NoteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {
    private final NoteRepository noteRepository;

    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public List<Note> listAll() {
        return noteRepository.findAll();
    }

    public Note add(Note note) {
        return noteRepository.save(note);
    }

    public void deleteById(long id) {
        noteRepository.deleteById(id);
    }

    public void update(Note note) {
        if (noteRepository.existsById(note.getId())) {
            noteRepository.save(note);
        } else {
            throw new RuntimeException("Note with id " + note.getId() + " not found");
        }
    }

    public Note getById(long id) {
        return noteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Note with id " + id + " not found"));
    }
}