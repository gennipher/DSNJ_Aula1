package br.pro.aguiar.apiclient.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.pro.aguiar.apiclient.model.Endereco;

@FeignClient(
    url = "https://viacep.com.br/ws/", 
    name = "ViaCepService")
public interface ViaCepService {

    @GetMapping("{cep}/json")
    Endereco buscarEnderecoPorCep(
        @PathVariable("cep") String cep);
}
