package com.desafio.gerenciadorDeImpostos.repositories;

import com.desafio.gerenciadorDeImpostos.models.RoleModel;
import com.desafio.gerenciadorDeImpostos.models.UserModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Test
    void testSaveAndFindById() {
        RoleModel role = new RoleModel();
        role.setName("ADMIN");

        UserModel userModel = new UserModel();
        userModel.setName("Luiz Moura");
        userModel.setPassword("password123");
        userModel.setRoles(Collections.singleton(role)); // Adiciona um papel ao usuario

        UserModel savedUser = userRepository.save(userModel);
        Optional<UserModel> foundUser = userRepository.findById(savedUser.getId());

        assertTrue(foundUser.isPresent());
        assertEquals("Luiz Moura", foundUser.get().getName());
        assertEquals("password123", foundUser.get().getPassword());
        assertEquals(1, foundUser.get().getRoles().size());
        assertEquals("ADMIN", foundUser.get().getRoles().iterator().next().getName());

    }

    @Test
    void testFindByName() {

        RoleModel roleModel = new RoleModel();
        roleModel.setName("USER");

        roleModel = roleRepository.save(roleModel);

        UserModel userModel = new UserModel();
        userModel.setName("Luiz Moura");
        userModel.setPassword("password123");
        userModel.setRoles(Collections.singleton(roleModel)); // Sem papeis associados

        userRepository.save(userModel);

        Optional<UserModel> foundUser = userRepository.findByName("Luiz Moura");

        assertTrue(foundUser.isPresent());
        assertEquals("Luiz Moura", foundUser.get().getName());
        assertEquals(1, foundUser.get().getRoles().size());
        assertEquals("USER", foundUser.get().getRoles().iterator().next().getName());

    }

    @Test
    void testExistsByName() {

        RoleModel roleModel = new RoleModel();
        roleModel.setName("USER");
        roleModel = roleRepository.save(roleModel);

        UserModel userModel = new UserModel();
        userModel.setName("Luiz Moura");
        userModel.setPassword("password123");
        userModel.setRoles(Collections.singleton(roleModel));
        userRepository.save(userModel);

        boolean exists = userRepository.existsByName("Luiz Moura");
        assertTrue(exists);

        boolean notExists = userRepository.existsByName("NonExistentName");
        assertTrue(!notExists);

    }


}
