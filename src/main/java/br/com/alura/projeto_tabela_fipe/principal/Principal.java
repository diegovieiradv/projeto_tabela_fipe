package br.com.alura.projeto_tabela_fipe.principal;

import br.com.alura.projeto_tabela_fipe.model.Dados;
import br.com.alura.projeto_tabela_fipe.service.ConsumoApi;
import br.com.alura.projeto_tabela_fipe.service.ConverteDados;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Principal {

    private Scanner leitura = new Scanner(System.in);
    private ConsumoApi consumo = new ConsumoApi();
    private final String URL_BASE = "https://parallelum.com.br/fipe/api/v1/";
    private final Gson gson = new Gson();
    private ConverteDados conversor = new ConverteDados();

    public void exibeMenu() {

        while (true) {  // <-- O WHILE ABRE AQUI

            System.out.println("""
                Seja bem vindo à Tabela FIPE
                Escolha uma das opções abaixo:
                1- Carro
                2- Moto
                3- Caminhão
                0- Sair
                Digite a opção desejada:
                """);

            var opcao = leitura.nextLine();
            String tipo = "";

            // ------------------------------
            // SWITCH SEM ERRO, DENTRO DO WHILE
            // ------------------------------
            switch (opcao) {
                case "1": tipo = "carros"; break;
                case "2": tipo = "motos"; break;
                case "3": tipo = "caminhoes"; break;
                case "0":
                    System.out.println("Encerrando...");
                    return;   // <-- AGORA O RETURN ESTÁ CORRETO
                default:
                    System.out.println("Opção inválida");
                    continue; // <-- AGORA O CONTINUE ESTÁ CORRETO
            }

            // 1 - LISTA MARCAS
            var marcasJson = consumo.obterDados(URL_BASE + tipo + "/marcas");

            var listType = new TypeToken<List<Map<String, String>>>(){}.getType();
            List<Map<String, String>> marcas = gson.fromJson(marcasJson, listType);

            if (marcas == null) {
                System.out.println("Erro ao carregar marcas.");
                continue;
            }

            System.out.println("\nMarcas disponíveis:");
            marcas.forEach(m -> System.out.println(m.get("codigo") + " - " + m.get("nome")));

            System.out.print("\nDigite o código da marca: ");
            var marcaEscolhida = leitura.nextLine();

            // 2 - LISTA MODELOS
            var modelosJson = consumo.obterDados(URL_BASE + tipo + "/marcas/" + marcaEscolhida + "/modelos");

            Map<String, List<Map<String, String>>> modelosMap =
                    gson.fromJson(modelosJson, new TypeToken<Map<String, List<Map<String, String>>>>(){}.getType());

            var modelos = modelosMap.get("modelos");

            System.out.println("\nModelos disponíveis:");
            modelos.forEach(m -> System.out.println(m.get("codigo") + " - " + m.get("nome")));

            System.out.print("\nDigite o código ou nome do modelo: ");
            var modeloEscolhido = leitura.nextLine();

            // 3 - LISTA ANOS
            var anosJson = consumo.obterDados(
                    URL_BASE + tipo + "/marcas/" + marcaEscolhida + "/modelos/" + modeloEscolhido + "/anos"
            );

            List<Map<String, String>> anos =
                    gson.fromJson(anosJson, new TypeToken<List<Map<String, String>>>(){}.getType());

            System.out.println("\nAnos disponíveis:");
            anos.forEach(a -> System.out.println(a.get("codigo")));

            System.out.print("\nDigite o código do ano: ");
            var anoEscolhido = leitura.nextLine();

            // 4 - BUSCA VALOR FINAL
            var valorJson = consumo.obterDados(
                    URL_BASE + tipo + "/marcas/" + marcaEscolhida + "/modelos/" + modeloEscolhido + "/anos/" + anoEscolhido
            );



            System.out.println("\n===== RESULTADO COMPLETO =====");
            System.out.println(valorJson);

            System.out.println("\nPressione ENTER para voltar ao menu.");
            leitura.nextLine();
        } // <-- O WHILE FECHA AQUI

    }
}
