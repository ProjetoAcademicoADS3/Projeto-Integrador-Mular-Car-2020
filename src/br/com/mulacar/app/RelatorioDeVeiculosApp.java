/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mulacar.app;

import br.com.mulacar.bll.CategoriaBll;
import br.com.mulacar.bll.VeiculoBll;
import br.com.mulacar.dal.VeiculoDalOrdena;
import br.com.mulacar.dal.VeiculoDalOrdenaSituacao;
import br.com.mulacar.dal.VeiculoDalOrdenadoCategoriaSituacao;
import br.com.mulacar.dal.VeiculoDalOrdenadoPorCategoria;
import br.com.mulacar.enumeration.EnumSituacaoVeiculo;
import br.com.mulacar.model.Categoria;
import br.com.mulacar.model.Veiculo;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author roger
 */
public class RelatorioDeVeiculosApp extends javax.swing.JDialog {

    private Veiculo veiculo;
    private VeiculoBll veiculoBll;
    private CategoriaBll categoriaBll;
    private DefaultTableModel model;
    private VeiculoDalOrdena veiculoOrdenaCategoria;
    private VeiculoDalOrdena veiculoOrdenaSituacao;
    private VeiculoDalOrdena veiculoOrdenadoCategoriaSituacao;

    /**
     * Creates new form RelatorioDeVeiculosApp
     */
    public RelatorioDeVeiculosApp(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        try {
            initComponents();
            veiculoBll = new VeiculoBll();
            categoriaBll = new CategoriaBll();
            veiculoOrdenaCategoria = new VeiculoDalOrdenadoPorCategoria();
            veiculoOrdenaSituacao = new VeiculoDalOrdenaSituacao();
            veiculoOrdenadoCategoriaSituacao = new VeiculoDalOrdenadoCategoriaSituacao();

            String[] opcaoDeOrdenacao = {" ", "categoria", "categoria / situacao", "situação"};
            jComboBoxOrdenarPor.removeAllItems();
            for (String opcao : opcaoDeOrdenacao) {
                jComboBoxOrdenarPor.addItem(opcao.toUpperCase());
            }

            jComboBoxSituacaoDoVeiculo.removeAllItems();
            jComboBoxSituacaoDoVeiculo.addItem(" ");
            for (EnumSituacaoVeiculo situacao : EnumSituacaoVeiculo.values()) {
                jComboBoxSituacaoDoVeiculo.addItem(situacao.toString());
            }

            categoriaBll = new CategoriaBll();
            List<Categoria> listaCategoria = categoriaBll.getConsultaCategorias();
            categoriaBll.ordenaListaCategorias(listaCategoria);
            jComboBoxListarPorCategoria.removeAllItems();
            jComboBoxListarPorCategoria.addItem(" ");
            for (int pos = 0; pos < listaCategoria.size(); pos++) {
                Categoria cat = listaCategoria.get(pos);
                jComboBoxListarPorCategoria.addItem(cat.getDescricao().toUpperCase());
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Atenção na inicializacao dos componentes\n"
                    + ex.getMessage());
        }

    }

    private void imprimirDadosVeiculo(List<Veiculo> listaVeiculos) throws Exception {
        try {
            model = (DefaultTableModel) jTableVeiculos.getModel();
            model.setNumRows(0);
            int categoria = 1;
            int categoriaSituacao = 2;
            int situacao = 3;

            if (jComboBoxOrdenarPor.getSelectedIndex() == categoria) {
                veiculoOrdenaCategoria.ordenaVeiculos(listaVeiculos);
            }
            if (jComboBoxOrdenarPor.getSelectedIndex() == situacao) {
                veiculoOrdenaSituacao.ordenaVeiculos(listaVeiculos);
            }
            if (jComboBoxOrdenarPor.getSelectedIndex() == categoriaSituacao) {
                veiculoOrdenadoCategoriaSituacao.ordenaVeiculos(listaVeiculos);
            }

            for (int pos = 0; pos < listaVeiculos.size(); pos++) {
                String[] linha = new String[16];
                Veiculo aux = listaVeiculos.get(pos);

                linha[0] = aux.getId() + "";
                linha[1] = aux.getPlaca().toUpperCase();
                linha[2] = aux.getRenavan();
                linha[3] = aux.getCategoria().getDescricao().toUpperCase();
                linha[4] = aux.getModelo().getDescricao().toUpperCase();
                linha[5] = aux.getModelo().getMarca().getDescricao().toUpperCase();
                linha[6] = aux.getAnoFabricacao() + "";
                linha[7] = aux.getAnoModelo() + "";
                linha[8] = aux.getTipoCombustivel().toString();
                linha[9] = aux.getTipo().toString();
                linha[10] = String.format("%.2f", aux.getPrecoCompra());
                linha[11] = String.format("%.2f", aux.getPrecoVenda());
                linha[12] = aux.getStatus().toString();
                linha[13] = aux.getNumPassageiros() + "";
                linha[14] = aux.getKm() + "";
                linha[15] = aux.getSituacao().toString();
                model.addRow(linha);

            }
            jTextFieldQuantRegistros.setText(listaVeiculos.size() + "");

        } catch (Exception erro) {
            throw new Exception(erro.getMessage());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelRelatorioDeVeiculos = new javax.swing.JPanel();
        jButtonFechar = new javax.swing.JButton();
        jButtonListar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableVeiculos = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldQuantRegistros = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jComboBoxOrdenarPor = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jComboBoxListarPorCategoria = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jComboBoxSituacaoDoVeiculo = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Relatorio de veículos");
        setResizable(false);

        jPanelRelatorioDeVeiculos.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jButtonFechar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/mulacar/imagens/Actions-window-close-icon-24px.png"))); // NOI18N
        jButtonFechar.setText("Fechar");
        jButtonFechar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonFecharActionPerformed(evt);
            }
        });

        jButtonListar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/mulacar/imagens/Data-List-icon-24px.png"))); // NOI18N
        jButtonListar.setText("Listar");
        jButtonListar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonListarActionPerformed(evt);
            }
        });

        jTableVeiculos.setAutoCreateRowSorter(true);
        jTableVeiculos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Placa", "Renavan", "Categoria", "Modelo", "Marca", "Ano Fab.", "Ano Mod.", "Combustível", "Tipo", "Pço. Compra", "Pço. Venda", "Status", "Num. Pass.", "Km", "Situação"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTableVeiculos);
        if (jTableVeiculos.getColumnModel().getColumnCount() > 0) {
            jTableVeiculos.getColumnModel().getColumn(0).setMinWidth(50);
            jTableVeiculos.getColumnModel().getColumn(0).setPreferredWidth(50);
            jTableVeiculos.getColumnModel().getColumn(0).setMaxWidth(50);
            jTableVeiculos.getColumnModel().getColumn(6).setMinWidth(60);
            jTableVeiculos.getColumnModel().getColumn(6).setPreferredWidth(60);
            jTableVeiculos.getColumnModel().getColumn(6).setMaxWidth(60);
            jTableVeiculos.getColumnModel().getColumn(7).setMinWidth(60);
            jTableVeiculos.getColumnModel().getColumn(7).setPreferredWidth(60);
            jTableVeiculos.getColumnModel().getColumn(7).setMaxWidth(60);
            jTableVeiculos.getColumnModel().getColumn(14).setMinWidth(70);
            jTableVeiculos.getColumnModel().getColumn(14).setPreferredWidth(70);
            jTableVeiculos.getColumnModel().getColumn(14).setMaxWidth(70);
        }

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        jLabel1.setText("Quant. Registros: ");

        jLabel2.setFont(new java.awt.Font("Dialog", 0, 13)); // NOI18N
        jLabel2.setText("Ordenar por:");

        jComboBoxOrdenarPor.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jComboBoxOrdenarPor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBoxOrdenarPor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxOrdenarPorActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Dialog", 0, 13)); // NOI18N
        jLabel3.setText("Filtrar por:");

        jComboBoxListarPorCategoria.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jComboBoxListarPorCategoria.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel4.setFont(new java.awt.Font("Dialog", 0, 13)); // NOI18N
        jLabel4.setText("Situação");

        jComboBoxSituacaoDoVeiculo.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jComboBoxSituacaoDoVeiculo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanelRelatorioDeVeiculosLayout = new javax.swing.GroupLayout(jPanelRelatorioDeVeiculos);
        jPanelRelatorioDeVeiculos.setLayout(jPanelRelatorioDeVeiculosLayout);
        jPanelRelatorioDeVeiculosLayout.setHorizontalGroup(
            jPanelRelatorioDeVeiculosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelRelatorioDeVeiculosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelRelatorioDeVeiculosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelRelatorioDeVeiculosLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBoxOrdenarPor, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(46, 46, 46)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBoxListarPorCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBoxSituacaoDoVeiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 201, Short.MAX_VALUE)
                        .addComponent(jButtonListar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonFechar))
                    .addGroup(jPanelRelatorioDeVeiculosLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldQuantRegistros, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanelRelatorioDeVeiculosLayout.setVerticalGroup(
            jPanelRelatorioDeVeiculosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelRelatorioDeVeiculosLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanelRelatorioDeVeiculosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonFechar)
                    .addComponent(jButtonListar)
                    .addComponent(jLabel2)
                    .addComponent(jComboBoxOrdenarPor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jComboBoxListarPorCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jComboBoxSituacaoDoVeiculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addGroup(jPanelRelatorioDeVeiculosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextFieldQuantRegistros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelRelatorioDeVeiculos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelRelatorioDeVeiculos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFecharActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButtonFecharActionPerformed

    private void jButtonListarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonListarActionPerformed
        try {
            // TODO add your handling code here:
            int indiceCategoria = jComboBoxListarPorCategoria.getSelectedIndex();
            int indiceSituacao = jComboBoxSituacaoDoVeiculo.getSelectedIndex();
            String situacao = jComboBoxSituacaoDoVeiculo.getSelectedItem().toString();
            
            Categoria categoria = new Categoria();
            String nomeCategoria = jComboBoxListarPorCategoria.getSelectedItem().toString();
            categoria = categoriaBll.getCategoriaPorNome(nomeCategoria);

            if (indiceCategoria != 0 && indiceSituacao != 0) {

                imprimirDadosVeiculo(veiculoBll.
                        getConsultarVeiculoByCategoria(categoria.getId(),
                                situacao));
            } else if (indiceCategoria != 0 && indiceSituacao == 0) {
                imprimirDadosVeiculo(veiculoBll.getConsultarVeiculoByCategoria(categoria.getId()));
            } else if (indiceCategoria == 0 && indiceSituacao != 0){
                imprimirDadosVeiculo(veiculoBll.getConsultarVeiculoBySituacao(situacao));
            }else {
                imprimirDadosVeiculo(veiculoBll.getConsultarVeiculos());
            }

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Atenção no botão listar " + erro.getMessage());
        }


    }//GEN-LAST:event_jButtonListarActionPerformed

    private void jComboBoxOrdenarPorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxOrdenarPorActionPerformed
        // TODO add your handling code here:
        try {

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Atenção do ordenar !" + erro.getMessage());
        }
    }//GEN-LAST:event_jComboBoxOrdenarPorActionPerformed

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
            java.util.logging.Logger.getLogger(RelatorioDeVeiculosApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RelatorioDeVeiculosApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RelatorioDeVeiculosApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RelatorioDeVeiculosApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                RelatorioDeVeiculosApp dialog = new RelatorioDeVeiculosApp(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton jButtonListar;
    private javax.swing.JComboBox<String> jComboBoxListarPorCategoria;
    private javax.swing.JComboBox<String> jComboBoxOrdenarPor;
    private javax.swing.JComboBox<String> jComboBoxSituacaoDoVeiculo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanelRelatorioDeVeiculos;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableVeiculos;
    private javax.swing.JTextField jTextFieldQuantRegistros;
    // End of variables declaration//GEN-END:variables
}
