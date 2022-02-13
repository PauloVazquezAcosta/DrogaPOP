package drogapop.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;


@Entity
@Table(name="sedes", catalog="DrogaPOP")
public class Sede extends Entidade implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name="id")
    int id;

    @Column(name="ubicacion")
    String ubicacion;

    @Column(name="telefono")
    String telefono;

    public Sede(int id, String ubicacion, String telefono) {
        this.id = id;
        this.ubicacion = ubicacion;
        this.telefono = telefono;
    }

    public Sede() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    /** Pasa a una cadena de texto los valores relacionados con Sede
     *
     * @version 1.0.0
     */
    public String toString() {
        return "Sede{" +
                "id=" + id +
                ", ubicacion='" + ubicacion + '\'' +
                ", telefono='" + telefono + '\'' +
                '}';
    }
}