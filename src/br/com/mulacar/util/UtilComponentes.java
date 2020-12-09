package br.com.mulacar.util;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.text.JTextComponent;

/**
 *
 * @author Gabriel Cunha <gabrielcunhadev@gmail.com>
 */
public class UtilComponentes {
    
    
    /**
     * Limpar campos de JTextComponent
     * @param campos - campos para setar string vazia
    */
    public static void limparCampos(JTextComponent... campos) {
        String vazio = "";
        for (JTextComponent c : campos) {
            c.setText(vazio);
        }
    }
    
    /**
     * Limpar campos de JTextComponent
     * @param campos - campos para setar string vazia
    */
    public static void limparCampos(JLabel... campos) {
        String vazio = "";
        for (JLabel c : campos) {
            c.setText(vazio);
        }
    }    

    /**
     * Maximizar JInternalFrame para preencher o tamanho do frame pai.
     * @param frame - frame a ser maximizado.
    */   
    public static void maximizarJInternalFrame(JInternalFrame frame) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        
        frame.setPreferredSize(new Dimension(screenSize.width, 
                screenSize.height));  
    }

    /**
     * Remove Barra de titulo e borda do frame
     * @param frame - tela a ser removido a barra e borda
     * @throws Exception 
    */      
    public static void removerBarraTituloEBorda(JInternalFrame frame) throws Exception{
        BasicInternalFrameUI bif = (BasicInternalFrameUI) frame.getUI();
        bif.setNorthPane(null);
        frame.setBorder(null);       
    }
    
    /**
     * Dsabilita ou Habilita componentes JButton passados por parâmetro.
     * @param valor - valor booleano true habilita, false desabilita.
     * @param botoes - botões para habilitar ou desabilitar
    */     
    public static void habilitarComponentes(boolean valor, JComponent ...componentes) {
        for (JComponent b : componentes) {
            b.setEnabled(valor);
        }
    }
    
    public static void adicionarKeyListenerlimitaQuantidadeCaracteresJTextField(JTextField campo, int qunatidadeCaracteres) {
        campo.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (!UtilObjetos.ehNuloOuVazio(campo)) {
                    String texto = campo.getText();

                    if (texto.length() > qunatidadeCaracteres) {
                        campo.setText(texto.substring(0, qunatidadeCaracteres));
                    }
                }
            }
            
        });
    }    
    
    public static void adicionarKeyListenerSomenteLetras(JTextField... campos) {
        for (JTextField c : campos) {
            c.addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {
                        String string = c.getText();

                        string = UtilString.removerCaracteresNaoAlfaNumericos(string);

                        string = UtilString.removerAcentos(string);

                        string = UtilString.removerNumeros(string);

                        c.setText(string.toUpperCase());
                }
            });
        }
    }

    public static void adicionarKeyListenerSomenteNumeros(JTextField... campos) {
        for (JTextField c : campos) {
            c.addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {
                    String string = c.getText();

                    string = UtilString.removerCaracteresNaoAlfaNumericos(string);

                    string = UtilString.removerAcentos(string);

                    string = UtilString.removerLetras(string);

                    c.setText(string.toUpperCase());
                }
            });
        }
    } 

    public static void setFocusTextField(JTextField field, JFrame frame) {
//        frame.addWindowListener( new WindowAdapter() {
//            public void windowOpened( WindowEvent e ){
//                field.requestFocus();
//            }
//        });         
    }
}
