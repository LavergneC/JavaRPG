package game_management.Interfaces;


import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import entities.Entity;
import entities.Monster;
import entities.Player;
import entities.character.Ninja;
import entities.character.Warrior;
import entities.character.Wizard;
import entities.characterisics.MagicianCharacteristiques;
import game_management.Action_Enums.Attack;
import game_management.Action_Enums.Game_action;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import java.awt.Image;
import java.awt.Component;
import java.awt.Dimension;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javax.swing.JProgressBar;
import javax.swing.JScrollPane;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;

import java.awt.event.ActionEvent;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JTextPane;
import javax.swing.UIManager;

public class GUI extends JFrame {
	public static String message;
	private static boolean JAR = true; // Do not change that
	private JPanel contentPane;

	private JProgressBar HPBar;
	private JProgressBar manaBar;
	private JProgressBar manaBarShield;
	private JProgressBar staminaBar;
	private JProgressBar xpBar;

	private JLabel lblLvlGame;
	private JLabel lblWave;
	private JLabel lblTurn;
	private JLabel lblLvl;

	private JPanel monstersPanel;
	private JPanel Actions;
	
	private JPanel commandPanel;

	private int position = 0;

	private Game_action game_action = Game_action.PENDING;
	private Attack attackType;
	private int monsterTarget = -1;

	JButton button_attack_basic;
	JButton button_attack_special;
	JButton button_def;
	JButton button_rest;

	static public void reset_message() {
		message = "";
	}

	static public void edit_message(String s_message) {
		message += '\n' + s_message;
	}

	/**
	 * Create the frame.
	 */

	public GUI(Player player) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 850, 550);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel Header = new JPanel();
		Header.setBackground(Color.WHITE);
		contentPane.add(Header, BorderLayout.NORTH);
		Header.setLayout(new BorderLayout(0, 0));

		JPanel headTexts = new JPanel();
		headTexts.setBackground(Color.WHITE);
		FlowLayout flowLayout = (FlowLayout) headTexts.getLayout();
		flowLayout.setAlignOnBaseline(true);
		flowLayout.setAlignment(FlowLayout.LEFT);
		Header.add(headTexts, BorderLayout.WEST);

		JPanel H_text = new JPanel();
		H_text.setBackground(Color.WHITE);
		headTexts.add(H_text);
		H_text.setLayout(new GridLayout(0, 1, 0, 2));


		ImageIcon player_img = null;

		if(player instanceof Ninja)
			player_img = createImage("Images/ninja.png", 45);
		else if(player instanceof Wizard)
			player_img = createImage("Images/wizard.png", 45);
		else if(player instanceof Warrior)
			player_img = createImage("Images/knight.png", 45);

		JLabel player_image = new JLabel(player_img);
		H_text.add(player_image);

		JLabel lblName = new JLabel(player.getName());
		H_text.add(lblName);
		lblName.setHorizontalAlignment(SwingConstants.CENTER);

		JPanel bars = new JPanel();
		Header.add(bars, BorderLayout.CENTER);
		bars.setBorder(new EmptyBorder(0, 0, 0, 10));
		bars.setBackground(Color.WHITE);
		bars.setLayout(new GridLayout(0, 2, 0, 0));

		JPanel HP_panel = new JPanel();
		HP_panel.setBackground(Color.WHITE);
		bars.add(HP_panel);

		JLabel lblHP = new JLabel(createImage("Images/heart.png", 25));
		lblHP.setHorizontalAlignment(SwingConstants.LEFT);

		HPBar = new JProgressBar();
		HPBar.setStringPainted(true);
		HPBar.setMaximum(player.getHp());
		HPBar.setValue(player.getHp());
		HPBar.setForeground(Color.RED);
		HPBar.setString(HPBar.getValue() + "/" + HPBar.getMaximum());
		HP_panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		HP_panel.add(lblHP);
		HP_panel.add(HPBar);

		JPanel stamina_panel = new JPanel();
		stamina_panel.setBackground(Color.WHITE);
		bars.add(stamina_panel);

		JLabel lblStamina = new JLabel(createImage("Images/flash.png", 25));
		lblStamina.setHorizontalAlignment(SwingConstants.CENTER);

		staminaBar = new JProgressBar();
		staminaBar.setMaximum(player.getStamina());
		staminaBar.setValue(player.getStamina());
		staminaBar.setStringPainted(true);
		staminaBar.setString(player.getStamina() + "/" + staminaBar.getMaximum());

		staminaBar.setForeground(UIManager.getColor("OptionPane.questionDialog.border.background"));
		stamina_panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		stamina_panel.add(lblStamina);
		stamina_panel.add(staminaBar);

		JPanel mana_panel = new JPanel();
		mana_panel.setBackground(Color.WHITE);
		bars.add(mana_panel);

		JPanel mana_shield_panel = new JPanel();
		bars.add(mana_shield_panel);
		mana_shield_panel.setBackground(Color.WHITE);

		JLabel lblMana = new JLabel("Mana");
		lblMana.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel lblManaShield = new JLabel("Mana_shield");
		lblManaShield.setHorizontalAlignment(SwingConstants.CENTER);

		JPanel header_bot = new JPanel();
		Header.add(header_bot, BorderLayout.SOUTH);
		header_bot.setLayout(new BorderLayout(0, 0));

		JPanel xp_panel = new JPanel();
		xp_panel.setBackground(Color.WHITE);
		header_bot.add(xp_panel, BorderLayout.NORTH);
		xp_panel.setLayout(new BorderLayout(0, 0));

		lblLvl = new JLabel("lvl. " + player.getLevel());
		xp_panel.add(lblLvl, BorderLayout.NORTH);
		lblLvl.setHorizontalAlignment(SwingConstants.CENTER);

		xpBar = new JProgressBar();
		xpBar.setValue(0);
		xpBar.setForeground(Color.GREEN);
		xpBar.setBorder(null);
		xp_panel.add(xpBar);

		JPanel infoBar = new JPanel();
		infoBar.setBorder(new EmptyBorder(12, 0, 0, 0));
		header_bot.add(infoBar);
		infoBar.setBackground(Color.WHITE);
		infoBar.setLayout(new GridLayout(0, 3, 0, 0));

		lblLvlGame = new JLabel("Level X - Default level Name");
		lblLvlGame.setVerticalAlignment(SwingConstants.TOP);
		lblLvlGame.setHorizontalAlignment(SwingConstants.CENTER);
		lblLvlGame.setBackground(Color.WHITE);
		infoBar.add(lblLvlGame);

		lblWave = new JLabel("Wave XX");
		lblWave.setHorizontalAlignment(SwingConstants.CENTER);
		lblWave.setBackground(Color.WHITE);
		infoBar.add(lblWave);

		lblTurn = new JLabel("Turn XX");
		lblTurn.setHorizontalAlignment(SwingConstants.CENTER);
		lblTurn.setBackground(Color.WHITE);
		infoBar.add(lblTurn);

		JSeparator separator_3 = new JSeparator();
		separator_3.setForeground(Color.BLACK);
		infoBar.add(separator_3);

		JSeparator separator_2_1 = new JSeparator();
		separator_2_1.setForeground(Color.BLACK);
		infoBar.add(separator_2_1);

		JSeparator separator_1_1 = new JSeparator();
		separator_1_1.setForeground(Color.BLACK);
		infoBar.add(separator_1_1);

		Actions = new JPanel();
		contentPane.add(Actions, BorderLayout.WEST);
		Actions.setLayout(new GridLayout(0, 1, 0, 0));

		JButton button_attack = new JButton("Attack");
		button_attack_basic = new JButton("<html>Basic<br>attack</html>");
		button_attack_special = new JButton("<html>Special<br>attack</html>");
		JButton button_attack_back = new JButton("<");
		button_def = new JButton("Defence");
		button_rest = new JButton("Rest");

		button_attack_basic.setBackground(Color.WHITE);
		button_attack_special.setBackground(Color.WHITE);
		button_attack_back.setBackground(Color.WHITE);
		button_attack.setBackground(Color.WHITE);
		button_def.setBackground(Color.WHITE);
		button_rest.setBackground(Color.WHITE);

		button_attack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
				Actions.remove(button_attack);
				Actions.add(button_attack_basic);
				Actions.add(button_attack_special);
				Actions.add(button_attack_back);
				Actions.remove(button_def);
				Actions.remove(button_rest);

				Actions.revalidate();
				Actions.repaint();
			}
		});
		Actions.add(button_attack);

		button_attack_basic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Attack basic");
				game_action = Game_action.ATTACK;
				attackType = Attack.BASIC_ATTACK;
				setSwordsVisible(true);
			}
		});
		//Actions.add(button_attack_basic);

		button_attack_special.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				game_action = Game_action.ATTACK;
				attackType = Attack.SPECIAL_ATTACK;
				setSwordsVisible(true);
			}
		});
		//Actions.add(button_attack_special);

		button_attack_back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
				Actions.remove(button_attack_back);
				Actions.add(button_attack);
				Actions.add(button_def);
				Actions.add(button_rest);
				Actions.remove(button_attack_basic);
				Actions.remove(button_attack_special);

				Actions.revalidate();
				Actions.repaint();
			}
		});
		//Actions.add(button_attack_back);

		Actions.add(button_def);
		button_def.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				game_action = Game_action.DEFENSE;
				System.out.println("Defense");
			}
		});

		Actions.add(button_rest);
		button_rest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				game_action = Game_action.REST;
				System.out.println("rest");
			}
		});
		
		commandPanel = new JPanel();
		commandPanel.setBorder(new EmptyBorder(30, 30, 0, 30));
		contentPane.add(commandPanel, BorderLayout.EAST);
		commandPanel.setLayout(new GridLayout(0, 1, 0, 0));
		commandPanel.setPreferredSize(new Dimension(400, 350));
		
		commandPanel.add(new JLabel(""));
		commandPanel.add(new JLabel(""));
		commandPanel.add(new JLabel(""));
		commandPanel.add(new JLabel(""));
		//TODO c'est ici
		
		monstersPanel = new JPanel();
		monstersPanel.setBorder(new EmptyBorder(30, 30, 0, 30));
		contentPane.add(monstersPanel, BorderLayout.CENTER);
		monstersPanel.setLayout(new GridLayout(0, 1, 0, 0));

		JPanel monster_1 = new JPanel();
		monstersPanel.add(monster_1);
		monster_1.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 5));

		JLabel monster_1_name = new JLabel("monster1_name");
		monster_1.add(monster_1_name);

		JProgressBar progressBar = new JProgressBar();
		progressBar.setForeground(Color.RED);
		progressBar.setStringPainted(true);
		progressBar.setValue(50);
		monster_1.add(progressBar);

		/*JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);*/

		if (player instanceof Wizard) {
			lblMana = new JLabel(createImage("Images/mana.png", 25));
			lblMana.setHorizontalAlignment(SwingConstants.CENTER);

			manaBar = new JProgressBar();
			manaBar.setMaximum(((Wizard)player).getMana());
			manaBar.setValue(manaBar.getMaximum());
			manaBar.setStringPainted(true);
			manaBar.setString(player.getMana() + "/" + manaBar.getMaximum());
			manaBar.setForeground(Color.BLUE);
			mana_panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			mana_panel.add(lblMana);
			mana_panel.add(lblMana);

			mana_panel.add(manaBar);

			lblManaShield = new JLabel(createImage("Images/shield.png", 25));
			lblManaShield.setHorizontalAlignment(SwingConstants.CENTER);

			manaBarShield = new JProgressBar();
			manaBarShield.setMaximum(((Wizard)player).getMagicShieldMaxHp());
			manaBarShield.setValue(0);
			manaBarShield.setStringPainted(true);
			manaBarShield.setString("0/" + manaBarShield.getMaximum());
			manaBarShield.setForeground(Color.BLUE);
			mana_shield_panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			mana_shield_panel.add(lblManaShield);
			mana_shield_panel.add(lblManaShield);

			mana_shield_panel.add(manaBarShield);
		}

		setSwordsVisible(false);
	}

	public void updatePlayerBars(Player p) {
		HPBar.setMaximum(p.getCharacteristics().getMax_hp());
		HPBar.setValue(p.getHp());
		HPBar.setString(HPBar.getValue() + "/" + HPBar.getMaximum());

		staminaBar.setMaximum(p.getCharacteristics().getMax_stamina());
		staminaBar.setValue(p.getStamina());
		staminaBar.setString(staminaBar.getValue() + "/" + staminaBar.getMaximum());
		
		xpBar.setMaximum(p.getNext_lvl_xp());
		xpBar.setValue(p.getXp());

		if (p instanceof Wizard) {
			manaBar.setMaximum(((MagicianCharacteristiques)p.getCharacteristics()).getManaMax());
			manaBar.setValue(p.getMana());
			manaBar.setString(manaBar.getValue() + "/" + manaBar.getMaximum());

			manaBarShield.setValue(
					((Wizard)p).getMagicShieldHp()
					);
			manaBarShield.setString(manaBarShield.getValue() + "/" + manaBarShield.getMaximum());
		}
	}

	public void infobarNewLevel(int levelNumber, String levelName) {
		lblLvlGame.setText("Level " + levelNumber + " - " + levelName);
		lblWave.setText("Wave 1");
		lblTurn.setText("Turn 1");
	}

	public void infobarSetWave(int waveNumber) {
		lblWave.setText("Wave " + waveNumber);
		lblTurn.setText("Turn 1");
	}

	public void infobarSetTurn(int turnNumber) {
		lblTurn.setText("Turn " + turnNumber);
	}

	public void updateMonsters(ArrayList<Monster> wave) {
		monstersPanel.removeAll();

		for (Monster monster : wave) {
			JPanel new_monster_panel = new JPanel();
			new_monster_panel.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 5));

			JLabel new_monster_name = new JLabel(monster.getName());
			new_monster_panel.add(new_monster_name);

			JProgressBar progressBar = new JProgressBar();
			progressBar.setForeground(Color.RED);
			progressBar.setStringPainted(true);
			progressBar.setMaximum(monster.getCharacteristics().getMax_hp());
			progressBar.setValue(monster.getHp());
			progressBar.setString(progressBar.getValue() + "/" + progressBar.getMaximum());
			new_monster_panel.add(progressBar);

			JButton swordButton = swordButton(wave.indexOf(monster));
			new_monster_panel.add(swordButton);

			monstersPanel.add(new_monster_panel);
		}
		monstersPanel.revalidate();
		monstersPanel.repaint();
	}
	
	public void generate_commands(String typeCommande, Entity entity) {
		commandPanel.remove(0);
		JPanel text_panel = new JPanel();
		//text_panel.setPreferredSize(new Dimension(100, 20));
		text_panel.setLayout(new FlowLayout());
		
		JLabel entity_name = new JLabel(entity.getName());
		entity_name.setHorizontalAlignment(SwingConstants.RIGHT);
		
		text_panel.add(entity_name);
		ImageIcon img = null;
		if (typeCommande == "Rest") {
			img = createImage("Images/rest.png", 18);
		}
		else if (typeCommande == "Protect"){
			img = createImage("Images/protect.png", 18);
		}
		JLabel imageAttaque = new JLabel(img);
		text_panel.add(imageAttaque);
		
		commandPanel.add(text_panel);
		commandPanel.revalidate();
		commandPanel.repaint();
	}
	
	public void generate_commands(String typeCommande, Entity entity1, Entity entity2) {
		
		commandPanel.remove(0);
		JPanel text_panel = new JPanel();
		//text_panel.setPreferredSize(new Dimension(100, 20));
		text_panel.setLayout(new FlowLayout());
		
		JLabel attacker_name = new JLabel(entity1.getName());
		attacker_name.setHorizontalAlignment(SwingConstants.RIGHT);
		
		text_panel.add(attacker_name);
		ImageIcon img = null;
		if (typeCommande == "Attack") {
			img = createImage("Images/sword.png", 14);
		}
		else if (typeCommande == "Special"){
			img = createImage("Images/SpecialHit.png", 18);
		}
		JLabel imageAttaque = new JLabel(img);
		text_panel.add(imageAttaque);
		
		JLabel attacked_name = new JLabel(entity2.getName());
		attacker_name.setHorizontalAlignment(SwingConstants.LEFT);
		text_panel.add(attacked_name);
		
		JPanel big_panel = new JPanel();
		//big_panel.setPreferredSize(new Dimension(75, 30));
		big_panel.setLayout(new GridLayout(2, 1, 0, 0));
		big_panel.add(text_panel);
		JLabel radom_txt = new JLabel("Truc a pris truc de dommages");
		radom_txt.setHorizontalAlignment(SwingConstants.CENTER);
		radom_txt.setVerticalAlignment(SwingConstants.TOP);
		big_panel.add(radom_txt);
		
		commandPanel.add(big_panel);
		commandPanel.revalidate();
		commandPanel.repaint();
	}

	public JButton swordButton(int indexMonster){
		ImageIcon icon = null;
		if(JAR) {
			try {
				icon = new ImageIcon(getClass().getResource("/Images/sword.png"));
			}catch(Exception e) {
				JAR = false; // switch to non-JAR mode
				icon = new ImageIcon("Images/sword.png");
			}
		}
		else
			icon = new ImageIcon("Images/sword.png");

		Image img = icon.getImage() ;  
		Image newimg = img.getScaledInstance( 16, 16,  java.awt.Image.SCALE_SMOOTH ) ;  
		icon = new ImageIcon(newimg);
		JButton swordButton = new JButton("");
		swordButton.setIcon(icon);
		swordButton.setBackground(Color.WHITE);
		swordButton.setPreferredSize(new Dimension(30, 30));
		swordButton.setFocusable(false);
		swordButton.setVisible(false);

		swordButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				monsterTarget = indexMonster;
				setSwordsVisible(false);
			}
		});

		return swordButton;
	}

	public void setButtonsEnable(boolean  b) {
		for(Component button : Actions.getComponents()) {
			button.setEnabled(b);
		}
	}

	public void setButtonsEnable(boolean  b, Player player) {
		if(b == false) {
			setButtonsEnable(false);
			return;
		}
		setButtonsEnable(true);
		button_attack_basic.setEnabled(player.actionPossible(Attack.BASIC_ATTACK));
		button_attack_special.setEnabled(player.actionPossible(Attack.SPECIAL_ATTACK));
		button_def.setEnabled(player.actionPossible(Game_action.DEFENSE));
		button_rest.setEnabled(player.actionPossible(Game_action.REST));
	}

	public void setSwordsVisible(boolean b) {
		for(Component c : monstersPanel.getComponents()) {
			for(Component c2 : ((JPanel)c).getComponents()){
				if(c2 instanceof JButton) 
					c2.setVisible(b);
			}
		}
	}

	public Game_action getGame_action() {
		return game_action;
	}

	public void setGame_action(Game_action game_action) {
		this.game_action = game_action;
	}

	public int getMonsterTarget() {
		return monsterTarget;
	}

	public void resetMonsterTarget() {
		monsterTarget = -1;
	}

	public Attack getAttackType() {
		return attackType;
	}

	private void delaySec(int s) {
		try {
			TimeUnit.SECONDS.sleep(s);
		}
		catch(InterruptedException ex)
		{
			Thread.currentThread().interrupt();
		}
	}

	public void update_playerLevel(int new_level) {
		lblLvl.setText("lvl. " + new_level);
	}

	private ImageIcon createImage(String source, int size) {
		ImageIcon icon3 = null;
		if(JAR) {
			try {
				icon3 = new ImageIcon(getClass().getResource("/" + source));
			}catch(Exception e) {
				JAR = false; // switch to non-JAR mode
				icon3 = new ImageIcon(source);
			}
		}
		else
			icon3 = new ImageIcon(source);

		Image img3 = icon3.getImage() ;  
		Image newimg3 = img3.getScaledInstance( size, size,  java.awt.Image.SCALE_SMOOTH );

		return new ImageIcon(newimg3);
	}
}
