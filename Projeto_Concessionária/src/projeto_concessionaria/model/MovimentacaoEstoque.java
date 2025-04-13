/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projeto_concessionaria.Model;

import java.util.Date;
import projeto_concessionaria.model.enums.TipoMovimentacao;

/**
 *
 * @author gabri
 */
public class MovimentacaoEstoque {
    private final TipoMovimentacao tipo; 
    private final Date dataHora; 
    private final Veiculo veiculo;
    private final Funcionario responsavel;
    
    public MovimentacaoEstoque(TipoMovimentacao tipo, Veiculo veiculo, Funcionario responsavel) {
        this.dataHora = new Date();
        this.responsavel = responsavel;
        this.veiculo = veiculo;
        this.tipo = tipo;
    }

    public Date getDataHora() {
        return dataHora;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public Funcionario getResponsavel() {
        return responsavel;
    }

    public TipoMovimentacao getTipo() {
        return tipo;
    }
    
    
    
}
