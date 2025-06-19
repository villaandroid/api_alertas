package sistema_alertas.Alertas.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import sistema_alertas.Alertas.model.Antecedente;


import java.util.Optional;

public interface AntecedenteRepository extends JpaRepository<Antecedente, Integer> {
    Optional<Antecedente> findByEstudianteId(Integer estudianteId);
}
