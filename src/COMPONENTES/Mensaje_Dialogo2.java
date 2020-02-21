package COMPONENTES;


import static COMPONENTES.ClaseColores.co8panelfondo;
import static COMPONENTES.ClaseColores.colorblanco;
import static COMPONENTES.ClaseColores.colorceleste;
import DAO_2.usuario_DAO;
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
import javax.swing.BorderFactory;


/**
 *
 * @author Oscar
 */
public class Mensaje_Dialogo2 extends JDialog implements ActionListener{
   
    private Boton_Personalizado okButton = null;  
    private boolean answer = false;
    private final boolean answer1 = false;
    
    
    public Mensaje_Dialogo2(JFrame frame, boolean modal, String message) {        
        super(frame, modal);
        //Creación de las dimensiones del Dialogo
        Mensaje_Dialogo2.this.setPreferredSize( new Dimension(400,60));
        Mensaje_Dialogo2.this.setUndecorated(true);
        GridBagConstraints gridBagConstraints;
        
        JPanel myPanel = new JPanel();
        myPanel.setPreferredSize(new Dimension(400,100));
        myPanel.setBorder(BorderFactory.createLineBorder(colorceleste, 2));
        myPanel.setBackground(co8panelfondo);
        myPanel.setLayout(new GridBagLayout());
        
        Mensaje_Dialogo2.this.getContentPane().add(myPanel);        
        
        JLabel lbMsg = new JLabel(message);
        lbMsg.setForeground(new Color(255,255,255));
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
        okButton.setText("OK");
        okButton.setPreferredSize(new Dimension(80,34));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        myPanel.add(okButton,gridBagConstraints);           
        //segundo Botton
        
        
        /*okButton1 = new Boton_Personalizado();
        okButton1.setText("CANCELAR");
        okButton1.setPreferredSize(new Dimension(80,34));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        myPanel.add(okButton1,gridBagConstraints); */
        
        //listener
        Mensaje_Dialogo2.this.okButton.addActionListener(Mensaje_Dialogo2.this);        
       /* Mensaje_Dialogo.this.okButton1.addActionListener(Mensaje_Dialogo.this);  */ 
        Mensaje_Dialogo2.this.pack();
        Mensaje_Dialogo2.this.setLocationRelativeTo(frame);
        Mensaje_Dialogo2.this.setVisible(true);
    }

    public Mensaje_Dialogo2(usuario_DAO aThis, boolean b, Object object, String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void actionPerformed(ActionEvent e ) {
        if(okButton == e.getSource()) {
            answer = true;
            setVisible(false);
            
        }/*else{
            if(okButton1 == e.getSource()) {
            answer1 = true;
           }
        }*/      
    }
    
   
    
    public boolean getAnswer() { return answer; }
    public boolean getAnswer1() { return answer1; }
    
    
    
}