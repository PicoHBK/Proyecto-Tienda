package back.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import back.images.model.FileDB;
import lombok.Data;

@Entity
@Table(name = "producto")
//@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "nombre", length = 100, nullable = false)
    private String nombre;
    @Column(name = "descripcion", length = 1000, nullable = true)
    private String descripcion;
    @Column(name = "precio", nullable = true)
    private int precio; 
    @OneToOne(mappedBy = "producto")
    @JsonIgnore
    private FileDB image;
    @Column(name = "activo", columnDefinition =  "boolean default false")
    private boolean activo;

    
    
    
    
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public int getPrecio() {
        return precio;
    }
    public void setPrecio(int precio) {
        this.precio = precio;
    }
    //@JsonManagedReference
    public FileDB getImage() {
        return image;
    }
    public void setImage(FileDB image) {
        this.image = image;
    }
}

