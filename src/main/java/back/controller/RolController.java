package back.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import back.models.Rol;
import back.service.RolSer;

@Controller
public class RolController {
    
    @Autowired
    private RolSer rolSer;

    @GetMapping("/buscarRol/{id}")
    public ResponseEntity<Rol> buscarUsuario(@PathVariable long id) {
        Rol rol = rolSer.buscarRol(id).get();
        return ResponseEntity.ok(rol);
    }

    @GetMapping("/roles/{id}")
    public ResponseEntity<List<Rol>> usuarioRoles(@PathVariable long id) {
        List<Rol> roles= rolSer.rolesById(id);
        return ResponseEntity.ok(roles);
    }

}
