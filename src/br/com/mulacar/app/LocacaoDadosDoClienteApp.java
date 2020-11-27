/*
 * Novembro/Dezembro 2020.
 * Senai Fatesg Faculdade de Tecnologia
 * ADS - Análise e Desenvolvimento de Sistemas
 * Projeto Integrador - ADS3
 * Projeto Mula Car - aluguel de Veículos
 * Alunos: Aires Ribeiro, Gabriel Cunha, Lucas França e Rogério Reis
 */

package br.com.mulacar.app;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.postgresql.jdbc2.EscapedFunctions;


public class LocacaoDadosDoClienteApp extends javax.swing.JDialog {

    /**
     * Creates new form ContratoApp
     */
    public LocacaoDadosDoClienteApp(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jPanelLocacaoCliente = new javax.swing.JPanel();
        jLabel52 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jButtonSelecaoVeiculo = new javax.swing.JButton();
        jButtonDadosComplementares = new javax.swing.JButton();
        jButtonDadosDoCliente = new javax.swing.JButton();
        jButtonDadosDoMotorista = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        jLabel70 = new javax.swing.JLabel();
        jLabel83 = new javax.swing.JLabel();
        jButtonInserirCliente = new javax.swing.JButton();
        jButtonBuscarCliente = new javax.swing.JButton();
        jButtonConfirmarSelecaoVeiculo = new javax.swing.JButton();
        jTextFieldCpfCnpj = new javax.swing.JTextField();
        jTextFieldNomeCliente = new javax.swing.JTextField();
        jTextFieldRazaoSocial = new javax.swing.JTextField();
        jScrollPaneListaDeClientes = new javax.swing.JScrollPane();
        jTableDadosDoCliente = new javax.swing.JTable();
        jScrollPaneDadosDoCliente = new javax.swing.JScrollPane();
        jTextAreaDadosDoCliente = new javax.swing.JTextArea();
        jButtonFecharDadosCliente = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Locação de Veículos");

        jLabel52.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel52.setText("Locação de Veículos");

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/mulacar/imagens/Travel-BMV-icon-128px-MulaCar.png"))); // NOI18N

        jButtonSelecaoVeiculo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButtonSelecaoVeiculo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/mulacar/imagens/Arrow-previous-icon64px in.png"))); // NOI18N
        jButtonSelecaoVeiculo.setText("Seleção do Veiculo");
        jButtonSelecaoVeiculo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonSelecaoVeiculo.setMaximumSize(new java.awt.Dimension(110, 90));
        jButtonSelecaoVeiculo.setMinimumSize(new java.awt.Dimension(110, 90));
        jButtonSelecaoVeiculo.setPreferredSize(new java.awt.Dimension(110, 90));
        jButtonSelecaoVeiculo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonSelecaoVeiculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSelecaoVeiculoActionPerformed(evt);
            }
        });

        jButtonDadosComplementares.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButtonDadosComplementares.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/mulacar/imagens/Arrow-next-icon-64pxfim.png"))); // NOI18N
        jButtonDadosComplementares.setText("Dados complementares");
        jButtonDadosComplementares.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonDadosComplementares.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        jButtonDadosDoCliente.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButtonDadosDoCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/mulacar/imagens/arrow-next-icon-64px.png"))); // NOI18N
        jButtonDadosDoCliente.setText("Dados do Cliente");
        jButtonDadosDoCliente.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonDadosDoCliente.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonDadosDoCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDadosDoClienteActionPerformed(evt);
            }
        });

        jButtonDadosDoMotorista.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButtonDadosDoMotorista.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/mulacar/imagens/arrow-next-icon-64px.png"))); // NOI18N
        jButtonDadosDoMotorista.setText("Dados do Motorista");
        jButtonDadosDoMotorista.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonDadosDoMotorista.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonDadosDoMotorista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDadosDoMotoristaActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Dados Do Cliente");

        jLabel69.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel69.setText("Informe CPF ou CNPJ");

        jLabel70.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel70.setText("Informe Nome");

        jLabel83.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel83.setText("Informe Razão Social");

        jButtonInserirCliente.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButtonInserirCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/mulacar/imagens/User-Clients-icon-24px.png"))); // NOI18N
        jButtonInserirCliente.setText("Inserir");

        jButtonBuscarCliente.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButtonBuscarCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/mulacar/imagens/search-icon-24px.png"))); // NOI18N
        jButtonBuscarCliente.setText("Buscar");

        jButtonConfirmarSelecaoVeiculo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButtonConfirmarSelecaoVeiculo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/mulacar/imagens/Ok-icon-24px.png"))); // NOI18N
        jButtonConfirmarSelecaoVeiculo.setText("Confirmar");
        jButtonConfirmarSelecaoVeiculo.setMaximumSize(new java.awt.Dimension(115, 30));
        jButtonConfirmarSelecaoVeiculo.setMinimumSize(new java.awt.Dimension(115, 30));

        jTableDadosDoCliente.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder("")));
        jTableDadosDoCliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "CPF / CPNP", "Nome / Razão Social", ""
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPaneListaDeClientes.setViewportView(jTableDadosDoCliente);
        if (jTableDadosDoCliente.getColumnModel().getColumnCount() > 0) {
            jTableDadosDoCliente.getColumnModel().getColumn(0).setMinWidth(50);
            jTableDadosDoCliente.getColumnModel().getColumn(0).setPreferredWidth(50);
            jTableDadosDoCliente.getColumnModel().getColumn(0).setMaxWidth(50);
            jTableDadosDoCliente.getColumnModel().getColumn(1).setMinWidth(100);
            jTableDadosDoCliente.getColumnModel().getColumn(1).setPreferredWidth(100);
            jTableDadosDoCliente.getColumnModel().getColumn(1).setMaxWidth(100);
            jTableDadosDoCliente.getColumnModel().getColumn(3).setMinWidth(30);
            jTableDadosDoCliente.getColumnModel().getColumn(3).setPreferredWidth(30);
            jTableDadosDoCliente.getColumnModel().getColumn(3).setMaxWidth(30);
        }

        jScrollPaneDadosDoCliente.setBorder(javax.swing.BorderFactory.createTitledBorder("Dados Do Cliente"));

        jTextAreaDadosDoCliente.setColumns(20);
        jTextAreaDadosDoCliente.setRows(5);
        jScrollPaneDadosDoCliente.setViewportView(jTextAreaDadosDoCliente);

        jButtonFecharDadosCliente.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButtonFecharDadosCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/mulacar/imagens/Actions-window-close-icon-24px.png"))); // NOI18N
        jButtonFecharDadosCliente.setText("Fechar");
        jButtonFecharDadosCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonFecharDadosClienteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelLocacaoClienteLayout = new javax.swing.GroupLayout(jPanelLocacaoCliente);
        jPanelLocacaoCliente.setLayout(jPanelLocacaoClienteLayout);
        jPanelLocacaoClienteLayout.setHorizontalGroup(
            jPanelLocacaoClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLocacaoClienteLayout.createSequentialGroup()
                .addGroup(jPanelLocacaoClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelLocacaoClienteLayout.createSequentialGroup()
                        .addGroup(jPanelLocacaoClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelLocacaoClienteLayout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(jTextFieldCpfCnpj, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelLocacaoClienteLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel69)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanelLocacaoClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanelLocacaoClienteLayout.createSequentialGroup()
                                .addComponent(jTextFieldNomeCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(21, 21, 21)
                                .addGroup(jPanelLocacaoClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jButtonDadosComplementares)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelLocacaoClienteLayout.createSequentialGroup()
                                        .addGroup(jPanelLocacaoClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanelLocacaoClienteLayout.createSequentialGroup()
                                                .addComponent(jLabel83)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 187, Short.MAX_VALUE))
                                            .addGroup(jPanelLocacaoClienteLayout.createSequentialGroup()
                                                .addComponent(jTextFieldRazaoSocial)
                                                .addGap(18, 18, 18)))
                                        .addComponent(jButtonBuscarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanelLocacaoClienteLayout.createSequentialGroup()
                                .addGap(188, 188, 188)
                                .addComponent(jButtonDadosDoCliente)
                                .addGap(51, 51, 51)
                                .addComponent(jButtonDadosDoMotorista))
                            .addGroup(jPanelLocacaoClienteLayout.createSequentialGroup()
                                .addGroup(jPanelLocacaoClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel70, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanelLocacaoClienteLayout.createSequentialGroup()
                                        .addGap(201, 201, 201)
                                        .addComponent(jLabel2)))
                                .addGap(315, 315, 315)
                                .addComponent(jButtonInserirCliente, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanelLocacaoClienteLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanelLocacaoClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelLocacaoClienteLayout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(58, 58, 58)
                                .addComponent(jButtonSelecaoVeiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                                .addComponent(jLabel52)
                                .addGap(364, 364, 364))
                            .addGroup(jPanelLocacaoClienteLayout.createSequentialGroup()
                                .addComponent(jScrollPaneListaDeClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 514, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPaneDadosDoCliente))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelLocacaoClienteLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jButtonConfirmarSelecaoVeiculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButtonFecharDadosCliente)))))
                .addContainerGap())
        );
        jPanelLocacaoClienteLayout.setVerticalGroup(
            jPanelLocacaoClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLocacaoClienteLayout.createSequentialGroup()
                .addGroup(jPanelLocacaoClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelLocacaoClienteLayout.createSequentialGroup()
                        .addComponent(jLabel52)
                        .addGap(19, 19, 19)
                        .addGroup(jPanelLocacaoClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButtonDadosDoMotorista, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButtonDadosComplementares, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButtonDadosDoCliente, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButtonSelecaoVeiculo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanelLocacaoClienteLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)))
                .addGap(18, 18, 18)
                .addGroup(jPanelLocacaoClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanelLocacaoClienteLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(19, 19, 19)
                        .addGroup(jPanelLocacaoClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel83)
                            .addComponent(jLabel70)
                            .addComponent(jLabel69))
                        .addGap(1, 1, 1)
                        .addGroup(jPanelLocacaoClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldCpfCnpj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldNomeCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldRazaoSocial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(jPanelLocacaoClienteLayout.createSequentialGroup()
                        .addComponent(jButtonInserirCliente)
                        .addGap(9, 9, 9)
                        .addComponent(jButtonBuscarCliente)
                        .addGap(14, 14, 14)))
                .addGroup(jPanelLocacaoClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelLocacaoClienteLayout.createSequentialGroup()
                        .addComponent(jScrollPaneListaDeClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 9, Short.MAX_VALUE))
                    .addComponent(jScrollPaneDadosDoCliente))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelLocacaoClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonConfirmarSelecaoVeiculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonFecharDadosCliente))
                .addGap(6, 6, 6))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelLocacaoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelLocacaoCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setSize(new java.awt.Dimension(972, 677));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonDadosDoClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDadosDoClienteActionPerformed
        // TODO add your handling code here:
//             try {
//            new LocacaoDadosDoClienteApp(null, true).setVisible(true);
//            this.setVisible(true);
//        } catch (Exception erro) {
//            JOptionPane.showMessageDialog(null, erro.getMessage());
//        }
    }//GEN-LAST:event_jButtonDadosDoClienteActionPerformed

    private void jButtonSelecaoVeiculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSelecaoVeiculoActionPerformed
        // TODO add your handling code here:
          try {
            this.dispose();
//            new LocacaoEscolhaVeiculosApp(null, true).setVisible(true);
//            this.setVisible(true);
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, erro.getMessage());
        }
    }//GEN-LAST:event_jButtonSelecaoVeiculoActionPerformed

    private void jButtonFecharDadosClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFecharDadosClienteActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButtonFecharDadosClienteActionPerformed

    private void jButtonDadosDoMotoristaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDadosDoMotoristaActionPerformed
        // TODO add your handling code here:
        try {
            this.dispose();
            new LocacaoDadosDoMotoristaApp(null, true).setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_jButtonDadosDoMotoristaActionPerformed

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
            java.util.logging.Logger.getLogger(LocacaoDadosDoClienteApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LocacaoDadosDoClienteApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LocacaoDadosDoClienteApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LocacaoDadosDoClienteApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                LocacaoDadosDoClienteApp dialog = new LocacaoDadosDoClienteApp(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton jButtonConfirmarSelecaoVeiculo;
    private javax.swing.JButton jButtonDadosComplementares;
    private javax.swing.JButton jButtonDadosDoCliente;
    private javax.swing.JButton jButtonDadosDoMotorista;
    private javax.swing.JButton jButtonFecharDadosCliente;
    private javax.swing.JButton jButtonInserirCliente;
    private javax.swing.JButton jButtonSelecaoVeiculo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JPanel jPanelLocacaoCliente;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPaneDadosDoCliente;
    private javax.swing.JScrollPane jScrollPaneListaDeClientes;
    private javax.swing.JTable jTableDadosDoCliente;
    private javax.swing.JTextArea jTextAreaDadosDoCliente;
    private javax.swing.JTextField jTextFieldCpfCnpj;
    private javax.swing.JTextField jTextFieldNomeCliente;
    private javax.swing.JTextField jTextFieldRazaoSocial;
    // End of variables declaration//GEN-END:variables
}
