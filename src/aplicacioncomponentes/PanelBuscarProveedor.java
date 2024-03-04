/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package aplicacioncomponentes;

import static aplicacioncomponentes.VentanaPrincipal.con;
import comp4.EvtElementoSeleccionadoJList;
import comp4.InElementoSelccionadoListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.AbstractDocument;

/**
 *
 * @author samuel
 */

public class PanelBuscarProveedor extends javax.swing.JPanel {
    private VentanaPrincipal principal;
    private int codigoPais = -1;
    private int codigoMunicipio = -1;
   
    public PanelBuscarProveedor(VentanaPrincipal principal) {
        initComponents();
        this.principal = principal;
        Connection con = null;
        InElementoSelccionadoListener li = new InElementoSelccionadoListener(){
            @Override
            public void elementoSeleccionadoJlist(EvtElementoSeleccionadoJList ev) {
               cargarListaProvincia(ev.elementoSeleccionadoJList());
            }
        
        };
        
        compProvincia.addElementoSeleccionadoListener(li);
        
        
        
        
        InElementoSelccionadoListener li2 = new InElementoSelccionadoListener(){
            @Override
            public void elementoSeleccionadoJlist(EvtElementoSeleccionadoJList ev) {
               codigoPais = ev.elementoSeleccionadoJList();
               principal.buscarProveedor(codigoPais,codigoMunicipio);
            }
        
        };
        
        InElementoSelccionadoListener li3 = new InElementoSelccionadoListener(){
            @Override
            public void elementoSeleccionadoJlist(EvtElementoSeleccionadoJList ev) {
                codigoMunicipio = ev.elementoSeleccionadoJList();
               principal.buscarProveedor(codigoPais,codigoMunicipio);
            }
        
        };
        
        compMunicipio.addElementoSeleccionadoListener(li3);
        compPais.addElementoSeleccionadoListener(li2);
        
            try {
                String bbdd = "jdbc:hsqldb:hsql://localhost/";
                String usuario = "SA";
                String passw = "SA";
                Class.forName("org.hsqldb.jdbc.JDBCDriver");
                con = DriverManager.getConnection(bbdd, usuario, passw);
                String queryListaPaises = "SELECT codigo, nombre FROM PAISES";
                Statement stmt1 = con.createStatement();

                ResultSet resultSet = stmt1.executeQuery(queryListaPaises); 
                compPais.cargarLista(resultSet, "codigo", "nombre");
                
                
                
                stmt1.close();
                String queryListaProvincias = "SELECT DISTINCT PR.CODIGO, PR.NOMBRE FROM PROVINCIAS PR INNER JOIN MUNICIPIOS M ON PR.CODIGO = M.CODPROVINCIA INNER JOIN PROVEEDORES P ON M.CODIGO = P.CODIGOMUNICIPIO;";
                Statement stmt3 = con.createStatement();

                ResultSet resultSet3 = stmt3.executeQuery(queryListaProvincias); 
                compProvincia.cargarLista(resultSet3, "codigo", "nombre");
                System.out.println(compProvincia.codigoElementoSeleccionado());
                stmt3.close();
                
                
             }  catch (ClassNotFoundException ex) {
                 ex.printStackTrace();
            } catch (SQLException ex) {
                  ex.printStackTrace();
            }
    }
    
    public int cargarListaProvincia(int codigo){
     
        try {
                String queryListaMunicipios = "SELECT codigo, nombre FROM MUNICIPIOS WHERE codprovincia = " + codigo + " AND EXISTS (SELECT 1 FROM PROVEEDORES WHERE MUNICIPIOS.codigo = PROVEEDORES.CODIGOMUNICIPIO)";

                Statement stmt2 = con.createStatement();

                ResultSet resultSet2 = stmt2.executeQuery(queryListaMunicipios);
                compMunicipio.cargarLista(resultSet2, "codigo", "nombre");
                System.out.println(compMunicipio.textoElementoSeleccionado());
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

        panelFormularioBusqueda = new javax.swing.JPanel();
        compPais = new comp4.Comp4();
        compProvincia = new comp4.Comp4();
        compMunicipio = new comp4.Comp4();
        panelBotones = new javax.swing.JPanel();
        reiniciarBoton = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(1080, 400));

        panelFormularioBusqueda.setPreferredSize(new java.awt.Dimension(1080, 220));

        compPais.setActivarMensaje(true);
        compPais.setEtiqueta("Pais: ");

        compProvincia.setActivarMensaje(true);
        compProvincia.setEtiqueta("Provincia: ");

        compMunicipio.setActivarMensaje(true);
        compMunicipio.setEtiqueta("Municipios: ");

        javax.swing.GroupLayout panelFormularioBusquedaLayout = new javax.swing.GroupLayout(panelFormularioBusqueda);
        panelFormularioBusqueda.setLayout(panelFormularioBusquedaLayout);
        panelFormularioBusquedaLayout.setHorizontalGroup(
            panelFormularioBusquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFormularioBusquedaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(compPais, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(compProvincia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(compMunicipio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelFormularioBusquedaLayout.setVerticalGroup(
            panelFormularioBusquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFormularioBusquedaLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(panelFormularioBusquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(compMunicipio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(compProvincia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(compPais, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(133, Short.MAX_VALUE))
        );

        reiniciarBoton.setText("Reiniciar");
        reiniciarBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reiniciarBotonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelBotonesLayout = new javax.swing.GroupLayout(panelBotones);
        panelBotones.setLayout(panelBotonesLayout);
        panelBotonesLayout.setHorizontalGroup(
            panelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBotonesLayout.createSequentialGroup()
                .addGap(473, 473, 473)
                .addComponent(reiniciarBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(200, Short.MAX_VALUE))
        );
        panelBotonesLayout.setVerticalGroup(
            panelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBotonesLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(reiniciarBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(122, 122, 122))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelBotones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(281, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelFormularioBusqueda, javax.swing.GroupLayout.DEFAULT_SIZE, 1074, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(panelFormularioBusqueda, javax.swing.GroupLayout.DEFAULT_SIZE, 305, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelBotones, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void reiniciarBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reiniciarBotonActionPerformed
        reiniciar();
    }//GEN-LAST:event_reiniciarBotonActionPerformed

    public void reiniciar(){
        compProvincia.reiniciarSeleccionLista();
        compPais.reiniciarSeleccionLista();
        
        principal.cargarTablaBuscar();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private comp4.Comp4 compMunicipio;
    private comp4.Comp4 compPais;
    private comp4.Comp4 compProvincia;
    private javax.swing.JPanel panelBotones;
    private javax.swing.JPanel panelFormularioBusqueda;
    private javax.swing.JButton reiniciarBoton;
    // End of variables declaration//GEN-END:variables
}
