package drogapop.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;


@Entity
@Table(name="tipo_contrato", catalog="DrogaPOP")
public class TipoContrato extends Entidade implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="id")
    int id;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="id")
    Set<Contrato> contratos;

    @Column(name="nome")
    String nome;

    public TipoContrato(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public TipoContrato() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}