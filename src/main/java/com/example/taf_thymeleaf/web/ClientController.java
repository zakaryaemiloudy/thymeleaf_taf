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

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ClientController {

    private final ClientRepo clientRepo;


    @GetMapping({"/user/index", "/", "/user/clients"})
    public String ListClients(Model model) {
        List<Client> clients = (List<Client>) clientRepo.findAll();
        model.addAttribute("clients", clients);
        return "clients";
    }

    @GetMapping("/admin/delete")
    public String DeleteClient(@RequestParam Long id) {
        clientRepo.deleteById(id);
        return "redirect:/user/index";
    }

    @GetMapping("/admin/addForm")
    public String AddClientForm(Model model) {
        model.addAttribute("client", new Client());
        return "/add";

    }

    @PostMapping("/admin/add")
    public String AddClient(@Valid Client client, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("client", client);
            return "/add";
        }
        clientRepo.save(client);
        return "redirect:/user/index";
    }


    @GetMapping("/admin/updateForm")
    public String UpdateClientForm(@RequestParam Long id, Model model) {
        Client client = clientRepo.findById(id).get();
        model.addAttribute("client", client);
        return "update";
    }

    @PostMapping("/admin/update")
    public String UpdateClient(@Valid Client client, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("client", client);
            return "update";
        }
        clientRepo.save(client);
        return "redirect:/user/index";
    }


    @GetMapping("/user/search")
    public String SearchClient(Model model, @RequestParam String mot) {
        List<Client> clients = clientRepo.findClientsByNameContains(mot);
        model.addAttribute("clients", clients);
        model.addAttribute("mot", mot);
        return "clients";
    }








}