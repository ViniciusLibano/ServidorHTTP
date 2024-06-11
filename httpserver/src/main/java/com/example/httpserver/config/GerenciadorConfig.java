package com.example.httpserver.config;

import com.example.httpserver.util.Json;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class GerenciadorConfig {
    
    private static GerenciadorConfig gerenciadorConfig;
    private static Configuracao configuracao;

    private GerenciadorConfig(){}

    public static GerenciadorConfig Instancia() {
        if (gerenciadorConfig==null)
            gerenciadorConfig = new GerenciadorConfig();
        return gerenciadorConfig;
    }

    public void carregarArquivoConfig(String path) throws HttpConfigurationException {
        FileReader fr = null;
        
        try {
            fr = new FileReader(path);
        } catch (FileNotFoundException e) {
            throw new HttpConfigurationException(e);
        }
        StringBuffer sb = new StringBuffer();
        
        try {
            int i;
            while((i = fr.read()) != -1) {
                sb.append((char)i);
            }
            fr.close();
        } catch (IOException e) {
            throw new HttpConfigurationException(e);
        }

        
        
        JsonNode conf = null;
        try {
            conf = Json.parse(sb.toString());
        } catch (IOException e) {
            throw new HttpConfigurationException("Erro ao realizar Parsing do Arquivo JSON de configura��o", e);
        }
        
        try {
            configuracao = Json.fromJson(conf, Configuracao.class);
        } catch (JsonProcessingException e) {
            throw new HttpConfigurationException("Erro ao realizar Parsing do Arquivo JSON de configura��o, erro interno", e);
        }

        
    }

    public Configuracao configuracaoAtual() throws HttpConfigurationException {
        if (configuracao == null) {
            throw new HttpConfigurationException("Atualmente sem configura��o definida");
        }
        return configuracao;
    }
}
