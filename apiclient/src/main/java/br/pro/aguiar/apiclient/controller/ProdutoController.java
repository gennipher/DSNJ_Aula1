package br.pro.aguiar.apiclient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import br.pro.aguiar.apiclient.service.ProdutosService;

@Controller
@RequestMapping("/produtos")
@EnableFeignClients(basePackages = "br.pro.aguiar.apiclient.service")
public class ProdutoController {

    @Autowired //injeção de dependencia
    private ProdutosService produtosService;
    
    @GetMapping()
    public String listar() {
        return "produtos/lista";
    }

    @GetMapping("/inserir")
    public String inserir() {
        return "produtos/form";
    }

    @GetMapping("/{id}")
    public String exibir(@PathVariable long id) {
        return "produtos/exibir";
    }

    @GetMapping("{id}/atualizar")
    public String atualizar(@PathVariable long id) {
        return "produtos/form";
    }

    @GetMapping("/{id}/delete")
    public String excluir(@PathVariable long id) {
        return "produtos/lista";
    }
}
