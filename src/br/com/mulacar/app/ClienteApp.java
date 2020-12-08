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
import br.com.mulacar.bll.ContatoBll;
import br.com.mulacar.bll.EnderecoBll;
import br.com.mulacar.enumeration.EnumStatus;
import br.com.mulacar.enumeration.EnumUF;
import br.com.mulacar.enumeration.EnumTipoCliente;
import br.com.mulacar.enumeration.EnumTipoEndereco;
import br.com.mulacar.enumeration.EnumTipoTelefone;
import br.com.mulacar.exception.MulaCarException;
import br.com.mulacar.model.Cliente;
import br.com.mulacar.model.Contato;
import br.com.mulacar.model.Endereco;
import br.com.mulacar.util.UtilComponentes;
import br.com.mulacar.util.UtilObjetos;
import br.com.mulacar.util.UtilString;
import br.com.mulacar.util.UtilTabela;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class ClienteApp extends javax.swing.JDialog {

    private static final Logger LOG = Logger.getLogger(ClienteApp.class.getName());
    
    private static final int LIMITE_CARACTERES_CPF = 11;
    
    private static final int LIMITE_CARACTERES_CEP = 8;
    
    private List<Contato> contatos;

    private ClienteBll clienteBll;
    
    private EnderecoBll enderecoBll;
    
    private ContatoBll contatoBll;
    
    private List<Endereco> enderecos;
    
    private boolean ambienteDesenvolvimento = false;
    
    private String mensagemErroPadrao;
    
    private int idEnderecoSelecinadoTabela;
    
    private int idContatoSelecinadoTabela;
    
    public ClienteApp(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        
        initComponents();
        
        inicializar();
    }
    
    private void inicializar() {
        
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        setTitle("Manutenção de Clientes");

        setResizable(false);    
        
        jButtonRemoverEnderecoTabela.setEnabled(false);
        
        jButtonRemoverContatoTabela.setEnabled(false);
        
        jButtonIncluirEnderecoTeste.setVisible(false);
        
        this.mensagemErroPadrao = "Entre em contato com suporte";

        this.clienteBll = new ClienteBll();
        
        this.enderecoBll = new EnderecoBll();
        
        this.contatoBll = new ContatoBll();
        
        this.contatos = new ArrayList();
        
        this.enderecos = new ArrayList();
        
        this.adicionarMouseListenerTabelaContatos();
        
        this.adicionarMouseListenerTabelaEnderecos();

        this.reiniciarTodosCampos();
        
        UtilComponentes.adicionarKeyListenerlimitaQuantidadeCaracteresJTextField(jTextFieldCpf, LIMITE_CARACTERES_CPF);
        
        UtilComponentes.adicionarKeyListenerlimitaQuantidadeCaracteresJTextField(jTextFieldCEP, LIMITE_CARACTERES_CEP);
        
        UtilComponentes.adicionarKeyListenerSomenteLetras(jTextFieldNome, 
                                                        jTextFieldOrgaoEmissor,
                                                        jTextFieldRazaoSocial,
                                                        jTextFieldNomeFantasia,
                                                        jTextFieldRua,
                                                        jTextFieldComplemento,
                                                        jTextFieldCidade,
                                                        jTextFieldBairro);
        
        
        UtilComponentes.adicionarKeyListenerSomenteNumeros(jTextFieldCpf, 
                                                        jTextFieldRg,
                                                        jTextFieldCnpj,
                                                        jTextFieldCEP,
                                                        jTextFieldNumero,
                                                        jTextFieldNumeroTelefone);
        
        UtilString.ehEmailValido(jTextField1Email.getText());
        
        jTextFieldNome.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
//                TODO: BLOQUEAR CAMPOS DE PESSOA JURIDICA
            }
            
        });
        
        UtilTabela.limparTabelas(jTableContatos, jTableEnderecos);
        
//      PARA PRODUÇÃO ATRIBUIR FALSE  
        this.ambienteDesenvolvimento = true;
        
        if (ambienteDesenvolvimento) {
            this.preecherDadosTesteCliente();
            this.jButtonIncluirEnderecoTeste.setVisible(true);
        }
        
    }    
    
   

    private void adicionarMouseListenerTabelaEnderecos() {
        jTableEnderecos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                int linha = jTableEnderecos.getSelectedRow();
                
                idEnderecoSelecinadoTabela = Integer.parseInt(jTableEnderecos.getValueAt(linha, 0).toString());
                
                habilitarDesabilitarBotaoRemoverItemTabela(jTableEnderecos, jButtonRemoverEnderecoTabela);
            }
        });
    }
    
    private void adicionarMouseListenerTabelaContatos() {
        jTableContatos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                int linha = jTableContatos.getSelectedRow();
                
                idContatoSelecinadoTabela = Integer.parseInt(jTableContatos.getValueAt(linha, 0).toString());
                
                habilitarDesabilitarBotaoRemoverItemTabela(jTableContatos, jButtonRemoverContatoTabela);
            }
        });
    }    

    private void carregarComboBoxes() {
        Vector<EnumUF> ufs = EnumUF.carregarUnidadesFederativas();
        jComboBoxUf.setModel(new DefaultComboBoxModel(ufs));
        jComboBoxUf.setSelectedItem(EnumUF.ACRE);
        
        Vector<EnumTipoEndereco> tiposEndereco = EnumTipoEndereco.carregarTiposEndereco();
        jComboBoxTipoEndereco.setModel(new DefaultComboBoxModel(tiposEndereco));
        
        Vector<EnumTipoTelefone> tiposTelefone = EnumTipoTelefone.carregarTiposTelefones();
        jComboBoxTipoTelefone.setModel(new DefaultComboBoxModel(tiposTelefone));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPaneCliente = new javax.swing.JTabbedPane();
        jPanelPessoaFisica = new javax.swing.JPanel();
        jLabelNome = new javax.swing.JLabel();
        jTextFieldNome = new javax.swing.JTextField();
        jLabelRgOuRazaoSocial = new javax.swing.JLabel();
        jTextFieldRg = new javax.swing.JTextField();
        jLabelOrgaoEmissor = new javax.swing.JLabel();
        jTextFieldOrgaoEmissor = new javax.swing.JTextField();
        jLabelCpjCnpj = new javax.swing.JLabel();
        jTextFieldCpf = new javax.swing.JTextField();
        jButtonIrPFisicaParaTabEndereco = new javax.swing.JButton();
        jButtonLimparCamposPessoaFisica = new javax.swing.JButton();
        jPanelPessoaJuridica = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldRazaoSocial = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldCnpj = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jTextFieldNomeFantasia = new javax.swing.JTextField();
        jButtonIrParaTabEndereco = new javax.swing.JButton();
        jPanelEndereco = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jComboBoxTipoEndereco = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldCEP = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextFieldRua = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTextFieldNumero = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jTextFieldComplemento = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jTextFieldBairro = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jTextFieldCidade = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jComboBoxUf = new javax.swing.JComboBox<>();
        jButtonIrParaTabContato = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableEnderecos = new javax.swing.JTable();
        jButtonIncluirEnderecoTeste = new javax.swing.JButton();
        jButtonRemoverEnderecoTabela = new javax.swing.JButton();
        jButtonAdicionarEnderecoTabela = new javax.swing.JButton();
        jPanelContato = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jComboBoxTipoTelefone = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        jTextFieldNumeroTelefone = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jTextField1Email = new javax.swing.JTextField();
        jButtonFechar = new javax.swing.JButton();
        jButtonConcluirCadastro = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableContatos = new javax.swing.JTable();
        jButtonAdicionarContatoTabela = new javax.swing.JButton();
        jButtonRemoverContatoTabela = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jTabbedPaneCliente.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        jTabbedPaneCliente.setToolTipText("");
        jTabbedPaneCliente.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jTabbedPaneCliente.setPreferredSize(new java.awt.Dimension(600, 480));

        jLabelNome.setText("Nome: ");

        jLabelRgOuRazaoSocial.setText("RG:");

        jLabelOrgaoEmissor.setText("Org. Emissor: ");

        jLabelCpjCnpj.setText("CPF:");

        jButtonIrPFisicaParaTabEndereco.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/mulacar/imagens/seta-continuar.png"))); // NOI18N
        jButtonIrPFisicaParaTabEndereco.setMnemonic('c');
        jButtonIrPFisicaParaTabEndereco.setText("Continuar");
        jButtonIrPFisicaParaTabEndereco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonIrPFisicaParaTabEnderecoActionPerformed(evt);
            }
        });

        jButtonLimparCamposPessoaFisica.setText("Limpar Campos");
        jButtonLimparCamposPessoaFisica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLimparCamposPessoaFisicaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelPessoaFisicaLayout = new javax.swing.GroupLayout(jPanelPessoaFisica);
        jPanelPessoaFisica.setLayout(jPanelPessoaFisicaLayout);
        jPanelPessoaFisicaLayout.setHorizontalGroup(
            jPanelPessoaFisicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPessoaFisicaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelPessoaFisicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelPessoaFisicaLayout.createSequentialGroup()
                        .addGroup(jPanelPessoaFisicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelOrgaoEmissor)
                            .addComponent(jLabelRgOuRazaoSocial))
                        .addGap(18, 18, 18)
                        .addGroup(jPanelPessoaFisicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldRg, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldOrgaoEmissor, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanelPessoaFisicaLayout.createSequentialGroup()
                        .addGroup(jPanelPessoaFisicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelCpjCnpj)
                            .addComponent(jLabelNome))
                        .addGap(59, 59, 59)
                        .addComponent(jTextFieldNome, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelPessoaFisicaLayout.createSequentialGroup()
                        .addGap(98, 98, 98)
                        .addComponent(jTextFieldCpf, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 305, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelPessoaFisicaLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonLimparCamposPessoaFisica)
                .addGap(18, 18, 18)
                .addComponent(jButtonIrPFisicaParaTabEndereco)
                .addContainerGap())
        );
        jPanelPessoaFisicaLayout.setVerticalGroup(
            jPanelPessoaFisicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPessoaFisicaLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanelPessoaFisicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelNome))
                .addGap(18, 18, 18)
                .addGroup(jPanelPessoaFisicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelCpjCnpj)
                    .addComponent(jTextFieldCpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelPessoaFisicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelRgOuRazaoSocial)
                    .addComponent(jTextFieldRg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelPessoaFisicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldOrgaoEmissor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelOrgaoEmissor))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 225, Short.MAX_VALUE)
                .addGroup(jPanelPessoaFisicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButtonLimparCamposPessoaFisica, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonIrPFisicaParaTabEndereco, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTabbedPaneCliente.addTab("Pessoa Física", jPanelPessoaFisica);

        jPanelPessoaJuridica.setPreferredSize(new java.awt.Dimension(600, 454));

        jLabel1.setText("Razão Social");

        jLabel2.setText("CNPJ");

        jLabel15.setText("Nome Fantasia");

        jButtonIrParaTabEndereco.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/mulacar/imagens/seta-continuar.png"))); // NOI18N
        jButtonIrParaTabEndereco.setMnemonic('c');
        jButtonIrParaTabEndereco.setText("Continuar");
        jButtonIrParaTabEndereco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonIrParaTabEnderecoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelPessoaJuridicaLayout = new javax.swing.GroupLayout(jPanelPessoaJuridica);
        jPanelPessoaJuridica.setLayout(jPanelPessoaJuridicaLayout);
        jPanelPessoaJuridicaLayout.setHorizontalGroup(
            jPanelPessoaJuridicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPessoaJuridicaLayout.createSequentialGroup()
                .addGroup(jPanelPessoaJuridicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelPessoaJuridicaLayout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addGroup(jPanelPessoaJuridicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel15)
                            .addComponent(jLabel2))
                        .addGap(21, 21, 21)
                        .addComponent(jTextFieldNomeFantasia, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelPessoaJuridicaLayout.createSequentialGroup()
                        .addGap(110, 110, 110)
                        .addComponent(jTextFieldRazaoSocial, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelPessoaJuridicaLayout.createSequentialGroup()
                        .addGap(110, 110, 110)
                        .addComponent(jTextFieldCnpj, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 299, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelPessoaJuridicaLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButtonIrParaTabEndereco)
                .addContainerGap())
        );
        jPanelPessoaJuridicaLayout.setVerticalGroup(
            jPanelPessoaJuridicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPessoaJuridicaLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanelPessoaJuridicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldRazaoSocial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(jPanelPessoaJuridicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jTextFieldNomeFantasia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelPessoaJuridicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldCnpj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 267, Short.MAX_VALUE)
                .addComponent(jButtonIrParaTabEndereco)
                .addContainerGap())
        );

        jTabbedPaneCliente.addTab("Pessoa Jurídica", jPanelPessoaJuridica);

        jLabel3.setText("Tipo");

        jLabel4.setText("CEP");

        jLabel5.setText("Rua");

        jLabel6.setText("Número");

        jLabel7.setText("Complemento");

        jLabel8.setText("Bairro");

        jLabel9.setText("Cidade");

        jLabel10.setText("Estado:");

        jComboBoxUf.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButtonIrParaTabContato.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/mulacar/imagens/seta-continuar.png"))); // NOI18N
        jButtonIrParaTabContato.setMnemonic('c');
        jButtonIrParaTabContato.setText("Continuar");
        jButtonIrParaTabContato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonIrParaTabContatoActionPerformed(evt);
            }
        });

        jTableEnderecos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Tipo", "Cep", "Rua", "Número", "Compl.", "Bairro", "Cidade", "Estado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTableEnderecos);
        if (jTableEnderecos.getColumnModel().getColumnCount() > 0) {
            jTableEnderecos.getColumnModel().getColumn(0).setPreferredWidth(3);
            jTableEnderecos.getColumnModel().getColumn(1).setPreferredWidth(10);
            jTableEnderecos.getColumnModel().getColumn(2).setPreferredWidth(10);
            jTableEnderecos.getColumnModel().getColumn(3).setPreferredWidth(20);
            jTableEnderecos.getColumnModel().getColumn(4).setPreferredWidth(5);
            jTableEnderecos.getColumnModel().getColumn(5).setPreferredWidth(20);
            jTableEnderecos.getColumnModel().getColumn(6).setPreferredWidth(15);
            jTableEnderecos.getColumnModel().getColumn(7).setPreferredWidth(15);
            jTableEnderecos.getColumnModel().getColumn(8).setPreferredWidth(3);
        }

        jButtonIncluirEnderecoTeste.setMnemonic('p');
        jButtonIncluirEnderecoTeste.setText("preencher Endereço para Teste");
        jButtonIncluirEnderecoTeste.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonIncluirEnderecoTesteActionPerformed(evt);
            }
        });

        jButtonRemoverEnderecoTabela.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/mulacar/imagens/Action-remove-icon-24px.png"))); // NOI18N
        jButtonRemoverEnderecoTabela.setMnemonic('r');
        jButtonRemoverEnderecoTabela.setText("Remover");
        jButtonRemoverEnderecoTabela.setToolTipText("Selecione o endereço na tabela e clique aqui para excluir.");
        jButtonRemoverEnderecoTabela.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRemoverEnderecoTabelaActionPerformed(evt);
            }
        });

        jButtonAdicionarEnderecoTabela.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/mulacar/imagens/Apps-File-New-icon-24px.png"))); // NOI18N
        jButtonAdicionarEnderecoTabela.setMnemonic('a');
        jButtonAdicionarEnderecoTabela.setText("Adicionar");
        jButtonAdicionarEnderecoTabela.setToolTipText("Preencha os dados do endereço e clique aqui para adicionar. na tabela.");
        jButtonAdicionarEnderecoTabela.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAdicionarEnderecoTabelaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelEnderecoLayout = new javax.swing.GroupLayout(jPanelEndereco);
        jPanelEndereco.setLayout(jPanelEnderecoLayout);
        jPanelEnderecoLayout.setHorizontalGroup(
            jPanelEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelEnderecoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelEnderecoLayout.createSequentialGroup()
                        .addComponent(jButtonIncluirEnderecoTeste)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonIrParaTabContato))
                    .addGroup(jPanelEnderecoLayout.createSequentialGroup()
                        .addGroup(jPanelEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelEnderecoLayout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(18, 18, 18)
                                .addComponent(jTextFieldComplemento, javax.swing.GroupLayout.PREFERRED_SIZE, 433, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelEnderecoLayout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(55, 55, 55)
                                .addComponent(jTextFieldCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel10)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBoxUf, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanelEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelEnderecoLayout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelEnderecoLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jButtonRemoverEnderecoTabela))))
                    .addGroup(jPanelEnderecoLayout.createSequentialGroup()
                        .addGroup(jPanelEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelEnderecoLayout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(73, 73, 73)
                                .addComponent(jComboBoxTipoEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(jTextFieldCEP))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelEnderecoLayout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(75, 75, 75)
                                .addComponent(jTextFieldRua, javax.swing.GroupLayout.PREFERRED_SIZE, 433, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(12, 12, 12)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanelEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelEnderecoLayout.createSequentialGroup()
                                .addComponent(jButtonAdicionarEnderecoTabela)
                                .addGap(0, 136, Short.MAX_VALUE))
                            .addComponent(jTextFieldBairro, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextFieldNumero))))
                .addContainerGap())
        );
        jPanelEnderecoLayout.setVerticalGroup(
            jPanelEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelEnderecoLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanelEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jComboBoxTipoEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldCEP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(jPanelEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldRua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jTextFieldNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(jPanelEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jTextFieldComplemento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldBairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addGroup(jPanelEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButtonRemoverEnderecoTabela)
                        .addComponent(jButtonAdicionarEnderecoTabela))
                    .addGroup(jPanelEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jComboBoxUf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel10)
                        .addComponent(jTextFieldCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel9)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanelEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonIrParaTabContato)
                    .addComponent(jButtonIncluirEnderecoTeste))
                .addGap(15, 15, 15))
        );

        jTabbedPaneCliente.addTab("Endereço", jPanelEndereco);

        jLabel11.setText("Tipo Telefone");

        jLabel13.setText("Telefone:");

        jLabel14.setText("E-mail:");

        jButtonFechar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/mulacar/imagens/Actions-window-close-icon-24px.png"))); // NOI18N
        jButtonFechar.setMnemonic('f');
        jButtonFechar.setText("Fechar");
        jButtonFechar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonFecharActionPerformed(evt);
            }
        });

        jButtonConcluirCadastro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/mulacar/imagens/Actions-document-save-icon-24px.png"))); // NOI18N
        jButtonConcluirCadastro.setMnemonic('c');
        jButtonConcluirCadastro.setText("Concluir");
        jButtonConcluirCadastro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonConcluirCadastroActionPerformed(evt);
            }
        });

        jTableContatos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "E-mail", "Tipo Telefone", "Número"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableContatos.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTableContatos);
        if (jTableContatos.getColumnModel().getColumnCount() > 0) {
            jTableContatos.getColumnModel().getColumn(1).setPreferredWidth(10);
            jTableContatos.getColumnModel().getColumn(2).setPreferredWidth(60);
        }

        jButtonAdicionarContatoTabela.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/mulacar/imagens/Apps-File-New-icon-24px.png"))); // NOI18N
        jButtonAdicionarContatoTabela.setMnemonic('a');
        jButtonAdicionarContatoTabela.setText("Adicionar");
        jButtonAdicionarContatoTabela.setToolTipText("Preencha os dados do endereço e clique aqui para adicionar. na tabela.");
        jButtonAdicionarContatoTabela.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAdicionarContatoTabelaActionPerformed(evt);
            }
        });

        jButtonRemoverContatoTabela.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/mulacar/imagens/Action-remove-icon-24px.png"))); // NOI18N
        jButtonRemoverContatoTabela.setMnemonic('r');
        jButtonRemoverContatoTabela.setText("Remover");
        jButtonRemoverContatoTabela.setToolTipText("Selecione o endereço na tabela e clique aqui para excluir.");
        jButtonRemoverContatoTabela.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRemoverContatoTabelaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelContatoLayout = new javax.swing.GroupLayout(jPanelContato);
        jPanelContato.setLayout(jPanelContatoLayout);
        jPanelContatoLayout.setHorizontalGroup(
            jPanelContatoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelContatoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelContatoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelContatoLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButtonConcluirCadastro)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonFechar))
                    .addGroup(jPanelContatoLayout.createSequentialGroup()
                        .addGroup(jPanelContatoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13)
                            .addComponent(jLabel11)
                            .addComponent(jLabel14))
                        .addGap(31, 31, 31)
                        .addGroup(jPanelContatoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField1Email, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanelContatoLayout.createSequentialGroup()
                                .addComponent(jButtonAdicionarContatoTabela)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButtonRemoverContatoTabela, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelContatoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jComboBoxTipoTelefone, javax.swing.GroupLayout.Alignment.LEADING, 0, 237, Short.MAX_VALUE)
                                .addComponent(jTextFieldNumeroTelefone, javax.swing.GroupLayout.Alignment.LEADING)))
                        .addGap(0, 290, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 847, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanelContatoLayout.setVerticalGroup(
            jPanelContatoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelContatoLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanelContatoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jTextField1Email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelContatoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxTipoTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addGap(18, 18, 18)
                .addGroup(jPanelContatoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jTextFieldNumeroTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelContatoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonRemoverContatoTabela)
                    .addComponent(jButtonAdicionarContatoTabela))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addGroup(jPanelContatoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonFechar)
                    .addComponent(jButtonConcluirCadastro))
                .addGap(19, 19, 19))
        );

        jTabbedPaneCliente.addTab("Contato", jPanelContato);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPaneCliente, javax.swing.GroupLayout.DEFAULT_SIZE, 861, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPaneCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(7, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonIrPFisicaParaTabEnderecoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonIrPFisicaParaTabEnderecoActionPerformed
        jTabbedPaneCliente.setSelectedIndex(2);
    }//GEN-LAST:event_jButtonIrPFisicaParaTabEnderecoActionPerformed

    private void jButtonIrParaTabEnderecoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonIrParaTabEnderecoActionPerformed
        jTabbedPaneCliente.setSelectedIndex(2);
    }//GEN-LAST:event_jButtonIrParaTabEnderecoActionPerformed

    private void jButtonConcluirCadastroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonConcluirCadastroActionPerformed
        try {
    //      Pessoa Física        
            String nome         = jTextFieldNome.getText();
            String cpf          = jTextFieldCpf.getText();
            String rg           = jTextFieldRg.getText();
            String orgaoEmissor = jTextFieldOrgaoEmissor.getText();

    //      Pessoa Jurídica
            String cnpj         = jTextFieldCnpj.getText();
            String razaoSocial  = jTextFieldRazaoSocial.getText();
            String nomeFantasia = jTextFieldNomeFantasia.getText();

            Cliente cliente = preencherCliente(cpf, nome, rg, orgaoEmissor, razaoSocial, cnpj, nomeFantasia);

            Cliente clienteBanco = clienteBll.adicionarCliente(cliente);

            preencherEnderecoComCliente(this.enderecos, clienteBanco);
            
            enderecoBll.adicionarEnderecos(this.enderecos);
            
            preencherContatosComCliente(this.contatos, clienteBanco);

            contatoBll.adicionarContatos(this.contatos);
            
            UtilTabela.limparTabelas(jTableContatos);
            
            this.contatos = new ArrayList<>();
            
            UtilTabela.limparTabelas(jTableEnderecos);
            
            this.enderecos = new ArrayList();
            
            reiniciarTodosCampos();
            
            JOptionPane.showMessageDialog(null, "Cliente Salvo com sucesso!!");
            
        } catch (Exception e) {
            if (!(e instanceof MulaCarException)) {
                JOptionPane.showMessageDialog(null, "Ocorreu um erro ao salvar o cliente. Entre em contato com suporte!");
            } else {
                JOptionPane.showMessageDialog(null, e.getMessage());            
            }
        }
    }//GEN-LAST:event_jButtonConcluirCadastroActionPerformed

    private void preencherEnderecoComCliente(List<Endereco> enderecos, Cliente cliente) {
        for (Endereco end: enderecos) {
            end.setCliente(cliente);
        }
    }
    
    private void preencherContatosComCliente(List<Contato> contatos, Cliente cliente) {
        for (Contato cont: contatos) {
            cont.setCliente(cliente);
        }
    }    

    private Cliente preencherCliente(String cpf, String nome, String rg, String orgaoEmissor, String razaoSocial, String cnpj, String nomeFantasia) {
        
        Cliente cliente = new Cliente();
        
        cliente.setStatus(EnumStatus.ATIVO);
        
        if (!UtilObjetos.ehNuloOuVazio(cpf)) {
            cliente.setTipoCliente(EnumTipoCliente.PESSOA_FISICA);
            cliente.setNome(nome);
            cliente.setCpfCnpj(cpf);
            cliente.setRg(rg);
            cliente.setOrgaoEmissor(orgaoEmissor);
        } else {
            cliente.setTipoCliente(EnumTipoCliente.PESSOA_JURIDICA);
            cliente.setRazaoSocial(razaoSocial);
            cliente.setCpfCnpj(cnpj);
            cliente.setNome(nomeFantasia);
        }
        
        return cliente;
    }

    private void jButtonFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFecharActionPerformed
        try {
            this.dispose();

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Não foi possível fechar a janela!");
        }
    }//GEN-LAST:event_jButtonFecharActionPerformed

    private void jButtonIrParaTabContatoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonIrParaTabContatoActionPerformed
        jTabbedPaneCliente.setSelectedIndex(3);
    }//GEN-LAST:event_jButtonIrParaTabContatoActionPerformed

    private void jButtonIncluirEnderecoTesteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonIncluirEnderecoTesteActionPerformed
            jComboBoxTipoEndereco.setSelectedItem(EnumTipoEndereco.RESIDENCIAL);
            jTextFieldCEP.setText("74356400");
            jTextFieldRua.setText("Rua teste 2");
            jTextFieldNumero.setText("543");
            jTextFieldComplemento.setText("Complemento Teste 2");
            jTextFieldBairro.setText("Bairro teste 2");
            jTextFieldCidade.setText("Cidade Teste 2");
            jComboBoxUf.setSelectedItem(EnumUF.ESPIRITO_SANTO);        
    }//GEN-LAST:event_jButtonIncluirEnderecoTesteActionPerformed

    private void jButtonRemoverEnderecoTabelaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRemoverEnderecoTabelaActionPerformed
        try {
            String pergunta = "Remover o endereço da tabela?";
            String tituloJanela = "Endereço";
            Icon icone =new ImageIcon(getClass().getResource("/br/com/mulacar/imagens/Delete-icon - x - 24 x 24px.png"));
            String[] botaoOpcoes = {"Sim", "Não"};
            String label = botaoOpcoes[0];
            
            int opcao = JOptionPane.showOptionDialog(this, 
                                                    pergunta,
                                                    tituloJanela,
                                                    JOptionPane.YES_NO_OPTION, 
                                                    JOptionPane.WARNING_MESSAGE,
                                                    null,
                                                    botaoOpcoes,
                                                    label);
            if (opcao == JOptionPane.YES_OPTION) {
                
                UtilTabela.limparTabelas(jTableEnderecos);
                
                int indiceEnderecoParaRemover = this.idEnderecoSelecinadoTabela - 1;
                
                this.enderecos.remove(indiceEnderecoParaRemover);
                
                this.recriarIdsTemporariosEnderecosTabela();
                
                this.preencherTabelaEndereco(this.enderecos);
                
                this.habilitarDesabilitarBotaoRemoverItemTabela(jTableEnderecos, jButtonRemoverEnderecoTabela);
                
                jButtonRemoverEnderecoTabela.setEnabled(false);
                
                JOptionPane.showMessageDialog(this, "Endereço Removido.");
            } 
        } catch (Exception erro) {
            LOG.log(Level.SEVERE, null, erro);
            JOptionPane.showMessageDialog(null, "Erro ao remover o endereço da tabela. " + mensagemErroPadrao);
        }
    }//GEN-LAST:event_jButtonRemoverEnderecoTabelaActionPerformed

    private void habilitarDesabilitarBotaoRemoverItemTabela(JTable tabela, JButton botao) {
        
        DefaultTableModel model = (DefaultTableModel) tabela.getModel();
        
        if (model.getRowCount() > 0) {
            botao.setEnabled(true);
        } else {
            botao.setEnabled(false);
        }
    }

    private void recriarIdsTemporariosEnderecosTabela() {
       
        for (int i = 0; i < this.enderecos.size(); i++) {
                this.enderecos.get(i).setId(i + 1);
        }
//        this.enderecos
//                .stream()
//                .filter((end) -> (end.getId() != 1))
//                .forEachOrdered((end) -> {end.setId(end.getId() - 1);        
    }
    
    private void recriarIdsTemporariosContatosTabela() {
        for (Contato cont : this.contatos) {
            if (cont.getId() != 1) {
                cont.setId(cont.getId() - 1);
            }
        }
    }    


    private void jButtonAdicionarEnderecoTabelaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAdicionarEnderecoTabelaActionPerformed
        try {
            EnumTipoEndereco tipoEndereco = (EnumTipoEndereco) jComboBoxTipoEndereco.getSelectedItem();
            String cep          = jTextFieldCEP.getText();
            String rua          = jTextFieldRua.getText();
            String numero       = jTextFieldNumero.getText();
            String complemento  = jTextFieldComplemento.getText();
            String bairro       = jTextFieldBairro.getText();
            String cidade       = jTextFieldCidade.getText();
            EnumUF uf           = (EnumUF) jComboBoxUf.getSelectedItem();  
            
            boolean temCamposVazios = UtilObjetos.ehNuloOuVazio(cep)
                                      || UtilObjetos.ehNuloOuVazio(tipoEndereco)
                                      || UtilObjetos.ehNuloOuVazio(rua)
                                      || UtilObjetos.ehNuloOuVazio(numero)           
                                      || UtilObjetos.ehNuloOuVazio(complemento)           
                                      || UtilObjetos.ehNuloOuVazio(bairro)            
                                      || UtilObjetos.ehNuloOuVazio(cidade)            
                                      || UtilObjetos.ehNuloOuVazio(uf);  
            
            if (temCamposVazios) {
                JOptionPane.showMessageDialog(null, "Campos obrigatórios devem ser preenchidos.");
                return;
            }         
            
            int idTemporario = this.enderecos.size() + 1;
            
            Endereco endereco = new Endereco(idTemporario);
            endereco.setCep(cep);
            endereco.setTipoEndereco(tipoEndereco);
            endereco.setRua(rua);
            endereco.setNumero(numero);
            endereco.setComplemento(complemento);
            endereco.setBairro(bairro);
            endereco.setCidade(cidade);
            endereco.setUf(uf);
            
            this.enderecos.add(endereco);

            UtilTabela.limparTabelas(jTableEnderecos);
        
            preencherTabelaEndereco(enderecos);
            
            UtilComponentes.limparCampos(jTextFieldCEP,
                                        jTextFieldRua,
                                        jTextFieldNumero,
                                        jTextFieldComplemento,
                                        jTextFieldBairro,
                                        jTextFieldCidade);
            
            this.carregarComboBoxes();
            
        } catch (Exception e) {
            Logger.getLogger(ClienteApp.class.getName()).log(Level.SEVERE, null, e);
            JOptionPane.showMessageDialog(null, "Problema ao adicionar endereco. Entre em contato com suporte.");            
        }
        
    }//GEN-LAST:event_jButtonAdicionarEnderecoTabelaActionPerformed

    private void jButtonAdicionarContatoTabelaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAdicionarContatoTabelaActionPerformed

        try {
            String email = jTextField1Email.getText();
            
            EnumTipoTelefone tipoTelefone = EnumTipoTelefone.fromDescricao(jComboBoxTipoTelefone.getSelectedItem().toString());
           
            String numeroTelefone = jTextFieldNumeroTelefone.getText();
            
            boolean temCamposVazios = UtilObjetos.ehNuloOuVazio(email)
                                      || UtilObjetos.ehNuloOuVazio(tipoTelefone)
                                      || UtilObjetos.ehNuloOuVazio(numeroTelefone);
            
            if (temCamposVazios) {
                JOptionPane.showMessageDialog(null, "Campos obrigatórios devem ser preenchidos.");
                jTextField1Email.setFocusable(true);
                return;
            }
            
            boolean emailValido = UtilString.ehEmailValido(email);
            
            if (!emailValido) {
                JOptionPane.showMessageDialog(null, "Preencha com um email validdo.");
                jTextField1Email.setFocusable(true);
                return;                
            }
            
            boolean jaExisteEmailNaTabela = this.contatos.stream()
                                            .map(Contato::getEmail)
                                            .filter(email::equals)
                                            .findFirst()
                                            .isPresent();
            
            if (jaExisteEmailNaTabela) {
                JOptionPane.showMessageDialog(null, "Já existe um contato cadastrado com esse email.");
                return;
            }
            
            int idTemporario = this.contatos.size() + 1;
            
            Contato contato = new Contato(idTemporario, email, tipoTelefone, numeroTelefone);

            this.contatos.add(contato);

            UtilTabela.limparTabelas(jTableContatos);
        
            preencherTabelaContato(this.contatos);
            
            UtilComponentes.limparCampos(jTextFieldNumeroTelefone, jTextField1Email);
            
            jTextField1Email.setFocusable(true);
            
        } catch (Exception ex) {
            Logger.getLogger(ClienteApp.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Problema ao adicionar telefone. Entre em contato com suporte.");
        }

    }//GEN-LAST:event_jButtonAdicionarContatoTabelaActionPerformed

    private void jButtonRemoverContatoTabelaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRemoverContatoTabelaActionPerformed
        try {
            String pergunta = "Remover contato da tabela?";
            String tituloJanela = "Contato";
            Icon icone =new ImageIcon(getClass().getResource("/br/com/mulacar/imagens/Delete-icon - x - 24 x 24px.png"));
            String[] botaoOpcoes = {"Sim", "Não"};
            String label = botaoOpcoes[0];
            
            int opcao = JOptionPane.showOptionDialog(this, 
                                                    pergunta,
                                                    tituloJanela,
                                                    JOptionPane.YES_NO_OPTION, 
                                                    JOptionPane.WARNING_MESSAGE,
                                                    null,
                                                    botaoOpcoes,
                                                    label);
            if (opcao == JOptionPane.YES_OPTION) {
                
                UtilTabela.limparTabelas(jTableContatos);
                
                int indiceContatoParaRemover = this.idContatoSelecinadoTabela - 1;
                
                this.contatos.remove(indiceContatoParaRemover);
                
                this.recriarIdsTemporariosContatosTabela();
                
                this.preencherTabelaContato(this.contatos);
                
                habilitarDesabilitarBotaoRemoverItemTabela(jTableContatos, jButtonRemoverContatoTabela);
                
                jButtonRemoverContatoTabela.setEnabled(false);
                
                JOptionPane.showMessageDialog(this, "Contato Removido.");
            } 
        } catch (Exception erro) {
            LOG.log(Level.SEVERE, null, erro);
            JOptionPane.showMessageDialog(null, "Erro ao remover o contato da tabela. " + mensagemErroPadrao);
        }

    }//GEN-LAST:event_jButtonRemoverContatoTabelaActionPerformed

    private void jButtonLimparCamposPessoaFisicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLimparCamposPessoaFisicaActionPerformed
        UtilComponentes.limparCampos(jTextFieldNome,
                                    jTextFieldCpf,
                                    jTextFieldRg,
                                    jTextFieldOrgaoEmissor);
    }//GEN-LAST:event_jButtonLimparCamposPessoaFisicaActionPerformed

    private void preencherTabelaContato(List<Contato> contatos) throws Exception {
        
        DefaultTableModel model = (DefaultTableModel) jTableContatos.getModel();
        
        model.setNumRows(0);
        
//        contatoBll.ordenaListaContatos(this.telefones);
        
        for (int i = 0; i < contatos.size(); i++) {
            String[] linha = new String[4];
            
            linha[0] = String.valueOf(contatos.get(i).getId());
            linha[1] = contatos.get(i).getEmail();
            linha[2] = contatos.get(i).getTipoTelefone().name();
            linha[3] = String.valueOf(contatos.get(i).getNumero());
            
            model.addRow(linha);
        }
    }
    
    private void preencherTabelaEndereco(List<Endereco> enderecos) throws Exception {
        
        DefaultTableModel model = (DefaultTableModel) jTableEnderecos.getModel();
        
        model.setNumRows(0);
        
//        contatoBll.ordenaListaContatos(this.telefones);
        
        for (int i = 0; i < enderecos.size(); i++) {
            
            String[] linha = new String[9];
            
            linha[0] = String.valueOf(enderecos.get(i).getId());
            linha[1] = enderecos.get(i).getTipoEndereco().name();
            linha[2] = enderecos.get(i).getCep();
            linha[3] = enderecos.get(i).getRua();
            linha[4] = enderecos.get(i).getNumero();
            linha[5] = enderecos.get(i).getComplemento();
            linha[6] = enderecos.get(i).getBairro();
            linha[7] = enderecos.get(i).getCidade();
            linha[8] = enderecos.get(i).getUf().sigla();
            
            model.addRow(linha);
        }
    }    
    

    
    public static void main(String args[]) {
        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ClienteApp dialog = new ClienteApp(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton jButtonAdicionarContatoTabela;
    private javax.swing.JButton jButtonAdicionarEnderecoTabela;
    private javax.swing.JButton jButtonConcluirCadastro;
    private javax.swing.JButton jButtonFechar;
    private javax.swing.JButton jButtonIncluirEnderecoTeste;
    private javax.swing.JButton jButtonIrPFisicaParaTabEndereco;
    private javax.swing.JButton jButtonIrParaTabContato;
    private javax.swing.JButton jButtonIrParaTabEndereco;
    private javax.swing.JButton jButtonLimparCamposPessoaFisica;
    private javax.swing.JButton jButtonRemoverContatoTabela;
    private javax.swing.JButton jButtonRemoverEnderecoTabela;
    private javax.swing.JComboBox<String> jComboBoxTipoEndereco;
    private javax.swing.JComboBox<String> jComboBoxTipoTelefone;
    private javax.swing.JComboBox<String> jComboBoxUf;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelCpjCnpj;
    private javax.swing.JLabel jLabelNome;
    private javax.swing.JLabel jLabelOrgaoEmissor;
    private javax.swing.JLabel jLabelRgOuRazaoSocial;
    private javax.swing.JPanel jPanelContato;
    private javax.swing.JPanel jPanelEndereco;
    private javax.swing.JPanel jPanelPessoaFisica;
    private javax.swing.JPanel jPanelPessoaJuridica;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPaneCliente;
    private javax.swing.JTable jTableContatos;
    private javax.swing.JTable jTableEnderecos;
    private javax.swing.JTextField jTextField1Email;
    private javax.swing.JTextField jTextFieldBairro;
    private javax.swing.JTextField jTextFieldCEP;
    private javax.swing.JTextField jTextFieldCidade;
    private javax.swing.JTextField jTextFieldCnpj;
    private javax.swing.JTextField jTextFieldComplemento;
    private javax.swing.JTextField jTextFieldCpf;
    private javax.swing.JTextField jTextFieldNome;
    private javax.swing.JTextField jTextFieldNomeFantasia;
    private javax.swing.JTextField jTextFieldNumero;
    private javax.swing.JTextField jTextFieldNumeroTelefone;
    private javax.swing.JTextField jTextFieldOrgaoEmissor;
    private javax.swing.JTextField jTextFieldRazaoSocial;
    private javax.swing.JTextField jTextFieldRg;
    private javax.swing.JTextField jTextFieldRua;
    // End of variables declaration//GEN-END:variables

    private void preecherDadosTesteCliente() {
            jComboBoxTipoEndereco.setSelectedItem(EnumTipoEndereco.RESIDENCIAL);
            jTextFieldCEP.setText("74000000");
            jTextFieldRua.setText("Rua teste");
            jTextFieldNumero.setText("12354");
            jTextFieldComplemento.setText("Complemento Teste");
            jTextFieldBairro.setText("Bairro teste");
            jTextFieldCidade.setText("Cidade Teste");
            jComboBoxUf.setSelectedItem(EnumUF.GOIAS);

    //      Contato
            jTextField1Email.setText("teste@gmail.com");
            jComboBoxTipoTelefone.setSelectedItem(EnumTipoTelefone.CELULAR);
            jTextFieldNumeroTelefone.setText("62981903202");

    //      Pessoa Física        
            jTextFieldNome.setText("Fulano da Silva");
            jTextFieldCpf.setText("00535650023");
            jTextFieldRg.setText("406735");
            jTextFieldOrgaoEmissor.setText("SSP");

    //      Pessoa Jurídica
//            jTextFieldCnpj.getText();
//            jTextFieldRazaoSocial.getText();
//            jTextFieldNomeFantasia.getText();  
    }

    private void reiniciarTodosCampos() {
        UtilComponentes.limparCampos(jTextFieldNome,
                                    jTextFieldCpf,
                                    jTextFieldRg,
                                    jTextFieldOrgaoEmissor,
                                    jTextFieldCnpj,
                                    jTextFieldRazaoSocial,
                                    jTextFieldNomeFantasia,
                                    jTextFieldCEP,
                                    jTextFieldRua,
                                    jTextFieldNumero,
                                    jTextFieldComplemento,
                                    jTextFieldBairro,
                                    jTextFieldCidade,
                                    jTextField1Email,
                                    jTextFieldNumeroTelefone);
        
        UtilTabela.limparTabelas(jTableContatos, jTableEnderecos);
    
        this.carregarComboBoxes();
        
    }

}
