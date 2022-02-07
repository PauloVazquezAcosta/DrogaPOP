package drogapop.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="departamentos", catalog="DrogaPOP")
public class Departamento extends Entidade implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="id")
    int deptno;

    @Column(name="nome")
    String nome;

    @Column(name="xefe")
    int xefe;

    @Column(name="ubicacion")
    int ubicacion;

    @Column(name="telefono")
    String telefono;

    public Departamento(int deptno, String nome, int xefe, int ubicacion, String telefono) {
        this.deptno = deptno;
        this.nome = nome;
        this.xefe = xefe;
        this.ubicacion = ubicacion;
        this.telefono = telefono;
    }

    public Departamento() {

    }

    public int getDeptno() {
        return deptno;
    }

    public void setDeptno(int deptno) {
        this.deptno = deptno;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getXefe() {
        return xefe;
    }

    public void setXefe(int xefe) {
        this.xefe = xefe;
    }

    public int getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(int ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}