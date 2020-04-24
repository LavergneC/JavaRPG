package game_management.Interfaces;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import entities.character.Ninja;
import entities.character.Warrior;
import entities.character.Wizard;
import game_management.Classic_RPG;

import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.swing.JToggleButton;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import java.awt.FlowLayout;

public class PlayerCreation extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private boolean readyToLaunch = false;
	private boolean JAR = true;
	private List<String> list_carac;
	/**
	 * Create the frame.
	 */
	public PlayerCreation(Classic_RPG game) {
		
		list_carac = new ArrayList<String>();
		list_carac.add("the annoying");
		list_carac.add("the brainless");
		list_carac.add("the useless");
		list_carac.add("the pigs's friend");
		list_carac.add("the insignificant");
		list_carac.add("the courageous");
		list_carac.add("the adventurous");
		list_carac.add("the hobo");
		list_carac.add("the brave");
		list_carac.add("the idiot");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 604, 357);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel Header = new JPanel();
		contentPane.add(Header, BorderLayout.NORTH);
		Header.setLayout(new BorderLayout(0, 0));

		JLabel lblNewLabel = new JLabel("Classic RPG");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 20));
		Header.add(lblNewLabel, BorderLayout.CENTER);

		JLabel lblNewLabel_1 = new JLabel("Character creation");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		Header.add(lblNewLabel_1, BorderLayout.SOUTH);

		JPanel Body = new JPanel();
		contentPane.add(Body, BorderLayout.CENTER);
		Body.setLayout(new BorderLayout(0, 0));

		JPanel Name_panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) Name_panel.getLayout();
		flowLayout.setHgap(18);
		Name_panel.setBorder(new EmptyBorder(10, 0, 10, 0));
		Body.add(Name_panel, BorderLayout.NORTH);

		JLabel lblNewLabel_2 = new JLabel("Name");
		Name_panel.add(lblNewLabel_2);

		JToggleButton tglbtnNewToggleButton = new JToggleButton("");
		JToggleButton tglbtnNewToggleButton_2 = new JToggleButton("");
		JToggleButton tglbtnNewToggleButton_1 = new JToggleButton("");
		tglbtnNewToggleButton.setEnabled(false);
		tglbtnNewToggleButton_1.setEnabled(false);
		tglbtnNewToggleButton_2.setEnabled(false);

		textField = new JTextField();
		Name_panel.add(textField);
		textField.setColumns(10);

		JButton okName = new JButton("ok");
		okName.setBackground(Color.white);
		okName.setFocusable(false);
		okName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!textField.getText().isEmpty()) {
					tglbtnNewToggleButton.setEnabled(true);
					tglbtnNewToggleButton_1.setEnabled(true);
					tglbtnNewToggleButton_2.setEnabled(true);
				}
			}
		});

		Name_panel.add(okName);
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!textField.getText().isEmpty()) {
					tglbtnNewToggleButton.setEnabled(true);
					tglbtnNewToggleButton_1.setEnabled(true);
					tglbtnNewToggleButton_2.setEnabled(true);
				}
			}
		});

		JPanel job_panel = new JPanel();
		Body.add(job_panel, BorderLayout.CENTER);

		ImageIcon icon = null;
		if(JAR) {
			try {
				icon = new ImageIcon(getClass().getResource("/Images/knight.png"));
			}catch(Exception e) {
				JAR = false; // switch to non-JAR mode
				icon = new ImageIcon("Images/knight.png");
			}
		}
		else
			icon = new ImageIcon("Images/knight.png");
		
		Image img = icon.getImage() ;  
		Image newimg = img.getScaledInstance( 150, 150,  java.awt.Image.SCALE_SMOOTH );
		icon = new ImageIcon(newimg);

		JButton validateButton = new JButton("Validate");
		job_panel.add(tglbtnNewToggleButton);
		tglbtnNewToggleButton.setIcon(icon);
		tglbtnNewToggleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tglbtnNewToggleButton_2.setSelected(false);
				tglbtnNewToggleButton_1.setSelected(false);
				validateButton.setEnabled(true);
			}
		});
		tglbtnNewToggleButton.setFocusable(false);
		tglbtnNewToggleButton_1.setFocusable(false);
		tglbtnNewToggleButton_2.setFocusable(false);
		tglbtnNewToggleButton.setBackground(Color.WHITE);
		tglbtnNewToggleButton_1.setBackground(Color.WHITE);
		tglbtnNewToggleButton_2.setBackground(Color.WHITE);

		ImageIcon icon2 = null;
		if(JAR) {
			try {
				icon2 = new ImageIcon(getClass().getResource("/Images/wizard.png"));
			}catch(Exception e) {
				JAR = false; // switch to non-JAR mode
				icon2 = new ImageIcon("Images/wizard.png");
			}
		}
		else
			icon2 = new ImageIcon("Images/wizard.png");
		
		Image img2 = icon2.getImage() ;  
		Image newimg2 = img2.getScaledInstance( 150, 150,  java.awt.Image.SCALE_SMOOTH );
		icon2 = new ImageIcon(newimg2);

		job_panel.add(tglbtnNewToggleButton_1);
		tglbtnNewToggleButton_1.setIcon(icon2);
		tglbtnNewToggleButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tglbtnNewToggleButton_2.setSelected(false);
				tglbtnNewToggleButton.setSelected(false);
				validateButton.setEnabled(true);
			}
		});

		ImageIcon icon3 = null;
		if(JAR) {
			try {
				icon3 = new ImageIcon(getClass().getResource("/Images/ninja.png"));
			}catch(Exception e) {
				JAR = false; // switch to non-JAR mode
				icon3 = new ImageIcon("Images/ninja.png");
			}
		}
		else
			icon3 = new ImageIcon("Images/ninja.png");
		
		Image img3 = icon3.getImage() ;  
		Image newimg3 = img3.getScaledInstance( 150, 150,  java.awt.Image.SCALE_SMOOTH );
		icon3 = new ImageIcon(newimg3);

		job_panel.add(tglbtnNewToggleButton_2);
		tglbtnNewToggleButton_2.setIcon(icon3);
		tglbtnNewToggleButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tglbtnNewToggleButton.setSelected(false);
				tglbtnNewToggleButton_1.setSelected(false);
				validateButton.setEnabled(true);
			}
		});

		JPanel footer = new JPanel();
		footer.setBorder(new EmptyBorder(10, 0, 5, 30));
		contentPane.add(footer, BorderLayout.SOUTH);
		footer.setLayout(new BorderLayout(0, 0));

		footer.add(validateButton, BorderLayout.EAST);
		validateButton.setFocusable(false);
		validateButton.setEnabled(false);
		validateButton.setBackground(Color.white);
		
		validateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String nom = textField.getText();
				if (nom.length() <= 10 ) {
					nom += ' ' + list_carac.get(nom.length() % 10);
				}
				else if (nom.length() > 18) {
					nom = "annoying long name";
				}
				if(tglbtnNewToggleButton.isSelected())
					game.setPlayer(new Warrior(nom));
				else if(tglbtnNewToggleButton_1.isSelected())
					game.setPlayer(new Wizard(nom));
				else if(tglbtnNewToggleButton_2.isSelected())
					game.setPlayer(new Ninja(nom));
				else {
					System.out.println("Not any Job selected !");
					return;
				}
				readyToLaunch = true;
				close();
			}
		});
	}
	
	public void close() {
		this.setVisible(false);
	}
	
	public void waitPlayerCreated() {
		while(!readyToLaunch) {
			try {
				TimeUnit.MILLISECONDS.sleep(250);
			} catch (InterruptedException e) {}
		}
	}

}
