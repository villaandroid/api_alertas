package sistema_alertas.Alertas.service;

import java.util.List;


import sistema_alertas.Alertas.model.Estudiante;

public interface EstudianteService {
    List<Estudiante> obtenerTodos();
    Estudiante obtenerPorId(Integer id);
    List<Estudiante> buscarPorNombre(String nombre);
    List<Estudiante> buscarPorDocumento(String documento);
    Estudiante guardar(Estudiante estudiante);
    Estudiante actualizar(Integer id, Estudiante estudiante);
    boolean eliminar(Integer id);
    long contar();
}