package sistema_alertas.Alertas.service.serviceImpl;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sistema_alertas.Alertas.dto.EstadisticasDTO;
import sistema_alertas.Alertas.model.Consulta;
import sistema_alertas.Alertas.model.Seguimiento;
import sistema_alertas.Alertas.repository.ConsultaRepository;
import sistema_alertas.Alertas.repository.SeguimientoRepository;
import sistema_alertas.Alertas.service.EstadisticaService;

@Service
public class EstadisticaServiceImpl implements EstadisticaService {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private SeguimientoRepository seguimientoRepository;

    @Override
    public EstadisticasDTO obtenerEstadisticas() {
        List<Consulta> todas = consultaRepository.findAll();
        List<Seguimiento> seguimientos = seguimientoRepository.findAll();

        EstadisticasDTO dto = new EstadisticasDTO();

        int totalAlertas = todas.size();
        int pendientes = 0;
        int completadas = 0;
        int critico = 0;
        int alto = 0;
        int moderado = 0;
        int leve = 0;
        int mejoras = 0;
        int sinSeguimiento = 0;

        List<Integer> idsEstudiantes = new ArrayList<>();
        List<Integer> consultasConSeguimiento = new ArrayList<>();

        for (int i = 0; i < seguimientos.size(); i++) {
            Seguimiento s = seguimientos.get(i);

            if (s.getSeguimiento() != null && s.getSeguimiento().equalsIgnoreCase("mejora")) {
                mejoras++;
            }

            if (s.getConsulta() != null && !consultasConSeguimiento.contains(s.getConsulta().getId())) {
                consultasConSeguimiento.add(s.getConsulta().getId());
            }

            if (s.getEstado() != null) {
                if (s.getEstado().equalsIgnoreCase("pendiente")) {
                    pendientes++;
                } else if (s.getEstado().equalsIgnoreCase("completado")) {
                    completadas++;
                }
            }
        }

        for (int i = 0; i < todas.size(); i++) {
            Consulta c = todas.get(i);

            if (c.getEstudiante() != null && !idsEstudiantes.contains(c.getEstudiante().getId())) {
                idsEstudiantes.add(c.getEstudiante().getId());
            }

            if (c.getAlerta() != null) {
                if (c.getAlerta().equalsIgnoreCase("CRITICO")) {
                    critico++;
                } else if (c.getAlerta().equalsIgnoreCase("MODERADO")) {
                    moderado++;
                } else if (c.getAlerta().equalsIgnoreCase("LEVE")) {
                    leve++;
                }
                  else if (c.getAlerta().equalsIgnoreCase("ALTO")) {
                    alto++;
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
        dto.setPromedioPorEstudiante(idsEstudiantes.size() > 0 ? totalAlertas / (double) idsEstudiantes.size() : 0.0);

        Map<String, Integer> consultasPorMes = new LinkedHashMap<>();
        String[] meses = {
                "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
                "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"
        };

        // Inicializa con 0 por cada mes
        for (String mes : meses) {
            consultasPorMes.put(mes, 0);
        }

        for (Consulta c : todas) {
            if (c.getFecha() != null) {
                Calendar cal = Calendar.getInstance();
                cal.setTime(c.getFecha());
                int mes = cal.get(Calendar.MONTH); // 0 = Enero
                String nombreMes = meses[mes];
                consultasPorMes.put(nombreMes, consultasPorMes.get(nombreMes) + 1);
            }
        }

        dto.setConsultasPorMes(consultasPorMes);
        return dto;
    }
}