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
public class Moto extends Veiculo {
    private Integer quantidadeDeRodas;

    public Moto(String marca, String modelo, String placa, Integer anoFabricacao, String cor, TipoCombustivel tipoCombustivel, Double quilometragem, Status status, String chassi, double preco) {
        super(marca, modelo, placa, anoFabricacao, cor, tipoCombustivel, quilometragem, status, chassi,preco);
        this.quantidadeDeRodas = quantidadeDeRodas;
    }

    
    
    
}
