package sistema_alertas.Alertas.service;

import java.util.List;

import sistema_alertas.Alertas.model.Consulta;

public interface ConsultaService {

    List<Consulta> obtenerTodas();

    Consulta obtenerPorId(Integer id);

    Consulta guardar(Consulta consulta);

    Consulta actualizar(Integer id, Consulta datos);

    boolean eliminar(Integer id);

    List<Consulta> buscarPorMotivo(String motivo);

    List<Consulta> buscarPorEstudiante(Integer estuId);

    long contar();
}
