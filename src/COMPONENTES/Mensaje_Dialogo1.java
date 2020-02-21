package COMPONENTES;


import static COMPONENTES.ClaseColores.co8panelfondo;
import static COMPONENTES.ClaseColores.co9CelesteClaro;
import com.sun.net.httpserver.Authenticator.Success;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JDialog; 
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.event.ActionEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import rojerusan.RSAnimation;


/**
 *
 * @author Oscar
 */
public class Mensaje_Dialogo1 extends JDialog implements ActionListener{
   
    private Boton_Personalizado okButton = null;  
    private boolean answer = false;
    
    private Boton_Personalizado okButton1  = null;
    private boolean answer1 = false;

    
    
    public Mensaje_Dialogo1(JFrame frame, boolean modal, String message) {        
        super(frame, modal);
        //Creación de las dimensiones del Dialogo
        //tamanio del panel modal
        Mensaje_Dialogo1.this.setPreferredSize( new Dimension(300,60));
        Mensaje_Dialogo1.this.setUndecorated(true);
        GridBagConstraints gridBagConstraints;
        
        JPanel myPanel = new JPanel();
        myPanel.setPreferredSize(new Dimension(400,100));
        myPanel.setBorder(BorderFactory.createLineBorder(co9CelesteClaro, 2));
        myPanel.setBackground(co8panelfondo);
        myPanel.setLayout(new GridBagLayout());
        
        Mensaje_Dialogo1.this.getContentPane().add(myPanel);        
        
        JLabel lbMsg = new JLabel(message);
        lbMsg.setForeground(ClaseColores.colorblanco);
        lbMsg.setOpaque(false);
        lbMsg.setFont(new java.awt.Font("Ebrima", 0, 16));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 20);
        myPanel.add(lbMsg,gridBagConstraints);
        //Creación del Boton
        okButton = new Boton_Personalizado();
        okButton.setText("SALIR");
        okButton.setPreferredSize(new Dimension(80,34));        
//      okButton.setPreferredSize(new Dimension(80,34));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        myPanel.add(okButton,gridBagConstraints);
        okButton.setBorder(javax.swing.BorderFactory.createLineBorder(co9CelesteClaro));

        //segundo Botton  
        okButton1 = new Boton_Personalizado();
        okButton1.setText("CANCELAR");
        okButton1.setPreferredSize(new Dimension(80,34));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        myPanel.add(okButton1,gridBagConstraints);
        okButton1.setBorder(javax.swing.BorderFactory.createLineBorder(co9CelesteClaro));

        
        //listener
        Mensaje_Dialogo1.this.okButton.addActionListener(Mensaje_Dialogo1.this);        
        Mensaje_Dialogo1.this.okButton1.addActionListener(Mensaje_Dialogo1.this);   
        Mensaje_Dialogo1.this.pack();
        Mensaje_Dialogo1.this.setLocationRelativeTo(frame);
        Mensaje_Dialogo1.this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e ) {
        if(okButton == e.getSource()) {
            answer = true;
//            setVisible(false);
//----------------------------------
try {
            //Nuestra ventana una vez que se abre se encuentra en la posición 200
            //por lo que ahora la posición inicial es 200 y la final la -230
            //los siguientes paramétros los dejamos en 2
            RSAnimation.setSubir(10, -500, 2, 2, this);
            //Ahora le daremos un tiempo para que la ventana se cierre
            //utilizamos un Thread,R le damos 1 segundo
            Thread.sleep(1000);
            //y posteriormente cerramos la ventana
//            this.dispose();
             System.exit( 0 );
        } catch (InterruptedException ex) {
            Logger.getLogger(Success.class.getName()).log(Level.SEVERE, null, ex);
        }
    }                                         

                                      

        
//---------------------------------
           
            
        /*else{*/
            if(okButton1 == e.getSource()) {
            answer1 = true;
           
            try {
            //Nuestra ventana una vez que se abre se encuentra en la posición 200
            //por lo que ahora la posición inicial es 200 y la final la -230
            //los siguientes paramétros los dejamos en 2
            RSAnimation.setSubir(10, -500, 2, 2, this);
            //Ahora le daremos un tiempo para que la ventana se cierre
            //utilizamos un Thread,R le damos 1 segundo
            Thread.sleep(1000);
            //y posteriormente cerramos la ventana
//            this.dispose();
//             System.exit( 0 );
        } catch (InterruptedException ex) {
            Logger.getLogger(Success.class.getName()).log(Level.SEVERE, null, ex);
        }
            
             setVisible(false); 
           }/*
        }*/      
    }
    
   
    
    public boolean getAnswer() { return answer; }
    public boolean getAnswer1() { return answer1; }
    
    
    
}