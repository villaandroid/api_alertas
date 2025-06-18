package sistema_alertas.Alertas.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sistema_alertas.Alertas.model.Consulta;
import sistema_alertas.Alertas.model.Estudiante;
import sistema_alertas.Alertas.repository.ConsultaRepository;
import sistema_alertas.Alertas.repository.EstudianteRepository;
import sistema_alertas.Alertas.service.ConsultaService;

@Service
public class ConsultaServiceImpl implements ConsultaService {

    @Autowired
    private ConsultaRepository repository;

    @Autowired
    private EstudianteRepository estudianteRepository;

    @Override
    public List<Consulta> obtenerTodas() {
        return repository.findAll();
    }

    @Override
    public Consulta obtenerPorId(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Consulta guardar(Consulta consulta) {
        return repository.save(consulta);
    }

    @Override
    public Consulta actualizar(Integer id, Consulta datos) {
        Consulta actual = obtenerPorId(id);
        if (actual == null)
            return null;

        actual.setFecha(datos.getFecha());
        actual.setMotivo(datos.getMotivo());
        actual.setDescargos(datos.getDescargos());
        actual.setAlerta(datos.getAlerta());
        actual.setDocente(datos.getDocente());
        actual.setEstudiante(datos.getEstudiante());

        return repository.save(actual);
    }

    @Override
    public boolean eliminar(Integer id) {
        Consulta consulta = obtenerPorId(id);
        if (consulta != null) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<Consulta> buscarPorMotivo(String motivo) {
        return repository.findByMotivoContainingIgnoreCase(motivo);
    }

    @Override
    public List<Consulta> buscarPorEstudiante(Integer estuId) {
        Estudiante estudiante = estudianteRepository.findById(estuId).orElse(null);
        if (estudiante == null)
            return List.of();
        return repository.findByEstudiante(estudiante);
    }

    @Override
    public long contar() {
        return repository.count();
    }
}
