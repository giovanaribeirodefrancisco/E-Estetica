package com.estetica.es.repositores;

import com.estetica.es.models.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UusarioRepository extends JpaRepository<UsuarioModel, Integer>  {
    
}
