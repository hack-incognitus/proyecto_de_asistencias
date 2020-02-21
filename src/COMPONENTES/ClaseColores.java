/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package COMPONENTES;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Rectangle;
import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.plaf.ComboBoxUI;
import javax.swing.plaf.basic.BasicComboBoxUI;
import java.awt.Image;

/**
 *
 * @author Elber
 */
///////////////////////////////////////////////////////////////////////////////////////////// esta es la clase ClaseColores

public class ClaseColores extends BasicComboBoxUI {
   
   public static int num;
   public static int multiplicador;   
   public static Color co1formularios=new java.awt.Color(245,255,255);   //formularios
   public static Color co2fondobotones=new java.awt.Color(102,176,202);   //fondo de botones y estado normal
   public static Color co3hover=new java.awt.Color(50,105,125);   //hover   
   public static Color co4cip=new java.awt.Color(255, 0, 102);   //clip botone
   
   public static Color co5texto= new java.awt.Color(50, 105, 125);   //texto
   public static Color co6panel= new java.awt.Color(255, 255, 255);   //paneles
   public static Color co7fondo= new java.awt.Color(206, 206, 206);   //otras   
   public static Color colorblanco= new java.awt.Color(255, 255, 255);   //otras   
   public static Color hovercombo= new java.awt.Color(190, 205, 210);   //combo fondo uscuro
   public static Color co8panelfondo= new java.awt.Color(41,70,102);   //combo fondo uscuro   
   public static Color co9CelesteClaro= new Color(51,204,255);   //panel encabezado coolo llamtivo celeste
   public static Color colorceleste= new Color(102, 176, 255);   //panel encabezado coolo llamtivo celeste
   public static Color colorrojo= new Color(225, 49, 80);   //panel encabezado coolo llamtivo celeste   
   public static Color colorbotonoscuro= new Color(46,78,114);   //botones oscuros   
   public static Color coloramarillo= new Color(255,200,0);   //clor amarillo   
   public static Color colorrosa= new Color(255,0,153);   //clor rosa

   


   

//   new Color(51,204,255)
   
        
//   a.awt.Color[r=0,g=255,b=153   [255,99,71]


   
//    public ClaseColores() {
//        initComponents();
//    }
   
  

//    ClaseColores() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
   
//    public  void setNum(int a){
//    num = a;
//   
//    }
//   
//    public int getNum(){
//    return num;   // a qui deseo que se multipique al hacer clic en el jbutton
//       
//    }
   
   //color decombo
   
   
   
//}
////color y propiedades del combo
//public class ClaseColores extends BasicComboBoxUI{
    
    
      
   

    
    public static ComboBoxUI createUI(JComponent com){
        return new ClaseColores();
    }

    @Override
    protected JButton createArrowButton() {
    
        JButton btn = new JButton();
        
        btn.setIcon(new ImageIcon(getClass().getResource("/Image/icons8_Expand_Arrow_32px.png")));
//        btn.setBackground(co5texto);
        btn.setForeground(co3hover);
        btn.setBorder(BorderFactory.createLineBorder(colorblanco,1));
        btn.setContentAreaFilled(false);
        return btn;
    }

    @Override
    public void paintCurrentValueBackground(Graphics g, Rectangle bounds, boolean hasFocus) {
        g.setColor(colorblanco);//
        g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
    }

    
    
    @Override
    protected ListCellRenderer createRenderer() {
        
        return new DefaultListCellRenderer(){
            

            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus); //To change body of generated methods, choose Tools | Templates.
                
              
                list.setSelectionBackground(hovercombo);//hover rojo 
                list.setForeground(co3hover);//letra oscuro 
                
//                
//                if(index!=-1){
//                    
//                    setIcon(new ImageIcon(getClass().getResource("/img/flecha.png")));
//                }
//                
                
                return this;
            }

        };
    }

    public static class co1formularios {

        public co1formularios() {
        }
    }
    
    
    

}
