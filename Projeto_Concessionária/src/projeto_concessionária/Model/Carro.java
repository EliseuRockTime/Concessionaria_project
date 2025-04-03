/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projeto_concessionária.Model;

import projeto_concessionária.Model.enums.Status;
import projeto_concessionária.Model.enums.TipoCombustivel;

/**
 *
 * @author Guilherme
 */
public class Carro extends Veiculo{
    
    private Integer quantidadeDeRodas;

    public Carro(String marca, String modelo, String placa, Integer anoFabricacao, String cor, TipoCombustivel tipoCombustivel, Double quilometragem, Status status, String chassi, Carro carro) {
        super(marca, modelo, placa, anoFabricacao, cor, tipoCombustivel, quilometragem, status, chassi);
        this.quantidadeDeRodas = quantidadeDeRodas;
    }
    
    
}
