/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projeto_concessionaria.Model;

import projeto_concessionaria.model.enums.Status;


/**
 *
 * @author gabri
 */
public class FiltroBusca {
    private String marca;
    private String modelo;
    private Integer anoMin;
    private Integer anoMax;
    private Status status;
    private Double precoMin;
    private Double precoMax;

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

    public Integer getAnoMin() {
        return anoMin;
    }

    public void setAnoMin(int anoMin) {
        this.anoMin = anoMin;
    }

    public Integer getAnoMax() {
        return anoMax;
    }

    public void setAnoMax(int anoMax) {
        this.anoMax = anoMax;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Double getPrecoMin() {
        return precoMin;
    }

    public void setPrecoMin(double precoMin) {
        this.precoMin = precoMin;
    }

    public Double getPrecoMax() {
        return precoMax;
    }

    public void setPrecoMax(double precoMax) {
        this.precoMax = precoMax;
    }


    
    
    
}
