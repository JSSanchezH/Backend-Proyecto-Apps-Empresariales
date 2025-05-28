package com.proyect.Human_Resources.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyect.Human_Resources.Repositories.IRoleReposiotry;
import com.proyect.Human_Resources.models.Role;

@Service
public class RoleService {

    @Autowired
    private IRoleReposiotry roleRepository; 

    public ArrayList<Role> getRoles(){
        return (ArrayList<Role>) roleRepository.findAll(); 
    }

    public Role saveRole(Role role){
        return roleRepository.save(role); 
    }

    public ArrayList<Role> saveRoles(ArrayList<Role> roles){
        return (ArrayList<Role>) roleRepository.saveAll(roles); 
    }

    public Optional<Role> getRoleById(long id){
        return roleRepository.findById(id); 
    }

    public Role updateRole(Role role, long id){
        Role roleToUpdRole = roleRepository.findById(id).get();
        roleToUpdRole.setName(role.getName());
        return roleRepository.save(roleToUpdRole);
    }

    public boolean deleteRole(long id){
        try {
            roleRepository.deleteById(id);
            return true; 
        } catch (Exception e) {
            return false; 
        }
    }
}
