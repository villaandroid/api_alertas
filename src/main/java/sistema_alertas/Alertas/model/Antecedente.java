package sistema_alertas.Alertas.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "antecedentes")
@Getter
@Setter
public class Antecedente {

    @Id
    @Column(name = "ESTU_ID")
    private Integer id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "ESTU_ID")
    private Estudiante estudiante;

    @Column(name = "ANTE_SALUD")
    private String salud;

    @Column(name = "ANTE_FORTAL")
    private String fortalecimiento;

    @Column(name = "ANTE_REPROBADOS")
    private String reprobados;

    @Column(name = "ANTE_PERSONALES")
    private String personales;

    @Column(name = "ANTE_FILARES")
    private String filiares;

    @Column(name = "ANTE_EDUCA")
    private String educacion;

    @Column(name = "ANTE_SOCIOAFEC")
    private String socioafectivo;

    @Column(name = "ANTE_DISCIP")
    private String disciplina;
}
