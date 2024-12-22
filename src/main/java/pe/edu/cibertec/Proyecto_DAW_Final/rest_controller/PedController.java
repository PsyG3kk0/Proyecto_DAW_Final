package pe.edu.cibertec.Proyecto_DAW_Final.rest_controller;
import pe.edu.cibertec.Proyecto_DAW_Final.entity.PedidoEntity;
import pe.edu.cibertec.Proyecto_DAW_Final.entity.UsuarioEntity;
import pe.edu.cibertec.Proyecto_DAW_Final.repository.PedidoRepository;
import pe.edu.cibertec.Proyecto_DAW_Final.repository.UsuarioRepository;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/pedidos") // Base del endpoint: api/pedidos
@AllArgsConstructor
public class PedController {
	private final PedidoRepository pedidoRepository;
    private final UsuarioRepository usuarioRepository;

    // 1. Listar todos los pedidos
    @GetMapping
    public List<PedidoEntity> list() {
        return pedidoRepository.findAll();
    }

    // 2. Buscar pedido por ID
    @GetMapping("{id}")
    public ResponseEntity<PedidoEntity> findById(@PathVariable Long id) {
        return ResponseEntity.of(pedidoRepository.findById(id));
    }

    // 3. Registrar un nuevo pedido
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<PedidoEntity> register(@RequestBody PedidoEntity pedido) {
        // Validar que el usuario existe
        if (usuarioRepository.existsById(pedido.getUsuarioEntity().getCodigo())) {
            pedido.setFechaCompra(LocalDate.now()); // Asignar la fecha actual automáticamente
            return ResponseEntity.ok(pedidoRepository.save(pedido));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    // 4. Editar un pedido existente
    @PutMapping("{id}")
    public ResponseEntity<PedidoEntity> edit(@PathVariable Long id, @RequestBody PedidoEntity pedidoRequest) {
        Optional<PedidoEntity> optionalPedido = pedidoRepository.findById(id);
        if (optionalPedido.isPresent()) {
            PedidoEntity pedido = optionalPedido.get();
            pedido.setFechaCompra(pedidoRequest.getFechaCompra());
            pedido.setUsuarioEntity(pedidoRequest.getUsuarioEntity());
            pedido.setBoleta(pedidoRequest.getBoleta());
            return ResponseEntity.ok(pedidoRepository.save(pedido));
        }
        return ResponseEntity.notFound().build();
    }

    // 5. Eliminar un pedido
    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (pedidoRepository.existsById(id)) {
            pedidoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
