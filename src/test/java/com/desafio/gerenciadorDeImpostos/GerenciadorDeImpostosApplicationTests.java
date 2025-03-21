package com.desafio.gerenciadorDeImpostos;

import com.desafio.gerenciadorDeImpostos.models.ImpostoModel;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GerenciadorDeImpostosApplicationTests {

	public static void main(String[] args) {
		ImpostoModel imposto = ImpostoModel.builder()
				.name("Imposto de Renda")
				.descricao("Imposto sobre a renda")
				.aliquota(15.0)
				.valorFixoImposto(100.0)
				.build();

		System.out.println(imposto);
	}

}
