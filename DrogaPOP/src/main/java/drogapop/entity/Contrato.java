package drogapop.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="contratos", catalog="DrogaPOP")
public class Contrato extends Entidade implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="id")
    int id;

    @Column(name="empregado")
    int empregado;

    @Column(name="data_inicio")
    Date dataInicio;

    @Column(name="data_fin")
    Date dataFin;

    @Column(name="salario")
    float salario;

    @Column(name="tipo")
    int tipo;

    @Column(name="meses_duracion")
    int mesesDuracion;

    @Column(name="horas_jornada_semanal")
    int horasJornadaSemanal;

    public Contrato(int id, int empregado, Date dataInicio, Date dataFin, float salario, int tipo, int mesesDuracion, int horasJornadaSemanal) {
        this.id = id;
        this.empregado = empregado;
        this.dataInicio = dataInicio;
        this.dataFin = dataFin;
        this.salario = salario;
        this.tipo = tipo;
        this.mesesDuracion = mesesDuracion;
        this.horasJornadaSemanal = horasJornadaSemanal;
    }

    public Contrato() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEmpregado() {
        return empregado;
    }

    public void setEmpregado(int empregado) {
        this.empregado = empregado;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFin() {
        return dataFin;
    }

    public void setDataFin(Date dataFin) {
        this.dataFin = dataFin;
    }

    public float getSalario() {
        return salario;
    }

    public void setSalario(float salario) {
        this.salario = salario;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public int getMesesDuracion() {
        return mesesDuracion;
    }

    public void setMesesDuracion(int mesesDuracion) {
        this.mesesDuracion = mesesDuracion;
    }

    public int getHorasJornadaSemanal() {
        return horasJornadaSemanal;
    }

    public void setHorasJornadaSemanal(int horasJornadaSemanal) {
        this.horasJornadaSemanal = horasJornadaSemanal;
    }
}