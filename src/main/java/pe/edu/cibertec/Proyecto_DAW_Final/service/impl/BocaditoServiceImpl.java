package pe.edu.cibertec.Proyecto_DAW_Final.service.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.cibertec.Proyecto_DAW_Final.entity.BocaditoEntity;
import pe.edu.cibertec.Proyecto_DAW_Final.repository.BocaditoRepository;
import pe.edu.cibertec.Proyecto_DAW_Final.servi.BocaditoService;

import jakarta.transaction.Transactional;

@Service
public class BocaditoServiceImpl implements BocaditoService{

    @Autowired
    private BocaditoRepository bocaditoRepository; 

    @Override
    public List<BocaditoEntity> buscarTodosBocaditos() {
        return bocaditoRepository.findAll();
    }

    @Override
    public BocaditoEntity buscarBocaditoPorId(Long id) {
        return bocaditoRepository.findById(id).get();
    }

    @Transactional
    @Override
    public BocaditoEntity actualizarStock(Long id, Integer cantidadComprada) {
        // Lógica para actualizar el stock del producto en la base de datos
        BocaditoEntity bocadito = buscarBocaditoPorId(id);
        int nuevoStock = bocadito.getStock_boca() - cantidadComprada;
        bocadito.setStock_boca(nuevoStock);
        bocaditoRepository.save(bocadito);
        return bocadito;
    }

    @Override
    public BocaditoEntity guardarBocadito(BocaditoEntity bocadito) {
        return bocaditoRepository.save(bocadito);
    }

    @Override
    public void eliminarBocadito(Long id) {
        bocaditoRepository.deleteById(id);
    }
}
