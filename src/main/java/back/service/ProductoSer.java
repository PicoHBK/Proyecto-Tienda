package back.service;

import java.util.List;

import back.models.Producto;

public interface ProductoSer {
    public Producto getProductoNombre(String nombre);

    public List<Producto> getProductoNombreLike(String nombreLike);

    public List<Producto> getProductos();

    public void cargarProducto(Producto producto);

    public void borrarProducto(long id);

    

}
