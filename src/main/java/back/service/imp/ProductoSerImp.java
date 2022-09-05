package back.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import back.models.Producto;
import back.repository.ProductoRepo;
import back.service.ProductoSer;
@Service
public class ProductoSerImp implements ProductoSer{

    @Autowired
    private ProductoRepo productoRepo;
    
    
    

    @Override
    public void borrarProducto(long id) {
        productoRepo.deleteById(id);
    }

    @Override
    public void cargarProducto(Producto producto) {
        productoRepo.save(producto);
    }

    @Override
    public Producto getProductoNombre(String nombre) {
        return productoRepo.findByNombre(nombre);
    }

    @Override
    public List<Producto> getProductos() {
        return productoRepo.findAll();
    }

    @Override
    public List<Producto> getProductoNombreLike(String nombreLike) {
        return productoRepo.findByNombreIsContaining(nombreLike);
    }
    
}
