package back.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "producto")
@Data
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
}
