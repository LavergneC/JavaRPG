package game_management;


import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import entities.Monster;
import entities.Player;
import entities.character.Wizard;
import entities.characterisics.MagicianCharacteristiques;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Component;
import java.awt.Dimension;

import java.util.ArrayList;

import javax.swing.JProgressBar;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class GUI extends JFrame {
	private JPanel contentPane;

	private JProgressBar HPBar;
	private JProgressBar manaBar;
	private JProgressBar staminaBar;

	private JLabel lblLvlGame;
	private JLabel lblWave;
	private JLabel lblTurn;

	private JPanel monstersPanel;
	private JPanel Actions;

	private Game_action game_action = Game_action.PENDING;
	private Attack attackType;
	private int monsterTarget = -1;
	
	JButton button_attack_basic;
	JButton button_attack_special;
	JButton button_def;
	JButton button_rest;

	/**
	 * Create the frame.
	 */

	public GUI(Player player) {
		System.out.println("PLOP");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 706, 490);
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
		H_text.setLayout(new GridLayout(0, 1, 0, 30));

		JLabel lblName = new JLabel(player.getName());
		H_text.add(lblName);
		lblName.setHorizontalAlignment(SwingConstants.LEFT);

		JLabel lblLvl = new JLabel("lvl. 1");
		H_text.add(lblLvl);

		JPanel bars = new JPanel();
		Header.add(bars, BorderLayout.CENTER);
		bars.setBorder(new EmptyBorder(0, 0, 0, 10));
		bars.setBackground(Color.WHITE);
		bars.setLayout(new GridLayout(0, 2, 0, 0));

		JPanel HP_panel = new JPanel();
		HP_panel.setBackground(Color.WHITE);
		bars.add(HP_panel);

		JLabel lblHP = new JLabel("HP");
		lblHP.setHorizontalAlignment(SwingConstants.LEFT);

		HPBar = new JProgressBar();
		HPBar.setStringPainted(true);
		HPBar.setMaximum(player.getHp());
		HPBar.setValue(player.getHp());
		HPBar.setForeground(Color.RED);
		HPBar.setString(HPBar.getValue() + "/" + HPBar.getMaximum());
		GroupLayout gl_HP_panel = new GroupLayout(HP_panel);
		gl_HP_panel.setHorizontalGroup(gl_HP_panel.createParallelGroup(Alignment.LEADING)
				.addGap(0, 250, Short.MAX_VALUE)
				.addGroup(gl_HP_panel
						.createSequentialGroup().addGap(18).addComponent(lblHP).addGap(27).addComponent(HPBar,
								GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGap(38)));
		gl_HP_panel.setVerticalGroup(gl_HP_panel.createParallelGroup(Alignment.LEADING).addGap(0, 56, Short.MAX_VALUE)
				.addGroup(gl_HP_panel.createSequentialGroup().addContainerGap()
						.addGroup(gl_HP_panel.createParallelGroup(Alignment.BASELINE).addComponent(lblHP).addComponent(
								HPBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
						.addContainerGap(24, Short.MAX_VALUE)));
		HP_panel.setLayout(gl_HP_panel);

		JPanel stamina_panel = new JPanel();
		stamina_panel.setBackground(Color.WHITE);
		bars.add(stamina_panel);

		JLabel lblStamina = new JLabel("Stamina");
		lblStamina.setHorizontalAlignment(SwingConstants.CENTER);

		staminaBar = new JProgressBar();
		staminaBar.setMaximum(player.getStamina());
		staminaBar.setValue(player.getStamina());
		staminaBar.setStringPainted(true);
		staminaBar.setString(player.getStamina() + "/" + staminaBar.getMaximum());
		staminaBar.setForeground(Color.GREEN);
		GroupLayout gl_stamina_panel = new GroupLayout(stamina_panel);
		gl_stamina_panel.setHorizontalGroup(gl_stamina_panel.createParallelGroup(Alignment.LEADING)
				.addGap(0, 250, Short.MAX_VALUE)
				.addGroup(gl_stamina_panel.createSequentialGroup().addContainerGap().addComponent(lblStamina)
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(staminaBar,
								GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(20, Short.MAX_VALUE)));
		gl_stamina_panel
		.setVerticalGroup(gl_stamina_panel.createParallelGroup(Alignment.LEADING).addGap(0, 56, Short.MAX_VALUE)
				.addGroup(gl_stamina_panel.createSequentialGroup()
						.addGroup(gl_stamina_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(staminaBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblStamina))
						.addContainerGap(24, Short.MAX_VALUE)));
		gl_stamina_panel.setAutoCreateContainerGaps(true);
		stamina_panel.setLayout(gl_stamina_panel);

		JPanel mana_panel = new JPanel();
		mana_panel.setBackground(Color.WHITE);
		bars.add(mana_panel);

		JLabel lblMana = new JLabel("Mana");
		lblMana.setHorizontalAlignment(SwingConstants.CENTER);

		JPanel infoBar = new JPanel();
		Header.add(infoBar, BorderLayout.SOUTH);
		infoBar.setBackground(Color.WHITE);
		infoBar.setLayout(new GridLayout(0, 3, 0, 0));

		JSeparator separator = new JSeparator();
		infoBar.add(separator);
		separator.setForeground(Color.BLACK);

		JSeparator separator_2 = new JSeparator();
		separator_2.setForeground(Color.BLACK);
		infoBar.add(separator_2);

		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(Color.BLACK);
		infoBar.add(separator_1);

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
				game_action = Game_action.DEFENCE;
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


		if (player instanceof Wizard) {
			manaBar = new JProgressBar();
			manaBar.setMaximum(player.getMana());
			manaBar.setValue(player.getMana());
			manaBar.setString(player.getMana() + "/" + ((MagicianCharacteristiques) player.getCharacteristics()).getManaMax());
			manaBar.setStringPainted(true);
			manaBar.setForeground(Color.BLUE);
			GroupLayout gl_mana_panel = new GroupLayout(mana_panel);
			gl_mana_panel.setHorizontalGroup(gl_mana_panel.createParallelGroup(Alignment.LEADING)
					.addGap(0, 250, Short.MAX_VALUE)
					.addGroup(gl_mana_panel.createSequentialGroup().addContainerGap().addComponent(lblMana)
							.addPreferredGap(ComponentPlacement.RELATED).addComponent(manaBar,
									GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(39, Short.MAX_VALUE)));
			gl_mana_panel.setVerticalGroup(
					gl_mana_panel.createParallelGroup(Alignment.LEADING).addGap(0, 56, Short.MAX_VALUE)
					.addGroup(gl_mana_panel.createSequentialGroup()
							.addGroup(gl_mana_panel.createParallelGroup(Alignment.BASELINE)
									.addComponent(manaBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
											GroupLayout.PREFERRED_SIZE)
									.addComponent(lblMana))
							.addContainerGap(24, Short.MAX_VALUE)));
			gl_mana_panel.setAutoCreateContainerGaps(true);
			mana_panel.setLayout(gl_mana_panel);
		}
		
		setSwordsVisible(false);
		System.out.println("PLoup");
	}

	public void updatePlayerBars(Player p) {
		HPBar.setValue(p.getHp());
		HPBar.setString(HPBar.getValue() + "/" + HPBar.getMaximum());

		staminaBar.setValue(p.getStamina());
		staminaBar.setString(staminaBar.getValue() + "/" + staminaBar.getMaximum());

		if (p instanceof Wizard) {
			manaBar.setValue(p.getMana());
			manaBar.setString(manaBar.getValue() + "/" + manaBar.getMaximum());
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

	public JButton swordButton(int indexMonster){
		ImageIcon icon = new ImageIcon("Images/sword.png");
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
		button_def.setEnabled(player.actionPossible(Action.DEFENSE));
		button_rest.setEnabled(player.actionPossible(Action.REST));
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
}
