/*
 * Novembro 2020.
 * Senai Fatesg Faculdade de Tecnologia
 * ADS - Análise e Desenvolvimento de Sistemas
 * Projeto Integrador - ADS3
 * Projeto Mula Car - aluguel de Veículos
 * Alunos: Aires Ribeiro, Gabriel Cunha, Lucas França e Rogério Reis
 */

package br.com.mulacar.app;

import br.com.mulacar.bll.UsuarioBll;
import br.com.mulacar.model.Usuario;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class RelatorioDeUsuariosApp extends javax.swing.JDialog {
    
    UsuarioBll usuBll = new UsuarioBll();

    /**
     * Creates new form RelatorioDeUsuarios
     */
    public RelatorioDeUsuariosApp(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }
    
    public void imprimirRelatorioDeUsuarios(List<Usuario> listaUsuarios) throws Exception {
        DefaultTableModel model = (DefaultTableModel) jTableRelatorioUsuarios.getModel();
        model.setNumRows(0);
        usuBll.ordenaListaUsuarioa(listaUsuarios);
        for (int pos = 0; pos < listaUsuarios.size(); pos++) {
            String[] linha = new String[8];
            Usuario usu = listaUsuarios.get(pos);
            linha[0] = usu.getId() + "";
            linha[1] = usu.getNome().toUpperCase();
            linha[2] = usu.getCpf();
            linha[3] = usu.getEmail();
            linha[4] = usu.getSenha();
            linha[5] = usu.getPerfil().toString();
            linha[6] = usu.getStatus().toString();
            model.addRow(linha);
        }
        jTextFieldQuantRegistros.setText(listaUsuarios.size() + "");

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelRelatorioUsuarios = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableRelatorioUsuarios = new javax.swing.JTable();
        jButtonFecharRelatorio = new javax.swing.JButton();
        jButtonListarUsuario = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldQuantRegistros = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jComboBoxOrdenarUsuarioPor = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Relatório de usuários");
        setResizable(false);

        jPanelRelatorioUsuarios.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jTableRelatorioUsuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cód.", "Nome", "Cpf", "Email", "Senha", "Perfil", "Status"
            }
        ));
        jScrollPane1.setViewportView(jTableRelatorioUsuarios);
        if (jTableRelatorioUsuarios.getColumnModel().getColumnCount() > 0) {
            jTableRelatorioUsuarios.getColumnModel().getColumn(0).setMinWidth(50);
            jTableRelatorioUsuarios.getColumnModel().getColumn(0).setPreferredWidth(50);
            jTableRelatorioUsuarios.getColumnModel().getColumn(0).setMaxWidth(50);
            jTableRelatorioUsuarios.getColumnModel().getColumn(2).setMinWidth(90);
            jTableRelatorioUsuarios.getColumnModel().getColumn(2).setPreferredWidth(90);
            jTableRelatorioUsuarios.getColumnModel().getColumn(2).setMaxWidth(90);
            jTableRelatorioUsuarios.getColumnModel().getColumn(3).setMinWidth(200);
            jTableRelatorioUsuarios.getColumnModel().getColumn(3).setPreferredWidth(200);
            jTableRelatorioUsuarios.getColumnModel().getColumn(3).setMaxWidth(200);
            jTableRelatorioUsuarios.getColumnModel().getColumn(4).setMinWidth(60);
            jTableRelatorioUsuarios.getColumnModel().getColumn(4).setPreferredWidth(60);
            jTableRelatorioUsuarios.getColumnModel().getColumn(4).setMaxWidth(60);
            jTableRelatorioUsuarios.getColumnModel().getColumn(5).setMinWidth(110);
            jTableRelatorioUsuarios.getColumnModel().getColumn(5).setPreferredWidth(110);
            jTableRelatorioUsuarios.getColumnModel().getColumn(5).setMaxWidth(110);
            jTableRelatorioUsuarios.getColumnModel().getColumn(6).setMinWidth(80);
            jTableRelatorioUsuarios.getColumnModel().getColumn(6).setPreferredWidth(80);
            jTableRelatorioUsuarios.getColumnModel().getColumn(6).setMaxWidth(80);
        }

        jButtonFecharRelatorio.setFont(new java.awt.Font("Dialog", 0, 13)); // NOI18N
        jButtonFecharRelatorio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/mulacar/imagens/Actions-window-close-icon-24px.png"))); // NOI18N
        jButtonFecharRelatorio.setText("Fechar");
        jButtonFecharRelatorio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonFecharRelatorioActionPerformed(evt);
            }
        });

        jButtonListarUsuario.setFont(new java.awt.Font("Dialog", 0, 13)); // NOI18N
        jButtonListarUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/mulacar/imagens/Data-List-icon-24px.png"))); // NOI18N
        jButtonListarUsuario.setText("Listar");
        jButtonListarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonListarUsuarioActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel1.setText("Quant.Registros");

        jLabel2.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel2.setText("Ordenar por: ");

        jComboBoxOrdenarUsuarioPor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanelRelatorioUsuariosLayout = new javax.swing.GroupLayout(jPanelRelatorioUsuarios);
        jPanelRelatorioUsuarios.setLayout(jPanelRelatorioUsuariosLayout);
        jPanelRelatorioUsuariosLayout.setHorizontalGroup(
            jPanelRelatorioUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelRelatorioUsuariosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelRelatorioUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelRelatorioUsuariosLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldQuantRegistros, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelRelatorioUsuariosLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBoxOrdenarUsuarioPor, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 333, Short.MAX_VALUE)
                        .addComponent(jButtonListarUsuario)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonFecharRelatorio)))
                .addContainerGap())
        );

        jPanelRelatorioUsuariosLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButtonFecharRelatorio, jButtonListarUsuario});

        jPanelRelatorioUsuariosLayout.setVerticalGroup(
            jPanelRelatorioUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelRelatorioUsuariosLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanelRelatorioUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonListarUsuario)
                    .addComponent(jButtonFecharRelatorio)
                    .addComponent(jLabel2)
                    .addComponent(jComboBoxOrdenarUsuarioPor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 356, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanelRelatorioUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextFieldQuantRegistros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22))
        );

        jPanelRelatorioUsuariosLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButtonFecharRelatorio, jButtonListarUsuario});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelRelatorioUsuarios, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelRelatorioUsuarios, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonListarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonListarUsuarioActionPerformed
        // TODO add your handling code here:
        try {
            imprimirRelatorioDeUsuarios(usuBll.getConsultaUsuarios());
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, erro.getMessage());
        }
    }//GEN-LAST:event_jButtonListarUsuarioActionPerformed

    private void jButtonFecharRelatorioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFecharRelatorioActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButtonFecharRelatorioActionPerformed

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
            java.util.logging.Logger.getLogger(RelatorioDeUsuariosApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RelatorioDeUsuariosApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RelatorioDeUsuariosApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RelatorioDeUsuariosApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                RelatorioDeUsuariosApp dialog = new RelatorioDeUsuariosApp(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton jButtonFecharRelatorio;
    private javax.swing.JButton jButtonListarUsuario;
    private javax.swing.JComboBox<String> jComboBoxOrdenarUsuarioPor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanelRelatorioUsuarios;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableRelatorioUsuarios;
    private javax.swing.JTextField jTextFieldQuantRegistros;
    // End of variables declaration//GEN-END:variables
}
