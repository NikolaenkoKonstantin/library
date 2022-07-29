package ru.alishev.springcourse.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.alishev.springcourse.dao.BookDAO;
import ru.alishev.springcourse.dao.PersonDAO;
import ru.alishev.springcourse.models.Person;
import ru.alishev.springcourse.util.PersonValidator;

import javax.validation.Valid;

@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PersonDAO personDAO;
    private final PersonValidator personValidator;

    @Autowired
    public PeopleController(PersonDAO personDAO, BookDAO bookDAO, PersonValidator personValidator) {
        this.personDAO = personDAO;
        this.personValidator = personValidator;
    }

    //Conclusion of all people
    @GetMapping()
    public String index(Model model) {
        model.addAttribute("people", personDAO.index());
        return "people/index";
    }

    //Displaying information of a specific person
    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", personDAO.show(id));
        model.addAttribute("books", personDAO.getBooksByPersonId(id));
        return "people/show";
    }

    //Create new person, metod GET
    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person) {
        return "people/new";
    }

    //Creare new person, motod POST
    @PostMapping()
    public String create(@ModelAttribute("person") @Valid Person person,
                         BindingResult bindingResult) {
        personValidator.validate(person, bindingResult);

        if (bindingResult.hasErrors())
            return "people/new";

        personDAO.save(person);
        return "redirect:/people";
    }

    //Edit person, metod GET
    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("person", personDAO.show(id).get());
        return "people/edit";
    }

    //Edit person, metod PATCH
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        personValidator.validate(person, bindingResult);

        if (bindingResult.hasErrors())
            return "people/edit";

        personDAO.update(id, person);
        return "redirect:/people";
    }

    //Delete person
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        personDAO.delete(id);
        return "redirect:/people";
    }
}
