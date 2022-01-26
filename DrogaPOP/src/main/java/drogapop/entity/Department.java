package drogapop.entity;



import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="departamentos", catalog="DrogaPOP")
public class Department implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="id")
    private int deptno;

    @Column(name="nome")
    private String nome;

    @Column(name="xefe")
    private int xefe;

    @Column(name="ubicacion")
    private String ubicacion;


    public Department(int deptno, String nome, int xefe, String ubicacion) {
        this.deptno = deptno;
        this.nome = nome;
        this.xefe = xefe;
        this.ubicacion = ubicacion;
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

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
}