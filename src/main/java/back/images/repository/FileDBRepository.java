package back.images.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import back.images.model.FileDB;



@Repository
public interface FileDBRepository extends JpaRepository<FileDB,String>{
     @Query( value = "delete from proyecto1.files where proyecto1.files.id_producto =:id", nativeQuery = true)
     @Modifying
      @Transactional
    void borrarByProducto (@Param("id")long id);

    @Query( value = "select * from proyecto1.files where proyecto1.files.id_producto =:id", nativeQuery = true)
    FileDB obtenerImgPorProducto (@Param("id")long id);
    
    
    
}
