package pe.edu.cibertec.Proyecto_DAW_Final.rest_controller;

import pe.edu.cibertec.Proyecto_DAW_Final.entity.BoletaEntity;
import pe.edu.cibertec.Proyecto_DAW_Final.repository.BoletaRepository;
import pe.edu.cibertec.Proyecto_DAW_Final.repository.PedidoRepository;
import pe.edu.cibertec.Proyecto_DAW_Final.repository.TortaRepository;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/boletas") // Base del endpoint: api/boletas
@AllArgsConstructor
public class BoleController {
	private final BoletaRepository boletaRepository;
    private final PedidoRepository pedidoRepository;
    private final TortaRepository tortaRepository;

    // 1. Listar todas las boletas
    @GetMapping
    public List<BoletaEntity> list() {
        return boletaRepository.findAll();
    }

    // 2. Buscar boleta por ID
    @GetMapping("{id}")
    public ResponseEntity<BoletaEntity> findById(@PathVariable Integer id) {
        return ResponseEntity.of(boletaRepository.findById(id));
    }

    // 3. Registrar una nueva boleta
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<BoletaEntity> register(@RequestBody BoletaEntity boleta) {
        // Validar que el pedido y la torta existen
        if (pedidoRepository.existsById(boleta.getPedidoEntity().getPedidoId()) &&
            tortaRepository.existsById(boleta.getTortaEntity().getIdtorta())) {

            return ResponseEntity.ok(boletaRepository.save(boleta));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    // 4. Editar una boleta existente
    @PutMapping("{id}")
    public ResponseEntity<BoletaEntity> edit(@PathVariable Integer id, @RequestBody BoletaEntity boletaRequest) {
        Optional<BoletaEntity> optionalBoleta = boletaRepository.findById(id);
        if (optionalBoleta.isPresent()) {
            BoletaEntity boleta = optionalBoleta.get();
            boleta.setCantidad(boletaRequest.getCantidad());
            boleta.setTortaEntity(boletaRequest.getTortaEntity());
            boleta.setPedidoEntity(boletaRequest.getPedidoEntity());
            return ResponseEntity.ok(boletaRepository.save(boleta));
        }
        return ResponseEntity.notFound().build();
    }

    // 5. Eliminar una boleta
    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (boletaRepository.existsById(id)) {
            boletaRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
