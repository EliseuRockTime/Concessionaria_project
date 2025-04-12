/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package projeto_concessionaria;

import javax.swing.JFrame;
import projeto_concessionaria.view.TelaLogin;


/**
 *
 * @author Elise
 */
public class ProjetoConcessionaria {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame("Automotive Java");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        TelaLogin login = new TelaLogin(frame);
        frame.setContentPane(login);

        frame.pack(); 
        frame.setLocationRelativeTo(null);
        frame.setVisible(true); 
    }
    
    
    
}