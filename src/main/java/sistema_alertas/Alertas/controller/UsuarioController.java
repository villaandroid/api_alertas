package sistema_alertas.Alertas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import sistema_alertas.Alertas.model.Usuario;
import sistema_alertas.Alertas.model.Docente;
import sistema_alertas.Alertas.model.Psicorientador;
import sistema_alertas.Alertas.model.Estudiante;
import sistema_alertas.Alertas.repository.*;

import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@RestController
@RequestMapping(value = "/api/usuarios", produces = "application/json")
@CrossOrigin(origins = "*")

public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private DocenteRepository docenteRepository;

    @Autowired
    private EstudianteRepository estudianteRepository;

    @Autowired
    private PsicorientadorRepository psicorientadorRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/generar")
    public ResponseEntity<?> generarUsuario(
            @RequestParam String cedula,
            @RequestParam int rol) {
        if (usuarioRepository.existsByCedula(cedula)) {
            return ResponseEntity.badRequest().body("El usuario ya existe.");
        }

        Usuario usuario = new Usuario();
        usuario.setCedula(cedula);
        usuario.setRol(rol);
        usuario.setPassword(passwordEncoder.encode(cedula));

        switch (rol) {
            case 0: { // Docente
                Optional<Docente> dOpt = docenteRepository.findByNroDoc(cedula);
                if (dOpt.isEmpty())
                    return ResponseEntity.badRequest().body("Docente no encontrado.");
                Docente d = dOpt.get();
                usuario.setNombres(d.getNombres() + " " + d.getApellidos());
                usuario.setCorreo(d.getCorreo());
                break;
            }
            case 1: { // Estudiante
                Optional<Estudiante> eOpt = estudianteRepository.findByNroDoc(cedula);
                if (eOpt.isEmpty())
                    return ResponseEntity.badRequest().body("Estudiante no encontrado.");
                Estudiante e = eOpt.get();
                usuario.setNombres(e.getNombres() + " " + e.getApellidos());
                usuario.setCorreo(e.getCorreo());
                break;
            }
            case 2: { // Psicorientador
                Optional<Psicorientador> pOpt = psicorientadorRepository.findByNroDoc(cedula);
                if (pOpt.isEmpty())
                    return ResponseEntity.badRequest().body("Psicorientador no encontrado.");
                Psicorientador p = pOpt.get();
                usuario.setNombres(p.getNombres() + " " + p.getApellidos());
                usuario.setCorreo(p.getCorreo());
                break;
            }
            default:
                return ResponseEntity.badRequest().body("Rol inválido.");
        }

        Usuario creado = usuarioRepository.save(usuario);
        return ResponseEntity.ok(creado);
    }
}
