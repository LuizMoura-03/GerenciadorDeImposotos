package com.desafio.gerenciadorDeImpostos.repositories;

import com.desafio.gerenciadorDeImpostos.models.ImpostoModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class ImpostoRepositoryTest {

    @Autowired
    private ImpostoRepository impostoRepository;

    @Test
    void testSaveAndFindById() {
        ImpostoModel impostoModel = ImpostoModel.builder()
                .name("ICMS")
                .descricao("Imposto sobre circulação de mercadorias")
                .aliquota(18.0)
                .valorFixoImposto(100.0)
                .build();

        ImpostoModel savedImposto = impostoRepository.save(impostoModel);
        Optional<ImpostoModel> foundImposto = impostoRepository.findById(savedImposto.getId());

        assertTrue(foundImposto.isPresent());
        assertEquals("ICMS", foundImposto.get().getName());
        assertEquals("Imposto sobre circulação de mercadorias", foundImposto.get().getDescricao());
        assertEquals(18.0, foundImposto.get().getAliquota());
        assertEquals(100.0, foundImposto.get().getValorFixoImposto());
    }

    @Test
    void testExistsByName() {
        ImpostoModel imposto = ImpostoModel.builder()
                .name("ISS")
                .descricao("Imposto sobre Serviços")
                .aliquota(5.0)
                .valorFixoImposto(50.0)
                .build();
        impostoRepository.save(imposto);

        boolean exists = impostoRepository.existsByName("ISS");
        assertTrue(exists);

    }

    @Test
    void testDeleteById() {
        ImpostoModel imposto = ImpostoModel.builder()
                .name("IPI")
                .descricao("Imposto sobre Produtos Industrializados")
                .aliquota(10.0)
                .valorFixoImposto(200.0)
                .build();
        ImpostoModel savedImposto = impostoRepository.save(imposto);

        impostoRepository.deleteById(savedImposto.getId());
        Optional<ImpostoModel> foundImposto = impostoRepository.findById(savedImposto.getId());
        assertFalse(foundImposto.isPresent());

    }

}
