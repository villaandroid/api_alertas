package sistema_alertas.Alertas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sistema_alertas.Alertas.model.Seguimiento;
import sistema_alertas.Alertas.service.SeguimientoService;

import java.util.List;

@RestController
@RequestMapping("/api/seguimientos")
@CrossOrigin(origins = "*")
public class SeguimientoController {

    @Autowired
    private SeguimientoService seguimientoService;

    @GetMapping("/consulta/{consultaId}")
    public List<Seguimiento> obtenerPorConsulta(@PathVariable Integer consultaId) {
        return seguimientoService.obtenerPorConsulta(consultaId);
    }

    @PostMapping
    public ResponseEntity<Seguimiento> guardar(@RequestBody Seguimiento seguimiento) {
        return ResponseEntity.ok(seguimientoService.guardar(seguimiento));
    }

    @GetMapping("/estado/{consultaId}")
    public ResponseEntity<String> obtenerEstadoActual(@PathVariable Integer consultaId) {
        Seguimiento ultimo = seguimientoService.obtenerUltimoSeguimiento(consultaId);
        if (ultimo != null) {
            return ResponseEntity.ok(ultimo.getEstado());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
