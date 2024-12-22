package pe.edu.cibertec.Proyecto_DAW_Final.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.cibertec.Proyecto_DAW_Final.entity.TortaEntity;
import pe.edu.cibertec.Proyecto_DAW_Final.repository.TortaRepository;
import pe.edu.cibertec.Proyecto_DAW_Final.servi.TortaService;

import jakarta.transaction.Transactional;

@Service
public class TortaServiceImpl implements TortaService{

	@Autowired
	private TortaRepository tortaRepository; 
	
	@Override
	public List<TortaEntity> buscarTodasTortas() {
		return tortaRepository.findAll();
	}

	@Override
	public TortaEntity buscarTortaPorId(long id) {
		return tortaRepository.findById(id).get();
	}

	@Transactional
	public TortaEntity actualizarStock(Integer id, Integer cantidadComprada) {
		// Lógica para actualizar el stock del producto en la base de datos
        TortaEntity torta = buscarTortaPorId(id);
        int nuevoStock = torta.getStock() - cantidadComprada;
        torta.setStock(nuevoStock);
        tortaRepository.save(torta);
		return torta;
	}

	@Override
	public TortaEntity guardarTorta(TortaEntity torta) {
		// TODO Auto-generated method stub
		return tortaRepository.save(torta);
	}

	@Override
	public void eliminarTorta(long id) {
		tortaRepository.deleteById(id);
		
	}
}
