package br.pro.aguiar.apiclient;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import br.pro.aguiar.apiclient.model.Produto;
import br.pro.aguiar.apiclient.service.ProdutosService;
//import br.pro.aguiar.apiclient.service.ViaCepService;

@SpringBootApplication
@EnableFeignClients
public class ApiclientApplication 
	implements CommandLineRunner {

	/* @Autowired
	private ViaCepService viaCepService; */

	@Autowired
	private ProdutosService produtosService;

	public static void main(String[] args) {
		SpringApplication.run(
			ApiclientApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {

		/* System.out.println("Lista de Produtos");
		List<Produto> produtos = produtosService.listar();
		produtos.forEach(System.out::println); */

		/* System.out.println("Exibir Produtos");
		System.out.println(produtosService.exibir((long) 1)); */

		/* System.out.println("Inserir Produtos");
		Produto produto = new Produto();
		produto.setNome("nome");
		produto.setMarca("marca");
		produtosService.inserir(produto);
		List<Produto> produtos = produtosService.listar();
		produtos.forEach(System.out::println); */

		System.out.println("Atualizar Produtos");
		Produto produto = new Produto();
		produto.setNome("nome");
		produto.setMarca("marca");
		produtosService.atualizar((long) 1, produto);
		List<Produto> produtos = produtosService.listar();
		produtos.forEach(System.out::println);

	}

}
