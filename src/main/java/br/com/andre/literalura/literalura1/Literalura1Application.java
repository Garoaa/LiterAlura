package br.com.andre.literalura.literalura1;

import br.com.andre.literalura.literalura1.principal.Principal;
import br.com.andre.literalura.literalura1.repository.RepositoryAutor;
import br.com.andre.literalura.literalura1.repository.RepositoryLivros;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Literalura1Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Literalura1Application.class, args);
	}

	@Autowired
	private RepositoryLivros repositoryLivros;
	@Autowired
	private RepositoryAutor repositoryAutor;

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(repositoryAutor, repositoryLivros);
		principal.exibeMenu();
	}
}
