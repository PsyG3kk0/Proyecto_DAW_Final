package pe.edu.cibertec.Proyecto_DAW_Final.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.cibertec.Proyecto_DAW_Final.entity.BocaditoEntity;

@Repository
public interface BocaditoRepository extends JpaRepository<BocaditoEntity, Long> {

}
