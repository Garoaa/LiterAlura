package br.com.andre.literalura.literalura1.model;

import jakarta.persistence.*;

@Entity
@Table(name = "livros")
public class Livros {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String lingua;
    private Double download;

    @ManyToOne
    private Autor autor;

    public Livros(){}

    public Livros(DadosLivros dadosLivros) {
        this.titulo = dadosLivros.titulo();
        this.lingua = dadosLivros.lingua().get(0);
        this.download = dadosLivros.download();
    }


    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getLingua() {
        return lingua;
    }

    public void setLingua(String lingua) {
        this.lingua = lingua;
    }

    public Double getDownload() {
        return download;
    }

    public void setDownload(Double download) {
        this.download = download;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    @Override
    public String toString() {
        return "titulo='" + titulo + '\'' +
                ", lingua='" + lingua + '\'' +
                ", download=" + download +
                '}';
    }
}
