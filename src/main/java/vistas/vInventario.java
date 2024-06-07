
package vistas;

import DAOs.DAO_Categorias;
import DAOs.DAO_Detalles_procesos;
import DAOs.DAO_Productos;
import DAOs.DAO_Proveedores;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import modelo.Categorias;
import modelo.Detalles_procesos;
import modelo.Productos;
import modelo.Proveedores;

public class vInventario extends javax.swing.JFrame {
           
    private DAO_Productos daoProductos;
    private DAO_Categorias daoCategorias;
    private DAO_Proveedores daoProveedores;
    private DAO_Detalles_procesos daoDetallesProcesos;

    String encabezados_productos[] = {"Producto ID", "Nombre", "Precio", "Stock", "Foto", "Categoria ID"};
    String encabezados_categorias[] = {"Categoria ID", "Nombre Categoria", "Descripción"};
    String encabezados_proveedores[] = {"Proveedor ID", "Nombre Proveedor"};
    String encabezados_detalles_procesos[] = {"Detalle Proceso ID", "Producto ID", "Proceso ID"};

    DefaultTableModel modeloProductos = new DefaultTableModel(encabezados_productos, 0);
    DefaultTableModel modeloCategorias = new DefaultTableModel(encabezados_categorias, 0);
    DefaultTableModel modeloProveedores = new DefaultTableModel(encabezados_proveedores, 0);
    DefaultTableModel modeloDetallesProcesos = new DefaultTableModel(encabezados_detalles_procesos, 0);

    private int categoriaSeleccionadaID;

    public vInventario() {
        initComponents();
        this.daoProductos = new DAO_Productos();
        this.daoCategorias = new DAO_Categorias();
        this.daoProveedores = new DAO_Proveedores();
        this.daoDetallesProcesos = new DAO_Detalles_procesos();

        txtProductos_nombre.setEnabled(false);
        txtProductos_precio.setEnabled(false);
        cantidad.setEnabled(false);
        txtProductos_foto.setEnabled(false);
        txtProductos_categoriaid.setEnabled(false);

        CargarTablaProductos();
        CargarTablaCategorias();
        CargarTablaProveedores();
        CargarTablaDetallesProcesos();
    
        
        // Agregar el ListSelectionListener a la tabla de proveedores
        tblProveedores.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event) {
                int filaSeleccionada = tblProveedores.getSelectedRow();
                if (filaSeleccionada != -1) {
                    // Si se selecciona una fila, habilitar los botones de eliminar y modificar
                    btnProveedor_eliminar.setEnabled(true);
                    btnProveedor_modificar.setEnabled(true);
                    btnProveedor_confirmar.setEnabled(true);
                    
                } else {
                    // Si no se selecciona ninguna fila, deshabilitar los botones de eliminar y modificar
                    btnProveedor_eliminar.setEnabled(false);
                    btnProveedor_modificar.setEnabled(false);
                    btnProveedor_confirmar.setEnabled(false);
                }
            }
        });


        // Agregar el ListSelectionListener a la tabla de categorías
        tblCategorias.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event) {
                int filaSeleccionada = tblCategorias.getSelectedRow();
                if (filaSeleccionada != -1) {
                    DefaultTableModel modelo = (DefaultTableModel) tblCategorias.getModel();
                    categoriaSeleccionadaID = Integer.parseInt(modelo.getValueAt(filaSeleccionada, 0).toString());
                    txtCategoria_modificar_nombre.setText(modelo.getValueAt(filaSeleccionada, 1).toString());
                    txtCategoria_modificar_descripcion.setText(modelo.getValueAt(filaSeleccionada, 2).toString());

                    txtCategoria_modificar_nombre.setEnabled(false);
                    txtCategoria_modificar_descripcion.setEnabled(false);
                    btnCategoria_modificar.setEnabled(true);
                } else {
                    btnCategoria_modificar.setEnabled(false);
                }

                if (tblCategorias.getSelectedRow() != -1) {
                    btnCategorias_eliminar.setEnabled(true);
                } else {
                    btnCategorias_eliminar.setEnabled(false);
                }
            }
        });
    }
    

        private void CargarTablaProductos() {
            ArrayList<Object[]> datosProductos = this.daoProductos.seleccionar();
            modeloProductos.setNumRows(0);
            for (Object[] fila : datosProductos) {
                modeloProductos.addRow(fila);
            }
            this.tblProductos.setModel(modeloProductos);
        }

        private void CargarTablaCategorias() {
            ArrayList<Object[]> datosCategorias = this.daoCategorias.seleccionar();
            modeloCategorias.setNumRows(0);
            for (Object[] fila : datosCategorias) {
                modeloCategorias.addRow(fila);
            }
            this.tblCategorias.setModel(modeloCategorias);
        }

        private void CargarTablaProveedores() {
            ArrayList<Object[]> datosProveedores = this.daoProveedores.seleccionar();
            modeloProveedores.setNumRows(0);
            for (Object[] fila : datosProveedores) {
                modeloProveedores.addRow(fila);
            }
            this.tblProveedores.setModel(modeloProveedores);
        }
        private void CargarTablaDetallesProcesos() {
        ArrayList<Object[]> datosDetallesProcesos = (ArrayList<Object[]>) this.daoDetallesProcesos.seleccionar();
        modeloDetallesProcesos.setNumRows(0);
        for (Object[] fila : datosDetallesProcesos) {
            modeloDetallesProcesos.addRow(fila);
        }
        this.tblRegistros.setModel(modeloDetallesProcesos);
    }
        

       /*
        private void CargarTablaDetallesProcesos() {
        ArrayList<Object[]> datosDetallesProcesos = (ArrayList<Object[]>) this.daoDetallesProcesos.seleccionar();
        modeloRegistros.setNumRows(0);
        for (Object[] fila : datosDetallesProcesos) {
            modeloRegistros.addRow(fila);
        }
        this.tblRegistros.setModel(modeloRegistros);
    }*/

        private void tblProductosMouseClicked(java.awt.event.MouseEvent evt) {
            int filaSeleccionada = tblProductos.getSelectedRow();
            DefaultTableModel modelo = (DefaultTableModel) tblProductos.getModel();
            txtProductos_productoid.setText(modelo.getValueAt(filaSeleccionada, 0).toString());
            txtProductos_nombre.setText(modelo.getValueAt(filaSeleccionada, 1).toString());
            txtProductos_precio.setText(modelo.getValueAt(filaSeleccionada, 2).toString());
            cantidad.setText(modelo.getValueAt(filaSeleccionada, 3).toString());
            txtProductos_foto.setText(modelo.getValueAt(filaSeleccionada, 4).toString());
            txtProductos_categoriaid.setText(modelo.getValueAt(filaSeleccionada, 5).toString());
        }
        

    private void tblCategoriasMouseClicked(java.awt.event.MouseEvent evt) {
        int filaSeleccionada = tblCategorias.getSelectedRow();
        if (filaSeleccionada != -1) {
            
            txtCategoria_modificar_nombre.setEnabled(false);
            txtCategoria_modificar_descripcion.setEnabled(false);
            
            DefaultTableModel modelo = (DefaultTableModel) tblCategorias.getModel();
            categoriaSeleccionadaID = Integer.parseInt(modelo.getValueAt(filaSeleccionada, 0).toString());
            txtCategoria_modificar_nombre.setText(modelo.getValueAt(filaSeleccionada, 1).toString());
            txtCategoria_modificar_descripcion.setText(modelo.getValueAt(filaSeleccionada, 2).toString());

            
        } else {
            btnCategoria_modificar.setEnabled(false);
        }
        
        
    }
    

    
    /*
    
        String ecabezados_productos [] = {"Producto ID", "Nombre", "Precio", "Stock", "Foto", "Categoria ID"};
        DefaultTableModel modelo = new DefaultTableModel(ecabezados_productos, 0);

        DAOs.DAO_Productos dao = new DAOs.DAO_Productos();

        ArrayList<Object[]> datos = dao.seleccionar();


        public vInventario() {
            initComponents();
            CargarTabla();
        }

        
        
        private void CargarTabla(){

            this.datos = this.dao.seleccionar();
            this.modelo.setNumRows(0);
            for(Object[] fila: this.datos){
                this.modelo.addRow(fila);

            }
            this.tblProductos.setModel(modelo);
        }
    */
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        popProductos_agregar = new javax.swing.JPopupMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblRegistros = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblProveedores = new javax.swing.JTable();
        lblProveedor_panel_de_control = new javax.swing.JLabel();
        lblInformacion_Proveedores1 = new javax.swing.JLabel();
        lblProveedor_nombre = new javax.swing.JLabel();
        btnProveedor_confirmar = new javax.swing.JButton();
        txtProveedor_nombre = new javax.swing.JTextField();
        btnProveedor_eliminar = new javax.swing.JButton();
        btnProveedor_modificar = new javax.swing.JButton();
        btnProveedor_agregar_confirmar = new javax.swing.JButton();
        lblProveedor_agregar_agregar = new javax.swing.JLabel();
        txtProveedor_agregar_nombre = new javax.swing.JTextField();
        lblProveedor_agregar_nombre = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblCategorias = new javax.swing.JTable();
        lblCategorias_panel_de_control = new javax.swing.JLabel();
        lblInformacion_categorias1 = new javax.swing.JLabel();
        btnCategorias_eliminar = new javax.swing.JButton();
        btnCategoria_modificar = new javax.swing.JButton();
        btnCategoria_agregar_confirmar = new javax.swing.JButton();
        txtCategoria_agregar_nombre = new javax.swing.JTextField();
        txtCategoria_agregar_descripcion = new javax.swing.JTextField();
        lblCategoria_agregar_nombre = new javax.swing.JLabel();
        lblCategoria_agregar_descripcion = new javax.swing.JLabel();
        lblCategoria_agregar = new javax.swing.JLabel();
        txtCategoria_modificar_nombre = new javax.swing.JTextField();
        txtCategoria_modificar_descripcion = new javax.swing.JTextField();
        lblCategoria_modificar_nombre = new javax.swing.JLabel();
        lblCategoria_modificar_descripcion = new javax.swing.JLabel();
        btnCategoria_modificar_confirmar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProductos = new javax.swing.JTable();
        btnProductos_agregar = new javax.swing.JButton();
        lblInformacion_productos = new javax.swing.JLabel();
        lblPanel_de_control_productos = new javax.swing.JLabel();
        btnProductos_eliminar = new javax.swing.JButton();
        btnProductos_modificar = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        lblProducto_busqueda = new javax.swing.JLabel();
        txtProducto_productoid = new javax.swing.JTextField();
        lblProducto_busqueda_productoid = new javax.swing.JLabel();
        lblProducto_busqueda_nombre = new javax.swing.JLabel();
        txtProducto_categoriaid = new javax.swing.JTextField();
        lblProducto_busqueda_categoriaid = new javax.swing.JLabel();
        txtProducto_nombre = new javax.swing.JTextField();
        txtProductos_productoid = new javax.swing.JTextField();
        txtProductos_nombre = new javax.swing.JTextField();
        lblProducto_productoid = new javax.swing.JLabel();
        lblProducto_nombre = new javax.swing.JLabel();
        lblProducto_precio = new javax.swing.JLabel();
        lblProducto_cantidad = new javax.swing.JLabel();
        lblProducto_foto = new javax.swing.JLabel();
        txtProductos_precio = new javax.swing.JTextField();
        cantidad = new javax.swing.JTextField();
        txtProductos_foto = new javax.swing.JTextField();
        btnProductos_busqueda_confirmar = new javax.swing.JButton();
        txtProductos_categoriaid = new javax.swing.JTextField();
        lblProducto_categoriaid = new javax.swing.JLabel();

        jMenuItem1.setText("jMenuItem1");
        popProductos_agregar.add(jMenuItem1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tblRegistros.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane4.setViewportView(tblRegistros);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 928, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(232, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(103, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 495, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Registros", jPanel4);

        tblProveedores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(tblProveedores);

        lblProveedor_panel_de_control.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblProveedor_panel_de_control.setText("Panel de control");

        lblInformacion_Proveedores1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblInformacion_Proveedores1.setText("Informacion de Proveedores");

        lblProveedor_nombre.setText("Nombre");

        btnProveedor_confirmar.setText("Confirmar");
        btnProveedor_confirmar.setEnabled(false);
        btnProveedor_confirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProveedor_confirmarActionPerformed(evt);
            }
        });

        txtProveedor_nombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtProveedor_nombreActionPerformed(evt);
            }
        });

        btnProveedor_eliminar.setText("Eliminar");
        btnProveedor_eliminar.setEnabled(false);
        btnProveedor_eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProveedor_eliminarActionPerformed(evt);
            }
        });

        btnProveedor_modificar.setText("Modificar");
        btnProveedor_modificar.setEnabled(false);
        btnProveedor_modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProveedor_modificarActionPerformed(evt);
            }
        });

        btnProveedor_agregar_confirmar.setText("Confirmar");
        btnProveedor_agregar_confirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProveedor_agregar_confirmarActionPerformed(evt);
            }
        });

        lblProveedor_agregar_agregar.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblProveedor_agregar_agregar.setText("Agregar");

        txtProveedor_agregar_nombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtProveedor_agregar_nombreActionPerformed(evt);
            }
        });

        lblProveedor_agregar_nombre.setText("Nombre");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(301, 301, 301)
                        .addComponent(lblInformacion_Proveedores1))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 867, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(lblProveedor_panel_de_control)
                                .addGap(84, 84, 84))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(lblProveedor_agregar_nombre)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtProveedor_agregar_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(lblProveedor_nombre)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtProveedor_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(btnProveedor_agregar_confirmar)
                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addComponent(btnProveedor_eliminar)
                                                .addGap(82, 82, 82)
                                                .addComponent(btnProveedor_modificar))
                                            .addComponent(btnProveedor_confirmar))))
                                .addGap(51, 51, 51))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(107, 107, 107)
                        .addComponent(lblProveedor_agregar_agregar)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(lblInformacion_Proveedores1))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(lblProveedor_panel_de_control)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 458, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnProveedor_eliminar)
                                    .addComponent(btnProveedor_modificar))
                                .addGap(43, 43, 43)
                                .addComponent(txtProveedor_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblProveedor_nombre))
                        .addGap(31, 31, 31)
                        .addComponent(btnProveedor_confirmar)
                        .addGap(18, 18, 18)
                        .addComponent(lblProveedor_agregar_agregar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblProveedor_agregar_nombre)
                            .addComponent(txtProveedor_agregar_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31)
                        .addComponent(btnProveedor_agregar_confirmar)
                        .addGap(181, 181, 181))))
        );

        jTabbedPane1.addTab("Proveedores", jPanel3);

        tblCategorias.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tblCategorias);

        lblCategorias_panel_de_control.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblCategorias_panel_de_control.setText("Panel de control");

        lblInformacion_categorias1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblInformacion_categorias1.setText("Informacion de Categorias");

        btnCategorias_eliminar.setText("Eliminar");
        btnCategorias_eliminar.setEnabled(false);
        btnCategorias_eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCategorias_eliminarActionPerformed(evt);
            }
        });

        btnCategoria_modificar.setText("Modificar");
        btnCategoria_modificar.setEnabled(false);
        btnCategoria_modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCategoria_modificarActionPerformed(evt);
            }
        });

        btnCategoria_agregar_confirmar.setText("Confirmar");
        btnCategoria_agregar_confirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCategoria_agregar_confirmarActionPerformed(evt);
            }
        });

        txtCategoria_agregar_nombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCategoria_agregar_nombreActionPerformed(evt);
            }
        });

        txtCategoria_agregar_descripcion.setColumns(3);
        txtCategoria_agregar_descripcion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCategoria_agregar_descripcionActionPerformed(evt);
            }
        });

        lblCategoria_agregar_nombre.setText("Nombre");

        lblCategoria_agregar_descripcion.setText("Descipcion");

        lblCategoria_agregar.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblCategoria_agregar.setText("Agregar");

        txtCategoria_modificar_nombre.setColumns(3);
        txtCategoria_modificar_nombre.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtCategoria_modificar_nombre.setDragEnabled(true);
        txtCategoria_modificar_nombre.setEnabled(false);
        txtCategoria_modificar_nombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCategoria_modificar_nombreActionPerformed(evt);
            }
        });

        txtCategoria_modificar_descripcion.setColumns(3);
        txtCategoria_modificar_descripcion.setToolTipText("");
        txtCategoria_modificar_descripcion.setEnabled(false);
        txtCategoria_modificar_descripcion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCategoria_modificar_descripcionActionPerformed(evt);
            }
        });

        lblCategoria_modificar_nombre.setText("Nombre");

        lblCategoria_modificar_descripcion.setText("Descripcion");

        btnCategoria_modificar_confirmar.setText("Confirmar");
        btnCategoria_modificar_confirmar.setEnabled(false);
        btnCategoria_modificar_confirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCategoria_modificar_confirmarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(275, 275, 275)
                        .addComponent(lblInformacion_categorias1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblCategorias_panel_de_control)
                        .addGap(12, 12, 12))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 921, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtCategoria_agregar_descripcion, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(btnCategorias_eliminar)
                                            .addComponent(lblCategoria_modificar_nombre)
                                            .addComponent(lblCategoria_modificar_descripcion))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(btnCategoria_modificar, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(txtCategoria_modificar_nombre, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(txtCategoria_modificar_descripcion)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(lblCategoria_agregar_nombre)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtCategoria_agregar_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(btnCategoria_agregar_confirmar)
                                            .addComponent(lblCategoria_agregar_descripcion)
                                            .addComponent(btnCategoria_modificar_confirmar))
                                        .addGap(0, 0, Short.MAX_VALUE))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblCategoria_agregar)
                                .addGap(69, 69, 69)))))
                .addGap(24, 24, 24))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblInformacion_categorias1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCategorias_panel_de_control, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 53, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnCategorias_eliminar)
                            .addComponent(btnCategoria_modificar))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCategoria_modificar_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblCategoria_modificar_nombre))
                        .addGap(18, 18, 18)
                        .addComponent(lblCategoria_modificar_descripcion)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCategoria_modificar_descripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22)
                        .addComponent(btnCategoria_modificar_confirmar)
                        .addGap(28, 28, 28)
                        .addComponent(lblCategoria_agregar, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblCategoria_agregar_nombre)
                            .addComponent(txtCategoria_agregar_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblCategoria_agregar_descripcion)
                        .addGap(12, 12, 12)
                        .addComponent(txtCategoria_agregar_descripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(btnCategoria_agregar_confirmar))
                    .addComponent(jScrollPane2))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Categoria", jPanel2);

        tblProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblProductos);

        btnProductos_agregar.setText("Agregar");
        btnProductos_agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProductos_agregarActionPerformed(evt);
            }
        });

        lblInformacion_productos.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblInformacion_productos.setText("Informacion de Productos");

        lblPanel_de_control_productos.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblPanel_de_control_productos.setText("Panel de control");

        btnProductos_eliminar.setText("Eliminar");
        btnProductos_eliminar.setEnabled(false);
        btnProductos_eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProductos_eliminarActionPerformed(evt);
            }
        });

        btnProductos_modificar.setText("Modificar");
        btnProductos_modificar.setEnabled(false);
        btnProductos_modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProductos_modificarActionPerformed(evt);
            }
        });

        jButton3.setText("jButton3");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        lblProducto_busqueda.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblProducto_busqueda.setText("Busqueda");

        txtProducto_productoid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtProducto_productoidActionPerformed(evt);
            }
        });

        lblProducto_busqueda_productoid.setText("Producto ID");

        lblProducto_busqueda_nombre.setText("Nombre");

        lblProducto_busqueda_categoriaid.setText("Categoria");

        txtProductos_productoid.setEnabled(false);
        txtProductos_productoid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtProductos_productoidActionPerformed(evt);
            }
        });

        txtProductos_nombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtProductos_nombreActionPerformed(evt);
            }
        });

        lblProducto_productoid.setText("Producto ID");

        lblProducto_nombre.setText("Nombre");

        lblProducto_precio.setText("Precio");

        lblProducto_cantidad.setText("Cantidad");

        lblProducto_foto.setText("Foto");

        txtProductos_precio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtProductos_precioActionPerformed(evt);
            }
        });

        cantidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cantidadActionPerformed(evt);
            }
        });

        txtProductos_foto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtProductos_fotoActionPerformed(evt);
            }
        });

        btnProductos_busqueda_confirmar.setText("Confirmar");
        btnProductos_busqueda_confirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProductos_busqueda_confirmarActionPerformed(evt);
            }
        });

        txtProductos_categoriaid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtProductos_categoriaidActionPerformed(evt);
            }
        });

        lblProducto_categoriaid.setText("ID Categoria");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(265, 265, 265)
                .addComponent(lblInformacion_productos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblPanel_de_control_productos)
                .addGap(92, 92, 92))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 824, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(86, 86, 86)
                        .addComponent(lblProducto_busqueda)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                        .addComponent(btnProductos_busqueda_confirmar)
                        .addGap(232, 232, 232))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lblProducto_categoriaid)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtProductos_categoriaid, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnProductos_modificar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblProducto_productoid)
                                    .addComponent(lblProducto_busqueda_productoid)
                                    .addComponent(lblProducto_busqueda_nombre)
                                    .addComponent(lblProducto_busqueda_categoriaid)
                                    .addComponent(btnProductos_agregar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(28, 28, 28)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtProducto_productoid, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                                            .addComponent(txtProducto_nombre)
                                            .addComponent(txtProducto_categoriaid))
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtProductos_productoid, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(btnProductos_eliminar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(lblProducto_precio)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtProductos_precio, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(lblProducto_nombre)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtProductos_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(lblProducto_cantidad)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(lblProducto_foto)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtProductos_foto, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(lblInformacion_productos, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(lblPanel_de_control_productos, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnProductos_eliminar)
                            .addComponent(btnProductos_agregar))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnProductos_modificar)
                            .addComponent(jButton3))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblProducto_productoid)
                            .addComponent(txtProductos_productoid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblProducto_nombre)
                            .addComponent(txtProductos_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblProducto_precio)
                            .addComponent(txtProductos_precio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblProducto_cantidad)
                            .addComponent(cantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtProductos_foto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblProducto_foto))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblProducto_categoriaid)
                            .addComponent(txtProductos_categoriaid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                        .addComponent(lblProducto_busqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblProducto_busqueda_productoid)
                            .addComponent(txtProducto_productoid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblProducto_busqueda_nombre)
                            .addComponent(txtProducto_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtProducto_categoriaid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblProducto_busqueda_categoriaid))
                        .addGap(18, 18, 18)
                        .addComponent(btnProductos_busqueda_confirmar)
                        .addGap(30, 30, 30))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())))
        );

        jTabbedPane1.addTab("Producto", jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnProductos_agregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProductos_agregarActionPerformed
        // Habilitar los campos de texto
        txtProductos_nombre.setEnabled(true);
        txtProductos_precio.setEnabled(true);
        cantidad.setEnabled(true);
        txtProductos_foto.setEnabled(true);
        txtProductos_categoriaid.setEnabled(true);

        // Limpiar los campos de texto
        txtProductos_nombre.setText("");
        txtProductos_precio.setText("");
        cantidad.setText("");
        txtProductos_foto.setText("");
        txtProductos_categoriaid.setText("");
    }

    private boolean esNumero(String texto) {
        try {
            Integer.parseInt(texto);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }//GEN-LAST:event_btnProductos_agregarActionPerformed

    private void btnProductos_modificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProductos_modificarActionPerformed
        
        int productoID = Integer.parseInt(txtProductos_productoid.getText());

        String nombre = txtProductos_nombre.getText();
        float precio = Float.parseFloat(txtProductos_precio.getText());
        int stock = Integer.parseInt(cantidad.getText());
        String foto = txtProductos_foto.getText();
        int categoriaID = Integer.parseInt(txtProductos_categoriaid.getText());

        // Crear un nuevo objeto Producto con los datos modificados
        Productos productoModificado = new Productos(productoID, nombre, precio, stock, foto, categoriaID);

        // Actualizar el producto en la base de datos utilizando el DAO_Productos
        DAO_Productos daoProductos = new DAO_Productos();
        boolean modificado = daoProductos.modificar(productoModificado);

        // Mostrar mensaje status
        if (modificado) {
            JOptionPane.showMessageDialog(null, "Producto modificado correctamente");
            CargarTablaProductos();
        } else {
            // Verificar si el producto que se intenta modificar existe en la base de datos
            if (!daoProductos.existeProducto(productoID)) {
                JOptionPane.showMessageDialog(null, "El producto con ID " + productoID + " no existe en la base de datos");
            } else {
                JOptionPane.showMessageDialog(null, "Error al modificar el producto");
            }
        }
    }//GEN-LAST:event_btnProductos_modificarActionPerformed

    
    
    private void btnProductos_eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProductos_eliminarActionPerformed

        // Obtener el indice de la fila seleccionada
        int filaSeleccionada = tblProductos.getSelectedRow();

        // Verificar si hay alguna fila seleccionada en la tabla de productos
        if (filaSeleccionada != -1) {
            // Obtener el ID del producto a eliminar
            int productoID = Integer.parseInt(modeloProductos.getValueAt(filaSeleccionada, 0).toString());

            // Verificar si el producto existe antes de intentar eliminarlo
            DAO_Productos daoProductos = new DAO_Productos();
            if (daoProductos.existeProducto(productoID)) {
                // Si el producto existe eliminacion
                boolean eliminado = daoProductos.eliminar(productoID);
                if (eliminado) {
                    JOptionPane.showMessageDialog(null, "Producto eliminado correctamente");
                    CargarTablaProductos();
                } else {
                    JOptionPane.showMessageDialog(null, "Error al eliminar el producto");
                }
            } else {
                // Si el producto no existe mostrar un mensaje 
                JOptionPane.showMessageDialog(null, "El producto con ID " + productoID + " no existe");
            }
        } else {

        }
    

    }//GEN-LAST:event_btnProductos_eliminarActionPerformed

    private void btnProductos_busqueda_confirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProductos_busqueda_confirmarActionPerformed
        // Obtener los valores de los campos de búsqueda
        String nombre = txtProducto_nombre.getText();
        String productoIDText = txtProducto_productoid.getText();
        String categoriaIDText = txtProducto_categoriaid.getText();

        // Validar si los campos están vacios
        boolean filtroVacio = nombre.isEmpty() && productoIDText.isEmpty() && categoriaIDText.isEmpty();

        // Declarar las variables para almacenar los valores convertidos a int
        int productoID = 0;
        int categoriaID = 0;

        // Verificar si se ha proporcionado un valor para el ID del producto y si es un numero valido
        if (!productoIDText.isEmpty()) {
            try {
                productoID = Integer.parseInt(productoIDText);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "El ID del producto debe ser un numero entero valido.");
                return;
            }
        }

        // Verificar si se ha proporcionado un valor para la categoría y si es un numero valido
        if (!categoriaIDText.isEmpty()) {
            try {
                categoriaID = Integer.parseInt(categoriaIDText);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "El ID de la categoría debe ser un número entero válido.");
                return; 
            }
        }

        // Realizar la busqueda en la base de datos
        ArrayList<Object[]> datosFiltrados = daoProductos.filtrarProductos(nombre, productoID, categoriaID);

        // Actualizar la tabla de productos con los resultados de la busqueda
        DefaultTableModel modelo = (DefaultTableModel) tblProductos.getModel();
        modelo.setRowCount(0); // Limpiar la tabla antes de agregar los nuevos datos
        for (Object[] fila : datosFiltrados) {
            modelo.addRow(fila);
        }

        // Mostrar un mensaje si no se encontraron resultados
        if (datosFiltrados.isEmpty() && !filtroVacio) {
            JOptionPane.showMessageDialog(null, "No se encontraron productos que coincidan con los criterios de búsqueda.");
        }
    }//GEN-LAST:event_btnProductos_busqueda_confirmarActionPerformed
    
    
    
    
    
    private void txtProducto_productoidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtProducto_productoidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtProducto_productoidActionPerformed

    private void txtProductos_nombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtProductos_nombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtProductos_nombreActionPerformed

    private void txtProductos_precioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtProductos_precioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtProductos_precioActionPerformed

    private void cantidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cantidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cantidadActionPerformed

    private void txtProductos_fotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtProductos_fotoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtProductos_fotoActionPerformed

    private void txtProductos_categoriaidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtProductos_categoriaidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtProductos_categoriaidActionPerformed
    private void listaCategoriasValueChanged(javax.swing.event.ListSelectionEvent evt) {
        
    }
    private void btnCategorias_eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCategorias_eliminarActionPerformed
        int filaSeleccionada = tblCategorias.getSelectedRow();
        if (filaSeleccionada != -1) {
            try {
                // Obtener el ID de la categoria seleccionada
                int categoriaID = (int) tblCategorias.getValueAt(filaSeleccionada, 0);

                // Crear un objeto de Categorias con el ID de la categoría seleccionada
                Categorias categoriaAEliminar = new Categorias();
                categoriaAEliminar.setCategoria_id(categoriaID);

                // Llamar del DAO para eliminar la categoria
                boolean eliminado = daoCategorias.eliminar(categoriaAEliminar);

                // Verificar si la eliminacion fue exitosa
                if (eliminado) {
                    JOptionPane.showMessageDialog(null, "Categoria eliminada correctamente.");

                    CargarTablaCategorias();
                } else {
                    JOptionPane.showMessageDialog(null, "Error al eliminar la categoria.");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error al eliminar la categoria: " + ex.getMessage());
            }
        } else {
        }
    }//GEN-LAST:event_btnCategorias_eliminarActionPerformed

    private void btnCategoria_modificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCategoria_modificarActionPerformed
        // Verificar si hay una categoria seleccionada en la tabla
        int filaSeleccionada = tblCategorias.getSelectedRow();
        if (filaSeleccionada != -1) {
            DefaultTableModel modelo = (DefaultTableModel) tblCategorias.getModel();
            // Obtener los datos de la fila seleccionada y cargarlos en los campos de texto
            txtCategoria_modificar_nombre.setText(modelo.getValueAt(filaSeleccionada, 1).toString());
            txtCategoria_modificar_descripcion.setText(modelo.getValueAt(filaSeleccionada, 2).toString());

            // Habilitar los campos de texto para permitir la edicion
            txtCategoria_modificar_nombre.setEnabled(true);
            txtCategoria_modificar_descripcion.setEnabled(true);

            // Activar el boton Confirmar
            btnCategoria_modificar_confirmar.setEnabled(true);
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione una categoría para modificar.");
        }
    }//GEN-LAST:event_btnCategoria_modificarActionPerformed

    private void txtCategoria_modificar_nombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCategoria_modificar_nombreActionPerformed

    }//GEN-LAST:event_txtCategoria_modificar_nombreActionPerformed

    private void txtCategoria_modificar_descripcionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCategoria_modificar_descripcionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCategoria_modificar_descripcionActionPerformed

    private void btnCategoria_modificar_confirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCategoria_modificar_confirmarActionPerformed
        // Obtener los nuevos valores del texto txtCategoria_modificar_nombre, txtCategoria_modificar_descripcion
        String nuevoNombre = txtCategoria_modificar_nombre.getText();
        String nuevaDescripcion = txtCategoria_modificar_descripcion.getText();

        // Obtener la fila seleccionada en la tabla de categorias
        int filaSeleccionada = tblCategorias.getSelectedRow();

        // Verificar si se ha seleccionado una fila
        if (filaSeleccionada != -1) {
            try {
                // Obtener el ID de la categoria seleccionada
                int categoriaID = (int) tblCategorias.getValueAt(filaSeleccionada, 0);

                // Crear un objeto de Categorias con los nuevos valores
                Categorias categoriaModificada = new Categorias();
                categoriaModificada.setCategoria_id(categoriaID);
                categoriaModificada.setNombre_categoria(nuevoNombre);
                categoriaModificada.setDescripcion(nuevaDescripcion);

                // Llamar al DAO para modificar la categoría en la base de datos
                boolean modificado = daoCategorias.modificar(categoriaModificada);

                // Verificar si la modificacion fue exitosa
                if (modificado) {
                    JOptionPane.showMessageDialog(null, "Categoria modificada correctamente.");

                    // Deshabilitar los campos de texto
                    txtCategoria_modificar_nombre.setEnabled(false);
                    txtCategoria_modificar_descripcion.setEnabled(false);

                    // Desactivar el botón Confirmar
                    btnCategoria_modificar_confirmar.setEnabled(false);

                    // Limpiar los campos de texto
                    txtCategoria_modificar_nombre.setText("");
                    txtCategoria_modificar_descripcion.setText("");

                    // Actualizar la tabla de categorias para reflejar los cambios
                    CargarTablaCategorias();
                } else {
                    JOptionPane.showMessageDialog(null, "Error al modificar la categoria.");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error al modificar la categoria: " + ex.getMessage());
            }
        } else {
        }
    
    
    }//GEN-LAST:event_btnCategoria_modificar_confirmarActionPerformed

    private void btnCategoria_agregar_confirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCategoria_agregar_confirmarActionPerformed
        // Capturar la información del usuario desde los campos de texto
        String nombreCategoria = txtCategoria_agregar_nombre.getText();
        String descripcionCategoria = txtCategoria_agregar_descripcion.getText();

        // Verificar si tanto el nombre como la descripción están presentes
        if (nombreCategoria.isEmpty() || descripcionCategoria.isEmpty()) {
            // Si alguno de los campos está vacío, mostrar un mensaje de error y salir del método
            JOptionPane.showMessageDialog(null, "Debe ingresar tanto el nombre como la descripción de la categoría.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Crear un objeto Categorias con la información capturada
        Categorias nuevaCategoria = new Categorias();
        nuevaCategoria.setNombre_categoria(nombreCategoria);
        nuevaCategoria.setDescripcion(descripcionCategoria);

        // Llamar al método insertar del DAO para guardar la nueva categoría
        boolean insertado = daoCategorias.insertar(nuevaCategoria);

        if (insertado) {
            // Si se insertó correctamente, actualizar la tabla de categorías
            CargarTablaCategorias();
            // Limpiar los campos de texto después de insertar
            txtCategoria_agregar_nombre.setText("");
            txtCategoria_agregar_descripcion.setText("");
            // Opcional: mostrar un mensaje de éxito al usuario
            JOptionPane.showMessageDialog(null, "Categoría agregada correctamente.");
        } else {
            // Si ocurrió un error al insertar, mostrar un mensaje de error al usuario
            JOptionPane.showMessageDialog(null, "Error al insertar la categoría.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    
    }//GEN-LAST:event_btnCategoria_agregar_confirmarActionPerformed

    private void txtCategoria_agregar_nombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCategoria_agregar_nombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCategoria_agregar_nombreActionPerformed

    private void txtCategoria_agregar_descripcionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCategoria_agregar_descripcionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCategoria_agregar_descripcionActionPerformed
    private int categoriaSeleccionadaIDCombo;
    private void btnProveedor_eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProveedor_eliminarActionPerformed
        int filaSeleccionada = tblProveedores.getSelectedRow();
        if (filaSeleccionada != -1) {
            try {
                // Obtener el ID del proveedor seleccionado
                int proveedorID = (int) tblProveedores.getValueAt(filaSeleccionada, 0);

                // Crear un objeto de Proveedores con el ID del proveedor seleccionado
                Proveedores proveedorAEliminar = new Proveedores();
                proveedorAEliminar.setProveedor_id(proveedorID);

                // Llamar al DAO para eliminar el proveedor
                boolean eliminado = daoProveedores.eliminar(proveedorAEliminar);

                // Verificar si la eliminación fue exitosa
                if (eliminado) {
                    JOptionPane.showMessageDialog(null, "Proveedor eliminado correctamente.");

                    // Actualizar la tabla de proveedores para reflejar los cambios
                    CargarTablaProveedores();
                } else {
                    JOptionPane.showMessageDialog(null, "Error al eliminar el proveedor.");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error al eliminar el proveedor: " + ex.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione un proveedor para eliminar.");
        }

    }//GEN-LAST:event_btnProveedor_eliminarActionPerformed

    private void btnProveedor_agregar_confirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProveedor_agregar_confirmarActionPerformed
        // Capturar la información del usuario desde los campos de texto
        String nombreProveedor = txtProveedor_agregar_nombre.getText();

        // Verificar si el nombre del proveedor está presente
        if (nombreProveedor.isEmpty()) {
            // Si el campo está vacío, mostrar un mensaje de error y salir del método
            JOptionPane.showMessageDialog(null, "Debe ingresar el nombre del proveedor.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Crear un objeto Proveedores con la información capturada
        Proveedores nuevoProveedor = new Proveedores();
        nuevoProveedor.setNombre_proveedor(nombreProveedor);

        // Llamar al método insertar del DAO para guardar el nuevo proveedor
        boolean insertado = daoProveedores.insertar(nuevoProveedor);

        if (insertado) {
            // Si se insertó correctamente, actualizar la tabla de proveedores
            CargarTablaProveedores();
            // Limpiar el campo de texto después de insertar
            txtProveedor_agregar_nombre.setText("");
            // Opcional: mostrar un mensaje de éxito al usuario
            JOptionPane.showMessageDialog(null, "Proveedor agregado correctamente.");
        } else {
            // Si ocurrió un error al insertar, mostrar un mensaje de error al usuario
            JOptionPane.showMessageDialog(null, "Error al insertar el proveedor.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnProveedor_agregar_confirmarActionPerformed

    private void txtProveedor_nombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtProveedor_nombreActionPerformed
        
    }//GEN-LAST:event_txtProveedor_nombreActionPerformed

    private void btnProveedor_modificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProveedor_modificarActionPerformed
        int filaSeleccionada = tblProveedores.getSelectedRow();
        if (filaSeleccionada != -1) {
            DefaultTableModel modelo = (DefaultTableModel) tblProveedores.getModel();
            // Obtener los datos de la fila seleccionada y cargarlos en los campos de texto
            txtProveedor_nombre.setText(modelo.getValueAt(filaSeleccionada, 1).toString());

            // Habilitar los campos de texto para permitir la edición
            txtProveedor_nombre.setEnabled(true);

            // Activar el botón Confirmar
            txtProveedor_nombre.setEnabled(true);
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione un proveedor para modificar.");
        }
    }//GEN-LAST:event_btnProveedor_modificarActionPerformed

    private void btnProveedor_confirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProveedor_confirmarActionPerformed
        // Obtener el nuevo valor del texto txtProveedor_nombre
        String nuevoNombre = txtProveedor_nombre.getText();

        // Verificar si el campo está vacío
        if (nuevoNombre.isEmpty()) {
            JOptionPane.showMessageDialog(null, "El nombre del proveedor no puede estar vacío.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Obtener la fila seleccionada en la tabla de proveedores
        int filaSeleccionada = tblProveedores.getSelectedRow();

        // Verificar si se ha seleccionado una fila
        if (filaSeleccionada != -1) {
            // Mostrar un mensaje de confirmación antes de modificar el proveedor
            int opcion = JOptionPane.showConfirmDialog(null, "¿Estás seguro de que quieres modificar este proveedor?", "Confirmar modificación", JOptionPane.YES_NO_OPTION);
            if (opcion == JOptionPane.YES_OPTION) {
                try {
                    // Obtener el ID del proveedor seleccionado
                    int proveedorID = (int) tblProveedores.getValueAt(filaSeleccionada, 0);

                    // Crear un objeto de Proveedores con el nuevo valor
                    Proveedores proveedorModificado = new Proveedores();
                    proveedorModificado.setProveedor_id(proveedorID);
                    proveedorModificado.setNombre_proveedor(nuevoNombre);

                    // Llamar al DAO para modificar el proveedor en la base de datos
                    boolean modificado = daoProveedores.modificar(proveedorModificado);

                    // Verificar si la modificación fue exitosa
                    if (modificado) {
                        JOptionPane.showMessageDialog(null, "Proveedor modificado correctamente.");

                        // Deshabilitar el campo de texto
                        txtProveedor_nombre.setEnabled(false);

                        // Desactivar el botón Confirmar
                        btnProveedor_confirmar.setEnabled(false);

                        // Limpiar el campo de texto
                        txtProveedor_nombre.setText("");

                        // Actualizar la tabla de proveedores para reflejar los cambios
                        CargarTablaProveedores();
                    } else {
                        JOptionPane.showMessageDialog(null, "Error al modificar el proveedor.");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error al modificar el proveedor: " + ex.getMessage());
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione un proveedor para modificar.");
        }

    }//GEN-LAST:event_btnProveedor_confirmarActionPerformed

    private void txtProveedor_agregar_nombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtProveedor_agregar_nombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtProveedor_agregar_nombreActionPerformed
    
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        Productos nuevoProducto = new Productos();
        try {
            // Verificar si todos los campos están llenos
            if (txtProductos_nombre.getText().isEmpty()
                    || txtProductos_precio.getText().isEmpty()
                    || cantidad.getText().isEmpty()
                    || txtProductos_foto.getText().isEmpty()
                    || txtProductos_categoriaid.getText().isEmpty()) {

                JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos.");
                return;
            }

            // Asignar los datos al objeto nuevoProducto
            nuevoProducto.setNombre_producto(txtProductos_nombre.getText());
            nuevoProducto.setPrecio(Float.parseFloat(txtProductos_precio.getText()));
            nuevoProducto.setInStock(Integer.parseInt(cantidad.getText()));
            nuevoProducto.setFoto(txtProductos_foto.getText());

            // Asignar el ID de categoría obtenido del campo de texto
            nuevoProducto.setCategoria_id(Integer.parseInt(txtProductos_categoriaid.getText()));

            // Crear una instancia de DAO_Productos
            DAO_Productos daoProductos = new DAO_Productos();

            // Insertar el nuevo producto utilizando el método insertar de DAO_Productos
            boolean insertado = daoProductos.insertar(nuevoProducto);

            // Si el producto se inserta correctamente, mostrar un mensaje
            if (insertado) {
                JOptionPane.showMessageDialog(null, "Producto agregado correctamente");
                CargarTablaProductos();
            } else {
                JOptionPane.showMessageDialog(null, "Error al agregar el producto");
            }
        } catch (NumberFormatException ex) {
            // Manejar la excepción si los valores numéricos no son válidos
            String mensaje = "Por favor, ingrese valores numéricos válidos para ";
            if (!esNumero(txtProductos_precio.getText())) {
                mensaje += "Precio\n";
            }
            if (!esNumero(cantidad.getText())) {
                mensaje += "Cantidad\n";
            }
            if (!esNumero(txtProductos_categoriaid.getText())) {
                mensaje += "ID de categoría\n";
            }
            JOptionPane.showMessageDialog(null, mensaje);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void txtProductos_productoidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtProductos_productoidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtProductos_productoidActionPerformed

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
            java.util.logging.Logger.getLogger(vInventario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(vInventario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(vInventario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(vInventario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new vInventario().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCategoria_agregar_confirmar;
    private javax.swing.JButton btnCategoria_modificar;
    private javax.swing.JButton btnCategoria_modificar_confirmar;
    private javax.swing.JButton btnCategorias_eliminar;
    private javax.swing.JButton btnProductos_agregar;
    private javax.swing.JButton btnProductos_busqueda_confirmar;
    private javax.swing.JButton btnProductos_eliminar;
    private javax.swing.JButton btnProductos_modificar;
    private javax.swing.JButton btnProveedor_agregar_confirmar;
    private javax.swing.JButton btnProveedor_confirmar;
    private javax.swing.JButton btnProveedor_eliminar;
    private javax.swing.JButton btnProveedor_modificar;
    private javax.swing.JTextField cantidad;
    private javax.swing.JButton jButton3;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblCategoria_agregar;
    private javax.swing.JLabel lblCategoria_agregar_descripcion;
    private javax.swing.JLabel lblCategoria_agregar_nombre;
    private javax.swing.JLabel lblCategoria_modificar_descripcion;
    private javax.swing.JLabel lblCategoria_modificar_nombre;
    private javax.swing.JLabel lblCategorias_panel_de_control;
    private javax.swing.JLabel lblInformacion_Proveedores1;
    private javax.swing.JLabel lblInformacion_categorias1;
    private javax.swing.JLabel lblInformacion_productos;
    private javax.swing.JLabel lblPanel_de_control_productos;
    private javax.swing.JLabel lblProducto_busqueda;
    private javax.swing.JLabel lblProducto_busqueda_categoriaid;
    private javax.swing.JLabel lblProducto_busqueda_nombre;
    private javax.swing.JLabel lblProducto_busqueda_productoid;
    private javax.swing.JLabel lblProducto_cantidad;
    private javax.swing.JLabel lblProducto_categoriaid;
    private javax.swing.JLabel lblProducto_foto;
    private javax.swing.JLabel lblProducto_nombre;
    private javax.swing.JLabel lblProducto_precio;
    private javax.swing.JLabel lblProducto_productoid;
    private javax.swing.JLabel lblProveedor_agregar_agregar;
    private javax.swing.JLabel lblProveedor_agregar_nombre;
    private javax.swing.JLabel lblProveedor_nombre;
    private javax.swing.JLabel lblProveedor_panel_de_control;
    private javax.swing.JPopupMenu popProductos_agregar;
    private javax.swing.JTable tblCategorias;
    private javax.swing.JTable tblProductos;
    private javax.swing.JTable tblProveedores;
    private javax.swing.JTable tblRegistros;
    private javax.swing.JTextField txtCategoria_agregar_descripcion;
    private javax.swing.JTextField txtCategoria_agregar_nombre;
    private javax.swing.JTextField txtCategoria_modificar_descripcion;
    private javax.swing.JTextField txtCategoria_modificar_nombre;
    private javax.swing.JTextField txtProducto_categoriaid;
    private javax.swing.JTextField txtProducto_nombre;
    private javax.swing.JTextField txtProducto_productoid;
    private javax.swing.JTextField txtProductos_categoriaid;
    private javax.swing.JTextField txtProductos_foto;
    private javax.swing.JTextField txtProductos_nombre;
    private javax.swing.JTextField txtProductos_precio;
    private javax.swing.JTextField txtProductos_productoid;
    private javax.swing.JTextField txtProveedor_agregar_nombre;
    private javax.swing.JTextField txtProveedor_nombre;
    // End of variables declaration//GEN-END:variables
}
