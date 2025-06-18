package sistema_alertas.Alertas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import sistema_alertas.Alertas.model.Estudiante;

public interface EstudianteRepository extends JpaRepository<Estudiante, Integer> {
    List<Estudiante> findByNombresContainingIgnoreCase(String nombres);

    List<Estudiante> findByNroDocContaining(String nroDoc);
}