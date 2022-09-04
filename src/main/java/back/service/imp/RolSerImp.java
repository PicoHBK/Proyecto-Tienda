package back.service.imp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import back.models.Rol;
import back.models.Usuario;
import back.repository.RolRepo;
import back.service.RolSer;

@Service
public class RolSerImp implements RolSer{

    @Autowired
    private RolRepo rolRepo;

    @Override
    public void cargarRol(Rol rol) {
            rolRepo.save(rol);
    }

    @Override
    public Optional<Rol> buscarRol(long id) {
        return rolRepo.findById(id);
    }

    @Override
    public List<Rol> rolesById(long id) {
        return rolRepo.getRoles(id);
    }
    
}
