package back.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import back.dto.ResListImgDTO;
import back.models.Producto;

public interface ProductoSer {
    public Producto getProductoNombre(String nombre);

    public List<Producto> getProductoNombreLike(String nombreLike);

    public List<Producto> getProductos();

    public void cargarProducto(Producto producto);

    public void borrarProducto(long id);


    //PAGINACION
    public List<Producto> getProductosPage(int pageNumber);

    //TEST
    public List<ResListImgDTO> laverdad();

    public List<ResListImgDTO> laVerdadPageable(int pageNumber);


    //BUSQUEDA LIKE PAGINADO

    public List<ResListImgDTO> busquedaLikePaginado(String name, int page);

    //BUSQUEDA POR ID

    public ResListImgDTO obtenerDetails(long id );
    

}
