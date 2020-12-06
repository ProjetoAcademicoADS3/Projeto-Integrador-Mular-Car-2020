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
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class ClienteApp extends javax.swing.JDialog {

    private List<Contato> contatos;

    private ClienteBll clienteBll;
    
    private EnderecoBll enderecoBll;
    
    private ContatoBll contatoBll;
    
    private List<Endereco> enderecos;
    
    private boolean ambienteDesenvolvimento = false;
    
    private DefaultTableModel tableModel;
    
    public ClienteApp(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        
        initComponents();
        
        inicializar();
    }
    
    private void inicializar() {
        
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Manutenção de Clientes");
        setResizable(false);    

        clienteBll = new ClienteBll();
        
        enderecoBll = new EnderecoBll();
        
        contatoBll = new ContatoBll();
        
        contatos = new ArrayList();
        
        enderecos = new ArrayList();
        
        tableModel = (DefaultTableModel) jTableContatos.getModel();
        
        limparTabela(jTableContatos);
        
        limparTabela(jTableEnderecos);
        
        Vector<EnumUF> ufs = EnumUF.carregarUnidadesFederativas();
        jComboBoxUf.setModel(new DefaultComboBoxModel(ufs));        
        
        Vector<EnumTipoEndereco> tiposEndereco = EnumTipoEndereco.carregarTiposEndereco();
        jComboBoxTipoEndereco.setModel(new DefaultComboBoxModel(tiposEndereco));   

        Vector<EnumTipoTelefone> tiposTelefone = EnumTipoTelefone.carregarTiposTelefones();
        jComboBoxTipoTelefone.setModel(new DefaultComboBoxModel(tiposTelefone));  
        
        ambienteDesenvolvimento = true;
        
        if (ambienteDesenvolvimento) {
            preecherDadosTesteCliente();
        }
        
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
        jButtonFisicaParaTabEndereco = new javax.swing.JButton();
        jPanelPessoaJuridica = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldRazaoSocial = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldCnpj = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jTextFieldNomeFantasia = new javax.swing.JTextField();
        jButtonJuridicaParaTabEndereco = new javax.swing.JButton();
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
        jButtonEnderecoParaContato = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableEnderecos = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jPanelContato = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jComboBoxTipoTelefone = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        jTextFieldNumeroTelefone = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jTextField1Email = new javax.swing.JTextField();
        jButtonAdicionarContatoTabela = new javax.swing.JButton();
        jButtonFechar = new javax.swing.JButton();
        jButtonConcluirCadastro = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableContatos = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jTabbedPaneCliente.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        jTabbedPaneCliente.setToolTipText("");
        jTabbedPaneCliente.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jTabbedPaneCliente.setPreferredSize(new java.awt.Dimension(600, 480));

        jLabelNome.setText("Nome: ");

        jLabelRgOuRazaoSocial.setText("RG:");

        jLabelOrgaoEmissor.setText("Org. Emissor: ");

        jLabelCpjCnpj.setText("CPF:");

        jButtonFisicaParaTabEndereco.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/mulacar/imagens/seta-continuar.png"))); // NOI18N
        jButtonFisicaParaTabEndereco.setMnemonic('c');
        jButtonFisicaParaTabEndereco.setText("Continuar");
        jButtonFisicaParaTabEndereco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonFisicaParaTabEnderecoActionPerformed(evt);
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
                .addGap(0, 227, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelPessoaFisicaLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonFisicaParaTabEndereco)
                .addContainerGap())
        );
        jPanelPessoaFisicaLayout.setVerticalGroup(
            jPanelPessoaFisicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPessoaFisicaLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(jPanelPessoaFisicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelNome))
                .addGap(18, 18, 18)
                .addGroup(jPanelPessoaFisicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelCpjCnpj)
                    .addComponent(jTextFieldCpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(jPanelPessoaFisicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelRgOuRazaoSocial)
                    .addComponent(jTextFieldRg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelPessoaFisicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldOrgaoEmissor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelOrgaoEmissor))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 211, Short.MAX_VALUE)
                .addComponent(jButtonFisicaParaTabEndereco)
                .addContainerGap())
        );

        jTabbedPaneCliente.addTab("Pessoa Física", jPanelPessoaFisica);

        jPanelPessoaJuridica.setPreferredSize(new java.awt.Dimension(600, 454));

        jLabel1.setText("Razão Social");

        jLabel2.setText("CNPJ");

        jLabel15.setText("Nome Fantasia");

        jButtonJuridicaParaTabEndereco.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/mulacar/imagens/seta-continuar.png"))); // NOI18N
        jButtonJuridicaParaTabEndereco.setMnemonic('c');
        jButtonJuridicaParaTabEndereco.setText("Continuar");
        jButtonJuridicaParaTabEndereco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonJuridicaParaTabEnderecoActionPerformed(evt);
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
                .addGap(20, 221, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelPessoaJuridicaLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButtonJuridicaParaTabEndereco)
                .addContainerGap())
        );
        jPanelPessoaJuridicaLayout.setVerticalGroup(
            jPanelPessoaJuridicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPessoaJuridicaLayout.createSequentialGroup()
                .addGap(49, 49, 49)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 247, Short.MAX_VALUE)
                .addComponent(jButtonJuridicaParaTabEndereco)
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

        jButtonEnderecoParaContato.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/mulacar/imagens/seta-continuar.png"))); // NOI18N
        jButtonEnderecoParaContato.setMnemonic('c');
        jButtonEnderecoParaContato.setText("Continuar");
        jButtonEnderecoParaContato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEnderecoParaContatoActionPerformed(evt);
            }
        });

        jTableEnderecos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Tipo", "Cep", "Rua", "Número", "Compl.", "Bairro", "Cidade", "Estado"
            }
        ));
        jScrollPane2.setViewportView(jTableEnderecos);
        if (jTableEnderecos.getColumnModel().getColumnCount() > 0) {
            jTableEnderecos.getColumnModel().getColumn(0).setPreferredWidth(20);
        }

        jButton1.setMnemonic('a');
        jButton1.setText("Adicionar Endereço");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
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
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButtonEnderecoParaContato))
                    .addGroup(jPanelEnderecoLayout.createSequentialGroup()
                        .addGroup(jPanelEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelEnderecoLayout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(73, 73, 73)
                                .addComponent(jComboBoxTipoEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelEnderecoLayout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(75, 75, 75)
                                .addComponent(jTextFieldRua, javax.swing.GroupLayout.PREFERRED_SIZE, 433, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(jPanelEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldCEP, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                            .addComponent(jTextFieldBairro, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextFieldNumero)))
                    .addGroup(jPanelEnderecoLayout.createSequentialGroup()
                        .addGroup(jPanelEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelEnderecoLayout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(55, 55, 55)
                                .addComponent(jTextFieldCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel10)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBoxUf, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelEnderecoLayout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(18, 18, 18)
                                .addComponent(jTextFieldComplemento, javax.swing.GroupLayout.PREFERRED_SIZE, 433, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanelEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelEnderecoLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel8))
                            .addGroup(jPanelEnderecoLayout.createSequentialGroup()
                                .addGap(69, 69, 69)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        jPanelEnderecoLayout.setVerticalGroup(
            jPanelEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelEnderecoLayout.createSequentialGroup()
                .addGap(13, 13, 13)
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
                .addGroup(jPanelEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jTextFieldCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(jComboBoxUf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jButtonEnderecoParaContato)
                .addGap(15, 15, 15))
        );

        jTabbedPaneCliente.addTab("Endereço", jPanelEndereco);

        jLabel11.setText("Tipo Telefone");

        jLabel13.setText("Telefone:");

        jLabel14.setText("E-mail:");

        jButtonAdicionarContatoTabela.setMnemonic('a');
        jButtonAdicionarContatoTabela.setText("Adicionar");
        jButtonAdicionarContatoTabela.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAdicionarContatoTabelaActionPerformed(evt);
            }
        });

        jButtonFechar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/mulacar/imagens/Actions-window-close-icon-24px.png"))); // NOI18N
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
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
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
                            .addComponent(jTextField1Email, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBoxTipoTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanelContatoLayout.createSequentialGroup()
                                .addComponent(jTextFieldNumeroTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButtonAdicionarContatoTabela)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 769, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanelContatoLayout.setVerticalGroup(
            jPanelContatoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelContatoLayout.createSequentialGroup()
                .addGap(27, 27, 27)
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
                    .addComponent(jTextFieldNumeroTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonAdicionarContatoTabela))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
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
                .addComponent(jTabbedPaneCliente, javax.swing.GroupLayout.DEFAULT_SIZE, 783, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPaneCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonFisicaParaTabEnderecoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFisicaParaTabEnderecoActionPerformed
        jTabbedPaneCliente.setSelectedIndex(2);
    }//GEN-LAST:event_jButtonFisicaParaTabEnderecoActionPerformed

    private void jButtonJuridicaParaTabEnderecoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonJuridicaParaTabEnderecoActionPerformed
        jTabbedPaneCliente.setSelectedIndex(2);
    }//GEN-LAST:event_jButtonJuridicaParaTabEnderecoActionPerformed

    private void jButtonConcluirCadastroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonConcluirCadastroActionPerformed
        try {
    //      Contato
            String email                    = jTextField1Email.getText();
            EnumTipoTelefone tipoTelefone   = (EnumTipoTelefone) jComboBoxTipoTelefone.getSelectedItem();

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
            
        } catch (Exception e) {
            if (!(e instanceof MulaCarException)) {
                JOptionPane.showMessageDialog(null, "Ocorreu um erro ao salvar o cliente. Entre em contato com suporte!");
            } else {
                JOptionPane.showMessageDialog(null, e.getMessage());            
            }
        }
    }//GEN-LAST:event_jButtonConcluirCadastroActionPerformed

    private void preencherContatos(String email, EnumTipoTelefone tipoTelefone,
            String numeroTelefone, Cliente cliente, List<Contato> contatos) {
        
        for (Contato cont : this.contatos) {
            Contato contato = new Contato();
            contato.setEmail(email);
            contato.setTipoTelefone(tipoTelefone);
            contato.setNumero(numeroTelefone);
            contato.setCliente(cliente);
            contatos.add(contato);
        }
    }

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

    private void jButtonEnderecoParaContatoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEnderecoParaContatoActionPerformed
        jTabbedPaneCliente.setSelectedIndex(3);
    }//GEN-LAST:event_jButtonEnderecoParaContatoActionPerformed

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
            
            Contato contato = new Contato(email, tipoTelefone, numeroTelefone);

            this.contatos.add(contato);

            limparTabela(jTableContatos);
        
            preencherTabelaContato(this.contatos);
            
            UtilComponentes.limparCampos(jTextFieldNumeroTelefone, jTextField1Email);
            
        } catch (Exception ex) {
            Logger.getLogger(ClienteApp.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Problema ao adicionar telefone. Entre em contato com suporte.");
        }
    }//GEN-LAST:event_jButtonAdicionarContatoTabelaActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
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
            
            Endereco endereco = new Endereco();
            endereco.setCep(cep);
            endereco.setTipoEndereco(tipoEndereco);
            endereco.setRua(rua);
            endereco.setNumero(numero);
            endereco.setComplemento(complemento);
            endereco.setBairro(bairro);
            endereco.setCidade(cidade);
            endereco.setUf(uf);
            
            this.enderecos.add(endereco);

            limparTabela(jTableEnderecos);
        
            preencherTabelaEndereco(enderecos);
            
            UtilComponentes.limparCampos(jTextFieldCEP,
                                        jTextFieldRua,
                                        jTextFieldNumero,
                                        jTextFieldComplemento,
                                        jTextFieldBairro,
                                        jTextFieldCidade);
            
        } catch (Exception e) {
            Logger.getLogger(ClienteApp.class.getName()).log(Level.SEVERE, null, e);
            JOptionPane.showMessageDialog(null, "Problema ao adicionar endereco. Entre em contato com suporte.");            
        }
            
    }//GEN-LAST:event_jButton1ActionPerformed

    private void preencherTabelaContato(List<Contato> contatos) throws Exception {
        
        DefaultTableModel model = (DefaultTableModel) jTableContatos.getModel();
        
        model.setNumRows(0);
        
//        contatoBll.ordenaListaContatos(this.telefones);
        
        for (int i = 0; i < contatos.size(); i++) {
            String[] linha = new String[3];
            
            linha[0] = contatos.get(i).getEmail();
            linha[1] = contatos.get(i).getTipoTelefone().name();
            linha[2] = String.valueOf(contatos.get(i).getNumero());
            
            model.addRow(linha);
        }
    }
    
    private void preencherTabelaEndereco(List<Endereco> enderecos) throws Exception {
        
        DefaultTableModel model = (DefaultTableModel) jTableEnderecos.getModel();
        
        model.setNumRows(0);
        
//        contatoBll.ordenaListaContatos(this.telefones);
        
        for (int i = 0; i < enderecos.size(); i++) {
            
            String[] linha = new String[8];
            
            linha[0] = enderecos.get(i).getTipoEndereco().name();
            linha[1] = enderecos.get(i).getCep();
            linha[2] = enderecos.get(i).getRua();
            linha[3] = enderecos.get(i).getNumero();
            linha[4] = enderecos.get(i).getComplemento();
            linha[5] = enderecos.get(i).getBairro();
            linha[6] = enderecos.get(i).getCidade();
            linha[7] = enderecos.get(i).getUf().sigla();
            
            model.addRow(linha);
        }
    }    
    
    public void limparTabela(JTable tabela) {
        try {
            tableModel = (DefaultTableModel) tabela.getModel();
            tableModel.setNumRows(0);

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, erro.getMessage());
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
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonAdicionarContatoTabela;
    private javax.swing.JButton jButtonConcluirCadastro;
    private javax.swing.JButton jButtonEnderecoParaContato;
    private javax.swing.JButton jButtonFechar;
    private javax.swing.JButton jButtonFisicaParaTabEndereco;
    private javax.swing.JButton jButtonJuridicaParaTabEndereco;
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
            jComboBoxTipoEndereco.getSelectedItem();
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


}
