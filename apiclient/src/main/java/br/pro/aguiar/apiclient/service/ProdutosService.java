package br.pro.aguiar.apiclient.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import br.pro.aguiar.apiclient.model.Produto;

@FeignClient(
    url = "http://localhost:8081/",
    name = "ProdutosService"
)
public interface ProdutosService {
    
    @GetMapping("produtos")
    List<Produto> listar();
    
}
