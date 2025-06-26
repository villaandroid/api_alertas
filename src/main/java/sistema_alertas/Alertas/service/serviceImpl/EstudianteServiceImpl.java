package sistema_alertas.Alertas.service.serviceImpl;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import sistema_alertas.Alertas.model.Estudiante;
import sistema_alertas.Alertas.repository.EstudianteRepository;
import sistema_alertas.Alertas.service.EstudianteService;

import java.nio.file.*;

@Service
public class EstudianteServiceImpl implements EstudianteService {

    @Autowired
    private EstudianteRepository repository;

    @Override
    public List<Estudiante> obtenerTodos() {
        return repository.findAll(
                Sort.by(Sort.Direction.ASC, "nombres")
                        .and(Sort.by(Sort.Direction.ASC, "apellidos")));
    }

    @Override
    public Estudiante obtenerPorId(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Estudiante> buscarPorNombre(String nombre) {
        return repository.findByNombresContainingIgnoreCase(nombre);
    }

    @Override
    public Estudiante guardar(Estudiante datos) {
        Estudiante nuevo = new Estudiante(
                datos.getTipoDoc(),
                datos.getNroDoc(),
                datos.getNombres(),
                datos.getApellidos(),
                datos.getGenero(),
                datos.getFechaNac(),
                datos.getDireccion(),
                datos.getBarrio(),
                datos.getEstrato(),
                datos.getSisben(),
                datos.getEps(),
                datos.getRh(),
                datos.getAcudiente(),
                datos.getTel(),
                datos.getSms(),
                datos.getCorreo(),
                datos.getCurso(),
                datos.getEstadoCivil(),
                datos.getTiempo(),
                datos.getNroHnos(),
                datos.getTipoVivienda(),
                datos.getImagen(),
                datos.getHuellaHash());

        return repository.save(nuevo);
    }

    @Override
    public Estudiante actualizar(Integer id, Estudiante datos) {
        Estudiante actual = obtenerPorId(id);
        if (actual == null)
            return null;

        Estudiante actualizado = new Estudiante(
                datos.getTipoDoc(),
                datos.getNroDoc(),
                datos.getNombres(),
                datos.getApellidos(),
                datos.getGenero(),
                datos.getFechaNac(),
                datos.getDireccion(),
                datos.getBarrio(),
                datos.getEstrato(),
                datos.getSisben(),
                datos.getEps(),
                datos.getRh(),
                datos.getAcudiente(),
                datos.getTel(),
                datos.getSms(),
                datos.getCorreo(),
                datos.getCurso(),
                datos.getEstadoCivil(),
                datos.getTiempo(),
                datos.getNroHnos(),
                datos.getTipoVivienda(),
                datos.getImagen(),
                datos.getHuellaHash());

        actualizado.setId(id);

        return repository.save(actualizado);
    }

    @Override
    public List<Estudiante> buscarPorDocumento(String documento) {
        return repository.findByNroDocContaining(documento);
    }

    @Override
    public boolean eliminar(Integer id) {
        Estudiante estudiante = obtenerPorId(id);
        if (estudiante != null) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public long contar() {
        return repository.count();
    }

    private static final String RUTA_IMAGENES = "imagenes_estudiantes/";

    @Override
    public String subirImagen(Integer id, MultipartFile archivo) {
        System.out.println("iniciando carga de imagen para estudiante id " + id);

        Estudiante estudiante = obtenerPorId(id);
        if (estudiante == null) {
            System.out.println("estudiante no encontrado");
            return null;
        }

        if (archivo == null || archivo.isEmpty()) {
            System.out.println("archivo nulo o vacio");
            return null;
        }

        String original = archivo.getOriginalFilename();
        System.out.println("nombre original del archivo " + original);

        if (original == null || !original.contains(".")) {
            System.out.println("nombre de archivo invalido");
            return null;
        }

        String extension = original.substring(original.lastIndexOf(".")).toLowerCase();
        System.out.println("extension detectada " + extension);

        if (!extension.equals(".jpg") &&
            !extension.equals(".jpeg") &&
            !extension.equals(".png") &&
            !extension.equals(".gif")) {
            System.out.println("extension no permitida");
            return null;
        }

        System.out.println("tamano del archivo " + archivo.getSize() + " bytes");
        if (archivo.getSize() > 5 * 1024 * 1024) {
            System.out.println("archivo demasiado grande");
            return null;
        }

        String nombreNuevo = UUID.randomUUID().toString() + extension;
        Path carpeta = Paths.get(System.getProperty("user.dir"), RUTA_IMAGENES);
        Path destino = carpeta.resolve(nombreNuevo);

        System.out.println("ruta destino " + destino.toAbsolutePath());

        try {
            Files.createDirectories(carpeta);
            archivo.transferTo(destino.toFile());
            System.out.println("imagen guardada correctamente");

            if (estudiante.getImagen() != null) {
                System.out.println("eliminando imagen anterior " + estudiante.getImagen());
                Files.deleteIfExists(carpeta.resolve(estudiante.getImagen()));
            }

            estudiante.setImagen(nombreNuevo);
            repository.save(estudiante);
            System.out.println("imagen registrada en el estudiante");
            return nombreNuevo;

        } catch (IOException e) {
            System.out.println("error de escritura en disco " + e.getMessage());
            return null;
        }
    }

    @Override
    public boolean eliminarImagen(Integer id) {
        Estudiante estudiante = obtenerPorId(id);
        if (estudiante == null || estudiante.getImagen() == null)
            return false;

        Path ruta = Paths.get(RUTA_IMAGENES + estudiante.getImagen());
        try {
            Files.deleteIfExists(ruta);
            estudiante.setImagen(null);
            repository.save(estudiante);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    @Override
    public byte[] obtenerImagen(Integer id) {
        Estudiante estudiante = obtenerPorId(id);
        if (estudiante == null || estudiante.getImagen() == null) return null;

        Path ruta = Paths.get(System.getProperty("user.dir"), RUTA_IMAGENES, estudiante.getImagen());

        try {
            return Files.readAllBytes(ruta);
        } catch (IOException e) {
            System.out.println("error al leer la imagen " + e.getMessage());
            return null;
        }
    }
}
