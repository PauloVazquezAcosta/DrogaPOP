package drogapop.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.*;


@Entity
@Table(name="departamentos", catalog="DrogaPOP")
public class Departamento extends Entidade implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator="secuencia_id_departamento")
    @SequenceGenerator(name="secuencia_id_departamento", sequenceName="departamento_id_seq", allocationSize=1)
    @Column(name="id")
    int deptno;


    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id")
    private List<Empregado> empregados;

    public List<Empregado> getEmpregados() {
        return empregados;
    }

    public void setEmpregados(List<Empregado> empregados) {
        this.empregados = empregados;
    }

    @Column(name="nome")
    String nome;

    //un departamento solo tiene un jefe y un empregado es jefe de solo un departamento
    //que columna en la tabla Empregado tiene la FK
    @JoinColumn(name = "xefe")
    @OneToOne(fetch = FetchType.EAGER)
    private Empregado xefe;

    public Empregado getXefe() {
        return xefe;
    }
    public void setXefe(Empregado xefe) {
        this.xefe = xefe;
    }
    //un departamento esta solo en una ubicación y una sede puede tener muchos departamentos
    @ManyToOne()
    @JoinColumn(name = "ubicacion")
    Sede sede;

    public Sede getSede() {
        return sede;
    }

    public void setSede(Sede sede) {
        this.sede = sede;
    }

    @Column(name="telefono")
    String telefono;



    public Departamento(String nome, Empregado xefe, Sede ubicacion, String telefono) {
        this.nome = nome;
        this.xefe = xefe;
        this.sede = ubicacion;
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
//en caso de que el departamento no tenga asignado un jefe aun, al mostrarlo le damos un valor vacio para evitar el NullPointerExeption
    @Override
    public String toString() {
        return "[" +
                "deptno=" + deptno +
                ", nome='" + nome + '\'' +
                ", xefe=" + (xefe!=null?xefe.getNome():"(sin jefe asignado) ") +
                ", sede=" + sede.getUbicacion() +
                ", telefono='" + telefono + '\'' +
                ']';
    }
}