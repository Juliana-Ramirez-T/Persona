/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Personas;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class VentanaPrincipal extends JFrame implements ActionListener {
    private ListaPersonas lista; 
    private Container contenedor;
    private JLabel nombre, apellidos, teléfono, dirección;
    private JTextField campoNombre, campoApellidos, campoTeléfono, campoDirección;
    private JButton añadir, eliminar, borrarLista; 
    private JList listaNombres; // Lista de personas
    private DefaultListModel modelo; 
    private JScrollPane scrollLista; 
    public VentanaPrincipal(){
        lista = new ListaPersonas(); 
        inicio();
        setTitle("Personas"); 
        setSize(270,350); 
        setLocationRelativeTo(null); 
        // Establece que el botón de cerrar permitirá salir de la aplicación
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false); 
    }
    private void inicio() {
        contenedor = getContentPane();
        contenedor.setLayout(null);
        // Establece la etiqueta y el campo nombre
        nombre = new JLabel();
        nombre.setText("Nombre:");
        nombre.setBounds(20, 20, 135, 23); 
        campoNombre = new JTextField();
        campoNombre.setBounds(105, 20, 135, 23);
        // Establece la etiqueta y el campo apellidos
        apellidos = new JLabel();
        apellidos.setText("Apellidos:"); 
        apellidos.setBounds(20, 50, 135, 23);
        campoApellidos = new JTextField();
        campoApellidos.setBounds(105, 50, 135, 23);
        // Establece la etiqueta y el campo teléfono
        teléfono = new JLabel();
        teléfono.setText("Teléfono:");
        teléfono.setBounds(20, 80, 135, 23);
        campoTeléfono = new JTextField();
        campoTeléfono.setBounds(105, 80, 135, 23);
        // Establece la etiqueta y el campo dirección
        dirección = new JLabel();
        dirección.setText("Dirección:");
        dirección.setBounds(20, 110, 135, 23); 
        campoDirección = new JTextField();
        campoDirección.setBounds(105, 110, 135, 23);
        // Establece el botón Añadir persona
        añadir = new JButton();
        añadir.setText("Añadir");
        añadir.setBounds(105, 150, 80, 23);
        /* Agrega al botón un ActionListener para que gestione eventos del botón */
        añadir.addActionListener(this);
        // Establece el botón Eliminar persona
        eliminar= new JButton();
        eliminar.setText("Eliminar");
        eliminar.setBounds(20, 280, 80, 23);
        /* Agrega al botón un ActionListener para que gestione eventos del botón */
        eliminar.addActionListener(this);
        // Establece el botón Borrar lista
        borrarLista= new JButton();
        borrarLista.setText("Borrar Lista");
        borrarLista.setBounds(120, 280, 120, 23);
        /* Agrega al botón un ActionListener para que gestione eventos del botón */
        borrarLista.addActionListener(this);
        // Establece la lista gráfica de personas
        listaNombres = new JList();
        listaNombres.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        modelo = new DefaultListModel();
        // Establece una barra de desplazamiento vertical
        scrollLista = new JScrollPane();
        // Establece la posición de la barra de desplazamiento vertical
        scrollLista.setBounds(20, 190 ,220, 80);
        // Asocia la barra de desplazamiento vertical a la lista de personas
        scrollLista.setViewportView(listaNombres);
        // Se añade cada componente gráfico al contenedor de la ventana
        contenedor.add(nombre);
        contenedor.add(campoNombre);
        contenedor.add(apellidos);
        contenedor.add(campoApellidos);
        contenedor.add(teléfono);
        contenedor.add(campoTeléfono);
        contenedor.add(dirección);
        contenedor.add(campoDirección);
        contenedor.add(añadir);
        contenedor.add(eliminar);
        contenedor.add(borrarLista);
        contenedor.add(scrollLista);
    }
    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == añadir) { // Si se pulsa el botón añadir
            añadirPersona(); // Se invoca añadir persona
        }
        if (evento.getSource() == eliminar) { /* Si se pulsa el botón eliminar */
            eliminarNombre(listaNombres.getSelectedIndex() );
        }
        if (evento.getSource() == borrarLista) { /* Si se pulsa el botón borrar lista */
            borrarLista(); // Se invoca borrar lista
        }
    }
    private void añadirPersona() {
        Persona p = new Persona(campoNombre.getText(),campoApellidos.getText(),campoTeléfono.getText(), campoDirección.getText());
        lista.añadirPersona(p);
        String elemento = campoNombre.getText() + "-" + campoApellidos.getText() + "-" + campoTeléfono.getText() + "-" + campoDirección.getText();
        modelo.addElement(elemento);
        listaNombres.setModel(modelo);
        campoNombre.setText("");
        campoApellidos.setText("");
        campoTeléfono.setText("");
        campoDirección.setText("");
    }
    private void eliminarNombre(int indice) {
        if (indice >= 0) { 
            modelo.removeElementAt(indice);
            lista.eliminarPersona(indice); 
        } else {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un elemento","Error",JOptionPane.ERROR_MESSAGE);
        }
    }
    private void borrarLista() {
        lista.borrarLista(); 
        modelo.clear(); // Limpia el JList, la lista gráfica de personas
    }
}