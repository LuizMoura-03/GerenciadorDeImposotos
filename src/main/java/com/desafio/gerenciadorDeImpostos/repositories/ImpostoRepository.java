package com.desafio.gerenciadorDeImpostos.repositories;

import com.desafio.gerenciadorDeImpostos.models.ImpostoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImpostoRepository extends JpaRepository<ImpostoModel, Long> {
    boolean existsByNome(String name);
}
