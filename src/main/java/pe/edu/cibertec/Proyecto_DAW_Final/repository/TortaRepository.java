package pe.edu.cibertec.Proyecto_DAW_Final.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.cibertec.Proyecto_DAW_Final.entity.TortaEntity;

@Repository
public interface TortaRepository extends JpaRepository<TortaEntity, Long> {

}
