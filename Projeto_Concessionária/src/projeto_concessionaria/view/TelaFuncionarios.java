/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package projeto_concessionaria.view;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import projeto_concessionaria.Model.Funcionario;
import projeto_concessionaria.controller.FuncionarioController;
import projeto_concessionaria.model.enums.CargoFuncionario;
import java.util.List;

/**
 *
 * @author gabri
 */
public class TelaFuncionarios extends javax.swing.JPanel {
    
   private FuncionarioController controller;
   private final Frame parentFrame = (Frame) SwingUtilities.getWindowAncestor(this);
    
    /**
     * Creates new form A1
     * @param controller
     */
    public TelaFuncionarios() {
        initComponents();
        controller = new FuncionarioController();
        btnAlterar.addActionListener(e -> {
        mostrarDialogoAlterarFuncionario();
        });
        btnExcluir.addActionListener(e -> mostrarDialogoExcluirFuncionario());
        
        Funcionario f1 = new Funcionario("Carlos Silva", "123.456.789-00", "(11) 99999-0001", "carlos.silva@email.com", CargoFuncionario.GERENTE, "carloss", "senha123");
        Funcionario f2 = new Funcionario("Ana Pereira", "234.567.890-11", "(11) 99999-0002", "ana.pereira@email.com", CargoFuncionario.GERENTE, "anap", "senha123");
        Funcionario f3 = new Funcionario("João Souza", "345.678.901-22", "(11) 99999-0003", "joao.souza@email.com", CargoFuncionario.GERENTE, "joaos", "senha123");
        Funcionario f4 = new Funcionario("Mariana Lima", "456.789.012-33", "(11) 99999-0004", "mariana.lima@email.com", CargoFuncionario.GERENTE, "marianal", "senha123");
        Funcionario f5 = new Funcionario("Pedro Santos", "567.890.123-44", "(11) 99999-0005", "pedro.santos@email.com", CargoFuncionario.GERENTE, "pedros", "senha123");
        Funcionario f6 = new Funcionario("Juliana Costa", "678.901.234-55", "(11) 99999-0006", "juliana.costa@email.com", CargoFuncionario.GERENTE, "julianac", "senha123");
        Funcionario f7 = new Funcionario("Lucas Rocha", "789.012.345-66", "(11) 99999-0007", "lucas.rocha@email.com", CargoFuncionario.GERENTE, "lucasr", "senha123");
        Funcionario f8 = new Funcionario("Beatriz Almeida", "890.123.456-77", "(11) 99999-0008", "beatriz.almeida@email.com", CargoFuncionario.GERENTE, "beatriza", "senha123");
        Funcionario f9 = new Funcionario("Rafael Oliveira", "901.234.567-88", "(11) 99999-0009", "rafael.oliveira@email.com", CargoFuncionario.GERENTE, "rafaelo", "senha123");
        Funcionario f10 = new Funcionario("Camila Martins", "012.345.678-99", "(11) 99999-0010", "camila.martins@email.com", CargoFuncionario.GERENTE, "camilam", "senha123");
        
        controller.cadastrarFuncionario(f1);
        controller.cadastrarFuncionario(f2);
        controller.cadastrarFuncionario(f3);
        controller.cadastrarFuncionario(f4);
        controller.cadastrarFuncionario(f5);
        controller.cadastrarFuncionario(f6);
        controller.cadastrarFuncionario(f7);
        controller.cadastrarFuncionario(f8);
        controller.cadastrarFuncionario(f9);
        controller.cadastrarFuncionario(f10);
        
        atualizarTabela();
    }
    
    
    private void mostrarMensagemCadastroSucesso() {
    JDialog dialog = new JDialog (parentFrame, "Cadastro", true); 
    dialog.setSize(300, 150);
    dialog.setLayout(new FlowLayout());

    JLabel sucessoLabel = new JLabel("Cadastro realizado com sucesso!");
    JButton okButton = new JButton("OK");

    okButton.addActionListener(e -> dialog.dispose());

    dialog.add(sucessoLabel);
    dialog.add(okButton);

    dialog.setLocationRelativeTo(this);  
    dialog.setVisible(true);  
}
   private void mostrarDialogoAlterarFuncionario() {
    int linhaSelecionada = tabelaFuncionario.getSelectedRow();

    if (linhaSelecionada == -1) {
        JOptionPane.showMessageDialog(this, "Selecione um funcionário para alterar.");
        return;
    }

    String cpf = (String) tabelaFuncionario.getValueAt(linhaSelecionada, 1);

   
    Funcionario funcionarioOriginal = controller.buscarPorCPF(cpf);

    if (funcionarioOriginal == null) {
        JOptionPane.showMessageDialog(this, "Funcionário não encontrado.");
        return;
    }

    JDialog dialog = new JDialog(parentFrame, "Alterar Funcionário", true);
    dialog.setLayout(new GridBagLayout());
    dialog.setSize(400, 400);

    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(5, 5, 5, 5);
    gbc.fill = GridBagConstraints.HORIZONTAL;

    JTextField campoNome = new JTextField(funcionarioOriginal.getNome());
    JTextField campoCPF = new JTextField(funcionarioOriginal.getCpf());
    JTextField campoTelefone = new JTextField(funcionarioOriginal.getTelefone());
    JTextField campoEmail = new JTextField(funcionarioOriginal.getEmail());
    JTextField campoLogin = new JTextField(funcionarioOriginal.getLogin());
    JPasswordField campoSenha = new JPasswordField(funcionarioOriginal.getSenha());
    JTextField campoCargo = new JTextField(funcionarioOriginal.getCargo().toString());

    int linha = 0;

    gbc.gridx = 0; gbc.gridy = linha;
    dialog.add(new JLabel("Nome:"), gbc);
    gbc.gridx = 1;
    dialog.add(campoNome, gbc); linha++;

    gbc.gridx = 0; gbc.gridy = linha;
    dialog.add(new JLabel("CPF:"), gbc);
    gbc.gridx = 1;
    dialog.add(campoCPF, gbc); linha++;

    gbc.gridx = 0; gbc.gridy = linha;
    dialog.add(new JLabel("Telefone:"), gbc);
    gbc.gridx = 1;
    dialog.add(campoTelefone, gbc); linha++;

    gbc.gridx = 0; gbc.gridy = linha;
    dialog.add(new JLabel("Email:"), gbc);
    gbc.gridx = 1;
    dialog.add(campoEmail, gbc); linha++;

    gbc.gridx = 0; gbc.gridy = linha;
    dialog.add(new JLabel("Login:"), gbc);
    gbc.gridx = 1;
    dialog.add(campoLogin, gbc); linha++;

    gbc.gridx = 0; gbc.gridy = linha;
    dialog.add(new JLabel("Senha:"), gbc);
    gbc.gridx = 1;
    dialog.add(campoSenha, gbc); linha++;

    gbc.gridx = 0; gbc.gridy = linha;
    dialog.add(new JLabel("Cargo:"), gbc);
    gbc.gridx = 1;
    dialog.add(campoCargo, gbc); linha++;

    JButton botaoSalvar = new JButton("Salvar");
    gbc.gridx = 0; gbc.gridy = linha;
    gbc.gridwidth = 2;
    dialog.add(botaoSalvar, gbc);

    botaoSalvar.addActionListener(e -> {
        try {
            funcionarioOriginal.setNome(campoNome.getText());
            funcionarioOriginal.setCpf(campoCPF.getText());
            funcionarioOriginal.setTelefone(campoTelefone.getText());
            funcionarioOriginal.setEmail(campoEmail.getText());
            funcionarioOriginal.setLogin(campoLogin.getText());
            funcionarioOriginal.setSenha(new String(campoSenha.getPassword()));
            try {
                CargoFuncionario cargo = CargoFuncionario.valueOf(campoCargo.getText().toUpperCase().trim());
                funcionarioOriginal.setCargo(cargo);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(dialog, "Cargo inválido.");
                return;
            }

            controller.alterarFuncionario(funcionarioOriginal); 

            JOptionPane.showMessageDialog(dialog, "Funcionário atualizado com sucesso!");
            atualizarTabela(); 
            dialog.dispose();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(dialog, "Erro ao salvar alterações: " + ex.getMessage());
        }
    });

    dialog.setLocationRelativeTo(this);
    dialog.setVisible(true);
}
   
   private void mostrarDialogoExcluirFuncionario() {
    int linhaSelecionada = tabelaFuncionario.getSelectedRow();

    if (linhaSelecionada == -1) {
        JOptionPane.showMessageDialog(this, "Selecione um funcionário para excluir.");
        return;
    }

    String cpf = ((String) tabelaFuncionario.getValueAt(linhaSelecionada, 1)).trim();

    
    Funcionario funcionario = controller.buscarPorCPF(cpf);

    if (funcionario == null) {
        JOptionPane.showMessageDialog(this, "Funcionário não encontrado.");
        return;
    }

    // Criar o JDialog de confirmação
    JDialog dialog = new JDialog(parentFrame, "Confirmar Exclusão", true);
    dialog.setLayout(new GridBagLayout());
    dialog.setSize(400, 200);

    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(10, 10, 10, 10);
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.gridwidth = 2;

    JLabel mensagem = new JLabel("<html>Deseja realmente excluir o funcionário:<br><b>" +
        funcionario.getNome() + " (" + funcionario.getCpf() + ")</b>?</html>");
    dialog.add(mensagem, gbc);

    JButton btnConfirmar = new JButton("Confirmar");
    JButton btnCancelar = new JButton("Cancelar");

    gbc.gridy++;
    gbc.gridwidth = 1;
    dialog.add(btnConfirmar, gbc);

    gbc.gridx = 1;
    dialog.add(btnCancelar, gbc);

   
    btnConfirmar.addActionListener(e -> {
        controller.removerFuncionario(cpf);
        atualizarTabela(); 
        dialog.dispose();
        JOptionPane.showMessageDialog(this, "Funcionário excluído com sucesso!");
    });

    
    btnCancelar.addActionListener(e -> dialog.dispose());

    dialog.setLocationRelativeTo(this);
    dialog.setVisible(true);
}


    
    
   private void atualizarTabela() {
    List<Funcionario> listaFuncionarios = controller.listarFuncionarios();
    System.out.println("Atualizando tabela..."); 
    System.out.println("Número de funcionários: " + listaFuncionarios.size()); 

   
    DefaultTableModel modelo = (DefaultTableModel) tabelaFuncionario.getModel();
    modelo.setRowCount(0); 

    
    for (Funcionario funcionario : listaFuncionarios) {
        System.out.println("Adicionando: " + funcionario.getNome());
        modelo.addRow(new Object[] {
            funcionario.getNome(),
            funcionario.getCpf(),
            funcionario.getTelefone(),
            funcionario.getEmail(),
            funcionario.getCargo(),
            funcionario.getLogin()
        });
    }
}
   private void limparCampos() {
    txtNomeCompleto.setText("");
    txtCPF.setText("");
    txtTelefone.setText("");
    txtEmail.setText("");
    txtLogin.setText("");
    txtSenha.setText("");
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
        jLabel8 = new javax.swing.JLabel();
        txtPesquisarCPF = new javax.swing.JFormattedTextField();
        btnPesquisar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaFuncionario = new javax.swing.JTable();
        btnAlterar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnAtualizar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtCPF = new javax.swing.JFormattedTextField();
        txtNomeCompleto = new javax.swing.JTextField();
        txtTelefone = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        txtLogin = new javax.swing.JTextField();
        txtSenha = new javax.swing.JPasswordField();
        btnConfirmarCadastro = new javax.swing.JButton();
        btnCancelarCadastro = new javax.swing.JButton();
        CargoComboBox = new javax.swing.JComboBox<>();

        jTabbedPane1.setTabPlacement(javax.swing.JTabbedPane.LEFT);

        jLabel8.setText("CPF:");

        try {
            txtPesquisarCPF.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtPesquisarCPF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPesquisarCPFActionPerformed(evt);
            }
        });

        btnPesquisar.setText("Pesquisar");
        btnPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisarActionPerformed(evt);
            }
        });

        tabelaFuncionario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "CPF", "Nome", "Telefone", "E-mail", "Cargo", "Login", "Senha"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tabelaFuncionario);

        btnAlterar.setText("Alterar");
        btnAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarActionPerformed(evt);
            }
        });

        btnExcluir.setText("Excluir");
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        btnAtualizar.setText("Atualizar");
        btnAtualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtualizarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPesquisarCPF, javax.swing.GroupLayout.DEFAULT_SIZE, 358, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAtualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtPesquisarCPF, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnExcluir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAtualizar)))
                .addContainerGap(104, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Consulta", jPanel2);

        jLabel1.setText("CPF:");

        jLabel2.setText("Nome Completo:");

        jLabel3.setText("Telefone:");

        jLabel4.setText("E-Mail:");

        jLabel5.setText("Cargo:");

        jLabel6.setText("Login:");

        jLabel7.setText("Senha:");

        try {
            txtCPF.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtCPF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCPFActionPerformed(evt);
            }
        });

        txtNomeCompleto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNomeCompletoActionPerformed(evt);
            }
        });

        txtLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLoginActionPerformed(evt);
            }
        });

        txtSenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSenhaActionPerformed(evt);
            }
        });

        btnConfirmarCadastro.setText("Confirmar");
        btnConfirmarCadastro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmarCadastroActionPerformed(evt);
            }
        });

        btnCancelarCadastro.setText("Cancelar");
        btnCancelarCadastro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarCadastroActionPerformed(evt);
            }
        });

        CargoComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Gerente", "Funcionario" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtSenha))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtEmail))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtCPF))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtTelefone))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtNomeCompleto))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtLogin))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnConfirmarCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43)
                        .addComponent(btnCancelarCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(CargoComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(20, 20, 20))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtCPF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNomeCompleto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(CargoComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnCancelarCadastro, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
                    .addComponent(btnConfirmarCadastro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(32, Short.MAX_VALUE))
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

    private void txtCPFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCPFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCPFActionPerformed

    private void txtNomeCompletoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomeCompletoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomeCompletoActionPerformed

    private void txtLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLoginActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLoginActionPerformed

    private void txtSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSenhaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSenhaActionPerformed

    private void btnCancelarCadastroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarCadastroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCancelarCadastroActionPerformed

    private void txtPesquisarCPFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPesquisarCPFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPesquisarCPFActionPerformed

    private void btnConfirmarCadastroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmarCadastroActionPerformed
       
    String nome = txtNomeCompleto.getText();
    String cpf = txtCPF.getText();
    String telefone = txtTelefone.getText();
    String email = txtEmail.getText();
    String login = txtLogin.getText();
    String senha = new String(txtSenha.getPassword());
    CargoFuncionario cargo = CargoFuncionario.valueOf(CargoComboBox.getSelectedItem().toString().toUpperCase());
    

    Funcionario novoFuncionario = new Funcionario(nome, cpf, telefone, email, cargo, login, senha);

    
    if (controller.cadastrarFuncionario(novoFuncionario)) {
        System.out.println("Novo funcionário adicionado: " + novoFuncionario.getNome());
        mostrarMensagemCadastroSucesso();
        atualizarTabela();  
    } else {
        JOptionPane.showMessageDialog(this, "Erro ao cadastrar funcionário.");
    }
    
    limparCampos();
    
    
    }//GEN-LAST:event_btnConfirmarCadastroActionPerformed

    private void btnPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarActionPerformed
        String cpf = txtPesquisarCPF.getText().trim();

    if (cpf.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Digite um CPF para buscar.");
        return;
    }

    
    Funcionario encontrado = controller.buscarPorCPF(cpf);

    DefaultTableModel modelo = (DefaultTableModel) tabelaFuncionario.getModel();
    modelo.setRowCount(0); 

    if (encontrado != null) {
        modelo.addRow(new Object[]{
            encontrado.getNome(),
            encontrado.getCpf(),
            encontrado.getTelefone(),
            encontrado.getEmail(),
            encontrado.getCargo(),
            encontrado.getLogin()
        });
    } else {
        JOptionPane.showMessageDialog(this, "Funcionário não encontrado.");
    }
    }//GEN-LAST:event_btnPesquisarActionPerformed

    private void btnAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarActionPerformed
        
    }//GEN-LAST:event_btnAlterarActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void btnAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtualizarActionPerformed
        atualizarTabela();
    }//GEN-LAST:event_btnAtualizarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> CargoComboBox;
    private javax.swing.JButton btnAlterar;
    private javax.swing.JButton btnAtualizar;
    private javax.swing.JButton btnCancelarCadastro;
    private javax.swing.JButton btnConfirmarCadastro;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnPesquisar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable tabelaFuncionario;
    private javax.swing.JFormattedTextField txtCPF;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtLogin;
    private javax.swing.JTextField txtNomeCompleto;
    private javax.swing.JFormattedTextField txtPesquisarCPF;
    private javax.swing.JPasswordField txtSenha;
    private javax.swing.JTextField txtTelefone;
    // End of variables declaration//GEN-END:variables
}
