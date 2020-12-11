package br.com.mulacar.util;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Gabriel Cunha <gabrielcunhadev@gmail.com>
 */
public class UtilTabela {
    
    
    /**
     * Limpar as linhas da tabela
     * @param tabela - tabela a ser limpada.
    */
    public static void limparTabelas(JTable... tabelas) {
        try {
            for (JTable tabela : tabelas) {
                
                DefaultTableModel tableModel = new DefaultTableModel();
                
                if (!UtilObjetos.ehNuloOuVazio(tabela.getModel())) {
                    tableModel = (DefaultTableModel) tabela.getModel();
                    tableModel.setNumRows(0);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao limpar as linhas da tabela.");
        }        
    }
  
}
