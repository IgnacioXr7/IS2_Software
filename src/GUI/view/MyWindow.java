package GUI.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
//Clase sobre las que heredan las demás ventanas al contener esta atributos como la altura y anchura de la ventana del programa,
//el fondo principal, o el logotipo presente en todo el programa, además de métodos comunes entre subsistemas para ser reutilizados.
public class MyWindow extends JFrame{
	private static final long serialVersionUID = 1L;
	protected int actualWidth;
    protected int actualHeight;
    protected JPanel mainPanel;
    protected ImageIcon codaIcon;
    protected JButton backButton;
	protected JPanel data;
	protected Dimension DimensionMessageWindow;
	private Font writeFont;
	//Se establecen las dimensiones 
	public MyWindow(String name) {
		super(name);
		this.actualWidth = 1550;
        this.actualHeight = 840;
		this.DimensionMessageWindow = new Dimension(500, 105);
		//setResizable(false);
		initMainGUI();
	}
	private void initMainGUI() {
		//Se acceden a las imagenes principales de fondo y logo principal
        ImageIcon imageIcon = new ImageIcon("resources/icons/Fondo.png");
        ImageIcon logo = new ImageIcon("resources/icons/Coda_icon.png");
        this.setIconImage(logo.getImage());
        mainPanel = new JPanel() {
        	private static final long serialVersionUID = 1L;
			@Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Image image = imageIcon.getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
            }
        };
        this.setContentPane(mainPanel);
        codaIcon = new ImageIcon("resources/icons/Coda_icon.png");
        ImageIcon imageIcon2 = new ImageIcon("resources/icons/new_back_icon.png");
        Image image2 = imageIcon2.getImage().getScaledInstance(100, 67, java.awt.Image.SCALE_SMOOTH);
        imageIcon2 = new ImageIcon(image2);
        backButton = new JButton();
        backButton.setIcon(imageIcon2);
        backButton.setSize(82, 67);
        writeFont = new Font("Comic Sans", Font.BOLD, 15);
        data = new JPanel();
	    data.setBackground(new Color(0,0,0,0));
	}
	//Se crea este método para poder acceder al retroceso del resto de ventanas a traves de este 
	public JButton getBackButton() {
		return this.backButton;
	}
	//Se crea este método para mostrar cuando un campo sobre el que escribir está siendo seleccionado
	public void selectField(JTextField field) {
		field.addFocusListener(new FocusAdapter() {
		    @Override
		    public void focusGained(FocusEvent e) {
		    	field.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
		    }
		    @Override
		    public void focusLost(FocusEvent e) {
		    	field.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
		    }
		});
	}
  	//Función para limitar la cantidad de caracteres en los campos
	public static void setMaxCharacters(JTextField textField, int maxCharacters) {
	      DocumentFilter filter = new DocumentFilter() {
	          @Override
	          public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
	              if (fb.getDocument().getLength() + string.length() <= maxCharacters) {
	                  super.insertString(fb, offset, string, attr);
	              }
	          }
           @Override
	          public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
	              if (fb.getDocument().getLength() - length + text.length() <= maxCharacters) {
	                  super.replace(fb, offset, length, text, attrs);
               }
           }
	      };
	      ((AbstractDocument) textField.getDocument()).setDocumentFilter(filter);
	}
	//Establece las propiedades principales de los campos de escritura
	public void setJTextFieldProperties(JTextField field) {
		field.setFont(writeFont);
		field.setBackground(Color.BLACK); 
		field.setForeground(Color.WHITE);
        Font font = field.getFont();
        field.setFont(new Font(font.getName(), Font.PLAIN, 18));
        Border border = BorderFactory.createLineBorder(Color.WHITE, 2); // Establecer el color y grosor de la línea de separación
        field.setBorder(border);		   
	}
}
