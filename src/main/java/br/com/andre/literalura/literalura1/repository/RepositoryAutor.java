package br.com.andre.literalura.literalura1.repository;

import br.com.andre.literalura.literalura1.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RepositoryAutor extends JpaRepository<Autor, Long> {

    Optional<Autor> findByNome(String nome);

    @Query("SELECT a FROM Autor a WHERE a.nascimento < %:anoInformado% AND a.falescimento > %:anoInformado%")
    List<Autor> autoresVivos(int anoInformado);
}
