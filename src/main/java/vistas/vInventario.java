
package vistas;

import DAOs.DAO_Categorias;
import DAOs.DAO_Productos;
import DAOs.DAO_Proveedores;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import modelo.Productos;

public class vInventario extends javax.swing.JFrame {


        private DAO_Productos daoProductos;
        private DAO_Categorias daoCategorias;
        private DAO_Proveedores daoProveedores;

        String encabezados_productos[] = {"Producto ID", "Nombre", "Precio", "Stock", "Foto", "Categoria ID"};
        String encabezados_categorias[] = {"Categoria ID", "Nombre Categoria", "Descripción"};
        String encabezados_proveedores[] = {"Proveedor ID", "Nombre Proveedor"};

        DefaultTableModel modeloProductos = new DefaultTableModel(encabezados_productos, 0);
        DefaultTableModel modeloCategorias = new DefaultTableModel(encabezados_categorias, 0);
        DefaultTableModel modeloProveedores = new DefaultTableModel(encabezados_proveedores, 0);

        public vInventario() {
            initComponents();
            this.daoProductos = new DAO_Productos();
            this.daoCategorias = new DAO_Categorias();
            this.daoProveedores = new DAO_Proveedores();
            CargarTablaProductos();
            CargarTablaCategorias();
            CargarTablaProveedores();

            // Agregar el ListSelectionListener a la tabla de productos
            tblProductos.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent event) {
                    // Verificar si hay alguna fila seleccionada en la tabla de productos
                    if (tblProductos.getSelectedRow() != -1) {
                        // Habilitar el botón de eliminar si hay una fila seleccionada
                        btnProductos_eliminar.setEnabled(true);
                        // Habilitar el botón de modificar si hay una fila seleccionada
                        btnProductos_modificar.setEnabled(true);
                    } else {
                        // Deshabilitar el botón de eliminar si no hay una fila seleccionada
                        btnProductos_eliminar.setEnabled(false);
                        // Deshabilitar el botón de modificar si no hay una fila seleccionada
                        btnProductos_modificar.setEnabled(false);
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

        private void tblProductosMouseClicked(java.awt.event.MouseEvent evt) {
            // Obtener el número de fila seleccionada
            int filaSeleccionada = tblProductos.getSelectedRow();

            // Obtener los datos de la fila seleccionada
            DefaultTableModel modelo = (DefaultTableModel) tblProductos.getModel();
            txtProductos_productoid.setText(modelo.getValueAt(filaSeleccionada, 0).toString());
            txtProductos_nombre.setText(modelo.getValueAt(filaSeleccionada, 1).toString());
            txtProductos_precio.setText(modelo.getValueAt(filaSeleccionada, 2).toString());
            cantidad.setText(modelo.getValueAt(filaSeleccionada, 3).toString());
            txtProductos_foto.setText(modelo.getValueAt(filaSeleccionada, 4).toString());
            txtProductos_categoriaid.setText(modelo.getValueAt(filaSeleccionada, 5).toString());
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
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblCategorias = new javax.swing.JTable();
        lblInformacion_categorias = new javax.swing.JLabel();
        lblPanel_de_control_productos1 = new javax.swing.JLabel();
        lblInformacion_categorias1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblInformacion_categorias2 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
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
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblProveedores = new javax.swing.JTable();
        lblInformacion_Proveedores = new javax.swing.JLabel();
        lblInformacion_Proveedores1 = new javax.swing.JLabel();

        jMenuItem1.setText("jMenuItem1");
        popProductos_agregar.add(jMenuItem1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

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

        lblInformacion_categorias.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblInformacion_categorias.setText("Panel de control");

        lblPanel_de_control_productos1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblPanel_de_control_productos1.setText("Panel de control");

        lblInformacion_categorias1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblInformacion_categorias1.setText("Informacion de Categorias");

        jButton1.setText("jButton1");

        jButton2.setText("jButton2");

        jButton4.setText("jButton4");

        jTextField1.setText("jTextField1");

        jTextField2.setText("jTextField2");

        jTextField3.setText("jTextField3");

        jLabel1.setText("jLabel1");

        jLabel2.setText("jLabel2");

        jLabel3.setText("jLabel3");

        lblInformacion_categorias2.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblInformacion_categorias2.setText("Agregar");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 921, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jButton1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                                        .addComponent(jButton2))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jButton4)
                                        .addGap(0, 0, Short.MAX_VALUE))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblInformacion_categorias2)
                                .addGap(57, 57, 57))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lblInformacion_categorias)))
                .addGap(24, 24, 24))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(495, 495, 495)
                    .addComponent(lblPanel_de_control_productos1)
                    .addContainerGap(507, Short.MAX_VALUE)))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(271, 271, 271)
                    .addComponent(lblInformacion_categorias1)
                    .addContainerGap(624, Short.MAX_VALUE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(46, Short.MAX_VALUE)
                .addComponent(lblInformacion_categorias, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 436, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(jButton2))
                        .addGap(93, 93, 93)
                        .addComponent(lblInformacion_categorias2, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(35, 35, 35)
                        .addComponent(jButton4)))
                .addContainerGap())
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(262, 262, 262)
                    .addComponent(lblPanel_de_control_productos1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(262, Short.MAX_VALUE)))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(38, 38, 38)
                    .addComponent(lblInformacion_categorias1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(486, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("Categoria", jPanel2);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1176, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 576, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Registros", jPanel4);

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

        txtProductos_productoid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtProductos_productoidActionPerformed(evt);
            }
        });

        lblProducto_productoid.setText("Producto ID");

        lblProducto_nombre.setText("Nombre");

        lblProducto_precio.setText("Precio");

        lblProducto_cantidad.setText("Cantidad");

        lblProducto_foto.setText("Foto");

        btnProductos_busqueda_confirmar.setText("Confirmar");
        btnProductos_busqueda_confirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProductos_busqueda_confirmarActionPerformed(evt);
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
                .addGap(101, 101, 101))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 824, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lblProducto_busqueda_categoriaid, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnProductos_busqueda_confirmar, javax.swing.GroupLayout.Alignment.LEADING))
                                .addGap(232, 232, 232))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(btnProductos_agregar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnProductos_modificar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE))
                                    .addComponent(lblProducto_nombre)
                                    .addComponent(lblProducto_precio)
                                    .addComponent(lblProducto_cantidad)
                                    .addComponent(lblProducto_productoid)
                                    .addComponent(lblProducto_foto)
                                    .addComponent(lblProducto_categoriaid)
                                    .addComponent(lblProducto_busqueda_productoid)
                                    .addComponent(lblProducto_busqueda_nombre))
                                .addGap(28, 28, 28)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnProductos_eliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                                    .addComponent(txtProducto_productoid)
                                    .addComponent(txtProducto_nombre)
                                    .addComponent(txtProducto_categoriaid)
                                    .addComponent(txtProductos_productoid)
                                    .addComponent(txtProductos_nombre)
                                    .addComponent(txtProductos_precio)
                                    .addComponent(cantidad)
                                    .addComponent(txtProductos_foto)
                                    .addComponent(txtProductos_categoriaid))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(86, 86, 86)
                        .addComponent(lblProducto_busqueda)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblInformacion_productos, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPanel_de_control_productos, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnProductos_agregar)
                            .addComponent(btnProductos_eliminar))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnProductos_modificar)
                            .addComponent(jButton3))
                        .addGap(36, 36, 36)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtProductos_productoid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblProducto_productoid))
                        .addGap(6, 6, 6)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtProductos_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblProducto_nombre))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtProductos_precio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblProducto_precio))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblProducto_cantidad))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblProducto_foto)
                            .addComponent(txtProductos_foto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtProductos_categoriaid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblProducto_categoriaid))
                        .addGap(2, 2, 2)
                        .addComponent(lblProducto_busqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblProducto_busqueda_productoid)
                            .addComponent(txtProducto_productoid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblProducto_busqueda_nombre)
                            .addComponent(txtProducto_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtProducto_categoriaid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblProducto_busqueda_categoriaid))
                        .addGap(18, 18, 18)
                        .addComponent(btnProductos_busqueda_confirmar)
                        .addGap(30, 30, 30))))
        );

        jTabbedPane1.addTab("Producto", jPanel1);

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

        lblInformacion_Proveedores.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblInformacion_Proveedores.setText("Panel de control");

        lblInformacion_Proveedores1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblInformacion_Proveedores1.setText("Informacion de Proveedores");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 867, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(303, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblInformacion_Proveedores)
                .addGap(39, 39, 39))
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGap(315, 315, 315)
                    .addComponent(lblInformacion_Proveedores1)
                    .addContainerGap(560, Short.MAX_VALUE)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(lblInformacion_Proveedores)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 93, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 413, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGap(31, 31, 31)
                    .addComponent(lblInformacion_Proveedores1)
                    .addContainerGap(513, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("Proveedores", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnProductos_agregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProductos_agregarActionPerformed
        // Crear un nuevo objeto Producto con los datos ingresados en los campos correspondientes
        Productos nuevoProducto = new Productos();
        // Asegurarse de manejar cualquier excepción al convertir datos de texto a números
        try {
            nuevoProducto.setProducto_id(Integer.parseInt(txtProductos_productoid.getText()));
            nuevoProducto.setNombre_producto(txtProductos_nombre.getText());
            nuevoProducto.setPrecio(Float.parseFloat(txtProductos_precio.getText()));
            nuevoProducto.setInStock(Integer.parseInt(cantidad.getText()));
            nuevoProducto.setFoto(txtProductos_foto.getText());
            nuevoProducto.setCategoria_id(Integer.parseInt(txtProductos_categoriaid.getText()));

            // Crear una instancia de DAO_Productos
            DAO_Productos daoProductos = new DAO_Productos();

            // Insertar el nuevo producto utilizando el método insertar de DAO_Productos
            boolean insertado = daoProductos.insertar(nuevoProducto);

            // Si el producto se inserta correctamente, mostrar un mensaje de éxito
            if (insertado) {
                JOptionPane.showMessageDialog(null, "Producto agregado correctamente");
                // Actualizar la tabla de productos después de agregar uno nuevo
                CargarTablaProductos();
            } else {
                JOptionPane.showMessageDialog(null, "Error al agregar el producto");
            }
        } catch (NumberFormatException ex) {
            // Manejar la excepción si los campos numéricos no contienen valores válidos
            String mensaje = "Por favor, ingrese valores numéricos válidos para ";
            if (!esNumero(txtProductos_productoid.getText())) {
                mensaje += "Producto ID\n";
            }
            if (!esNumero(txtProductos_precio.getText())) {
                mensaje += "Precio\n";
            }
            if (!esNumero(cantidad.getText())) {
                mensaje += "Cantidad\n";
            }
            if (!esNumero(txtProductos_categoriaid.getText())) {
                mensaje += "ID de Categoría";
            }
            JOptionPane.showMessageDialog(null, mensaje);
        }
    }

    private boolean esNumero(String texto) {
        try {
            Integer.parseInt(texto);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }//GEN-LAST:event_btnProductos_agregarActionPerformed

    private void txtProductos_productoidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtProductos_productoidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtProductos_productoidActionPerformed

    private void btnProductos_modificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProductos_modificarActionPerformed
        // Obtener los datos modificados
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

        // Mostrar mensaje de éxito o error
        if (modificado) {
            JOptionPane.showMessageDialog(null, "Producto modificado correctamente");
            // Actualizar la tabla de productos después de modificar uno existente
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

        // Obtener el índice de la fila seleccionada
        int filaSeleccionada = tblProductos.getSelectedRow();

        // Verificar si hay alguna fila seleccionada en la tabla de productos
        if (filaSeleccionada != -1) {
            // Obtener el ID del producto a eliminar
            int productoID = Integer.parseInt(modeloProductos.getValueAt(filaSeleccionada, 0).toString());

            // Verificar si el producto existe antes de intentar eliminarlo
            DAO_Productos daoProductos = new DAO_Productos();
            if (daoProductos.existeProducto(productoID)) {
                // Si el producto existe, proceder con la eliminación
                boolean eliminado = daoProductos.eliminar(productoID);
                if (eliminado) {
                    JOptionPane.showMessageDialog(null, "Producto eliminado correctamente");
                    // Actualizar la tabla de productos después de eliminar uno existente
                    CargarTablaProductos();
                } else {
                    JOptionPane.showMessageDialog(null, "Error al eliminar el producto");
                }
            } else {
                // Si el producto no existe, mostrar un mensaje al usuario
                JOptionPane.showMessageDialog(null, "El producto con ID " + productoID + " no existe");
            }
        } else {
            // Si no hay ninguna fila seleccionada, mostrar un mensaje al usuario
            JOptionPane.showMessageDialog(null, "Por favor, seleccione un producto para eliminar");
        }
    

    }//GEN-LAST:event_btnProductos_eliminarActionPerformed

    private void btnProductos_busqueda_confirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProductos_busqueda_confirmarActionPerformed
        // Obtener los valores de los campos de búsqueda
        String nombre = txtProducto_nombre.getText();
        String categoriaIDText = txtProducto_categoriaid.getText(); // Obtener el texto del campo de texto
        int categoriaID = 0; // Declarar la variable para almacenar la categoría como int

        // Validar si los campos están vacíos
        boolean filtroVacio = nombre.isEmpty() && categoriaIDText.isEmpty();

        // Verificar si se ha proporcionado un valor para la categoría y si es un número válido
        if (!categoriaIDText.isEmpty()) {
            try {
                categoriaID = Integer.parseInt(categoriaIDText); // Convertir el texto a int
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "El ID de la categoría debe ser un número entero válido.");
                return; // Salir del método si el ID de la categoría no es un número válido
            }
        }

        // Realizar la búsqueda en la base de datos
        ArrayList<Object[]> datosFiltrados = daoProductos.filtrarProductos(nombre, categoriaID);

        // Actualizar la tabla de productos con los resultados de la búsqueda
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
    private javax.swing.JButton btnProductos_agregar;
    private javax.swing.JButton btnProductos_busqueda_confirmar;
    private javax.swing.JButton btnProductos_eliminar;
    private javax.swing.JButton btnProductos_modificar;
    private javax.swing.JTextField cantidad;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JLabel lblInformacion_Proveedores;
    private javax.swing.JLabel lblInformacion_Proveedores1;
    private javax.swing.JLabel lblInformacion_categorias;
    private javax.swing.JLabel lblInformacion_categorias1;
    private javax.swing.JLabel lblInformacion_categorias2;
    private javax.swing.JLabel lblInformacion_productos;
    private javax.swing.JLabel lblPanel_de_control_productos;
    private javax.swing.JLabel lblPanel_de_control_productos1;
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
    private javax.swing.JPopupMenu popProductos_agregar;
    private javax.swing.JTable tblCategorias;
    private javax.swing.JTable tblProductos;
    private javax.swing.JTable tblProveedores;
    private javax.swing.JTextField txtProducto_categoriaid;
    private javax.swing.JTextField txtProducto_nombre;
    private javax.swing.JTextField txtProducto_productoid;
    private javax.swing.JTextField txtProductos_categoriaid;
    private javax.swing.JTextField txtProductos_foto;
    private javax.swing.JTextField txtProductos_nombre;
    private javax.swing.JTextField txtProductos_precio;
    private javax.swing.JTextField txtProductos_productoid;
    // End of variables declaration//GEN-END:variables
}
