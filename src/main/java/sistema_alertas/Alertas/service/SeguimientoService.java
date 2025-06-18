package sistema_alertas.Alertas.service;

import sistema_alertas.Alertas.model.Seguimiento;

import java.util.List;

public interface SeguimientoService {
    List<Seguimiento> obtenerPorConsulta(Integer consultaId);
    Seguimiento guardar(Seguimiento seguimiento);
    Seguimiento obtenerUltimoSeguimiento(Integer consultaId);
}
