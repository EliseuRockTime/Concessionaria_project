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
    List<Veiculo> ListaDeVeiculos = new ArrayList<>();
 
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
            .filter(v -> filtro.getAnoMin() == null || v.getAnoFabricacao() >= filtro.getAnoMin())
            .filter(v -> filtro.getAnoMax() == null || v.getAnoFabricacao() <= filtro.getAnoMax())
            .filter(v -> filtro.getPrecoMin() == null || v.getPreco() >= filtro.getPrecoMin())
            .filter(v -> filtro.getPrecoMax() == null || v.getPreco() <= filtro.getPrecoMax())
            .filter(v -> filtro.getStatus() == null || v.getStatus().equals(filtro.getStatus()))
            .collect(Collectors.toList());

        System.out.println("Veículos filtrados:");
        veiculosFiltrados.forEach(System.out::println);

        return veiculosFiltrados;
    }
    
    public void removerVeiculo(String chassi) { 
        ListaDeVeiculos.removeIf(veiculo -> veiculo.getChassi().equals(chassi));
        System.out.println("Veículo removido com sucesso!");
    }  
}