package sistema_alertas.Alertas.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Entity
@Table(name = "consultas")
@Getter
@Setter
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CONS_ID")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "ESTU_ID", referencedColumnName = "ESTU_ID")
    private Estudiante estudiante;

    @ManyToOne
    @JoinColumn(name = "DOCE_ID", referencedColumnName = "DOCE_ID")
    private Docente docente;

    @Column(name = "CONS_FECHA")
    @Temporal(TemporalType.DATE)
    private Date fecha;

    @Column(name = "CONS_MOTIVO")
    private String motivo;

    @Column(name = "CONS_DESCARGOS")
    private String descargos;

    @Column(name = "CONS_ALERTA")
    private String alerta;


    public Consulta() {}

    public Consulta(Estudiante estudiante, Docente docente, Date fecha, String motivo, String descargos, String alerta) {
        this.estudiante = estudiante;
        this.docente = docente;
        this.fecha = fecha;
        this.motivo = motivo;
        this.descargos = descargos;
        this.alerta = alerta;
    }
   
}
