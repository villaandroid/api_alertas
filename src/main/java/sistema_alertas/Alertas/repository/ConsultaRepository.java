package sistema_alertas.Alertas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import sistema_alertas.Alertas.model.Consulta;
import sistema_alertas.Alertas.model.Estudiante;

public interface ConsultaRepository extends JpaRepository<Consulta, Integer> {

    // Buscar todas las consultas de un estudiante específico
    List<Consulta> findByEstudiante(Estudiante estudiante);

    // Buscar por nombre o parte del motivo (opcional, si deseas hacer búsqueda avanzada)
    List<Consulta> findByMotivoContainingIgnoreCase(String motivo);
}
