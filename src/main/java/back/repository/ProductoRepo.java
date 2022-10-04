package back.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import back.dto.ResListImgDTO;
import back.models.Producto;

public interface ProductoRepo extends JpaRepository<Producto, Long>{
    public Producto findByNombre(String nombre);
    public List<Producto> findByNombreIsContaining(String nombre);

    //PAGINACION

    Page<Producto> findAll(Pageable pageable);


    @Query("SELECT new back.dto.ResListImgDTO(p,p.image.data) FROM Producto p")
    List<ResListImgDTO> getListaImgProductosLaVerdad();

    @Query("SELECT new back.dto.ResListImgDTO(p,p.image.data) FROM Producto p")
    Page<ResListImgDTO> obetenerProductosPage(Pageable pageable);


    @Query("SELECT new back.dto.ResListImgDTO(p,p.image.data) FROM Producto p WHERE p.nombre LIKE %:name%")
    Page<ResListImgDTO> obetenerProductosBuscadoPage(@Param("name") String name,Pageable pageable);

    

    
}
