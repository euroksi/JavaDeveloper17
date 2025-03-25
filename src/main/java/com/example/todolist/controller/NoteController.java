package com.example.todolist.controller;

import com.example.todolist.model.Note;
import com.example.todolist.service.NoteService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/note")
public class NoteController {
    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping("/list")
    @PreAuthorize("hasRole('USER')")
    public String listNotes(Model model) {
        model.addAttribute("notes", noteService.listAll());
        return "note/list";
    }

    @PostMapping("/delete")
    @PreAuthorize("hasRole('USER')")
    public String deleteNote(@RequestParam("id") long id) {
        noteService.deleteById(id);
        return "redirect:/note/list";
    }

    @GetMapping("/edit")
    @PreAuthorize("hasRole('USER')")
    public String editNoteForm(@RequestParam("id") long id, Model model) {
        Note note = noteService.getById(id);
        model.addAttribute("note", note);
        return "note/edit";
    }

    @PostMapping("/edit")
    @PreAuthorize("hasRole('USER')")
    public String editNote(@ModelAttribute Note note) {
        noteService.update(note);
        return "redirect:/note/list";
    }
}