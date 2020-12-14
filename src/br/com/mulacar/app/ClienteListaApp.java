/*
 * Novembro/Dezembro 2020.
 * Senai Fatesg Faculdade de Tecnologia
 * ADS - Análise e Desenvolvimento de Sistemas
 * Projeto Integrador - ADS3
 * Projeto Mula Car - aluguel de Veículos
 * Alunos: Aires Ribeiro, Gabriel Cunha, Lucas França e Rogério Reis
 */
package br.com.mulacar.app;

import br.com.mulacar.bll.ClienteBll;
import br.com.mulacar.bll.LocacaoBll;
import br.com.mulacar.enumeration.EnumTipoCliente;
import br.com.mulacar.model.Cliente;
import br.com.mulacar.model.Locacao;
import br.com.mulacar.util.UtilComponentes;
import br.com.mulacar.util.UtilObjetos;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ClienteListaApp extends javax.swing.JDialog {

    private DefaultTableModel model;
    
    private ClienteBll clienteBll; 
    
    private List<Cliente> clientes;
    
    private LocacaoBll locacaoBll;
    
    private Cliente clienteSelecionadoTabela;
    
    private String mensagemErroPadrao;
    

    public ClienteListaApp(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        
        initComponents();
        
        clienteBll = new ClienteBll();

        clientes = new ArrayList<>();
        
        locacaoBll = new LocacaoBll();
        
        this.mensagemErroPadrao = "Entre em contato com suporte";
        
        adicionarMouseListenerTabelaClientes();
    }
    
    private void adicionarMouseListenerTabelaClientes() {
        jTableClientes.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                
                int linha = jTableClientes.getSelectedRow();
                
                clienteSelecionadoTabela = new Cliente();
                
                clienteSelecionadoTabela.setId(Integer.parseInt(jTableClientes.getValueAt(linha, 0).toString()));
                
            }
        });
    }    

    private void preencherTabelaClientes(List<Cliente> listaDeClientes) throws Exception {
        
        model = (DefaultTableModel) jTableClientes.getModel();
       
        model.setNumRows(0);
        
        clienteBll.ordenarListaClientes(this.clientes);
        
        for (int pos = 0; pos < listaDeClientes.size(); pos++) {
            String[] linha = new String[6];
            Cliente cli = this.clientes.get(pos);
            linha[0] = "" + cli.getId();
            
            if (EnumTipoCliente.PESSOA_FISICA.equals(cli.getTipoCliente())) {
                linha[1] = cli.getNome().toUpperCase();
            } else {
                linha[1] = cli.getNomeFantasia().toUpperCase();
            }          
            
            linha[2] = cli.getCpfCnpj();
            linha[3] = cli.getTipoCliente().name();

            linha[4] = cli.getStatus().name();
            
            Locacao locacaoBanco =  locacaoBll.consultarLocacaoPorCliente(new Locacao(cli));
            
            if (!UtilObjetos.ehNuloOuVazio(locacaoBanco)) {
                if (locacaoBanco.isReserva()) {
                    linha[5] = "SIM";   
                } else {
                    linha[5] = "NÃO";   
                }
            } else {
               linha[5] = "NÃO"; 
            }
            
            model.addRow(linha);
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelCategoria = new javax.swing.JPanel();
        jButtonSelecionar = new javax.swing.JButton();
        jButtonNovo = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableClientes = new javax.swing.JTable();
        jButtonFechar = new javax.swing.JButton();
        jTextFieldNomeOuCPF = new javax.swing.JTextField();
        jButtonBuscarCliente = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Clientes");
        setResizable(false);

        jPanelCategoria.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jButtonSelecionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/mulacar/imagens/Actions-document-save-icon-24px.png"))); // NOI18N
        jButtonSelecionar.setText("Selecionar");
        jButtonSelecionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSelecionarActionPerformed(evt);
            }
        });

        jButtonNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/mulacar/imagens/Apps-File-New-icon-24px.png"))); // NOI18N
        jButtonNovo.setMnemonic('n');
        jButtonNovo.setText("Novo");
        jButtonNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNovoActionPerformed(evt);
            }
        });

        jTableClientes.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jTableClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Nome/Razão Social", "CPF/CNPJ", "Tipo Pessoa", "Status", "Tem Reserva"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTableClientes);
        if (jTableClientes.getColumnModel().getColumnCount() > 0) {
            jTableClientes.getColumnModel().getColumn(0).setMinWidth(40);
            jTableClientes.getColumnModel().getColumn(0).setPreferredWidth(40);
            jTableClientes.getColumnModel().getColumn(0).setMaxWidth(40);
            jTableClientes.getColumnModel().getColumn(2).setMinWidth(120);
            jTableClientes.getColumnModel().getColumn(2).setPreferredWidth(120);
            jTableClientes.getColumnModel().getColumn(2).setMaxWidth(100);
            jTableClientes.getColumnModel().getColumn(3).setMinWidth(100);
            jTableClientes.getColumnModel().getColumn(3).setPreferredWidth(100);
            jTableClientes.getColumnModel().getColumn(3).setMaxWidth(100);
            jTableClientes.getColumnModel().getColumn(4).setMinWidth(70);
            jTableClientes.getColumnModel().getColumn(4).setPreferredWidth(70);
            jTableClientes.getColumnModel().getColumn(4).setMaxWidth(70);
            jTableClientes.getColumnModel().getColumn(5).setMinWidth(80);
            jTableClientes.getColumnModel().getColumn(5).setPreferredWidth(80);
            jTableClientes.getColumnModel().getColumn(5).setMaxWidth(80);
        }

        jButtonFechar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/mulacar/imagens/Actions-window-close-icon-24px.png"))); // NOI18N
        jButtonFechar.setText("Fechar");
        jButtonFechar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonFecharActionPerformed(evt);
            }
        });

        jButtonBuscarCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/mulacar/imagens/search-icon-16px.png"))); // NOI18N
        jButtonBuscarCliente.setMnemonic('b');
        jButtonBuscarCliente.setText("Buscar");
        jButtonBuscarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBuscarClienteActionPerformed(evt);
            }
        });

        jLabel1.setText("Nome / CNPJ");

        javax.swing.GroupLayout jPanelCategoriaLayout = new javax.swing.GroupLayout(jPanelCategoria);
        jPanelCategoria.setLayout(jPanelCategoriaLayout);
        jPanelCategoriaLayout.setHorizontalGroup(
            jPanelCategoriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCategoriaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelCategoriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanelCategoriaLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButtonNovo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonSelecionar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonFechar))
                    .addGroup(jPanelCategoriaLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(jTextFieldNomeOuCPF, javax.swing.GroupLayout.PREFERRED_SIZE, 544, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonBuscarCliente, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanelCategoriaLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButtonFechar, jButtonNovo, jButtonSelecionar});

        jPanelCategoriaLayout.setVerticalGroup(
            jPanelCategoriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCategoriaLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanelCategoriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldNomeOuCPF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonBuscarCliente)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addGroup(jPanelCategoriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonFechar)
                    .addComponent(jButtonSelecionar)
                    .addComponent(jButtonNovo))
                .addGap(21, 21, 21))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelCategoria, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelCategoria, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonSelecionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSelecionarActionPerformed
            try {
                
                if (!UtilObjetos.ehNuloOuVazio(clienteSelecionadoTabela)) {
//                    new LocacaoVeiculosApp1(this.clienteSelecionadoTabela).setVisible(true);
//                    this.setVisible(false);
                    System.exit(0);
                } else {
                  JOptionPane.showMessageDialog(null, "Selecione um cliente!"); 
                }
                

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, String.format("Erro ao selecionar o Cliente. %s", mensagemErroPadrao));
            }
    }//GEN-LAST:event_jButtonSelecionarActionPerformed

    private void jButtonFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFecharActionPerformed
        try {
            this.dispose();

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, erro.getMessage());
        }
    }//GEN-LAST:event_jButtonFecharActionPerformed

    private void jButtonNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNovoActionPerformed
        // TODO add your handling code here:
        try {
            DefaultTableModel model = (DefaultTableModel) jTableClientes.getModel();
            model.setNumRows(0);

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, erro.getMessage());
        }
    }//GEN-LAST:event_jButtonNovoActionPerformed

    private void jButtonBuscarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBuscarClienteActionPerformed
        String nomeOuCnpj = jTextFieldNomeOuCPF.getText();
        
        if (UtilObjetos.ehNuloOuVazio(nomeOuCnpj)){
            JOptionPane.showMessageDialog(null, "Digite um NOME, NOME FANTASIA ou CNPJ para pesquisa.");
            return;
        }
       
        Integer cnpj = null;
        
        try {
            cnpj = Integer.valueOf(nomeOuCnpj);
        } catch (NumberFormatException e) {
        }
        
        try {
            if (UtilObjetos.ehNuloOuVazio(cnpj)){
                Cliente cliente = new Cliente();
                cliente.setNome(nomeOuCnpj);
                cliente.setNomeFantasia(nomeOuCnpj);
                
                Cliente clienteBanco = clienteBll.getClienteByNomeOuFantasia(cliente);
                
                if (!UtilObjetos.ehNuloOuVazio(clienteBanco)) {
                    this.clientes.clear();
                    this.clientes.add(clienteBanco);
                    preencherTabelaClientes(this.clientes);
                    UtilComponentes.limparCampos(jTextFieldNomeOuCPF);
                    return;
                }
                
            } else {
                Cliente clienteBanco = clienteBll.getClienteByNomeOuFantasia(clienteSelecionadoTabela);
                
                if (!UtilObjetos.ehNuloOuVazio(clienteBanco)) {
                    this.clientes.clear();
                    this.clientes.add(clienteBanco);
                    preencherTabelaClientes(this.clientes);
                    UtilComponentes.limparCampos(jTextFieldNomeOuCPF);
                    return;
                }
            }
            JOptionPane.showMessageDialog(null, "Não foi encontrado um cliente com esse nome/CNPJ");
            UtilComponentes.limparCampos(jTextFieldNomeOuCPF);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, String.format("Erro ao buscar o cliente. %s", mensagemErroPadrao));
        }
        
    }//GEN-LAST:event_jButtonBuscarClienteActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ClienteListaApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ClienteListaApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ClienteListaApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ClienteListaApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }


        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ClienteListaApp dialog = new ClienteListaApp(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonBuscarCliente;
    private javax.swing.JButton jButtonFechar;
    private javax.swing.JButton jButtonNovo;
    private javax.swing.JButton jButtonSelecionar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanelCategoria;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableClientes;
    private javax.swing.JTextField jTextFieldNomeOuCPF;
    // End of variables declaration//GEN-END:variables
}
