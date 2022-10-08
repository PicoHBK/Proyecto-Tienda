package back.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import back.models.Rol;
import back.models.Usuario;
import back.security.model.AuthenticationReq;
import back.security.util.JwtUtilService;
import back.security.util.TokenInfo;
import back.service.RolSer;
import back.service.UsuarioSer;

@Controller
public class UsuarioController {

    @Autowired
    private UsuarioSer usuarioSer;
    
    @Autowired
    private RolSer rolSer;



    @Autowired
    private AuthenticationManager authenticationManager;
  
    @Autowired
    UserDetailsService usuarioDetailsService;
  
    @Autowired
    private JwtUtilService jwtUtilService;

    private static final Logger logger = LoggerFactory.getLogger(UsuarioController.class);

    @PostMapping("/cargarUsuario")
    public ResponseEntity<?> cargarUser (@RequestBody Usuario usuario) {
       /* Rol rol1 = new Rol();
        rol1.setRol("ADMIN");
        rol1.setUsuario(usuario);*/

        Rol rol2 = new Rol();
        rol2.setRol("USER");
        rol2.setUsuario(usuario);

        usuarioSer.cargarUser(usuario);
        //rolSer.cargarRol(rol1);
        rolSer.cargarRol(rol2);
        return ResponseEntity.ok("Cargado");
    }
    
    
    @GetMapping("/buscar")
    public ResponseEntity<Usuario> buscarUsuario(@RequestBody String username) {
        Usuario usuario = usuarioSer.buscarPorUser(username);
        return ResponseEntity.ok(usuario);
    }


    
    @PostMapping("/authenticate")
  public ResponseEntity<TokenInfo> authenticate(@RequestBody AuthenticationReq authenticationReq) {


    // Autenticar al usuario usando el AuthenticationManager mandando el usuario y la contraseña
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(authenticationReq.getUsuario(),
            authenticationReq.getClave()));

    // retornar el un user invocando al UserDetailsService loadUserByUsername
    final UserDetails userDetails = usuarioDetailsService.loadUserByUsername(
        authenticationReq.getUsuario());

    // genera el token con mandano el user 
    final String jwt = jwtUtilService.generateToken(userDetails);

    // almacena el token en un obejto
    
    Usuario user = usuarioSer.buscarPorUser(authenticationReq.getUsuario());
    TokenInfo tokenInfo = new TokenInfo(jwt,user.getId());

    return ResponseEntity.ok(tokenInfo);
  }

  @GetMapping("/test")
  public ResponseEntity<?> fido(){
    var auth =  SecurityContextHolder.getContext().getAuthentication();
    logger.info("Datos del Usuario: {}", auth.getPrincipal());
    logger.info("Datos de los Permisos {}", auth.getAuthorities());
    logger.info("Esta autenticado {}", auth.isAuthenticated());

    return ResponseEntity.ok(auth.getAuthorities());
  }

  @GetMapping("/test2")
  public ResponseEntity<?> fido2(){
    var auth =  SecurityContextHolder.getContext().getAuthentication();
    logger.info("Datos del Usuario: {}", auth.getPrincipal());
    logger.info("Datos de los Permisos {}", auth.getAuthorities());
    logger.info("Esta autenticado {}", auth.isAuthenticated());

    return ResponseEntity.ok(auth.getAuthorities());
  }

  @GetMapping("/test3")
  public ResponseEntity<?> fido3(){
    var auth =  SecurityContextHolder.getContext().getAuthentication();
    logger.info("Datos del Usuario: {}", auth.getPrincipal());
    logger.info("Datos de los Permisos {}", auth.getAuthorities());
    logger.info("Esta autenticado {}", auth.isAuthenticated());

    return ResponseEntity.ok("test3");
  }
    
}
