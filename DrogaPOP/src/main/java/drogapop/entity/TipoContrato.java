package drogapop.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="tipo_contrato", catalog="DrogaPOP")
public class TipoContrato extends Entidade implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="id")
    int id;

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