package br.com.andre.literalura.literalura1.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Integer nascimento;
    private Integer falescimento;

    @OneToMany(mappedBy = "autor", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private List<Livros> livros = new ArrayList<>();

    public Autor() {}

    public Autor(DadosAutor dadosAutor) {
        this.nome = dadosAutor.nome();
        this.nascimento = dadosAutor.nascimento();
        this.falescimento = dadosAutor.falescimento();
    }

    public Autor pegaAutor(DadosLivros dadosLivros){
        DadosAutor dadosAutor = dadosLivros.autores().get(0);
        return new Autor(dadosAutor);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getNascimento() {
        return nascimento;
    }

    public void setNascimento(Integer nascimento) {
        this.nascimento = nascimento;
    }

    public Integer getFalescimento() {
        return falescimento;
    }

    public void setFalescimento(Integer falescimento) {
        this.falescimento = falescimento;
    }

    public List<Livros> getLivros() {
        return livros;
    }

    public void setLivros(List<Livros> livros) {
        this.livros = livros;
    }

    @Override
    public String toString() {

        String nomeLivros = livros.stream().map(Livros::getTitulo).collect(Collectors.joining(", "));
        return "id=" + id +
                ", autor = " + nome +
                ", nascimento = " + nascimento +
                ", falescimento = " + falescimento +
                ", livros = " + nomeLivros;
    }
}
