package com.company.NoteApp.controllers;

import com.company.NoteApp.models.Note;
import com.company.NoteApp.services.NotesService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/notes")
public class NotesController {

    private final NotesService notesService;

    @Autowired
    public NotesController(NotesService notesService) {
        this.notesService = notesService;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("notes", notesService.findAll());
        return "notes/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("note", notesService.findOne(id));
        return "notes/show";
    }

    @GetMapping("/new")
    public String newNote(@ModelAttribute("note") Note note) {
        return "notes/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("person") @Valid Note note,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "notes/new";

        notesService.save(note);
        return "redirect:/notes";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("note", notesService.findOne(id));
        return "notes/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid Note note, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "notes/edit";

        notesService.update(id, note);
        return "redirect:/notes";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        notesService.delete(id);
        return "redirect:/notes";
    }

}
