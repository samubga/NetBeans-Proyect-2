/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package aplicacioncomponentes;

import static aplicacioncomponentes.VentanaPrincipal.con;
import comp.DatoIntroducidoListener;
import comp.EventoIntroducirDato;
import comp4.EvtElementoSeleccionadoJList;
import comp4.InElementoSelccionadoListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author samuel
 */
public class PanelAnadirProveedor extends javax.swing.JPanel {

    /**
     * Creates new form PanelAnadirProveedor
     */
    
    private VentanaPrincipal principal;
    private boolean proveedorIntroducido = false;
    private boolean contactoIntroducido = false;
    private boolean emailIntroducido = false;
    private ArrayList<String> listaCamposVacios = new ArrayList<>();
    private String camposVacios;
    
    
    public PanelAnadirProveedor(VentanaPrincipal principal) {
        initComponents();
        this.principal = principal;
        botonAddProveedor.setEnabled(false);
        updateVisorErrores();
        listaCamposVacios.add("Proveedor: ");
        listaCamposVacios.add("Contacto: ");
        listaCamposVacios.add("Email: ");
        camposVacios = String.join(", ", listaCamposVacios);
        visorErrores.setText("Los siguientes campos obligatorios están vacíos: " + camposVacios);
    
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
    
    private void updateVisorErrores() {
        camposVacios = String.join(", ", listaCamposVacios);
        if (camposVacios.isEmpty()) {
            visorErrores.setText("");
        } else {
            visorErrores.setText("Los siguientes campos obligatorios están vacíos: " + camposVacios);
        }

        if (proveedorIntroducido && contactoIntroducido && emailIntroducido) {
            botonAddProveedor.setEnabled(true);
        }
        else{
            botonAddProveedor.setEnabled(false);
        }
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
        
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelFormulario = new javax.swing.JPanel();
        comp2Proveedor = new comp.PanelComp2();
        comp2Contacto = new comp.PanelComp2();
        comp2Telefono = new comp.PanelComp2();
        comp2Email = new comp.PanelComp2();
        comp3Pais = new comp3_.Comp3();
        visorErrores = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        comp4Provincia = new comp4.Comp4();
        comp4Municipio = new comp4.Comp4();
        panelBotones = new javax.swing.JPanel();
        botonAddProveedor = new javax.swing.JButton();
        botonReiniciar = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(1080, 400));

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

        botonAddProveedor.setText("Añadir proveedor");
        botonAddProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAddProveedorActionPerformed(evt);
            }
        });

        botonReiniciar.setText("Reiniciar");
        botonReiniciar.setToolTipText("");
        botonReiniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonReiniciarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelBotonesLayout = new javax.swing.GroupLayout(panelBotones);
        panelBotones.setLayout(panelBotonesLayout);
        panelBotonesLayout.setHorizontalGroup(
            panelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBotonesLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(botonAddProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49)
                .addComponent(botonReiniciar, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelBotonesLayout.setVerticalGroup(
            panelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBotonesLayout.createSequentialGroup()
                .addGroup(panelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonAddProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonReiniciar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(panelBotones, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(panelFormulario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panelFormulario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelBotones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void botonAddProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAddProveedorActionPerformed
        try {
            String nombreProveedor = comp2Proveedor.getDatoIntroducido();
            String nombreContacto = comp2Contacto.getDatoIntroducido();
            String telefono = comp2Telefono.getDatoIntroducido();
            String email = comp2Email.getDatoIntroducido();
            int codigoPais = comp3Pais.codigoElementoSeleccionado();
            int codigoMunicipio = comp4Municipio.codigoElementoSeleccionado();
            
            String queryAgregarProveedor = "INSERT INTO PROVEEDORES (NOMBRE, NOMCONTACTO, TELEFONO, EMAILCONTACTO, CODIGOPAIS, CODIGOMUNICIPIO) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = con.prepareStatement(queryAgregarProveedor);
            statement.setString(1, nombreProveedor);
            statement.setString(2, nombreContacto);
            statement.setString(3, telefono);
            statement.setString(4, email);
            statement.setInt(5, codigoPais);
            statement.setInt(6, codigoMunicipio);
            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Proveedor " + nombreProveedor + " añadido correctamente", "Información", JOptionPane.INFORMATION_MESSAGE);
            principal.cargarTablaAnadir();
        } catch (SQLException ex) {
            Logger.getLogger(PanelAnadirProveedor.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }//GEN-LAST:event_botonAddProveedorActionPerformed

    private void botonReiniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonReiniciarActionPerformed
        comp2Proveedor.setDatoIntroducido("");
        comp2Contacto.setDatoIntroducido("");
        comp2Telefono.setDatoIntroducido("");
        comp2Email.setDatoIntroducido("");
        comp4Provincia.reiniciarSeleccionLista();
        comp3Pais.reiniciarSeleccionLista();
        updateVisorErrores();
        listaCamposVacios.add("Proveedor: ");
        listaCamposVacios.add("Contacto: ");
        listaCamposVacios.add("Email: ");
        camposVacios = String.join(", ", listaCamposVacios);
        visorErrores.setText("Los siguientes campos obligatorios están vacíos: " + camposVacios);
    }//GEN-LAST:event_botonReiniciarActionPerformed

    
        
    
    
   
    
    
    
    public boolean esNumero(String texto) {
        try {
            Integer.parseInt(texto);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonAddProveedor;
    private javax.swing.JButton botonReiniciar;
    private comp.PanelComp2 comp2Contacto;
    private comp.PanelComp2 comp2Email;
    private comp.PanelComp2 comp2Proveedor;
    private comp.PanelComp2 comp2Telefono;
    private comp3_.Comp3 comp3Pais;
    private comp4.Comp4 comp4Municipio;
    private comp4.Comp4 comp4Provincia;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel panelBotones;
    private javax.swing.JPanel panelFormulario;
    private javax.swing.JLabel visorErrores;
    // End of variables declaration//GEN-END:variables
}
