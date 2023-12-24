package presentacion;

import javax.swing.JOptionPane;
import javax.swing.table.TableRowSorter;
import negocio.SucursalControl;
import entidades.Cliente;
import entidades.Distrito;

public class FrmiSucursal extends javax.swing.JInternalFrame {

    //atributos
    private final SucursalControl CONTROL;
    private String filtro;

    //constructor
    public FrmiSucursal() {
        initComponents();
        this.filtro = "c.nombre";
        this.CONTROL = new SucursalControl();
        this.listado("", this.filtro);
        this.listarCombobox();
        limpiar();
//        desactivar();
//        cboCliente.setSelectedItem(null);
//        cboDistrito.setSelectedItem(null);

    }

    //metodos
    private void listado(String texto, String campo) {
        tblListado.setModel(this.CONTROL.listar(texto, campo));
        TableRowSorter orden = new TableRowSorter(tblListado.getModel());
        tblListado.setRowSorter(orden);
        ocultarColumnas();
    }

    private void seleccionarFiltro() {
        if (rbtnCliente.isSelected()) {
            this.filtro = "c.nombre";
        }
        if (rbtnDistrito.isSelected()) {
            this.filtro = "d.nombre";
        }
    }

    private void listarCombobox() {
        cboCliente.setModel(this.CONTROL.listarClientes());
        cboDistrito.setModel(this.CONTROL.listarDistritos());
    }

    private void activar() {
        btnGuardar.setEnabled(true);
        btnRegistrar.setEnabled(false);
    }

    private void desactivar() {
        btnEditar.setEnabled(false);
        btnRegistrar.setEnabled(true);
        btnGuardar.setEnabled(false);
    }

    private void limpiar() {
        cboCliente.setSelectedItem(null);
        cboDistrito.setSelectedItem(null);
        txtDireccion.setText("");
        txtIdCliente.setText("");
        rbtnActivo.setSelected(true);
        txtBuscar.setText("");
        cboCliente.requestFocus();
        txtIdSucursal.setText("");

        desactivar();
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

    private static int mensajeConfirmacion(String mensaje) {
        return JOptionPane.showConfirmDialog(null, mensaje, "Confirmación", JOptionPane.YES_NO_OPTION);
    }

    private void ocultarColumnas() {
        tblListado.getColumnModel().getColumn(0).setMaxWidth(30);
        tblListado.getColumnModel().getColumn(0).setMinWidth(30);
        tblListado.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(30);
        tblListado.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(30);
        tblListado.getColumnModel().getColumn(1).setMaxWidth(0);
        tblListado.getColumnModel().getColumn(1).setMinWidth(0);
        tblListado.getTableHeader().getColumnModel().getColumn(1).setMaxWidth(0);
        tblListado.getTableHeader().getColumnModel().getColumn(1).setMaxWidth(0);
        tblListado.getColumnModel().getColumn(2).setMaxWidth(0);
        tblListado.getColumnModel().getColumn(2).setMinWidth(0);
        tblListado.getTableHeader().getColumnModel().getColumn(2).setMaxWidth(0);
        tblListado.getTableHeader().getColumnModel().getColumn(2).setMaxWidth(0);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rbtngEstado = new javax.swing.ButtonGroup();
        rbtngCampoBusqueda = new javax.swing.ButtonGroup();
        tabGeneral = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblListado = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        rbtnCliente = new javax.swing.JRadioButton();
        rbtnDistrito = new javax.swing.JRadioButton();
        btnEditar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cboCliente = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        cboDistrito = new javax.swing.JComboBox<>();
        btnRegistrar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        rbtnActivo = new javax.swing.JRadioButton();
        rbtnInactivo = new javax.swing.JRadioButton();
        jLabel8 = new javax.swing.JLabel();
        txtIdCliente = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtIdSucursal = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtDireccion = new javax.swing.JTextArea();
        jLabel10 = new javax.swing.JLabel();
        btnGuardar = new javax.swing.JButton();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Sucursales");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Sucursales registradas"));

        tblListado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblListado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblListadoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblListado);

        jLabel6.setText("Buscar");

        txtBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBuscarActionPerformed(evt);
            }
        });
        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarKeyReleased(evt);
            }
        });

        rbtngCampoBusqueda.add(rbtnCliente);
        rbtnCliente.setSelected(true);
        rbtnCliente.setText("Cliente");
        rbtnCliente.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                rbtnClienteStateChanged(evt);
            }
        });
        rbtnCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtnClienteActionPerformed(evt);
            }
        });

        rbtngCampoBusqueda.add(rbtnDistrito);
        rbtnDistrito.setText("Distrito");
        rbtnDistrito.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                rbtnDistritoStateChanged(evt);
            }
        });

        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/presentacion/imagenes/Notes.png"))); // NOI18N
        btnEditar.setText("Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/presentacion/imagenes/132180_log_out_out_log.png"))); // NOI18N
        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 543, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(rbtnCliente)
                        .addGap(28, 28, 28)
                        .addComponent(rbtnDistrito)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnEditar))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnSalir)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rbtnCliente)
                    .addComponent(rbtnDistrito)
                    .addComponent(btnEditar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSalir)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tabGeneral.addTab("Listado", jPanel2);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Registrar sucursal de cliente"));

        jLabel1.setText("Nombre de cliente");

        cboCliente.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboClienteItemStateChanged(evt);
            }
        });
        cboCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboClienteActionPerformed(evt);
            }
        });

        jLabel2.setText("Distrito");

        cboDistrito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboDistritoActionPerformed(evt);
            }
        });

        btnRegistrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/presentacion/imagenes/OK.png"))); // NOI18N
        btnRegistrar.setText("Registrar");
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });

        btnLimpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/presentacion/imagenes/Undo.png"))); // NOI18N
        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        jLabel7.setText("Estado de direccion");

        rbtngEstado.add(rbtnActivo);
        rbtnActivo.setText("Activo");

        rbtngEstado.add(rbtnInactivo);
        rbtnInactivo.setText("Inactivo");
        rbtnInactivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtnInactivoActionPerformed(evt);
            }
        });

        jLabel8.setText("Id cliente");

        txtIdCliente.setEnabled(false);

        jLabel9.setText("Id sucursal");

        txtIdSucursal.setEnabled(false);
        txtIdSucursal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdSucursalActionPerformed(evt);
            }
        });

        txtDireccion.setColumns(20);
        txtDireccion.setRows(5);
        jScrollPane2.setViewportView(txtDireccion);

        jLabel10.setText("Direccion");

        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/presentacion/imagenes/Save.png"))); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(0, 499, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnRegistrar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnLimpiar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnGuardar))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 535, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel1)
                                                .addGap(18, 18, 18))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel2)
                                                .addGap(78, 78, 78)))
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(cboCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(cboDistrito, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(46, 46, 46)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel9)
                                                .addGap(18, 18, 18)
                                                .addComponent(txtIdSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel8)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(txtIdCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(rbtnActivo)
                                        .addGap(18, 18, 18)
                                        .addComponent(rbtnInactivo)))))
                        .addGap(14, 14, 14))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8)
                        .addComponent(txtIdCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(cboCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel9)
                        .addComponent(txtIdSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(cboDistrito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbtnActivo)
                    .addComponent(rbtnInactivo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRegistrar)
                    .addComponent(btnLimpiar)
                    .addComponent(btnGuardar))
                .addContainerGap())
        );

        tabGeneral.addTab("Mantenimiento", jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tabGeneral)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tabGeneral)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cboDistritoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboDistritoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboDistritoActionPerformed

    private void rbtnInactivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnInactivoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rbtnInactivoActionPerformed

    private void cboClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboClienteActionPerformed

    private void rbtnClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rbtnClienteActionPerformed

    private void tblListadoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblListadoMouseClicked
        btnEditar.setEnabled(true);
    }//GEN-LAST:event_tblListadoMouseClicked

    private void cboClienteItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboClienteItemStateChanged
        //Realizar refundicion a las clases cliente y distrito con los objetos seleccionados
        //en los combobox
        Cliente clienteSeleccionado = (Cliente) cboCliente.getSelectedItem();
//        Distrito distritoSeleccionado = (Distrito) cboDistrito.getSelectedItem();
        String idCliente = "";
        if (clienteSeleccionado == null) {
            idCliente = "";
        } else {
            idCliente = String.valueOf(clienteSeleccionado.getIdCliente());
        }

        txtIdCliente.setText(idCliente);

    }//GEN-LAST:event_cboClienteItemStateChanged

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        limpiar();          // TODO add your handling code here:
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
        if (cboCliente.getSelectedItem() == null || cboDistrito.getSelectedItem() == null) {
            mensajeAlerta("Debe seleccionar un cliente y distrito");
            return;
        }
        String respuesta = "";
        //Realizar refundicion a las clases cliente y distrito con los objetos seleccionados
        //en los combobox para luego capturar las ID cliente, distrito
        Cliente clienteSeleccionado = (Cliente) cboCliente.getSelectedItem();
        Distrito distritoSeleccionado = (Distrito) cboDistrito.getSelectedItem();
        int clienteId = clienteSeleccionado.getIdCliente();
//        String clienteNombre = clienteSeleccionado.getNombre();
        int distritoId = distritoSeleccionado.getIdDistrito();
//        String distritoNombre = distritoSeleccionado.getNombre();
        String direccion = txtDireccion.getText();
        boolean estado;
        if (rbtnActivo.isSelected()) {
            estado = true;
        } else {
            estado = false;
        }

        respuesta = this.CONTROL.insertar(distritoId, clienteId, direccion, estado);
        if (respuesta.equals("OK")) {
            mensajeInformacion("Registro insertado");
        } else {
            mensajeError("Error insertando el registro");
        }
        limpiar();
        this.listado("", this.filtro);

     }//GEN-LAST:event_btnRegistrarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        if (tblListado.getSelectedRowCount() == 1) {
            int fila = tblListado.getSelectedRow();

            String sucursalId = String.valueOf(tblListado.getValueAt(fila, 0));
            int clienteId = Integer.parseInt(String.valueOf(tblListado.getValueAt(fila, 2)));
            String clienteNombre = String.valueOf(tblListado.getValueAt(fila, 3));
            int distritoId = Integer.parseInt(String.valueOf(tblListado.getValueAt(fila, 1)));
            String distritoNombre = String.valueOf(tblListado.getValueAt(fila, 4));
            String direccion = String.valueOf(tblListado.getValueAt(fila, 5));

            String estado = String.valueOf(tblListado.getValueAt(fila, 6));

            //Instanciar nuevos objetos cliente y distrito para determinar
            //el objeto seleccionado en la tabla
            Cliente clienteSeleccionado = new Cliente(clienteId, clienteNombre);
            Distrito distritoSeleccionado = new Distrito(distritoId, distritoNombre);

            txtIdSucursal.setText(sucursalId);
            cboCliente.setSelectedItem(clienteSeleccionado);
            cboDistrito.setSelectedItem(distritoSeleccionado);
            txtIdCliente.setText(String.valueOf(clienteId));
            txtDireccion.setText(direccion);

            if (estado.equals("Activo")) {
                rbtnActivo.setSelected(true);
            } else {
                rbtnInactivo.setSelected(true);
            }
            activar();
            tabGeneral.setSelectedIndex(1);
        } else {
            mensajeError("Debe seleccionar almenos un registro");
        }
    }//GEN-LAST:event_btnEditarActionPerformed

    private void txtIdSucursalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdSucursalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdSucursalActionPerformed

    private void txtBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarActionPerformed

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
        this.listado(txtBuscar.getText(), this.filtro);        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarKeyReleased

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if (cboCliente.getSelectedItem() == null || cboDistrito.getSelectedItem() == null) {
            mensajeAlerta("Debe seleccionar un cliente y distrito");
            return;
        }
        if (mensajeConfirmacion("Desea editar este registro") == 0) {
            String respuesta = "";
            //Realizar refundicion a las clases cliente y distrito con los objetos seleccionados
            //en los combobox para luego capturar las ID cliente, distrito
            int sucursalId = Integer.parseInt(txtIdSucursal.getText());
            Cliente clienteSeleccionado = (Cliente) cboCliente.getSelectedItem();
            Distrito distritoSeleccionado = (Distrito) cboDistrito.getSelectedItem();
            int clienteId = clienteSeleccionado.getIdCliente();

            int distritoId = distritoSeleccionado.getIdDistrito();
            String direccion = txtDireccion.getText();
            boolean estado;
            if (rbtnActivo.isSelected()) {
                estado = true;
            } else {
                estado = false;
            }

            respuesta = this.CONTROL.actualizar(sucursalId, distritoId, clienteId, direccion, estado);

            if (respuesta.equals("OK")) {
                mensajeInformacion("Registro actualizado");
            } else {
                mensajeError("Error actualizando el registro");
            }
            limpiar();
            this.listado("", this.filtro);
        } else {
            limpiar();
        }        // TODO add your handling code here:
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void rbtnClienteStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_rbtnClienteStateChanged
        seleccionarFiltro();
    }//GEN-LAST:event_rbtnClienteStateChanged

    private void rbtnDistritoStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_rbtnDistritoStateChanged
        seleccionarFiltro();
    }//GEN-LAST:event_rbtnDistritoStateChanged

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JComboBox<String> cboCliente;
    private javax.swing.JComboBox<String> cboDistrito;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JRadioButton rbtnActivo;
    private javax.swing.JRadioButton rbtnCliente;
    private javax.swing.JRadioButton rbtnDistrito;
    private javax.swing.JRadioButton rbtnInactivo;
    private javax.swing.ButtonGroup rbtngCampoBusqueda;
    private javax.swing.ButtonGroup rbtngEstado;
    private javax.swing.JTabbedPane tabGeneral;
    private javax.swing.JTable tblListado;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextArea txtDireccion;
    private javax.swing.JTextField txtIdCliente;
    private javax.swing.JTextField txtIdSucursal;
    // End of variables declaration//GEN-END:variables
}
