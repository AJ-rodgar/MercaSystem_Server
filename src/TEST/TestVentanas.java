package TEST;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Point;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import modelos.Empleado;
import modelos.EstadoEmpleado;
import modelos.Rol;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTable;
import java.awt.Color;

public class TestVentanas extends JFrame {

    private JPanel contentPane;
    private JTable table;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
	EventQueue.invokeLater(new Runnable() {
	    public void run() {
		try {
		    TestVentanas frame = new TestVentanas();
		    frame.setVisible(true);
		} catch (Exception e) {
		    e.printStackTrace();
		}
	    }
	});
    }

    /**
     * Create the frame.
     */
    public TestVentanas() {
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setBounds(210, 100, 690, 600);
	contentPane = new JPanel();
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	setContentPane(contentPane);
	contentPane.setLayout(null);
	
	
	inicializaComponentes();
    }
    
    public void inicializaComponentes() {

	List<Empleado> empleados = new ArrayList<>();
	
	
	EmpleadoTableModel modelo = new EmpleadoTableModel(empleados);
	
	table = new JTable();
	table.setModel(modelo);
	table.setBounds(20, 20, 498, 179);
	table.addMouseListener(new MouseAdapter() {
	    public void mousePressed(MouseEvent mouseEvent) {
	        JTable table = (JTable) mouseEvent.getSource();
	        Point point = mouseEvent.getPoint();
	        int row = table.rowAtPoint(point);
	        if (mouseEvent.getClickCount() == 2 && row != -1) {
	            EmpleadoTableModel model = (EmpleadoTableModel) table.getModel();
	            if (model.getEmpleadoAt(row) != null) {
	        	System.out.println(model.getEmpleadoAt(row));
	            }
	        }
	    }
	});
	      
	ScrollPane scroll = new ScrollPane();
	scroll.setBounds(20, 20, 644, 200);
	scroll.add(table);
	
	contentPane.add(scroll);
	
    }
}
