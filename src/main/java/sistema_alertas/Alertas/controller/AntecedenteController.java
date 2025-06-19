package sistema_alertas.Alertas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sistema_alertas.Alertas.model.Antecedente;
import sistema_alertas.Alertas.service.AntecedenteService;

@RestController
@RequestMapping("/api/antecedentes")
@CrossOrigin(origins = "*")
public class AntecedenteController {

    @Autowired
    private AntecedenteService service;

   @GetMapping("/estudiante/{id}")
public ResponseEntity<Antecedente> obtenerPorEstudiante(@PathVariable Integer id) {
    Antecedente antecedentes = service.obtenerPorEstudiante(id);
    if (antecedentes == null) {
        return ResponseEntity.notFound().build(); 
    }
    return ResponseEntity.ok(antecedentes);
}

    @PostMapping
    public ResponseEntity<Antecedente> crear(@RequestBody Antecedente antecedente) {
        return ResponseEntity.ok(service.guardar(antecedente));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Antecedente> actualizar(@PathVariable Integer id, @RequestBody Antecedente datos) {
        Antecedente actualizado = service.actualizar(id, datos);
        return actualizado != null ? ResponseEntity.ok(actualizado) : ResponseEntity.notFound().build();
    }
}
