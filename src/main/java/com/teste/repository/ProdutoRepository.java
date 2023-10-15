package com.teste.repository;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;
import com.teste.model.Produto;

@Repository
public class ProdutoRepository {

    private List <Produto> produtos = new ArrayList<Produto>();
    private Integer ultimoId = 0;

    /**
     * Metodo para Retorna uma lista de produtos
     * @return lista de produtos
     */
    public List<Produto> obterTodos(){
        return produtos;
    }

    /**
     * Metodo que retorna o produto encontrato pelo seu ID
     * @param id do produto que será localizado
     * @return Retorna um produto caso seja encontrato
     */
    public Optional <Produto> obterPorId(Integer id){
        return produtos
        .stream()
        .filter(produto -> produto.getId() == id)
        .findFirst();
    }

    /**
     * Metodo para adicionar produto na lista.
     * @param produto que será adicionado.
     * @return produto que foi adicionado na lista.
     */
    public Produto adicionar (Produto produto){
        
        ultimoId++;

        produto.setId(ultimoId);
        produtos.add(produto);

        return produto;
    }

    /**
     * Metodo para deletar o produto por id
     * @param id do produto a ser deletado
     */
    public void deletar(Integer id){
        //Remova para mim, se o id que está passando é igual ao id que temos aqui dentro
        produtos.removeIf(produto -> produto.getId() == id);
    }

    /**
     * Metodo para atualizar o produto na lista.
     * @param produto que será atualizado.
     * @return Retorna o produto após atualizar a lista.
     */
    public Produto atualizar(Produto produto){
        //encontrar o produto na lista.
        Optional<Produto> produtoEncontrato =  obterPorId(produto.getId());
        if(produtoEncontrato.isEmpty()){
            throw new InputMismatchException("Produto não encontrado");
        }
        //Preciso remover o produto antigo da lista.
        deletar(produto.getId());
        
        //Depois adicionar o produto atualizado na lista.
        produtos.add(produto);

        return produto;
    }
}
