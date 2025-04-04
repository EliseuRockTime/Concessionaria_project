/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package projeto_concessionária;

import projeto_concessionária.Model.FiltroBusca;
import projeto_concessionária.Model.Veiculo;
import projeto_concessionária.Model.enums.Status;
import projeto_concessionária.Model.enums.TipoCombustivel;
import projeto_concessionário.Controller.VeiculoController;

/**
 *
 * @author Elise
 */
public class Projeto_Concessionária {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         Veiculo v = new Veiculo("Honda", "HRV", "GAB-1234", 2024, "Preto", TipoCombustivel.DIESEL, 74000.0, Status.DISPONIVEL, "chassi", 120000.0);
        
        VeiculoController VController = new VeiculoController();
        
        // Cadastro
        VController.cadastrarVeiculo(v);
        VController.ListarVeiculos();
        
        // Exclusão
        VController.removerVeiculo("chassi");
        System.out.println("Veículos cadastrados: ");
        VController.ListarVeiculos();

        // Filtragem 
        // FiltroBusca filtro = new FiltroBusca();
        // filtro.setMarca("Honda");
        //VController.ListarVeiculosFiltrados(filtro);

    }
    
    
    
}
