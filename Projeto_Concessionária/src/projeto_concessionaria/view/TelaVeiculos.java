/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package projeto_concessionaria.view;

import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import projeto_concessionaria.Model.FiltroBusca;
import projeto_concessionaria.Model.Funcionario;
import projeto_concessionaria.Model.Veiculo;
import projeto_concessionaria.controller.MovimentacaoController;
import projeto_concessionaria.controller.VeiculoController;
import projeto_concessionaria.model.enums.Status;
import projeto_concessionaria.model.enums.TipoCombustivel;

/**
 *
 * @author gabri
 */
public class TelaVeiculos extends javax.swing.JPanel {
    private final VeiculoController controller = new VeiculoController();
    private final MovimentacaoController ControllerDaMovimentacao;
    private final Funcionario UsuarioLogado;
    private final Frame parentFrame = (Frame) SwingUtilities.getWindowAncestor(this);

    public TelaVeiculos(MovimentacaoController ControllerDaMovimentacao, Funcionario UsuarioLogado) {
        initComponents();
        btnFiltro.addActionListener(e ->{
            mostrarDialogoFiltro();
        });
        btnAlterar.addActionListener(e -> mostrarDialogoAlterar());
        btnExcluir.addActionListener(e -> mostrarDialogoExcluir());
        this.ControllerDaMovimentacao = ControllerDaMovimentacao;
        this.UsuarioLogado = UsuarioLogado;
    }
    
    private FiltroBusca filtroAtual;
    private JTextField campoMarcaFiltro;
    private JTextField campoModeloFiltro;
    private JTextField campoAnoMin;
    private JTextField campoAnoMax;
    private JTextField campoPrecoMin;
    private JTextField campoPrecoMax;
    private JComboBox campoStatus;
    
    
    private void mostrarDialogoFiltro() {
        
        JDialog dialog = new JDialog (parentFrame, "Filtros de Busca", true); 
        dialog.setLayout(new GridBagLayout());
        dialog.setSize(400, 400); 

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;


        campoMarcaFiltro = new JTextField();
        campoModeloFiltro = new JTextField();
        campoAnoMin = new JTextField();
        campoAnoMax = new JTextField();
        campoPrecoMin = new JTextField();
        campoPrecoMax = new JTextField();
        campoStatus = new JComboBox<>(Status.values());

        int linha = 0;

        gbc.gridx = 0; gbc.gridy = linha;
        dialog.add(new JLabel("Marca:"), gbc);
        gbc.gridx = 1;
        dialog.add(campoMarcaFiltro, gbc); linha++;

        gbc.gridx = 0; gbc.gridy = linha;
        dialog.add(new JLabel("Modelo:"), gbc);
        gbc.gridx = 1;
        dialog.add(campoModeloFiltro, gbc); linha++;

        gbc.gridx = 0; gbc.gridy = linha;
        dialog.add(new JLabel("Ano Mínimo:"), gbc);
        gbc.gridx = 1;
        dialog.add(campoAnoMin, gbc); linha++;

        gbc.gridx = 0; gbc.gridy = linha;
        dialog.add(new JLabel("Ano Máximo:"), gbc);
        gbc.gridx = 1;
        dialog.add(campoAnoMax, gbc); linha++;

        gbc.gridx = 0; gbc.gridy = linha;
        dialog.add(new JLabel("Preço Mínimo:"), gbc);
        gbc.gridx = 1;
        dialog.add(campoPrecoMin, gbc); linha++;

        gbc.gridx = 0; gbc.gridy = linha;
        dialog.add(new JLabel("Preço Máximo:"), gbc);
        gbc.gridx = 1;
        dialog.add(campoPrecoMax, gbc); linha++;

        gbc.gridx = 0; gbc.gridy = linha;
        dialog.add(new JLabel("Status:"), gbc);
        gbc.gridx = 1;
        dialog.add(campoStatus, gbc); linha++;


        JButton botaoAplicar = new JButton("Aplicar Filtro");
        gbc.gridx = 0; gbc.gridy = linha; gbc.gridwidth = 2;
        dialog.add(botaoAplicar, gbc);


        botaoAplicar.addActionListener(e -> {
        aplicarFiltro();            
        preencherTabelaFiltrada();  
        dialog.dispose();           
    });


        dialog.setLocationRelativeTo(this); 
        dialog.setVisible(true);
}
      
    private void aplicarFiltro() {
        filtroAtual = new FiltroBusca();

        filtroAtual.setMarca(campoMarcaFiltro.getText().isEmpty() ? null : campoMarcaFiltro.getText());
        filtroAtual.setModelo(campoModeloFiltro.getText().isEmpty() ? null : campoModeloFiltro.getText());

        try {
            filtroAtual.setAnoMin(campoAnoMin.getText().isEmpty() ? null : Integer.parseInt(campoAnoMin.getText()));
            filtroAtual.setAnoMax(campoAnoMax.getText().isEmpty() ? null : Integer.parseInt(campoAnoMax.getText()));
            filtroAtual.setPrecoMin(campoPrecoMin.getText().isEmpty() ? null : Double.parseDouble(campoPrecoMin.getText()));
            filtroAtual.setPrecoMax(campoPrecoMax.getText().isEmpty() ? null : Double.parseDouble(campoPrecoMax.getText()));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Preencha os campos numéricos corretamente.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        filtroAtual.setStatus((Status) campoStatus.getSelectedItem());


        System.out.println("Marca: " + filtroAtual.getMarca());
        System.out.println("Modelo: " + filtroAtual.getModelo());
        System.out.println("Ano Min: " + filtroAtual.getAnoMin());
        System.out.println("Ano Max: " + filtroAtual.getAnoMax());
        System.out.println("Preço Min: " + filtroAtual.getPrecoMin());
        System.out.println("Preço Max: " + filtroAtual.getPrecoMax());
        System.out.println("Status: " + filtroAtual.getStatus());

        preencherTabelaFiltrada();
}
   
    private void preencherTabelaFiltrada() {
        List<Veiculo> veiculosFiltrados = controller.ListarVeiculosFiltrados(filtroAtual);
        DefaultTableModel modelo = (DefaultTableModel) tabelaVeiculos.getModel();
        modelo.setRowCount(0);

        for(Veiculo v : veiculosFiltrados)
        modelo.addRow(new Object[]{
            v.getMarca(),
            v.getModelo(),
            v.getPlaca(),
            String.valueOf(v.getAnoFabricacao()),
            v.getCor(),
            String.format("%.2f", v.getPreco()),  
            String.valueOf(v.getTipoCombustivel()),
            String.valueOf(v.getQuilometragem()),
            v.getChassi(),
            v.getStatus().toString() 
        });
    }
    
    private void mostrarDialogoAlterar() {
        int linhaSelecionada = tabelaVeiculos.getSelectedRow();

        if (linhaSelecionada == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um veículo para alterar.");
            return;
        }

        String chassi = (String) tabelaVeiculos.getValueAt(linhaSelecionada, 8);

        VeiculoController controller = new VeiculoController();
        Veiculo veiculoOriginal = controller.buscarVeiculoPorChassi(chassi);

        if (veiculoOriginal == null) {
            JOptionPane.showMessageDialog(this, "Veículo não encontrado.");
            return;
        }

        JDialog dialog = new JDialog(parentFrame, "Alterar Veículo", true);
        dialog.setLayout(new GridBagLayout());
        dialog.setSize(400, 400);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JTextField campoMarca = new JTextField(veiculoOriginal.getMarca());
        JTextField campoModelo = new JTextField(veiculoOriginal.getModelo());
        JTextField campoAno = new JTextField(String.valueOf(veiculoOriginal.getAnoFabricacao()));
        JTextField campoCor = new JTextField(veiculoOriginal.getCor());
        JTextField campoPreco = new JTextField(String.valueOf(veiculoOriginal.getPreco()));
        JComboBox<Status> comboStatus = new JComboBox<>(Status.values());
        comboStatus.setSelectedItem(veiculoOriginal.getStatus());

        int linha = 0;

        gbc.gridx = 0; gbc.gridy = linha;
        dialog.add(new JLabel("Marca:"), gbc);
        gbc.gridx = 1;
        dialog.add(campoMarca, gbc); linha++;

        gbc.gridx = 0; gbc.gridy = linha;
        dialog.add(new JLabel("Modelo:"), gbc);
        gbc.gridx = 1;
        dialog.add(campoModelo, gbc); linha++;

        gbc.gridx = 0; gbc.gridy = linha;
        dialog.add(new JLabel("Ano:"), gbc);
        gbc.gridx = 1;
        dialog.add(campoAno, gbc); linha++;

        gbc.gridx = 0; gbc.gridy = linha;
        dialog.add(new JLabel("Cor:"), gbc);
        gbc.gridx = 1;
        dialog.add(campoCor, gbc); linha++;

        gbc.gridx = 0; gbc.gridy = linha;
        dialog.add(new JLabel("Preço:"), gbc);
        gbc.gridx = 1;
        dialog.add(campoPreco, gbc); linha++;

        gbc.gridx = 0; gbc.gridy = linha;
        dialog.add(new JLabel("Status:"), gbc);
        gbc.gridx = 1;
        dialog.add(comboStatus, gbc); linha++;

        JButton botaoSalvar = new JButton("Salvar");
        gbc.gridx = 0; gbc.gridy = linha;
        gbc.gridwidth = 2;
        dialog.add(botaoSalvar, gbc);

        btnAlterar.addActionListener(e -> mostrarDialogoAlterar());


        botaoSalvar.addActionListener(e -> {
        try {
            veiculoOriginal.setMarca(campoMarca.getText());
            veiculoOriginal.setModelo(campoModelo.getText());
            veiculoOriginal.setAnoFabricacao(Integer.parseInt(campoAno.getText()));
            veiculoOriginal.setCor(campoCor.getText());
            veiculoOriginal.setPreco(Double.parseDouble(campoPreco.getText()));
            veiculoOriginal.setStatus((Status) comboStatus.getSelectedItem());

            controller.alterarVeiculo(veiculoOriginal); // método void

            JOptionPane.showMessageDialog(dialog, "Veículo atualizado com sucesso!");
            preencherTabelaVeiculos();
            dialog.dispose();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(dialog, "Erro ao salvar alterações: " + ex.getMessage());
        }
    });

        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }
    
    private void mostrarDialogoExcluir() {
        int linhaSelecionada = tabelaVeiculos.getSelectedRow();

        if (linhaSelecionada == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um veículo para excluir.");
            return;
        }

        String chassi = ((String) tabelaVeiculos.getValueAt(linhaSelecionada, 8)).trim();

        Veiculo veiculo = controller.buscarVeiculoPorChassi(chassi);

        if (veiculo == null) {
            JOptionPane.showMessageDialog(this, "Veículo não encontrado.");
            return;
        }

        JDialog dialog = new JDialog(parentFrame, "Confirmar Exclusão", true);
        dialog.setLayout(new GridBagLayout());
        dialog.setSize(400, 200);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;

        JLabel mensagem = new JLabel("<html>Deseja realmente excluir o veículo:<br><b>" +
            veiculo.getMarca() + " " + veiculo.getModelo() + " (" + veiculo.getChassi() + ")</b>?</html>");
        dialog.add(mensagem, gbc);

        JButton btnConfirmar = new JButton("Confirmar");
        JButton btnCancelar = new JButton("Cancelar");

        gbc.gridy++;
        gbc.gridwidth = 1;
        dialog.add(btnConfirmar, gbc);

        gbc.gridx = 1;
        dialog.add(btnCancelar, gbc);


        btnConfirmar.addActionListener(e -> {
            controller.removerVeiculo(chassi);
            ControllerDaMovimentacao.registrarSaida(veiculo, UsuarioLogado);
            preencherTabelaVeiculos(); 
            dialog.dispose();
            JOptionPane.showMessageDialog(this, "Veículo excluído com sucesso!");
        });


        btnCancelar.addActionListener(e -> dialog.dispose());

        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    private void preencherTabelaVeiculos() {
        List<Veiculo> ListaDeVeiculos = controller.ListarVeiculos();

        DefaultTableModel modelo = (DefaultTableModel) tabelaVeiculos.getModel();
        modelo.setRowCount(0); 

        for (Veiculo v : ListaDeVeiculos) {
            modelo.addRow(new Object[]{
                v.getMarca(),
                v.getModelo(),
                v.getPlaca(),
                String.valueOf(v.getAnoFabricacao()),
                v.getCor(),
                String.format("%.2f", v.getPreco()),  
                String.valueOf(v.getTipoCombustivel()),
                String.valueOf(v.getQuilometragem()),
                v.getChassi(),
                v.getStatus().toString() 
            });
        } 
    }

    private void limparCamposCadastro() {
        txtChassi.setText("");
        txtMarca.setText("");
        txtModelo.setText("");
        txtPlaca.setText("");
        txtAnoFabricacao.setText("");
        txtPreco.setText("");
        txtTipoCombustivel.setText("");
        txtQuilometragem.setText("");
        txtStatus.setText("");
        txtCor.setText("");
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jTextField11 = new javax.swing.JTextField();
        btnPesquisar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaVeiculos = new javax.swing.JTable();
        btnAlterar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnFiltro = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtMarca = new javax.swing.JTextField();
        txtModelo = new javax.swing.JTextField();
        txtPlaca = new javax.swing.JTextField();
        txtAnoFabricacao = new javax.swing.JTextField();
        txtPreco = new javax.swing.JTextField();
        txtTipoCombustivel = new javax.swing.JTextField();
        txtQuilometragem = new javax.swing.JTextField();
        txtStatus = new javax.swing.JTextField();
        txtCor = new javax.swing.JTextField();
        txtChassi = new javax.swing.JTextField();
        btnAdicionar = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane1.setTabPlacement(javax.swing.JTabbedPane.LEFT);

        jLabel11.setText("Chassi:");

        btnPesquisar.setText("Pesquisar");
        btnPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisarActionPerformed(evt);
            }
        });

        tabelaVeiculos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Marca", "Modelo", "Placa", "Ano de Fabricação", "Cor", "Preço", "Tipo de Combustível", "Quilometragem", "Chassi", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                true, true, true, true, true, true, true, true, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelaVeiculos.setName("tabelaVeiculos"); // NOI18N
        jScrollPane1.setViewportView(tabelaVeiculos);

        btnAlterar.setText("Alterar");
        btnAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarActionPerformed(evt);
            }
        });

        btnExcluir.setText("Excluir");

        btnFiltro.setText("Filtro");
        btnFiltro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFiltroActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField11, javax.swing.GroupLayout.DEFAULT_SIZE, 342, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnPesquisar, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)
                    .addComponent(btnAlterar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnExcluir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnFiltro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(181, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Consulta", jPanel2);

        jLabel1.setText("Chassi:");

        jLabel2.setText("Marca:");

        jLabel3.setText("Modelo:");

        jLabel4.setText("Placa:");

        jLabel5.setText("Ano:");

        jLabel6.setText("Preço:");

        jLabel7.setText("Combustível:");

        jLabel8.setText("Quilometragem:");

        jLabel9.setText("Status:");

        jLabel10.setText("Cor:");

        txtPreco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPrecoActionPerformed(evt);
            }
        });

        txtChassi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtChassiActionPerformed(evt);
            }
        });

        btnAdicionar.setText("Adicionar");
        btnAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarActionPerformed(evt);
            }
        });

        jButton2.setText("Cancelar");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel9)
                    .addComponent(jLabel8)
                    .addComponent(jLabel7)
                    .addComponent(jLabel6)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtChassi)
                        .addComponent(txtMarca)
                        .addComponent(txtModelo)
                        .addComponent(txtPlaca)
                        .addComponent(txtTipoCombustivel)
                        .addComponent(txtCor, javax.swing.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
                        .addComponent(txtAnoFabricacao, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtPreco)
                        .addComponent(txtQuilometragem, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtStatus)))
                .addGap(20, 20, 20))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtChassi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtModelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtAnoFabricacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtPreco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtTipoCombustivel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtQuilometragem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtCor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );

        jTabbedPane1.addTab("Cadastro", jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtPrecoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPrecoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPrecoActionPerformed

    private void txtChassiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtChassiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtChassiActionPerformed

    private void btnAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarActionPerformed

        try {

            String marca = txtMarca.getText();
            String modelo = txtModelo.getText();
            String placa = txtPlaca.getText();
            Integer AnoFabricacao = Integer.parseInt(txtAnoFabricacao.getText());
            String cor = txtCor.getText();
            Double preco = Double.parseDouble(txtPreco.getText().replace(",","."));
            TipoCombustivel tipoCombustivel = TipoCombustivel.valueOf(txtTipoCombustivel.getText().toUpperCase());
            Double quilometragem = Double.parseDouble(txtQuilometragem.getText());
            String chassi = txtChassi.getText();
            Status status = Status.valueOf(txtStatus.getText().toUpperCase());

            for (Veiculo veiculoExistente : controller.ListarVeiculos()) {
                if (veiculoExistente.getChassi().equalsIgnoreCase(txtChassi.getText())) {
                    JOptionPane.showMessageDialog(this, "Já existe um veículo com esse chassi!");
                    return; // interrompe o processo
                }
            }

            Veiculo v = new Veiculo(marca,modelo,placa,AnoFabricacao,cor,tipoCombustivel,quilometragem,status,chassi,preco);

            controller.cadastrarVeiculo(v);
            ControllerDaMovimentacao.registrarEntrada(v, UsuarioLogado);
            
            preencherTabelaVeiculos();

            JOptionPane.showMessageDialog(this, "Veículo adicionado com sucesso!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao adicionar veículo: " + ex.getMessage());
        }

        limparCamposCadastro();
    }//GEN-LAST:event_btnAdicionarActionPerformed

    private void btnPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarActionPerformed
        if (filtroAtual == null) {
            JOptionPane.showMessageDialog(this, "Nenhum filtro aplicado!");
            return;
        }

        List<Veiculo> listaFiltrada = controller.ListarVeiculosFiltrados(filtroAtual);

        DefaultTableModel modelo = (DefaultTableModel) tabelaVeiculos.getModel();
        modelo.setRowCount(0);

        for (Veiculo v : listaFiltrada) {
            modelo.addRow(new Object[]{
                
                v.getMarca(),
                v.getModelo(),
                v.getPlaca(),
                v.getAnoFabricacao(),
                v.getCor(),
                v.getPreco(),
                v.getTipoCombustivel(),
                v.getQuilometragem(),
                v.getStatus(),
            });
        }
    }//GEN-LAST:event_btnPesquisarActionPerformed

    private void btnAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAlterarActionPerformed

    private void btnFiltroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFiltroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnFiltroActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdicionar;
    private javax.swing.JButton btnAlterar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnFiltro;
    private javax.swing.JButton btnPesquisar;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTable tabelaVeiculos;
    private javax.swing.JTextField txtAnoFabricacao;
    private javax.swing.JTextField txtChassi;
    private javax.swing.JTextField txtCor;
    private javax.swing.JTextField txtMarca;
    private javax.swing.JTextField txtModelo;
    private javax.swing.JTextField txtPlaca;
    private javax.swing.JTextField txtPreco;
    private javax.swing.JTextField txtQuilometragem;
    private javax.swing.JTextField txtStatus;
    private javax.swing.JTextField txtTipoCombustivel;
    // End of variables declaration//GEN-END:variables
}
