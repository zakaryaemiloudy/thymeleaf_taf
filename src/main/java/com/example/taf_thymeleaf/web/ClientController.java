package com.example.taf_thymeleaf.web;

import com.example.taf_thymeleaf.Entities.Client;
import com.example.taf_thymeleaf.Repositories.ClientRepo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ClientController {

    private final ClientRepo clientRepo;


    @GetMapping({"/index", "/", "/clients"})
    public String ListClients(Model model) {
        List<Client> clients = (List<Client>) clientRepo.findAll();
        model.addAttribute("clients", clients);
        return "clients";
    }

    @GetMapping("/delete")
    public String DeleteClient(@RequestParam Long id) {
        clientRepo.deleteById(id);
        return "redirect:/clients";
    }

    @GetMapping("/addForm")
    public String AddClientForm(Model model) {
        model.addAttribute("client", new Client());
        return "/add";

    }

    @PostMapping("/add")
    public String AddClient(@Valid Client client, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("client", client);
            return "/add";
        }
        clientRepo.save(client);
        return "redirect:/clients";
    }


    @GetMapping("/updateForm")
    public String UpdateClientForm(@RequestParam Long id, Model model) {
        Client client = clientRepo.findById(id).get();
        model.addAttribute("client", client);
        return "update";
    }

    @PostMapping("/update")
    public String UpdateClient(@Valid Client client, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("client", client);
            return "update";
        }
        clientRepo.save(client);
        return "redirect:/clients";
    }


    @GetMapping("/search")
    public String SearchClient(Model model, @RequestParam String mot) {
        List<Client> clients = clientRepo.findClientsByNameContains(mot);
        model.addAttribute("clients", clients);
        model.addAttribute("mot", mot);
        return "clients";
    }









}