package drogapop.entity.clasesAuxiliares;

import drogapop.entity.Entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

//@Entity
//@Table(name="empregados_non_jefes", catalog="DrogaPOP")
public class EmpregadoNonXefe extends Entidade implements Serializable {

    private static final long serialVersionUID = 1L;

   // @Column(name = "dni", updatable = false, nullable = false)
    private String dni;

   // @Column(name="nome", updatable = false, nullable = false)
    private String nombre;

    public EmpregadoNonXefe(String dni, String nombre) {
        this.dni = dni;
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "EmpregadoNonXefe{" +
                "dni='" + dni + '\'' +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
