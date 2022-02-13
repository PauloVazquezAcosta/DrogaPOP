package drogapop.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.*;


@Entity
@Table(name="contratos", catalog="DrogaPOP")
public class Contrato extends Entidade implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="id")
    int id;
    //un contrato pertenece solo a un empleado
    @ManyToOne(optional = false, cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "empregado")
    Empregado empregado;

    @Column(name="data_inicio")
    Date dataInicio;

    @Column(name="data_fin")
    Date dataFin;

    @Column(name="salario")
    double salario;

    @Column(name="tipo")
    int tipo;

    @Column(name="meses_duracion")
    Integer mesesDuracion;

    @Column(name="horas_xornada_semanal")
    int horasJornadaSemanal;

    public Contrato(int id, Empregado empregado, Date dataInicio, Date dataFin, double salario, int tipo, Integer mesesDuracion, int horasJornadaSemanal) {
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

    public Empregado getEmpregado() {
        return empregado;
    }

    public void setEmpregado(Empregado empregado) {
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

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public Integer getMesesDuracion() {
        return mesesDuracion;
    }

    public void setMesesDuracion(Integer mesesDuracion) {
        this.mesesDuracion = mesesDuracion;
    }

    public int getHorasJornadaSemanal() {
        return horasJornadaSemanal;
    }

    public void setHorasJornadaSemanal(int horasJornadaSemanal) {
        this.horasJornadaSemanal = horasJornadaSemanal;
    }

    @Override
    /** Pasa a una cadena de texto los valores relacionados con Contrato
     *
     * @version 1.0.0
     */
    public String toString() {
        return "Contrato{" +"\n"+
                " id=" + id +"\n"+
                " empregado=" + empregado.getNome() +"\n"+
                " dataInicio=" + dataInicio +"\n"+
                " dataFin=" + dataFin +"\n"+
                " salario=" + salario +"\n"+
                " tipo=" + tipo +"\n"+
                " mesesDuracion="+ (mesesDuracion!=null?mesesDuracion :"(indefinido)") +"\n"+
                " horasJornadaSemanal=" + horasJornadaSemanal +"\n"+
                '}';
    }
}