/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projeto_concessionário.Controller;

import java.util.ArrayList;
import java.util.List;
import projeto_concessionária.Model.Funcionario;


/**
 *
 * @author Guilherme
 */
public class FuncionarioController {
    private List<Funcionario> f = new ArrayList<>();
    
    public Funcionario login(String login, String senha) {
    return f.stream()
            .filter(func -> func.getLogin().equals(login) && func.getSenha().equals(senha))
            .findFirst()
            .orElse(null);
    }
    
    public boolean cadastrarFuncionario(Funcionario novoFuncionario) {
    
    if (novoFuncionario == null) {
        return false;
    }
    
    f.add(novoFuncionario);
    return true;
}
}
