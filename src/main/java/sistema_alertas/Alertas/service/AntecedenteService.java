package sistema_alertas.Alertas.service;

import sistema_alertas.Alertas.model.Antecedente;

public interface AntecedenteService {
    Antecedente obtenerPorEstudiante(Integer estudianteId);
    Antecedente guardar(Antecedente antecedente);
    Antecedente actualizar(Integer estudianteId, Antecedente datos);
}
