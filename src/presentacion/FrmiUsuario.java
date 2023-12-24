package presentacion;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import javax.swing.JOptionPane;
import javax.swing.table.TableRowSorter;
import negocio.UsuarioControl;
import java.sql.Date;
import java.util.Calendar;
import entidades.Rol;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import org.apache.commons.validator.EmailValidator;
import clases.ValidacionDeTexto;

public class FrmiUsuario extends javax.swing.JInternalFrame {

    //atributos
    private final UsuarioControl CONTROL;
    private String rutaDestino;
    private final String DIRECTORIO = "src/files/usuarios/";
    private String imagen = "";
    private String imagenAnt;
    private String filtro;

    //atributos de camara
    File salidaImage;
    int anchoCamara = 225;
    int altoCamara = 200;
    Dimension dimension = new Dimension(anchoCamara, altoCamara);
    Dimension dimension1 = WebcamResolution.VGA.getSize();
    Webcam webcam = Webcam.getDefault();
    WebcamPanel webcamPanel = new WebcamPanel(webcam, dimension, false);
    BufferedImage rutaWebCam;

    //constructor
    public FrmiUsuario() {
        initComponents();
        this.filtro = "u.nombre";
        this.CONTROL = new UsuarioControl();
        this.listado("", this.filtro);
        this.listarCombobox();

        dcFechaNac.setDate(new Date(1970 - 1900, 00, 01));

        txtIdUsuario.setEnabled(false);
        btnGuardar.setEnabled(false);

        webcam.setViewSize(dimension1);
        webcamPanel.setFillArea(true);
        pnlCamara.setLayout(new FlowLayout());
        pnlCamara.add(webcamPanel);

        desactiva();
        aplicarFiltros();
    }

    private void activa() {
        btnCapturaFoto.setEnabled(true);
    }

    private void desactiva() {
        btnCapturaFoto.setEnabled(false);
    }
    
    private void seleccionarFiltro(){
        if (rbtnNombre.isSelected()) {
            this.filtro = "u.nombre";            
        } 
        if (rbtnRol.isSelected()) {
            this.filtro = "r.nombre";
        }
        if (rbtnCI.isSelected()) {
            this.filtro = "u.ci";
        }
    }

    //metodos
    private void listado(String texto) {
        tblListado.setModel(this.CONTROL.listar(texto));
        TableRowSorter orden = new TableRowSorter(tblListado.getModel());
        tblListado.setRowSorter(orden);
        ocultarColumnas();
    }

    private void listado(String texto, String campo) {
        tblListado.setModel(this.CONTROL.listar(texto, campo));
        TableRowSorter orden = new TableRowSorter(tblListado.getModel());
        tblListado.setRowSorter(orden);
        ocultarColumnas();
    }

    private void listarCombobox() {
        cboRol.setModel(this.CONTROL.cargarRoles());
    }

    private void activar() {
        btnGuardar.setEnabled(true);
        btnRegistrar.setEnabled(false);
    }

    private void desactivar() {
        btnGuardar.setEnabled(false);
        btnRegistrar.setEnabled(true);
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

    private void limpiar() {
        txtNombre.setText("");
        txtApellido.setText("");
        txtIdUsuario.setText("");
        cboRol.setSelectedItem(null);
        txtCi.setText("");
        txtEmail.setText("");
        txtContraseña.setText("");
        rbtnActivo.setSelected(true);
        dcFechaNac.setDate(new Date(1970 - 1900, 00, 01));
        txtNombre.requestFocus();
        desactivar();

        this.imagen = "";
        this.imagenAnt = "";
        lblFoto.setIcon(null);
        this.rutaDestino = "";

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
        tblListado.getColumnModel().getColumn(5).setMaxWidth(0);
        tblListado.getColumnModel().getColumn(5).setMinWidth(0);
        tblListado.getTableHeader().getColumnModel().getColumn(5).setMaxWidth(0);
        tblListado.getTableHeader().getColumnModel().getColumn(5).setMaxWidth(0);
        tblListado.getColumnModel().getColumn(9).setMaxWidth(0);
        tblListado.getColumnModel().getColumn(9).setMinWidth(0);
        tblListado.getTableHeader().getColumnModel().getColumn(9).setMaxWidth(0);
        tblListado.getTableHeader().getColumnModel().getColumn(9).setMaxWidth(0);
    }

    private void subirImagenes() {
        this.imagen = CONTROL.generaNombreImagen(txtNombre.getText(), txtCi.getText()).trim() + ".jpg";
        this.rutaDestino = this.DIRECTORIO + this.imagen;
        salidaImage = new File(rutaDestino);
        try {
            ImageIO.write(rutaWebCam, "jpg", salidaImage);
            mensajeInformacion("Imagen guardada en: " + salidaImage.getAbsolutePath());
        } catch (HeadlessException | IOException e) {
            mensajeError(e.getMessage());
        }
    }

    private void aplicarFiltros() {
        ValidacionDeTexto.applyNumericFilter(txtCi);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField4 = new javax.swing.JTextField();
        rbtgOpcionBusqueda = new javax.swing.ButtonGroup();
        rbtgEstado = new javax.swing.ButtonGroup();
        tabbGeneral = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        rbtnNombre = new javax.swing.JRadioButton();
        rbtnRol = new javax.swing.JRadioButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblListado = new javax.swing.JTable();
        btnSalir = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        rbtnCI = new javax.swing.JRadioButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtApellido = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtCi = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtContraseña = new javax.swing.JPasswordField();
        btnRegistrar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        rbtnActivo = new javax.swing.JRadioButton();
        rbtnInactivo = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        cboRol = new javax.swing.JComboBox<>();
        dcFechaNac = new com.toedter.calendar.JDateChooser();
        txtIdUsuario = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        btnLimpiar = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        pnlCamara = new javax.swing.JPanel();
        lblFoto = new javax.swing.JLabel();
        btnIniciarCam = new javax.swing.JButton();
        btnApagarCam = new javax.swing.JButton();
        btnCapturaFoto = new javax.swing.JButton();

        jTextField4.setText("jTextField4");

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Usuarios");
        setToolTipText("");
        setAutoscrolls(true);
        setPreferredSize(new java.awt.Dimension(800, 631));

        jLabel2.setText("Buscar usuario");

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

        rbtgOpcionBusqueda.add(rbtnNombre);
        rbtnNombre.setSelected(true);
        rbtnNombre.setText("Nombre");
        rbtnNombre.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                rbtnNombreStateChanged(evt);
            }
        });

        rbtgOpcionBusqueda.add(rbtnRol);
        rbtnRol.setText("Rol");
        rbtnRol.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                rbtnRolStateChanged(evt);
            }
        });

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

        btnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/presentacion/imagenes/132180_log_out_out_log.png"))); // NOI18N
        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/presentacion/imagenes/Notes.png"))); // NOI18N
        btnEditar.setText("Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        rbtgOpcionBusqueda.add(rbtnCI);
        rbtnCI.setText("CI");
        rbtnCI.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                rbtnCIStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(46, 46, 46)
                                .addComponent(rbtnNombre)
                                .addGap(32, 32, 32)
                                .addComponent(rbtnRol)
                                .addGap(32, 32, 32)
                                .addComponent(rbtnCI)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 165, Short.MAX_VALUE)
                                .addComponent(btnEditar)))
                        .addContainerGap())))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSalir)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rbtnNombre)
                    .addComponent(rbtnRol)
                    .addComponent(rbtnCI)
                    .addComponent(btnEditar))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 422, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btnSalir)
                .addContainerGap())
        );

        tabbGeneral.addTab("Usuarios", jPanel1);

        jLabel3.setText("Nombre");

        jLabel4.setText("Apellido");

        jLabel5.setText("Documento de identidad");

        jLabel6.setText("Email");

        jLabel7.setText("Fecha de nacimiento");

        jLabel8.setText("Contraseña");

        btnRegistrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/presentacion/imagenes/OK.png"))); // NOI18N
        btnRegistrar.setText("Registrar");
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });

        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/presentacion/imagenes/Save.png"))); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        jLabel10.setText("Estado");

        rbtgEstado.add(rbtnActivo);
        rbtnActivo.setSelected(true);
        rbtnActivo.setText("Activo");

        rbtgEstado.add(rbtnInactivo);
        rbtnInactivo.setText("Inactivo");

        jLabel1.setText("Rol");

        jLabel11.setText("Id usuario");

        btnLimpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/presentacion/imagenes/Undo.png"))); // NOI18N
        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        jLabel13.setText("Foto");

        pnlCamara.setBackground(new java.awt.Color(153, 153, 153));
        pnlCamara.setPreferredSize(new java.awt.Dimension(225, 220));

        javax.swing.GroupLayout pnlCamaraLayout = new javax.swing.GroupLayout(pnlCamara);
        pnlCamara.setLayout(pnlCamaraLayout);
        pnlCamaraLayout.setHorizontalGroup(
            pnlCamaraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 225, Short.MAX_VALUE)
        );
        pnlCamaraLayout.setVerticalGroup(
            pnlCamaraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 220, Short.MAX_VALUE)
        );

        lblFoto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lblFoto.setPreferredSize(new java.awt.Dimension(225, 220));

        btnIniciarCam.setIcon(new javax.swing.ImageIcon(getClass().getResource("/presentacion/imagenes/132207_camera_camera.png"))); // NOI18N
        btnIniciarCam.setText("Iniciar camara");
        btnIniciarCam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarCamActionPerformed(evt);
            }
        });

        btnApagarCam.setIcon(new javax.swing.ImageIcon(getClass().getResource("/presentacion/imagenes/Turn off.png"))); // NOI18N
        btnApagarCam.setText("Apagar camara");
        btnApagarCam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnApagarCamActionPerformed(evt);
            }
        });

        btnCapturaFoto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/presentacion/imagenes/Record.png"))); // NOI18N
        btnCapturaFoto.setText("Capturar");
        btnCapturaFoto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapturaFotoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnRegistrar)
                        .addGap(18, 18, 18)
                        .addComponent(btnLimpiar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnGuardar))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtContraseña)
                            .addComponent(txtCi)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel8)
                            .addComponent(txtNombre))
                        .addGap(39, 39, 39)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txtApellido, javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel4))
                        .addGap(32, 32, 32)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dcFechaNac, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(rbtnActivo)
                                        .addGap(18, 18, 18)
                                        .addComponent(rbtnInactivo))
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel7)
                                    .addComponent(cboRol, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11)
                                    .addComponent(txtIdUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel13)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(pnlCamara, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblFoto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnApagarCam, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnCapturaFoto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnIniciarCam, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(0, 140, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel1)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboRol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIdUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtCi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(dcFechaNac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtContraseña, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rbtnActivo)
                    .addComponent(rbtnInactivo))
                .addGap(18, 18, 18)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlCamara, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblFoto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnIniciarCam)
                        .addGap(18, 18, 18)
                        .addComponent(btnApagarCam)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCapturaFoto)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRegistrar)
                    .addComponent(btnGuardar)
                    .addComponent(btnLimpiar))
                .addGap(62, 62, 62))
        );

        tabbGeneral.addTab("Mantenimiento", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tabbGeneral)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tabbGeneral))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarActionPerformed

    }//GEN-LAST:event_txtBuscarActionPerformed

    private void tblListadoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblListadoMouseClicked


    }//GEN-LAST:event_tblListadoMouseClicked

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
        this.listado(txtBuscar.getText(), this.filtro);        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarKeyReleased

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
        String respuesta = "";
        String imagenActual = "";
        if (this.imagen.equals("")) {
            imagenActual = this.imagenAnt;
        } else {
            imagenActual = this.imagen;
        }

        String usuarioNombre = txtNombre.getText();
        String usuarioApellido = txtApellido.getText();
        String usuarioCI = txtCi.getText();
        String usuarioEmail = txtEmail.getText();
        String usuarioPassword = txtContraseña.getText();

        Rol rolSeleccionado = (Rol) cboRol.getSelectedItem();
        int rolId = rolSeleccionado.getIdRol();

        boolean estado;
        if (rbtnActivo.isSelected()) {
            estado = true;
        } else {
            estado = false;
        }

        Calendar cal;
        Date usuarioFechaNac;
        int d, m, a;
        try {
            cal = dcFechaNac.getCalendar();
            d = cal.get(Calendar.DAY_OF_MONTH);
            m = cal.get(Calendar.MONTH);
            a = cal.get(Calendar.YEAR) - 1900;
            usuarioFechaNac = new Date(a, m, d);
        } catch (Exception e) {
            mensajeError(e.getMessage());
            usuarioFechaNac = new Date(1970 - 1900, 00, 01);
        }

        respuesta = this.CONTROL.insertar(rolId, usuarioPassword, usuarioNombre, usuarioApellido, usuarioCI, usuarioEmail, usuarioFechaNac, imagenActual, estado);
        if (respuesta.equals("OK")) {
            if (!this.imagen.equals("")) {
                this.subirImagenes();
            }
            mensajeInformacion("Registro insertado");
        } else {
            mensajeError("Error insertando el registro");
        }
        limpiar();
        tabbGeneral.setSelectedIndex(0);
        this.listado("");

    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        limpiar();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if (mensajeConfirmacion("Desea editar este registro") == 0) {
            String respuesta = "";

            String imagenActual = "";
            if (this.imagen.equals("")) {
                imagenActual = this.imagenAnt;
            } else {
                imagenActual = this.imagen;
            }

            String usuarioNombre = txtNombre.getText();
            String usuarioApellido = txtApellido.getText();
            String usuarioCI = txtCi.getText();
            String usuarioEmail = txtEmail.getText();
            String usuarioPassword = txtContraseña.getText();

            Rol rolSeleccionado = (Rol) cboRol.getSelectedItem();
            int rolId = rolSeleccionado.getIdRol();
            int usuarioId = Integer.parseInt(txtIdUsuario.getText());

            boolean estado;
            if (rbtnActivo.isSelected()) {
                estado = true;
            } else {
                estado = false;
            }

            Calendar cal;
            Date usuarioFechaNac;
            int d, m, a;
            try {
                cal = dcFechaNac.getCalendar();
                d = cal.get(Calendar.DAY_OF_MONTH);
                m = cal.get(Calendar.MONTH);
                a = cal.get(Calendar.YEAR) - 1900;
                usuarioFechaNac = new Date(a, m, d);
            } catch (Exception e) {
                mensajeError(e.getMessage());
                usuarioFechaNac = new Date(1970 - 1900, 00, 01);
            }

//        String fecha = ((JTextField)dcFechaNac.getDateEditor().getUiComponent()).getText();
            respuesta = this.CONTROL.actualizar(usuarioId, rolId, usuarioPassword, usuarioNombre, usuarioApellido, usuarioCI, usuarioEmail, usuarioFechaNac, imagenActual, estado);
            if (respuesta.equals("OK")) {
                if (!this.imagen.equals("")) {
                    this.subirImagenes();
                }
                mensajeInformacion("Registro Actualizado");
            } else {
                mensajeError("Error actualizando el registro");
            }
            limpiar();
            tabbGeneral.setSelectedIndex(0);
            this.listado("");
        } else {
            limpiar();
            desactivar();
            tabbGeneral.setSelectedIndex(0);
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        if (tblListado.getSelectedRowCount() == 1) {
            int fila = tblListado.getSelectedRow();

            String usuarioId = String.valueOf(tblListado.getValueAt(fila, 0));
            String rolId = String.valueOf(tblListado.getValueAt(fila, 1));
            String rolNombre = String.valueOf(tblListado.getValueAt(fila, 2));
            String usuarioNombre = String.valueOf(tblListado.getValueAt(fila, 3));
            String usuarioApellido = String.valueOf(tblListado.getValueAt(fila, 4));
            String usuarioPassword = String.valueOf(tblListado.getValueAt(fila, 5));
            String usuarioCI = String.valueOf(tblListado.getValueAt(fila, 6));
            String usuarioEmail = String.valueOf(tblListado.getValueAt(fila, 7));
            String usuarioFoto = String.valueOf(tblListado.getValueAt(fila, 9));
            String estado = String.valueOf(tblListado.getValueAt(fila, 10));

            Date usuarioFechaNac = Date.valueOf(tblListado.getValueAt(fila, 8).toString());

            Rol rolSeleccionado = new Rol(Integer.parseInt(rolId), rolNombre);

            txtIdUsuario.setText(usuarioId);
            txtNombre.setText(usuarioNombre);
            txtApellido.setText(usuarioApellido);
            txtContraseña.setText(usuarioPassword);
            txtCi.setText(usuarioCI);
            txtEmail.setText(usuarioEmail);
//            txtFoto.setText(usuarioFoto);
            cboRol.setSelectedItem(rolSeleccionado);
            dcFechaNac.setDate(usuarioFechaNac);

            if (estado.equals("Activo")) {
                rbtnActivo.setSelected(true);
            } else {
                rbtnInactivo.setSelected(true);
            }

            ImageIcon im = new ImageIcon(this.DIRECTORIO + this.imagenAnt);
            Icon icono = new ImageIcon(im.getImage().getScaledInstance(lblFoto.getWidth(), lblFoto.getHeight(), Image.SCALE_DEFAULT));
            lblFoto.setIcon(icono);
            lblFoto.repaint();

            activar();
            tabbGeneral.setSelectedIndex(1);
        } else {
            mensajeError("Debe seleccionar almenos un registro");
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnIniciarCamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarCamActionPerformed
        // TODO add your handling code here:
        Thread hilo = new Thread() {
            @Override
            public void run() {
                webcamPanel.start();
            }
        };

        hilo.setDaemon(true);
        hilo.start();
        activa();
    }//GEN-LAST:event_btnIniciarCamActionPerformed

    private void btnApagarCamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnApagarCamActionPerformed
        // TODO add your handling code here:
        webcamPanel.stop();
        desactiva();
    }//GEN-LAST:event_btnApagarCamActionPerformed

    private void btnCapturaFotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapturaFotoActionPerformed
        // TODO add your handling code here:
        if (txtNombre.getText().isEmpty() || txtNombre.getText().length() > 40) {
            mensajeAlerta("Debes ingresar un nombre, menor a 40 caracteres");
            txtNombre.requestFocus();
            return;
        }
        if (txtCi.getText().isEmpty() || txtCi.getText().length() > 20) {
            mensajeAlerta("Debes ingresar un ci, menor a 20 caracteres");
            txtCi.requestFocus();
            return;
        }

        ImageIcon foto;
        this.imagen = CONTROL.generaNombreImagen(txtNombre.getText(), txtCi.getText()).trim() + ".jpg";
        foto = new ImageIcon(webcam.getImage());
        Icon iconoFoto = new ImageIcon(foto.getImage().getScaledInstance(lblFoto.getWidth(), lblFoto.getHeight(), Image.SCALE_SMOOTH));
        lblFoto.setIcon(iconoFoto);
        rutaWebCam = webcam.getImage();
    }//GEN-LAST:event_btnCapturaFotoActionPerformed

    private void rbtnNombreStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_rbtnNombreStateChanged
        seleccionarFiltro();
    }//GEN-LAST:event_rbtnNombreStateChanged

    private void rbtnRolStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_rbtnRolStateChanged
        seleccionarFiltro();
    }//GEN-LAST:event_rbtnRolStateChanged

    private void rbtnCIStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_rbtnCIStateChanged
        seleccionarFiltro();
    }//GEN-LAST:event_rbtnCIStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnApagarCam;
    private javax.swing.JButton btnCapturaFoto;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnIniciarCam;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JComboBox<String> cboRol;
    private com.toedter.calendar.JDateChooser dcFechaNac;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JLabel lblFoto;
    private javax.swing.JPanel pnlCamara;
    private javax.swing.ButtonGroup rbtgEstado;
    private javax.swing.ButtonGroup rbtgOpcionBusqueda;
    private javax.swing.JRadioButton rbtnActivo;
    private javax.swing.JRadioButton rbtnCI;
    private javax.swing.JRadioButton rbtnInactivo;
    private javax.swing.JRadioButton rbtnNombre;
    private javax.swing.JRadioButton rbtnRol;
    private javax.swing.JTabbedPane tabbGeneral;
    private javax.swing.JTable tblListado;
    private javax.swing.JTextField txtApellido;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtCi;
    private javax.swing.JPasswordField txtContraseña;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtIdUsuario;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
