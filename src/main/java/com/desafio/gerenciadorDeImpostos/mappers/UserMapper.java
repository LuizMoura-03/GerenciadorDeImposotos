package com.desafio.gerenciadorDeImpostos.mappers;

import com.desafio.gerenciadorDeImpostos.controllers.dto.UserRequestDTO;
import com.desafio.gerenciadorDeImpostos.controllers.dto.UserResponseDTO;
import com.desafio.gerenciadorDeImpostos.models.RoleModel;
import com.desafio.gerenciadorDeImpostos.models.UserModel;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UserMapper {
    public UserModel toEntity(UserRequestDTO userRequestDTO, String encodedPassword, Set<RoleModel> roles) {
        return UserModel.builder()
                .userName(userRequestDTO.getUsername())
                .password(encodedPassword)
                .roles(roles)
                .build();
    }

    public UserResponseDTO toResponse(UserModel user) {
        Set<String> roleNames = user.getRoles().stream()
                .map(RoleModel::getName)
                .collect(Collectors.toSet());
        return new UserResponseDTO(user.getId(), user.getUserName(), roleNames);
    }
}
