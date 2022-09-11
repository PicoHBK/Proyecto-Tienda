package back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import back.models.Rol;

public interface RolRepo extends JpaRepository<Rol, Long> {
    @Query( value = "SELECT * FROM proyecto1.rol WHERE proyecto1.rol.id_usuario = :id", nativeQuery = true)
    List<Rol> getRoles (@Param("id")long id);
}
