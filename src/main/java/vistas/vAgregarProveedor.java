/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vistas;

import DAOs.DAO_Proveedores;
import javax.swing.JOptionPane;
import modelo.Proveedores;

/**
 *
 * @author Oscar Tobar <pe.tobar@icloud.com>
 */
public class vAgregarProveedor extends javax.swing.JFrame {

    /**
     * Creates new form vAgregarProveedor
     */
    public vAgregarProveedor() {
        initComponents();
        this.daoProveedores = new DAO_Proveedores();

    }
    
        private DAO_Proveedores daoProveedores;


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblAgregar_proveedor = new javax.swing.JLabel();
        lblAgregar_proveedor_nombre = new javax.swing.JLabel();
        txtAgregar_proveedor_nombre = new javax.swing.JTextField();
        btnAgregar_proveedor_agregar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblAgregar_proveedor.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblAgregar_proveedor.setText("Agregar Proveedor");

        lblAgregar_proveedor_nombre.setText("Nombre:");

        btnAgregar_proveedor_agregar.setText("Agregar");
        btnAgregar_proveedor_agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregar_proveedor_agregarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(lblAgregar_proveedor_nombre)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtAgregar_proveedor_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(52, Short.MAX_VALUE)
                        .addComponent(lblAgregar_proveedor)))
                .addGap(68, 68, 68))
            .addGroup(layout.createSequentialGroup()
                .addGap(97, 97, 97)
                .addComponent(btnAgregar_proveedor_agregar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(lblAgregar_proveedor))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(96, 96, 96)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblAgregar_proveedor_nombre)
                            .addComponent(txtAgregar_proveedor_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addComponent(btnAgregar_proveedor_agregar)
                .addGap(40, 40, 40))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAgregar_proveedor_agregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregar_proveedor_agregarActionPerformed
        // Capturar la informacion del usuario desde los campos de texto
        String nombreProveedor = txtAgregar_proveedor_nombre.getText();

        // Verificar si el nombre del proveedor esta presente
        if (nombreProveedor.isEmpty()) {
            // Si el campo esta vacio, mostrar un mensaje de error y salir del metodo
            JOptionPane.showMessageDialog(null, "Debe ingresar el nombre del proveedor.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Crear un objeto Proveedores con la informacion capturada
        Proveedores nuevoProveedor = new Proveedores();
        nuevoProveedor.setNombre_proveedor(nombreProveedor);

        // Llamar al metodo insertar del DAO para guardar el nuevo proveedor
        boolean insertado = daoProveedores.insertar(nuevoProveedor);

        if (insertado) {
            // Limpiar el campo de texto despues de insertar
            txtAgregar_proveedor_nombre.setText("");
            // Opcional: mostrar un mensaje de exito al usuario
            JOptionPane.showMessageDialog(null, "Proveedor agregado correctamente.");
        } else {
            // Si ocurrio un error al insertar, mostrar un mensaje de error al usuario
            JOptionPane.showMessageDialog(null, "Error al insertar el proveedor.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnAgregar_proveedor_agregarActionPerformed

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
            java.util.logging.Logger.getLogger(vAgregarProveedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(vAgregarProveedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(vAgregarProveedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(vAgregarProveedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new vAgregarProveedor().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar_proveedor_agregar;
    private javax.swing.JLabel lblAgregar_proveedor;
    private javax.swing.JLabel lblAgregar_proveedor_nombre;
    private javax.swing.JTextField txtAgregar_proveedor_nombre;
    // End of variables declaration//GEN-END:variables
}
