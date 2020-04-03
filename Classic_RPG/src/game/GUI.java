
package game;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JSplitPane;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JProgressBar;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import java.awt.Button;
import javax.swing.JLayeredPane;

public class GUI extends JFrame {

	private JPanel contentPane;
	
	private JProgressBar HPBar;
	private JProgressBar manaBar;
	private JProgressBar staminaBar;
	
	private JLabel lblLvlGame;
	private JLabel lblWave;
	private JLabel lblTurn;

	/**
	 * Create the frame.
	 */

	public GUI(Player player) {
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
		gl_HP_panel.setHorizontalGroup(
				gl_HP_panel.createParallelGroup(Alignment.LEADING)
				.addGap(0, 250, Short.MAX_VALUE)
				.addGroup(gl_HP_panel.createSequentialGroup()
						.addGap(18)
						.addComponent(lblHP)
						.addGap(27)
						.addComponent(HPBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGap(38))
				);
		gl_HP_panel.setVerticalGroup(
				gl_HP_panel.createParallelGroup(Alignment.LEADING)
				.addGap(0, 56, Short.MAX_VALUE)
				.addGroup(gl_HP_panel.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_HP_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblHP)
								.addComponent(HPBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addContainerGap(24, Short.MAX_VALUE))
				);
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
		gl_stamina_panel.setHorizontalGroup(
				gl_stamina_panel.createParallelGroup(Alignment.LEADING)
				.addGap(0, 250, Short.MAX_VALUE)
				.addGroup(gl_stamina_panel.createSequentialGroup()
						.addContainerGap()
						.addComponent(lblStamina)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(staminaBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(20, Short.MAX_VALUE))
				);
		gl_stamina_panel.setVerticalGroup(
				gl_stamina_panel.createParallelGroup(Alignment.LEADING)
				.addGap(0, 56, Short.MAX_VALUE)
				.addGroup(gl_stamina_panel.createSequentialGroup()
						.addGroup(gl_stamina_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(staminaBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblStamina))
						.addContainerGap(24, Short.MAX_VALUE))
				);
		gl_stamina_panel.setAutoCreateContainerGaps(true);
		stamina_panel.setLayout(gl_stamina_panel);

		JPanel mana_panel = new JPanel();
		mana_panel.setBackground(Color.WHITE);
		bars.add(mana_panel);

		JLabel lblMana = new JLabel("Mana");
		lblMana.setHorizontalAlignment(SwingConstants.CENTER);

		JPanel place_and_time = new JPanel();
		Header.add(place_and_time, BorderLayout.SOUTH);
		place_and_time.setBackground(Color.WHITE);
		place_and_time.setLayout(new GridLayout(0, 3, 0, 0));

		JSeparator separator = new JSeparator();
		place_and_time.add(separator);
		separator.setForeground(Color.BLACK);

		JSeparator separator_2 = new JSeparator();
		separator_2.setForeground(Color.BLACK);
		place_and_time.add(separator_2);

		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(Color.BLACK);
		place_and_time.add(separator_1);

		lblLvlGame = new JLabel("Level X - Default level Name");
		lblLvlGame.setVerticalAlignment(SwingConstants.TOP);
		lblLvlGame.setHorizontalAlignment(SwingConstants.CENTER);
		lblLvlGame.setBackground(Color.WHITE);
		place_and_time.add(lblLvlGame);

		lblWave = new JLabel("Wave XX");
		lblWave.setHorizontalAlignment(SwingConstants.CENTER);
		lblWave.setBackground(Color.WHITE);
		place_and_time.add(lblWave);

		 lblTurn = new JLabel("Turn XX");
		lblTurn.setHorizontalAlignment(SwingConstants.CENTER);
		lblTurn.setBackground(Color.WHITE);
		place_and_time.add(lblTurn);

		JSeparator separator_3 = new JSeparator();
		separator_3.setForeground(Color.BLACK);
		place_and_time.add(separator_3);

		JSeparator separator_2_1 = new JSeparator();
		separator_2_1.setForeground(Color.BLACK);
		place_and_time.add(separator_2_1);

		JSeparator separator_1_1 = new JSeparator();
		separator_1_1.setForeground(Color.BLACK);
		place_and_time.add(separator_1_1);

		JPanel Actions = new JPanel();
		contentPane.add(Actions, BorderLayout.WEST);
		Actions.setLayout(new GridLayout(0, 1, 0, 0));

		Button button_attack = new Button("Attack");
		Actions.add(button_attack);

		Button button_def = new Button("Defence");
		Actions.add(button_def);

		Button button_rest = new Button("Rest");
		Actions.add(button_rest);

		if(player instanceof Wizard) {
			manaBar = new JProgressBar();
			manaBar.setMaximum(player.getMana());
			manaBar.setValue(player.getMana());
			manaBar.setString(player.getMana() + "/" + ((MagicianCharacteristiques)player.characteristics).getManaMax());
			manaBar.setStringPainted(true);
			manaBar.setForeground(Color.BLUE);
			GroupLayout gl_mana_panel = new GroupLayout(mana_panel);
			gl_mana_panel.setHorizontalGroup(
					gl_mana_panel.createParallelGroup(Alignment.LEADING)
					.addGap(0, 250, Short.MAX_VALUE)
					.addGroup(gl_mana_panel.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblMana)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(manaBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(39, Short.MAX_VALUE))
					);
			gl_mana_panel.setVerticalGroup(
					gl_mana_panel.createParallelGroup(Alignment.LEADING)
					.addGap(0, 56, Short.MAX_VALUE)
					.addGroup(gl_mana_panel.createSequentialGroup()
							.addGroup(gl_mana_panel.createParallelGroup(Alignment.BASELINE)
									.addComponent(manaBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addComponent(lblMana))
							.addContainerGap(24, Short.MAX_VALUE))
					);
			gl_mana_panel.setAutoCreateContainerGaps(true);
			mana_panel.setLayout(gl_mana_panel);
		}
	}

	public void updatePlayerBars(Player p) {
		HPBar.setValue(p.getHp());
		HPBar.setString(HPBar.getValue() + "/" + HPBar.getMaximum());

		staminaBar.setValue(p.getStamina());
		staminaBar.setString(staminaBar.getValue() + "/" + staminaBar.getMaximum());
		
		if(p instanceof Wizard) {
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
}
