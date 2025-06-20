package sistema_alertas.Alertas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import sistema_alertas.Alertas.dto.ConsultaConEstadoDTO;
import sistema_alertas.Alertas.model.Consulta;
import sistema_alertas.Alertas.service.ConsultaService;
import sistema_alertas.Alertas.service.SeguimientoService;

@RestController
@RequestMapping("/api/consultas")
@CrossOrigin(origins = "*")
public class ConsultaController {

    @Autowired
    private ConsultaService consultaService;
    @Autowired
    private SeguimientoService seguimientoService;

    @GetMapping
    public List<ConsultaConEstadoDTO> obtenerTodas() {
        List<Consulta> consultas = consultaService.obtenerTodas();

        return consultas.stream().map(consulta -> {
            String estado = "Sin seguimiento";
            if (seguimientoService.obtenerUltimoSeguimiento(consulta.getId()) != null) {
                estado = seguimientoService.obtenerUltimoSeguimiento(consulta.getId()).getEstado();
            }

            return new ConsultaConEstadoDTO(
                    consulta.getId(),

                    consulta.getEstudiante().getId(),
                    consulta.getEstudiante().getNombres(),
                    consulta.getEstudiante().getApellidos(),
                    consulta.getEstudiante().getNroDoc(),

                    consulta.getDocente().getId(),
                    consulta.getDocente().getNombres(),
                    consulta.getDocente().getApellidos(),

                    consulta.getFecha(),
                    consulta.getMotivo(),
                    consulta.getDescargos(),
                    consulta.getAlerta(),
                    estado,

                    consulta.getPresenciaEstudiante(),
                    consulta.getMetodoValidacion().name());
        }).toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Consulta> obtenerPorId(@PathVariable Integer id) {
        Consulta consulta = consultaService.obtenerPorId(id);
        if (consulta != null) {
            return ResponseEntity.ok(consulta);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/buscar")
    public List<Consulta> buscarPorMotivo(@RequestParam(required = false) String motivo,
            @RequestParam(required = false) Integer estudianteId) {
        if (motivo != null) {
            return consultaService.buscarPorMotivo(motivo);
        }
        if (estudianteId != null) {
            return consultaService.buscarPorEstudiante(estudianteId);
        }
        return consultaService.obtenerTodas();
    }

    @PostMapping
    public ResponseEntity<Consulta> guardar(@RequestBody Consulta consulta) {
        Consulta nueva = consultaService.guardar(consulta);
        return ResponseEntity.ok(nueva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Consulta> actualizar(@PathVariable Integer id, @RequestBody Consulta consulta) {
        Consulta actualizada = consultaService.actualizar(id, consulta);
        if (actualizada != null) {
            return ResponseEntity.ok(actualizada);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Integer id) {
        if (consultaService.eliminar(id)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/estudiante/{id}")
    public List<Consulta> obtenerPorEstudiante(@PathVariable Integer id) {
        return consultaService.buscarPorEstudiante(id);
    }

    @GetMapping("/total")
    public long totalConsultas() {
        return consultaService.contar();
    }
}
