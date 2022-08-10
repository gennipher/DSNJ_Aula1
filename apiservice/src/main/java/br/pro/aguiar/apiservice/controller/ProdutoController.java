package br.pro.aguiar.apiservice.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.pro.aguiar.apiservice.model.Produto;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private List<Produto> produtos = new ArrayList<>();

    @GetMapping
    public ResponseEntity listar() {
        if (produtos.isEmpty())
            return ResponseEntity.ok().body("Lista vazia.");
        return ResponseEntity.ok().body(produtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity exibir(@PathVariable long id) {
        for (Produto p : produtos){
            if (p.getId() == id){
                return ResponseEntity
                    .ok()
                    .body(p);
            }
        }
        return ResponseEntity
            .status(404)
            .body("Nenhum produto encontrado.");
    }

    @PostMapping
    public ResponseEntity inserir(@RequestBody Produto produto) {
        if (produto.getNome() == null || produto.getMarca() == null)
            return ResponseEntity.status(400).body("Dados incorretos.");
    
        var lastIndex = 0L;
        if (!produtos.isEmpty())
            lastIndex = produtos.get(produtos.size() - 1).getId();
        produto.setId(lastIndex + 1);
        
        if (produtos.add(produto))
            return ResponseEntity.status(201).body(produto);
        return ResponseEntity.status(500).body("Erro interno.");
    }

    @PutMapping("/{id}")
    public ResponseEntity atualizar(@PathVariable Long id, @RequestBody Produto produto) {
        for (Produto p : produtos) {
            if (p.getId() == id) {
                var nome = produto.getNome();
                if (nome != null)
                    p.setNome(nome);
                var marca = produto.getMarca();
                if (marca != null)
                    p.setMarca(marca);

                return ResponseEntity.ok().body(p);
            }
        }
        return ResponseEntity.status(500).body("Erro interno.");
    }

    @PatchMapping("/{id}")
    public ResponseEntity alterar(@PathVariable Long id, @RequestBody Produto produto) {
        for (Produto p : produtos) {
            if (p.getId() == id) {
                var nome = produto.getNome();
                p.setNome(nome);
                var marca = produto.getMarca();
                p.setMarca(marca);
                return ResponseEntity.ok().body(p);
            }
        }
        return ResponseEntity.status(500).body("Erro interno.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity remover(@PathVariable Long id) {
        for (Produto p : produtos) {
            if (p.getId() == id) {
                produtos.remove(p);
                return ResponseEntity.ok().body(p);
            }
        }
        return ResponseEntity.status(500).body("Erro interno.");
    }
}
