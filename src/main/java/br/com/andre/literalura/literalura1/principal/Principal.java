package br.com.andre.literalura.literalura1.principal;

import br.com.andre.literalura.literalura1.model.Autor;
import br.com.andre.literalura.literalura1.model.DadosLivros;
import br.com.andre.literalura.literalura1.model.Livros;
import br.com.andre.literalura.literalura1.repository.RepositoryAutor;
import br.com.andre.literalura.literalura1.repository.RepositoryLivros;
import br.com.andre.literalura.literalura1.service.ConsumoApi;
import br.com.andre.literalura.literalura1.service.ConverteDados;
import br.com.andre.literalura.literalura1.service.RetornoAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Component
public class Principal {

    private RepositoryAutor repositoryAutor;
    private RepositoryLivros repositoryLivros;

    private Scanner scanner = new Scanner(System.in);
    private ConsumoApi consumo = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();

    private final String ENDERECO = "https://gutendex.com/books?search=";

    private List<Livros> livros = new ArrayList<>();
    private List<Autor> autores = new ArrayList<>();

    @Autowired
    public Principal(RepositoryAutor repositoryAutor, RepositoryLivros repositoryLivros) {
        this.repositoryAutor = repositoryAutor;
        this.repositoryLivros = repositoryLivros;
    }

    public void exibeMenu(){
        var opcao = -1;
        while (opcao != 0) {
            var menu = """
                    ______________________________________________
                    Escolha uma opção:
                    
                    1 - Buscar livro pelo titulo
                    2 - Listar livros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos em um determinado ano
                    5 - Listar livros em um determinado idioma
                    
                    0 - Sair
                    ______________________________________________
                    """;

            System.out.println(menu);
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    buscarLivro();
                    break;
                case 2:
                    listarLivros();
                    break;
                case 3:
                    listarAutores();
                    break;
                case 4:
                    listarAutoresVivos();
                    break;
                case 5:
                    listarLivrosEmUmIdioma();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }


    private void buscarLivro() {
        DadosLivros dadosLivros = getDadosLivros();

        Optional<Livros> livro = repositoryLivros.findByTituloContainingIgnoreCase(dadosLivros.titulo());
        if (livro.isPresent()){
            System.out.println("Livro já cadastrado no banco de dados!");
            listarLivros();
        }

        Autor nomeAutor = new Autor().pegaAutor(dadosLivros);
        Optional<Autor> autorExistente = repositoryAutor.findByNome(nomeAutor.getNome());
        if (autorExistente.isPresent()){
            nomeAutor = autorExistente.get();
        } else {
            nomeAutor = repositoryAutor.save(nomeAutor);
        }

        Livros livros = new Livros(dadosLivros);
        livros.setAutor(nomeAutor);
        nomeAutor.getLivros().add(livros);
        repositoryLivros.save(livros);
        System.out.println(livros);
    }

    private void listarLivros() {
        livros = repositoryLivros.findAll();
        livros.forEach(System.out::println);
    }

    private DadosLivros getDadosLivros() {
        System.out.println("Digite o nome de um livro: ");
        var nomeLivro = scanner.nextLine();
        var json = consumo.obterDados(ENDERECO + nomeLivro.toLowerCase().replace(" ", "%20"));
        RetornoAPI retornoAPI = conversor.obterDados(json, RetornoAPI.class);
        var dadosFinais = retornoAPI.retornoApi().get(0);

        return dadosFinais;
    }

    private void listarAutores() {
        autores = repositoryAutor.findAll();
        autores.forEach(System.out::println);
    }


    private void listarAutoresVivos() {

        System.out.println("Digite um ano: ");
        var anoInformado = scanner.nextInt();
        List<Autor> autoresVivos = repositoryAutor.autoresVivos(anoInformado);
        if (autoresVivos.isEmpty()){
            System.out.println("Nenhum autor encontrado!");
        } else {
            System.out.println("Autores encontrados: ");
            autoresVivos.forEach(System.out::println);
        }
    }

    private void listarLivrosEmUmIdioma() {
        System.out.println("""
                _______________________
                Selecione um idioma:
                
                pt - Português
                en - Inglês
                fr - Francês
                es - Espanhol
                _______________________
                """);
        var idiomaSelecionado = scanner.nextLine();
        List<Livros> livrosIdioma = repositoryLivros.livrosIdioma(idiomaSelecionado);
        if (livrosIdioma.isEmpty()){
            System.out.println("Nenhum livro encontrado!");
        } else {
            System.out.println("Livros encontrados: ");
            livrosIdioma.forEach(System.out::println);
        }

    }

}
