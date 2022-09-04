package back.security.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import back.models.Rol;
import back.models.Usuario;
import back.service.RolSer;
import back.service.UsuarioSer;

import org.springframework.security.core.userdetails.User;

//
//

@Service
public class UsuarioDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioSer usuarioSer;

    @Autowired
    private RolSer rolSer;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        Usuario usuario = usuarioSer.buscarPorUser(username);

        List<Rol> roles = rolSer.rolesById(usuario.getId());
        String rol = roles.get(0).getRol();
        String rol2 = roles.get(1).getRol();


        
        if (rol != null){
            User.UserBuilder userBuilder = User.withUsername(username);

            String encryptedPassword = usuario.getPassword();
            userBuilder.password(encryptedPassword).roles(rol,rol2);
            
            return userBuilder.build();

        }else{
            throw new UsernameNotFoundException(username);
        }

    }
    
}