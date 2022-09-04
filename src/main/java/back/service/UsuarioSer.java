package back.service;

import back.models.Usuario;

public interface UsuarioSer {
    public Usuario buscarPorUser(String username);
    public void cargarUser(Usuario usuario);
}
