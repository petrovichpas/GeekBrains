package ru.geekbrains.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.entity.Role;
import ru.geekbrains.exeptions.NotFoundException;
import ru.geekbrains.repo.RoleRepository;
import ru.geekbrains.repo.UserRepository;

import javax.validation.Valid;

@Controller
@RequestMapping("/role")
public class RoleController {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    @Autowired
    public RoleController(RoleRepository roleRepository, UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    @GetMapping
    public String roleList(Model model) {
        model.addAttribute("roles", roleRepository.findAll());
        model.addAttribute("users", userRepository.findAll());
        return "roles";
    }

    @GetMapping("new")
    public String createRole(Model model) {
        model.addAttribute("role", new Role());
        model.addAttribute("users", userRepository.findAll());
        return "role";
    }

    @GetMapping("edit/{id}")
    public String updateRole(Model model, @PathVariable(value = "id") Long id) {
        model.addAttribute("role", roleRepository.findById(id).orElseThrow(NotFoundException::new));
        model.addAttribute("users", userRepository.findAll());
        return "role";
    }

    @PostMapping
    public String saveRole(@Valid Role role, BindingResult bindingResult) {
        roleRepository.save(role);
        return "redirect:/role";
    }

    @DeleteMapping
    public String deleteRole(@RequestParam("id") Long id) {
        roleRepository.deleteById(id);
        return "redirect:/role";
    }
}
