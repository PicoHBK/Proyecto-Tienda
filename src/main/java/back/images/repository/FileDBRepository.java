package back.images.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import back.dto.ResImgIdDTO;
import back.dto.ResListImgDTO;
import back.dto.ResponseDTO;
import back.images.model.FileDB;



@Repository
public interface FileDBRepository extends JpaRepository<FileDB,String>{
     @Query( value = "delete from proyecto1.files where proyecto1.files.id_producto =:id", nativeQuery = true)
     @Modifying
      @Transactional
    void borrarByProducto (@Param("id")long id);

    @Query( value = "select * from proyecto1.files where proyecto1.files.id_producto =:id", nativeQuery = true)
    FileDB obtenerImgPorProducto (@Param("id")long id);
    
    @Query("SELECT new back.dto.ResponseDTO(f.type , p.nombre) FROM FileDB f JOIN f.producto p")
    List<ResponseDTO> info();

    //TESTEANDO
    @Query("SELECT new back.dto.ResImgIdDTO(f.id, f.type , f.name) FROM FileDB f WHERE f.producto.id =:id")
    ResImgIdDTO getIdImg(@Param("id")long id);

    @Query("SELECT new back.dto.ResListImgDTO(f.producto,f.data ) FROM FileDB f ")
    List<ResListImgDTO> getListaImg();



    
    
}

