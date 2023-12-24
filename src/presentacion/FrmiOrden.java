package presentacion;

import entidades.Articulo;
import entidades.Cliente;
import entidades.Sucursal;
import entidades.OrdenVenta;
import entidades.Lote;
import entidades.Transaccion;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import negocio.OrdenVentaControl;
import negocio.LoteControl;
import java.text.DecimalFormat;
import javax.swing.JFrame;

public class FrmiOrden extends javax.swing.JInternalFrame {

    public final OrdenVentaControl CONTROL;
    public final LoteControl LOTECONTROL;
    private Cliente cliente;
    private Sucursal sucursal;
    private OrdenVenta ordenVenta;
    private List<Transaccion> listaTransacciones;
    private DefaultTableModel modeloTabla;
    private final DecimalFormat FORMATO = new DecimalFormat("#.###");
    String[] titulos = {"ARTICULO", "DESCRIPCION", "LOTE", "CANTIDAD", "IMPORTE"};

    public FrmiOrden() {
        initComponents();
        this.cliente = new Cliente();
        this.sucursal = new Sucursal();
        this.ordenVenta = new OrdenVenta();
        this.CONTROL = new OrdenVentaControl();
        this.LOTECONTROL = new LoteControl();
        this.listaTransacciones = new ArrayList();
        this.listarComboboxArticulo();

        this.modeloTabla = new DefaultTableModel(null, titulos);
        this.listarDetalle();
        vaciarLabels();

        cboArticulo.setSelectedItem(null);
    }

    private void limpiarOrden() {
        txtSucursal.setText("");
        txtCliente.setText("");
        txtImporteTotal.setText("");
        txtCantidad.setText("");
        this.sucursal = new Sucursal();
        this.cliente = new Cliente();
        this.listaTransacciones = new ArrayList();
        this.modeloTabla = new DefaultTableModel(null, titulos);
        listarDetalle();

    }

    private void vaciarLabels() {
        lblCliente.setText("");
        lblDistrito.setText("");
        lblFecha.setText("");
        lblImporte.setText("");
    }

    private void limpiarArticulo() {
        cboLote.setSelectedItem(null);
        cboArticulo.setSelectedItem(null);
        txtCantidad.setText("");
        cboArticulo.requestFocus();
    }

    private void listarComboboxArticulo() {
        cboArticulo.setModel(this.LOTECONTROL.cargarArticulo());

    }

    private void listarComboboxLote(int idArticulo) {
        cboLote.setModel(this.CONTROL.cargarLote(idArticulo));
    }

    private void listarDetalle() {
        tblDetalle.setModel(this.modeloTabla);
        TableRowSorter orden = new TableRowSorter(tblDetalle.getModel());
        tblDetalle.setRowSorter(orden);
    }

    private void listadoConsulta(OrdenVenta orden) {

        tblListado.setModel(this.CONTROL.listarTransaccion(orden.getIdOrden()));

    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    //Metodos
    private void mensajeError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Sistema", JOptionPane.ERROR_MESSAGE);
    }

    private void mensajeAdvertencia(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Sistema", JOptionPane.WARNING_MESSAGE);
    }

    private void mensajeInformacion(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Sistema", JOptionPane.INFORMATION_MESSAGE);
    }

    private static int mensajeConfirmacion(String mensaje) {
        return JOptionPane.showConfirmDialog(null, mensaje, "Confirmación", JOptionPane.YES_NO_OPTION);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        btnCliente = new javax.swing.JButton();
        txtCliente = new javax.swing.JTextField();
        btnSucursal = new javax.swing.JButton();
        txtSucursal = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cboArticulo = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        cboLote = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        btnAgregar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        txtCantidad = new javax.swing.JTextField();
        btnQuitar = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDetalle = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        txtImporteTotal = new javax.swing.JTextField();
        btnConfirmar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        btnOrdenes = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblListado = new javax.swing.JTable();
        btnLimpiar2 = new javax.swing.JButton();
        jToolBar1 = new javax.swing.JToolBar();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        lblCliente = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lblDistrito = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        lblFecha = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        lblImporte = new javax.swing.JLabel();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Orden de venta");

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Generar orden"));

        btnCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/presentacion/imagenes/People.png"))); // NOI18N
        btnCliente.setText("Cliente");
        btnCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClienteActionPerformed(evt);
            }
        });

        txtCliente.setEnabled(false);

        btnSucursal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/presentacion/imagenes/132240_home_home.png"))); // NOI18N
        btnSucursal.setText("Sucursal");
        btnSucursal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSucursalActionPerformed(evt);
            }
        });

        txtSucursal.setEnabled(false);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSucursal)
                    .addComponent(btnCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtCliente, javax.swing.GroupLayout.DEFAULT_SIZE, 378, Short.MAX_VALUE)
                    .addComponent(txtSucursal))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCliente)
                    .addComponent(txtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSucursal)
                    .addComponent(txtSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Agregar articulos"));

        jLabel1.setText("Articulo");

        cboArticulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboArticuloActionPerformed(evt);
            }
        });

        jLabel3.setText("Lote");

        jLabel4.setText("Cantidad");

        btnAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/presentacion/imagenes/Add.png"))); // NOI18N
        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        btnLimpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/presentacion/imagenes/Undo.png"))); // NOI18N
        btnLimpiar.setText("Limpiar");

        btnQuitar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/presentacion/imagenes/Remove.png"))); // NOI18N
        btnQuitar.setText("Quitar transaccion");
        btnQuitar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuitarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(cboArticulo, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(cboLote, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 332, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(143, 143, 143))
                            .addComponent(txtCantidad)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(btnAgregar)
                        .addGap(29, 29, 29)
                        .addComponent(btnLimpiar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnQuitar)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboArticulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboLote, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAgregar)
                    .addComponent(btnLimpiar)
                    .addComponent(btnQuitar))
                .addGap(16, 16, 16))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Detalle"));

        tblDetalle.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tblDetalle);

        jLabel5.setText("Importe total");

        txtImporteTotal.setEnabled(false);
        txtImporteTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtImporteTotalActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel5)
                        .addGap(37, 37, 37)
                        .addComponent(txtImporteTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtImporteTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnConfirmar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/presentacion/imagenes/OK.png"))); // NOI18N
        btnConfirmar.setText("Confirmar orden");
        btnConfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmarActionPerformed(evt);
            }
        });

        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/presentacion/imagenes/132266_no_no exit_cae_delete_vote icon.png"))); // NOI18N
        btnCancelar.setText("Cancelar orden");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/presentacion/imagenes/132180_log_out_out_log.png"))); // NOI18N
        jButton1.setText("Salir");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnConfirmar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCancelar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addGap(22, 22, 22))
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(76, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnConfirmar)
                    .addComponent(btnCancelar)
                    .addComponent(jButton1))
                .addContainerGap(139, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Generar", jPanel2);

        btnOrdenes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/presentacion/imagenes/Shopping cart.png"))); // NOI18N
        btnOrdenes.setText("Ordenes ");
        btnOrdenes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOrdenesActionPerformed(evt);
            }
        });

        tblListado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane3.setViewportView(tblListado);

        btnLimpiar2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/presentacion/imagenes/Undo.png"))); // NOI18N
        btnLimpiar2.setText("Limpiar");
        btnLimpiar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiar2ActionPerformed(evt);
            }
        });

        jToolBar1.setRollover(true);

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel11.setText("Cliente:");
        jToolBar1.add(jLabel11);

        jLabel12.setText("   ");
        jToolBar1.add(jLabel12);

        lblCliente.setText("cliente");
        jToolBar1.add(lblCliente);

        jLabel2.setText("   ");
        jToolBar1.add(jLabel2);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("Distrito:");
        jToolBar1.add(jLabel6);

        jLabel7.setText("   ");
        jToolBar1.add(jLabel7);

        lblDistrito.setText("distrito");
        jToolBar1.add(lblDistrito);

        jLabel9.setText("   ");
        jToolBar1.add(jLabel9);

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel10.setText("Fecha:");
        jToolBar1.add(jLabel10);

        jLabel13.setText("   ");
        jToolBar1.add(jLabel13);

        lblFecha.setText("fecha");
        jToolBar1.add(lblFecha);

        jLabel15.setText("   ");
        jToolBar1.add(jLabel15);

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel16.setText("Importe");
        jToolBar1.add(jLabel16);

        jLabel17.setText("   ");
        jToolBar1.add(jLabel17);

        lblImporte.setText("importe");
        jToolBar1.add(lblImporte);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(btnOrdenes)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnLimpiar2))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 1024, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(74, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnOrdenes)
                    .addComponent(btnLimpiar2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 517, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(128, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Consultas", jPanel1);

        jScrollPane2.setViewportView(jTabbedPane1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1061, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 678, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        limpiarOrden();
        limpiarArticulo();
        btnCliente.requestFocus();

    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClienteActionPerformed
        DialogCliente ventanaCliente = new DialogCliente((JFrame) this.getTopLevelAncestor(), true);
        ventanaCliente.setLocationRelativeTo(null);
        ventanaCliente.setVisible(true);
//        ventanaCliente.getCliente().toString();
        this.cliente = ventanaCliente.getCliente();
        txtCliente.setText(this.cliente.toString());

    }//GEN-LAST:event_btnClienteActionPerformed

    private void btnSucursalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSucursalActionPerformed
        DialogSucursal ventanaSucursal = new DialogSucursal((JFrame) this.getTopLevelAncestor(), true);
        ventanaSucursal.setIdCliente(this.cliente.getIdCliente());
        ventanaSucursal.setNombreCliente(this.cliente.getNombre());
        ventanaSucursal.listado(this.cliente.getIdCliente(), this.cliente.getNombre());

        ventanaSucursal.setLocationRelativeTo(null);
        ventanaSucursal.setVisible(true);

        this.sucursal = ventanaSucursal.getSucursal();
        txtSucursal.setText(this.sucursal.toString());
    }//GEN-LAST:event_btnSucursalActionPerformed

    private void cboArticuloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboArticuloActionPerformed
        if (!(cboArticulo.getSelectedItem() == null)) {
            Articulo articuloSeleccionado = (Articulo) cboArticulo.getSelectedItem();
            int articuloId = articuloSeleccionado.getIdArticulo();
            listarComboboxLote(articuloId);
        }

    }//GEN-LAST:event_cboArticuloActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        String[] registro = new String[5];
        float importeTotal = 0f;
        if (cboArticulo.getSelectedItem() != null && cboLote.getSelectedItem() != null && !txtCantidad.getText().isEmpty()) {
            this.modeloTabla.setDataVector(null, titulos);
            Articulo articuloSeleccionado = (Articulo) cboArticulo.getSelectedItem();
            Lote loteSeleccionado = (Lote) cboLote.getSelectedItem();
            int cantidad = Integer.parseInt(txtCantidad.getText());

            String articulo = articuloSeleccionado.getCodigo();
            String descripcion = articuloSeleccionado.getDescripcion();
            String lote = loteSeleccionado.getLoteCodigo();
            int loteId = loteSeleccionado.getIdLote();
            float precioUnitario = loteSeleccionado.getPrecioUnitario();
            float importe = cantidad * precioUnitario;
            float importe2d = Float.parseFloat(FORMATO.format(importe).replace(',', '.'));

            this.listaTransacciones.add(new Transaccion(articulo, descripcion, lote, loteId, cantidad, importe2d));

            for (Transaccion item : this.listaTransacciones) {
                registro[0] = item.getArticulo();
                registro[1] = item.getArticuloDescripcion();
                registro[2] = item.getLoteCodigo();
                registro[3] = Integer.toString(item.getCantidad());
                registro[4] = Float.toString(item.getImporte());
                importeTotal = item.getImporte() + importeTotal;
                this.modeloTabla.addRow(registro);
            }
            importeTotal = Float.parseFloat(FORMATO.format(importeTotal).replace(',', '.'));
            txtImporteTotal.setText(String.valueOf(importeTotal));

        } else {
            mensajeAdvertencia("Debe llenar todos los campos antes de proceder con esta operacion");
        }
        this.listarDetalle();
        limpiarArticulo();
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnQuitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuitarActionPerformed
        String[] registro = new String[5];
        float importeTotal = 0f;
        if (tblDetalle.getSelectedRowCount() == 1) {

            int indice = tblDetalle.getSelectedRow();
            this.modeloTabla.setDataVector(null, titulos);
            this.listaTransacciones.remove(indice);
            for (Transaccion item : this.listaTransacciones) {
                registro[0] = item.getArticulo();
                registro[1] = item.getArticuloDescripcion();
                registro[2] = item.getLoteCodigo();
                registro[3] = Integer.toString(item.getCantidad());
                registro[4] = Float.toString(item.getImporte());
                importeTotal = item.getImporte() + importeTotal;
                this.modeloTabla.addRow(registro);
            }
            importeTotal = Float.parseFloat(FORMATO.format(importeTotal).replace(',', '.'));
            txtImporteTotal.setText(String.valueOf(importeTotal));
        } else {
            mensajeAdvertencia("Debe seleccionar un registro para quitar");
        }
    }//GEN-LAST:event_btnQuitarActionPerformed

    private void btnConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmarActionPerformed
        if (txtCliente.getText().isEmpty() || txtSucursal.getText().isEmpty()) {
            mensajeAdvertencia("Debe seleccionar un cliente y sucursal");
            return;
        }
        if (this.listaTransacciones.isEmpty()) {
            mensajeAdvertencia("Debe agregar transacciones");
            return;
        }

        String respuesta = "";
        ordenVenta.setIdSucursal(this.sucursal.getIdSucursal());
        
        ordenVenta.setIdUsuario(clases.Variables.usuarioId);
        ordenVenta.setNumeroOrden(((int) (Math.random() * 100)));

        respuesta = CONTROL.insertar(this.ordenVenta.getNumeroOrden(), this.ordenVenta.getIdSucursal(), this.ordenVenta.getIdUsuario(), this.listaTransacciones);
        if (respuesta.equals("OK")) {
            mensajeInformacion("Orden generada correctamente");
//            limpiarArticulo();
//            limpiarOrden();
        } else {
            mensajeError("Error generando orden");
        }
        limpiarArticulo();
        limpiarOrden();
    }//GEN-LAST:event_btnConfirmarActionPerformed

    private void txtImporteTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtImporteTotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtImporteTotalActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnOrdenesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOrdenesActionPerformed
        DialogOrden ventanaOrden = new DialogOrden((JFrame) this.getTopLevelAncestor(), true);
        ventanaOrden.setLocationRelativeTo(null);
        ventanaOrden.setVisible(true);
        listadoConsulta(ventanaOrden.getOrdenVenta());
        lblCliente.setText(ventanaOrden.getOrdenVenta().getClienteNombre());
        lblDistrito.setText(ventanaOrden.getOrdenVenta().getDistritoNombre());
        lblFecha.setText(String.valueOf(ventanaOrden.getOrdenVenta().getFechaHora()));
        lblImporte.setText(Float.toString(ventanaOrden.getOrdenVenta().getImporteTotal()));
    }//GEN-LAST:event_btnOrdenesActionPerformed

    private void btnLimpiar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiar2ActionPerformed
        tblListado.setModel(new DefaultTableModel(null, titulos));
        vaciarLabels();

    }//GEN-LAST:event_btnLimpiar2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnCliente;
    private javax.swing.JButton btnConfirmar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnLimpiar2;
    private javax.swing.JButton btnOrdenes;
    private javax.swing.JButton btnQuitar;
    private javax.swing.JButton btnSucursal;
    private javax.swing.JComboBox<String> cboArticulo;
    private javax.swing.JComboBox<String> cboLote;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel lblCliente;
    private javax.swing.JLabel lblDistrito;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblImporte;
    private javax.swing.JTable tblDetalle;
    private javax.swing.JTable tblListado;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JTextField txtCliente;
    private javax.swing.JTextField txtImporteTotal;
    private javax.swing.JTextField txtSucursal;
    // End of variables declaration//GEN-END:variables
}
