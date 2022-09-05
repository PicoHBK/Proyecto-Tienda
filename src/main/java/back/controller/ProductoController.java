package back.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import back.models.Producto;
import back.service.ProductoSer;

@Controller()
@RequestMapping("/producto")
public class ProductoController {
    
    @Autowired
    private ProductoSer productoSer;

    @PostMapping("/cargar")
    public ResponseEntity<?> cargar(@RequestBody Producto producto) {
        productoSer.cargarProducto(producto);
        return ResponseEntity.ok("PRODUCTO CARGADO");
    }

    
    @GetMapping("/lista")
    public ResponseEntity<List<Producto>> lista() {
        return ResponseEntity.ok(productoSer.getProductos());
    }


      @GetMapping("/buscar/{nombre}")
    public ResponseEntity<List<Producto>> producto(@PathVariable String nombre) {
        //return ResponseEntity.ok(productoSer.getProductoNombre(nombre));
        return ResponseEntity.ok(productoSer.getProductoNombreLike(nombre));
    }

    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<?> borrar(@PathVariable long id) {
        productoSer.borrarProducto(id);
        return ResponseEntity.ok("PRODUCTO BORRADO");
    }
    
    
    
    
    
    
}
