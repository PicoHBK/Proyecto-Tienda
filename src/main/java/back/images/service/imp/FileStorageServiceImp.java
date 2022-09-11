package back.images.service.imp;

import java.io.IOException;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import back.images.model.FileDB;
import back.images.repository.FileDBRepository;
import back.images.service.FileStorageService;
import back.models.Producto;

import java.io.IOException;

@Service
public class FileStorageServiceImp implements FileStorageService {


    @Autowired
  private FileDBRepository fileDBRepository;



    @Override
    public FileDB store(MultipartFile file, Producto producto) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        FileDB FileDB = new FileDB(fileName, file.getContentType(), file.getBytes());
        FileDB.setProducto(producto);
        return fileDBRepository.save(FileDB);
      }

    @Override
    public FileDB getFile(String id) {
        return fileDBRepository.findById(id).get();
    }

    @Override
    public Stream<FileDB> getAllFiles() {
        return fileDBRepository.findAll().stream();
    }

    @Override
    public boolean deleteById(String id) {
        try{
            fileDBRepository.deleteById(id);
            return true;
        }catch(Exception e){
            return false;
        }
       
    }

    @Override
    public void deleteByIdProducto(long id) {
        fileDBRepository.borrarByProducto(id);
    }

    @Override
    public FileDB getImgProducto(long id) {
        return fileDBRepository.obtenerImgPorProducto(id);
    }

}

