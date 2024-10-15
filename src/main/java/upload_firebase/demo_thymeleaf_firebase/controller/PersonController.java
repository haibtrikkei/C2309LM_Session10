package upload_firebase.demo_thymeleaf_firebase.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import upload_firebase.demo_thymeleaf_firebase.model.entity.Person;
import upload_firebase.demo_thymeleaf_firebase.service.PersonService;
import upload_firebase.demo_thymeleaf_firebase.service.UploadService;

import java.util.List;

@Controller
@RequestMapping("/persons")
public class PersonController {
    @Autowired
    private PersonService personService;
    @Autowired
    private UploadService uploadService;

    @GetMapping
    public String home(Model model){
        List<Person> list = personService.findAll();
        model.addAttribute("list",list);
        return "listPerson";
    }

    @GetMapping("/initAddPerson")
    public String initAddPerson(Model model){
        Person p = new Person();
        model.addAttribute("p",p);
        return "insertPerson";
    }

    @PostMapping("/insertPerson")
    public String insertPerson (@Valid @ModelAttribute("p")Person p, BindingResult result, @RequestParam("avatarFile") MultipartFile avatarFile, Model model){
        if(result.hasErrors()){
            model.addAttribute("p",p);
            return "insertPerson";
        }
        String avatarUrl = uploadService.uploadToLocal(avatarFile);
        p.setAvatar(avatarUrl);

        personService.insert(p);
        return "redirect:/persons";
    }

}
