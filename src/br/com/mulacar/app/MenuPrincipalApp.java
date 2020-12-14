/*
 * Novembro/Dezembro 2020.
 * Senai Fatesg Faculdade de Tecnologia
 * ADS - Análise e Desenvolvimento de Sistemas
 * Projeto Integrador - ADS3
 * Projeto Mula Car - aluguel de Veículos
 * Alunos: Aires Ribeiro, Gabriel Cunha, Lucas França e Rogério Reis
 */
package br.com.mulacar.app;

import br.com.mulacar.util.CanvasTelaPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.Timer;

public class MenuPrincipalApp extends javax.swing.JFrame {

    /**
     * Creates new form TelaPrincipal
     */
    public MenuPrincipalApp() throws IOException {
        setExtendedState(MAXIMIZED_BOTH);

        initComponents();

        CanvasTelaPrincipal canvas = new CanvasTelaPrincipal();
        this.jDesktopPaneMulaCar.add(canvas);
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDesktopPaneMulaCar = new javax.swing.JDesktopPane();
        jPanel1 = new javax.swing.JPanel();
        jLabelHora = new javax.swing.JLabel();
        jLabelData = new javax.swing.JLabel();
        jToolBar1 = new javax.swing.JToolBar();
        jSeparator4 = new javax.swing.JToolBar.Separator();
        jButtonSair = new javax.swing.JButton();
        jButtonUsuario = new javax.swing.JButton();
        jButtonCadVeiculos = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jSeparator5 = new javax.swing.JSeparator();
        jMenuBarTelaPrincipal = new javax.swing.JMenuBar();
        jMenuOperacoes = new javax.swing.JMenu();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItemLocarVeiculos = new javax.swing.JMenuItem();
        jMenuItemDevolverVeiculos = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jMenuItemSair = new javax.swing.JMenuItem();
        jMenuCadastros = new javax.swing.JMenu();
        jMenuItemCadCategorias = new javax.swing.JMenuItem();
        jMenuItemCadMarcas = new javax.swing.JMenuItem();
        jMenuItemCadModelos = new javax.swing.JMenuItem();
        jMenuItemCadVeiculos = new javax.swing.JMenuItem();
        jMenuItemCadClientes = new javax.swing.JMenuItem();
        jMenuItemCadUsuarios = new javax.swing.JMenuItem();
        jMenuItemAcessorios = new javax.swing.JMenuItem();
        jMenuRelatorios = new javax.swing.JMenu();
        jMenuItemRelCategorias = new javax.swing.JMenuItem();
        jMenuItemRelMarcas = new javax.swing.JMenuItem();
        jMenuItemRelModelos = new javax.swing.JMenuItem();
        jMenuItemRelVeiculos = new javax.swing.JMenuItem();
        jMenuItemRelClientes = new javax.swing.JMenuItem();
        jMenuItemRelUsuarios = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem17 = new javax.swing.JMenuItem();
        jMenuFerramentas = new javax.swing.JMenu();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItemERS = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenuItem12 = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        jMenuItem10 = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        jMenu7 = new javax.swing.JMenu();
        jMenuItem13 = new javax.swing.JMenuItem();
        jMenuItem14 = new javax.swing.JMenuItem();
        jMenuItem16 = new javax.swing.JMenuItem();
        jMenuItem15 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Mula Car Rent a Car");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jDesktopPaneMulaCar.setBackground(new java.awt.Color(255, 255, 255));
        jDesktopPaneMulaCar.setLayout(new java.awt.BorderLayout());

        jLabelHora.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelHora.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

        jLabelData.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelData.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 556, Short.MAX_VALUE)
                .addComponent(jLabelData, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelHora, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelData, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelHora, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);
        jToolBar1.setMaximumSize(new java.awt.Dimension(55, 30));
        jToolBar1.setMinimumSize(new java.awt.Dimension(55, 30));
        jToolBar1.add(jSeparator4);

        jButtonSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/mulacar/imagens/Log-Out - icon - Porta - 24 x 24px.png"))); // NOI18N
        jButtonSair.setFocusable(false);
        jButtonSair.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonSair.setMaximumSize(new java.awt.Dimension(35, 35));
        jButtonSair.setMinimumSize(new java.awt.Dimension(35, 35));
        jButtonSair.setPreferredSize(new java.awt.Dimension(35, 35));
        jButtonSair.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSairActionPerformed(evt);
            }
        });
        jToolBar1.add(jButtonSair);

        jButtonUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/mulacar/imagens/Person-Male-Light-icon-24px.png"))); // NOI18N
        jButtonUsuario.setFocusable(false);
        jButtonUsuario.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonUsuario.setMaximumSize(new java.awt.Dimension(35, 35));
        jButtonUsuario.setMinimumSize(new java.awt.Dimension(35, 35));
        jButtonUsuario.setPreferredSize(new java.awt.Dimension(35, 35));
        jButtonUsuario.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUsuarioActionPerformed(evt);
            }
        });
        jToolBar1.add(jButtonUsuario);

        jButtonCadVeiculos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/mulacar/imagens/car-add-icon-24px.png"))); // NOI18N
        jButtonCadVeiculos.setFocusable(false);
        jButtonCadVeiculos.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonCadVeiculos.setMaximumSize(new java.awt.Dimension(35, 35));
        jButtonCadVeiculos.setMinimumSize(new java.awt.Dimension(35, 35));
        jButtonCadVeiculos.setPreferredSize(new java.awt.Dimension(35, 35));
        jButtonCadVeiculos.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonCadVeiculos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCadVeiculosActionPerformed(evt);
            }
        });
        jToolBar1.add(jButtonCadVeiculos);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/mulacar/imagens/car-delete-icon-24px.png"))); // NOI18N
        jButton2.setFocusable(false);
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton2.setMaximumSize(new java.awt.Dimension(35, 35));
        jButton2.setMinimumSize(new java.awt.Dimension(35, 35));
        jButton2.setPreferredSize(new java.awt.Dimension(35, 35));
        jButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(jButton2);

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/mulacar/imagens/Actions-document-save-icon-24px.png"))); // NOI18N
        jButton3.setFocusable(false);
        jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton3.setMaximumSize(new java.awt.Dimension(35, 35));
        jButton3.setMinimumSize(new java.awt.Dimension(35, 35));
        jButton3.setPreferredSize(new java.awt.Dimension(35, 35));
        jButton3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(jButton3);

        jMenuOperacoes.setMnemonic('o');
        jMenuOperacoes.setText("Operação");
        jMenuOperacoes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuOperacoesActionPerformed(evt);
            }
        });
        jMenuOperacoes.add(jSeparator1);

        jMenuItemLocarVeiculos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/mulacar/imagens/car-add-icon-24px.png"))); // NOI18N
        jMenuItemLocarVeiculos.setMnemonic('l');
        jMenuItemLocarVeiculos.setText("Locação de Veículos");
        jMenuItemLocarVeiculos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemLocarVeiculosActionPerformed(evt);
            }
        });
        jMenuOperacoes.add(jMenuItemLocarVeiculos);

        jMenuItemDevolverVeiculos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/mulacar/imagens/car-delete-icon-24px.png"))); // NOI18N
        jMenuItemDevolverVeiculos.setMnemonic('d');
        jMenuItemDevolverVeiculos.setText("Devolução de Veículos");
        jMenuOperacoes.add(jMenuItemDevolverVeiculos);
        jMenuOperacoes.add(jSeparator2);

        jMenuItemSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/mulacar/imagens/Log-Out - icon - Porta - 24 x 24px.png"))); // NOI18N
        jMenuItemSair.setMnemonic('s');
        jMenuItemSair.setText("Sair");
        jMenuItemSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemSairActionPerformed(evt);
            }
        });
        jMenuOperacoes.add(jMenuItemSair);

        jMenuBarTelaPrincipal.add(jMenuOperacoes);

        jMenuCadastros.setMnemonic('c');
        jMenuCadastros.setText("Cadastros");

        jMenuItemCadCategorias.setMnemonic('c');
        jMenuItemCadCategorias.setText("Categorias");
        jMenuItemCadCategorias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCadCategoriasActionPerformed(evt);
            }
        });
        jMenuCadastros.add(jMenuItemCadCategorias);

        jMenuItemCadMarcas.setMnemonic('m');
        jMenuItemCadMarcas.setText("Marcas");
        jMenuItemCadMarcas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCadMarcasActionPerformed(evt);
            }
        });
        jMenuCadastros.add(jMenuItemCadMarcas);

        jMenuItemCadModelos.setMnemonic('o');
        jMenuItemCadModelos.setText("Modelos");
        jMenuItemCadModelos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCadModelosActionPerformed(evt);
            }
        });
        jMenuCadastros.add(jMenuItemCadModelos);

        jMenuItemCadVeiculos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/mulacar/imagens/Rolls-Royce.png"))); // NOI18N
        jMenuItemCadVeiculos.setMnemonic('v');
        jMenuItemCadVeiculos.setText("Veículos");
        jMenuItemCadVeiculos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCadVeiculosActionPerformed(evt);
            }
        });
        jMenuCadastros.add(jMenuItemCadVeiculos);

        jMenuItemCadClientes.setMnemonic('l');
        jMenuItemCadClientes.setText("Clientes");
        jMenuItemCadClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCadClientesActionPerformed(evt);
            }
        });
        jMenuCadastros.add(jMenuItemCadClientes);

        jMenuItemCadUsuarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/mulacar/imagens/Person-Male-Light-icon-24px.png"))); // NOI18N
        jMenuItemCadUsuarios.setMnemonic('u');
        jMenuItemCadUsuarios.setText("Usuários");
        jMenuItemCadUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCadUsuariosActionPerformed(evt);
            }
        });
        jMenuCadastros.add(jMenuItemCadUsuarios);

        jMenuItemAcessorios.setText("Acessórios");
        jMenuItemAcessorios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemAcessoriosActionPerformed(evt);
            }
        });
        jMenuCadastros.add(jMenuItemAcessorios);

        jMenuBarTelaPrincipal.add(jMenuCadastros);

        jMenuRelatorios.setMnemonic('r');
        jMenuRelatorios.setText("Relatórios");
        jMenuRelatorios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuRelatoriosActionPerformed(evt);
            }
        });

        jMenuItemRelCategorias.setText("Categorias");
        jMenuItemRelCategorias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemRelCategoriasActionPerformed(evt);
            }
        });
        jMenuRelatorios.add(jMenuItemRelCategorias);

        jMenuItemRelMarcas.setText("Marcas");
        jMenuItemRelMarcas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemRelMarcasActionPerformed(evt);
            }
        });
        jMenuRelatorios.add(jMenuItemRelMarcas);

        jMenuItemRelModelos.setText("Modelos");
        jMenuItemRelModelos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemRelModelosActionPerformed(evt);
            }
        });
        jMenuRelatorios.add(jMenuItemRelModelos);

        jMenuItemRelVeiculos.setText("Veiculos ");
        jMenuItemRelVeiculos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemRelVeiculosActionPerformed(evt);
            }
        });
        jMenuRelatorios.add(jMenuItemRelVeiculos);

        jMenuItemRelClientes.setText("Clientes");
        jMenuItemRelClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemRelClientesActionPerformed(evt);
            }
        });
        jMenuRelatorios.add(jMenuItemRelClientes);

        jMenuItemRelUsuarios.setText("Usuários");
        jMenuItemRelUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemRelUsuariosActionPerformed(evt);
            }
        });
        jMenuRelatorios.add(jMenuItemRelUsuarios);

        jMenuItem2.setText("Motoristas");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenuRelatorios.add(jMenuItem2);

        jMenuItem5.setText("Locações");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenuRelatorios.add(jMenuItem5);

        jMenuItem17.setText("ContratoTeste");
        jMenuItem17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem17ActionPerformed(evt);
            }
        });
        jMenuRelatorios.add(jMenuItem17);

        jMenuBarTelaPrincipal.add(jMenuRelatorios);

        jMenuFerramentas.setMnemonic('a');
        jMenuFerramentas.setText("Ajuda");
        jMenuFerramentas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuFerramentasActionPerformed(evt);
            }
        });

        jMenu1.setText("Modelagem de Banco de Dados");

        jMenuItem3.setText("Modelo Físico");
        jMenu1.add(jMenuItem3);

        jMenuFerramentas.add(jMenu1);

        jMenu2.setText("Estrutura de Dados");

        jMenuItem4.setText("Sistema com 3 estrutura de Ordenação");
        jMenu2.add(jMenuItem4);

        jMenuFerramentas.add(jMenu2);

        jMenu3.setText("Gestão de Projetos");

        jMenuItemERS.setText("ERS");
        jMenuItemERS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemERSActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItemERS);

        jMenuItem6.setText("Planejamento");
        jMenu3.add(jMenuItem6);

        jMenuItem1.setText("Prototipação das Telas");
        jMenu3.add(jMenuItem1);

        jMenuFerramentas.add(jMenu3);

        jMenu4.setText("Arquitetura e Projeto de Software");

        jMenuItem7.setText("Aplicação da organização de estrutura");
        jMenu4.add(jMenuItem7);

        jMenuItem8.setText("Aplicação distribuida em camadas");
        jMenu4.add(jMenuItem8);

        jMenuFerramentas.add(jMenu4);

        jMenu5.setText("Padrões de Projeto");

        jMenuItem9.setText("Aplicação dos padrões de Projetos minico 7");
        jMenu5.add(jMenuItem9);

        jMenuItem11.setText("Diagrama de Classe");
        jMenu5.add(jMenuItem11);

        jMenuItem12.setText("Diagrama de Caso de Uso");
        jMenu5.add(jMenuItem12);

        jMenuFerramentas.add(jMenu5);

        jMenu6.setText("Relações Humanas o Trabalho");

        jMenuItem10.setText("Proposta de Estratégias");
        jMenu6.add(jMenuItem10);

        jMenuFerramentas.add(jMenu6);
        jMenuFerramentas.add(jSeparator3);

        jMenu7.setText("Diagramas");

        jMenuItem13.setText("Diagrama de Caso de Uso");
        jMenu7.add(jMenuItem13);

        jMenuItem14.setText("Diagrama de Classe");
        jMenu7.add(jMenuItem14);

        jMenuItem16.setText("Diagrama Conceitual");
        jMenu7.add(jMenuItem16);

        jMenuItem15.setText("Diagrama Lógico");
        jMenu7.add(jMenuItem15);

        jMenuFerramentas.add(jMenu7);

        jMenuBarTelaPrincipal.add(jMenuFerramentas);

        setJMenuBar(jMenuBarTelaPrincipal);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator5)
                    .addComponent(jDesktopPaneMulaCar)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jDesktopPaneMulaCar, javax.swing.GroupLayout.DEFAULT_SIZE, 660, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        setSize(new java.awt.Dimension(719, 795));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuFerramentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuFerramentasActionPerformed
    }//GEN-LAST:event_jMenuFerramentasActionPerformed

    //Sair do programa
    private void jMenuItemSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemSairActionPerformed
        System.exit(0);                                 //Sai do sistema fechando a tela
    }//GEN-LAST:event_jMenuItemSairActionPerformed

    private void jMenuItemCadCategoriasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCadCategoriasActionPerformed
        try {
            new CategoriaApp(this, true).setVisible(true);
            this.setVisible(true);

        } catch (Exception e) {
        }
    }//GEN-LAST:event_jMenuItemCadCategoriasActionPerformed

    private void jMenuItemCadClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCadClientesActionPerformed
        try {
            new ClienteApp(this, true).setVisible(true);
            this.setVisible(true);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao abrir a janela de manutenção de clientes.");
        }
    }//GEN-LAST:event_jMenuItemCadClientesActionPerformed

    private void jMenuItemCadVeiculosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCadVeiculosActionPerformed
        // TODO add your handling code here:
        new VeiculoApp(this, true).setVisible(true);
        this.setVisible(true);
    }//GEN-LAST:event_jMenuItemCadVeiculosActionPerformed

    private void jMenuItemCadUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCadUsuariosActionPerformed
        try {
            new UsuarioApp(this, true).setVisible(true);
            this.setVisible(true);
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, erro.getMessage());
        }

    }//GEN-LAST:event_jMenuItemCadUsuariosActionPerformed

    private void jMenuItemRelClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemRelClientesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItemRelClientesActionPerformed

    private void jMenuItemRelModelosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemRelModelosActionPerformed
        // TODO add your handling code here:
        try {
            new RelatorioDeModeloApp(this, true).setVisible(true);
            this.setVisible(true);
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, erro.getMessage());
        }
    }//GEN-LAST:event_jMenuItemRelModelosActionPerformed

    private void jMenuItemRelCategoriasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemRelCategoriasActionPerformed
        // TODO add your handling code here:
        new RelatorioDeCategoriaApp(this, true).setVisible(true);
        this.setVisible(true);
    }//GEN-LAST:event_jMenuItemRelCategoriasActionPerformed

    private void jMenuRelatoriosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuRelatoriosActionPerformed
        // TODO add your handling code here:


    }//GEN-LAST:event_jMenuRelatoriosActionPerformed

    private void jMenuItemRelUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemRelUsuariosActionPerformed
        // TODO add your handling code here:
        new RelatorioDeUsuariosApp(this, true).setVisible(true);
        this.setVisible(true);
    }//GEN-LAST:event_jMenuItemRelUsuariosActionPerformed

    private void jMenuItemRelVeiculosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemRelVeiculosActionPerformed
        // TODO add your handling code here:
        try {
            new RelatorioDeVeiculosApp(this, true).setVisible(true);
            this.setVisible(true);
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, erro.getMessage());
        }
    }//GEN-LAST:event_jMenuItemRelVeiculosActionPerformed

    private void jMenuItemCadMarcasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCadMarcasActionPerformed
        // TODO add your handling code here:
        try {
            new MarcasApp(this, true).setVisible(true);
            this.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_jMenuItemCadMarcasActionPerformed

    private void jMenuItemCadModelosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCadModelosActionPerformed
        // TODO add your handling code here:
        try {
            new ModeloApp(this, true).setVisible(true);
            this.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_jMenuItemCadModelosActionPerformed

    private void jMenuItemLocarVeiculosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemLocarVeiculosActionPerformed
        // TODO add your handling code here:
        new LocacaoVeiculosApp1(this, true).setVisible(true);
        this.setVisible(true);


    }//GEN-LAST:event_jMenuItemLocarVeiculosActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        //DATA RODAPÉ
        Date dataSistema = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        jLabelData.setText(formato.format(dataSistema));

        //HORA RODAPÉ
        Timer timer = new Timer(1000, new hora());
        timer.start();

    }//GEN-LAST:event_formWindowOpened

    private void jMenuItemERSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemERSActionPerformed
        try {
            java.awt.Desktop.getDesktop().browse(new java.net.URI("https://drive.google.com/file/d/1z4Q1Y5infFKUjAlnTfn-oNKOlGv11gtk/view?usp=sharing"));
        } catch (IOException ex) {
            Logger.getLogger(MenuPrincipalApp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (URISyntaxException ex) {
            Logger.getLogger(MenuPrincipalApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItemERSActionPerformed

    private void jButtonSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSairActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jButtonSairActionPerformed

    private void jButtonUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUsuarioActionPerformed
        // TODO add your handling code here:
        try {
            new UsuarioApp(this, true).setVisible(true);
            this.setVisible(true);
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, erro.getMessage());
        }
    }//GEN-LAST:event_jButtonUsuarioActionPerformed

    private void jButtonCadVeiculosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCadVeiculosActionPerformed
        // TODO add your handling code here:
        try {
            new VeiculoApp(this, true).setVisible(true);
            this.setVisible(true);
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, erro.getMessage());
        }
    }//GEN-LAST:event_jButtonCadVeiculosActionPerformed

    private void jMenuItemRelMarcasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemRelMarcasActionPerformed
        // TODO add your handling code here:
        try {
            new RelatorioDeMarcaApp(this, true).setVisible(true);
            this.setVisible(true);

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, erro.getMessage());
        }
    }//GEN-LAST:event_jMenuItemRelMarcasActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        new RelatorioDeMotoristaApp(this, true).setVisible(true);
        this.setVisible(true);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        // TODO add your handling code here:
        new RelatorioDeLocacoesApp(this, true).setVisible(true);
        this.setVisible(true);
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuOperacoesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuOperacoesActionPerformed
        // TODO add your handling code here:
        new LocacaoVeiculosApp(this, true).setVisible(true);
        this.setVisible(true);
    }//GEN-LAST:event_jMenuOperacoesActionPerformed

    private void jMenuItemAcessoriosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemAcessoriosActionPerformed
        // TODO add your handling code here:
        new AcessorioApp(this, true).setVisible(true);
        this.setVisible(true);
    }//GEN-LAST:event_jMenuItemAcessoriosActionPerformed

    private void jMenuItem17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem17ActionPerformed
        try {
            // TODO add your handling code here:
            new ContratoDeLocacaoApp(this, true).setVisible(true);
        } catch (Exception ex) {
            Logger.getLogger(MenuPrincipalApp.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.setVisible(true);
    }//GEN-LAST:event_jMenuItem17ActionPerformed

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
                if ("Windows".equals(info.getName())) {                                      //alterar para que os botoes fiquem no padrão Windows
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipalApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipalApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipalApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipalApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new MenuPrincipalApp().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(MenuPrincipalApp.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    class hora implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Calendar now = Calendar.getInstance();
            jLabelHora.setText(String.format("%1$tH:%1$tM:%1$tS", now));
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButtonCadVeiculos;
    private javax.swing.JButton jButtonSair;
    private javax.swing.JButton jButtonUsuario;
    private javax.swing.JDesktopPane jDesktopPaneMulaCar;
    private javax.swing.JLabel jLabelData;
    private javax.swing.JLabel jLabelHora;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenuBar jMenuBarTelaPrincipal;
    private javax.swing.JMenu jMenuCadastros;
    private javax.swing.JMenu jMenuFerramentas;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuItem14;
    private javax.swing.JMenuItem jMenuItem15;
    private javax.swing.JMenuItem jMenuItem16;
    private javax.swing.JMenuItem jMenuItem17;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JMenuItem jMenuItemAcessorios;
    private javax.swing.JMenuItem jMenuItemCadCategorias;
    private javax.swing.JMenuItem jMenuItemCadClientes;
    private javax.swing.JMenuItem jMenuItemCadMarcas;
    private javax.swing.JMenuItem jMenuItemCadModelos;
    private javax.swing.JMenuItem jMenuItemCadUsuarios;
    private javax.swing.JMenuItem jMenuItemCadVeiculos;
    private javax.swing.JMenuItem jMenuItemDevolverVeiculos;
    private javax.swing.JMenuItem jMenuItemERS;
    private javax.swing.JMenuItem jMenuItemLocarVeiculos;
    private javax.swing.JMenuItem jMenuItemRelCategorias;
    private javax.swing.JMenuItem jMenuItemRelClientes;
    private javax.swing.JMenuItem jMenuItemRelMarcas;
    private javax.swing.JMenuItem jMenuItemRelModelos;
    private javax.swing.JMenuItem jMenuItemRelUsuarios;
    private javax.swing.JMenuItem jMenuItemRelVeiculos;
    private javax.swing.JMenuItem jMenuItemSair;
    private javax.swing.JMenu jMenuOperacoes;
    private javax.swing.JMenu jMenuRelatorios;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JToolBar.Separator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JToolBar jToolBar1;
    // End of variables declaration//GEN-END:variables
}
