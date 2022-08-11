package br.pro.aguiar.apiclient.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.pro.aguiar.apiclient.model.Produto;

@FeignClient(
    url = "http://localhost:8081/",
    name = "ProdutosService"
)
public interface ProdutosService {
    
    @GetMapping("produtos")
    List<Produto> listar();
    
    @PostMapping("produtos")
    Produto inserir(@RequestBody Produto produto);

    @GetMapping("produtos/{id}")
    Produto exibir(@PathVariable("id") Long id);

    @PutMapping("produto/{id}")
    Produto atualizar(@PathVariable("id") Long id, @RequestBody Produto produto);

    /* @PatchMapping("produtos/{id}")
    Produto alterar(@PathVariable Long id, @RequestBody Produto produto);
 */
    @DeleteMapping("produtos/{id}")
    Produto remover(@PathVariable("id") Long id);
    
}
