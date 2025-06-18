package sistema_alertas.Alertas.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import sistema_alertas.Alertas.model.Estudiante;
import sistema_alertas.Alertas.repository.EstudianteRepository;

import sistema_alertas.Alertas.service.EstudianteService;

@Service
public class EstudianteServiceImpl implements EstudianteService {

    @Autowired
    private EstudianteRepository repository;

@Override
public List<Estudiante> obtenerTodos() {
    return repository.findAll(
        Sort.by(Sort.Direction.ASC, "nombres")
            .and(Sort.by(Sort.Direction.ASC, "apellidos"))
    );
}

    @Override
    public Estudiante obtenerPorId(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Estudiante> buscarPorNombre(String nombre) {
        return repository.findByNombresContainingIgnoreCase(nombre);
    }

    @Override
    public Estudiante guardar(Estudiante datos) {
        Estudiante nuevo = new Estudiante(
                datos.getTipoDoc(),
                datos.getNroDoc(),
                datos.getNombres(),
                datos.getApellidos(),
                datos.getGenero(),
                datos.getFechaNac(),
                datos.getDireccion(),
                datos.getBarrio(),
                datos.getEstrato(),
                datos.getSisben(),
                datos.getEps(),
                datos.getRh(),
                datos.getAcudiente(),
                datos.getTel(),
                datos.getSms(),
                datos.getCurso(),
                datos.getEstadoCivil(),
                datos.getTiempo(),
                datos.getNroHnos(),
                datos.getTipoVivienda(),
                datos.getImagen());

        return repository.save(nuevo);
    }

    @Override
    public Estudiante actualizar(Integer id, Estudiante datos) {
        Estudiante actual = obtenerPorId(id);
        if (actual == null)
            return null;

        Estudiante actualizado = new Estudiante(
                datos.getTipoDoc(),
                datos.getNroDoc(),
                datos.getNombres(),
                datos.getApellidos(),
                datos.getGenero(),
                datos.getFechaNac(),
                datos.getDireccion(),
                datos.getBarrio(),
                datos.getEstrato(),
                datos.getSisben(),
                datos.getEps(),
                datos.getRh(),
                datos.getAcudiente(),
                datos.getTel(),
                datos.getSms(),
                datos.getCurso(),
                datos.getEstadoCivil(),
                datos.getTiempo(),
                datos.getNroHnos(),
                datos.getTipoVivienda(),
                datos.getImagen());

        actualizado.setId(id);

        return repository.save(actualizado);
    }

    @Override
    public List<Estudiante> buscarPorDocumento(String documento) {
        return repository.findByNroDocContaining(documento);
    }

    @Override
    public boolean eliminar(Integer id) {
        Estudiante estudiante = obtenerPorId(id);
        if (estudiante != null) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public long contar() {
        return repository.count();
    }

}