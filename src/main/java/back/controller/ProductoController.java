package back.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import back.dto.ResImgIdDTO;
import back.dto.ResListImgDTO;
import back.dto.ResponseDTO;
import back.images.message.ResponseMessage;
import back.images.model.FileDB;
import back.images.repository.FileDBRepository;
import back.images.service.FileStorageService;
import back.models.Producto;
import back.repository.ProductoRepo;
import back.service.ProductoSer;




@RestController
@RequestMapping("/producto")
public class ProductoController {

    @Autowired
    private FileStorageService storageService;
    
    @Autowired
    private ProductoSer productoSer;

    //test
    @Autowired
    private FileDBRepository dbRepository;

   
    
    

    
    //CARGAR CON IMAGEN
  @PostMapping("/cargarcon")
  public ResponseEntity<?> cargarCon(@RequestPart("data") Producto producto , @RequestParam("file") MultipartFile file) {
    try{
      productoSer.cargarProducto(producto);
      storageService.store(file, producto);
      return ResponseEntity.ok("PRODUCTO CARGADO");
    }catch (Exception e) {
        return ResponseEntity.ok(e.getMessage());
    }
  }

    //CARGAR CON IMAGEN
    @PostMapping("/update")
    public ResponseEntity<?> update(@RequestPart("data") Producto producto , @RequestParam("file") MultipartFile file) {
      try{
        productoSer.cargarProducto(producto);
        System.out.println(("BORRANDO"));
        storageService.deleteByIdProducto(producto.getId());
        storageService.store(file, producto);
        return ResponseEntity.ok("PRODUCTO ACTUALIZADO SOLO DATA");
      }catch (Exception e) {
          return ResponseEntity.ok(e.getMessage());
      }
    }
 
  @GetMapping("/data/{id}")
  ResponseEntity<ResImgIdDTO> info2(@PathVariable long id){
    return ResponseEntity.ok(dbRepository.getIdImg(id));
  }


  @GetMapping("/listdata")
  ResponseEntity<List<ResListImgDTO>> info3(){
    return ResponseEntity.ok(productoSer.laverdad());
  }
  
  
  
  @GetMapping("/page/{pageNumber}")
    public ResponseEntity<List<ResListImgDTO>> page(@PathVariable int pageNumber) {
        return ResponseEntity.ok(productoSer.laVerdadPageable(pageNumber));
    }
  
  
    
    

    @PostMapping("/cargar")
    public ResponseEntity<?> cargar(@RequestBody Producto producto) {
        productoSer.cargarProducto(producto);
        return ResponseEntity.ok("PRODUCTO CARGADO");
    }
    
    @GetMapping("/lista")
    public ResponseEntity<List<Producto>> lista() {
        return ResponseEntity.ok(productoSer.getProductos());
    }

    /* @GetMapping("/page/{pageNumber}")
    public ResponseEntity<List<Producto>> page(@PathVariable int pageNumber) {
        return ResponseEntity.ok(productoSer.getProductosPage(pageNumber));
    } */


    //BUSQUEDA POR NOMBRE MAS IMG PAGINADO
    @GetMapping("/search/{page}/{search}")
    public ResponseEntity<List<ResListImgDTO>> busquedaPaginada(@PathVariable int page,@PathVariable String search) {
        return ResponseEntity.ok(productoSer.busquedaLikePaginado(search,page));
    }



    //BUSCA POR NOMBRE EL PRODUCTO
      @GetMapping("/buscar/{nombre}")
    public ResponseEntity<List<Producto>> producto(@PathVariable String nombre) {
        //return ResponseEntity.ok(productoSer.getProductoNombre(nombre));
        return ResponseEntity.ok(productoSer.getProductoNombreLike(nombre));
    }
    // BORRAR EL PRODUCTO Y SU RELACION EN FILES
    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<?> borrar(@PathVariable long id) {
        storageService.deleteByIdProducto(id);
        productoSer.borrarProducto(id);
        return ResponseEntity.ok("PRODUCTO BORRADO");
    }
    

    @GetMapping("/download/image/{id}")
  public ResponseEntity<byte[]> getFile(@PathVariable String id) {
    FileDB fileDB = storageService.getFile(id);

    return ResponseEntity.ok()
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDB.getName() + "\"")
        .body(fileDB.getData());
  }
    
  @GetMapping("/image/{id}")
  public ResponseEntity<FileDB> getImage(@PathVariable long id){
    return ResponseEntity.ok(storageService.getImgProducto(id));
  }


  //OBTENER POR ID
    @GetMapping("details/id/{id}")
    public ResponseEntity<ResListImgDTO> details (@PathVariable long id) {
        return  ResponseEntity.ok(productoSer.obtenerDetails(id));
    }
    
    
    
    
    
}
