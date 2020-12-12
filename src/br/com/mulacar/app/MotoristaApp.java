/*
 * Novembro/Dezembro 2020.
 * Senai Fatesg Faculdade de Tecnologia
 * ADS - Análise e Desenvolvimento de Sistemas
 * Projeto Integrador - ADS3
 * Projeto Mula Car - aluguel de Veículos
 * Alunos: Aires Ribeiro, Gabriel Cunha, Lucas França e Rogério Reis
 */
package br.com.mulacar.app;

import br.com.mulacar.bll.MotoristaBll;
import br.com.mulacar.bll.ContatoBll;
import br.com.mulacar.bll.EnderecoBll;
import br.com.mulacar.enumeration.EnumStatus;
import br.com.mulacar.enumeration.EnumUF;
import br.com.mulacar.enumeration.EnumTipoEndereco;
import br.com.mulacar.enumeration.EnumTipoTelefone;
import br.com.mulacar.exception.MulaCarException;
import br.com.mulacar.model.Motorista;
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


public class MotoristaApp extends javax.swing.JDialog {

    private static final Logger LOG = Logger.getLogger(MotoristaApp.class.getName());
    
    private static final int LIMITE_CARACTERES_CPF = 11;
    
    private static final int LIMITE_CARACTERES_CEP = 8;
    
    private List<Contato> contatos;

    private MotoristaBll motoristaBll;
    
    private EnderecoBll enderecoBll;
    
    private ContatoBll contatoBll;
    
    private List<Endereco> enderecos;
    
    private boolean ambienteDesenvolvimento = false;
    
    private String mensagemErroPadrao;
    
    private int idEnderecoSelecinadoTabela;
    
    private int idContatoSelecinadoTabela;
    
    public MotoristaApp(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        
        initComponents();
        
        inicializar();
    }
    
    private void inicializar() {
        
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        setTitle("Manutenção de Motoristas");

        setResizable(false);    
        
        jButtonRemoverEnderecoTabela.setEnabled(false);
        
        jButtonRemoverContatoTabela.setEnabled(false);
        
        jButtonIncluirEnderecoTeste.setVisible(false);
        
        this.mensagemErroPadrao = "Entre em contato com suporte";

        this.motoristaBll = new MotoristaBll();
        
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
                                                        jTextFieldRua,
                                                        jTextFieldComplemento,
                                                        jTextFieldCidade,
                                                        jTextFieldBairro);
        
        
        UtilComponentes.adicionarKeyListenerSomenteNumeros(jTextFieldCpf, 
                                                        jTextFieldRg,
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
        
        UtilTabela.limparTabelas(jTableContatosMotorista, jTableEnderecos);
        
        
//      PARA PRODUÇÃO ATRIBUIR FALSE  
        this.ambienteDesenvolvimento = false;
        
        if (ambienteDesenvolvimento) {
            this.preecherDadosTesteMotorista();
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
        jTableContatosMotorista.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                int linha = jTableContatosMotorista.getSelectedRow();
                
                idContatoSelecinadoTabela = Integer.parseInt(jTableContatosMotorista.getValueAt(linha, 0).toString());
                
                habilitarDesabilitarBotaoRemoverItemTabela(jTableContatosMotorista, jButtonRemoverContatoTabela);
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

        jTabbedPaneMotorista = new javax.swing.JTabbedPane();
        jPanelMotorista = new javax.swing.JPanel();
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
        jTextFieldNomeMotoristaConsultar = new javax.swing.JTextField();
        jTextFieldCodigo = new javax.swing.JTextField();
        jButtonConsultarMotorista = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jComboBoxStatus = new javax.swing.JComboBox();
        jPanelEndereco = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jComboBoxTipoEndereco = new javax.swing.JComboBox<String>();
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
        jComboBoxUf = new javax.swing.JComboBox<String>();
        jButtonIrParaTabContato = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableEnderecos = new javax.swing.JTable();
        jButtonIncluirEnderecoTeste = new javax.swing.JButton();
        jButtonRemoverEnderecoTabela = new javax.swing.JButton();
        jButtonAdicionarEnderecoTabela = new javax.swing.JButton();
        jPanelContato = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jComboBoxTipoTelefone = new javax.swing.JComboBox<String>();
        jLabel13 = new javax.swing.JLabel();
        jTextFieldNumeroTelefone = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jTextField1Email = new javax.swing.JTextField();
        jButtonFechar = new javax.swing.JButton();
        jButtonConcluirCadastro = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableContatosMotorista = new javax.swing.JTable();
        jButtonAdicionarContatoTabela = new javax.swing.JButton();
        jButtonRemoverContatoTabela = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jTabbedPaneMotorista.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        jTabbedPaneMotorista.setToolTipText("");
        jTabbedPaneMotorista.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jTabbedPaneMotorista.setPreferredSize(new java.awt.Dimension(600, 480));

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

        jTextFieldCodigo.setEditable(false);

        jButtonConsultarMotorista.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/mulacar/imagens/search-icon-24px.png"))); // NOI18N
        jButtonConsultarMotorista.setText("Consultar");
        jButtonConsultarMotorista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonConsultarMotoristaActionPerformed(evt);
            }
        });

        jLabel1.setText("Id:");

        jLabel2.setText("Nome");

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setText("Dados do Motorista");

        jLabel15.setText("Status:");

        jComboBoxStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxStatusActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelMotoristaLayout = new javax.swing.GroupLayout(jPanelMotorista);
        jPanelMotorista.setLayout(jPanelMotoristaLayout);
        jPanelMotoristaLayout.setHorizontalGroup(
            jPanelMotoristaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMotoristaLayout.createSequentialGroup()
                .addGroup(jPanelMotoristaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelMotoristaLayout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(jPanelMotoristaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelNome, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabelCpjCnpj, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanelMotoristaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelMotoristaLayout.createSequentialGroup()
                                .addComponent(jTextFieldCpf, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 78, Short.MAX_VALUE)
                                .addComponent(jLabelRgOuRazaoSocial, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldRg, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(34, 34, 34)
                                .addComponent(jLabelOrgaoEmissor)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextFieldOrgaoEmissor, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelMotoristaLayout.createSequentialGroup()
                                .addComponent(jTextFieldNome, javax.swing.GroupLayout.PREFERRED_SIZE, 603, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel15)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBoxStatus, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelMotoristaLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonLimparCamposPessoaFisica)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonIrPFisicaParaTabEndereco))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelMotoristaLayout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextFieldCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextFieldNomeMotoristaConsultar)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonConsultarMotorista)))
                .addContainerGap())
            .addGroup(jPanelMotoristaLayout.createSequentialGroup()
                .addGap(379, 379, 379)
                .addComponent(jLabel12)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelMotoristaLayout.setVerticalGroup(
            jPanelMotoristaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMotoristaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelMotoristaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldNomeMotoristaConsultar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonConsultarMotorista)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel12)
                .addGap(18, 18, 18)
                .addGroup(jPanelMotoristaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelNome)
                    .addComponent(jLabel15)
                    .addComponent(jComboBoxStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelMotoristaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelCpjCnpj)
                    .addComponent(jTextFieldCpf, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldRg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelRgOuRazaoSocial)
                    .addComponent(jTextFieldOrgaoEmissor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelOrgaoEmissor))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 255, Short.MAX_VALUE)
                .addGroup(jPanelMotoristaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButtonLimparCamposPessoaFisica, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonIrPFisicaParaTabEndereco, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTabbedPaneMotorista.addTab("Motorista", jPanelMotorista);

        jLabel3.setText("Tipo");

        jLabel4.setText("CEP");

        jLabel5.setText("Rua");

        jLabel6.setText("Número");

        jLabel7.setText("Complemento");

        jLabel8.setText("Bairro");

        jLabel9.setText("Cidade");

        jLabel10.setText("Estado:");

        jComboBoxUf.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

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
            jTableEnderecos.getColumnModel().getColumn(0).setMinWidth(50);
            jTableEnderecos.getColumnModel().getColumn(0).setPreferredWidth(50);
            jTableEnderecos.getColumnModel().getColumn(0).setMaxWidth(50);
            jTableEnderecos.getColumnModel().getColumn(1).setPreferredWidth(10);
            jTableEnderecos.getColumnModel().getColumn(2).setMinWidth(100);
            jTableEnderecos.getColumnModel().getColumn(2).setPreferredWidth(100);
            jTableEnderecos.getColumnModel().getColumn(2).setMaxWidth(100);
            jTableEnderecos.getColumnModel().getColumn(3).setPreferredWidth(20);
            jTableEnderecos.getColumnModel().getColumn(4).setMinWidth(50);
            jTableEnderecos.getColumnModel().getColumn(4).setPreferredWidth(50);
            jTableEnderecos.getColumnModel().getColumn(4).setMaxWidth(50);
            jTableEnderecos.getColumnModel().getColumn(5).setPreferredWidth(20);
            jTableEnderecos.getColumnModel().getColumn(6).setPreferredWidth(15);
            jTableEnderecos.getColumnModel().getColumn(7).setPreferredWidth(15);
            jTableEnderecos.getColumnModel().getColumn(8).setMinWidth(50);
            jTableEnderecos.getColumnModel().getColumn(8).setPreferredWidth(50);
            jTableEnderecos.getColumnModel().getColumn(8).setMaxWidth(50);
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
                        .addGroup(jPanelEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel9))
                        .addGap(18, 18, 18)
                        .addGroup(jPanelEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanelEnderecoLayout.createSequentialGroup()
                                .addComponent(jTextFieldCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(23, 23, 23)
                                .addComponent(jLabel10)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBoxUf, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jTextFieldComplemento, javax.swing.GroupLayout.PREFERRED_SIZE, 433, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanelEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelEnderecoLayout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelEnderecoLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jButtonRemoverEnderecoTabela))))
                    .addGroup(jPanelEnderecoLayout.createSequentialGroup()
                        .addGroup(jPanelEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel3))
                        .addGap(63, 63, 63)
                        .addGroup(jPanelEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelEnderecoLayout.createSequentialGroup()
                                .addComponent(jTextFieldRua, javax.swing.GroupLayout.PREFERRED_SIZE, 433, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(23, 23, 23)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanelEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanelEnderecoLayout.createSequentialGroup()
                                        .addComponent(jButtonAdicionarEnderecoTabela)
                                        .addGap(0, 182, Short.MAX_VALUE))
                                    .addComponent(jTextFieldBairro, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jTextFieldNumero)))
                            .addGroup(jPanelEnderecoLayout.createSequentialGroup()
                                .addComponent(jComboBoxTipoEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(jTextFieldCEP, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))))
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
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanelEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonIrParaTabContato)
                    .addComponent(jButtonIncluirEnderecoTeste))
                .addGap(15, 15, 15))
        );

        jTabbedPaneMotorista.addTab("Endereço", jPanelEndereco);

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

        jTableContatosMotorista.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Tipo Telefone", "Telefone", "Email"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableContatosMotorista.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTableContatosMotorista);
        if (jTableContatosMotorista.getColumnModel().getColumnCount() > 0) {
            jTableContatosMotorista.getColumnModel().getColumn(0).setMinWidth(50);
            jTableContatosMotorista.getColumnModel().getColumn(0).setPreferredWidth(50);
            jTableContatosMotorista.getColumnModel().getColumn(0).setMaxWidth(50);
            jTableContatosMotorista.getColumnModel().getColumn(1).setMinWidth(150);
            jTableContatosMotorista.getColumnModel().getColumn(1).setPreferredWidth(150);
            jTableContatosMotorista.getColumnModel().getColumn(1).setMaxWidth(150);
            jTableContatosMotorista.getColumnModel().getColumn(2).setMinWidth(150);
            jTableContatosMotorista.getColumnModel().getColumn(2).setPreferredWidth(150);
            jTableContatosMotorista.getColumnModel().getColumn(2).setMaxWidth(150);
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
                        .addGap(0, 327, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 873, Short.MAX_VALUE))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 62, Short.MAX_VALUE)
                .addGroup(jPanelContatoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonFechar)
                    .addComponent(jButtonConcluirCadastro))
                .addGap(19, 19, 19))
        );

        jTabbedPaneMotorista.addTab("Contato", jPanelContato);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPaneMotorista, javax.swing.GroupLayout.DEFAULT_SIZE, 898, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPaneMotorista, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonIrPFisicaParaTabEnderecoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonIrPFisicaParaTabEnderecoActionPerformed
        jTabbedPaneMotorista.setSelectedIndex(2);
    }//GEN-LAST:event_jButtonIrPFisicaParaTabEnderecoActionPerformed

    private void jButtonConcluirCadastroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonConcluirCadastroActionPerformed
        try {
    //      Pessoa Física        
            String nome         = jTextFieldNome.getText();
            String cpf          = jTextFieldCpf.getText();
            String rg           = jTextFieldRg.getText();
            String orgaoEmissor = jTextFieldOrgaoEmissor.getText();

            Motorista motorista = preencherMotorista(cpf, nome, rg, orgaoEmissor);

            Motorista motoristaBanco = motoristaBll.adicionarMotorista(motorista);

            preencherEnderecoComMotorista(this.enderecos, motoristaBanco);
            
            enderecoBll.adicionarEnderecos(this.enderecos);
            
            preencherContatosComMotorista(this.contatos, motoristaBanco);

            contatoBll.adicionarContatos(this.contatos);
            
            UtilTabela.limparTabelas(jTableContatosMotorista);
            
            this.contatos = new ArrayList<>();
            
            UtilTabela.limparTabelas(jTableEnderecos);
            
            this.enderecos = new ArrayList();
            
            reiniciarTodosCampos();
            
            JOptionPane.showMessageDialog(null, "Motorista Salvo com sucesso!!");
            
        } catch (Exception e) {
            if (!(e instanceof MulaCarException)) {
                JOptionPane.showMessageDialog(null, "Ocorreu um erro ao salvar o motorista. Entre em contato com suporte!");
            } else {
                JOptionPane.showMessageDialog(null, e.getMessage());            
            }
        }
    }//GEN-LAST:event_jButtonConcluirCadastroActionPerformed

    private void preencherEnderecoComMotorista(List<Endereco> enderecos, Motorista motorista) {
        for (Endereco end: enderecos) {
            end.setMotorista(motorista);
        }
    }
    
    private void preencherContatosComMotorista(List<Contato> contatos, Motorista motorista) {
        for (Contato cont: contatos) {
            cont.setMotorista(motorista);
        }
    }    

    private Motorista preencherMotorista(String cpf, String nome, String rg, String orgaoEmissor) {
        
        Motorista motorista = new Motorista();
        
        motorista.setStatus(EnumStatus.ATIVO);
        
        if (!UtilObjetos.ehNuloOuVazio(cpf)) {
//            motorista.setTipoMotorista(EnumTipoMotorista.PESSOA_FISICA);
            motorista.setNome(nome);
            motorista.setCpf(cpf);
            motorista.setRg(rg);
            motorista.setOrgaoEmissor(orgaoEmissor);
        }
//        else {
//            motorista.setTipoMotorista(EnumTipoMotorista.PESSOA_JURIDICA);
//            motorista.setRazaoSocial(razaoSocial);
//            motorista.setCpfCnpj(cnpj);
//            motorista.setNome(nomeFantasia);
//        }
        
        return motorista;
    }

    private void jButtonFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFecharActionPerformed
        try {
            this.dispose();

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Não foi possível fechar a janela!");
        }
    }//GEN-LAST:event_jButtonFecharActionPerformed

    private void jButtonIrParaTabContatoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonIrParaTabContatoActionPerformed
        jTabbedPaneMotorista.setSelectedIndex(3);
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
            Logger.getLogger(MotoristaApp.class.getName()).log(Level.SEVERE, null, e);
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

            UtilTabela.limparTabelas(jTableContatosMotorista);
        
            preencherTabelaContato(this.contatos);
            
            UtilComponentes.limparCampos(jTextFieldNumeroTelefone, jTextField1Email);
            
            jTextField1Email.setFocusable(true);
            
        } catch (Exception ex) {
            Logger.getLogger(MotoristaApp.class.getName()).log(Level.SEVERE, null, ex);
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
                
                UtilTabela.limparTabelas(jTableContatosMotorista);
                
                int indiceContatoParaRemover = this.idContatoSelecinadoTabela - 1;
                
                this.contatos.remove(indiceContatoParaRemover);
                
                this.recriarIdsTemporariosContatosTabela();
                
                this.preencherTabelaContato(this.contatos);
                
                habilitarDesabilitarBotaoRemoverItemTabela(jTableContatosMotorista, jButtonRemoverContatoTabela);
                
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

    private void jButtonConsultarMotoristaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonConsultarMotoristaActionPerformed
        // TODO add your handling code here:
        try {
            //            imprimirDadosCategoria(catBll.getConsultaCategorias());
//            imprimirDadosCategoria(motoristaBll.pesquisarMotorista(jTextFieldNomeMotoristaConsultar.getText()));

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, erro.getMessage());
        }
    }//GEN-LAST:event_jButtonConsultarMotoristaActionPerformed

    private void jComboBoxStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxStatusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxStatusActionPerformed

    private void preencherTabelaContato(List<Contato> contatos) throws Exception {
        
        DefaultTableModel model = (DefaultTableModel) jTableContatosMotorista.getModel();
        
        model.setNumRows(0);
        
//        contatoBll.ordenaListaContatos(this.telefones);
        
        for (int i = 0; i < contatos.size(); i++) {
            String[] linha = new String[4];
            
            linha[0] = String.valueOf(contatos.get(i).getId());
            linha[1] = contatos.get(i).getTipoTelefone().name();
            linha[2] = String.valueOf(contatos.get(i).getNumero());
            linha[3] = contatos.get(i).getEmail();
            
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
                MotoristaApp dialog = new MotoristaApp(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton jButtonConsultarMotorista;
    private javax.swing.JButton jButtonFechar;
    private javax.swing.JButton jButtonIncluirEnderecoTeste;
    private javax.swing.JButton jButtonIrPFisicaParaTabEndereco;
    private javax.swing.JButton jButtonIrParaTabContato;
    private javax.swing.JButton jButtonLimparCamposPessoaFisica;
    private javax.swing.JButton jButtonRemoverContatoTabela;
    private javax.swing.JButton jButtonRemoverEnderecoTabela;
    private javax.swing.JComboBox jComboBoxStatus;
    private javax.swing.JComboBox<String> jComboBoxTipoEndereco;
    private javax.swing.JComboBox<String> jComboBoxTipoTelefone;
    private javax.swing.JComboBox<String> jComboBoxUf;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
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
    private javax.swing.JPanel jPanelMotorista;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPaneMotorista;
    private javax.swing.JTable jTableContatosMotorista;
    private javax.swing.JTable jTableEnderecos;
    private javax.swing.JTextField jTextField1Email;
    private javax.swing.JTextField jTextFieldBairro;
    private javax.swing.JTextField jTextFieldCEP;
    private javax.swing.JTextField jTextFieldCidade;
    private javax.swing.JTextField jTextFieldCodigo;
    private javax.swing.JTextField jTextFieldComplemento;
    private javax.swing.JTextField jTextFieldCpf;
    private javax.swing.JTextField jTextFieldNome;
    private javax.swing.JTextField jTextFieldNomeMotoristaConsultar;
    private javax.swing.JTextField jTextFieldNumero;
    private javax.swing.JTextField jTextFieldNumeroTelefone;
    private javax.swing.JTextField jTextFieldOrgaoEmissor;
    private javax.swing.JTextField jTextFieldRg;
    private javax.swing.JTextField jTextFieldRua;
    // End of variables declaration//GEN-END:variables

    private void preecherDadosTesteMotorista() {
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


    }

    private void reiniciarTodosCampos() {
        UtilComponentes.limparCampos(jTextFieldNome,
                                    jTextFieldCpf,
                                    jTextFieldRg,
                                    jTextFieldOrgaoEmissor,
                                    jTextFieldCEP,
                                    jTextFieldRua,
                                    jTextFieldNumero,
                                    jTextFieldComplemento,
                                    jTextFieldBairro,
                                    jTextFieldCidade,
                                    jTextField1Email,
                                    jTextFieldNumeroTelefone);
        
        UtilTabela.limparTabelas(jTableContatosMotorista, jTableEnderecos);
    
        this.carregarComboBoxes();
        
    }

}
