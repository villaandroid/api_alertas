package sistema_alertas.Alertas.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "seguimientos")
@Getter
@Setter
public class Seguimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SEGU_ID")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "CONS_ID", referencedColumnName = "CONS_ID")
    private Consulta consulta;

    @Column(name = "SEGU_FECHA")
    @Temporal(TemporalType.DATE)
    private Date fecha;

    @Column(name = "SEGU_SEGUIMIENTO")
    private String seguimiento;

    @Column(name = "SEGU_OBS")
    private String observaciones;

    @Column(name = "estado")
    private String estado;

    public Seguimiento() {}

    public Seguimiento(Consulta consulta, Date fecha, String seguimiento, String observaciones, String estado) {
        this.consulta = consulta;
        this.fecha = fecha;
        this.seguimiento = seguimiento;
        this.observaciones = observaciones;
        this.estado = estado;
    }
}
