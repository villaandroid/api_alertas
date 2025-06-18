package sistema_alertas.Alertas.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConsultaConEstadoDTO {
    private Integer id;

    // Estudiante
    private Integer estudianteId;
    private String estudianteNombres;
    private String estudianteApellidos;
    private String estudianteDocumento;

    // Docente
    private Integer docenteId;
    private String docenteNombres;
    private String docenteApellidos;

    private Date fecha;
    private String motivo;
    private String descargos;
    private String alerta;
    private String estado;
}
