package GUI.view;


import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Image;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
//Clase que servirá para generar ventanas de mensajes informativos sobre las acciones que se vayan realizando en el programa
public class MessageWindow extends JDialog{
	private static final long serialVersionUID = 1L;
	private String message;
	private String title;
	public MessageWindow(Frame parent, String message, String title, Dimension dimension) {
		super(parent, true);
		this.message = message;
		this.title = title;
		 setPreferredSize(dimension);
		initGUI();
	}
	private void initGUI() {
	    setTitle(this.title);
	    getContentPane().setBackground(Color.BLACK);

	    JPanel mainPanel = new JPanel();
	    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
	    setContentPane(mainPanel);
	    JLabel help = new JLabel(this.message);
	    help.setForeground(Color.WHITE); // Se establece el color del texto
	    help.setAlignmentX(Component.CENTER_ALIGNMENT);
	    mainPanel.add(help);
	    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
	    buttonPanel.setBackground(Color.BLACK); // Se establece el color de fondo del panel de botones
	    mainPanel.add(buttonPanel);
	    mainPanel.setBackground(Color.black);
	    JButton okButton = new JButton("OK");
	    okButton.setPreferredSize(new Dimension(50, 35));
	    okButton.setFont(new Font("Comic Sans", Font.BOLD, 10));
	    okButton.setForeground(Color.white);
	    okButton.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            setVisible(false);
	        }
	    });
	    ImageIcon imageIcon3 = new ImageIcon("resources/icons/Fondo_campos.png");
        Image image2 = imageIcon3.getImage().getScaledInstance(48, 33, java.awt.Image.SCALE_SMOOTH);
        imageIcon3 = new ImageIcon(image2);
	    okButton.setIcon(imageIcon3);
	    okButton.setVerticalTextPosition(SwingConstants.CENTER);
	    okButton.setHorizontalTextPosition(SwingConstants.CENTER);
	    buttonPanel.add(okButton);
	    pack();
	    setLocationRelativeTo(getParent());
	    setVisible(true);
	}
	//Modifica la altura de la ventana
	public void setHeight(int height) {
		setPreferredSize(new Dimension(500, height));
	}
}
