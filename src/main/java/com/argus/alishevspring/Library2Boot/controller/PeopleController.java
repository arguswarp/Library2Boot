package com.argus.alishevspring.Library2Boot.controller;


import com.argus.alishevspring.Library2Boot.model.Book;
import com.argus.alishevspring.Library2Boot.model.Person;
import com.argus.alishevspring.Library2Boot.service.BooksService;
import com.argus.alishevspring.Library2Boot.service.PeopleService;
import com.argus.alishevspring.Library2Boot.util.PersonValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.logging.Logger;

@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PeopleService peopleService;

    private final BooksService booksService;
    private final PersonValidator personValidator;

    private final Logger logger = Logger.getLogger("com.argus.alishevspring");

    @Autowired
    public PeopleController(PeopleService peopleService, BooksService booksService, PersonValidator personValidator) {
        this.peopleService = peopleService;
        this.booksService = booksService;
        this.personValidator = personValidator;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("people", peopleService.index());
        return "people/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model, @ModelAttribute("book") Book book) {
        model.addAttribute("books", booksService.index());
        model.addAttribute("person", peopleService.show(id));
        model.addAttribute("personBooks", peopleService.showBooks(id));
        return "people/show";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person) {
        return "people/new";
    }

    @PostMapping
    public String create(@ModelAttribute("person") @Valid Person person,
                         BindingResult bindingResult) {
        personValidator.validate(person, bindingResult);
        if (bindingResult.hasErrors()) {
            return "people/new";
        }
        peopleService.save(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("person", peopleService.show(id));
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult, @PathVariable("id") int id) {
        personValidator.validate(person, bindingResult);
        if (bindingResult.hasErrors()) {
            return "people/edit";
        }
        peopleService.update(id, person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        peopleService.delete(id);
        return "redirect:/people";
    }

    @PatchMapping("/{id}/assign")
    public String assign(@PathVariable("id") int id, @ModelAttribute("book") Book selectedBook) {
        booksService.assignPerson(selectedBook.getBookId(), peopleService.show(id));
        return "redirect:/people/" + id;
    }

    @PatchMapping("/{id}/release")
    public String release(@PathVariable("id") int id) {
        int personId = booksService.showPerson(id).getPersonId();
        booksService.releasePerson(id);
        return "redirect:/people/" + personId;
    }
}
