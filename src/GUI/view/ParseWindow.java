package GUI.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import Controller.ControllerClientes;
import Controller.ControllerPedidos;
import Controller.ControllerProductos;
import Controller.ControllerProveedores;

import javax.swing.*;
//Clase que sirve para introducir datos en los subsistemas, el resto de subsistemas heredan del mismo con el objetivo de tener
//la mayor reutilización posible sobre los métodos y atributos comunes.
public class ParseWindow extends MyWindow{
	private static final long serialVersionUID = 1L;
	protected JLabel imageLabel;
	protected JLayeredPane layeredPanel;
	protected JPanel description;
	protected ImageIcon imageIcon3;
	protected JButton addButton;
	protected int numDatos;
	protected ControllerClientes ctrlCl;
	protected ControllerPedidos ctrlPd;
	protected ControllerProductos ctrlProd;
	protected ControllerProveedores ctrlProv;
	protected int action;
	protected String errorMessage;
	private Font writeFont;
	private Font fieldFont;
	//Se inicializan los controllers sobre los que se va a operar
	public ParseWindow(String name, ControllerClientes ctrlCl, ControllerPedidos ctrlPd, ControllerProductos ctrlProd,
			ControllerProveedores ctrlProv) {
		super(name);
		this.ctrlCl = ctrlCl;
		this.ctrlPd = ctrlPd;
		this.ctrlProd = ctrlProd;
		this.ctrlProv = ctrlProv; 
		this.errorMessage = "";
		initParseGUI();
    }
	private void initParseGUI() {
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		Image image = codaIcon.getImage().getScaledInstance(200, 155, java.awt.Image.SCALE_SMOOTH);
		codaIcon = new ImageIcon(image);
	    imageLabel = new JLabel(codaIcon);
	    imageLabel.setOpaque(true);
	    imageLabel.setBackground(new Color(0,0,0,0));
	    imageLabel.setSize(200, 155);
	    layeredPanel = new JLayeredPane();
	    layeredPanel.add(imageLabel, Integer.valueOf(3)); 
	    layeredPanel.add(backButton, Integer.valueOf(2));
	    ImageIcon imageIcon2 = new ImageIcon("resources/icons/Marco_inserta.jpeg");
        Image image2 = imageIcon2.getImage().getScaledInstance(240, 114, java.awt.Image.SCALE_SMOOTH);
        imageIcon2 = new ImageIcon(image2);
        //Se crea el botón de confirmación de introducir los datos en el subsistema correspondiente,
        //además de establecerse las imagenes sobre los paneles y botones que se van a usar.
	    addButton = new JButton("Añadir");
	    addButton.setFont(new Font("Comic Sans", Font.BOLD, 20));
	    addButton.setForeground(Color.white);
	    addButton.setVerticalTextPosition(SwingConstants.CENTER);
	    addButton.setHorizontalTextPosition(SwingConstants.CENTER);
	    addButton.setIcon(imageIcon2);
	    addButton.setSize(200, 100);
	    layeredPanel.add(addButton, Integer.valueOf(4));
	    description = new JPanel();
		description.setBackground(new Color(0,0,0,0));
		imageIcon3 = new ImageIcon("resources/icons/Fondo_campos.png");
        Image image3 = imageIcon3.getImage().getScaledInstance(210, 47, java.awt.Image.SCALE_SMOOTH);
        imageIcon3 = new ImageIcon(image3);
        fieldFont = new Font("Comic Sans", Font.BOLD, 18);
        writeFont = new Font("Comic Sans", Font.BOLD, 15);
	}
	//Se establecen las propiedades de las descripciones
	public void setLabelProperties(JLabel label) {
		   label.setForeground(Color.white);
		   label.setIcon(imageIcon3);
		   label.setFont(fieldFont);
		   label.setVerticalTextPosition(SwingConstants.CENTER);
		   label.setHorizontalTextPosition(SwingConstants.CENTER);
	}
	//Se establecen las propiedades de los campos
	public void setJTextFieldProperties(JTextField field) {
		field.setFont(writeFont);
		field.setBackground(Color.BLACK); 
		field.setForeground(Color.WHITE);
        Font font = field.getFont();
        field.setFont(new Font(font.getName(), Font.PLAIN, 18));
        Border border = BorderFactory.createLineBorder(Color.WHITE, 3); // Establecer el color y grosor de la línea de separación
        field.setBorder(border);		   
	}
	//Se lleva a cabo el redimensionamiento de componentes de la ventana y también de su posicion en la ventana, en lugar
	//de usar Layouts para mayor precisión.
	public void resizer() {
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				int ejeX = (int) ((0.39) * getWidth() - description.getWidth() / 2);
				int ejeY = (int) ((0.42) * getHeight() - description.getHeight() / 2);            
				description.setLocation(ejeX, ejeY);
				ejeX = (int) (0.69 * getWidth() - data.getWidth() / 2);
				ejeY = (int) (0.42 * getHeight() - data.getHeight() / 2);
				data.setLocation(ejeX, ejeY);
				ejeX = (int) (0.07  * getWidth() - imageLabel.getWidth() / 2);
				ejeY = (int) (0.81 * getHeight() - imageLabel.getHeight() / 2);
				imageLabel.setLocation(ejeX, ejeY);
				ejeX = (int) (0.1 * getWidth() - backButton.getWidth() / 2);
				ejeY = (int) (0.08 * getHeight() - backButton.getHeight() / 2);
				backButton.setLocation(ejeX, ejeY);
				ejeX = (int) (0.5 * getWidth() - addButton.getWidth() / 2);
				ejeY = (int) (0.83 * getHeight() - addButton.getHeight() / 2);
				addButton.setLocation(ejeX, ejeY);
			}
		});
	}
	//Método que indica el tipo de error cometido en el campo asociado en caso de haberlo. 
	//Indicando en el marco del campo, el acierto o el error.
	public String isOkField(JTextField field, JLabel tipo, String aux) {
		String aux2 = "";
		boolean ok = false;
		JLabel parametro = new JLabel(tipo.getText());
		if (field.getText().isEmpty()) 
			aux2 += "está vacío  -";
		else if (!checkInteger(field) && aux == "n") 
			aux2 += "no es nº de teléfono  -";
		else if (!checkInteger(field) && aux == "i") 
			aux2 += "no es un nº entero  -"; 
		else if(!checkDouble(field) && aux == "d")
			aux2 += "no es un nº decimal  -";
		else if ((field.getText().length() < 9 && aux == "dni") || (field.getText().length() < 9 && aux == "cif") 
				|| (field.getText().length() < 9 && aux == "n"))  
			aux2 += "tiene menos de 9 caracteres  -";
		else if	(field.getText().length() < 8 && aux == "f") 
			aux2 += "tiene menos de 8 caracteres  -";
		else if (field.getText().length() == 8  && !checkDate(field) && aux == "f") 
			aux2 += "fecha no válida -";
		else if	(field.getText().length() < 4 && aux == "cod")  
			aux2 += "tiene menos de 4 caracteres  -";
		else if	 ((!field.getText().equals("true") && !field.getText().equals("false")) && aux == "e")
				aux2 += "no válido, debe ser true o false  -";
		else if (field.getText().length() == 9  && !checkDNI(field) && aux == "dni") 
			aux2 += "dni no válido -";
		else if (field.getText().length() == 9  && !checkCIF(field) && aux == "cif") 
			aux2 += "cif no válido -";
		else
			ok = true;
		if (!ok) { 
			aux2 = "- " + parametro.getText() + " "+ aux2;
			field.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
		}
		else field.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));	
		return aux2;
	}
	//Verifica si se trata de un entero
	public boolean checkInteger(JTextField field) {
	    try {
	        Integer.parseInt(field.getText());
	    } catch (NumberFormatException e) {
	        return false;
	    }
	    return true;
	}
	//Verifica si se trata de un DNI
	public boolean checkDNI(JTextField textField) {
	    String text = textField.getText();
	    for (int i = 0; i < 8; i++) {
	        if (!Character.isDigit(text.charAt(i))) 
	            return false;
	        
	    }
	    return Character.isLetter(text.charAt(8));
	}
	//Verifica si se trata de una fecha
	public boolean checkDate(JTextField textField) {
	    String text = textField.getText();
	    if (text.charAt(2) != '/' || text.charAt(5) != '/') {
	        return false;
	    }
	    for (int i = 0; i < 8; i++) {
	        if (i != 2 && i != 5 && !Character.isDigit(text.charAt(i))) 
	            return false;
	    }
	    return true;
	}
	//Verifica si se trata de un CIF
	public boolean checkCIF(JTextField textField) {
	    String text = textField.getText();
	    for (int i = 1; i < 8; i++) {
	        if (!Character.isDigit(text.charAt(i))) 
	            return false;
	    }
	    return Character.isDigit(text.charAt(8));
	}

	//Verifica si se trata de un double
	public boolean checkDouble(JTextField field) {
	    try {
	        Double.parseDouble(field.getText());
	    } catch (NumberFormatException e) {
	        return false;
	    }
	    return true;
	}
}
