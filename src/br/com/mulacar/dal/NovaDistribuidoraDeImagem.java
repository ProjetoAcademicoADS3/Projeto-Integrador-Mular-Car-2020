/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mulacar.dal;

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author roger
 */
public class NovaDistribuidoraDeImagem {
    
    public ImageIcon exibirImagemAdaptada (String path)throws Exception{
        
        ImageIcon imageIcon = new ImageIcon(path);
        Image image = imageIcon.getImage();
        Image newing = image.getScaledInstance(300, 250, java.awt.Image.SCALE_SMOOTH);
        
        ImageIcon icon = new ImageIcon(newing);
        
        return icon;
        
        
        
        
    }
    
}
