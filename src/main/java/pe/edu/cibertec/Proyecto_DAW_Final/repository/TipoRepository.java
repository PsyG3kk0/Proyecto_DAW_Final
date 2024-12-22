package pe.edu.cibertec.Proyecto_DAW_Final.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.cibertec.Proyecto_DAW_Final.entity.TipoEntity;

@Repository
public interface TipoRepository  extends JpaRepository<TipoEntity, Integer>{

}
