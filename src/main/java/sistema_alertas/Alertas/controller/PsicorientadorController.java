package sistema_alertas.Alertas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import sistema_alertas.Alertas.model.Psicorientador;
import sistema_alertas.Alertas.service.PsicorientadorService;

@RestController
@RequestMapping(value = "/api/psicorientadores", produces = "application/json")
@CrossOrigin(origins = "*")

public class PsicorientadorController {

    @Autowired
    private PsicorientadorService service;

    @GetMapping
    public List<Psicorientador> obtenerTodos() {
        return service.obtenerTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Psicorientador> obtenerPorId(@PathVariable Integer id) {
        Psicorientador psic = service.obtenerPorId(id);
        return psic != null ? ResponseEntity.ok(psic) : ResponseEntity.notFound().build();
    }

    @GetMapping("/buscar")
    public List<Psicorientador> buscar(
        @RequestParam(required = false) String nombre,
        @RequestParam(required = false) String documento
    ) {
        if (nombre != null) return service.buscarPorNombre(nombre);
        if (documento != null) return service.buscarPorDocumento(documento);
        return service.obtenerTodos();
    }

    @PostMapping
    public ResponseEntity<Psicorientador> guardar(@RequestBody Psicorientador datos) {
        return ResponseEntity.ok(service.guardar(datos));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Psicorientador> actualizar(@PathVariable Integer id, @RequestBody Psicorientador datos) {
        Psicorientador actualizado = service.actualizar(id, datos);
        return actualizado != null ? ResponseEntity.ok(actualizado) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Integer id) {
        return service.eliminar(id) ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @GetMapping("/total")
    public long total() {
        return service.contar();
    }
}
