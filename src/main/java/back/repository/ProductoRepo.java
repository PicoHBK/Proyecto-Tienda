package back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import back.models.Producto;

public interface ProductoRepo extends JpaRepository<Producto, Long>{
    public Producto findByNombre(String nombre);
    public List<Producto> findByNombreIsContaining(String nombre);
}
