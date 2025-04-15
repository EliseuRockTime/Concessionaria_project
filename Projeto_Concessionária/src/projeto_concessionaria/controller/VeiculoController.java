/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projeto_concessionaria.controller;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import projeto_concessionaria.Model.FiltroBusca;
import projeto_concessionaria.Model.Veiculo;

/**
 *
 * @author gabri
 */
public class VeiculoController {
    private static List<Veiculo> ListaDeVeiculos = new ArrayList<>();
 
    public void cadastrarVeiculo(Veiculo v) {
        ListaDeVeiculos.add(v);
        System.out.println("Veículo cadastrado com sucesso!");
    }
    
    public List<Veiculo> ListarVeiculos() {
        for (Veiculo v : ListaDeVeiculos) {
            System.out.println(v);
        }
        return ListaDeVeiculos;
    }

    public List<Veiculo> ListarVeiculosFiltrados(FiltroBusca filtro) {
        List<Veiculo> veiculosFiltrados = ListaDeVeiculos.stream()
                .filter(v -> filtro.getMarca() == null || v.getMarca().equalsIgnoreCase(filtro.getMarca()))
                .filter(v -> filtro.getModelo() == null || v.getModelo().equalsIgnoreCase(filtro.getModelo()))
                .filter(v -> {
                    Integer anoMin = filtro.getAnoMin();
                    return anoMin == null || v.getAnoFabricacao() >= anoMin;
                })
                .filter(v -> {
                    Integer anoMax = filtro.getAnoMax();
                    return anoMax == null || v.getAnoFabricacao() <= anoMax;
                })
                .filter(v -> {
                    Double precoMin = filtro.getPrecoMin();
                    return precoMin == null || v.getPreco() >= precoMin;
                })
                .filter(v -> {
                    Double precoMax = filtro.getPrecoMax();
                    return precoMax == null || v.getPreco() <= precoMax;
                })
                .filter(v -> filtro.getStatus() == null || v.getStatus().equals(filtro.getStatus()))
                .collect(Collectors.toList());

        System.out.println("Veículos filtrados:");
        veiculosFiltrados.forEach(System.out::println);

        return veiculosFiltrados;
    }

    
    public void alterarVeiculo(Veiculo veiculoAlterado) {
    for (int i = 0; i < ListaDeVeiculos.size(); i++) {
        Veiculo v = ListaDeVeiculos.get(i);
        if (v.getChassi().equals(veiculoAlterado.getChassi())) {
            ListaDeVeiculos.set(i, veiculoAlterado);
            System.out.println("Veículo alterado com sucesso!");
            return;
        }
    }
    System.out.println("Veículo não encontrado!");
}
    
    public Veiculo buscarVeiculoPorChassi(String chassi) {
    for (Veiculo v : ListaDeVeiculos) {
        System.out.println("Verificando chassi: " + v.getChassi());
        if (v.getChassi().equals(chassi)) {
            return v;
        }
    }
    System.out.println("Nenhum veículo com chassi: " + chassi);
    return null;
}
    
    public void removerVeiculo(String chassi) { 
        ListaDeVeiculos.removeIf(veiculo -> veiculo.getChassi().equals(chassi));
        System.out.println("Veículo removido com sucesso!");
    }  
}