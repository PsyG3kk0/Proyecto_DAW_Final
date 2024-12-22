package pe.edu.cibertec.Proyecto_DAW_Final.servi;


import java.util.List;

import pe.edu.cibertec.Proyecto_DAW_Final.entity.TortaEntity;


public interface TortaService {
	List<TortaEntity>buscarTodasTortas();
	TortaEntity buscarTortaPorId(long id);
	TortaEntity actualizarStock (Integer id, Integer cantidadComprada);
	TortaEntity guardarTorta(TortaEntity torta);
	void eliminarTorta(long id);
}
