/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projeto_concessionária.Model;

import java.util.Date;

/**
 *
 * @author gabri
 */
public class MovimentacaoEstoque {
    private final String tipoMovimentacao;
    private final Date dataHora; 
    private final Veiculo veiculo;
    private final Funcionario responsavel;
    
    public MovimentacaoEstoque(String tipoMovimentacao, Veiculo veiculo, Funcionario responsavel) {
        this.tipoMovimentacao = tipoMovimentacao;
        this.dataHora = new Date();
        this.responsavel = responsavel;
        this.veiculo = veiculo;
    }

    public String getTipoMovimentacao() {
        return tipoMovimentacao;
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
    
    
}
