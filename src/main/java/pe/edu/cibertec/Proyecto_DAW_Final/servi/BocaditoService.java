package pe.edu.cibertec.Proyecto_DAW_Final.servi;

import java.util.List;

import pe.edu.cibertec.Proyecto_DAW_Final.entity.BocaditoEntity;


public interface BocaditoService {
    List<BocaditoEntity> buscarTodosBocaditos();
    BocaditoEntity buscarBocaditoPorId(Long id);
    BocaditoEntity actualizarStock(Long id, Integer cantidadComprada);
    BocaditoEntity guardarBocadito(BocaditoEntity bocadito);
    void eliminarBocadito(Long id);
}
