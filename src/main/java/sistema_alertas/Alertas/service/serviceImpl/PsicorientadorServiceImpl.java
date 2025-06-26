package sistema_alertas.Alertas.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sistema_alertas.Alertas.model.Psicorientador;
import sistema_alertas.Alertas.repository.PsicorientadorRepository;
import sistema_alertas.Alertas.service.PsicorientadorService;

@Service
public class PsicorientadorServiceImpl implements PsicorientadorService {

    @Autowired
    private PsicorientadorRepository repository;

    @Override
    public List<Psicorientador> obtenerTodos() {
        return repository.findAll();
    }

    @Override
    public Psicorientador obtenerPorId(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Psicorientador> buscarPorNombre(String nombre) {
        return repository.findByNombresContainingIgnoreCase(nombre);
    }

    @Override
    public List<Psicorientador> buscarPorDocumento(String documento) {
        return repository.findByNroDocContaining(documento);
    }

    @Override
    public Psicorientador guardar(Psicorientador psic) {
        return repository.save(psic);
    }

    @Override
    public Psicorientador actualizar(Integer id, Psicorientador datos) {
        Psicorientador actual = obtenerPorId(id);
        if (actual == null) return null;

        actual.setTipoDoc(datos.getTipoDoc());
        actual.setNroDoc(datos.getNroDoc());
        actual.setNombres(datos.getNombres());
        actual.setApellidos(datos.getApellidos());

        return repository.save(actual);
    }

    @Override
    public boolean eliminar(Integer id) {
        if (repository.existsById(id)) {
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
