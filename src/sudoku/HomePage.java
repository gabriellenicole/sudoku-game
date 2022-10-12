package sudoku;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import javax.swing.border.AbstractBorder;

import sudoku.GamePage.RoundedCornerBorder;
import sudoku.SoundEffect.Volume;

// Swing Program Template
@SuppressWarnings("serial")
public class HomePage extends JFrame {
	// Name-constants to define the various dimensions
	public static final int WINDOW_WIDTH = 1440;
	public static final int WINDOW_HEIGHT = 900;
	public static final Color DARK_MODE_COLOR = new Color(18, 18, 18);
	public static final Color NOT_SO_DARK_MODE_COLOR = new Color(36, 36, 36);

	// ......

	// private variables of UI components
	// ......
	static int difficulty = 30;
	static boolean isDark = false;

	static int bgVolume = 50;
	private static String playerName = "Wesley";
	static JLabel lblPlayerName = new JLabel("", SwingConstants.RIGHT);

	public HomePage() {
		if (!(System.getProperty("os.name").startsWith("Mac"))) {
			if (System.getProperty("os.name") != "Mac") {
				try {
					UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
						| UnsupportedLookAndFeelException e1) {
					// TODO Auto-generated catch block
					System.out.println("Failed to import Look and Feel");
				}
			}
		}
		SoundEffect.BACKGROUND.playLoop();
		SoundEffect.BACKGROUND.volume = Volume.LOW;
		Container cp = this.getContentPane();

		cp.setLayout(new GridBagLayout());

		// Making panels
		JPanel left = new JPanel(), middle = new JPanel(), right = new JPanel();
		left.setLayout(new BoxLayout(left, BoxLayout.Y_AXIS));
		middle.setLayout(new GridBagLayout());
		right.setLayout(new OverlayLayout(right));

		GridBagConstraints c = new GridBagConstraints();

		c.fill = GridBagConstraints.BOTH;

		// Set panels weightage
		c.weightx = 1;
		c.weighty = 1;
		c.gridx = 0;
		c.insets = new Insets(30, 20, 0, 0);
		cp.add(left, c);

		c.weightx = 7;
		c.gridx = 1;
		c.insets = new Insets(180, 200, 180, 0);
		cp.add(middle, c);

		c.weightx = 3;
		c.gridx = 2;
		c.insets = new Insets(40, 20, 0, 0);
		cp.add(right, c);
		c.insets = new Insets(0, 0, 0, 0);

		// LEFT PANEL LAYOUT
		// Add close icon
		String pathClose = "img/close.png";
		String pathCloseDark = "img/close_dark.png";
		JButton btnClose = new JButton();
		btnClose.setContentAreaFilled(false);
		btnClose.setBorderPainted(false);
		left.add(btnClose);

		left.add(Box.createVerticalStrut(10));
		// Add dark mode icon
		String pathDarkMode = "img/dark_mode_light.png";
		String pathSun = "img/dark_mode_dark.png";
		JButton btnDarkMode = new JButton();
		btnDarkMode.setContentAreaFilled(false);
		btnDarkMode.setBorderPainted(false);
		left.add(btnDarkMode);

		left.add(Box.createVerticalStrut(10));

		// Add Setting icon
		String pathSetting = "img/settings_light.png";
		String pathSettingDark = "img/settings_dark.png";
		JButton btnSetting = new JButton();
		btnSetting.setContentAreaFilled(false);
		btnSetting.setBorderPainted(false);
		left.add(btnSetting);

		left.add(Box.createVerticalStrut(10));

		// Add help icon
		String pathHelp = "img/help_light.png";
		String pathHelpDark = "img/help_dark.png";
		JButton btnHelp = new JButton();
		btnHelp.setContentAreaFilled(false);
		btnHelp.setBorderPainted(false);
		left.add(btnHelp);

		// Initialize font
		Font Poppins_SemiBold_96f = null;
		Poppins_SemiBold_96f = importFont("img/Poppins-SemiBold.ttf", 96f);

		// MIDDLE PANEL LAYOUT
		// Title label
		c.fill = GridBagConstraints.VERTICAL;
		c.gridwidth = 6;
		c.gridx = 0;
		c.gridy = 0;
		JLabel lblTitle = new JLabel("Sudoku");
		lblTitle.setFont(Poppins_SemiBold_96f);
		middle.add(lblTitle, c);

		// Easy Button
		c.insets = new Insets(0, 0, 0, -300);
		c.ipadx = 0;
		c.gridwidth = 1;
		c.weighty = 1;
		c.weightx = 2;
		c.gridx = 2;
		c.gridy = 1;
		String pathEasyButton = "img/easy_button.png";
		String pathEasyButtonPressed = "img/easy_button_pressed.png";
		String pathEasyButtonDark = "img/easy_button_dark.png";
		String pathEasyButtonDarkPressed = "img/easy_button_dark_pressed.png";
		ImageIcon imageEasyButton = importImage(pathEasyButton);
		ImageIcon imageEasyButtonPressed = importImage(pathEasyButtonPressed);
		ImageIcon imageEasyButtonDark = importImage(pathEasyButtonDark);
		ImageIcon imageEasyButtonPressedDark = importImage(pathEasyButtonDarkPressed);
		JButton btnEasy = new JButton();
		btnEasy.setContentAreaFilled(false);
		btnEasy.setBorderPainted(false);
		middle.add(btnEasy, c);
		btnEasy.getModel();

		// Medium Button
		c.insets = new Insets(0, 0, 0, 0);
		c.gridx = 3;
		String pathMediumButton = "img/medium_button.png";
		String pathMediumButtonPressed = "img/medium_button_pressed.png";
		String pathMediumButtonDark = "img/medium_button_dark.png";
		String pathMediumButtonDarkPressed = "img/medium_button_dark_pressed.png";
		ImageIcon imageMediumButton = importImage(pathMediumButton);
		ImageIcon imageMediumButtonPressed = importImage(pathMediumButtonPressed);
		ImageIcon imageMediumButtonDark = importImage(pathMediumButtonDark);
		ImageIcon imageMediumButtonPressedDark = importImage(pathMediumButtonDarkPressed);
		JButton btnMedium = new JButton();
		btnMedium.setContentAreaFilled(false);
		btnMedium.setBorderPainted(false);
		middle.add(btnMedium, c);

		// Hard Button
		c.insets = new Insets(0, -275, 0, 0);
		c.fill = GridBagConstraints.REMAINDER;
		c.gridx = 4;
		String pathHardButton = "img/hard_button.png";
		String pathHardButtonPressed = "img/hard_button_pressed.png";
		String pathHardButtonDark = "img/hard_button_dark.png";
		String pathHardButtonDarkPressed = "img/hard_button_dark_pressed.png";
		JButton btnHard = new JButton();
		ImageIcon imageHardButton = importImage(pathHardButton);
		ImageIcon imageHardButtonPressed = importImage(pathHardButtonPressed);
		ImageIcon imageHardButtonDark = importImage(pathHardButtonDark);
		ImageIcon imageHardButtonPressedDark = importImage(pathHardButtonDarkPressed);
		btnHard.setContentAreaFilled(false);
		btnHard.setBorderPainted(false);

		middle.add(btnHard, c);

		// Offline Button
		c.insets = new Insets(0, 0, 0, 0);
		c.weightx = 2;
		c.gridy = 2;
		c.gridx = 0;
		c.gridwidth = 7;
		String pathPlayButton = "img/play.png";
		String pathPlayButtonDark = "img/play_dark.png";
		JButton playButton = new JButton();
		playButton.setContentAreaFilled(false);
		playButton.setBorderPainted(false);

		middle.add(playButton, c);

		// RIGHT PANEL LAYOUT
		// IMPORT FONT
		Font Poppins_Medium_24f = null;
		Poppins_Medium_24f = importFont("Poppins-Medium.ttf", 24f);

		// Name label
		lblPlayerName.setText("<html><br>" + getPlayerName() + "</html>");
		lblPlayerName.setFont(Poppins_Medium_24f);
		lblPlayerName.setAlignmentX(1.0f);
		lblPlayerName.setAlignmentY(0f);
		right.add(lblPlayerName);

		String pathNameIcon = "img/name_icon copy.png";
		String pathNameIconDark = "img/name_icon_dark.png";
		JLabel lblNameIcon = new JLabel();
		lblNameIcon.setAlignmentX(0.8f);
		lblNameIcon.setAlignmentY(0f);
		right.add(lblNameIcon);

		if (isDark) {
			cp.setBackground(DARK_MODE_COLOR);
			left.setBackground(DARK_MODE_COLOR);
			middle.setBackground(DARK_MODE_COLOR);
			right.setBackground(DARK_MODE_COLOR);

			btnClose.setIcon(importImage(pathCloseDark));
			btnDarkMode.setIcon(importImage(pathSun));
			btnSetting.setIcon(importImage(pathSettingDark));
			btnHelp.setIcon(importImage(pathHelpDark));

			btnEasy.setIcon(imageEasyButtonDark);
			btnMedium.setIcon(imageMediumButtonDark);
			btnHard.setIcon(imageHardButtonDark);

			playButton.setIcon(importImage(pathPlayButtonDark));

			lblNameIcon.setIcon(importImage(pathNameIconDark));

			lblTitle.setForeground(Color.WHITE);
			lblPlayerName.setForeground(Color.WHITE);
		} else {
			cp.setBackground(Color.WHITE);
			left.setBackground(Color.WHITE);
			middle.setBackground(Color.WHITE);
			right.setBackground(Color.WHITE);

			btnClose.setIcon(importImage(pathClose));
			btnDarkMode.setIcon(importImage(pathDarkMode));
			btnSetting.setIcon(importImage(pathSetting));
			btnHelp.setIcon(importImage(pathHelp));

			btnEasy.setIcon(imageEasyButton);
			btnMedium.setIcon(imageMediumButton);
			btnHard.setIcon(imageHardButton);

			playButton.setIcon(importImage(pathPlayButton));

			lblNameIcon.setIcon(importImage(pathNameIcon));

			lblTitle.setForeground(Color.BLACK);
			lblPlayerName.setForeground(Color.BLACK);
		}

		btnEasy.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				SoundEffect.CLICK.play();

				difficulty = 30;
				if (!isDark) {
					btnEasy.setIcon(imageEasyButtonPressed);
					btnMedium.setIcon(imageMediumButton);
					btnHard.setIcon(imageHardButton);
				} else {
					btnEasy.setIcon(imageEasyButtonPressedDark);
					btnMedium.setIcon(imageMediumButtonDark);
					btnHard.setIcon(imageHardButtonDark);
				}
			}
		});

		btnMedium.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				SoundEffect.CLICK.play();

				difficulty = 40;
				if (!isDark) {
					btnMedium.setIcon(imageMediumButtonPressed);
					btnEasy.setIcon(imageEasyButton);
					btnHard.setIcon(imageHardButton);
				} else {
					btnMedium.setIcon(imageMediumButtonPressedDark);
					btnEasy.setIcon(imageEasyButtonDark);
					btnHard.setIcon(imageHardButtonDark);
				}

			}
		});

		btnHard.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				SoundEffect.CLICK.play();

				difficulty = 50;
				if (!isDark) {
					btnHard.setIcon(imageHardButtonPressed);
					btnEasy.setIcon(imageEasyButton);
					btnMedium.setIcon(imageMediumButton);
				} else {
					btnHard.setIcon(imageHardButtonPressedDark);
					btnEasy.setIcon(imageEasyButtonDark);
					btnMedium.setIcon(imageMediumButtonDark);
				}
			}
		});

		playButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				SoundEffect.CLICK.play();
				dispose();
				Sudoku.newPuzzle();
				new GamePage();

			}
		});

		btnClose.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				SoundEffect.BACKGROUND.mute();
				SoundEffect.CLICK.play();
				dispose();
			}
		});

		// IF DARK MODE ICON IS CLICKED
		btnDarkMode.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				// you can open a new frame here as
				// i have assumed you have declared "frame" as instance variable
				// CHANGE TO DARK!!!
				SoundEffect.CLICK.play();

				if (!isDark) {
					isDark = true;
					cp.setBackground(DARK_MODE_COLOR);
					left.setBackground(DARK_MODE_COLOR);
					middle.setBackground(DARK_MODE_COLOR);
					right.setBackground(DARK_MODE_COLOR);

					lblTitle.setForeground(Color.WHITE);

					btnDarkMode.setIcon(importImage(pathSun));
					btnSetting.setIcon(importImage(pathSettingDark));
					btnHelp.setIcon(importImage(pathHelpDark));

					btnEasy.setIcon(importImage(pathEasyButtonDark));
					btnMedium.setIcon(importImage(pathMediumButtonDark));
					btnHard.setIcon(importImage(pathHardButtonDark));

					playButton.setIcon(importImage(pathPlayButtonDark));

					lblNameIcon.setIcon(importImage(pathNameIconDark));
					lblPlayerName.setForeground(Color.WHITE);

					if (difficulty == 0) {
						btnEasy.setIcon(imageEasyButtonDark);
						btnMedium.setIcon(imageMediumButtonDark);
						btnHard.setIcon(imageHardButtonDark);
					} else if (difficulty == 30) {
						btnEasy.setIcon(imageEasyButtonPressedDark);
						btnMedium.setIcon(imageMediumButtonDark);
						btnHard.setIcon(imageHardButtonDark);
					} else if (difficulty == 40) {
						btnEasy.setIcon(imageEasyButtonDark);
						btnMedium.setIcon(imageMediumButtonPressedDark);
						btnHard.setIcon(imageHardButtonDark);
					} else if (difficulty == 50) {
						btnEasy.setIcon(imageEasyButtonDark);
						btnMedium.setIcon(imageMediumButtonDark);
						btnHard.setIcon(imageHardButtonPressedDark);
					}
				} else {
					isDark = false;
					cp.setBackground(Color.WHITE);
					left.setBackground(Color.WHITE);
					middle.setBackground(Color.WHITE);
					right.setBackground(Color.WHITE);

					lblTitle.setForeground(Color.BLACK);

					btnDarkMode.setIcon(importImage(pathDarkMode));
					btnSetting.setIcon(importImage(pathSetting));
					btnHelp.setIcon(importImage(pathHelp));

					playButton.setIcon(importImage(pathPlayButton));

					lblNameIcon.setIcon(importImage(pathNameIcon));
					lblPlayerName.setForeground(Color.BLACK);

					if (difficulty == 0) {
						btnEasy.setIcon(importImage(pathEasyButton));
						btnMedium.setIcon(importImage(pathMediumButton));
						btnHard.setIcon(importImage(pathHardButton));
					} else if (difficulty == 30) {
						btnEasy.setIcon(imageEasyButtonPressed);
						btnMedium.setIcon(imageMediumButton);
						btnHard.setIcon(imageHardButton);
					} else if (difficulty == 40) {
						btnMedium.setIcon(imageMediumButtonPressed);
						btnEasy.setIcon(imageEasyButton);
						btnHard.setIcon(imageHardButton);
					} else if (difficulty == 50) {
						btnHard.setIcon(imageHardButtonPressed);
						btnEasy.setIcon(imageEasyButton);
						btnMedium.setIcon(imageMediumButton);
					}
				}
			}
		});

		// If Setting is clicked
		btnSetting.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				SoundEffect.CLICK.play();
				JDialog settingDialog = new JDialog();
				JPanel settingPanel = new JPanel();
				// Content-pane sets layout
				settingPanel.setLayout(new BoxLayout(settingPanel, BoxLayout.PAGE_AXIS));

				// Importing fonts
				Font Poppins_SemiBold_30f = null;
				Poppins_SemiBold_30f = importFont("Poppins-SemiBold.ttf", 30f);

				Font Poppins_Medium_24f = null;
				Poppins_Medium_24f = importFont("Poppins-Medium.ttf", 24f);

				// Allocate the UI components
				settingPanel.add(Box.createVerticalStrut(15));
				JLabel lblTitle = new JLabel("Settings", JLabel.CENTER);
				lblTitle.setFont(Poppins_SemiBold_30f);
				lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
				settingPanel.add(lblTitle);

				settingPanel.add(Box.createVerticalStrut(15));
				// Name row

				JPanel namePanel = new JPanel();
				namePanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 10));
				String pathName = "img/name.png";
				String pathNameDark = "img/name_dark.png";
				JLabel namePic = new JLabel();
				namePanel.add(namePic);

				JLabel lblName = new JLabel("Name  ");
				lblName.setFont(Poppins_Medium_24f);
				namePanel.add(lblName);

				JTextField tfName = new JTextField(9) {
					@Override
					protected void paintComponent(Graphics g) {
						if (!isOpaque() && getBorder() instanceof RoundedCornerBorder) {
							Graphics2D g2 = (Graphics2D) g.create();
							g2.setPaint(getBackground());
							g2.fill(((RoundedCornerBorder) getBorder()).getBorderShape(
									0, 0, getWidth() - 1, getHeight() - 1));
							g2.dispose();
						}
						super.paintComponent(g);
					}

					@Override
					public void updateUI() {
						super.updateUI();
						setOpaque(false);
						setBorder(new RoundedCornerBorder());
					}
				};
				tfName.setText(getPlayerName());
				tfName.setFont(Poppins_Medium_24f);
				namePanel.add(tfName);

				settingPanel.add(namePanel);

				// Background music row
				JPanel bgPanel = new JPanel();
				bgPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 10));

				JLabel lblBG = new JLabel("Background Music                  ");
				lblBG.setFont(Poppins_Medium_24f);
				bgPanel.add(lblBG);

				String pathVolNormal = "img/bg_normal.png";
				String pathVolNormalDark = "imgg/bg_normal_dark.png";
				String pathVolMuted = "img/bg_muted.png";
				String pathVolMutedDark = "img/bg_muted_dark.png";
				JButton btnVol = new JButton();

				btnVol.setBorderPainted(false);
				bgPanel.add(btnVol);

				bgPanel.setBackground(Color.WHITE);

				settingPanel.add(bgPanel);

				// sound effect row
				JPanel sfxPanel = new JPanel();
				sfxPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 10));

				JLabel lblSFX = new JLabel("Sound Effect                                ");
				lblSFX.setFont(Poppins_Medium_24f);
				sfxPanel.add(lblSFX);

				String pathSFXNormal = "img/sfx_normal.png";
				String pathSFXMuted = "img/sfx_muted.png";
				String pathSFXNormalDark = "img/sfx_normal_dark.png";
				String pathSFXMutedDark = "img/sfx_muted_dark.png";
				JButton btnSFX = new JButton();
				btnSFX.setBorderPainted(false);
				sfxPanel.add(btnSFX);

				sfxPanel.setBackground(Color.WHITE);
				sfxPanel.add(Box.createVerticalStrut(20));

				settingPanel.add(sfxPanel);

				// Button row
				JPanel savePanel = new JPanel();
				savePanel.setLayout(new BoxLayout(savePanel, BoxLayout.PAGE_AXIS));

				String pathBtnSave = "img/save.png";
				String pathBtnSaveDark = "img/save_dark.png";
				JButton btnSave = new JButton();
				btnSave.setBorderPainted(false);
				btnSave.setContentAreaFilled(false);
				btnSave.setAlignmentX(CENTER_ALIGNMENT);
				savePanel.add(btnSave);

				savePanel.setBackground(Color.WHITE);

				settingPanel.add(savePanel);

				settingPanel.add(Box.createVerticalStrut(15));

				btnVol.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						SoundEffect.CLICK.play();

						if (SoundEffect.BACKGROUND.volume != Volume.MUTE) {
							SoundEffect.BACKGROUND.volume = Volume.MUTE;
							SoundEffect.BACKGROUND.mute();
							if (!HomePage.getIsDark())
								btnVol.setIcon(importImage(pathVolNormal));
							else
								btnVol.setIcon(importImage(pathVolNormalDark));
						} else {
							SoundEffect.BACKGROUND.volume = Volume.HIGH;
							SoundEffect.BACKGROUND.play();
							if (!HomePage.getIsDark())
								btnVol.setIcon(importImage(pathVolMuted));
							else
								btnVol.setIcon(importImage(pathVolMutedDark));
						}
					}
				});

				btnSFX.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						SoundEffect.CLICK.play();

						if (SoundEffect.CLICK.volume != Volume.MUTE) {
							SoundEffect.CLICK.volume = Volume.MUTE;
							SoundEffect.CORRECT.volume = Volume.MUTE;
							SoundEffect.WRONG.volume = Volume.MUTE;
							SoundEffect.HINT.volume = Volume.MUTE;

							if (!HomePage.getIsDark())
								btnSFX.setIcon(importImage(pathSFXNormal));
							else
								btnSFX.setIcon(importImage(pathSFXNormalDark));
						} else {
							SoundEffect.CLICK.volume = Volume.HIGH;
							SoundEffect.CORRECT.volume = Volume.HIGH;
							SoundEffect.WRONG.volume = Volume.HIGH;
							SoundEffect.HINT.volume = Volume.HIGH;
							if (!HomePage.getIsDark())
								btnSFX.setIcon(importImage(pathSFXMuted));
							else
								btnSFX.setIcon(importImage(pathSFXMutedDark));
						}
					}
				});

				btnSave.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						SoundEffect.CLICK.play();

						setPlayerName(tfName.getText());
						lblPlayerName.setText("<html><br>" + getPlayerName() + "</html>");
						settingDialog.dispose();
					}
				});

				// FOR DARK MODE
				if (!HomePage.getIsDark()) {
					settingPanel.setBackground(Color.WHITE);
					namePanel.setBackground(Color.WHITE);
					bgPanel.setBackground(Color.WHITE);
					sfxPanel.setBackground(Color.WHITE);
					savePanel.setBackground(Color.WHITE);

					lblTitle.setForeground(Color.BLACK);
					lblName.setForeground(Color.BLACK);
					lblBG.setForeground(Color.BLACK);
					lblSFX.setForeground(Color.BLACK);

					namePic.setIcon(importImage(pathName));

					btnSave.setIcon(importImage(pathBtnSave));

					if (SoundEffect.BACKGROUND.volume != Volume.MUTE) {
						btnVol.setIcon(importImage(pathVolMuted));
					} else {
						btnVol.setIcon(importImage(pathVolNormal));
					}

					if (SoundEffect.CLICK.volume != Volume.MUTE) {
						btnSFX.setIcon(importImage(pathSFXMuted));
					} else {
						btnSFX.setIcon(importImage(pathSFXNormal));
					}
				} else {
					settingPanel.setBackground(NOT_SO_DARK_MODE_COLOR);
					namePanel.setBackground(NOT_SO_DARK_MODE_COLOR);
					bgPanel.setBackground(NOT_SO_DARK_MODE_COLOR);
					sfxPanel.setBackground(NOT_SO_DARK_MODE_COLOR);
					savePanel.setBackground(NOT_SO_DARK_MODE_COLOR);

					lblTitle.setForeground(Color.WHITE);
					lblName.setForeground(Color.WHITE);
					lblBG.setForeground(Color.WHITE);
					lblSFX.setForeground(Color.WHITE);

					tfName.setBackground(NOT_SO_DARK_MODE_COLOR);
					tfName.setForeground(Color.WHITE);

					namePic.setIcon(importImage(pathNameDark));
					btnSave.setIcon(importImage(pathBtnSaveDark));

					if (SoundEffect.BACKGROUND.volume != Volume.MUTE) {
						btnVol.setIcon(importImage(pathVolMutedDark));
						btnSFX.setIcon(importImage(pathSFXMutedDark));
					} else {
						btnVol.setIcon(importImage(pathVolNormalDark));
						btnSFX.setIcon(importImage(pathSFXNormalDark));
					}
				}

				settingDialog.setContentPane(settingPanel);
				settingDialog.setTitle("");
				settingDialog.setSize(465, 400);
				settingDialog.setLocationRelativeTo(cp);
				settingDialog.setModal(true);
				settingDialog.setVisible(true);
			}
		});

		btnHelp.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				SoundEffect.CLICK.play();

				left.setVisible(false);
				middle.setVisible(false);
				right.setVisible(false);
				Font Poppins_SemiBold_24f = null;
				Poppins_SemiBold_24f = importFont("Poppins-SemiBold.ttf", 24f);

				Font Poppins_SemiBold_16f = null;
				Poppins_SemiBold_16f = importFont("Poppins-SemiBold.ttf", 16f);

				JDialog helpDialog = new JDialog();

				// Panel in Dialog
				JPanel helpPanel = new JPanel();
				helpPanel.setBackground(Color.WHITE);
				// Set layout for panel
				helpPanel.setLayout(new BoxLayout(helpPanel, BoxLayout.Y_AXIS));
				// Insert components
				helpPanel.add(Box.createVerticalStrut(20));
				JLabel lblHelp = new JLabel("Help", JLabel.CENTER);
				lblHelp.setFont(Poppins_SemiBold_24f);
				lblHelp.setAlignmentX(Component.CENTER_ALIGNMENT);
				helpPanel.add(lblHelp);

				JLabel lblHelpText = new JLabel("", JLabel.CENTER);
				lblHelpText.setAlignmentX(Component.CENTER_ALIGNMENT);
				lblHelpText.setAlignmentY(CENTER_ALIGNMENT);
				lblHelpText.setFont(Poppins_SemiBold_16f);
				lblHelpText.setText(
						"<html><body style='width: 200px;'>Sudoku is played on a grid of 9 x 9 spaces. Within the rows and columns are 9 â€œsquaresâ€� (made up of 3 x 3 spaces). Each row, column and square (9 spaces each) needs to be filled out with the numbers 1-9, without repeating any numbers within the row, column or square.</body></html>");
				helpPanel.add(lblHelpText);

				helpPanel.add(Box.createVerticalStrut(15));

				String pathResume = "img/resume.png";
				String pathResumeDark = "img/resume_dark.png";
				JButton btnResume = new JButton();
				btnResume.setIcon(importImage(pathResume));
				btnResume.setBorderPainted(false);
				btnResume.setAlignmentX(Component.CENTER_ALIGNMENT);
				helpPanel.add(btnResume);

				// FOR DARK MODE
				if (!HomePage.getIsDark()) {
					helpPanel.setBackground(Color.WHITE);
					lblHelp.setForeground(Color.BLACK);
					lblHelpText.setForeground(Color.BLACK);
					btnResume.setIcon(importImage(pathResume));
				} else {
					helpPanel.setBackground(NOT_SO_DARK_MODE_COLOR);
					lblHelp.setForeground(Color.WHITE);
					lblHelpText.setForeground(Color.WHITE);
					btnResume.setIcon(importImage(pathResumeDark));
				}

				// Continue is pressed
				btnResume.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						SoundEffect.CLICK.play();
						helpDialog.dispose();
						left.setVisible(true);
						middle.setVisible(true);
						right.setVisible(true);
					}
				});

				helpDialog.setUndecorated(true);
				helpDialog.setContentPane(helpPanel);
				helpDialog.setBackground(Color.WHITE);
				helpDialog.setTitle("");
				helpDialog.setSize(325, 400);
				helpDialog.setLocationRelativeTo(cp);
				helpDialog.setModal(true);
				helpDialog.setVisible(true);
			}
		});

		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Exit when close button clicked
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT); // or pack() the components
		setVisible(true); // show it

	}

	public static int getDifficulty() {
		return difficulty;
	}

	public static void setDifficulty(int difficulty) {
		HomePage.difficulty = difficulty;
	}

	public static boolean getIsDark() {
		return isDark;
	}

	public static void setIsDark(boolean isDark) {
		HomePage.isDark = isDark;
	}

	public Font importFont(String path, float size) {
		Font Poppins = null;
		try {
			InputStream myStream = new FileInputStream("bin/fonts/Poppins-SemiBold.ttf");
			Poppins = Font.createFont(Font.TRUETYPE_FONT, myStream).deriveFont(size);
			GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(Poppins);
		} catch (FontFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Poppins;
	}

	public ImageIcon importImage(String path) {
		ImageIcon icon = null;
		java.net.URL imgURL = getClass().getClassLoader().getResource(path);
		if (imgURL != null) {
			icon = new ImageIcon(imgURL);
		} else {
			System.err.println("Couldn't find file: " + path);
		}
		return icon;
	}

	class RoundedCornerBorder extends AbstractBorder {
		private final Color ALPHA_ZERO = new Color(0x0, true);

		@Override
		public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
			Graphics2D g2 = (Graphics2D) g.create();
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			Shape border = getBorderShape(x, y, width - 1, height - 1);
			g2.setPaint(ALPHA_ZERO);
			Area corner = new Area(new Rectangle2D.Double(x, y, width, height));
			corner.subtract(new Area(border));
			g2.fill(corner);
			g2.setPaint(Color.GRAY);
			g2.draw(border);
			g2.dispose();
		}

		public Shape getBorderShape(int x, int y, int w, int h) {
			int r = h; // h / 2;
			return new RoundRectangle2D.Double(x, y, w, h, r, r);
		}

		@Override
		public Insets getBorderInsets(Component c) {
			return new Insets(10, 16, 10, 16);
		}

		@Override
		public Insets getBorderInsets(Component c, Insets insets) {
			insets.set(10, 16, 10, 16);
			return insets;
		}
	}

	/** The entry main() method */
	public static void main(String[] args) {
		// Run GUI codes in the Event-Dispatching thread for thread safety
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new HomePage(); // Let the constructor do the job
			}
		});
	}

	public static String getPlayerName() {
		return playerName;
	}

	public static void setPlayerName(String playerName) {
		HomePage.playerName = playerName;
	}
}