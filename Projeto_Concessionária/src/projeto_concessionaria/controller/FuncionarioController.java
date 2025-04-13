/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projeto_concessionaria.controller;

import java.util.ArrayList;
import java.util.List;
import projeto_concessionaria.Model.Funcionario;


/**
 *
 * @author Guilherme
 */
public class FuncionarioController {
    private List<Funcionario> funcionarios = new ArrayList<>();
    
    public List<Funcionario> listarFuncionarios() {
    for (Funcionario f : funcionarios) {  
        System.out.println(f);  
    }
    return funcionarios;  
}
    
    public Funcionario buscarPorCPF(String cpf) {
    for (Funcionario func : funcionarios) {
        if (func.getCpf().equals(cpf)) {
            return func;
        }
    }
    return null;
}   
    
    public boolean alterarFuncionario(Funcionario funcionarioAlterado) {
        for (Funcionario f : funcionarios) {
            if (f.getCpf().equals(funcionarioAlterado.getCpf())) {
                f.setNome(funcionarioAlterado.getNome());
                f.setTelefone(funcionarioAlterado.getTelefone());
                f.setEmail(funcionarioAlterado.getEmail());
                f.setLogin(funcionarioAlterado.getLogin());
                f.setSenha(funcionarioAlterado.getSenha());
                f.setCargo(funcionarioAlterado.getCargo());
                return true;
            }
        }
        return false;
    }
    
    
    public Funcionario login(String login, String senha) {
    return funcionarios.stream()
            .filter(func -> func.getLogin().equals(login) && func.getSenha().equals(senha))
            .findFirst()
            .orElse(null);
    }
    
    public boolean cadastrarFuncionario(Funcionario novoFuncionario) {
    
    if (novoFuncionario == null) {
        return false;
    }
    
    funcionarios.add(novoFuncionario);
    System.out.println("Novo funcionario adicionado: " + novoFuncionario.getNome());
    return true;
}
    public boolean removerFuncionario(String cpf) {
        for (Funcionario f : funcionarios) {
            if (f.getCpf().equals(cpf)) {
                funcionarios.remove(f);
                return true;
            }
        }
        return false; 
    }
    
}
