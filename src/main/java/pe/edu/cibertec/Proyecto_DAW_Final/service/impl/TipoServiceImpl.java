package pe.edu.cibertec.Proyecto_DAW_Final.service.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.cibertec.Proyecto_DAW_Final.entity.TipoEntity;
import pe.edu.cibertec.Proyecto_DAW_Final.repository.TipoRepository;
import pe.edu.cibertec.Proyecto_DAW_Final.servi.TipoService;

@Service
public class TipoServiceImpl implements TipoService{

	@Autowired
	private TipoRepository tipoRepository;
	
	@Override
	public List<TipoEntity> findAllTipos() {
		// TODO Auto-generated method stub
		return tipoRepository.findAll();
	}
}
