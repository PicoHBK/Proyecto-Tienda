package back.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import back.models.Usuario;
import back.repository.UsuarioRepo;
import back.service.UsuarioSer;


@Service
public class UsuarioSerImp implements UsuarioSer {
    @Autowired
    private UsuarioRepo usuarioRepo;

    @Lazy
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Usuario buscarPorUser(String username) {

        return usuarioRepo.findByUsername(username);

    }

    @Override
    public void cargarUser(Usuario usuario) {
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        usuarioRepo.save(usuario);
    }
    
    

}
