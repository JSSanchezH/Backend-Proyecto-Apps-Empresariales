package com.proyect.Human_Resources.controllers;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyect.Human_Resources.models.Role;
import com.proyect.Human_Resources.services.RoleService;

@RestController
@RequestMapping("/roles")
public class RoleController {
    
    @Autowired
    private RoleService roleService;

    @GetMapping
    public ArrayList<Role> getRoles() {
        return roleService.getRoles();
    }    

    @PostMapping
    public Role saveRole(@RequestBody Role role) {
        return roleService.saveRole(role);
    }

    @GetMapping(path = "/{id}")
    public Optional<Role> getRoleById(@PathVariable("id") long id) {
        return roleService.getRoleById(id);
    }

    @PutMapping(path = "/{id}")
    public Role updateRole(@RequestBody Role role, @PathVariable("id") long id) {
        return roleService.updateRole(role, id);
    }

    @DeleteMapping(path = "/{id}")
    public String deleteRole(@PathVariable("id") long id) {
        boolean ok = roleService.deleteRole(id);
        if (ok) {
            return "Role deleted successfully";
        } else {
            return "Error deleting role";
        }
    }
}
