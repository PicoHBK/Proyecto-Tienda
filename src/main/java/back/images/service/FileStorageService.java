package back.images.service;

import java.io.IOException;
import java.util.stream.Stream;

import org.springframework.web.multipart.MultipartFile;

import back.images.model.FileDB;
import back.models.Producto;




public interface FileStorageService {
    
    public FileDB store(MultipartFile file, Producto producto) throws IOException;

    public FileDB getFile(String id);

    public Stream<FileDB> getAllFiles();

    public boolean deleteById(String id);

    public void deleteByIdProducto(long id);

    public FileDB getImgProducto(long id);
    
}
