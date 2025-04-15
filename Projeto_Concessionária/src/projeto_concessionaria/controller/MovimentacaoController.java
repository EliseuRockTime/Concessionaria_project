/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projeto_concessionaria.controller;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import projeto_concessionaria.Model.Funcionario;
import projeto_concessionaria.Model.MovimentacaoEstoque;
import projeto_concessionaria.Model.Veiculo;
import projeto_concessionaria.model.enums.TipoMovimentacao;
/**
 *
 * @author Elise
 */
public class MovimentacaoController {


    private final List<MovimentacaoEstoque> historico;

    public MovimentacaoController() {
        this.historico = new ArrayList<>();
    }

    public boolean registrarEntrada(Veiculo v, Funcionario responsavel) {
        MovimentacaoEstoque entrada = new MovimentacaoEstoque(TipoMovimentacao.CRIAÇÃO,  v, responsavel);
        return historico.add(entrada);
    }

    public boolean registrarSaida(Veiculo v, Funcionario responsavel) {
        MovimentacaoEstoque saida = new MovimentacaoEstoque(TipoMovimentacao.EXCLUSÃO, v, responsavel);
        return historico.add(saida);
    }

    public List<MovimentacaoEstoque> listaHistorico(Date inicio, Date fim) {
        List<MovimentacaoEstoque> resultado = new ArrayList<>();
        for (MovimentacaoEstoque m : historico) {
            Date data = m.getDataHora();
            if (!data.before(inicio) && !data.after(fim)) {
                resultado.add(m);
            }
        }
        return resultado;
    }

    public List<MovimentacaoEstoque> getHistorico() {
        return historico;
    }
    
    
}



