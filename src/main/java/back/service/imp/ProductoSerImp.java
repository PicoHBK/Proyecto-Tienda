package back.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import back.dto.ResListImgDTO;
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

    // PARA TEST IGNORAR
    public ProductoSerImp (ProductoRepo repo){
        this.productoRepo = repo;
    }


    //PAGINACION
    @Override
    public List<Producto> getProductosPage(int pageNumber) {
        Pageable page = PageRequest.of(pageNumber, 1);
        return productoRepo.findAll(page).getContent();
    }

    @Override
    public List<ResListImgDTO> laverdad() {
        return productoRepo.getListaImgProductosLaVerdad();

    }

    @Override
    public List<ResListImgDTO> laVerdadPageable(int pageNumber) {
        Pageable page = PageRequest.of(pageNumber, 6);
        return productoRepo.obetenerProductosPage(page).getContent();
    }

    @Override
    public List<ResListImgDTO> busquedaLikePaginado(String name, int pageNumber) {
        Pageable page = PageRequest.of(pageNumber, 5);
        return productoRepo.obetenerProductosBuscadoPage(name, page).getContent();
    }

    @Override
    public ResListImgDTO obtenerDetails(long id) {
        return productoRepo.obtenerPorId(id);
    }


}
