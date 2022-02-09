package drogapop.entity;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Date;

import javax.persistence.*;


@Entity
@Table(name="empregados", catalog="DrogaPOP")
public class Empregado extends Entidade implements Serializable  {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator="secuencia_id")
    @SequenceGenerator(name="secuencia_id", sequenceName="empregado_id_seq", allocationSize=1)
    @Column(name="id")
    int id;

    //Un empledo pertenece a un departamento
    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "deptno")
    Departamento departamento;

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    @Column(name="dni")
    String DNI;

    @Column(name="nome")
    String nome;

    @Column(name="apelidos")
    String apelidos;


    @Column(name="cargo")
    String cargo;

    @Column(name="telefono")
    String numeroTelefono;

    @Column(name="data_nacemento")
    Date dataNacemento;

    @Column(name="email")
    String email;

    @Column(name="numero_seg_social")
    String numeroSeguridadeSocial;

    public Empregado( String DNI, String nome, String apelidos, Departamento numeroDeDepartamento, String cargo, String numeroTelefono, Date dataNacemento, String email, String numeroSeguridadeSocial) {

        this.DNI = DNI;
        this.nome = nome;
        this.apelidos = apelidos;
        this.departamento= numeroDeDepartamento;
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

    @Override
    public String toString() {
        return "[" +
                "id=" + id +
                ", departamento=" + departamento.getNome() +
                ", DNI='" + DNI + '\'' +
                ", nome='" + nome + '\'' +
                ", apelidos='" + apelidos + '\'' +
                ", cargo='" + cargo + '\'' +
                ", numeroTelefono='" + numeroTelefono + '\'' +
                ", dataNacemento=" + dataNacemento +
                ", email='" + email + '\'' +
                ", numeroSeguridadeSocial='" + numeroSeguridadeSocial + '\'' +
                ']';
    }
}