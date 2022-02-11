package drogapop.entity.clasesAuxiliares;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="empregados_non_xefes", catalog="DrogaPOP")
public class EmpregadoNonXefe {

    private static final long serialVersionUID = 1L;
    @Column(name="dni")
    private String dni;
    @Column(name="nome")
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
}
