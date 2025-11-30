package br.com.alura.projeto_tabela_fipe.service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsumoApi {

    private HttpClient client = HttpClient.newHttpClient();

    public String obterDados(String endereco) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(endereco))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            return response.body();

        } catch (Exception e) {
            System.out.println("Erro ao consultar API: " + e.getMessage());
            return null;
        }
    }
}
