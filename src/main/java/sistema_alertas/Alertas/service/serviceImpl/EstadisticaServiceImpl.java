package sistema_alertas.Alertas.service.serviceImpl;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sistema_alertas.Alertas.dto.EstadisticasDTO;
import sistema_alertas.Alertas.model.Consulta;
import sistema_alertas.Alertas.model.Seguimiento;
import sistema_alertas.Alertas.model.ObservacionSeguimiento;
import sistema_alertas.Alertas.model.enums.ConsEstado;

import sistema_alertas.Alertas.repository.ConsultaRepository;
import sistema_alertas.Alertas.repository.SeguimientoRepository;
import sistema_alertas.Alertas.repository.ObservacionSeguimientoRepository;
import sistema_alertas.Alertas.service.EstadisticaService;

@Service
public class EstadisticaServiceImpl implements EstadisticaService {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private SeguimientoRepository seguimientoRepository;

    @Autowired
    private ObservacionSeguimientoRepository observacionRepository;

    @Override
    public EstadisticasDTO obtenerEstadisticas() {
        List<Consulta> todasConsultas = consultaRepository.findAll();
        List<Seguimiento> todosSeguimientos = seguimientoRepository.findAll();

        EstadisticasDTO dto = new EstadisticasDTO();

        int totalAlertas = todasConsultas.size();
        int pendientes = 0;
        int completadas = 0;
        int critico = 0;
        int alto = 0;
        int moderado = 0;
        int leve = 0;
        int mejoras = 0;
        int sinSeguimiento = 0;

        Set<Integer> idsEstudiantes = new HashSet<>();
        Set<Integer> consultasConSeguimiento = new HashSet<>();

        for (Seguimiento s : todosSeguimientos) {
            if (s.getConsulta() != null) {
                consultasConSeguimiento.add(s.getConsulta().getId());

                // Contar mejoras en observaciones asociadas
                List<ObservacionSeguimiento> obsList = observacionRepository.findBySeguimientoIdOrderByFechaAsc(s.getId());
                mejoras += obsList.stream()
                        .filter(obs -> "mejora".equalsIgnoreCase(obs.getTexto()))
                        .count();

                // Contar estados pendientes y completados desde consulta
                ConsEstado estadoConsulta = s.getConsulta().getEstado();
                if (estadoConsulta != null) {
                    if (estadoConsulta == ConsEstado.pendiente) pendientes++;
                    else if (estadoConsulta == ConsEstado.completado) completadas++;
                }
            }
        }

        for (Consulta c : todasConsultas) {
            if (c.getEstudiante() != null) {
                idsEstudiantes.add(c.getEstudiante().getId());
            }
            if (c.getNivel() != null) {
                switch (c.getNivel()) {
                    case critico -> critico++;
                    case alto -> alto++;
                    case moderado -> moderado++;
                    case leve -> leve++;
                }
            }
            if (!consultasConSeguimiento.contains(c.getId())) {
                sinSeguimiento++;
            }
        }

        dto.setTotalAlertas(totalAlertas);
        dto.setEstudiantesConAlertas(idsEstudiantes.size());
        dto.setAlertasPendientes(pendientes);
        dto.setAlertasCompletadas(completadas);
        dto.setAlertasCritico(critico);
        dto.setAlertasAlto(alto);
        dto.setAlertasModerado(moderado);
        dto.setAlertasLeve(leve);
        dto.setAlertasMejoradas(mejoras);
        dto.setAlertasSinSeguimiento(sinSeguimiento);
        dto.setPromedioPorMes(totalAlertas / 12.0);
        dto.setPromedioPorEstudiante(idsEstudiantes.isEmpty() ? 0.0 : totalAlertas / (double) idsEstudiantes.size());

        Map<String, Integer> consultasPorMes = new LinkedHashMap<>();
        String[] meses = {
            "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
            "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"
        };
        // Inicializar meses con 0
        for (String mes : meses) {
            consultasPorMes.put(mes, 0);
        }

        for (Consulta c : todasConsultas) {
            if (c.getFecha() != null) {
                Calendar cal = Calendar.getInstance();
                cal.setTime(c.getFecha());
                int mesIndex = cal.get(Calendar.MONTH);
                String nombreMes = meses[mesIndex];
                consultasPorMes.put(nombreMes, consultasPorMes.get(nombreMes) + 1);
            }
        }

        dto.setConsultasPorMes(consultasPorMes);

        return dto;
    }
}
