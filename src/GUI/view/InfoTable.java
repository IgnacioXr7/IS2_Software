package GUI.view;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;

import GUI.extra.CustomTable;

public class InfoTable extends JPanel {
	private static final long serialVersionUID = 1L;
	private TableModel _tableModel;
	//Clase que servirá para implementar la información que le llegue a la tabla correspondiente
	public InfoTable(TableModel tableModel) {
		_tableModel = tableModel;
		initGUI();
	}
	private void initGUI() {
		setLayout(new BorderLayout());
		JPanel contenedor = new JPanel(new BorderLayout());
		add(contenedor);
		JTable table = new CustomTable(_tableModel);
		JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.getViewport().setBackground(Color.BLACK);
        scrollPane.setBackground(Color.BLACK);
        contenedor.add(scrollPane);
	}
}

