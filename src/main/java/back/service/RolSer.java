package back.service;

import java.util.List;
import java.util.Optional;



import back.models.Rol;


public interface RolSer {
    
    public void cargarRol(Rol rol);
    public Optional<Rol> buscarRol (long id);
    public List<Rol> rolesById (long id);

    
}
