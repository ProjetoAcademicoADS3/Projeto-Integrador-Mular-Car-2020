/*
 * Novembro/Dezembro 2020.
 * Senai Fatesg Faculdade de Tecnologia
 * ADS - Análise e Desenvolvimento de Sistemas
 * Projeto Integrador - ADS3
 * Projeto Mula Car - aluguel de Veículos
 * Alunos: Aires Ribeiro, Gabriel Cunha, Lucas França e Rogério Reis
 */
package br.com.mulacar.app;

import br.com.mulacar.bll.VeiculoBll;
import br.com.mulacar.enumeration.EnumSituacaoVeiculo;
import br.com.mulacar.enumeration.EnumStatus;
import br.com.mulacar.enumeration.EnumTipoCombustivel;
import br.com.mulacar.enumeration.EnumTipoVeiculo;
import br.com.mulacar.model.Categoria;
import br.com.mulacar.model.Veiculo;
import java.util.Calendar;
import javax.swing.JOptionPane;

/**
 *
 * @author roger
 */
public class VeiculoApp extends javax.swing.JDialog {

    Calendar cal = Calendar.getInstance();
    Veiculo veic;
    VeiculoBll veicBll;

    /**
     * Creates new form VeiculooApp
     */
    public VeiculoApp(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        veicBll = new VeiculoBll();
        veic = new Veiculo();

        jComboBoxCategorias.removeAllItems();
        jComboBoxCategorias.addItem("< Selecione a Categoria do Veículo >");

        jComboBoxModelos.removeAllItems();
        jComboBoxModelos.addItem("< Selecione o Modelo do Veículo >");

        jComboBoxTipoDeCombustivel.removeAllItems();
        jComboBoxTipoDeCombustivel.addItem("< Tipo de Combustível >");
        for (EnumTipoCombustivel combustivel : EnumTipoCombustivel.values()) {
            jComboBoxTipoDeCombustivel.addItem(combustivel.toString());
        }
        jComboBoxTipoDoVeiculo.removeAllItems();
        jComboBoxTipoDoVeiculo.addItem("< Tipo do Veículo > ");
        for (EnumTipoVeiculo tipoDoVeiculo : EnumTipoVeiculo.values()) {
            jComboBoxTipoDoVeiculo.addItem(tipoDoVeiculo.toString());
        }
        jComboBoxStatus.removeAllItems();
        jComboBoxStatus.addItem("< Status do Veículo >");
        for (EnumStatus statusDoVeiculo : EnumStatus.values()) {
            jComboBoxStatus.addItem(statusDoVeiculo.toString());
        }
        
        jComboBoxNumPassageiros.removeAllItems();
        String[] numPassageiros = {"0", "2", "4", "5", "6", "7", "8", "9", "10"};
        for (int i = 0; i < numPassageiros.length; i++) {
            jComboBoxNumPassageiros.addItem(numPassageiros[i]);
        }
        jComboBoxSituacao.removeAllItems();
        jComboBoxSituacao.addItem("< Situação do Veículo >");
        for (EnumSituacaoVeiculo situacaoVeiculo : EnumSituacaoVeiculo.values()) {
            jComboBoxSituacao.addItem(situacaoVeiculo.toString());
        }
        jComboBoxAnoFabricacao.removeAllItems();
        jComboBoxAnoFabricacao.addItem("0");
        jComboBoxAnoModelo.removeAllItems();
        jComboBoxAnoModelo.addItem("0");
        int anoInicio = 1885;
        int anoFinal = cal.get(Calendar.YEAR);
        for (int i = anoInicio; i < anoFinal; i++) {
            anoInicio++;
            jComboBoxAnoFabricacao.addItem(anoInicio + "");
            jComboBoxAnoModelo.addItem(anoInicio + "");
        }
        jComboBoxAnoModelo.addItem(anoFinal + 1 + "");
    }

    private void preencherCampos(String placa) {
        try {
            veic = veicBll.getConsultarVeiculoByPlaca(placa);
            if (veic.getId() > 0) {
                jTextField_Id.setText(veic.getId() + "");
                jComboBoxCategorias.setSelectedItem(veic.getCategoria().getDescricao().toUpperCase());
                jComboBoxModelos.setSelectedItem(veic.getModelo().getDescricao().toUpperCase());
                jTextFieldMarca.setText(veic.getModelo().getMarca().getDescricao().toUpperCase());
                jComboBoxAnoFabricacao.setSelectedItem(veic.getAnoFabricacao());
                jComboBoxAnoModelo.setSelectedItem(veic.getAnoModelo());
                jComboBoxTipoDeCombustivel.setSelectedItem(veic.getTipoCombustivel().toString());
                jComboBoxTipoDoVeiculo.setSelectedItem(veic.getTipo().toString());
                jTextFieldRenavan.setText(veic.getRenavan());
                jTextFieldPrecoCompra.setText(String.format("%.2f", veic.getPrecoCompra()));
                jTextFieldPrecoVenda.setText(String.format("%.2f", veic.getPrecoVenda()));
                jTextFieldPlaca.setText(veic.getPlaca());
                jComboBoxStatus.setSelectedItem(veic.getStatus().toString());
                jComboBoxNumPassageiros.setSelectedItem(veic.getNumPassageiros());
                jTextFieldKm.setText(veic.getKm() + "");
                jComboBoxSituacao.setSelectedItem(veic.getSituacao().toString());
                jButtonSalvarVeiculo.setLabel("Editar");

            }

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, erro.getMessage());
        }

    }

    private void limpaCampos() {
        jTextField_Id.setText("");
        jTextFieldPesquisarVeiculo.setText("");
        jComboBoxCategorias.setSelectedIndex(0);
        jComboBoxModelos.setSelectedIndex(0);
        jTextFieldMarca.setText("");
        jComboBoxAnoFabricacao.setSelectedIndex(0);
        jComboBoxAnoModelo.setSelectedIndex(0);
        jComboBoxTipoDeCombustivel.setSelectedIndex(0);
        jComboBoxTipoDoVeiculo.setSelectedIndex(0);
        jTextFieldRenavan.setText("");
        jTextFieldPrecoCompra.setText("");
        jTextFieldPrecoVenda.setText("");
        jTextFieldPlaca.setText("");
        jComboBoxStatus.setSelectedIndex(0);
        jComboBoxNumPassageiros.setSelectedIndex(0);
        jTextFieldKm.setText("");
        jComboBoxSituacao.setSelectedIndex(0);
        jButtonSalvarVeiculo.setLabel("Salvar");

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelCadVeiculos3 = new javax.swing.JPanel();
        jButtonPesquisarVeiculo = new javax.swing.JButton();
        jTextFieldPesquisarVeiculo = new javax.swing.JTextField();
        jButtonNovoCadVeiculo = new javax.swing.JButton();
        jButtonListarVeiculos = new javax.swing.JButton();
        jButtonSalvarVeiculo = new javax.swing.JButton();
        jButtonFechar = new javax.swing.JButton();
        jLabel52 = new javax.swing.JLabel();
        jComboBoxCategorias = new javax.swing.JComboBox<>();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jComboBoxTipoDeCombustivel = new javax.swing.JComboBox<>();
        jLabel57 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        jTextFieldPrecoCompra = new javax.swing.JTextField();
        jLabel59 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        jTextFieldRenavan = new javax.swing.JTextField();
        jTextFieldPrecoVenda = new javax.swing.JTextField();
        jLabel61 = new javax.swing.JLabel();
        jComboBoxStatus = new javax.swing.JComboBox<>();
        jComboBoxModelos = new javax.swing.JComboBox<>();
        jLabel62 = new javax.swing.JLabel();
        jComboBoxTipoDoVeiculo = new javax.swing.JComboBox<>();
        jLabel63 = new javax.swing.JLabel();
        jTextFieldPlaca = new javax.swing.JTextField();
        jLabel64 = new javax.swing.JLabel();
        jComboBoxNumPassageiros = new javax.swing.JComboBox<>();
        jLabel65 = new javax.swing.JLabel();
        jTextFieldKm = new javax.swing.JTextField();
        jLabel66 = new javax.swing.JLabel();
        jTextField_Id = new javax.swing.JTextField();
        jLabel67 = new javax.swing.JLabel();
        jComboBoxSituacao = new javax.swing.JComboBox<>();
        jLabel68 = new javax.swing.JLabel();
        jTextFieldQuantRegistros = new javax.swing.JTextField();
        jTextFieldMarca = new javax.swing.JTextField();
        jComboBoxAnoFabricacao = new javax.swing.JComboBox<>();
        jComboBoxAnoModelo = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Manutenção de Cadastro de Veículos");
        setResizable(false);

        jPanelCadVeiculos3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jButtonPesquisarVeiculo.setFont(new java.awt.Font("Dialog", 0, 13)); // NOI18N
        jButtonPesquisarVeiculo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/mulacar/imagens/search-icon-24px.png"))); // NOI18N
        jButtonPesquisarVeiculo.setText("Pesquisar");
        jButtonPesquisarVeiculo.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButtonPesquisarVeiculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPesquisarVeiculojButtonPesquisarUsuarioActionPerformed(evt);
            }
        });

        jButtonNovoCadVeiculo.setFont(new java.awt.Font("Dialog", 0, 13)); // NOI18N
        jButtonNovoCadVeiculo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/mulacar/imagens/Apps-File-New-icon-24px.png"))); // NOI18N
        jButtonNovoCadVeiculo.setText("Novo Cadastro");
        jButtonNovoCadVeiculo.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButtonNovoCadVeiculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNovoCadVeiculojButtonNovoCadastroActionPerformed(evt);
            }
        });

        jButtonListarVeiculos.setFont(new java.awt.Font("Dialog", 0, 13)); // NOI18N
        jButtonListarVeiculos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/mulacar/imagens/Data-List-icon-24px.png"))); // NOI18N
        jButtonListarVeiculos.setText("Listagem");
        jButtonListarVeiculos.setAutoscrolls(true);
        jButtonListarVeiculos.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButtonListarVeiculos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonListarVeiculosjButtonListarUsuariosActionPerformed(evt);
            }
        });

        jButtonSalvarVeiculo.setFont(new java.awt.Font("Dialog", 0, 13)); // NOI18N
        jButtonSalvarVeiculo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/mulacar/imagens/Actions-document-save-icon-24px.png"))); // NOI18N
        jButtonSalvarVeiculo.setText("Salvar");
        jButtonSalvarVeiculo.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButtonSalvarVeiculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSalvarVeiculojButtonSalvarActionPerformed(evt);
            }
        });

        jButtonFechar.setFont(new java.awt.Font("Dialog", 0, 13)); // NOI18N
        jButtonFechar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/mulacar/imagens/Actions-window-close-icon-24px.png"))); // NOI18N
        jButtonFechar.setText("Fechar");
        jButtonFechar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButtonFechar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonFecharjButtonFecharActionPerformed(evt);
            }
        });

        jLabel52.setText("Categoria:");

        jComboBoxCategorias.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel53.setText("Modelo:");

        jLabel54.setText("Ano Fab:");

        jLabel55.setText("Ano Modelo:");

        jLabel56.setText("Combustível:");

        jComboBoxTipoDeCombustivel.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel57.setText("Tipo:");

        jLabel58.setText("Renavan:");

        jLabel59.setText("Preço de Compra:");

        jLabel60.setText("Preço de Venda:");

        jLabel61.setText("Status:");

        jComboBoxStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jComboBoxModelos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel62.setText("Marca:");

        jComboBoxTipoDoVeiculo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel63.setText("Placa:");

        jLabel64.setText("Num.Passageiros:");

        jComboBoxNumPassageiros.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel65.setText("Km:");

        jLabel66.setText("Id:");

        jTextField_Id.setEditable(false);

        jLabel67.setText("Situação:");

        jComboBoxSituacao.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel68.setText("Quant.Registros:");

        jTextFieldQuantRegistros.setEditable(false);

        jTextFieldMarca.setEditable(false);

        jComboBoxAnoFabricacao.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanelCadVeiculos3Layout = new javax.swing.GroupLayout(jPanelCadVeiculos3);
        jPanelCadVeiculos3.setLayout(jPanelCadVeiculos3Layout);
        jPanelCadVeiculos3Layout.setHorizontalGroup(
            jPanelCadVeiculos3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCadVeiculos3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelCadVeiculos3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel63)
                    .addComponent(jLabel54)
                    .addComponent(jLabel52)
                    .addComponent(jLabel58)
                    .addComponent(jLabel66)
                    .addComponent(jLabel67))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelCadVeiculos3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelCadVeiculos3Layout.createSequentialGroup()
                        .addComponent(jTextField_Id, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldPesquisarVeiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 606, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonPesquisarVeiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelCadVeiculos3Layout.createSequentialGroup()
                        .addGroup(jPanelCadVeiculos3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelCadVeiculos3Layout.createSequentialGroup()
                                .addGroup(jPanelCadVeiculos3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jComboBoxCategorias, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanelCadVeiculos3Layout.createSequentialGroup()
                                        .addComponent(jComboBoxAnoFabricacao, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel55)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jComboBoxAnoModelo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGap(18, 18, 18)
                                .addGroup(jPanelCadVeiculos3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel56)
                                    .addComponent(jLabel53)))
                            .addGroup(jPanelCadVeiculos3Layout.createSequentialGroup()
                                .addComponent(jTextFieldRenavan)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel59)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelCadVeiculos3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelCadVeiculos3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanelCadVeiculos3Layout.createSequentialGroup()
                                    .addGroup(jPanelCadVeiculos3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jComboBoxTipoDeCombustivel, 0, 189, Short.MAX_VALUE)
                                        .addComponent(jComboBoxModelos, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGap(18, 18, 18)
                                    .addGroup(jPanelCadVeiculos3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel57)
                                        .addComponent(jLabel62))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanelCadVeiculos3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jComboBoxTipoDoVeiculo, 0, 217, Short.MAX_VALUE)
                                        .addComponent(jTextFieldMarca)))
                                .addGroup(jPanelCadVeiculos3Layout.createSequentialGroup()
                                    .addComponent(jTextFieldPrecoCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel60)))
                            .addComponent(jTextFieldPrecoVenda, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanelCadVeiculos3Layout.createSequentialGroup()
                        .addGroup(jPanelCadVeiculos3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanelCadVeiculos3Layout.createSequentialGroup()
                                .addComponent(jTextFieldPlaca, javax.swing.GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel61))
                            .addComponent(jComboBoxSituacao, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jPanelCadVeiculos3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelCadVeiculos3Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBoxStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel64)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBoxNumPassageiros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel65)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldKm))
                            .addGroup(jPanelCadVeiculos3Layout.createSequentialGroup()
                                .addGap(36, 36, 36)
                                .addComponent(jLabel68)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldQuantRegistros, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanelCadVeiculos3Layout.createSequentialGroup()
                        .addGap(98, 98, 98)
                        .addComponent(jButtonNovoCadVeiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonListarVeiculos, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonSalvarVeiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonFechar, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanelCadVeiculos3Layout.setVerticalGroup(
            jPanelCadVeiculos3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCadVeiculos3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelCadVeiculos3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonPesquisarVeiculo)
                    .addComponent(jTextFieldPesquisarVeiculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel66)
                    .addComponent(jTextField_Id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelCadVeiculos3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxCategorias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel52)
                    .addComponent(jLabel53)
                    .addComponent(jComboBoxModelos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel62)
                    .addComponent(jTextFieldMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelCadVeiculos3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel54)
                    .addComponent(jLabel55)
                    .addComponent(jLabel56)
                    .addComponent(jComboBoxTipoDeCombustivel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel57)
                    .addComponent(jComboBoxTipoDoVeiculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxAnoFabricacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxAnoModelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelCadVeiculos3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel58)
                    .addComponent(jTextFieldPrecoCompra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel59)
                    .addComponent(jLabel60)
                    .addComponent(jTextFieldRenavan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldPrecoVenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelCadVeiculos3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel61)
                    .addComponent(jLabel63)
                    .addComponent(jTextFieldPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel64)
                    .addComponent(jComboBoxNumPassageiros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel65)
                    .addComponent(jTextFieldKm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelCadVeiculos3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel67)
                    .addComponent(jComboBoxSituacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel68)
                    .addComponent(jTextFieldQuantRegistros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43)
                .addGroup(jPanelCadVeiculos3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonNovoCadVeiculo)
                    .addComponent(jButtonListarVeiculos)
                    .addComponent(jButtonSalvarVeiculo)
                    .addComponent(jButtonFechar))
                .addContainerGap(43, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanelCadVeiculos3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanelCadVeiculos3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonPesquisarVeiculojButtonPesquisarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPesquisarVeiculojButtonPesquisarUsuarioActionPerformed
        // TODO add your handling code here:
        try {
            preencherCampos(jTextFieldPlaca.getText());
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Atenção!\n" + erro.getMessage());
        }
    }//GEN-LAST:event_jButtonPesquisarVeiculojButtonPesquisarUsuarioActionPerformed

    private void jButtonNovoCadVeiculojButtonNovoCadastroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNovoCadVeiculojButtonNovoCadastroActionPerformed
        // TODO add your handling code here:
        try {
            limpaCampos();
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Atenção!\n" + erro.getMessage());
        }
    }//GEN-LAST:event_jButtonNovoCadVeiculojButtonNovoCadastroActionPerformed

    private void jButtonListarVeiculosjButtonListarUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonListarVeiculosjButtonListarUsuariosActionPerformed
        // TODO add your handling code here:
        try {

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Atenção!\n" + erro.getMessage());
        }
    }//GEN-LAST:event_jButtonListarVeiculosjButtonListarUsuariosActionPerformed

    private void jButtonSalvarVeiculojButtonSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalvarVeiculojButtonSalvarActionPerformed
        // TODO add your handling code here:
        if (jComboBoxCategorias.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Selecione a categoria do veículo!\n");
        }
        if (jComboBoxModelos.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Selecione o modelo do veículo!\n");
        }
        if (jComboBoxAnoFabricacao.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Selecione o ano de fabricação do veículo!\n");
        }
        if (jComboBoxTipoDeCombustivel.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Selecione o tipo de combustível do veículo!\n");
        }
        if (jComboBoxTipoDoVeiculo.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Selecione o tipo do veículo!\n");
        }
        if (jTextFieldRenavan.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Informe o número do renavan!\n");
        }
        if (jTextFieldPrecoCompra.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Informe o valor de compra do veículo!\n");
        }
        if (jTextFieldPrecoVenda.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Informe o valor de venda do veículo!\n");
        }
        if (jTextFieldPlaca.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Informe a placa do veículo!\n");
        }
        if (jComboBoxStatus.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Informe o status do veículo!\n");
        }
        if (jComboBoxNumPassageiros.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Informe o número de passageiros!\n");
        }
        if (jTextFieldKm.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Informe a quilometragem atual do veículo!\n");
        }
        if (jComboBoxSituacao.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Informe a situação do veículo!\n");
        } else {

            try {
                Categoria categoria = new Categoria();
                categoria.splitCategoria(jComboBoxCategorias.getSelectedItem().toString());
                
                
                
                
                
                
                
                

            } catch (Exception erro) {
                JOptionPane.showMessageDialog(null, "Atenção!\n" + erro.getMessage());
            }
        }
    }//GEN-LAST:event_jButtonSalvarVeiculojButtonSalvarActionPerformed

    private void jButtonFecharjButtonFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFecharjButtonFecharActionPerformed
        // TODO add your handling code here:
        try {
            this.dispose();
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Atenção!\n" + erro.getMessage());
        }
    }//GEN-LAST:event_jButtonFecharjButtonFecharActionPerformed

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
            java.util.logging.Logger.getLogger(VeiculoApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VeiculoApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VeiculoApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VeiculoApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                VeiculoApp dialog = new VeiculoApp(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton jButtonListarVeiculos;
    private javax.swing.JButton jButtonNovoCadVeiculo;
    private javax.swing.JButton jButtonPesquisarVeiculo;
    private javax.swing.JButton jButtonSalvarVeiculo;
    private javax.swing.JComboBox<String> jComboBoxAnoFabricacao;
    private javax.swing.JComboBox<String> jComboBoxAnoModelo;
    private javax.swing.JComboBox<String> jComboBoxCategorias;
    private javax.swing.JComboBox<String> jComboBoxModelos;
    private javax.swing.JComboBox<String> jComboBoxNumPassageiros;
    private javax.swing.JComboBox<String> jComboBoxSituacao;
    private javax.swing.JComboBox<String> jComboBoxStatus;
    private javax.swing.JComboBox<String> jComboBoxTipoDeCombustivel;
    private javax.swing.JComboBox<String> jComboBoxTipoDoVeiculo;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JPanel jPanelCadVeiculos3;
    private javax.swing.JTextField jTextFieldKm;
    private javax.swing.JTextField jTextFieldMarca;
    private javax.swing.JTextField jTextFieldPesquisarVeiculo;
    private javax.swing.JTextField jTextFieldPlaca;
    private javax.swing.JTextField jTextFieldPrecoCompra;
    private javax.swing.JTextField jTextFieldPrecoVenda;
    private javax.swing.JTextField jTextFieldQuantRegistros;
    private javax.swing.JTextField jTextFieldRenavan;
    private javax.swing.JTextField jTextField_Id;
    // End of variables declaration//GEN-END:variables
}
