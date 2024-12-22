package pe.edu.cibertec.Proyecto_DAW_Final.rest_controller;

import pe.edu.cibertec.Proyecto_DAW_Final.entity.UsuarioEntity;
import pe.edu.cibertec.Proyecto_DAW_Final.repository.UsuarioRepository;
import pe.edu.cibertec.Proyecto_DAW_Final.repository.TipoRepository;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/usuarios") 
@AllArgsConstructor
public class UsuaController {
	private final UsuarioRepository usuarioRepository;
    private final TipoRepository tipoRepository;


    @GetMapping
    public List<UsuarioEntity> list() {
        return usuarioRepository.findAll();
    }


    @GetMapping("{id}")
    public ResponseEntity<UsuarioEntity> findById(@PathVariable Integer id) {
        return ResponseEntity.of(usuarioRepository.findById(id));
    }

    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<UsuarioEntity> register(@RequestBody UsuarioEntity usuario) {
        
        if (tipoRepository.existsById(usuario.getTipo().getIdtipo())) {
            return ResponseEntity.ok(usuarioRepository.save(usuario));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    
    @PutMapping("{id}")
    public ResponseEntity<UsuarioEntity> edit(@PathVariable Integer id, @RequestBody UsuarioEntity usuarioRequest) {
        Optional<UsuarioEntity> optionalUsuario = usuarioRepository.findById(id);
        if (optionalUsuario.isPresent()) {
            UsuarioEntity usuario = optionalUsuario.get();
            usuario.setNombre(usuarioRequest.getNombre());
            usuario.setApellido(usuarioRequest.getApellido());
            usuario.setEdad(usuarioRequest.getEdad());
            usuario.setUsuario(usuarioRequest.getUsuario());
            usuario.setClave(usuarioRequest.getClave());
            usuario.setUrlImagen(usuarioRequest.getUrlImagen());
            usuario.setTipo(usuarioRequest.getTipo()); 

            return ResponseEntity.ok(usuarioRepository.save(usuario));
        }
        return ResponseEntity.notFound().build();
    }

    
    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
