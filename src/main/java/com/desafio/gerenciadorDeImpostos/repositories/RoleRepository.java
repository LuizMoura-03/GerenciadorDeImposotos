package com.desafio.gerenciadorDeImpostos.repositories;

import com.desafio.gerenciadorDeImpostos.models.RoleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<RoleModel, Long> {
    @Override
    Optional<RoleModel> findById(Long id);

}
