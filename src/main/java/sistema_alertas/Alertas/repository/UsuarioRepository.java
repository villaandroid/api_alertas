package sistema_alertas.Alertas.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import sistema_alertas.Alertas.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    boolean existsByCedula(String cedula);
}