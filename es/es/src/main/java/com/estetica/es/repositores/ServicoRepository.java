package com.estetica.es.repositores;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.estetica.es.models.ServicoModel;
import com.estetica.es.models.UsuarioModel;

import java.util.List;


@Repository
public interface ServicoRepository extends JpaRepository<ServicoModel, Integer>   {
        List<ServicoModel> findByPrestadoresContaining(UsuarioModel prestador);

}
