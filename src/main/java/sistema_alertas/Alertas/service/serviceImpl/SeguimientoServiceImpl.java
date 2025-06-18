package sistema_alertas.Alertas.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sistema_alertas.Alertas.model.Seguimiento;
import sistema_alertas.Alertas.repository.SeguimientoRepository;
import sistema_alertas.Alertas.service.SeguimientoService;

import java.util.List;

@Service
public class SeguimientoServiceImpl implements SeguimientoService {

    @Autowired
    private SeguimientoRepository seguimientoRepository;

    @Override
    public List<Seguimiento> obtenerPorConsulta(Integer consultaId) {
        return seguimientoRepository.findByConsultaIdOrderByFechaDesc(consultaId);
    }

    @Override
    public Seguimiento guardar(Seguimiento seguimiento) {
        return seguimientoRepository.save(seguimiento);
    }

    @Override
    public Seguimiento obtenerUltimoSeguimiento(Integer consultaId) {
        return seguimientoRepository.findTopByConsultaIdOrderByFechaDesc(consultaId);
    }
}