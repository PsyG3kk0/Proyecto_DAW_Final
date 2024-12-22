package pe.edu.cibertec.Proyecto_DAW_Final.rest_controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import pe.edu.cibertec.Proyecto_DAW_Final.entity.TortaEntity;
import pe.edu.cibertec.Proyecto_DAW_Final.repository.TortaRepository;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/tortas")
@AllArgsConstructor
public class TorController {
	private final TortaRepository tortaRepository;


    @GetMapping
    public List<TortaEntity> list() {
        return tortaRepository.findAll();
    }


    @GetMapping("{id}")
    public ResponseEntity<TortaEntity> findById(@PathVariable Long id) {
        return ResponseEntity.of(tortaRepository.findById(id));
    }

 
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public TortaEntity register(@RequestBody TortaEntity torta) {
        return tortaRepository.save(torta);
    }

    
    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (tortaRepository.existsById(id)) {
            tortaRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    
    @PutMapping("{id}")
    public ResponseEntity<TortaEntity> edit(@PathVariable Long id, @RequestBody TortaEntity tortaRequest) {
        Optional<TortaEntity> tortaOptional = tortaRepository.findById(id);
        if (tortaOptional.isPresent()) {
            TortaEntity torta = tortaOptional.get();
            torta.setNombre(tortaRequest.getNombre());
            torta.setDescripcion(tortaRequest.getDescripcion());
            torta.setStock(tortaRequest.getStock());
            torta.setPrecio(tortaRequest.getPrecio());
            torta.setUrlImagen(tortaRequest.getUrlImagen());
            tortaRepository.save(torta);

            return ResponseEntity.ok(torta);
        }

        return ResponseEntity.notFound().build();
    }
}
