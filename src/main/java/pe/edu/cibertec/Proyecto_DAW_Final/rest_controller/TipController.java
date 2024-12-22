package pe.edu.cibertec.Proyecto_DAW_Final.rest_controller;

import pe.edu.cibertec.Proyecto_DAW_Final.entity.TipoEntity;
import pe.edu.cibertec.Proyecto_DAW_Final.repository.TipoRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/tipos") 
@AllArgsConstructor
public class TipController {
	private final TipoRepository tipoRepository;

    
    @GetMapping
    public List<TipoEntity> list() {
        return tipoRepository.findAll();
    }

    
    @GetMapping("{id}")
    public ResponseEntity<TipoEntity> findById(@PathVariable Integer id) {
        return ResponseEntity.of(tipoRepository.findById(id));
    }

    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TipoEntity register(@RequestBody TipoEntity tipo) {
        return tipoRepository.save(tipo);
    }

    
    @PutMapping("{id}")
    public ResponseEntity<TipoEntity> edit(@PathVariable Integer id, @RequestBody TipoEntity tipoRequest) {
        Optional<TipoEntity> optionalTipo = tipoRepository.findById(id);
        if (optionalTipo.isPresent()) {
            TipoEntity tipo = optionalTipo.get();
            tipo.setDescripcion(tipoRequest.getDescripcion());
            tipoRepository.save(tipo);

            return ResponseEntity.ok(tipo);
        }
        return ResponseEntity.notFound().build();
    }

    
    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (tipoRepository.existsById(id)) {
            tipoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
