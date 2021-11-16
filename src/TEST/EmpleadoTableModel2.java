package TEST;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import modelos.Empleado;

@SuppressWarnings("serial")
public class EmpleadoTableModel2 extends AbstractTableModel{

    String[] columnNames = {"Rol","Nombre","Estado"};
    
    List<Empleado> empleados;
    
    public EmpleadoTableModel2(List<Empleado> empleados) {
	this.empleados = empleados;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }
    
    @Override
    public int getRowCount() {
	return empleados.size();
    }

    @Override
    public int getColumnCount() {
	return columnNames.length;
    }
    
    @Override
    public boolean isCellEditable(int row, int col){ 
	return false; 
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
	switch(columnIndex) {
	case 0:
            return empleados.get(rowIndex).getRol();
        case 1:
            return empleados.get(rowIndex).getNombre() + " " + empleados.get(rowIndex).getApellido1() + " " + empleados.get(rowIndex).getApellido2();
        case 2:
            return empleados.get(rowIndex).getEstadoEmpleado();
        default:
            return " ";
	}
    }
    
    public Empleado getEmpleadoAt(int rowIndex) {
	return empleados.get(rowIndex);
    }

}
