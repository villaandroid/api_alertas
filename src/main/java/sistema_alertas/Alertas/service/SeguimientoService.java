package sistema_alertas.Alertas.service;

import sistema_alertas.Alertas.model.Seguimiento;

import java.util.Optional;

public interface SeguimientoService {

    Optional<Seguimiento> obtenerPorConsulta(Integer consultaId);

    Seguimiento guardar(Seguimiento seguimiento);

    Seguimiento obtenerUltimoSeguimiento(Integer consultaId);

    long contarPorConsulta(Integer consultaId);

    void asociarASeguimientoYCita(Integer seguimientoId, Integer citaId);
    public Optional<Seguimiento> obtenerPorId(Integer id);
}
