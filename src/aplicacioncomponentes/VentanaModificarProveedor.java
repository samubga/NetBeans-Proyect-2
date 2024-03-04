/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package aplicacioncomponentes;

import static aplicacioncomponentes.VentanaPrincipal.con;
import comp.DatoIntroducidoListener;
import comp.EventoIntroducirDato;
import comp4.EvtElementoSeleccionadoJList;
import comp4.InElementoSelccionadoListener;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author diurno
 */
public class VentanaModificarProveedor extends javax.swing.JDialog {

    /**
     * Creates new form VentanaModificarProveedor
     */
    
    
    private VentanaPrincipal principal;
    private boolean proveedorIntroducido = false;
    private boolean contactoIntroducido = false;
    private boolean emailIntroducido = false;
    private ArrayList<String> listaCamposVacios = new ArrayList<>();
    private String camposVacios;
    
    public VentanaModificarProveedor(java.awt.Frame parent, boolean modal, VentanaPrincipal principal) {
        super(parent, modal);
        initComponents();
        this.principal = principal;
        botonModifyProveedor.setEnabled(false);
        updateVisorErrores();
    
    DatoIntroducidoListener dil = new DatoIntroducidoListener() {
        @Override
        public void introducirDatoEvt(EventoIntroducirDato ev) {
            if (!ev.isError()) {
                proveedorIntroducido = true;
                listaCamposVacios.remove(ev.getNombreCampo());
            } else {
                proveedorIntroducido = false;
                if (!listaCamposVacios.contains(ev.getNombreCampo())) {
                    listaCamposVacios.add(ev.getNombreCampo());
                }
            }
            updateVisorErrores();
        }
    };

    DatoIntroducidoListener dil2 = new DatoIntroducidoListener() {
        @Override
        public void introducirDatoEvt(EventoIntroducirDato ev) {
            if (!ev.isError()) {
                contactoIntroducido = true;
                listaCamposVacios.remove(ev.getNombreCampo());
            } else {
                contactoIntroducido = false;
                if (!listaCamposVacios.contains(ev.getNombreCampo())) {
                    listaCamposVacios.add(ev.getNombreCampo());
                }
            }
            updateVisorErrores();
        }
    };

    DatoIntroducidoListener dil3 = new DatoIntroducidoListener() {
        @Override
        public void introducirDatoEvt(EventoIntroducirDato ev) {
            if (!ev.isError()) {
                emailIntroducido = true;
                listaCamposVacios.remove(ev.getNombreCampo());
            } else {
                emailIntroducido = false;
                if (!listaCamposVacios.contains(ev.getNombreCampo())) {
                    listaCamposVacios.add(ev.getNombreCampo());
                }
            }
            updateVisorErrores();
        }
    };

    comp2Proveedor.addDatoListener(dil);
    comp2Contacto.addDatoListener(dil2);
    comp2Email.addDatoListener(dil3);
        InElementoSelccionadoListener li = new InElementoSelccionadoListener(){
            @Override
            public void elementoSeleccionadoJlist(EvtElementoSeleccionadoJList ev) {
               cargarListaProvincia(ev.elementoSeleccionadoJList());
               
            }
        
        };
        
        comp4Provincia.addElementoSeleccionadoListener(li);
        try {
                String bbdd = "jdbc:hsqldb:hsql://localhost/";
                String usuario = "SA";
                String passw = "SA";
                Class.forName("org.hsqldb.jdbc.JDBCDriver");
                con = DriverManager.getConnection(bbdd, usuario, passw);
                String queryListaPaises = "SELECT codigo, nombre FROM PAISES";
                Statement stmt1 = con.createStatement();

                ResultSet resultSet = stmt1.executeQuery(queryListaPaises); 
                comp3Pais.cargarLista(resultSet, "codigo", "nombre");
                
                
                
                stmt1.close();
                String queryListaProvincias = "SELECT CODIGO, NOMBRE FROM PROVINCIAS";
                Statement stmt3 = con.createStatement();

                ResultSet resultSet3 = stmt3.executeQuery(queryListaProvincias); 
                comp4Provincia.cargarLista(resultSet3, "codigo", "nombre");
                stmt3.close();
                
                
             }  catch (ClassNotFoundException ex) {
                 ex.printStackTrace();
            } catch (SQLException ex) {
                  ex.printStackTrace();
            }
        
        
    }
    
    
     public VentanaModificarProveedor(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

   
    public int cargarListaProvincia(int codigo){
        
        try {
                String queryListaMunicipios = "SELECT codigo, nombre FROM MUNICIPIOS WHERE codprovincia = " + codigo;

                Statement stmt2 = con.createStatement();

                ResultSet resultSet2 = stmt2.executeQuery(queryListaMunicipios);
                comp4Municipio.cargarLista(resultSet2, "codigo", "nombre");
                stmt2.close();
            } catch (SQLException ex) {
                Logger.getLogger(PanelBuscarProveedor.class.getName()).log(Level.SEVERE, null, ex);
            }
     return codigo;
    }
    
    
    
    public void rellenarDatos(int codigoProveedor ){
        
        
        String query = "SELECT P.NOMBRE AS NombreProveedor,P.NOMCONTACTO AS NombreContacto,P.TELEFONO AS Telefono,P.EMAILCONTACTO AS Email,M.NOMBRE AS NombreMunicipio,"
                + "PA.NOMBRE AS NombrePais FROM PROVEEDORES P LEFT JOIN MUNICIPIOS M ON P.CODIGOMUNICIPIO=M.CODIGO LEFT JOIN PAISES PA ON P.CODIGOPAIS=PA.CODIGO WHERE P.CODIGO = ?";
        try{
            PreparedStatement pstmt = principal.con.prepareStatement(query);
            pstmt.setInt(1,codigoProveedor);
            
            // Ejecutar la consulta y obtener el conjunto de resultados
            ResultSet rs = pstmt.executeQuery();
            
            if(rs.next()){
                comp2Proveedor.setDatoIntroducido(rs.getString("NombreProveedor"));
                comp2Contacto.setDatoIntroducido(rs.getString("NombreContacto"));
                comp2Telefono.setDatoIntroducido(rs.getString("Telefono"));
                comp2Email.setDatoIntroducido(rs.getString("Email"));
                comp3Pais.seleccionarJList(rs.getString("NombrePais"));
                
                
                String nombreMunicipio  = rs.getString("NombreMunicipio");
                System.out.println("Municipio " + nombreMunicipio);
                String queryListaProvincias = "SELECT P.NOMBRE AS PROVINCIA FROM MUNICIPIOS M JOIN PROVINCIAS P ON M.CODPROVINCIA = P.CODIGO WHERE M.NOMBRE = '" + nombreMunicipio + "'";
                Statement stmt3 = con.createStatement();

                ResultSet resultSet3 = stmt3.executeQuery(queryListaProvincias); 
                
                
                if (resultSet3.next()) {
                    comp4Provincia.seleccionarJList(resultSet3.getString("PROVINCIA"));
                }
                
                comp4Municipio.seleccionarJList(rs.getString("NombreMunicipio"));
                stmt3.close();
                
            }
        }
        catch(SQLException e){
             e.printStackTrace();
             System.out.println("Error: " + e.getMessage());
        }
    }
    
    
   private void updateVisorErrores() {
        camposVacios = String.join(", ", listaCamposVacios);
        if (camposVacios.isEmpty()) {
            visorErrores.setText("");
        } else {
            visorErrores.setText("Los siguientes campos obligatorios están vacíos: " + camposVacios);
        }

        if (proveedorIntroducido && contactoIntroducido && emailIntroducido) {
            botonModifyProveedor.setEnabled(true);
        }
        else{
            botonModifyProveedor.setEnabled(false);
        }
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        comp4Provincia = new comp4.Comp4();
        comp4Municipio = new comp4.Comp4();
        panelBotones = new javax.swing.JPanel();
        botonModifyProveedor = new javax.swing.JButton();
        botonCancelar = new javax.swing.JButton();
        panelFormulario = new javax.swing.JPanel();
        comp2Proveedor = new comp.PanelComp2();
        comp2Contacto = new comp.PanelComp2();
        comp2Telefono = new comp.PanelComp2();
        comp2Email = new comp.PanelComp2();
        comp3Pais = new comp3_.Comp3();
        visorErrores = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jPanel1.setPreferredSize(new java.awt.Dimension(1080, 400));

        comp4Provincia.setActivarMensaje(true);
        comp4Provincia.setEtiqueta("Provincia: ");

        comp4Municipio.setActivarMensaje(true);
        comp4Municipio.setEtiqueta("Municipio: ");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(comp4Municipio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comp4Provincia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(82, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(comp4Provincia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(comp4Municipio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        botonModifyProveedor.setText("Modificar proveedor");
        botonModifyProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonModifyProveedorActionPerformed(evt);
            }
        });

        botonCancelar.setText("Cancelar");
        botonCancelar.setToolTipText("");
        botonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelBotonesLayout = new javax.swing.GroupLayout(panelBotones);
        panelBotones.setLayout(panelBotonesLayout);
        panelBotonesLayout.setHorizontalGroup(
            panelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBotonesLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(botonModifyProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49)
                .addComponent(botonCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelBotonesLayout.setVerticalGroup(
            panelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBotonesLayout.createSequentialGroup()
                .addGroup(panelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonModifyProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        comp2Proveedor.setLabel("Proveedor: ");

        comp2Contacto.setLabel("Contacto: ");

        comp2Telefono.setLabel("Teléfono: ");
        comp2Telefono.setObligatorio(false);

        comp2Email.setLabel("Email: ");

        comp3Pais.setActivarMensaje(true);
        comp3Pais.setEtiqueta("País: ");

        javax.swing.GroupLayout panelFormularioLayout = new javax.swing.GroupLayout(panelFormulario);
        panelFormulario.setLayout(panelFormularioLayout);
        panelFormularioLayout.setHorizontalGroup(
            panelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFormularioLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(panelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(visorErrores, javax.swing.GroupLayout.PREFERRED_SIZE, 539, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelFormularioLayout.createSequentialGroup()
                        .addGroup(panelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(comp2Email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comp2Telefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comp2Contacto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comp2Proveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(49, 49, 49)
                        .addComponent(comp3Pais, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        panelFormularioLayout.setVerticalGroup(
            panelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFormularioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(comp3Pais, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelFormularioLayout.createSequentialGroup()
                        .addComponent(comp2Proveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(comp2Contacto, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(comp2Telefono, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(comp2Email, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(visorErrores, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(99, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(panelBotones, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(panelFormulario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(panelFormulario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelBotones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void botonModifyProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonModifyProveedorActionPerformed
         try {
            String nombreProveedor = comp2Proveedor.getDatoIntroducido();
            String nombreContacto = comp2Contacto.getDatoIntroducido();
            String telefono = comp2Telefono.getDatoIntroducido();
            String email = comp2Email.getDatoIntroducido();
            int codigoPais = comp3Pais.codigoElementoSeleccionado();
            int codigoMunicipio = comp4Municipio.codigoElementoSeleccionado();

            String queryModificarProveedor = "UPDATE PROVEEDORES SET NOMCONTACTO = ?, TELEFONO = ?, EMAILCONTACTO = ?, CODIGOPAIS = ?, CODIGOMUNICIPIO = ? WHERE NOMBRE = ?";
            PreparedStatement statement = con.prepareStatement(queryModificarProveedor);
            statement.setString(1, nombreContacto);
            statement.setString(2, telefono);
            statement.setString(3, email);
            statement.setInt(4, codigoPais);
            statement.setInt(5, codigoMunicipio);
            statement.setString(6, nombreProveedor);
            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Proveedor " + nombreProveedor + " modificado correctamente", "Información", JOptionPane.INFORMATION_MESSAGE);
            principal.cargarTablaBuscar();
            dispose();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    

    }//GEN-LAST:event_botonModifyProveedorActionPerformed

    private void botonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCancelarActionPerformed
        dispose();
    }//GEN-LAST:event_botonCancelarActionPerformed

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
            java.util.logging.Logger.getLogger(VentanaModificarProveedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaModificarProveedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaModificarProveedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaModificarProveedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                VentanaModificarProveedor dialog = new VentanaModificarProveedor(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton botonCancelar;
    private javax.swing.JButton botonModifyProveedor;
    private comp.PanelComp2 comp2Contacto;
    private comp.PanelComp2 comp2Email;
    private comp.PanelComp2 comp2Proveedor;
    private comp.PanelComp2 comp2Telefono;
    private comp3_.Comp3 comp3Pais;
    private comp4.Comp4 comp4Municipio;
    private comp4.Comp4 comp4Provincia;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel panelBotones;
    private javax.swing.JPanel panelFormulario;
    private javax.swing.JLabel visorErrores;
    // End of variables declaration//GEN-END:variables
}
