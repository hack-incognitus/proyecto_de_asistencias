
package COMPONENTES;


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Oscar
 */


public class Caja_Texto_Editada extends JTextField implements FocusListener{
    
    //Borde de las caja de texto
    private Color borderColor = new Color(162,183,188);
    public Caja_Texto_Editada(){
        super();
        //Caja de texto Vacia al iniciar
        Caja_Texto_Editada.this.setText("");
        //Color de la letras
        Caja_Texto_Editada.this.setForeground(new Color(162,183,188));
        Caja_Texto_Editada.this.setPreferredSize(new Dimension(200,36));
        //Poner Visible
        Caja_Texto_Editada.this.setVisible(true);
        //Fuente de tipo de letra y tamaño
        Caja_Texto_Editada.this.setFont(new Font("Agency FB", Font.PLAIN, 18 ));        
        Caja_Texto_Editada.this.setBorder(new EmptyBorder(0, 12, 0, 12));
        //esto sirve para el click cambie de color 
        Caja_Texto_Editada.this.setCaretColor(new Color(248,110,1));
        Caja_Texto_Editada.this.setSelectionColor(new Color(248,110,1));
        Caja_Texto_Editada.this.setSelectedTextColor( new Color(162,183,188) );
        //Tamaño del cursor dentro de la caja de texto
        Caja_Texto_Editada.this.putClientProperty("caretWidth", 4);
        //Opacidad de la caja de texto
        Caja_Texto_Editada.this.setOpaque(false);
        //Esto sirve para cuando carga el JFrame este el click en la caja de texto
        Caja_Texto_Editada.this.addFocusListener(Caja_Texto_Editada.this);
    }
    
    //Metodo para las dimensiones de la caja de texto
    @Override
    public void paintComponent(Graphics g){
        
        Graphics2D g2 =(Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);            
        
        Polygon fig = new Polygon();
        fig.addPoint( 0,0 );
        fig.addPoint( getWidth()-3,0 );
        fig.addPoint( getWidth()-3,getHeight()-18 );
        fig.addPoint( getWidth()-18,getHeight()-3 );
        fig.addPoint( 0,getHeight()-3 );
        
        g2.setColor( new Color(8,43,49,200) ); 
        g2.fill( fig );
        
        g2.setColor( borderColor );        
        g2.setStroke(new BasicStroke( 3 ));
        g2.draw( fig );
        
        super.paintComponent(g);
    }
    // Esto sirve para en global la propiedades de los bordes
    
    @Override
    public void focusGained(FocusEvent e) {
        borderColor = new Color(248,110,1);
        repaint();
    }
    
    //Esto es para que caundo le das un click a al casilla de abajo cambie de color y no por la que pones por defecto a los borde
  
    @Override
    public void focusLost(FocusEvent e) {
        borderColor = new Color(162,183,188);
        repaint();
    }
    
    
}
