/*
 * Novembro/Dezembro 2020.
 * Senai Fatesg Faculdade de Tecnologia
 * ADS - Análise e Desenvolvimento de Sistemas
 * Projeto Integrador - ADS3
 * Projeto Mula Car - aluguel de Veículos
 * Alunos: Aires Ribeiro, Gabriel Cunha, Lucas França e Rogério Reis
 */
package br.com.mulacar.app;

import br.com.mulacar.bll.LocacaoBll;
import br.com.mulacar.enumeration.EnumTipoCliente;
import br.com.mulacar.model.Locacao;
import br.com.mulacar.util.UtilObjetos;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ReservaListaApp extends javax.swing.JDialog {

    private DefaultTableModel model;
    
    private LocacaoBll locacaoBll; 
    
    private List<Locacao> reservas;
    
    private Locacao reservaSelecionadoTabela;
    
    private String mensagemErroPadrao;
    

    public ReservaListaApp(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        
        initComponents();
        
        locacaoBll = new LocacaoBll();

        reservas = new ArrayList<>();
        
        this.mensagemErroPadrao = "Entre em contato com suporte";
        
        adicionarMouseListenerTabelaReservas();
    }
    
    private void adicionarMouseListenerTabelaReservas() {
        jTableReservas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                
                int linha = jTableReservas.getSelectedRow();
                
                reservaSelecionadoTabela = new Locacao();
                
                reservaSelecionadoTabela.setId(Integer.parseInt(jTableReservas.getValueAt(linha, 0).toString()));
                
            }
        });
    }    

    private void preencherTabelaReservas(List<Locacao> listaDeReservas) throws Exception {
        
        model = (DefaultTableModel) jTableReservas.getModel();
       
        model.setNumRows(0);
        
//        locacaoBll.ordenarListaReservas(this.reservas);
        
        for (int pos = 0; pos < listaDeReservas.size(); pos++) {
            String[] linha = new String[6];
            
            Locacao reserva = this.reservas.get(pos);
            
            linha[0] = "" + reserva.getId();
            
            if (EnumTipoCliente.PESSOA_FISICA.equals(reserva.getCliente().getTipoCliente())) {
                linha[1] = reserva.getCliente().getNome().toUpperCase();
            } else {
                linha[1] = reserva.getCliente().getNomeFantasia().toUpperCase();
            }          
            
            linha[2] = reserva.getCliente().getCpfCnpj();
            
            DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");  
            
            String dataRetirada = dateFormat.format(reserva.getDataRetirada());  
            linha[3] = dataRetirada;

            String dataDevolucao = dateFormat.format(reserva.getDataDevolucaoPrevista());  
            linha[4] = dataDevolucao;
            
            linha[5] = reserva.getStatus().name();
            
//          PAREI AQUI
//            Locacao locacaoBanco = locacaoBll.consultarLocacaoPorReserva(new Locacao(cli));
            
//            if (!UtilObjetos.ehNuloOuVazio(locacaoBanco)) {
//                if (locacaoBanco.isReserva()) {
//                    linha[5] = "SIM";   
//                } else {
//                    linha[5] = "NÃO";   
//                }
//            } else {
//               linha[5] = "NÃO"; 
//            }
            
            model.addRow(linha);
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelCategoria = new javax.swing.JPanel();
        jButtonSelecionar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableReservas = new javax.swing.JTable();
        jButtonFechar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Reservas");
        setResizable(false);

        jPanelCategoria.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jButtonSelecionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/mulacar/imagens/Actions-document-save-icon-24px.png"))); // NOI18N
        jButtonSelecionar.setText("Selecionar");
        jButtonSelecionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSelecionarActionPerformed(evt);
            }
        });

        jTableReservas.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jTableReservas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Nome/Razão Social", "CPF/CNPJ", "Data Retirada", "Data Devolução", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTableReservas);
        if (jTableReservas.getColumnModel().getColumnCount() > 0) {
            jTableReservas.getColumnModel().getColumn(0).setMinWidth(40);
            jTableReservas.getColumnModel().getColumn(0).setPreferredWidth(40);
            jTableReservas.getColumnModel().getColumn(0).setMaxWidth(40);
            jTableReservas.getColumnModel().getColumn(2).setMinWidth(120);
            jTableReservas.getColumnModel().getColumn(2).setPreferredWidth(120);
            jTableReservas.getColumnModel().getColumn(2).setMaxWidth(100);
            jTableReservas.getColumnModel().getColumn(3).setMinWidth(100);
            jTableReservas.getColumnModel().getColumn(3).setPreferredWidth(100);
            jTableReservas.getColumnModel().getColumn(3).setMaxWidth(100);
            jTableReservas.getColumnModel().getColumn(4).setMinWidth(100);
            jTableReservas.getColumnModel().getColumn(4).setPreferredWidth(100);
            jTableReservas.getColumnModel().getColumn(4).setMaxWidth(100);
            jTableReservas.getColumnModel().getColumn(5).setMinWidth(100);
            jTableReservas.getColumnModel().getColumn(5).setPreferredWidth(100);
            jTableReservas.getColumnModel().getColumn(5).setMaxWidth(100);
        }

        jButtonFechar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/mulacar/imagens/Actions-window-close-icon-24px.png"))); // NOI18N
        jButtonFechar.setText("Fechar");
        jButtonFechar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonFecharActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelCategoriaLayout = new javax.swing.GroupLayout(jPanelCategoria);
        jPanelCategoria.setLayout(jPanelCategoriaLayout);
        jPanelCategoriaLayout.setHorizontalGroup(
            jPanelCategoriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCategoriaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelCategoriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 827, Short.MAX_VALUE)
                    .addGroup(jPanelCategoriaLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButtonSelecionar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonFechar)))
                .addContainerGap())
        );

        jPanelCategoriaLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButtonFechar, jButtonSelecionar});

        jPanelCategoriaLayout.setVerticalGroup(
            jPanelCategoriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCategoriaLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addGroup(jPanelCategoriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonFechar)
                    .addComponent(jButtonSelecionar))
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
                
                if (!UtilObjetos.ehNuloOuVazio(reservaSelecionadoTabela)) {
//                    new LocacaoVeiculosApp1(this.reservaSelecionadoTabela).setVisible(true);
//                    this.setVisible(false);
                    System.exit(0);
                } else {
                  JOptionPane.showMessageDialog(null, "Selecione um reserva!"); 
                }
                

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, String.format("Erro ao selecionar o Reserva. %s", mensagemErroPadrao));
            }
    }//GEN-LAST:event_jButtonSelecionarActionPerformed

    private void jButtonFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFecharActionPerformed
        try {
            this.dispose();

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, erro.getMessage());
        }
    }//GEN-LAST:event_jButtonFecharActionPerformed

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
            java.util.logging.Logger.getLogger(ReservaListaApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ReservaListaApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ReservaListaApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ReservaListaApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }


        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ReservaListaApp dialog = new ReservaListaApp(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton jButtonFechar;
    private javax.swing.JButton jButtonSelecionar;
    private javax.swing.JPanel jPanelCategoria;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableReservas;
    // End of variables declaration//GEN-END:variables
}
