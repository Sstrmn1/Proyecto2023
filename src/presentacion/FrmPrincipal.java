package presentacion;

import clases.Variables;
import javax.swing.JOptionPane;

public class FrmPrincipal extends javax.swing.JFrame {

    public FrmPrincipal() {
        initComponents();
        setExtendedState(MAXIMIZED_BOTH);
        cargarOpcionesMenu();
    }

    /**
     * Loads the menu options based on the user's role.
     * If the user's role is null, displays an alert message and exits the system.
     * Enables or disables menu options based on the user's role.
     * Sets the labels with the user's name, last name, and role.
     */
    private void cargarOpcionesMenu() {
        if (clases.Variables.rolNombre == null) {
            mensajeAlerta("Debe ingresar al sistema mediante verificacion de usuario.");
            System.exit(0);
        }
        if (clases.Variables.rolNombre.equalsIgnoreCase("Administrador")) {
            mnuAcceso.setEnabled(true);
            mnuClientes.setEnabled(true);
            mnuOrdenVenta.setEnabled(true);
            mnuConsultas.setEnabled(true);
            mnuProductos.setEnabled(true);
        } else if (clases.Variables.rolNombre.equalsIgnoreCase("Regencia Farmaceutica")) {
            mnuAcceso.setEnabled(false);
            mnuClientes.setEnabled(false);
            mnuOrdenVenta.setEnabled(false);
            mnuConsultas.setEnabled(true);
            mnuProductos.setEnabled(true);
        } else if (clases.Variables.rolNombre.equalsIgnoreCase("Ventas")) {
            mnuAcceso.setEnabled(false);
            mnuClientes.setEnabled(true);
            mnuOrdenVenta.setEnabled(true);
            mnuConsultas.setEnabled(true);
            mnuProductos.setEnabled(false);
        } else if (clases.Variables.rolNombre.equalsIgnoreCase("Almacenes")) {
            mnuAcceso.setEnabled(false);
            mnuClientes.setEnabled(false);
            mnuOrdenVenta.setEnabled(false);
            mnuConsultas.setEnabled(true);
            mnuProductos.setEnabled(false);
        } else {
            mnuAcceso.setEnabled(false);
            mnuClientes.setEnabled(false);
            mnuOrdenVenta.setEnabled(false);
            mnuConsultas.setEnabled(false);
            mnuProductos.setEnabled(false);
        }
        lblNombre.setText(Variables.usuarioNombre);
        lblApellido.setText(Variables.usuarioApellido);
        lblRol.setText(Variables.rolNombre);
    }

    private void mensajeError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Sistema", JOptionPane.ERROR_MESSAGE);
    }

    private void mensajeInformacion(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Sistema", JOptionPane.INFORMATION_MESSAGE);
    }

    private void mensajeAlerta(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Sistema", JOptionPane.WARNING_MESSAGE);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        escritorio = new javax.swing.JDesktopPane();
        jToolBar1 = new javax.swing.JToolBar();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lblApellido = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        lblRol = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        menuBar = new javax.swing.JMenuBar();
        mnuInicio = new javax.swing.JMenu();
        mnuiSalir = new javax.swing.JMenuItem();
        mnuAcceso = new javax.swing.JMenu();
        mnuiRoles = new javax.swing.JMenuItem();
        mnuiUsuarios = new javax.swing.JMenuItem();
        mnuOrdenVenta = new javax.swing.JMenu();
        mnuiOrdenVenta = new javax.swing.JMenuItem();
        mnuProductos = new javax.swing.JMenu();
        mnuiArticulos = new javax.swing.JMenuItem();
        mnuiLotes = new javax.swing.JMenuItem();
        mnuiLaboratorios = new javax.swing.JMenuItem();
        mnuiFFarmaceuticas = new javax.swing.JMenuItem();
        mnuClientes = new javax.swing.JMenu();
        mnuiRegCliente = new javax.swing.JMenuItem();
        mnuiSucursales = new javax.swing.JMenuItem();
        mnuiDistritos = new javax.swing.JMenuItem();
        mnuConsultas = new javax.swing.JMenu();
        mnuiKardex = new javax.swing.JMenuItem();
        mnuSalir = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sisterma administrativo Genolab");

        jToolBar1.setRollover(true);

        jLabel1.setText("Nombre:");
        jToolBar1.add(jLabel1);

        jLabel2.setText("   ");
        jToolBar1.add(jLabel2);

        lblNombre.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblNombre.setText("nombre");
        jToolBar1.add(lblNombre);

        jLabel4.setText("   ");
        jToolBar1.add(jLabel4);

        jLabel5.setText("Apellido:");
        jToolBar1.add(jLabel5);

        jLabel6.setText("   ");
        jToolBar1.add(jLabel6);

        lblApellido.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblApellido.setText("apellido");
        jToolBar1.add(lblApellido);

        jLabel8.setText("   ");
        jToolBar1.add(jLabel8);

        jLabel9.setText("Rol:");
        jToolBar1.add(jLabel9);

        jLabel10.setText("   ");
        jToolBar1.add(jLabel10);

        lblRol.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblRol.setText("rol");
        jToolBar1.add(lblRol);

        jLabel12.setText("   ");
        jToolBar1.add(jLabel12);

        mnuInicio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/presentacion/imagenes/Home.png"))); // NOI18N
        mnuInicio.setMnemonic('f');
        mnuInicio.setText("Inicio");

        mnuiSalir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_W, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        mnuiSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/presentacion/imagenes/Exit.png"))); // NOI18N
        mnuiSalir.setMnemonic('x');
        mnuiSalir.setText("Salir");
        mnuiSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuiSalirActionPerformed(evt);
            }
        });
        mnuInicio.add(mnuiSalir);

        menuBar.add(mnuInicio);

        mnuAcceso.setIcon(new javax.swing.ImageIcon(getClass().getResource("/presentacion/imagenes/Lock.png"))); // NOI18N
        mnuAcceso.setMnemonic('h');
        mnuAcceso.setText("Acceso");

        mnuiRoles.setIcon(new javax.swing.ImageIcon(getClass().getResource("/presentacion/imagenes/Company.png"))); // NOI18N
        mnuiRoles.setMnemonic('c');
        mnuiRoles.setText("Roles");
        mnuiRoles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuiRolesActionPerformed(evt);
            }
        });
        mnuAcceso.add(mnuiRoles);

        mnuiUsuarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/presentacion/imagenes/People.png"))); // NOI18N
        mnuiUsuarios.setMnemonic('a');
        mnuiUsuarios.setText("Usuarios");
        mnuiUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuiUsuariosActionPerformed(evt);
            }
        });
        mnuAcceso.add(mnuiUsuarios);

        menuBar.add(mnuAcceso);

        mnuOrdenVenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/presentacion/imagenes/Shopping cart.png"))); // NOI18N
        mnuOrdenVenta.setMnemonic('e');
        mnuOrdenVenta.setText("Ordenes de venta");

        mnuiOrdenVenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/presentacion/imagenes/Notes.png"))); // NOI18N
        mnuiOrdenVenta.setMnemonic('y');
        mnuiOrdenVenta.setText("Orden de venta");
        mnuiOrdenVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuiOrdenVentaActionPerformed(evt);
            }
        });
        mnuOrdenVenta.add(mnuiOrdenVenta);

        menuBar.add(mnuOrdenVenta);

        mnuProductos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/presentacion/imagenes/snake_cup-024.png"))); // NOI18N
        mnuProductos.setText("Productos");

        mnuiArticulos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/presentacion/imagenes/pill-024.png"))); // NOI18N
        mnuiArticulos.setText("Articulos");
        mnuiArticulos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuiArticulosActionPerformed(evt);
            }
        });
        mnuProductos.add(mnuiArticulos);

        mnuiLotes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/presentacion/imagenes/132342_box_box.png"))); // NOI18N
        mnuiLotes.setText("Lotes");
        mnuiLotes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuiLotesActionPerformed(evt);
            }
        });
        mnuProductos.add(mnuiLotes);

        mnuiLaboratorios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/presentacion/imagenes/132186_industry_industry.png"))); // NOI18N
        mnuiLaboratorios.setText("Laboratorios");
        mnuiLaboratorios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuiLaboratoriosActionPerformed(evt);
            }
        });
        mnuProductos.add(mnuiLaboratorios);

        mnuiFFarmaceuticas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/presentacion/imagenes/Retort.png"))); // NOI18N
        mnuiFFarmaceuticas.setText("Formas Farmaceuticas");
        mnuiFFarmaceuticas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuiFFarmaceuticasActionPerformed(evt);
            }
        });
        mnuProductos.add(mnuiFFarmaceuticas);

        menuBar.add(mnuProductos);

        mnuClientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/presentacion/imagenes/User group.png"))); // NOI18N
        mnuClientes.setText("Clientes");

        mnuiRegCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/presentacion/imagenes/Picture.png"))); // NOI18N
        mnuiRegCliente.setText("Registrar");
        mnuiRegCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuiRegClienteActionPerformed(evt);
            }
        });
        mnuClientes.add(mnuiRegCliente);

        mnuiSucursales.setIcon(new javax.swing.ImageIcon(getClass().getResource("/presentacion/imagenes/132240_home_home.png"))); // NOI18N
        mnuiSucursales.setText("Sucursales");
        mnuiSucursales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuiSucursalesActionPerformed(evt);
            }
        });
        mnuClientes.add(mnuiSucursales);

        mnuiDistritos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/presentacion/imagenes/Earth.png"))); // NOI18N
        mnuiDistritos.setText("Distritos");
        mnuiDistritos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuiDistritosActionPerformed(evt);
            }
        });
        mnuClientes.add(mnuiDistritos);

        menuBar.add(mnuClientes);

        mnuConsultas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/presentacion/imagenes/Table.png"))); // NOI18N
        mnuConsultas.setText("Consultas");

        mnuiKardex.setIcon(new javax.swing.ImageIcon(getClass().getResource("/presentacion/imagenes/132237_card_card_file_file.png"))); // NOI18N
        mnuiKardex.setText("Kardex");
        mnuiKardex.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuiKardexActionPerformed(evt);
            }
        });
        mnuConsultas.add(mnuiKardex);

        menuBar.add(mnuConsultas);

        mnuSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/presentacion/imagenes/Exit.png"))); // NOI18N
        mnuSalir.setText("Salir");
        mnuSalir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mnuSalirMouseClicked(evt);
            }
        });
        mnuSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuSalirActionPerformed(evt);
            }
        });
        menuBar.add(mnuSalir);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(escritorio, javax.swing.GroupLayout.DEFAULT_SIZE, 1088, Short.MAX_VALUE)
                        .addContainerGap())
                    .addComponent(jToolBar1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(escritorio, javax.swing.GroupLayout.DEFAULT_SIZE, 622, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    /**
     * Handles the action performed when the "Salir" menu item is clicked.
     * Exits the application by calling System.exit(0).
     * 
     * @param evt the action event
     */
    private void mnuiSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuiSalirActionPerformed
        System.exit(0);
    }//GEN-LAST:event_mnuiSalirActionPerformed

 
    /**
     * Event handler for the "mnuiRoles" menu item.
     * Opens a new instance of FrmiRol and adds it to the desktop pane.
     * Sets the visibility of the new form to true.
     * 
     * @param evt The ActionEvent that triggered the event.
     */
    private void mnuiRolesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuiRolesActionPerformed
        FrmiRol frm = new FrmiRol();
        escritorio.add(frm);
        frm.setVisible(true);
    }//GEN-LAST:event_mnuiRolesActionPerformed

    private void mnuSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuSalirActionPerformed
        System.exit(0);        // TODO add your handling code here:
    }//GEN-LAST:event_mnuSalirActionPerformed

    private void mnuSalirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mnuSalirMouseClicked
        System.exit(0);
    }//GEN-LAST:event_mnuSalirMouseClicked

    private void mnuiArticulosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuiArticulosActionPerformed
        FrmiArticulo frm = new FrmiArticulo();
        escritorio.add(frm);
        frm.setVisible(true);
    }//GEN-LAST:event_mnuiArticulosActionPerformed

    private void mnuiUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuiUsuariosActionPerformed
        FrmiUsuario frm = new FrmiUsuario();
        escritorio.add(frm);
        frm.setVisible(true);
    }//GEN-LAST:event_mnuiUsuariosActionPerformed

    private void mnuiOrdenVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuiOrdenVentaActionPerformed
        FrmiOrden frm = new FrmiOrden();
        escritorio.add(frm);
        frm.setVisible(true);
    }//GEN-LAST:event_mnuiOrdenVentaActionPerformed

    private void mnuiLotesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuiLotesActionPerformed
        FrmiLote frm = new FrmiLote();
        escritorio.add(frm);
        frm.setVisible(true);
    }//GEN-LAST:event_mnuiLotesActionPerformed

    private void mnuiLaboratoriosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuiLaboratoriosActionPerformed
        FrmiLaboratorio frm = new FrmiLaboratorio();
        escritorio.add(frm);
        frm.setVisible(true);
    }//GEN-LAST:event_mnuiLaboratoriosActionPerformed

    private void mnuiFFarmaceuticasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuiFFarmaceuticasActionPerformed
        FrmiFFarmaceutica frm = new FrmiFFarmaceutica();
        escritorio.add(frm);
        frm.setVisible(true);
    }//GEN-LAST:event_mnuiFFarmaceuticasActionPerformed

    private void mnuiRegClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuiRegClienteActionPerformed
        FrmiCliente frm = new FrmiCliente();
        escritorio.add(frm);
        frm.setVisible(true);        // TODO add your handling code here:
    }//GEN-LAST:event_mnuiRegClienteActionPerformed

    private void mnuiSucursalesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuiSucursalesActionPerformed
        FrmiSucursal frm = new FrmiSucursal();
        escritorio.add(frm);
        frm.setVisible(true);
    }//GEN-LAST:event_mnuiSucursalesActionPerformed

    private void mnuiDistritosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuiDistritosActionPerformed
        FrmiDistrito frm = new FrmiDistrito();
        escritorio.add(frm);
        frm.setVisible(true);        // TODO add your handling code here:
    }//GEN-LAST:event_mnuiDistritosActionPerformed

    private void mnuiKardexActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuiKardexActionPerformed
        FrmiKardex frm = new FrmiKardex();
        escritorio.add(frm);
        frm.setVisible(true);
    }//GEN-LAST:event_mnuiKardexActionPerformed

    /**
     * The main method is the entry point of the application.
     * It sets the look and feel of the user interface and creates and displays the main form.
     *
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
            java.util.logging.Logger.getLogger(FrmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane escritorio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel lblApellido;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblRol;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenu mnuAcceso;
    private javax.swing.JMenu mnuClientes;
    private javax.swing.JMenu mnuConsultas;
    private javax.swing.JMenu mnuInicio;
    private javax.swing.JMenu mnuOrdenVenta;
    private javax.swing.JMenu mnuProductos;
    private javax.swing.JMenu mnuSalir;
    private javax.swing.JMenuItem mnuiArticulos;
    private javax.swing.JMenuItem mnuiDistritos;
    private javax.swing.JMenuItem mnuiFFarmaceuticas;
    private javax.swing.JMenuItem mnuiKardex;
    private javax.swing.JMenuItem mnuiLaboratorios;
    private javax.swing.JMenuItem mnuiLotes;
    private javax.swing.JMenuItem mnuiOrdenVenta;
    private javax.swing.JMenuItem mnuiRegCliente;
    private javax.swing.JMenuItem mnuiRoles;
    private javax.swing.JMenuItem mnuiSalir;
    private javax.swing.JMenuItem mnuiSucursales;
    private javax.swing.JMenuItem mnuiUsuarios;
    // End of variables declaration//GEN-END:variables

}
