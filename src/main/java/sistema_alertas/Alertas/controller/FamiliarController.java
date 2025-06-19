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
import org.springframework.web.bind.annotation.RestController;

import sistema_alertas.Alertas.model.Familiar;
import sistema_alertas.Alertas.service.FamiliarService;

@RestController
@RequestMapping("/api/familiares")
@CrossOrigin(origins = "*")
public class FamiliarController {

    @Autowired
    private FamiliarService service;

 @GetMapping("/estudiante/{id}")
public ResponseEntity<?> obtenerPorEstudiante(@PathVariable Integer id) {
    try {
        List<Familiar> lista = service.obtenerPorEstudiante(id);
        return ResponseEntity.ok(lista);
    } catch (Exception e) {
        e.printStackTrace(); 
        return ResponseEntity.status(500).body("Error al obtener familiares: " + e.getMessage());
    }
}

    @PostMapping
    public ResponseEntity<Familiar> crear(@RequestBody Familiar familiar) {
        return ResponseEntity.ok(service.guardar(familiar));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Familiar> actualizar(@PathVariable Integer id, @RequestBody Familiar datos) {
        Familiar actualizado = service.actualizar(id, datos);
        return actualizado != null ? ResponseEntity.ok(actualizado) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Integer id) {
        return service.eliminar(id) ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}
