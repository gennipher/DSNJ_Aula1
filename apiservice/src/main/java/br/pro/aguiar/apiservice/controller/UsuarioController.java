package br.pro.aguiar.apiservice.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.http.RequestEntity;
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

import br.pro.aguiar.apiservice.model.Usuario;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private List<Usuario> usuarios = new ArrayList<>();

    @GetMapping
    public ResponseEntity listar() {
        if (usuarios.isEmpty())
            return ResponseEntity.ok().body("Lista Vazia");
        return ResponseEntity.ok().body(usuarios);
    }

    @PostMapping()
    public ResponseEntity inserir(@RequestBody Usuario usuario) {
        if (usuario.getEmail() == null || usuario.getSenha() == null)
            return ResponseEntity.status(400)
                    .body("Email e Senha são obrigatórios.");
        var lastId = 0L;
        if (!usuarios.isEmpty())
            lastId = usuarios.get(usuarios.size() - 1).getId();
        usuario.setId(lastId + 1);
        if (usuarios.add(usuario))
            return ResponseEntity.ok().body(usuario);
        return ResponseEntity.status(500)
                .body("Erro interno.");
    }

    @GetMapping("/{id}")
    public ResponseEntity exibir(@PathVariable("id") int id) {
        if (usuarios.isEmpty())
            return ResponseEntity.status(404)
                    .body("Usuário não encontrado.");
        for (Usuario usuario : usuarios) {
            if (usuario.getId() == id)
            return ResponseEntity.ok().body(usuario);
        }
        return ResponseEntity.status(500)
                .body("Erro interno.");
    }

    @PutMapping("/{id}")
    public ResponseEntity atualizar(@PathVariable("id") int id,
            @RequestBody Usuario usuario) {
        for (Usuario u : usuarios) {
            if (u.getId() == id) {
                var nome = usuario.getNome();
                if (nome != null)
                    u.setNome(nome);
                var email = usuario.getEmail();
                if (email != null)
                    u.setEmail(email);
                var senha = usuario.getSenha();
                if (senha != null)
                    u.setSenha(senha);
                var perfil = usuario.getPerfil();
                if (perfil != null)
                    u.setPerfil(perfil);
                return ResponseEntity.ok().body(u);
            }
        }
        return ResponseEntity.status(500).body("Erro interno.");
    }

    @PatchMapping("/{id}")
    public ResponseEntity alterar(@PathVariable("id") int id,
            @RequestBody Usuario usuario) {
        for (Usuario u : usuarios) {
            if (u.getId() == id) {
                u.setNome(usuario.getNome());
                u.setPerfil(usuario.getPerfil());
                var email = usuario.getEmail();
                if (email != null)
                    u.setEmail(email);
                var senha = usuario.getSenha();
                if (senha != null)
                    u.setSenha(senha);
                return ResponseEntity.ok().body(u);
            }
        }
        return ResponseEntity.status(500).body("Erro interno.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity remover(@PathVariable("id") int id) {
        for (Usuario usuario : usuarios) {
            if (usuario.getId() == id) {
                usuarios.remove(usuario);
                return ResponseEntity.ok().body(usuarios);
            }
        }
        return ResponseEntity.status(500)
                .body("Erro interno.");
    }
}
