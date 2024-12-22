package pe.edu.cibertec.Proyecto_DAW_Final.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.cibertec.Proyecto_DAW_Final.entity.UsuarioEntity;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Integer> {
	UsuarioEntity findByCodigo(Integer codigo);
	
	UsuarioEntity findByUsuarioAndClave(String usuario, String clave);
	
	UsuarioEntity findByUsuario(String usuario);
}

