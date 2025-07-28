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

public class AcceptWindow extends JDialog{
	private static final long serialVersionUID = 1L;
	private String message;
	private JPanel buttonsPanel2;
	private JButton okButton;
	private JButton cancelButton;
	private String title;
	private boolean ok; 
	//Clase que servirá para generar ventanas de aceptación de las funciones sensibles como modificación o eliminación
	public AcceptWindow(Frame parent, String name, String title) {
		super(parent, true);
		this.message = name;
		this.title = title;
		this.ok = false;
		initGUI();
	}
	private void initGUI() {
	    setTitle(title);
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
	    buttonsPanel2 = new JPanel();
	    buttonsPanel2.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 0));
	    buttonsPanel2.setBackground(new Color(0,0,0,0));
	    buttonsPanel2.setOpaque(true);
	    buttonsPanel2.setPreferredSize(new Dimension(180, 35));
	    okButton = new JButton();
	    okButton.setText("SI");
	    okButton.setPreferredSize(new Dimension(50, 35));
	    okButton.setFont(new Font("Comic Sans", Font.BOLD, 10));
	    okButton.setForeground(Color.white);
	    cancelButton = new JButton();
	    cancelButton.setText("NO");
	    cancelButton.setPreferredSize(new Dimension(50, 35));
	    cancelButton.setFont(new Font("Comic Sans", Font.BOLD, 10));
	    cancelButton.setForeground(Color.white);
	    ImageIcon imageIcon3 = new ImageIcon("resources/icons/Fondo_campos.png");
        Image image2 = imageIcon3.getImage().getScaledInstance(55, 33, java.awt.Image.SCALE_SMOOTH);
        imageIcon3 = new ImageIcon(image2);
	    okButton.setIcon(imageIcon3);
	    okButton.setVerticalTextPosition(SwingConstants.CENTER);
	    okButton.setHorizontalTextPosition(SwingConstants.CENTER);
	    cancelButton.setIcon(imageIcon3);
	    cancelButton.setVerticalTextPosition(SwingConstants.CENTER);
	    cancelButton.setHorizontalTextPosition(SwingConstants.CENTER);
	    //Se añaden los correspondientes listener
	    okButton.addActionListener(new ActionListener() {
	       	 @Override
	           public void actionPerformed(ActionEvent e) {
	       		AcceptWindow.this.ok = true;
	       		setVisible(false);
	       	 }
	      }); 	
			cancelButton.addActionListener(new ActionListener() {
	       	 @Override
	           public void actionPerformed(ActionEvent e) {
	       		setVisible(false);
	       	 }
	      }); 	
	    buttonsPanel2.add(okButton);
	    buttonsPanel2.add(cancelButton);
	    mainPanel.add(buttonsPanel2);
	    setPreferredSize(new Dimension(450, 105));
	    pack();
	    setLocationRelativeTo(getParent());
	    setVisible(true);
	}
	
	public boolean getOk() {
		return this.ok;
	}
}