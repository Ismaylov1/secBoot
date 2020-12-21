package net.controller;

import net.service.RoleServiceImpl;
import net.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import net.model.Role;
import net.model.User;

import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RequestMapping(value = "/")
@Controller
public class AdminController {
    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private RoleServiceImpl roleService;

    @GetMapping(value = "/admin")
    public String allUsers(Model model) {
        model.addAttribute("allUsers", userService.listUsers());
        List<User> users = userService.listUsers();
        return "admin";
    }

    @GetMapping("/admin/add")
    public String getUserForm(Model model) {
        model.addAttribute("listRole", roleService.listRoles());
        return "userAdd";
    }

    @PostMapping("/admin/add")
    public String addUser(@ModelAttribute("addUser") User user,
                          @RequestParam(value = "newRole", required = false) String[] role) {
        user.setRoles(addNewRole(role));
        userService.add(user);
        return "redirect:/admin";
    }

    @GetMapping("/admin/edit/{id}")
    public String editUser(@PathVariable("id") long id, Model model) {
        model.addAttribute("listRole", roleService.listRoles());
        model.addAttribute("editUser", userService.getUserById(id));
        return "editUsers";
    }

    @PostMapping("/admin/edit/{id}")
    public String updateUser(@ModelAttribute("editUser") User user,
                             @RequestParam(value = "newRole", required = false) String[] role) {
        user.setRoles(addNewRole(role));
        userService.updateUsers(user);
        return "redirect:/admin";
    }

    @GetMapping("/admin/delete/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userService.remove(id);
        return "redirect:/admin";
    }


    //USER logic

    @GetMapping(value = "/user")
    public String clickMe(Model model, Principal principal) {
        model.addAttribute("oneUser", userService.getUserByName(principal.getName()));
        return "user";
    }

    private Set<Role> addNewRole(String[] role) {
        Set<Role> roleSet = new HashSet<>();
        for (int i = 0; i < role.length; i++) {
            roleSet.add(roleService.getRoleByName(role[i]));
        }
        return roleSet;
    }
}
