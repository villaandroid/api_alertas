package sistema_alertas.Alertas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import sistema_alertas.Alertas.model.Seguimiento;
import sistema_alertas.Alertas.service.SeguimientoService;

@RestController
@RequestMapping(value = "/api/seguimientos", produces = "application/json")
@CrossOrigin(origins = "*")

public class SeguimientoController {

    @Autowired
    private SeguimientoService seguimientoService;

    // seguimiento por ID de consulta
    @GetMapping("/consulta/{consultaId}")
    public ResponseEntity<Seguimiento> obtenerPorConsulta(@PathVariable Integer consultaId) {
        return seguimientoService.obtenerPorConsulta(consultaId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

 
    @PostMapping
    public ResponseEntity<Seguimiento> guardar(@RequestBody Seguimiento seguimiento) {
        return ResponseEntity.ok(seguimientoService.guardar(seguimiento));
    }
}
