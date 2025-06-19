package sistema_alertas.Alertas.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sistema_alertas.Alertas.model.Antecedente;
import sistema_alertas.Alertas.repository.AntecedenteRepository;
import sistema_alertas.Alertas.service.AntecedenteService;

import java.util.Optional;

@Service
public class AntecedenteServiceImpl implements AntecedenteService {

    @Autowired
    private AntecedenteRepository repository;

   @Override
public Antecedente obtenerPorEstudiante(Integer estudianteId) {
    return repository.findByEstudianteId(estudianteId).orElse(null);
}

    @Override
    public Antecedente guardar(Antecedente antecedente) {
        return repository.save(antecedente);
    }

    @Override
    public Antecedente actualizar(Integer estudianteId, Antecedente nuevosDatos) {
        Optional<Antecedente> existente = repository.findById(estudianteId);
        if (existente.isPresent()) {
            Antecedente a = existente.get();
            a.setSalud(nuevosDatos.getSalud());
            a.setFortalecimiento(nuevosDatos.getFortalecimiento());
            a.setReprobados(nuevosDatos.getReprobados());
            a.setPersonales(nuevosDatos.getPersonales());
            a.setFiliares(nuevosDatos.getFiliares());
            a.setEducacion(nuevosDatos.getEducacion());
            a.setSocioafectivo(nuevosDatos.getSocioafectivo());
            a.setDisciplina(nuevosDatos.getDisciplina());
            return repository.save(a);
        }
        return null;
    }
}