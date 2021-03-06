/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VISTA;

import COMPONENTES.Mensaje_Dialogo1;
import COMPONENTES.Mensaje_Dialogo2;
import CONTROLADOR_3.c_socio;
import CONTROLADOR_3.c_terreno;
import CONTROLADOR_3.c_usuario;
import static VISTA.frm_padron.id_padron;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import rojerusan.RSAnimation;

/**
 *
 * @author lab-1 pc15
 */
public class frm_socio extends javax.swing.JFrame {

    int x, y;
    private ActionListener la;
    int transicion = 1;

    DefaultTableModel modelo;
    c_socio ocSocio = new c_socio();

    /**
     * Creates new form frm_oficial
     */
    public frm_socio() {
        initComponents();

        //decoraciones
        //46,78,114
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.white);
        listarSocio("");
        
        recuperar_padron();
        

    }
    
   // public static String id_padron="";
    public static String id_socio_e="";
void recuperar_padron(){
        try {
            frm_padron frm = new frm_padron();
            txt_id_padron.setText(frm.id_padron);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error al recueprar terreno");
        }
    }
    void limpiar() {
        txt_apellido_M.setText("");
        txt_apellido_P.setText("");
        txt_dni.setText("");
        txt_nombre.setText("");
        txt_id_padron.setText("");
        txt_id_socio.setText("");
        txt_telefono.setText("");
       label_foto.setText("");
       txtdate.setDatoFecha(null);
    }

    void listarSocio(String dato) {
        try {
            modelo = ocSocio.ListarSocio(dato);
            tabla.setModel(modelo);
        } catch (Exception e) {

            Mensaje_Dialogo2 myDialogo = new Mensaje_Dialogo2(this, true, "error al listar socio" + e);
        }
    }
    
    String rutaIma="";
    String RutaImagen="D:\\laptop  lenovo\\ISC\\2019-II\\Herramientas IV\\ pequeñaw.png"; 
    
public  String subirImagen (){
    String ruta="";
    final JFileChooser elejirImagen=new JFileChooser ();
    elejirImagen.setMultiSelectionEnabled(false);
    
    int o=elejirImagen.showOpenDialog(null);
    
    if (o==JFileChooser.APPROVE_OPTION) {
        ruta=elejirImagen.getSelectedFile().getAbsolutePath();
        Image preview= Toolkit .getDefaultToolkit().getImage(ruta);
        
        if (preview !=null) {
            label_foto.setText("");
            
            ImageIcon icon=new  ImageIcon(preview.getScaledInstance(label_foto.getWidth (),
            label_foto.getHeight(),Image.SCALE_DEFAULT));
            
            label_foto.setIcon(icon);
            
        }
    }
    return ruta;
}
    void insertar() {
        
        try {
            if (txt_apellido_M.getText().isEmpty() || txt_apellido_P.getText().isEmpty()|| txt_dni.getText().isEmpty()|| txt_id_padron.getText().isEmpty()
                   || txt_nombre.getText().isEmpty()|| txt_telefono.getText().isEmpty() 
                    || cboGenero.getSelectedItem().toString().equals("Seleccione...")) {

                Mensaje_Dialogo2 myDialogo = new Mensaje_Dialogo2(this, true, "complete los campos");
            } else {
                
                String fechastring = null;
                Date fecha = txtdate.getDatoFecha();
                SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
                fechastring = f.format(fecha);
                
                
                ocSocio.insertarSocio(txt_id_padron.getText(),txt_apellido_P.getText(),txt_apellido_M.getText(), txt_nombre.getText(),
                        txt_dni.getText(), txt_telefono.getText(),cboGenero.getSelectedItem().toString(),fechastring,rutaIma);
            }
        } catch (Exception e) {
        }
    }

    void recuperar() {
        int select = tabla.getSelectedRow();
        txt_id_socio.setText(modelo.getValueAt(select, 0).toString());
        txt_id_padron.setText(modelo.getValueAt(select, 1).toString());
        txt_apellido_P.setText(modelo.getValueAt(select, 2).toString());
        txt_apellido_M.setText(modelo.getValueAt(select, 3).toString());
        txt_nombre.setText(modelo.getValueAt(select, 4).toString());
        txt_dni.setText(modelo.getValueAt(select, 5).toString());
        txt_telefono.setText(modelo.getValueAt(select, 6).toString());
        cboGenero.setSelectedItem(modelo.getValueAt(select, 7).toString());
        
        try {
            Date fec = new SimpleDateFormat("yyyy-MM-dd").parse((String)modelo.getValueAt(select, 8));
            txtdate.setDatoFecha(fec);
        } catch (Exception e) {
        }

    }

    void modificar() {
        try {
            if (txt_apellido_M.getText().isEmpty() || txt_apellido_P.getText().isEmpty()|| txt_dni.getText().isEmpty()|| txt_id_padron.getText().isEmpty()
                    || txt_id_socio.getText().isEmpty()|| txt_nombre.getText().isEmpty()|| txt_telefono.getText().isEmpty() 
                    || cboGenero.getSelectedItem().toString().equals("Seleccione...")) {

                Mensaje_Dialogo2 myDialogo = new Mensaje_Dialogo2(this, true, "complete los campos");
            } else {
                String fechastring = null;
                Date fecha = txtdate.getDatoFecha();
                SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
                fechastring = f.format(fecha);
                
                
                ocSocio.modificarSocio(txt_id_socio.getText(),txt_id_padron.getText(),txt_apellido_P.getText(),txt_apellido_M.getText(), txt_nombre.getText(),
                        txt_dni.getText(), txt_telefono.getText(),cboGenero.getSelectedItem().toString(),fechastring,rutaIma);

            }

        } catch (Exception e) {

            Mensaje_Dialogo2 myDialogo = new Mensaje_Dialogo2(this, true, "ocurrio un error al modificar ");
        }
    }

    void eliminar() {
        try {

            int sselect = tabla.getSelectedRow();
            if (sselect >= 0) {
                ocSocio.eliminarSocio(modelo.getValueAt(sselect, 0).toString());
            } else {

                Mensaje_Dialogo2 myDialogo = new Mensaje_Dialogo2(this, true, "selecione para eliminar");
            }

        } catch (Exception e) {
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

        panerderecho = new javax.swing.JPanel();
        btn_desplegar = new javax.swing.JLabel();
        btn3 = new LIB.FSButtonMD();
        btn4 = new LIB.FSButtonMD();
        btn5 = new LIB.FSButtonMD();
        btn6 = new LIB.FSButtonMD();
        btn7 = new LIB.FSButtonMD();
        btn9 = new LIB.FSButtonMD();
        btn10 = new LIB.FSButtonMD();
        btn11 = new LIB.FSButtonMD();
        lcliente6 = new javax.swing.JLabel();
        panel_cabecera = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txt_nombre = new rojeru_san.RSMTextFull();
        btn_usuario2 = new rojerusan.RSMaterialButtonRound();
        btn_usuario3 = new rojerusan.RSMaterialButtonRound();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new rojerusan.RSTableMetro();
        txt_id_socio = new rojeru_san.RSMTextFull();
        txt_id_padron = new rojeru_san.RSMTextFull();
        txt_apellido_P = new rojeru_san.RSMTextFull();
        txt_apellido_M = new rojeru_san.RSMTextFull();
        txt_dni = new rojeru_san.RSMTextFull();
        txt_telefono = new rojeru_san.RSMTextFull();
        txtdate = new rojeru_san.componentes.RSDateChooser();
        cboGenero = new javax.swing.JComboBox();
        label_foto = new javax.swing.JLabel();
        lcodigo1 = new javax.swing.JLabel();
        btn_usuario4 = new rojerusan.RSMaterialButtonRound();
        btn8 = new LIB.FSButtonMD();
        btn_usuario5 = new rojerusan.RSMaterialButtonRound();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                formMouseDragged(evt);
            }
        });
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panerderecho.setBackground(new java.awt.Color(46, 78, 114));
        panerderecho.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                panerderechoMouseDragged(evt);
            }
        });
        panerderecho.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                panerderechoMousePressed(evt);
            }
        });
        panerderecho.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btn_desplegar.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        btn_desplegar.setForeground(new java.awt.Color(255, 255, 255));
        btn_desplegar.setText("≡");
        btn_desplegar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_desplegar.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                btn_desplegarMouseDragged(evt);
            }
        });
        btn_desplegar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_desplegarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_desplegarMouseEntered(evt);
            }
        });
        panerderecho.add(btn_desplegar, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 10, -1, 50));

        btn3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGENES/terrenos.png"))); // NOI18N
        btn3.setText("TERRENOS");
        btn3.setColorNormal(new java.awt.Color(46, 78, 114));
        btn3.setColorPressed(new java.awt.Color(46, 78, 114));
        btn3.setColorTextHover(new java.awt.Color(46, 78, 114));
        btn3.setColorTextNormal(new java.awt.Color(204, 204, 204));
        btn3.setFont(new java.awt.Font("Arial Black", 3, 18)); // NOI18N
        btn3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn3ActionPerformed(evt);
            }
        });
        panerderecho.add(btn3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 340, 40));

        btn4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGENES/usuarios.png"))); // NOI18N
        btn4.setText("USUARIOS");
        btn4.setColorNormal(new java.awt.Color(46, 78, 114));
        btn4.setColorPressed(new java.awt.Color(46, 78, 114));
        btn4.setColorTextHover(new java.awt.Color(46, 78, 114));
        btn4.setColorTextNormal(new java.awt.Color(204, 204, 204));
        btn4.setFont(new java.awt.Font("Arial Black", 3, 18)); // NOI18N
        btn4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn4ActionPerformed(evt);
            }
        });
        panerderecho.add(btn4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 140, 340, 40));

        btn5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGENES/socios.png"))); // NOI18N
        btn5.setText("SOCIOS");
        btn5.setColorNormal(new java.awt.Color(46, 78, 114));
        btn5.setColorPressed(new java.awt.Color(46, 78, 114));
        btn5.setColorTextHover(new java.awt.Color(46, 78, 114));
        btn5.setColorTextNormal(new java.awt.Color(204, 204, 204));
        btn5.setFont(new java.awt.Font("Arial Black", 3, 18)); // NOI18N
        btn5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn5ActionPerformed(evt);
            }
        });
        panerderecho.add(btn5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 290, 340, 40));

        btn6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGENES/socio_2.png"))); // NOI18N
        btn6.setText("PADRONES");
        btn6.setColorNormal(new java.awt.Color(46, 78, 114));
        btn6.setColorPressed(new java.awt.Color(46, 78, 114));
        btn6.setColorTextHover(new java.awt.Color(46, 78, 114));
        btn6.setColorTextNormal(new java.awt.Color(204, 204, 204));
        btn6.setFont(new java.awt.Font("Arial Black", 3, 18)); // NOI18N
        btn6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn6ActionPerformed(evt);
            }
        });
        panerderecho.add(btn6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 240, 340, 40));

        btn7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGENES/actividades.png"))); // NOI18N
        btn7.setText("ACTIVIDADES");
        btn7.setColorNormal(new java.awt.Color(46, 78, 114));
        btn7.setColorPressed(new java.awt.Color(46, 78, 114));
        btn7.setColorTextHover(new java.awt.Color(46, 78, 114));
        btn7.setColorTextNormal(new java.awt.Color(204, 204, 204));
        btn7.setFont(new java.awt.Font("Arial Black", 3, 18)); // NOI18N
        btn7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn7ActionPerformed(evt);
            }
        });
        panerderecho.add(btn7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 440, 340, 40));

        btn9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGENES/pagos (2).png"))); // NOI18N
        btn9.setText("PAGOS");
        btn9.setColorNormal(new java.awt.Color(46, 78, 114));
        btn9.setColorPressed(new java.awt.Color(46, 78, 114));
        btn9.setColorTextHover(new java.awt.Color(46, 78, 114));
        btn9.setColorTextNormal(new java.awt.Color(204, 204, 204));
        btn9.setFont(new java.awt.Font("Arial Black", 3, 18)); // NOI18N
        btn9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn9ActionPerformed(evt);
            }
        });
        panerderecho.add(btn9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 390, 340, 40));

        btn10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGENES/sanciones.png"))); // NOI18N
        btn10.setText("SANCIONES");
        btn10.setColorNormal(new java.awt.Color(46, 78, 114));
        btn10.setColorPressed(new java.awt.Color(46, 78, 114));
        btn10.setColorTextHover(new java.awt.Color(46, 78, 114));
        btn10.setColorTextNormal(new java.awt.Color(204, 204, 204));
        btn10.setFont(new java.awt.Font("Arial Black", 3, 18)); // NOI18N
        btn10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn10ActionPerformed(evt);
            }
        });
        panerderecho.add(btn10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 340, 340, 40));

        btn11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGENES/asistencias.png"))); // NOI18N
        btn11.setText("ASISTENCIAS");
        btn11.setColorNormal(new java.awt.Color(46, 78, 114));
        btn11.setColorPressed(new java.awt.Color(46, 78, 114));
        btn11.setColorTextHover(new java.awt.Color(46, 78, 114));
        btn11.setColorTextNormal(new java.awt.Color(204, 204, 204));
        btn11.setFont(new java.awt.Font("Arial Black", 3, 18)); // NOI18N
        btn11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn11ActionPerformed(evt);
            }
        });
        panerderecho.add(btn11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 490, 340, 40));

        lcliente6.setBackground(new java.awt.Color(255, 255, 255));
        lcliente6.setFont(new java.awt.Font("Poor Richard", 1, 28)); // NOI18N
        lcliente6.setForeground(new java.awt.Color(255, 255, 255));
        lcliente6.setText("APV. LOS ANDENES");
        panerderecho.add(lcliente6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 280, 40));

        getContentPane().add(panerderecho, new org.netbeans.lib.awtextra.AbsoluteConstraints(-290, 0, 340, 600));

        panel_cabecera.setBackground(new java.awt.Color(46, 78, 114));
        panel_cabecera.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                panel_cabeceraMousePressed(evt);
            }
        });

        jLabel16.setBackground(new java.awt.Color(255, 99, 71));
        jLabel16.setFont(new java.awt.Font("Ebrima", 0, 18)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_Expand_Arrow_32px.png"))); // NOI18N
        jLabel16.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel16.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jLabel16MouseMoved(evt);
            }
        });
        jLabel16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel16MouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel16MouseExited(evt);
            }
        });

        jLabel22.setBackground(new java.awt.Color(255, 99, 71));
        jLabel22.setFont(new java.awt.Font("Engravers MT", 1, 24)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(0, 204, 204));
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_Multiply_32px.png"))); // NOI18N
        jLabel22.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel22.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jLabel22MouseMoved(evt);
            }
        });
        jLabel22.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel22MouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel22MouseExited(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(46, 78, 114));
        jLabel1.setFont(new java.awt.Font("Tahoma", 3, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("REGISTRO DE SOCIOS");
        jLabel1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jLabel1MouseDragged(evt);
            }
        });
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel1MouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel1MousePressed(evt);
            }
        });

        javax.swing.GroupLayout panel_cabeceraLayout = new javax.swing.GroupLayout(panel_cabecera);
        panel_cabecera.setLayout(panel_cabeceraLayout);
        panel_cabeceraLayout.setHorizontalGroup(
            panel_cabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_cabeceraLayout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 807, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel22)
                .addContainerGap())
        );
        panel_cabeceraLayout.setVerticalGroup(
            panel_cabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_cabeceraLayout.createSequentialGroup()
                .addGroup(panel_cabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_cabeceraLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(panel_cabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        getContentPane().add(panel_cabecera, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 960, 50));

        txt_nombre.setFont(new java.awt.Font("Candara Light", 1, 14)); // NOI18N
        txt_nombre.setPlaceholder("nombre");
        txt_nombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_nombreKeyPressed(evt);
            }
        });
        getContentPane().add(txt_nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 120, 270, -1));

        btn_usuario2.setBackground(new java.awt.Color(0, 204, 255));
        btn_usuario2.setText("Registar");
        btn_usuario2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_usuario2ActionPerformed(evt);
            }
        });
        getContentPane().add(btn_usuario2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 310, 190, 50));

        btn_usuario3.setBackground(new java.awt.Color(0, 204, 255));
        btn_usuario3.setText("modificar");
        btn_usuario3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_usuario3ActionPerformed(evt);
            }
        });
        getContentPane().add(btn_usuario3, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 310, 190, 50));

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 5 ", "Title 5"
            }
        ));
        tabla.setAltoHead(25);
        tabla.setColorBackgoundHead(new java.awt.Color(102, 176, 202));
        tabla.setColorBordeFilas(new java.awt.Color(255, 255, 255));
        tabla.setColorBordeHead(new java.awt.Color(255, 255, 255));
        tabla.setColorFilasBackgound2(new java.awt.Color(156, 204, 221));
        tabla.setColorFilasForeground1(new java.awt.Color(102, 176, 202));
        tabla.setColorFilasForeground2(new java.awt.Color(255, 255, 255));
        tabla.setColorSelBackgound(new java.awt.Color(50, 105, 125));
        tabla.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tabla.setFuenteHead(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        tabla.setGridColor(new java.awt.Color(255, 255, 255));
        tabla.setGrosorBordeFilas(0);
        tabla.setMultipleSeleccion(false);
        tabla.setSelectionBackground(new java.awt.Color(255, 0, 0));
        tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tablaMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tabla);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 370, 840, 210));

        txt_id_socio.setEditable(false);
        txt_id_socio.setFont(new java.awt.Font("Candara Light", 1, 14)); // NOI18N
        txt_id_socio.setPlaceholder("id socio");
        txt_id_socio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_id_socioKeyPressed(evt);
            }
        });
        getContentPane().add(txt_id_socio, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 70, 110, -1));

        txt_id_padron.setEditable(false);
        txt_id_padron.setFont(new java.awt.Font("Candara Light", 1, 14)); // NOI18N
        txt_id_padron.setPlaceholder("id padron");
        txt_id_padron.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_id_padronKeyPressed(evt);
            }
        });
        getContentPane().add(txt_id_padron, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 70, 100, -1));

        txt_apellido_P.setFont(new java.awt.Font("Candara Light", 1, 14)); // NOI18N
        txt_apellido_P.setPlaceholder("apellido paterno");
        txt_apellido_P.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_apellido_PKeyPressed(evt);
            }
        });
        getContentPane().add(txt_apellido_P, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 70, 240, -1));

        txt_apellido_M.setFont(new java.awt.Font("Candara Light", 1, 14)); // NOI18N
        txt_apellido_M.setPlaceholder("apellido materno");
        txt_apellido_M.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_apellido_MKeyPressed(evt);
            }
        });
        getContentPane().add(txt_apellido_M, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 120, 280, -1));

        txt_dni.setFont(new java.awt.Font("Candara Light", 1, 14)); // NOI18N
        txt_dni.setPlaceholder("DNI");
        txt_dni.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_dniKeyPressed(evt);
            }
        });
        getContentPane().add(txt_dni, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 170, 280, -1));

        txt_telefono.setFont(new java.awt.Font("Candara Light", 1, 14)); // NOI18N
        txt_telefono.setPlaceholder("telefono");
        txt_telefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_telefonoKeyPressed(evt);
            }
        });
        getContentPane().add(txt_telefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 170, 270, -1));

        txtdate.setColorBackground(new java.awt.Color(255, 0, 102));
        txtdate.setColorDiaActual(new java.awt.Color(41, 70, 102));
        txtdate.setFuente(new java.awt.Font("Ebrima", 1, 14)); // NOI18N
        getContentPane().add(txtdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 240, 270, 40));

        cboGenero.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cboGenero.setForeground(new java.awt.Color(50, 105, 125));
        cboGenero.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione...", "Masculino", "femenino", "otro" }));
        cboGenero.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 176, 202), 2));
        getContentPane().add(cboGenero, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 240, 200, 40));

        label_foto.setBackground(new java.awt.Color(255, 99, 71));
        label_foto.setFont(new java.awt.Font("Ebrima", 3, 18)); // NOI18N
        label_foto.setForeground(new java.awt.Color(51, 204, 255));
        label_foto.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(0, 204, 255)));
        getContentPane().add(label_foto, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 60, 240, 230));

        lcodigo1.setBackground(new java.awt.Color(255, 99, 71));
        lcodigo1.setFont(new java.awt.Font("Ebrima", 3, 18)); // NOI18N
        lcodigo1.setForeground(new java.awt.Color(51, 204, 255));
        lcodigo1.setText("genero:");
        getContentPane().add(lcodigo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 240, 80, 40));

        btn_usuario4.setBackground(new java.awt.Color(0, 204, 255));
        btn_usuario4.setText("ENVIAR");
        btn_usuario4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_usuario4ActionPerformed(evt);
            }
        });
        getContentPane().add(btn_usuario4, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 310, 190, 50));

        btn8.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGENES/padrones.png"))); // NOI18N
        btn8.setText("IR ");
        btn8.setColorNormal(new java.awt.Color(46, 78, 114));
        btn8.setColorPressed(new java.awt.Color(46, 78, 114));
        btn8.setColorTextHover(new java.awt.Color(46, 78, 114));
        btn8.setColorTextNormal(new java.awt.Color(204, 204, 204));
        btn8.setFont(new java.awt.Font("Arial Black", 3, 18)); // NOI18N
        btn8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn8ActionPerformed(evt);
            }
        });
        getContentPane().add(btn8, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 70, 80, 40));

        btn_usuario5.setBackground(new java.awt.Color(0, 204, 255));
        btn_usuario5.setText("Eliminar");
        btn_usuario5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_usuario5ActionPerformed(evt);
            }
        });
        getContentPane().add(btn_usuario5, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 310, 190, 50));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_desplegarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_desplegarMouseClicked
        // TODO add your handling code here:
        if (transicion == 1) {

            RSAnimation.setMoverDerecha(-205, 0, 10, 8, panerderecho);
            transicion = 0;
        } else if (transicion == 0) {
            RSAnimation.setMoverIzquierda(0, -300, 10, 8, panerderecho);
            transicion = 1;
        }
    }//GEN-LAST:event_btn_desplegarMouseClicked

    private void btn_desplegarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_desplegarMouseEntered
        // TODO add your handling code here:
        //  panerderecho.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    }//GEN-LAST:event_btn_desplegarMouseEntered

    private void btn3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn3ActionPerformed

    private void btn4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn4ActionPerformed

    private void btn5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn5ActionPerformed

    private void btn6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn6ActionPerformed

    private void btn7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn7ActionPerformed

    private void btn9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn9ActionPerformed

    private void btn10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn10ActionPerformed

    private void btn11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn11ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn11ActionPerformed

    private void txt_nombreKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_nombreKeyPressed


    }//GEN-LAST:event_txt_nombreKeyPressed

    private void btn_usuario2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_usuario2ActionPerformed
        // TODO add your handling code here:
        insertar();
        listarSocio("");
        limpiar();
    }//GEN-LAST:event_btn_usuario2ActionPerformed

    private void btn_usuario3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_usuario3ActionPerformed
        // TODO add your handling code here:
        modificar();
        listarSocio("");
        limpiar();
    }//GEN-LAST:event_btn_usuario3ActionPerformed

    private void tablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMouseClicked
        // TODO add your handling code here:
        //         String coddetallepedido= TablaDetallepedido.getValueAt(TablaDetallepedido.getSelectedRow(), 1).toString();
        //         String vaorrr = String.valueOf(TablaDetallepedido.getValueAt(TablaDetallepedido.getSelectedRow(),0));
        //        String codpedido =TablaDetallepedido.getValueAt(TablaDetallepedido.getSelectedRow(), 2).toString();
        //        String codproducto=TablaDetallepedido.getValueAt(TablaDetallepedido.getSelectedRow(), 3).toString();
        //        String cantidad= TablaDetallepedido.getValueAt(TablaDetallepedido.getSelectedRow(), 4).toString();
        //        String precio=TablaDetallepedido.getValueAt(TablaDetallepedido.getSelectedRow(), 5).toString();
        //        String subtotal=TablaDetallepedido.getValueAt(TablaDetallepedido.getSelectedRow(), 6).toString();
        //        txtcodigo.setText(TablaDetallepedido.getValueAt(1,3)+"");
        //        txtpedido.setText(coddetallepedido);
        //        txtproducto.setText(coddetallepedido);
        //        txtcantidad.setText(coddetallepedido);
        //        txtprecio.setText(coddetallepedido);
        //        txtsubtotal.setText(coddetallepedido);
        //        txtsubtotal.setText("ffff");
    }//GEN-LAST:event_tablaMouseClicked

    private void tablaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMousePressed

        // TODO add your handling code here:
recuperar();
    }//GEN-LAST:event_tablaMousePressed

    private void jLabel22MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel22MouseMoved
        // TODO add your handling code here:
        jLabel22.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
    }//GEN-LAST:event_jLabel22MouseMoved

    private void jLabel22MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel22MouseClicked
        // TODO add your handling code here:
        this.dispose();
        //  Mensaje_Dialogo1 myDialogo = new Mensaje_Dialogo1(this, true, "Desea cerrar");

    }//GEN-LAST:event_jLabel22MouseClicked

    private void jLabel22MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel22MouseExited
        // TODO add your handling code here:
        jLabel22.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
    }//GEN-LAST:event_jLabel22MouseExited

    private void jLabel16MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel16MouseMoved
        // TODO add your handling code here:
        jLabel16.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
    }//GEN-LAST:event_jLabel16MouseMoved

    private void jLabel16MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel16MouseClicked
        // TODO add your handling code here:
        this.setExtendedState(ICONIFIED);
    }//GEN-LAST:event_jLabel16MouseClicked

    private void jLabel16MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel16MouseExited
        // TODO add your handling code here:
        jLabel16.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
    }//GEN-LAST:event_jLabel16MouseExited

    private void jLabel1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseEntered
        // TODO add your handling code here:


    }//GEN-LAST:event_jLabel1MouseEntered

    private void jLabel1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MousePressed
        // TODO add your handling code here:
        x = evt.getX();
        y = evt.getY();
    }//GEN-LAST:event_jLabel1MousePressed

    private void panerderechoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panerderechoMousePressed
//        // TODO add your handling code here:
        x = evt.getX();
        y = evt.getY();
    }//GEN-LAST:event_panerderechoMousePressed

    private void btn_desplegarMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_desplegarMouseDragged
        // TODO add your handling code here:
        Point p = MouseInfo.getPointerInfo().getLocation();
        this.setLocation(p.x - x, p.y - y);
    }//GEN-LAST:event_btn_desplegarMouseDragged

    private void panerderechoMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panerderechoMouseDragged
//        // TODO add your handling code here:
        Point p = MouseInfo.getPointerInfo().getLocation();
        this.setLocation(p.x - x, p.y - y);
    }//GEN-LAST:event_panerderechoMouseDragged

    private void panel_cabeceraMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel_cabeceraMousePressed
        // TODO add your handling code here:
        x = evt.getX();
        y = evt.getY();
    }//GEN-LAST:event_panel_cabeceraMousePressed

    private void jLabel1MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseDragged
        // TODO add your handling code here:
        Point p = MouseInfo.getPointerInfo().getLocation();
        this.setLocation(p.x - x, p.y - y);
    }//GEN-LAST:event_jLabel1MouseDragged

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        // TODO add your handling code here:
        x = evt.getX();
        y = evt.getY();
    }//GEN-LAST:event_formMousePressed

    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
        // TODO add your handling code here:
        Point p = MouseInfo.getPointerInfo().getLocation();
        this.setLocation(p.x - x, p.y - y);
    }//GEN-LAST:event_formMouseDragged

    private void txt_id_socioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_id_socioKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_id_socioKeyPressed

    private void txt_id_padronKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_id_padronKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_id_padronKeyPressed

    private void txt_apellido_PKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_apellido_PKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_apellido_PKeyPressed

    private void txt_apellido_MKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_apellido_MKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_apellido_MKeyPressed

    private void txt_dniKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_dniKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_dniKeyPressed

    private void txt_telefonoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_telefonoKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_telefonoKeyPressed

    private void btn_usuario4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_usuario4ActionPerformed
        // TODO add your handling code here:
       int select = tabla.getSelectedRow();
        if (select >= 0) {
            id_socio_e = modelo.getValueAt(select, 0).toString();

            frm_actividad frm_ac = new frm_actividad();
            frm_ac.setVisible(true);
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(null, "seleccione una fila");
        }
    }//GEN-LAST:event_btn_usuario4ActionPerformed

    private void btn8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn8ActionPerformed
        // TODO add your handling code here:
        frm_padron frm=new frm_padron();
        frm.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btn8ActionPerformed

    private void btn_usuario5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_usuario5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_usuario5ActionPerformed

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
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frm_socio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frm_socio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frm_socio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frm_socio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frm_socio().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private LIB.FSButtonMD btn10;
    private LIB.FSButtonMD btn11;
    private LIB.FSButtonMD btn3;
    private LIB.FSButtonMD btn4;
    private LIB.FSButtonMD btn5;
    private LIB.FSButtonMD btn6;
    private LIB.FSButtonMD btn7;
    private LIB.FSButtonMD btn8;
    private LIB.FSButtonMD btn9;
    private javax.swing.JLabel btn_desplegar;
    private rojerusan.RSMaterialButtonRound btn_usuario2;
    private rojerusan.RSMaterialButtonRound btn_usuario3;
    private rojerusan.RSMaterialButtonRound btn_usuario4;
    private rojerusan.RSMaterialButtonRound btn_usuario5;
    private javax.swing.JComboBox cboGenero;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel label_foto;
    private javax.swing.JLabel lcliente6;
    private javax.swing.JLabel lcodigo1;
    private javax.swing.JPanel panel_cabecera;
    private javax.swing.JPanel panerderecho;
    private rojerusan.RSTableMetro tabla;
    private rojeru_san.RSMTextFull txt_apellido_M;
    private rojeru_san.RSMTextFull txt_apellido_P;
    private rojeru_san.RSMTextFull txt_dni;
    private rojeru_san.RSMTextFull txt_id_padron;
    private rojeru_san.RSMTextFull txt_id_socio;
    private rojeru_san.RSMTextFull txt_nombre;
    private rojeru_san.RSMTextFull txt_telefono;
    private rojeru_san.componentes.RSDateChooser txtdate;
    // End of variables declaration//GEN-END:variables
}
