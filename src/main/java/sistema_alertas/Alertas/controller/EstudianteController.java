package sistema_alertas.Alertas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import sistema_alertas.Alertas.model.Estudiante;
import sistema_alertas.Alertas.service.EstudianteService;

@RestController
@RequestMapping("/api/estudiantes")
@CrossOrigin(origins = "*")
public class EstudianteController {

    @Autowired
    private EstudianteService estudianteService;

    @GetMapping
    public List<Estudiante> obtenerTodos() {
        return estudianteService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Estudiante> obtenerPorId(@PathVariable Integer id) {
        Estudiante estudiante = estudianteService.obtenerPorId(id);

        if (estudiante != null) {
            return ResponseEntity.ok(estudiante);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
@GetMapping("/buscar")
public ResponseEntity<List<Estudiante>> buscarEstudiante(@RequestParam String valor) {
    valor = valor.trim();
    return ResponseEntity.ok(estudianteService.buscarPorNombre(valor));
}


    @PostMapping
    public ResponseEntity<Estudiante> guardar(@RequestBody Estudiante datos) {
        return ResponseEntity.ok(estudianteService.guardar(datos));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Estudiante> actualizar(@PathVariable Integer id, @RequestBody Estudiante datos) {
        Estudiante actualizado = estudianteService.actualizar(id, datos);
        if (actualizado != null) {
            return ResponseEntity.ok(actualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        boolean eliminado = estudianteService.eliminar(id);
        if (eliminado) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/total")
    public long totalEstudiantes() {
        return estudianteService.contar();
    }

}