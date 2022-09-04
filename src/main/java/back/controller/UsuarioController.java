package back.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import back.models.Rol;
import back.models.Usuario;
import back.service.RolSer;
import back.service.UsuarioSer;

@Controller
public class UsuarioController {

    @Autowired
    private UsuarioSer usuarioSer;
    
    @Autowired
    private RolSer rolSer;

    @PostMapping("/cargarUsuario")
    public ResponseEntity<?> cargarUser (@RequestBody Usuario usuario) {
        Rol rol1 = new Rol();
        rol1.setRol("ADMIN");
        rol1.setUsuario(usuario);

        Rol rol2 = new Rol();
        rol2.setRol("USER");
        rol2.setUsuario(usuario);

        usuarioSer.cargarUser(usuario);
        rolSer.cargarRol(rol1);
        rolSer.cargarRol(rol2);
        return ResponseEntity.ok("Cargado");
    }
    
    
    @GetMapping("/buscar")
    public ResponseEntity<Usuario> buscarUsuario(@RequestBody String username) {
        Usuario usuario = usuarioSer.buscarPorUser(username);
        return ResponseEntity.ok(usuario);
    }

    
    
}
