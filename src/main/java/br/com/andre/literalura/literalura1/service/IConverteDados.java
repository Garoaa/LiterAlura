package br.com.andre.literalura.literalura1.service;

public interface IConverteDados {
    <T> T  obterDados(String json, Class<T> classe);
}
