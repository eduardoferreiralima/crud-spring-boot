package br.ifrn.edu.eduardo.CRUD_in_memory.controller;

import br.ifrn.edu.eduardo.CRUD_in_memory.models.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    private final AtomicLong counter = new AtomicLong();
    private final List<Produto> produtos = new ArrayList<>();

    @GetMapping("/allproducts")
    public List<Produto> getProducts(){
        return produtos;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> getProduct(@PathVariable long id){
        for(Produto produto : produtos){
            if(produto.getId() == id){
                return ResponseEntity.ok(produto);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public Produto create(@RequestBody Produto prod){
        Produto produto = new Produto();
        produto.setId(counter.incrementAndGet());
        produto.setNome(prod.getNome());
        produto.setPreco(prod.getPreco());
        produto.setQuantidade(prod.getQuantidade());
        produtos.add(produto);
        return produto;
    }

    @PutMapping(value = "/{id}")
    public Produto update(@PathVariable long id, @RequestBody Produto prod){
        for(Produto produtoUpdate : produtos){
            if(produtoUpdate.getId() == id){
                produtoUpdate.setId(produtoUpdate.getId());
                produtoUpdate.setNome(prod.getNome());
                produtoUpdate.setPreco(prod.getPreco());
                produtoUpdate.setQuantidade(prod.getQuantidade());
            }
        }
        return prod;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id){
        for(Produto prod : produtos){
            if(prod.getId() == id){
                produtos.remove(prod);
            }
        }
    }
}