/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mulacar.app;

import br.com.mulacar.bll.ClienteBll;
import br.com.mulacar.bll.DevolucaoBll;
import br.com.mulacar.bll.LocacaoBll;
import br.com.mulacar.bll.VeiculoBll;
import br.com.mulacar.enumeration.EnumStatus;
import br.com.mulacar.model.Cliente;
import br.com.mulacar.model.Devolucao;
import br.com.mulacar.model.Locacao;
import br.com.mulacar.model.Usuario;
import br.com.mulacar.model.Veiculo;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author roger
 */
public class DevolucaoApp extends javax.swing.JDialog {

    private Devolucao devolucao;
    private Locacao locacao;
    private LocacaoBll locacaoBll;
    private ClienteBll clienteBll;
    private VeiculoBll veiculoBll;
    private Usuario usuario;
    private DevolucaoBll devolucaoBll;
    private BigDecimal precoLitroDeCombustivel;
    public static BigDecimal valorTotalDeCombustivel;
    public static BigDecimal valorTotalApagar;

    /**
     * Creates new form DevolucaoApp
     */
    public DevolucaoApp(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        try {
            initComponents();

            this.usuario = LoginApp.usuario;
//            this.usuario = new Usuario(LoginApp.usuario.toString());
            jTextFieldUsuárioLogado.setText(String.valueOf(this.usuario.getNome()));

            locacaoBll = new LocacaoBll();
            clienteBll = new ClienteBll();
            veiculoBll = new VeiculoBll();
            devolucaoBll = new DevolucaoBll();
            precoLitroDeCombustivel = new BigDecimal(6);
            valorTotalDeCombustivel = new BigDecimal(0);

            jTextFieldCamposDePesquisa.requestFocus();
            jTextAreaObservacoes.setLineWrap(true);

            jComboBoxTanqueCheio.removeAllItems();
            String[] opcao = {" ", "Não", "Sim"};
            for (String string : opcao) {
                jComboBoxTanqueCheio.addItem(string.toUpperCase());
            }

            jComboBoxOpcaoDeFiltro.removeAllItems();
            String[] filtro = {"todos", "cpf cliente", "placa veículo", "vigente",
                "finalizado", "pendente", "aguardando", "prazo_expirado"};
            for (String escolha : filtro) {
                jComboBoxOpcaoDeFiltro.addItem(escolha.toUpperCase());
            }

            jComboBoxStatusDaLocacao.removeAllItems();
            jComboBoxStatusDaLocacao.addItem("");
            for (EnumStatus status : EnumStatus.values()) {
                jComboBoxStatusDaLocacao.addItem(status.toString());
            }
            limpaCampos();

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro ao inicializar algum componente!\n" + erro.getMessage());
        }

    }

    private void printOutLocations(List<Locacao> listaLocacoes) throws Exception {
        DefaultTableModel model = (DefaultTableModel) jTableLocacoes.getModel();
        model.setNumRows(0);
        locacaoBll.ordenaListaLocacoes(listaLocacoes);
        jLabelQuantRegistros.setText("Nº Reg: " + String.valueOf(listaLocacoes.size()));
        for (int pos = 0; pos < listaLocacoes.size(); pos++) {
            String[] linha = new String[5];
            Locacao aux = listaLocacoes.get(pos);
            linha[0] = aux.getId() + "";
            linha[1] = aux.getCliente().getNome().toUpperCase();
            linha[2] = aux.getCliente().getCpfCnpj();
            linha[3] = aux.getVeiculo().getPlaca().toUpperCase();
            linha[4] = aux.getStatus().toString();
            model.addRow(linha);
        }

    }

    private void printOutDevolutions(List<Devolucao> listaDevolucao) throws Exception {
        DefaultTableModel model = (DefaultTableModel) jTableDevolucaoConcluida.getModel();
        model.setNumRows(0);
        devolucaoBll.ordenarListaDeDevolucoes(listaDevolucao);
        for (int pos = 0; pos < listaDevolucao.size(); pos++) {
            String[] linha = new String[10];
            Devolucao aux = listaDevolucao.get(pos);
            linha[0] = aux.getId() + "";
            linha[1] = String.valueOf(aux.getLocacao().getId());
            linha[2] = convertDate(aux.getDataDevolucaoRealizada());
            linha[3] = String.valueOf(aux.isTanqueCheio()).toUpperCase();
            linha[4] = String.valueOf(aux.getKmFinal());
            linha[5] = String.valueOf(aux.getValorMulta());
            linha[6] = String.valueOf(aux.getValorCombustivel());
            linha[7] = String.valueOf(aux.getLocacao().getValorCaucao());
            linha[8] = String.valueOf(aux.getValorPago());
            linha[9] = String.valueOf(aux.getUsuario().getNome().toUpperCase());
            model.addRow(linha);
        }

    }

    public static String convertDate(Date dtConsulta) {
        try {
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss", new Locale("pt", "BR"));
            return formato.format(dtConsulta);
        } catch (Exception erro) {
            erro.printStackTrace();
            return null;
        }
    }

    private static Date createNewDate(String data) {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        try {
            return formato.parse(data);
        } catch (Exception erro) {
            erro.printStackTrace();
            return null;
        }
    }

    private void limpaCampos() {

        jTextFieldCamposDePesquisa.setText("");
        jTextField_IdLocacao.setText("");
        jTextFieldCpfMotorista.setText("");
        jTextFieldNomeMotorista.setText("");
        jTextFieldModeloVeiculo.setText("");
        jTextFieldMarcaVeiculo.setText("");
        jTextFieldSituacaoTanque.setText("");
        jTextFieldKmInicial.setText("");
        jTextFieldDataRetirada.setText("");
        jTextFieldDataDevolucaoPrevista.setText("");
        jTextFieldReserva.setText("");
        jTextFieldValorAcessorios.setText("0.00");
        jTextFieldValorLocacao.setText("0.00");
        jTextFieldValorSeguro.setText("0.00");
        jTextFieldValorCaucao.setText("0.00");
        jComboBoxStatusDaLocacao.setSelectedIndex(0);
        jTextAreaObservacoes.setText("");
        jTextFieldValorMulta.setText("0.00");
        jTextFieldValorCombustivel.setText("0.00");
        jTextFieldValorAbatimento.setText("0.00");
        jTextFieldValorApagar.setText("0.00");
        jTextFieldDataDevolucao.setText(convertDate(new Date()));
        jTextFieldKmFinal.setText("");
        jComboBoxTanqueCheio.setSelectedIndex(0);

    }

    private void preencherCamposLocacao(int id) throws Exception {
        if (id > 0) {
            try {

                // Opção de resposta da combbox
                int resposta = 0;

                // Caixa de diálogo se tanque está cheio ou não
                resposta = JOptionPane.showConfirmDialog(null, "O tanque deste veículo está cheio ? ");

                // Caso não esteja cheia o if executará outra caixa de diálogo para saber
                // quantos litros foram necessários para encher o tanque...
                // e faz os calculos do valor do combustível (Preço unitário do litro, declarado no início da classe)
                if (resposta == 1) {
                    jComboBoxTanqueCheio.setSelectedItem("NÃO");
                    BigDecimal litros = new BigDecimal(JOptionPane.showInputDialog(null,
                            "Informe aqui a quantidade de litros necessário para encher o tanque:",
                            "Situação do Tanque de Combustível", JOptionPane.NO_OPTION));
                    valorTotalDeCombustivel = precoLitroDeCombustivel.multiply(litros);
                    jTextFieldValorCombustivel.setText(String.format("%.2f", valorTotalDeCombustivel));

                    // Caso o tanque esteja cheio, não há caixa de diálogo e segue o fluxo normal com os cálculos
                } else if (resposta == 0) {
                    jComboBoxTanqueCheio.setSelectedItem("SIM");
                    valorTotalDeCombustivel = new BigDecimal(0);
                    jTextFieldValorCombustivel.setText(String.format("%.2f", valorTotalDeCombustivel));
                }

                // Este método busca uma locação específica pelo id fornecido
                this.locacao = locacaoBll.getLocacaoPorId(new Locacao(id));

                // Aqui inicia o preenchimento dos campos referentes a locação
                jTextField_IdLocacao.setText(String.valueOf(this.locacao.getId()));
                jTextFieldCpfMotorista.setText(this.locacao.getMotorista().getCpf());
                jTextFieldNomeMotorista.setText(this.locacao.getMotorista().getNome());
                jTextFieldModeloVeiculo.setText(this.locacao.getVeiculo().getModelo().getDescricao().toUpperCase());
                jTextFieldMarcaVeiculo.setText(this.locacao.getVeiculo().getModelo().getMarca().getDescricao().toUpperCase());
                if (this.locacao.isTanqueCheio()) {
                    jTextFieldSituacaoTanque.setText("CHEIO");
                }
                jTextFieldSituacaoTanque.setText("INCOMPLETO");
                jTextFieldKmInicial.setText(this.locacao.getKmInicial());
                jTextFieldDataRetirada.setText(convertDate(this.locacao.getDataRetirada()));
                jTextFieldDataDevolucaoPrevista.setText(convertDate(this.locacao.getDataDevolucaoPrevista()));
                if (this.locacao.isReserva()) {
                    jTextFieldReserva.setText("SIM");
                }
                jTextFieldReserva.setText("NÃO");
                jTextFieldValorAcessorios.setText(String.format("%.2f", this.locacao.getValorTotalAcessorios()));
                jTextFieldValorLocacao.setText(String.format("%.2f", this.locacao.getValorLocacao()));
                jTextFieldValorSeguro.setText(String.format("%.2f", this.locacao.getValorSeguro()));
                jTextFieldValorCaucao.setText(String.format("%.2f", this.locacao.getValorCaucao()));
                jComboBoxStatusDaLocacao.setSelectedItem(this.locacao.getStatus().toString());
                jTextAreaObservacoes.setText(this.locacao.getObservacoes().toUpperCase());
                jTextFieldValorAbatimento.setText(String.format("%.2f", this.locacao.getValorCaucao()));
                // Aqui termina o preenchimento dos campos referente a locação

                // Aqui começa o preenchimento de campos calculados pela regra de negócio
                jTextFieldValorMulta.setText(String.valueOf(this.locacao.getValorMulta()));

                jTextAreaObservacoes.append("\n\nValor Total Da Locação: R$ " + String.valueOf(valorTotalApagar));

                // Verificando se valor total a pagar é menor que zero
                if (valorTotalApagar.compareTo(BigDecimal.ZERO) == -1) {
                    jTextFieldValorApagar.setText(String.valueOf("0.00"));
                    jTextFieldValorAreceber.setText(String.valueOf(valorTotalApagar).replaceAll("-", ""));

                } else {
                    jTextFieldValorAreceber.setText(String.valueOf("0.00"));
                    jTextFieldValorApagar.setText(String.valueOf(valorTotalApagar));
                }
            } catch (Exception erro) {
                JOptionPane.showMessageDialog(null, "Atenção no preencher campos !!!\n" + erro.getMessage());
            }
        }
    }

    private void preencherCamposDevolucao(int id) throws Exception {
        if (id > 0) {
            try {
                this.devolucao = devolucaoBll.getConsultaDevolucoesPorId(new Devolucao(id));

                jTextFieldValorMulta.setText(String.valueOf(this.devolucao.getValorMulta()));
                jTextFieldDataDevolucao.setText(convertDate(this.devolucao.getDataDevolucaoRealizada()));
                jTextFieldValorCombustivel.setText(String.valueOf(this.devolucao.getValorCombustivel()));
                jTextFieldKmFinal.setText(String.valueOf(this.devolucao.getKmFinal()));
                jTextFieldValorAbatimento.setText(String.valueOf(this.devolucao.getLocacao().getValorCaucao()));
                if (this.devolucao.isTanqueCheio()) {
                    jComboBoxTanqueCheio.setSelectedItem("SIM");
                }
                jComboBoxTanqueCheio.setSelectedItem("NÃO");
                if (this.devolucao.getValorPago().compareTo(BigDecimal.ZERO) == -1) {
                    jTextFieldValorApagar.setText("0.00");
                    jTextFieldValorAreceber.setText(String.valueOf(this.devolucao.getValorPago()).replaceAll("-", ""));
                }
                jTextFieldValorApagar.setText(String.valueOf(this.devolucao.getValorPago()));
                jTextFieldValorAreceber.setText("0.00");
                jTextFieldValorAcessorios.setText(String.valueOf(this.devolucao.getLocacao().getValorTotalAcessorios()));
                jTextFieldValorLocacao.setText(String.valueOf(this.devolucao.getLocacao().getValorLocacao()));
                jTextFieldValorSeguro.setText(String.valueOf(this.devolucao.getLocacao().getValorSeguro()));
                jTextFieldValorCaucao.setText(String.valueOf(this.devolucao.getLocacao().getValorCaucao()));
                jComboBoxStatusDaLocacao.setSelectedItem(String.valueOf(this.devolucao.getLocacao().getStatus()));
                jTextAreaObservacoes.setText(String.valueOf(this.devolucao.getObservacao()));

            } catch (Exception erro) {
                JOptionPane.showMessageDialog(null, "Atenção ao preencher campos da devolução!!!" + erro.getMessage());
            }
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

        jToggleButton1 = new javax.swing.JToggleButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanelDevolucao = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldDataDevolucao = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jComboBoxTanqueCheio = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldKmFinal = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jTextFieldValorMulta = new javax.swing.JTextField();
        jTextFieldValorCombustivel = new javax.swing.JTextField();
        jTextFieldValorApagar = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableLocacoes = new javax.swing.JTable();
        jButtonBuscarLocacao = new javax.swing.JButton();
        jTextFieldCamposDePesquisa = new javax.swing.JTextField();
        jComboBoxOpcaoDeFiltro = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jTextFieldUsuárioLogado = new javax.swing.JTextField();
        jPanelDadosDaLocacao = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextField_IdLocacao = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jTextFieldCpfMotorista = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jTextFieldNomeMotorista = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jTextFieldModeloVeiculo = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jTextFieldMarcaVeiculo = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jTextFieldSituacaoTanque = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jTextFieldKmInicial = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jTextFieldDataRetirada = new javax.swing.JTextField();
        jTextFieldDataDevolucaoPrevista = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jTextFieldReserva = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jTextFieldValorAcessorios = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jTextFieldValorLocacao = new javax.swing.JTextField();
        jTextFieldValorSeguro = new javax.swing.JTextField();
        jTextFieldValorCaucao = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextAreaObservacoes = new javax.swing.JTextArea();
        jLabel12 = new javax.swing.JLabel();
        jComboBoxStatusDaLocacao = new javax.swing.JComboBox<>();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jTextFieldValorAbatimento = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTableDevolucaoConcluida = new javax.swing.JTable();
        jLabelQuantRegistros = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jTextFieldValorAreceber = new javax.swing.JTextField();
        jButtonFechar = new javax.swing.JButton();
        jButtonListarDevolucoes = new javax.swing.JButton();
        jButtonConcluirDevolucao = new javax.swing.JButton();
        jButtonListarLocacoes = new javax.swing.JButton();

        jToggleButton1.setText("jToggleButton1");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Manutenção de Devoluções de Veículos - MULACAR ");
        setResizable(false);

        jPanelDevolucao.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel2.setFont(new java.awt.Font("Dialog", 0, 13)); // NOI18N
        jLabel2.setText("Data da Devolução:");

        jTextFieldDataDevolucao.setEditable(false);
        jTextFieldDataDevolucao.setFont(new java.awt.Font("Dialog", 0, 13)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Dialog", 0, 13)); // NOI18N
        jLabel3.setText("Tanque Cheio ?");

        jComboBoxTanqueCheio.setFont(new java.awt.Font("Dialog", 0, 13)); // NOI18N
        jComboBoxTanqueCheio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel4.setFont(new java.awt.Font("Dialog", 0, 13)); // NOI18N
        jLabel4.setText("Km Final:");

        jTextFieldKmFinal.setFont(new java.awt.Font("Dialog", 0, 13)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Dialog", 0, 13)); // NOI18N
        jLabel6.setText("Valor da Multa:");

        jLabel7.setFont(new java.awt.Font("Dialog", 0, 13)); // NOI18N
        jLabel7.setText("Valor Combustível:");

        jLabel8.setFont(new java.awt.Font("Dialog", 0, 13)); // NOI18N
        jLabel8.setText("Valor à Pagar:");

        jTextFieldValorMulta.setEditable(false);
        jTextFieldValorMulta.setFont(new java.awt.Font("Dialog", 0, 13)); // NOI18N

        jTextFieldValorCombustivel.setEditable(false);
        jTextFieldValorCombustivel.setFont(new java.awt.Font("Dialog", 0, 13)); // NOI18N

        jTextFieldValorApagar.setEditable(false);
        jTextFieldValorApagar.setFont(new java.awt.Font("Dialog", 0, 13)); // NOI18N

        jTableLocacoes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nº Contrato", "Cliente", "CPF do Cliente", "Placa do Veículo", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableLocacoes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTableLocacoesMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jTableLocacoes);

        jButtonBuscarLocacao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/mulacar/imagens/search-icon-16px_1.png"))); // NOI18N
        jButtonBuscarLocacao.setText("Buscar Locação");
        jButtonBuscarLocacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBuscarLocacaoActionPerformed(evt);
            }
        });

        jTextFieldCamposDePesquisa.setFont(new java.awt.Font("Dialog", 0, 13)); // NOI18N

        jComboBoxOpcaoDeFiltro.setFont(new java.awt.Font("Dialog", 0, 13)); // NOI18N
        jComboBoxOpcaoDeFiltro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel10.setFont(new java.awt.Font("Dialog", 0, 13)); // NOI18N
        jLabel10.setText("Filtrar por:");

        jLabel11.setText("Usuário:");

        jTextFieldUsuárioLogado.setEditable(false);

        jPanelDadosDaLocacao.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel1.setFont(new java.awt.Font("Dialog", 0, 13)); // NOI18N
        jLabel1.setText("Id:");

        jTextField_IdLocacao.setEditable(false);
        jTextField_IdLocacao.setFont(new java.awt.Font("Dialog", 0, 13)); // NOI18N

        jLabel13.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel13.setText("CPF Motorista");

        jTextFieldCpfMotorista.setEditable(false);

        jLabel14.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel14.setText("Nome do Motorista");

        jTextFieldNomeMotorista.setEditable(false);

        jLabel15.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel15.setText("Modelo do Veículo:");

        jTextFieldModeloVeiculo.setEditable(false);

        jLabel16.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel16.setText("Marca do Veículo:");

        jTextFieldMarcaVeiculo.setEditable(false);

        jLabel17.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel17.setText("Sit. do Tanque:");

        jTextFieldSituacaoTanque.setEditable(false);

        jLabel18.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel18.setText("Km Inicial:");

        jTextFieldKmInicial.setEditable(false);

        jLabel19.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel19.setText("Data de Retirada:");

        jLabel20.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel20.setText("Data Prevista da Devolução");

        jTextFieldDataRetirada.setEditable(false);

        jTextFieldDataDevolucaoPrevista.setEditable(false);

        jLabel21.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel21.setText("Reserva ?");

        jTextFieldReserva.setEditable(false);

        jLabel22.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel22.setText("Valor dos Acessórios:");

        jTextFieldValorAcessorios.setEditable(false);

        jLabel23.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel23.setText("Valor Locação:");

        jLabel24.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel24.setText("Valor Seguro:");

        jLabel25.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel25.setText("Valor Caução");

        jTextFieldValorLocacao.setEditable(false);

        jTextFieldValorSeguro.setEditable(false);

        jTextFieldValorCaucao.setEditable(false);

        jLabel5.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel5.setText("Observações:");

        jTextAreaObservacoes.setColumns(20);
        jTextAreaObservacoes.setRows(5);
        jScrollPane2.setViewportView(jTextAreaObservacoes);

        jLabel12.setFont(new java.awt.Font("Dialog", 0, 13)); // NOI18N
        jLabel12.setText("Status:");

        jComboBoxStatusDaLocacao.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jComboBoxStatusDaLocacao.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanelDadosDaLocacaoLayout = new javax.swing.GroupLayout(jPanelDadosDaLocacao);
        jPanelDadosDaLocacao.setLayout(jPanelDadosDaLocacaoLayout);
        jPanelDadosDaLocacaoLayout.setHorizontalGroup(
            jPanelDadosDaLocacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelDadosDaLocacaoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelDadosDaLocacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelDadosDaLocacaoLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField_IdLocacao, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldCpfMotorista))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelDadosDaLocacaoLayout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldNomeMotorista))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelDadosDaLocacaoLayout.createSequentialGroup()
                        .addGroup(jPanelDadosDaLocacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15)
                            .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelDadosDaLocacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldMarcaVeiculo)
                            .addComponent(jTextFieldModeloVeiculo)))
                    .addGroup(jPanelDadosDaLocacaoLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldSituacaoTanque, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldKmInicial))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelDadosDaLocacaoLayout.createSequentialGroup()
                        .addGroup(jPanelDadosDaLocacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldDataRetirada, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19))
                        .addGap(18, 18, 18)
                        .addGroup(jPanelDadosDaLocacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextFieldDataDevolucaoPrevista, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanelDadosDaLocacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelDadosDaLocacaoLayout.createSequentialGroup()
                                .addComponent(jLabel21)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jTextFieldReserva)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelDadosDaLocacaoLayout.createSequentialGroup()
                        .addGroup(jPanelDadosDaLocacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelDadosDaLocacaoLayout.createSequentialGroup()
                                .addComponent(jTextFieldValorAcessorios, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28)
                                .addComponent(jTextFieldValorLocacao, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)
                                .addComponent(jTextFieldValorSeguro, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(22, 22, 22))
                            .addGroup(jPanelDadosDaLocacaoLayout.createSequentialGroup()
                                .addComponent(jLabel22)
                                .addGap(28, 28, 28)
                                .addComponent(jLabel23)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel24)
                                .addGap(26, 26, 26)))
                        .addGroup(jPanelDadosDaLocacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelDadosDaLocacaoLayout.createSequentialGroup()
                                .addComponent(jLabel25)
                                .addGap(11, 11, 11))
                            .addComponent(jTextFieldValorCaucao)))
                    .addGroup(jPanelDadosDaLocacaoLayout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBoxStatusDaLocacao, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanelDadosDaLocacaoLayout.setVerticalGroup(
            jPanelDadosDaLocacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDadosDaLocacaoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelDadosDaLocacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField_IdLocacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel13)
                    .addComponent(jTextFieldCpfMotorista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelDadosDaLocacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jTextFieldNomeMotorista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelDadosDaLocacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldModeloVeiculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelDadosDaLocacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldMarcaVeiculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelDadosDaLocacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(jTextFieldSituacaoTanque, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18)
                    .addComponent(jTextFieldKmInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelDadosDaLocacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(jLabel20)
                    .addComponent(jLabel21))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelDadosDaLocacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldDataRetirada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldDataDevolucaoPrevista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldReserva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelDadosDaLocacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(jLabel23)
                    .addComponent(jLabel24)
                    .addComponent(jLabel25))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelDadosDaLocacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldValorAcessorios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldValorLocacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldValorSeguro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldValorCaucao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelDadosDaLocacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxStatusDaLocacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel26.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel26.setText("Dados para Devolução do veículo");

        jLabel27.setFont(new java.awt.Font("Dialog", 0, 13)); // NOI18N
        jLabel27.setText("Abatimentos:");

        jTextFieldValorAbatimento.setEditable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jTableDevolucaoConcluida.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cód. Devolução", "Cod. Locacao", "Data Devolução", "Sit. do Tanque", "Km Final", "Multa  R$", "Combustível R$", "Abatimentos R$", "Total Pago R$", "Usuario"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableDevolucaoConcluida.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTableDevolucaoConcluidaMouseReleased(evt);
            }
        });
        jScrollPane4.setViewportView(jTableDevolucaoConcluida);
        if (jTableDevolucaoConcluida.getColumnModel().getColumnCount() > 0) {
            jTableDevolucaoConcluida.getColumnModel().getColumn(0).setMinWidth(100);
            jTableDevolucaoConcluida.getColumnModel().getColumn(0).setPreferredWidth(100);
            jTableDevolucaoConcluida.getColumnModel().getColumn(0).setMaxWidth(100);
            jTableDevolucaoConcluida.getColumnModel().getColumn(1).setMinWidth(80);
            jTableDevolucaoConcluida.getColumnModel().getColumn(1).setPreferredWidth(80);
            jTableDevolucaoConcluida.getColumnModel().getColumn(1).setMaxWidth(80);
            jTableDevolucaoConcluida.getColumnModel().getColumn(3).setMinWidth(100);
            jTableDevolucaoConcluida.getColumnModel().getColumn(3).setPreferredWidth(100);
            jTableDevolucaoConcluida.getColumnModel().getColumn(3).setMaxWidth(100);
            jTableDevolucaoConcluida.getColumnModel().getColumn(4).setMinWidth(80);
            jTableDevolucaoConcluida.getColumnModel().getColumn(4).setPreferredWidth(80);
            jTableDevolucaoConcluida.getColumnModel().getColumn(4).setMaxWidth(80);
            jTableDevolucaoConcluida.getColumnModel().getColumn(5).setMinWidth(120);
            jTableDevolucaoConcluida.getColumnModel().getColumn(5).setPreferredWidth(120);
            jTableDevolucaoConcluida.getColumnModel().getColumn(5).setMaxWidth(120);
            jTableDevolucaoConcluida.getColumnModel().getColumn(6).setMinWidth(120);
            jTableDevolucaoConcluida.getColumnModel().getColumn(6).setPreferredWidth(120);
            jTableDevolucaoConcluida.getColumnModel().getColumn(6).setMaxWidth(120);
            jTableDevolucaoConcluida.getColumnModel().getColumn(7).setMinWidth(120);
            jTableDevolucaoConcluida.getColumnModel().getColumn(7).setPreferredWidth(120);
            jTableDevolucaoConcluida.getColumnModel().getColumn(7).setMaxWidth(120);
            jTableDevolucaoConcluida.getColumnModel().getColumn(8).setMinWidth(120);
            jTableDevolucaoConcluida.getColumnModel().getColumn(8).setPreferredWidth(120);
            jTableDevolucaoConcluida.getColumnModel().getColumn(8).setMaxWidth(120);
        }

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabelQuantRegistros.setText("Quant. Reg:");

        jLabel9.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel9.setText("Valor à Receber:");

        jTextFieldValorAreceber.setEditable(false);

        jButtonFechar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/mulacar/imagens/Actions-window-close-icon-24px.png"))); // NOI18N
        jButtonFechar.setText("Fechar");
        jButtonFechar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonFecharActionPerformed(evt);
            }
        });

        jButtonListarDevolucoes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/mulacar/imagens/Data-List-icon-24px.png"))); // NOI18N
        jButtonListarDevolucoes.setText("Consultar Devoluções");
        jButtonListarDevolucoes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonListarDevolucoesActionPerformed(evt);
            }
        });

        jButtonConcluirDevolucao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/mulacar/imagens/Actions-document-save-icon-24px.png"))); // NOI18N
        jButtonConcluirDevolucao.setText("Concluir Devolução ");
        jButtonConcluirDevolucao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonConcluirDevolucaoActionPerformed(evt);
            }
        });

        jButtonListarLocacoes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/mulacar/imagens/Data-List-icon-24px.png"))); // NOI18N
        jButtonListarLocacoes.setText("Relatório de Locações");
        jButtonListarLocacoes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonListarLocacoesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelDevolucaoLayout = new javax.swing.GroupLayout(jPanelDevolucao);
        jPanelDevolucao.setLayout(jPanelDevolucaoLayout);
        jPanelDevolucaoLayout.setHorizontalGroup(
            jPanelDevolucaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelDevolucaoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelDevolucaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelDevolucaoLayout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldUsuárioLogado, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonConcluirDevolucao)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonListarDevolucoes)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonFechar, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelDevolucaoLayout.createSequentialGroup()
                        .addGroup(jPanelDevolucaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelDevolucaoLayout.createSequentialGroup()
                                .addGroup(jPanelDevolucaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanelDevolucaoLayout.createSequentialGroup()
                                        .addGroup(jPanelDevolucaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanelDevolucaoLayout.createSequentialGroup()
                                                .addGroup(jPanelDevolucaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel6)
                                                    .addComponent(jLabel7)
                                                    .addComponent(jLabel8))
                                                .addGap(10, 10, 10)
                                                .addGroup(jPanelDevolucaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(jPanelDevolucaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                        .addComponent(jTextFieldValorApagar)
                                                        .addComponent(jTextFieldValorAbatimento, javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jTextFieldValorCombustivel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addComponent(jTextFieldValorMulta)))
                                            .addGroup(jPanelDevolucaoLayout.createSequentialGroup()
                                                .addComponent(jLabel27)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 174, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(jPanelDevolucaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanelDevolucaoLayout.createSequentialGroup()
                                                .addGap(41, 41, 41)
                                                .addGroup(jPanelDevolucaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel2)
                                                    .addComponent(jLabel4))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jPanelDevolucaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(jTextFieldKmFinal)
                                                    .addComponent(jTextFieldDataDevolucao, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelDevolucaoLayout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(jPanelDevolucaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel3)
                                                    .addComponent(jLabel9))
                                                .addGap(31, 31, 31)
                                                .addGroup(jPanelDevolucaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(jComboBoxTanqueCheio, 0, 133, Short.MAX_VALUE)
                                                    .addComponent(jTextFieldValorAreceber)))))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelDevolucaoLayout.createSequentialGroup()
                                        .addComponent(jLabelQuantRegistros, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButtonListarLocacoes))
                                    .addGroup(jPanelDevolucaoLayout.createSequentialGroup()
                                        .addComponent(jLabel10)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jComboBoxOpcaoDeFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jTextFieldCamposDePesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButtonBuscarLocacao)
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanelDevolucaoLayout.createSequentialGroup()
                                .addComponent(jLabel26)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addComponent(jPanelDadosDaLocacao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanelDevolucaoLayout.setVerticalGroup(
            jPanelDevolucaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDevolucaoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanelDevolucaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelDevolucaoLayout.createSequentialGroup()
                        .addGroup(jPanelDevolucaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonBuscarLocacao)
                            .addComponent(jTextFieldCamposDePesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBoxOpcaoDeFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanelDevolucaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButtonListarLocacoes)
                            .addComponent(jLabelQuantRegistros))
                        .addGap(12, 12, 12)
                        .addComponent(jLabel26)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanelDevolucaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelDevolucaoLayout.createSequentialGroup()
                                .addGroup(jPanelDevolucaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextFieldValorMulta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanelDevolucaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextFieldValorCombustivel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7)))
                            .addGroup(jPanelDevolucaoLayout.createSequentialGroup()
                                .addGroup(jPanelDevolucaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2)
                                    .addComponent(jTextFieldDataDevolucao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanelDevolucaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(jTextFieldKmFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanelDevolucaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelDevolucaoLayout.createSequentialGroup()
                                .addGroup(jPanelDevolucaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel27)
                                    .addComponent(jTextFieldValorAbatimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(11, 11, 11)
                                .addGroup(jPanelDevolucaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextFieldValorApagar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel9)
                                    .addComponent(jTextFieldValorAreceber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanelDevolucaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel3)
                                .addComponent(jComboBoxTanqueCheio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(5, 5, 5))
                    .addComponent(jPanelDadosDaLocacao, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanelDevolucaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jTextFieldUsuárioLogado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonFechar)
                    .addComponent(jButtonListarDevolucoes)
                    .addComponent(jButtonConcluirDevolucao))
                .addGap(7, 7, 7))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelDevolucao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelDevolucao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonBuscarLocacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBuscarLocacaoActionPerformed
        // TODO add your handling code here:

        int opcaoFiltro = jComboBoxOpcaoDeFiltro.getSelectedIndex();
        String pesquisarCpfOuPlaca = jTextFieldCamposDePesquisa.getText().trim();
        try {

            switch (opcaoFiltro) {

                case 0:
                    printOutLocations(locacaoBll.getConsultaLocacoes());
                    break;
                case 1:
                    String cpf = JOptionPane.showInputDialog("Informe o CPF do cliente por favor: ");
                    Cliente cliente;
                    cliente = clienteBll.getConsultaClienteByCpfCnpj(new Cliente(cpf));
                    printOutLocations(locacaoBll.getConsultaAllLocationsByCliente(new Locacao(cliente)));
                    break;
                case 2:
                    Veiculo veiculo;
                    veiculo = veiculoBll.getConsultarVeiculoByPlaca(pesquisarCpfOuPlaca);
                    printOutLocations(locacaoBll.getConsultaAllLocationsByVeiculo(new Locacao(veiculo)));
                    break;
                default:
                    EnumStatus status = EnumStatus.valueOf(jComboBoxOpcaoDeFiltro.getSelectedItem().toString());
                    printOutLocations(locacaoBll.getConsultaLocacoesPorStatus(new Locacao(status)));
                    break;
            }

            limpaCampos();

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Atenção ao botão pesquisar locações\n" + erro.getMessage());
        }
    }//GEN-LAST:event_jButtonBuscarLocacaoActionPerformed

    private void jButtonFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFecharActionPerformed
        // TODO add your handling code here:
        try {

            this.dispose();

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, erro.getMessage());
        }
    }//GEN-LAST:event_jButtonFecharActionPerformed

    private void jButtonConcluirDevolucaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonConcluirDevolucaoActionPerformed
        // TODO add your handling code here:
        if (jTextFieldDataDevolucao.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Informe a data da devolução\n");
        } else if (jComboBoxTanqueCheio.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Informe a situação do tanque de combustível\n");
        } else if (jTextFieldKmFinal.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Informe a quilometragem\n");
        } else if (jTextFieldValorApagar.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Informe o valor a pagar\n");
        } else {
            try {

                //Insert dos dados das alterações da locação
                Locacao locacaoFinalizada = new Locacao();
                locacaoFinalizada.setId(Integer.parseInt(jTextField_IdLocacao.getText()));
                locacaoFinalizada.setStatus(EnumStatus.valueOf(jComboBoxStatusDaLocacao.getSelectedItem().toString()));
                locacaoFinalizada.setObservacoes(jTextAreaObservacoes.getText());

                locacaoBll.updateLocacaoStatus(locacaoFinalizada);
                printOutLocations(locacaoBll.getConsultaLocacoes());

                //insert dos dados da devolução
                Date DataDevolucao = createNewDate(jTextFieldDataDevolucao.getText());
                boolean tanqueCheioDevolucao = Boolean.parseBoolean(jComboBoxTanqueCheio.getSelectedItem().toString());
                int kmFinalDevolucao = Integer.parseInt(jTextFieldKmFinal.getText());
                String observacaoDevolucao = jTextAreaObservacoes.getText();
                BigDecimal valorMultaDevolucao = new BigDecimal(jTextFieldValorMulta.getText().replaceAll(",", "."));
                BigDecimal valorCombustivelDevolucao = valorTotalDeCombustivel;
                BigDecimal valorPagoDevolucao = valorTotalApagar;
                Locacao locacaoId = this.locacao;       //new Locacao(Integer.parseInt(jTextField_IdLocacao.getText()));
                Usuario usuarioId = this.usuario;

                devolucao = new Devolucao(DataDevolucao, tanqueCheioDevolucao,
                        kmFinalDevolucao, observacaoDevolucao, valorMultaDevolucao,
                        valorTotalDeCombustivel, valorTotalApagar, locacao, usuario);

                devolucaoBll.adicionarDevolucao(devolucao);
                printOutDevolutions(devolucaoBll.getConsultaDevolucoes());

                limpaCampos();
            } catch (Exception erro) {
                JOptionPane.showMessageDialog(null, "Atenção ao botão concluir devolução\n" + erro.getMessage());
            }
        }
    }//GEN-LAST:event_jButtonConcluirDevolucaoActionPerformed

    private void jButtonListarDevolucoesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonListarDevolucoesActionPerformed
        // TODO add your handling code here:
        try {
            printOutDevolutions(devolucaoBll.getConsultaDevolucoes());
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Atenção ao botão listar devoluções!!!\n" + erro.getMessage());
        }

    }//GEN-LAST:event_jButtonListarDevolucoesActionPerformed

    private void jTableLocacoesMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableLocacoesMouseReleased
        // TODO add your handling code here:
        try {

            int linha = jTableLocacoes.getSelectedRow();

            String status = jTableLocacoes.getValueAt(linha, 4).toString();

            if (status.equalsIgnoreCase("finalizado")) {
                JOptionPane.showMessageDialog(null, "Locação já está finalizada!\n"
                        + "Para detalhamento acesse o relatorio de locações!\n");
            } else {
            limpaCampos();
                Integer codigo = Integer.parseInt(jTableLocacoes.getValueAt(linha, 0).toString());
                preencherCamposLocacao(codigo);
            }

        } catch (Exception erro) {

            JOptionPane.showMessageDialog(null, "Atenção ao click do mouse na tabela\n" + erro.getMessage());

        }

    }//GEN-LAST:event_jTableLocacoesMouseReleased

    private void jTableDevolucaoConcluidaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableDevolucaoConcluidaMouseReleased
        // TODO add your handling code here:
        try {
            int linha = jTableDevolucaoConcluida.getSelectedRow();
            Integer codigo = Integer.parseInt(jTableDevolucaoConcluida.getValueAt(linha, 0).toString());
            preencherCamposDevolucao(codigo);

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Atenção ao mouse click da table devolution!!!\n" + erro.getMessage());
        }

    }//GEN-LAST:event_jTableDevolucaoConcluidaMouseReleased

    private void jButtonListarLocacoesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonListarLocacoesActionPerformed
        // TODO add your handling code here:
        try {
            new RelatorioDeLocacoesApp(null, true).setVisible(true);
            this.setVisible(true);
            
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Atenção ao botão listar locações!!!\n" + erro.getMessage());
        }
    }//GEN-LAST:event_jButtonListarLocacoesActionPerformed

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
            java.util.logging.Logger.getLogger(DevolucaoApp.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DevolucaoApp.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DevolucaoApp.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DevolucaoApp.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DevolucaoApp dialog = new DevolucaoApp(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton jButtonBuscarLocacao;
    private javax.swing.JButton jButtonConcluirDevolucao;
    private javax.swing.JButton jButtonFechar;
    private javax.swing.JButton jButtonListarDevolucoes;
    private javax.swing.JButton jButtonListarLocacoes;
    private javax.swing.JComboBox<String> jComboBoxOpcaoDeFiltro;
    private javax.swing.JComboBox<String> jComboBoxStatusDaLocacao;
    private javax.swing.JComboBox<String> jComboBoxTanqueCheio;
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
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelQuantRegistros;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelDadosDaLocacao;
    private javax.swing.JPanel jPanelDevolucao;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTableDevolucaoConcluida;
    private javax.swing.JTable jTableLocacoes;
    private javax.swing.JTextArea jTextAreaObservacoes;
    private javax.swing.JTextField jTextFieldCamposDePesquisa;
    private javax.swing.JTextField jTextFieldCpfMotorista;
    private javax.swing.JTextField jTextFieldDataDevolucao;
    private javax.swing.JTextField jTextFieldDataDevolucaoPrevista;
    private javax.swing.JTextField jTextFieldDataRetirada;
    private javax.swing.JTextField jTextFieldKmFinal;
    private javax.swing.JTextField jTextFieldKmInicial;
    private javax.swing.JTextField jTextFieldMarcaVeiculo;
    private javax.swing.JTextField jTextFieldModeloVeiculo;
    private javax.swing.JTextField jTextFieldNomeMotorista;
    private javax.swing.JTextField jTextFieldReserva;
    private javax.swing.JTextField jTextFieldSituacaoTanque;
    private javax.swing.JTextField jTextFieldUsuárioLogado;
    private javax.swing.JTextField jTextFieldValorAbatimento;
    private javax.swing.JTextField jTextFieldValorAcessorios;
    private javax.swing.JTextField jTextFieldValorApagar;
    private javax.swing.JTextField jTextFieldValorAreceber;
    private javax.swing.JTextField jTextFieldValorCaucao;
    private javax.swing.JTextField jTextFieldValorCombustivel;
    private javax.swing.JTextField jTextFieldValorLocacao;
    private javax.swing.JTextField jTextFieldValorMulta;
    private javax.swing.JTextField jTextFieldValorSeguro;
    private javax.swing.JTextField jTextField_IdLocacao;
    private javax.swing.JToggleButton jToggleButton1;
    // End of variables declaration//GEN-END:variables
}
