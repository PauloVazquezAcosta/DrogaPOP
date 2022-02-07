package drogapop.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="empregados", catalog="DrogaPOP")
public class Empregado implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="id")
    private int id;

    @Column(name="dni")
    private String DNI;

    @Column(name="nome")
    private String nome;

    @Column(name="apelidos")
    private String apelidos;

    @Column(name="deptno")
    private int numeroDeDepartamento;

    @Column(name="cargo")
    private String cargo;

    @Column(name="telefono")
    private String numeroTelefono;

    @Column(name="data_nacemento")
    private Date dataNacemento;

    @Column(name="email")
    private String email;

    @Column(name="numero_seg_social")
    private String numeroSeguridadeSocial;

    public Empregado(int id, String DNI, String nome, String apelidos, int numeroDeDepartamento, String cargo, String numeroTelefono, Date dataNacemento, String email, String numeroSeguridadeSocial) {
        this.id = id;
        this.DNI = DNI;
        this.nome = nome;
        this.apelidos = apelidos;
        this.numeroDeDepartamento = numeroDeDepartamento;
        this.cargo = cargo;
        this.numeroTelefono = numeroTelefono;
        this.dataNacemento = dataNacemento;
        this.email = email;
        this.numeroSeguridadeSocial = numeroSeguridadeSocial;
    }

    public Empregado() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
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

    public int getNumeroDeDepartamento() {
        return numeroDeDepartamento;
    }

    public void setNumeroDeDepartamento(int numeroDeDepartamento) {
        this.numeroDeDepartamento = numeroDeDepartamento;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getNumeroTelefono() {
        return numeroTelefono;
    }

    public void setNumeroTelefono(String numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
    }

    public Date getDataNacemento() {
        return dataNacemento;
    }

    public void setDataNacemento(Date dataNacemento) {
        this.dataNacemento = dataNacemento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumeroSeguridadeSocial() {
        return numeroSeguridadeSocial;
    }

    public void setNumeroSeguridadeSocial(String numeroSeguridadeSocial) {
        this.numeroSeguridadeSocial = numeroSeguridadeSocial;
    }
}