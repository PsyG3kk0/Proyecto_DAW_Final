package pe.edu.cibertec.Proyecto_DAW_Final.repository;

import pe.edu.cibertec.Proyecto_DAW_Final.entity.BoletaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoletaRepository extends JpaRepository<BoletaEntity, Integer> {
}
