package br.com.andre.literalura.literalura1.service;


import br.com.andre.literalura.literalura1.model.DadosLivros;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record RetornoAPI (@JsonAlias("results")List<DadosLivros> retornoApi) {
}
