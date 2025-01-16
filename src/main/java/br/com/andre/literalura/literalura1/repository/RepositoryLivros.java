package br.com.andre.literalura.literalura1.repository;

import br.com.andre.literalura.literalura1.model.Livros;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RepositoryLivros extends JpaRepository<Livros, Long> {
    Optional<Livros> findByTituloContainingIgnoreCase(String nomeLivro);

    @Query("SELECT l FROM Livros l WHERE l.lingua ILIKE %:idiomaSelecionado%")
    List<Livros> livrosIdioma(String idiomaSelecionado);
}
