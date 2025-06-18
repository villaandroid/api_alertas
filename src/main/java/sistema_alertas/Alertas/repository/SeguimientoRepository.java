package sistema_alertas.Alertas.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import sistema_alertas.Alertas.model.Seguimiento;

public interface SeguimientoRepository extends JpaRepository<Seguimiento, Integer> {
    List<Seguimiento> findByConsultaIdOrderByFechaDesc(Integer consultaId);
    Seguimiento findTopByConsultaIdOrderByFechaDesc(Integer consultaId);
}
