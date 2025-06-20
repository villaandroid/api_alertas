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

    @Column(name = "CONS_FECHA", insertable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;

    @Column(name = "CONS_MOTIVO")
    private String motivo;

    @Column(name = "CONS_DESCARGOS")
    private String descargos;

    @Column(name = "CONS_ALERTA")
    private String alerta;

    @Column(name = "ESTU_PRESENCIAL")
    private Boolean presenciaEstudiante = false;

    @Column(name = "ESTU_VALIDACION")
    @Enumerated(EnumType.STRING)
    private MetodoValidacion metodoValidacion = MetodoValidacion.NINGUNO;

    public Consulta() {
    }

    public Consulta(
            Estudiante estudiante, Docente docente, String motivo,
            String descargos, String alerta, Boolean presenciaEstudiante, MetodoValidacion metodoValidacion) {
        this.estudiante = estudiante;
        this.docente = docente;
        this.motivo = motivo;
        this.descargos = descargos;
        this.alerta = alerta;
        this.presenciaEstudiante = presenciaEstudiante;
        this.metodoValidacion = metodoValidacion;
    }
}
