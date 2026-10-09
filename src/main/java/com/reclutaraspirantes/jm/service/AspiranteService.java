package main.java.com.reclutaraspirantes.jm.service;

import java.util.List;
import main.java.com.reclutaraspirantes.jm.dto.AspiranteDTO;
import main.java.com.reclutaraspirantes.jm.exception.ValidacionException;
import main.java.com.reclutaraspirantes.jm.model.Aspirante;
import main.java.com.reclutaraspirantes.jm.repository.AspiranteRepository;


public class AspiranteService {

    private final AspiranteRepository repository;

    public AspiranteService(AspiranteRepository repository) {
        this.repository = repository;
    }

    public List<AspiranteDTO> listar() {
        return repository.listar().stream().map(this::aDto).toList();
    }

    public void agregar(AspiranteDTO dto) {
        validar(dto);
        repository.insertar(aEntidad(dto));
    }

    public void actualizar(AspiranteDTO dto) {
        validar(dto);
        repository.actualizar(aEntidad(dto));
    }

    public void eliminar(int id) {
        repository.eliminar(id);
    }

    private void validar(AspiranteDTO dto) {
        if (vacio(dto.nombre()) || vacio(dto.apellido()) || vacio(dto.correo())
                || vacio(dto.telefono()) || vacio(dto.direccion())) {
            throw new ValidacionException("Complete todos los campos.");
        }
        if (!dto.correo().contains("@")) {
            throw new ValidacionException("El correo no es válido.");
        }
        if (dto.edad() < 18 || dto.edad() > 99) {
            throw new ValidacionException("La edad debe estar entre 18 y 99.");
        }
        if (repository.existeCorreo(dto.correo().trim(), dto.id())) {
            throw new ValidacionException("Ya existe un aspirante con ese correo.");
        }
    }

    private boolean vacio(String texto) {
        return texto == null || texto.isBlank();
    }

    private AspiranteDTO aDto(Aspirante a) {
        return new AspiranteDTO(a.getIdAspirante(), a.getNombre(), a.getApellido(), a.getCorreo(),
                a.getTelefono(), a.getDireccion(), a.getEdad());
    }

    private Aspirante aEntidad(AspiranteDTO d) {
        return new Aspirante(d.id(), d.nombre().trim(), d.apellido().trim(), d.correo().trim(),
                d.telefono().trim(), d.direccion().trim(), d.edad());
    }
}
