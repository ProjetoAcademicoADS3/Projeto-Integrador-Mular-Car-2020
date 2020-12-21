/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mulacar.app;

import br.com.mulacar.bll.ContatoBll;
import br.com.mulacar.bll.EnderecoBll;
import br.com.mulacar.bll.LocacaoBll;
import br.com.mulacar.model.Contato;
import br.com.mulacar.model.Endereco;
import br.com.mulacar.model.Locacao;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author roger
 */
public class ContratoDeLocacaoApp extends javax.swing.JDialog {

    private LocacaoBll locacaoBll;
    private Locacao locacao;
    private EnderecoBll enderecoBll;
    private Endereco enderecoCliente;
    private ContatoBll contatoBll;
    private Contato contatoCliente;
    private Contato contatoMotorista;

    /**
     * Creates new form ContratoPDF_app
     */
    public ContratoDeLocacaoApp(java.awt.Frame parent, boolean modal) throws Exception {
        super(parent, modal);
        initComponents();
        jTextAreaSaidaDoContrato.setLineWrap(true);
        
        int idContrato = LocacaoVeiculosApp.numContrato.getId();

        locacaoBll = new LocacaoBll();
        enderecoBll = new EnderecoBll();
        contatoBll = new ContatoBll();

        locacao = locacaoBll.getLocacaoPorId(new Locacao(idContrato));
        enderecoCliente = enderecoBll.getConsultaEnderecoByCliente(locacao.getCliente());
        contatoCliente = contatoBll.getConsultaContatoPorClienteId(locacao.getCliente());
        contatoMotorista = contatoBll.getConsultaContatoPorMotoristaId(locacao.getMotorista());

        jTextAreaSaidaDoContrato.append(imprimirContrato(locacao, enderecoCliente, contatoCliente, contatoMotorista));
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

    private String imprimirContrato(Locacao locacao, Endereco end,
            Contato contatoCli, Contato contatoMotorista) throws Exception {

        String contrato = "\n\n\t\t\t\tCONTRATO DE LOCAÇÃO DE VEÍCULO\n"
                //Dados do contrato

                + "\n\t1. Partes Contratantes\n\n"
                //Dados da locadora MULACAR
                + "\t1.1 Locadora de Veículos\n\n\tMULACAR, sediada na cidade de Goiânia, "
                + "estado de Goiás, inscrita no CNPJ sob o nº 62.566.006/0001-88, "
                + "empresa esta que opera sob licenciamento, devidamente identifi"
                + "cada no anverso deste Contrato de Locação de Veículos.\n\n"
                //Dados do cliente
                + "\t1.2 Locatário\n\n\t" + locacao.getCliente().getNome().toUpperCase() + " portador do CPF/CNPJ nº " + locacao.getCliente().getCpfCnpj()
                + ", portador do RG " + locacao.getCliente().getRg() + ", orgão emissor: " + locacao.getCliente().getOrgaoEmissor().toUpperCase()
                + ", residente nesta cidade de " + end.getCidade() + ", estado de "
                + end.getUf() + ", à rua " + end.getRua()
                + " nº " + end.getNumero() + ", bairro " + end.getBairro()
                + ", Complemento: " + end.getComplemento() + ", cep " + end.getCep() + ".\n\n"
                //Contato do cliente
                + "\tContato do cliente:\n"
                + "\tTipo do contato: " + contatoCli.getTipoTelefone() + "\n"
                + "\tTelefone: " + contatoCli.getNumero() + "\n"
                + "\tE-mail: " + contatoCli.getEmail() + ".\n\n"
                //Dados do veículo locado
                + "\t2. Do Objeto do Contrato\n\n"
                + "\t2.1 Veiculo de placa " + locacao.getVeiculo().getPlaca().toUpperCase() + ", ano de fabricação " + locacao.getVeiculo().getAnoFabricacao()
                + ", ano modelo " + locacao.getVeiculo().getAnoModelo() + ", tipo de combustível " + locacao.getVeiculo().getTipoCombustivel()
                + ", número do renavam " + locacao.getVeiculo().getRenavan() + ", tipo do veículo " + locacao.getVeiculo().getTipo()
                + ", categoria " + locacao.getVeiculo().getCategoria().getDescricao() + ", modelo " + locacao.getVeiculo().getModelo().getDescricao().toUpperCase()
                + ", marca " + locacao.getVeiculo().getModelo().getMarca().getDescricao().toUpperCase() + ", km do veículo na data de retirada "
                + locacao.getVeiculo().getKm() + ",Observações: o veículo foi entregue ao cliente/motorista com o tanque cheio ? " + locacao.isTanqueCheio() + ".\n\n"
                //Dados do motorista
                + "\t3. Do motorista\n\n"
                + "\t3.1 " + locacao.getMotorista().getNome().toUpperCase() + ", portador do CPF " + locacao.getMotorista().getCpf()
                + ", RG " + locacao.getMotorista().getRg() + ", orgão emissor " + locacao.getMotorista().getOrgaoEmissor().toUpperCase()
                + ", CNH de número " + locacao.getMotorista().getNumeroCnh() + ", com data de validade " + convertDate(locacao.getMotorista().getDataValidadeCnh())
                + ", categoria " + locacao.getMotorista().getCategoriaCnh() + ".\n\n"
                //Contato do motorista
                + "\tContato do motorista:\n"
                + "\tTipo do contato: " + contatoMotorista.getTipoTelefone() + "\n"
                + "\tTelefone: " + contatoMotorista.getNumero() + "\n"
                + "\tE-mail: " + contatoMotorista.getEmail() + "\n\n"
                //Dados e valores do contrato
                + "\t4 Do valor da locação\n\n"
                + "\t4.1 O valor da locação é de R$ " + locacao.getValorLocacao() + " a diária, tarifa pública da LOCADORA,"
                + " da qual o LOCATÁRIO é concordante, a ser pago somente na devolução do veículo\n"
                + "\t4.2 O valor do caução é de R$ " + locacao.getValorCaucao() + ", a ser pago ao adquirir este contrato.\n"
                + "\t4.3 O valor do seguro é de R$ " + locacao.getValorSeguro() + ", conforme regido no contrato, a ser pago junto com a locação.\n"
                + "\t4.4 O valor de acessórios é de R$ " + locacao.getValorTotalAcessorios() + ", conforme solicitado pelo locatário, a ser pago junto com a locação.\n"
                + "\t4.5 O valor da multa é de R$ " + locacao.getValorMulta() + ", conforme ocorrência de multa, a ser paga conforme regido "
                + "neste contrato. Lembra-se que o valor da multa é de inteira responsabilidade do locatário/cliente e não do "
                + "motorista, porém os pontos na CNH, será na CNH do motorista.\n\n"
                + "\t5 Das observações\n\n"
                + "\tObservações: " + locacao.getObservacoes() + ".\n\n"
                + "\t6 O total a pagar pelo LOCATÁRIO corresponde aos serviços prestados pela LOCADORA, apurados nos termos"
                + " e condições ajustadas previamente.\n\n"
                + "\t6.1 Multas de trânsito, re-embolsos por danos causados ao veículo alugado, indenizações pôr danos "
                + "causados a terceiros e/ou seus bens, diferenças de cálculos, se pôr ventura ocorrerem, serão cobrados"
                + " posteriormente ao fechamento do Contrato de Aluguel, pôr impossibilidade de apuração imediata dos "
                + "seus valores.\n\n"
                + "\t7 Datas Do Prazo de Rescisão do Contrato\n\n"
                + "\t7.1 A data de retirada do veículo deste contrato é " + convertDate(locacao.getDataRetirada()) + "\n"
                + "\t7.2 O prazo do vencimento do aluguel é na data de " + convertDate(locacao.getDataDevolucaoPrevista()) + " devendo o LOCATÁRIO devolver o "
                + "veículo no dia, hora e local contratado. A prorrogação do vencimento dependerá de prévia autorização "
                + "escrita da LOCADORA.\n\n"
                + "\tDas Formas de Pagamento\n\n"
                + "\tO locatário reconhece o valor apurado neste instrumento como dívida líquida, certa e exigível, legitimando"
                + "a cobrança. o valor poderá ser pago em especie conforme moeda corrente deste país ou através de cartão de crédito.\n\n"
                + "\t\t\t\tGoiânia - GO, " + convertDate(new Date()) + "\n\n"
                + "\n"
                + "\n"
                + "		LOCADOR						LOCATÁRIO\n"
                + "\n"
                + "TESTEMUNHAS:\n"
                + "\n"
                + "\n"
                + "______________________________		_______________________________\n\n";

        return contrato;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaSaidaDoContrato = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("CONTRATO DE LOCAÇÃO DE VEÍCULOS - LOCADORA MULACAR");

        jTextAreaSaidaDoContrato.setEditable(false);
        jTextAreaSaidaDoContrato.setColumns(20);
        jTextAreaSaidaDoContrato.setRows(5);
        jScrollPane1.setViewportView(jTextAreaSaidaDoContrato);

        jLabel1.setFont(new java.awt.Font("Monotype Corsiva", 1, 18)); // NOI18N
        jLabel1.setText("Contrato de Locacão - LOCADORA MULACAR");

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/mulacar/imagens/impressora.png"))); // NOI18N
        jButton1.setText("Imprimir");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 952, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(296, 296, 296))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addContainerGap())))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 477, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap(11, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        try {
            this.dispose();
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, erro.getMessage());
        }
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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ContratoDeLocacaoApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ContratoDeLocacaoApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ContratoDeLocacaoApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ContratoDeLocacaoApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ContratoDeLocacaoApp dialog = new ContratoDeLocacaoApp(new javax.swing.JFrame(), true);
                    dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                        @Override
                        public void windowClosing(java.awt.event.WindowEvent e) {
                            System.exit(0);
                        }
                    });
                    dialog.setVisible(true);
                } catch (Exception ex) {
                    Logger.getLogger(ContratoDeLocacaoApp.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextAreaSaidaDoContrato;
    // End of variables declaration//GEN-END:variables
}
