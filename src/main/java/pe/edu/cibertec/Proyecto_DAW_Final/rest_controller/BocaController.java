package pe.edu.cibertec.Proyecto_DAW_Final.rest_controller;

import pe.edu.cibertec.Proyecto_DAW_Final.entity.BocaditoEntity;
import pe.edu.cibertec.Proyecto_DAW_Final.repository.BocaditoRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/bocaditos") 
@AllArgsConstructor
public class BocaController {

    private final BocaditoRepository bocaditoRepository;

    
    @GetMapping
    public List<BocaditoEntity> list() {
        return bocaditoRepository.findAll();
    }

    
    @GetMapping("{id}")
    public ResponseEntity<BocaditoEntity> findById(@PathVariable Long id) {
        return ResponseEntity.of(bocaditoRepository.findById(id));
    }

    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BocaditoEntity register(@RequestBody BocaditoEntity bocadito) {
        return bocaditoRepository.save(bocadito);
    }

    
    @PutMapping("{id}")
    public ResponseEntity<BocaditoEntity> edit(@PathVariable Long id, @RequestBody BocaditoEntity bocaditoRequest) {
        Optional<BocaditoEntity> optionalBocadito = bocaditoRepository.findById(id);
        if (optionalBocadito.isPresent()) {
            BocaditoEntity bocadito = optionalBocadito.get();
            bocadito.setNombre_boca(bocaditoRequest.getNombre_boca());
            bocadito.setDescripcion_boca(bocaditoRequest.getDescripcion_boca());
            bocadito.setPrecio_boca(bocaditoRequest.getPrecio_boca());
            bocadito.setStock_boca(bocaditoRequest.getStock_boca());
            bocadito.setUrlImagen(bocaditoRequest.getUrlImagen());
            bocaditoRepository.save(bocadito);

            return ResponseEntity.ok(bocadito);
        }
        return ResponseEntity.notFound().build();
    }

    // 5. Eliminar un bocadito
    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (bocaditoRepository.existsById(id)) {
            bocaditoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
