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
public class Veiculo {
    private String marca;
    private String modelo;
    private String placa;
    private Integer anoFabricacao;
    private String cor;
    private TipoCombustivel tipoCombustivel;
    private Double quilometragem;
    private Status status;
    private String chassi;
    
    private Carro carro;
    private Moto moto;

    public Veiculo(String marca, String modelo, String placa, Integer anoFabricacao, String cor, TipoCombustivel tipoCombustivel, Double quilometragem, Status status, String chassi, Carro carro, Moto moto) {
        this.marca = marca;
        this.modelo = modelo;
        this.placa = placa;
        this.anoFabricacao = anoFabricacao;
        this.cor = cor;
        this.tipoCombustivel = tipoCombustivel;
        this.quilometragem = quilometragem;
        this.status = status;
        this.chassi = chassi;
        this.carro = carro;
        this.moto = moto;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public Integer getAnoFabricacao() {
        return anoFabricacao;
    }

    public void setAnoFabricacao(Integer anoFabricacao) {
        this.anoFabricacao = anoFabricacao;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public TipoCombustivel getTipoCombustivel() {
        return tipoCombustivel;
    }

    public void setTipoCombustivel(TipoCombustivel tipoCombustivel) {
        this.tipoCombustivel = tipoCombustivel;
    }

    public Double getQuilometragem() {
        return quilometragem;
    }

    public void setQuilometragem(Double quilometragem) {
        this.quilometragem = quilometragem;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getChassi() {
        return chassi;
    }

    public void setChassi(String chassi) {
        this.chassi = chassi;
    }
    
    
    
    
}
