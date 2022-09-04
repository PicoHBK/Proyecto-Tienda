package back.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import back.models.Usuario;

public interface UsuarioRepo extends JpaRepository<Usuario, Long>{
    public Usuario findByUsername(String username);
}
