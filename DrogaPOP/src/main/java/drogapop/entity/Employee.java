package drogapop.entity;



import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="empregados", catalog="DrogaPOP")
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="id")
    private int empno;

    @Column(name="dni")
    private String dni;

    @Column(name="nome")
    private String nome;

    @Column(name="apelidos")
    private String apelidos;

    @Column(name="posto_traballo")
    private int postoTraballo;

    @Column(name="contrato_dende")
    private String contratoDende;

    @Column(name="salario")
    private float salario;

    @Column(name="deptno")
    private int deptno;


    public Employee(int empno, String dni, String nome, String apelidos, int postoTraballo, String contratoDende, float salario, int deptno) {
        this.empno = empno;
        this.dni = dni;
        this.nome = nome;
        this.apelidos = apelidos;
        this.postoTraballo = postoTraballo;
        this.contratoDende = contratoDende;
        this.salario = salario;
        this.deptno = deptno;
    }


    public int getEmpno() {
        return empno;
    }

    public void setEmpno(int empno) {
        this.empno = empno;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getApelidos() {
        return apelidos;
    }

    public void setApelidos(String apelidos) {
        this.apelidos = apelidos;
    }

    public int getPostoTraballo() {
        return postoTraballo;
    }

    public void setPostoTraballo(int postoTraballo) {
        this.postoTraballo = postoTraballo;
    }

    public String getContratoDende() {
        return contratoDende;
    }

    public void setContratoDende(String contratoDende) {
        this.contratoDende = contratoDende;
    }

    public float getSalario() {
        return salario;
    }

    public void setSalario(float salario) {
        this.salario = salario;
    }

    public int getDeptno() {
        return deptno;
    }

    public void setDeptno(int deptno) {
        this.deptno = deptno;
    }
}