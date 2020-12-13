/*
 * Novembro/Dezembro 2020.
 * Senai Fatesg Faculdade de Tecnologia
 * ADS - Análise e Desenvolvimento de Sistemas
 * Projeto Integrador - ADS3
 * Projeto Mula Car - aluguel de Veículos
 * Alunos: Aires Ribeiro, Gabriel Cunha, Lucas França e Rogério Reis
 */

package br.com.mulacar.app;

import br.com.mulacar.bll.*;
import br.com.mulacar.model.*;
import br.com.mulacar.util.UtilComponentes;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import org.jdesktop.swingx.JXDatePicker;


public class LocacaoVeiculosApp1 extends javax.swing.JDialog {
    private CategoriaBll catBll;
    private Categoria cat;
    private MarcaBll marBll;
    private ModeloBll modBll;
    private Modelo mod;
    private String mensagemErroPadrao;


    public LocacaoVeiculosApp1(java.awt.Frame parent, boolean modal) {
        
        super(parent, modal);
            
        initComponents();
            
        catBll = new CategoriaBll();
        
//        jXDatePickerDataRetirada = new JXDatePicker();
      
//        jXDatePickerDataDevolucao = new JXDatePicker();
        
        mensagemErroPadrao = "Contate o suporte.";
        
        jRadioButtonLocacao.setSelected(true);
        
        buttonGroupLocacaoReserva.add(jRadioButtonLocacao);
        
        buttonGroupLocacaoReserva.add(jRadioButtonReserva);
        
        try {
            
            UtilComponentes.habilitarComponentes(false, 
                                                jLabelSelecioneCategoria,
                                                jLabelselecioneModelo,
                                                jTabbedPaneDadosLocacao,
                                                jComboBoxSelecaoCategoria,
                                                jComboBoxSelecaoModelo,
                                                jButtonBuscarVeiculos,
                                                jLabelTituloTabVeiculo,
                                                jTableVeiculos);
        
            
            List<Categoria> listaCategoria = catBll.getConsultaCategorias();
            
            catBll.ordenaListaCategorias(listaCategoria);
            
            jComboBoxSelecaoCategoria.removeAllItems();
            jComboBoxSelecaoCategoria.addItem(" ");
            
            for (int pos = 0; pos < listaCategoria.size(); pos++) {
                cat = listaCategoria.get(pos);
                jComboBoxSelecaoCategoria.addItem(cat.getDescricao().toUpperCase());
//                jComboBoxSelecaoCategoria.addItem(cat.getId()+ " - " + cat.getDescricao().toUpperCase());
            }
            
            //LISTA MODELOS
            modBll = new ModeloBll();
            List<Modelo> listaModelo = modBll.getConsultaModelos();
            modBll.ordenaListaModelos(listaModelo);
            jComboBoxSelecaoModelo.removeAllItems();
            jComboBoxSelecaoModelo.addItem(" ");
            for (int pos = 0; pos < 10; pos++) {
                mod = listaModelo.get(pos);
                jComboBoxSelecaoModelo.addItem(mod.getDescricao().toUpperCase());
            }
            
            //LISTA MARCA
            marBll = new MarcaBll();
            List<Marca> listaMarca = marBll.getConsultaMarcas();
            marBll.ordenaListaMarcas(listaMarca);
            
//          Configura com o JXDatePicker  
            configurarJXDatePicker(jXDatePickerDataRetirada, jXDatePickerDataDevolucao);
            
//          Configura combobox horarios retirada/devolução
            Vector<String> horarios = preencherrHorariosLocacaoVector();
            jComboBoxHoraRetirada.setModel(new DefaultComboBoxModel(horarios));
            jComboBoxHoraDevolucao.setModel(new DefaultComboBoxModel(horarios));
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, String.format("Erro na iniciliazação da classe %s. %s", 
                                        LocacaoVeiculosApp1.class.getName() , mensagemErroPadrao));
        }
    }

    private void configurarJXDatePicker(JXDatePicker... listaDatePicker) {
        
        for (JXDatePicker picker : listaDatePicker) {
            
            // Restringe seleções anteriores. a data atual
            Calendar calendar = picker.getMonthView().getCalendar();
            calendar.setTime(new Date());
            picker.getMonthView().setLowerBound(calendar.getTime());

            picker.setFormats(new String[] {"dd/MM/yyyy"});
            picker.setLinkDay(new Date(), "Hoje é {0}");
            picker.setDate(new Date());
            picker.getMonthView().setZoomable(true);
        }
    }

    private Vector<String> preencherrHorariosLocacaoVector() {
        Vector<String> vectorHorariosLocacao = new Vector<>();
        for (int i = 0; i <= 23; i++) {
            if (i > 9) {
                vectorHorariosLocacao.add(String.format("%s:00", i));
                vectorHorariosLocacao.add(String.format("%s:30", i));
            } else {
                vectorHorariosLocacao.add(String.format("0%s:00", i));
                vectorHorariosLocacao.add(String.format("0%s:30", i));
            }
        }
        return vectorHorariosLocacao;
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroupLocacaoReserva = new javax.swing.ButtonGroup();
        jTabbedPaneDadosLocacao = new javax.swing.JTabbedPane();
        jPanelSelecaoDoVeiculo = new javax.swing.JPanel();
        jLabelSelecioneCategoria = new javax.swing.JLabel();
        jComboBoxSelecaoCategoria = new javax.swing.JComboBox();
        jLabelselecioneModelo = new javax.swing.JLabel();
        jComboBoxSelecaoModelo = new javax.swing.JComboBox();
        jButtonBuscarVeiculos = new javax.swing.JButton();
        jLabelTituloTabVeiculo = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableVeiculos = new javax.swing.JTable();
        jPanelDadosDoCliente = new javax.swing.JPanel();
        jScrollPaneListaDeClientes = new javax.swing.JScrollPane();
        jTableDadosDoCliente = new javax.swing.JTable();
        jScrollPaneDadosDoCliente = new javax.swing.JScrollPane();
        jTextAreaDadosDoCliente = new javax.swing.JTextArea();
        jTextFieldCpfCnpj = new javax.swing.JTextField();
        jLabel69 = new javax.swing.JLabel();
        jLabel70 = new javax.swing.JLabel();
        jTextFieldNomeCliente = new javax.swing.JTextField();
        jLabel83 = new javax.swing.JLabel();
        jTextFieldRazaoSocial = new javax.swing.JTextField();
        jButtonBuscarCliente = new javax.swing.JButton();
        jButtonInserirCliente = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanelDadosDoMotorista = new javax.swing.JPanel();
        jLabel73 = new javax.swing.JLabel();
        jTextFieldCpfCnpj1 = new javax.swing.JTextField();
        jLabel74 = new javax.swing.JLabel();
        jTextFieldNomeCliente1 = new javax.swing.JTextField();
        jButtonInserirMotorista = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jButtonBuscarMotorista = new javax.swing.JButton();
        jScrollPaneDadosDoCliente1 = new javax.swing.JScrollPane();
        jTextAreaDadosDoCliente1 = new javax.swing.JTextArea();
        jScrollPaneListaDeClientes1 = new javax.swing.JScrollPane();
        jTableDadosDoCliente1 = new javax.swing.JTable();
        jCheckBox1 = new javax.swing.JCheckBox();
        jPanelDadosComplementares = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jTextFieldSituacaoDoTanque = new javax.swing.JTextField();
        jTextFieldDataDaRetirada = new javax.swing.JTextField();
        jTextFieldDataDevolucaoPrevista = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaObservacao = new javax.swing.JTextArea();
        jLabel10 = new javax.swing.JLabel();
        jTextFieldKmInicial = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jTextFieldValorSeguro = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jTextFieldDataCadastro = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jTextFieldUsuarioCadastro = new javax.swing.JTextField();
        jButtonFinalizarCadastro = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        jCheckBoxAcessorioCadeirinha = new javax.swing.JCheckBox();
        jCheckBoxAcessorioSuporteParaBiciclet = new javax.swing.JCheckBox();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextAreaComposicaoValores = new javax.swing.JTextArea();
        jLabel16 = new javax.swing.JLabel();
        jButtonConfirmarComposicaoValores = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jTextFieldValorLocacao = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jTextFieldValorAcessorios = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jTextFieldValorCaucao = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jRadioButtonLocacao = new javax.swing.JRadioButton();
        jRadioButtonReserva = new javax.swing.JRadioButton();
        jLabel3 = new javax.swing.JLabel();
        jComboBoxHoraRetirada = new javax.swing.JComboBox<>();
        jXDatePickerDataRetirada = new org.jdesktop.swingx.JXDatePicker();
        jLabel11 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jXDatePickerDataDevolucao = new org.jdesktop.swingx.JXDatePicker();
        jLabel22 = new javax.swing.JLabel();
        jComboBoxHoraDevolucao = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(".");
        setResizable(false);

        jTabbedPaneDadosLocacao.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N

        jPanelSelecaoDoVeiculo.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jPanelSelecaoDoVeiculo.setPreferredSize(new java.awt.Dimension(1137, 434));

        jLabelSelecioneCategoria.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabelSelecioneCategoria.setText("Selecione a Categoria");

        jLabelselecioneModelo.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabelselecioneModelo.setText("Selecione o  Modelo");

        jComboBoxSelecaoModelo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxSelecaoModeloActionPerformed(evt);
            }
        });

        jButtonBuscarVeiculos.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButtonBuscarVeiculos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/mulacar/imagens/search-icon-16px.png"))); // NOI18N
        jButtonBuscarVeiculos.setMnemonic('b');
        jButtonBuscarVeiculos.setText("Buscar");
        jButtonBuscarVeiculos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBuscarVeiculosActionPerformed(evt);
            }
        });

        jLabelTituloTabVeiculo.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jLabelTituloTabVeiculo.setText("Escolha do Veículo");

        jTableVeiculos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Categoria", "Tipo", "Marca", "Modelo", "Situação", "Status", "Nº Passag.", "Km", "Tipo Combustível", "Valor Diária"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(jTableVeiculos);
        if (jTableVeiculos.getColumnModel().getColumnCount() > 0) {
            jTableVeiculos.getColumnModel().getColumn(0).setMinWidth(50);
            jTableVeiculos.getColumnModel().getColumn(0).setPreferredWidth(50);
            jTableVeiculos.getColumnModel().getColumn(0).setMaxWidth(50);
            jTableVeiculos.getColumnModel().getColumn(1).setMinWidth(120);
            jTableVeiculos.getColumnModel().getColumn(1).setPreferredWidth(120);
            jTableVeiculos.getColumnModel().getColumn(1).setMaxWidth(120);
            jTableVeiculos.getColumnModel().getColumn(2).setMinWidth(100);
            jTableVeiculos.getColumnModel().getColumn(2).setPreferredWidth(100);
            jTableVeiculos.getColumnModel().getColumn(2).setMaxWidth(100);
            jTableVeiculos.getColumnModel().getColumn(3).setMinWidth(120);
            jTableVeiculos.getColumnModel().getColumn(3).setPreferredWidth(120);
            jTableVeiculos.getColumnModel().getColumn(3).setMaxWidth(120);
            jTableVeiculos.getColumnModel().getColumn(4).setMinWidth(150);
            jTableVeiculos.getColumnModel().getColumn(4).setPreferredWidth(150);
            jTableVeiculos.getColumnModel().getColumn(4).setMaxWidth(150);
            jTableVeiculos.getColumnModel().getColumn(5).setMinWidth(120);
            jTableVeiculos.getColumnModel().getColumn(5).setPreferredWidth(120);
            jTableVeiculos.getColumnModel().getColumn(5).setMaxWidth(120);
            jTableVeiculos.getColumnModel().getColumn(6).setMinWidth(120);
            jTableVeiculos.getColumnModel().getColumn(6).setPreferredWidth(120);
            jTableVeiculos.getColumnModel().getColumn(6).setMaxWidth(120);
            jTableVeiculos.getColumnModel().getColumn(7).setMinWidth(80);
            jTableVeiculos.getColumnModel().getColumn(7).setPreferredWidth(80);
            jTableVeiculos.getColumnModel().getColumn(7).setMaxWidth(80);
            jTableVeiculos.getColumnModel().getColumn(8).setMinWidth(100);
            jTableVeiculos.getColumnModel().getColumn(8).setPreferredWidth(100);
            jTableVeiculos.getColumnModel().getColumn(8).setMaxWidth(100);
            jTableVeiculos.getColumnModel().getColumn(9).setMinWidth(120);
            jTableVeiculos.getColumnModel().getColumn(9).setPreferredWidth(120);
            jTableVeiculos.getColumnModel().getColumn(9).setMaxWidth(120);
            jTableVeiculos.getColumnModel().getColumn(10).setMinWidth(150);
            jTableVeiculos.getColumnModel().getColumn(10).setPreferredWidth(150);
            jTableVeiculos.getColumnModel().getColumn(10).setMaxWidth(150);
        }

        javax.swing.GroupLayout jPanelSelecaoDoVeiculoLayout = new javax.swing.GroupLayout(jPanelSelecaoDoVeiculo);
        jPanelSelecaoDoVeiculo.setLayout(jPanelSelecaoDoVeiculoLayout);
        jPanelSelecaoDoVeiculoLayout.setHorizontalGroup(
            jPanelSelecaoDoVeiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSelecaoDoVeiculoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelSelecaoDoVeiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                    .addGroup(jPanelSelecaoDoVeiculoLayout.createSequentialGroup()
                        .addGroup(jPanelSelecaoDoVeiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBoxSelecaoCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelSelecioneCategoria))
                        .addGap(30, 30, 30)
                        .addGroup(jPanelSelecaoDoVeiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelselecioneModelo)
                            .addGroup(jPanelSelecaoDoVeiculoLayout.createSequentialGroup()
                                .addComponent(jComboBoxSelecaoModelo, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButtonBuscarVeiculos, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanelSelecaoDoVeiculoLayout.createSequentialGroup()
                .addGap(534, 534, 534)
                .addComponent(jLabelTituloTabVeiculo)
                .addContainerGap(576, Short.MAX_VALUE))
        );
        jPanelSelecaoDoVeiculoLayout.setVerticalGroup(
            jPanelSelecaoDoVeiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSelecaoDoVeiculoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelTituloTabVeiculo)
                .addGap(21, 21, 21)
                .addGroup(jPanelSelecaoDoVeiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelSelecioneCategoria, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelselecioneModelo))
                .addGap(4, 4, 4)
                .addGroup(jPanelSelecaoDoVeiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxSelecaoCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxSelecaoModelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonBuscarVeiculos, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(55, Short.MAX_VALUE))
        );

        jTabbedPaneDadosLocacao.addTab("Veículo", jPanelSelecaoDoVeiculo);

        jPanelDadosDoCliente.setPreferredSize(new java.awt.Dimension(1137, 434));

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
            jTableDadosDoCliente.getColumnModel().getColumn(1).setMinWidth(200);
            jTableDadosDoCliente.getColumnModel().getColumn(1).setPreferredWidth(200);
            jTableDadosDoCliente.getColumnModel().getColumn(1).setMaxWidth(200);
            jTableDadosDoCliente.getColumnModel().getColumn(3).setMinWidth(30);
            jTableDadosDoCliente.getColumnModel().getColumn(3).setPreferredWidth(30);
            jTableDadosDoCliente.getColumnModel().getColumn(3).setMaxWidth(30);
        }

        jScrollPaneDadosDoCliente.setBorder(javax.swing.BorderFactory.createTitledBorder("Dados Do Cliente"));

        jTextAreaDadosDoCliente.setColumns(20);
        jTextAreaDadosDoCliente.setRows(5);
        jScrollPaneDadosDoCliente.setViewportView(jTextAreaDadosDoCliente);

        jLabel69.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel69.setText("Informe CPF ou CNPJ");

        jLabel70.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel70.setText("Informe Nome");

        jLabel83.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel83.setText("Informe Razão Social");

        jButtonBuscarCliente.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButtonBuscarCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/mulacar/imagens/search-icon-24px.png"))); // NOI18N
        jButtonBuscarCliente.setText("Buscar");

        jButtonInserirCliente.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButtonInserirCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/mulacar/imagens/User-Clients-icon-24px.png"))); // NOI18N
        jButtonInserirCliente.setText("Inserir");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("Dados do Cliente");

        javax.swing.GroupLayout jPanelDadosDoClienteLayout = new javax.swing.GroupLayout(jPanelDadosDoCliente);
        jPanelDadosDoCliente.setLayout(jPanelDadosDoClienteLayout);
        jPanelDadosDoClienteLayout.setHorizontalGroup(
            jPanelDadosDoClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDadosDoClienteLayout.createSequentialGroup()
                .addGroup(jPanelDadosDoClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelDadosDoClienteLayout.createSequentialGroup()
                        .addGroup(jPanelDadosDoClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelDadosDoClienteLayout.createSequentialGroup()
                                .addComponent(jTextFieldCpfCnpj, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelDadosDoClienteLayout.createSequentialGroup()
                                .addComponent(jLabel69)
                                .addGap(18, 18, 18)))
                        .addGroup(jPanelDadosDoClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelDadosDoClienteLayout.createSequentialGroup()
                                .addComponent(jTextFieldNomeCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(21, 21, 21)
                                .addGroup(jPanelDadosDoClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel83)
                                    .addComponent(jTextFieldRazaoSocial, javax.swing.GroupLayout.PREFERRED_SIZE, 520, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel70, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelDadosDoClienteLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addGap(365, 365, 365)))
                .addGroup(jPanelDadosDoClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonInserirCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonBuscarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addGroup(jPanelDadosDoClienteLayout.createSequentialGroup()
                .addComponent(jScrollPaneListaDeClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 707, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPaneDadosDoCliente))
        );
        jPanelDadosDoClienteLayout.setVerticalGroup(
            jPanelDadosDoClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelDadosDoClienteLayout.createSequentialGroup()
                .addGroup(jPanelDadosDoClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonInserirCliente)
                    .addComponent(jLabel1))
                .addGroup(jPanelDadosDoClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelDadosDoClienteLayout.createSequentialGroup()
                        .addGroup(jPanelDadosDoClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel83)
                            .addComponent(jLabel70)
                            .addComponent(jLabel69))
                        .addGap(1, 1, 1)
                        .addGroup(jPanelDadosDoClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldCpfCnpj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldNomeCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldRazaoSocial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanelDadosDoClienteLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonBuscarCliente)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanelDadosDoClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPaneListaDeClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPaneDadosDoCliente, javax.swing.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE))
                .addGap(272, 272, 272))
        );

        jTabbedPaneDadosLocacao.addTab("Cliente", jPanelDadosDoCliente);

        jPanelDadosDoMotorista.setPreferredSize(new java.awt.Dimension(1137, 434));

        jLabel73.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel73.setText("Informe CPF");

        jLabel74.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel74.setText("Informe Nome");

        jButtonInserirMotorista.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButtonInserirMotorista.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/mulacar/imagens/User-Clients-icon-24px.png"))); // NOI18N
        jButtonInserirMotorista.setText("Inserir");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel5.setText("Dados Do Motorista");

        jButtonBuscarMotorista.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButtonBuscarMotorista.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/mulacar/imagens/search-icon-24px.png"))); // NOI18N
        jButtonBuscarMotorista.setText("Buscar");

        jScrollPaneDadosDoCliente1.setBorder(javax.swing.BorderFactory.createTitledBorder("Dados Do Cliente"));

        jTextAreaDadosDoCliente1.setColumns(20);
        jTextAreaDadosDoCliente1.setRows(5);
        jScrollPaneDadosDoCliente1.setViewportView(jTextAreaDadosDoCliente1);

        jTableDadosDoCliente1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder("")));
        jTableDadosDoCliente1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPaneListaDeClientes1.setViewportView(jTableDadosDoCliente1);
        if (jTableDadosDoCliente1.getColumnModel().getColumnCount() > 0) {
            jTableDadosDoCliente1.getColumnModel().getColumn(0).setMinWidth(50);
            jTableDadosDoCliente1.getColumnModel().getColumn(0).setPreferredWidth(50);
            jTableDadosDoCliente1.getColumnModel().getColumn(0).setMaxWidth(50);
            jTableDadosDoCliente1.getColumnModel().getColumn(1).setMinWidth(200);
            jTableDadosDoCliente1.getColumnModel().getColumn(1).setPreferredWidth(200);
            jTableDadosDoCliente1.getColumnModel().getColumn(1).setMaxWidth(200);
            jTableDadosDoCliente1.getColumnModel().getColumn(3).setMinWidth(30);
            jTableDadosDoCliente1.getColumnModel().getColumn(3).setPreferredWidth(30);
            jTableDadosDoCliente1.getColumnModel().getColumn(3).setMaxWidth(30);
        }

        jCheckBox1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jCheckBox1.setText("Cliente é o Motorista");

        javax.swing.GroupLayout jPanelDadosDoMotoristaLayout = new javax.swing.GroupLayout(jPanelDadosDoMotorista);
        jPanelDadosDoMotorista.setLayout(jPanelDadosDoMotoristaLayout);
        jPanelDadosDoMotoristaLayout.setHorizontalGroup(
            jPanelDadosDoMotoristaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDadosDoMotoristaLayout.createSequentialGroup()
                .addGroup(jPanelDadosDoMotoristaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldCpfCnpj1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel73, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelDadosDoMotoristaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelDadosDoMotoristaLayout.createSequentialGroup()
                        .addComponent(jLabel74, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanelDadosDoMotoristaLayout.createSequentialGroup()
                        .addComponent(jTextFieldNomeCliente1)
                        .addGap(203, 203, 203)))
                .addComponent(jButtonBuscarMotorista, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jButtonInserirMotorista, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanelDadosDoMotoristaLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(485, 485, 485))
            .addGroup(jPanelDadosDoMotoristaLayout.createSequentialGroup()
                .addComponent(jScrollPaneListaDeClientes1, javax.swing.GroupLayout.DEFAULT_SIZE, 827, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelDadosDoMotoristaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelDadosDoMotoristaLayout.createSequentialGroup()
                        .addComponent(jCheckBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(252, 252, 252))
                    .addComponent(jScrollPaneDadosDoCliente1)))
        );
        jPanelDadosDoMotoristaLayout.setVerticalGroup(
            jPanelDadosDoMotoristaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDadosDoMotoristaLayout.createSequentialGroup()
                .addComponent(jLabel5)
                .addGroup(jPanelDadosDoMotoristaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelDadosDoMotoristaLayout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addGroup(jPanelDadosDoMotoristaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel74)
                            .addComponent(jLabel73))
                        .addGap(1, 1, 1)
                        .addGroup(jPanelDadosDoMotoristaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldCpfCnpj1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldNomeCliente1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanelDadosDoMotoristaLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelDadosDoMotoristaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonBuscarMotorista)
                            .addComponent(jButtonInserirMotorista)
                            .addComponent(jCheckBox1))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelDadosDoMotoristaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPaneDadosDoCliente1)
                    .addComponent(jScrollPaneListaDeClientes1, javax.swing.GroupLayout.DEFAULT_SIZE, 339, Short.MAX_VALUE)))
        );

        jTabbedPaneDadosLocacao.addTab("Motorista", jPanelDadosDoMotorista);

        jPanelDadosComplementares.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanelDadosComplementares.setPreferredSize(new java.awt.Dimension(1137, 434));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel6.setText("Dados Complementares");

        jLabel7.setText("Situação do Tanque:");

        jLabel8.setText("Data da Retirada:");

        jLabel9.setText("Data Devolução Prevista:");

        jTextAreaObservacao.setColumns(20);
        jTextAreaObservacao.setRows(5);
        jTextAreaObservacao.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jScrollPane1.setViewportView(jTextAreaObservacao);

        jLabel10.setText("KM Inicial:");

        jLabel12.setText("Valor Seguro:");

        jLabel13.setText("Data Cadastro:");

        jLabel14.setText("Usuário:");

        jButtonFinalizarCadastro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/mulacar/imagens/Ok-icon-24px.png"))); // NOI18N
        jButtonFinalizarCadastro.setText("Finalizar Cadastro");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel15.setText("Acessórios");

        jCheckBoxAcessorioCadeirinha.setText("Cadeirinha");

        jCheckBoxAcessorioSuporteParaBiciclet.setText("Suporte para Bicicleta");

        jTextAreaComposicaoValores.setColumns(20);
        jTextAreaComposicaoValores.setRows(5);
        jScrollPane2.setViewportView(jTextAreaComposicaoValores);

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel16.setText("Observações");

        jButtonConfirmarComposicaoValores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/mulacar/imagens/Button-Play-icon-24px.png"))); // NOI18N
        jButtonConfirmarComposicaoValores.setText("Confirmar");

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel17.setText("Composição dos Valores");

        jLabel18.setText("Valor Locação:");

        jLabel19.setText("Valor Acessorios:");

        jLabel20.setText("Valor Caução:");

        javax.swing.GroupLayout jPanelDadosComplementaresLayout = new javax.swing.GroupLayout(jPanelDadosComplementares);
        jPanelDadosComplementares.setLayout(jPanelDadosComplementaresLayout);
        jPanelDadosComplementaresLayout.setHorizontalGroup(
            jPanelDadosComplementaresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDadosComplementaresLayout.createSequentialGroup()
                .addGap(395, 395, 395)
                .addComponent(jLabel6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanelDadosComplementaresLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelDadosComplementaresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(jLabel7)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelDadosComplementaresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextFieldSituacaoDoTanque, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
                    .addComponent(jTextFieldDataDaRetirada)
                    .addComponent(jTextFieldDataDevolucaoPrevista)
                    .addComponent(jTextFieldKmInicial))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanelDadosComplementaresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelDadosComplementaresLayout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addGap(194, 194, 194))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelDadosComplementaresLayout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addGap(115, 115, 115)
                        .addComponent(jButtonConfirmarComposicaoValores)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelDadosComplementaresLayout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 513, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
            .addGroup(jPanelDadosComplementaresLayout.createSequentialGroup()
                .addGroup(jPanelDadosComplementaresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelDadosComplementaresLayout.createSequentialGroup()
                        .addGap(337, 337, 337)
                        .addGroup(jPanelDadosComplementaresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel18)
                            .addComponent(jLabel19)
                            .addComponent(jLabel20)
                            .addComponent(jLabel12))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanelDadosComplementaresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextFieldValorCaucao, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                            .addComponent(jTextFieldValorLocacao)
                            .addComponent(jTextFieldValorSeguro)
                            .addComponent(jTextFieldValorAcessorios)))
                    .addGroup(jPanelDadosComplementaresLayout.createSequentialGroup()
                        .addGap(167, 167, 167)
                        .addGroup(jPanelDadosComplementaresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanelDadosComplementaresLayout.createSequentialGroup()
                                .addComponent(jCheckBoxAcessorioCadeirinha)
                                .addGap(57, 57, 57)
                                .addComponent(jCheckBoxAcessorioSuporteParaBiciclet))
                            .addGroup(jPanelDadosComplementaresLayout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addGap(70, 70, 70)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanelDadosComplementaresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 513, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelDadosComplementaresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jButtonFinalizarCadastro)
                        .addGroup(jPanelDadosComplementaresLayout.createSequentialGroup()
                            .addGroup(jPanelDadosComplementaresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel14)
                                .addComponent(jLabel13))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanelDadosComplementaresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jTextFieldDataCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextFieldUsuarioCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 422, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        jPanelDadosComplementaresLayout.setVerticalGroup(
            jPanelDadosComplementaresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDadosComplementaresLayout.createSequentialGroup()
                .addComponent(jLabel6)
                .addGroup(jPanelDadosComplementaresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelDadosComplementaresLayout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(jPanelDadosComplementaresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jTextFieldSituacaoDoTanque, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel18)
                            .addComponent(jTextFieldValorLocacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24)
                        .addGroup(jPanelDadosComplementaresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(jTextFieldDataDaRetirada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldValorSeguro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12))
                        .addGap(18, 18, 18)
                        .addGroup(jPanelDadosComplementaresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(jTextFieldDataDevolucaoPrevista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldValorAcessorios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19))
                        .addGap(18, 18, 18)
                        .addGroup(jPanelDadosComplementaresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(jTextFieldKmInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel20)
                            .addComponent(jTextFieldValorCaucao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanelDadosComplementaresLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelDadosComplementaresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel17)
                            .addComponent(jButtonConfirmarComposicaoValores))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel16)
                .addGroup(jPanelDadosComplementaresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelDadosComplementaresLayout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(jPanelDadosComplementaresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15)
                            .addGroup(jPanelDadosComplementaresLayout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addGroup(jPanelDadosComplementaresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jCheckBoxAcessorioCadeirinha, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jCheckBoxAcessorioSuporteParaBiciclet)))))
                    .addGroup(jPanelDadosComplementaresLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelDadosComplementaresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jTextFieldDataCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanelDadosComplementaresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jTextFieldUsuarioCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonFinalizarCadastro))
        );

        jTabbedPaneDadosLocacao.addTab("Dados Complementares", jPanelDadosComplementares);

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel4.setText("Locação de Veículos");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Geral", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 16))); // NOI18N

        jRadioButtonLocacao.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jRadioButtonLocacao.setText("Locação");

        jRadioButtonReserva.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jRadioButtonReserva.setText("Reserva");

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel3.setText("Data Retirada");

        jComboBoxHoraRetirada.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "00:00" }));

        jLabel11.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel11.setText("Hora Retirada");

        jLabel21.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel21.setText("Data Devolução");

        jLabel22.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel22.setText("Hora Devolução");

        jComboBoxHoraDevolucao.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "00:00" }));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/mulacar/imagens/search-icon-16px.png"))); // NOI18N
        jButton1.setMnemonic('b');
        jButton1.setText("Buscar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jRadioButtonLocacao)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRadioButtonReserva)
                .addGap(36, 36, 36)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jXDatePickerDataRetirada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBoxHoraRetirada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(jLabel21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jXDatePickerDataDevolucao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBoxHoraDevolucao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jXDatePickerDataRetirada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxHoraRetirada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel21)
                    .addComponent(jXDatePickerDataDevolucao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22)
                    .addComponent(jComboBoxHoraDevolucao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jRadioButtonLocacao)
                    .addComponent(jRadioButtonReserva)
                    .addComponent(jButton1))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jTabbedPaneDadosLocacao))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(502, 502, 502)
                .addComponent(jLabel4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addComponent(jTabbedPaneDadosLocacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(138, 138, 138))
        );

        setSize(new java.awt.Dimension(1283, 687));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBoxSelecaoModeloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxSelecaoModeloActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jComboBoxSelecaoModeloActionPerformed

    private void jButtonBuscarVeiculosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBuscarVeiculosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonBuscarVeiculosActionPerformed

    private void jXDatePickerDataRetiradaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jXDatePickerDataRetiradaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jXDatePickerDataRetiradaActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
        try {
            LocalDate dataRetirada = jXDatePickerDataRetirada.getDate()
                                                             .toInstant()
                                                             .atZone(ZoneId.systemDefault())
                                                             .toLocalDate();

            LocalDate dataDevolucao = jXDatePickerDataDevolucao.getDate()
                                                               .toInstant()
                                                               .atZone(ZoneId.systemDefault())
                                                               .toLocalDate();

            if (dataRetirada.isAfter(dataDevolucao)) {
                JOptionPane.showMessageDialog(null, "Data de retirada não pode ser maior que a data de devolução.");    
                return;
            }
            
            

            if (jRadioButtonLocacao.isSelected()) {
                
            } else {

            }            
        } catch (Exception e) {
            
        }
        
//        LocacaoBll.consultarLocacoesDisponiveisPorPeriodo();
        
    }//GEN-LAST:event_jButton1ActionPerformed

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
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LocacaoVeiculosApp1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LocacaoVeiculosApp1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LocacaoVeiculosApp1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LocacaoVeiculosApp1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                LocacaoVeiculosApp1 dialog = new LocacaoVeiculosApp1(new javax.swing.JFrame(), true);
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
    private javax.swing.ButtonGroup buttonGroupLocacaoReserva;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonBuscarCliente;
    private javax.swing.JButton jButtonBuscarMotorista;
    private javax.swing.JButton jButtonBuscarVeiculos;
    private javax.swing.JButton jButtonConfirmarComposicaoValores;
    private javax.swing.JButton jButtonFinalizarCadastro;
    private javax.swing.JButton jButtonInserirCliente;
    private javax.swing.JButton jButtonInserirMotorista;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBoxAcessorioCadeirinha;
    private javax.swing.JCheckBox jCheckBoxAcessorioSuporteParaBiciclet;
    private javax.swing.JComboBox<String> jComboBoxHoraDevolucao;
    private javax.swing.JComboBox<String> jComboBoxHoraRetirada;
    private javax.swing.JComboBox jComboBoxSelecaoCategoria;
    private javax.swing.JComboBox jComboBoxSelecaoModelo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelSelecioneCategoria;
    private javax.swing.JLabel jLabelTituloTabVeiculo;
    private javax.swing.JLabel jLabelselecioneModelo;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelDadosComplementares;
    private javax.swing.JPanel jPanelDadosDoCliente;
    private javax.swing.JPanel jPanelDadosDoMotorista;
    private javax.swing.JPanel jPanelSelecaoDoVeiculo;
    private javax.swing.JRadioButton jRadioButtonLocacao;
    private javax.swing.JRadioButton jRadioButtonReserva;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPaneDadosDoCliente;
    private javax.swing.JScrollPane jScrollPaneDadosDoCliente1;
    private javax.swing.JScrollPane jScrollPaneListaDeClientes;
    private javax.swing.JScrollPane jScrollPaneListaDeClientes1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTabbedPane jTabbedPaneDadosLocacao;
    private javax.swing.JTable jTableDadosDoCliente;
    private javax.swing.JTable jTableDadosDoCliente1;
    private javax.swing.JTable jTableVeiculos;
    private javax.swing.JTextArea jTextAreaComposicaoValores;
    private javax.swing.JTextArea jTextAreaDadosDoCliente;
    private javax.swing.JTextArea jTextAreaDadosDoCliente1;
    private javax.swing.JTextArea jTextAreaObservacao;
    private javax.swing.JTextField jTextFieldCpfCnpj;
    private javax.swing.JTextField jTextFieldCpfCnpj1;
    private javax.swing.JTextField jTextFieldDataCadastro;
    private javax.swing.JTextField jTextFieldDataDaRetirada;
    private javax.swing.JTextField jTextFieldDataDevolucaoPrevista;
    private javax.swing.JTextField jTextFieldKmInicial;
    private javax.swing.JTextField jTextFieldNomeCliente;
    private javax.swing.JTextField jTextFieldNomeCliente1;
    private javax.swing.JTextField jTextFieldRazaoSocial;
    private javax.swing.JTextField jTextFieldSituacaoDoTanque;
    private javax.swing.JTextField jTextFieldUsuarioCadastro;
    private javax.swing.JTextField jTextFieldValorAcessorios;
    private javax.swing.JTextField jTextFieldValorCaucao;
    private javax.swing.JTextField jTextFieldValorLocacao;
    private javax.swing.JTextField jTextFieldValorSeguro;
    private org.jdesktop.swingx.JXDatePicker jXDatePickerDataDevolucao;
    private org.jdesktop.swingx.JXDatePicker jXDatePickerDataRetirada;
    // End of variables declaration//GEN-END:variables
}
