package sudoku;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.swing.*;
import javax.swing.border.AbstractBorder;
import javax.swing.plaf.LayerUI;

import sudoku.HomePage.RoundedCornerBorder;

import sudoku.SoundEffect.Volume;
 

// Swing Program Template
@SuppressWarnings("serial")
public class GamePage extends JFrame {
   // Name-constants to define the various dimensions
   public static final int WINDOW_WIDTH = 1440;
   public static final int WINDOW_HEIGHT = 900;

   // private variables of UI components
   // ......
   public static final Color DARK_MODE_COLOR = new Color(18,18,18);
   public static final Color NOT_SO_DARK_MODE_COLOR = new Color(36,36,36);
   
   public static StopWatch stopWatch;
  
   public GamePage() {
	   Container cp = this.getContentPane();
	   
	   cp.setLayout(new GridBagLayout());
	   
	   //Making panels
	   final JPanel left = new JPanel(), right = new JPanel();
	   left.setLayout(new BoxLayout(left, BoxLayout.Y_AXIS));
	   
	   Sudoku  sudokuPage = new Sudoku();
	   final JPanel middle = sudokuPage.Sudoku();
	   
	   right.setLayout(new GridBagLayout());
	   
	   
	   
	   GridBagConstraints c = new GridBagConstraints();

	   c.fill = GridBagConstraints.BOTH;
	   
	   //Set panels weightage
	   c.weightx = 100;
	   c.weighty = 1;
	   c.gridx = 0;
	   c.insets = new Insets(30,20,0,0);
	   cp.add(left, c);
	   
	   c.weightx = 550;
	   c.gridx = 1;
	   c.insets = new Insets(40,0,40,00);
	   cp.add(middle, c);
	   
	   c.weightx = 310;
	   c.gridx = 2;
	   c.insets = new Insets(200,20,100,0);
	   cp.add(right, c);
	   c.insets = new Insets(0,0,0,0);
	   
	   //LEFT PANEL LAYOUT
	   //Add dark mode icon
	   String pathPause = "img/pause.png";
	   String pathPauseDark = "img/pause_dark.png";
	   JButton btnPause = new JButton();
//	   btnPause.setIcon(importImage(pathPause));
	   btnPause.setBorderPainted(false);
	   left.add(btnPause);
	   btnPause.setContentAreaFilled(false);
	   left.add(Box.createVerticalStrut(10));
	   
	   String pathDarkMode = "img/dark_mode_light.png";
	   String pathSun = "img/dark_mode_dark.png";
	   JButton btnDarkMode = new JButton();
//	   btnDarkMode.setIcon(importImage(pathDarkMode));
	   btnDarkMode.setBorderPainted(false);
	   left.add(btnDarkMode);
	   btnDarkMode.setContentAreaFilled(false);
	   
	   left.add(Box.createVerticalStrut(10));
	   
	   //Add Setting icon
	   String pathSetting = "img/settings_light.png";
	   String pathSettingDark = "img/settings_dark.png";
	   JButton btnSetting = new JButton();
//	   btnSetting.setIcon(importImage(pathSetting));
	   btnSetting.setBorderPainted(false);
	   left.add(btnSetting);
	   btnSetting.setContentAreaFilled(false);
	   left.add(Box.createVerticalStrut(10));
	   
	   //Add help icon
	   String pathHelp = "img/help_light.png";
	   String pathHelpDark = "img/help_dark.png";
	   JButton btnHelp = new JButton();
	   btnHelp.setContentAreaFilled(false);
	   btnHelp.setBorderPainted(false);
	   left.add(btnHelp);
	   
	   //Initialize font
	   Font Poppins_SemiBold_96f = null;
	   Poppins_SemiBold_96f = importFont("Poppins-SemiBold.ttf", 96f);
	   
	   
	   //RIGHT PANEL LAYOUT
	   //IMPORT FONT
	   Font Poppins_Medium_80f = null;
	   Poppins_Medium_80f = importFont("Poppins-Medium.ttf", 80f);
	   
	   Font Poppins_Medium_24f = null;
	   Poppins_Medium_24f = importFont("Poppins-Medium.ttf", 24f);
	   
	   c.weightx=1;
	   c.weighty=1;
	   c.gridwidth =3;
	   c.gridy=0;
	   c.gridx=0;
	   
	   c.insets = new Insets(0,0,0,0);
	   JLabel lblTimer= new JLabel("00:00", JLabel.CENTER);
	   lblTimer.setFont(Poppins_Medium_80f);
	   right.add(lblTimer,c);
	   

	   stopWatch = new StopWatch();
	   Timer timer = new Timer(1, new ActionListener() {
		   @Override
		   public void actionPerformed(ActionEvent e) {
			   lblTimer.setText(DateTimeFormatter.ofPattern("mm:ss").format(LocalTime.MIDNIGHT.plus(stopWatch.getDuration())));
           }
	   });
	   timer.start();
	   stopWatch.start();
	   
	   c.weightx=1;
	   c.gridwidth=1;
	   c.gridy=1;
	   c.gridx=0;
	   c.insets = new Insets(375,0,0,0);
	   String pathHint = "img/hint.png";
	   String pathHintDark = "img/hint_dark.png";
	   JButton btnHint = new JButton();
	   btnHint.setContentAreaFilled(false);
	   btnHint.setBorderPainted(false);
	   right.add(btnHint,c);
	   
	   c.gridx=1;
	   String pathReset = "img/reset.png";
	   String pathResetDark = "img/reset_dark.png";
	   JButton btnReset = new JButton();
	   btnReset.setContentAreaFilled(false);
	   btnReset.setBorderPainted(false);
	   right.add(btnReset,c);
	   
	   c.gridx=2;
	   String pathExit = "img/logout.png";
	   String pathExitDark = "img/logout_dark.png";
	   JButton btnExit = new JButton();
	   btnExit.setContentAreaFilled(false);
	   btnExit.setBorderPainted(false);
	   right.add(btnExit,c);
	   
	   c.weightx=1;
	   c.gridwidth=1;
	   c.gridy=2;
	   c.gridx=0;
	   c.insets = new Insets(0,55,00,0);
	   JLabel lblHint = new JLabel("Hint");
	   lblHint.setFont(Poppins_Medium_24f);
	   right.add(lblHint,c);
	   
	   c.insets = new Insets(0,60,00,0);
	   c.gridx=1;
	   JLabel lblReset = new JLabel("Clear");
	   lblReset.setFont(Poppins_Medium_24f);
	   right.add(lblReset,c);
	   
	   c.insets = new Insets(0,50,00,0);
	   c.gridx=2;
	   JLabel lblExit = new JLabel("Exit");
	   lblExit.setFont(Poppins_Medium_24f);
	   right.add(lblExit,c);
	   
	   
	   //initialize icon images
	   if (!HomePage.getIsDark()) {
		   cp.setBackground(Color.WHITE);
		   left.setBackground(Color.WHITE);
		   right.setBackground(Color.WHITE);
		   btnPause.setIcon(importImage(pathPause));
		   btnDarkMode.setIcon(importImage(pathDarkMode));
		   btnSetting.setIcon(importImage(pathSetting));
		   btnHelp.setIcon(importImage(pathHelp));
		   
		   btnHint.setIcon(importImage(pathHint));
		   btnReset.setIcon(importImage(pathReset));
		   btnExit.setIcon(importImage(pathExit));
		   
		   Sudoku.setDark(false);
	   }
	   else {
		   cp.setBackground(DARK_MODE_COLOR);
		   left.setBackground(DARK_MODE_COLOR);
		   right.setBackground(DARK_MODE_COLOR);
		   btnPause.setIcon(importImage(pathPauseDark));
		   btnDarkMode.setIcon(importImage(pathSun));
		   btnSetting.setIcon(importImage(pathSettingDark));
		   btnHelp.setIcon(importImage(pathHelpDark));
		   
		   btnHint.setIcon(importImage(pathHintDark));
		   btnReset.setIcon(importImage(pathResetDark));
		   btnExit.setIcon(importImage(pathExitDark));
		   
		   lblTimer.setForeground(Color.WHITE);
		   lblHint.setForeground(Color.WHITE);
		   lblReset.setForeground(Color.WHITE);
		   lblExit.setForeground(Color.WHITE);
		   
		   Sudoku.setDark(true);
	   }
	   
	   
	   //IF PAUSE ICON IS CLICKED
	   btnPause.addMouseListener(new MouseAdapter() 
	   {
		   public void mouseClicked(MouseEvent e)
		   {
			   SoundEffect.CLICK.play();
			   left.setVisible(false);
			   middle.setVisible(false);
			   right.setVisible(false);
			   
			   Font Poppins_SemiBold_24f = null;
			   Poppins_SemiBold_24f = importFont("Poppins-SemiBold.ttf", 24f);
			   stopWatch.pause();
			   JDialog pauseDialog = new JDialog();
			   
			   //Panel in Dialog
			   JPanel pausePanel = new JPanel();
			   //Set layout for panel
			   pausePanel.setLayout(new BoxLayout(pausePanel, BoxLayout.Y_AXIS));
			   //Insert components
			   pausePanel.add(Box.createVerticalStrut(15));
			   JLabel lblPaused = new JLabel("Game Paused", JLabel.CENTER);
			   lblPaused.setFont(Poppins_SemiBold_24f);
			   lblPaused.setAlignmentX(Component.CENTER_ALIGNMENT);
			   pausePanel.add(lblPaused);

			   
			   pausePanel.add(Box.createVerticalStrut(15));
			   
			   String pathResume = "img/resume.png";
			   String pathResumeDark = "img/resume_dark.png";
			   JButton btnResume = new JButton();
			   btnResume.setBorderPainted(false);
			   btnResume.setAlignmentX(Component.CENTER_ALIGNMENT);
			   btnResume.setContentAreaFilled(false);
			   pausePanel.add(btnResume);
			   pausePanel.add(Box.createVerticalStrut(15));
			   
			   String pathRestart = "img/restart.png";
			   String pathRestartDark = "img/restart_dark.png";
			   JButton btnRestart = new JButton();
			   btnRestart.setBorderPainted(false);
			   btnRestart.setAlignmentX(Component.CENTER_ALIGNMENT);
			   btnRestart.setContentAreaFilled(false);
			   pausePanel.add(btnRestart);
			   pausePanel.add(Box.createVerticalStrut(15));
			   
			   String pathExit = "img/exit_paused.png";
			   String pathExitDark = "img/exit_paused_dark.png";
			   JButton btnExit = new JButton();
			   btnExit.setContentAreaFilled(false);
			   btnExit.setBorderPainted(false);
			   btnExit.setAlignmentX(Component.CENTER_ALIGNMENT);
			   pausePanel.add(btnExit);
			   pauseDialog.setUndecorated(true);
			   
			   //FOR DARK MODE
			   if (!HomePage.getIsDark()) {
				   pausePanel.setBackground(Color.WHITE);
				   lblPaused.setForeground(Color.BLACK);
				   btnResume.setIcon(importImage(pathResume));
				   btnRestart.setIcon(importImage(pathRestart));
				   btnExit.setIcon(importImage(pathExit));
			   }
			   else {
				   pausePanel.setBackground(NOT_SO_DARK_MODE_COLOR);
				   lblPaused.setForeground(Color.WHITE);
				   btnResume.setIcon(importImage(pathResumeDark));
				   btnRestart.setIcon(importImage(pathRestartDark));
				   btnExit.setIcon(importImage(pathExitDark));
			   }
			   
			   //Continue is pressed
			   btnResume.addMouseListener(new MouseAdapter() {
				   public void mouseClicked(MouseEvent e)
				   {
					   SoundEffect.CLICK.play();
					   pauseDialog.dispose();
					   stopWatch.resume();
					   left.setVisible(true);
					   middle.setVisible(true);
					   right.setVisible(true);
				   }
			   });
			   
			   btnRestart.addMouseListener(new MouseAdapter() {
				   public void mouseClicked(MouseEvent e)
				   {
					   SoundEffect.CLICK.play();
					   //stopWatch.pause();
					   Sudoku.reset();
					   pauseDialog.dispose();
					   stopWatch.reset();
					   left.setVisible(true);
					   middle.setVisible(true);
					   right.setVisible(true);
					   
					   //dispose();
				   }
			   });
			   
			   btnExit.addMouseListener(new MouseAdapter() {
				   public void mouseClicked(MouseEvent e) {
					   	  SoundEffect.CLICK.play();
					   new HomePage();
					   pauseDialog.dispose();
					   dispose();
				   }
			   });
			   
			   pauseDialog.setBackground(Color.WHITE);
			   pauseDialog.setContentPane(pausePanel);
			   pauseDialog.setTitle("");
			   pauseDialog.setSize(225,315);
		       pauseDialog.setLocationRelativeTo(cp);
		       pauseDialog.setModal(true);
		       pauseDialog.setVisible(true);
		   }
	   });
	   
	   btnSetting.addMouseListener(new MouseAdapter() {
		   public void mouseClicked(MouseEvent e) {
			   	  SoundEffect.CLICK.play();
				  JDialog settingDialog = new JDialog();
				  JPanel settingPanel = new JPanel();
			      // Content-pane sets layout
			      settingPanel.setLayout(new BoxLayout(settingPanel, BoxLayout.PAGE_AXIS));
			     
				  //Importing fonts
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
				  //Name row

				  JPanel namePanel = new JPanel();
				  namePanel.setLayout(new FlowLayout(FlowLayout.LEFT,20,10));
				  String pathName = "img/name.png";
				  String pathNameDark = "img/name_dark.png";
				  JLabel namePic = new JLabel();
				  namePanel.add(namePic);
				  
				  JLabel lblName = new JLabel("Name  ");
				  lblName.setFont(Poppins_Medium_24f);
				  namePanel.add(lblName);
				  
				  JTextField tfName = new JTextField(9){
					  @Override protected void paintComponent(Graphics g) {
						    if (!isOpaque() && getBorder() instanceof RoundedCornerBorder) {
						      Graphics2D g2 = (Graphics2D) g.create();
						      g2.setPaint(getBackground());
						      g2.fill(((RoundedCornerBorder) getBorder()).getBorderShape(
						          0, 0, getWidth() - 1, getHeight() - 1));
						      g2.dispose();
						    }
						    super.paintComponent(g);
						  }
						  @Override public void updateUI() {
						    super.updateUI();
						    setOpaque(false);
						    setBorder(new RoundedCornerBorder());
						  }
						};
				  tfName.setText(HomePage.getPlayerName());
				  tfName.setFont(Poppins_Medium_24f);
				  namePanel.add(tfName);
				 
				  settingPanel.add(namePanel);
				  
				  
				  //Background music row
				  JPanel bgPanel = new JPanel();
				  bgPanel.setLayout(new FlowLayout(FlowLayout.LEFT,20,10));
				  
				  JLabel lblBG = new JLabel("Background Music                  ");
				  lblBG.setFont(Poppins_Medium_24f);
				  bgPanel.add(lblBG);
				  
				  String pathVolNormal = "img/bg_normal.png";
				  String pathVolNormalDark = "img/bg_normal_dark.png";
				  String pathVolMuted = "img/bg_muted.png";
				  String pathVolMutedDark = "img/bg_muted_dark.png";
				  JButton btnVol = new JButton();

				  btnVol.setBorderPainted(false);
				  bgPanel.add(btnVol);
				  
				  bgPanel.setBackground(Color.WHITE);
				  
				  settingPanel.add(bgPanel);
				  

				  
				  //sound effect row
				  JPanel sfxPanel = new JPanel();
				  sfxPanel.setLayout(new FlowLayout(FlowLayout.LEFT,20,10));
				  
				  
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
				  
				  //Button row
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
							  else btnVol.setIcon(importImage(pathVolNormalDark));
						  }
						  else {
							  SoundEffect.BACKGROUND.volume = Volume.HIGH;
							  SoundEffect.BACKGROUND.play();
							  if (!HomePage.getIsDark()) 
							  btnVol.setIcon(importImage(pathVolMuted));
							  else btnVol.setIcon(importImage(pathVolMutedDark));
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
							  else btnSFX.setIcon(importImage(pathSFXNormalDark));
						  }
						  else {
							  SoundEffect.CLICK.volume = Volume.HIGH;
							  SoundEffect.CORRECT.volume = Volume.HIGH;
							  SoundEffect.WRONG.volume = Volume.HIGH;
							  SoundEffect.HINT.volume = Volume.HIGH;
							  if (!HomePage.getIsDark()) 
							  btnSFX.setIcon(importImage(pathSFXMuted));
							  else btnSFX.setIcon(importImage(pathSFXMutedDark));
						  }
					   }
				  });
				  
				  btnSave.addMouseListener(new MouseAdapter() {
					  public void mouseClicked(MouseEvent e) {
					   	  SoundEffect.CLICK.play();

						  HomePage.setPlayerName(tfName.getText());
						  settingDialog.dispose();
					   }
				  });
				  
				//FOR DARK MODE
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
					   }
					   else {
						   btnVol.setIcon(importImage(pathVolNormal));
					   }
					   
					   if (SoundEffect.CLICK.volume != Volume.MUTE) {
						   btnSFX.setIcon(importImage(pathSFXMuted));
					   }
					   else {
						   btnSFX.setIcon(importImage(pathSFXNormal));
					   }
				   }
				   else {
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
					   }
					   else {
						   btnVol.setIcon(importImage(pathVolNormalDark));
					   }
					   
					   if (SoundEffect.CLICK.volume != Volume.MUTE) {
						   btnSFX.setIcon(importImage(pathSFXMutedDark));
					   }
					   else {
						   btnSFX.setIcon(importImage(pathSFXNormalDark));
					   }
				   }
				  
				  settingDialog.setContentPane(settingPanel);
				  settingDialog.setTitle("");
				  settingDialog.setSize(465,400);
				  settingDialog.setLocationRelativeTo(cp);
				  settingDialog.setModal(true);
				  settingDialog.setVisible(true);
			   }
	   });

	   btnDarkMode.addMouseListener(new MouseAdapter()  
	   {  
	       public void mouseClicked(MouseEvent e)  
	       {  
			   	  SoundEffect.CLICK.play();

	    	   if (HomePage.getIsDark()) { //FROM 
	    		   HomePage.setIsDark(false);
	    		   cp.setBackground(Color.WHITE);
	    		   left.setBackground(Color.WHITE);
	    		   right.setBackground(Color.WHITE);
	    		   btnPause.setIcon(importImage(pathPause));
	    		   btnDarkMode.setIcon(importImage(pathDarkMode));
	    		   btnSetting.setIcon(importImage(pathSetting));
	    		   btnHelp.setIcon(importImage(pathHelp));
	    		   
	    		   btnHint.setIcon(importImage(pathHint));
	    		   btnReset.setIcon(importImage(pathReset));
	    		   btnExit.setIcon(importImage(pathExit));
	    		   
	    		   lblTimer.setForeground(Color.BLACK);
	    		   lblHint.setForeground(Color.BLACK);
	    		   lblReset.setForeground(Color.BLACK);
	    		   lblExit.setForeground(Color.BLACK);
	    		  
	    		   Sudoku.setDark(false);
	    	   }
	    	   else {
	    		   HomePage.setIsDark(true);
	    		   cp.setBackground(DARK_MODE_COLOR);
	    		   left.setBackground(DARK_MODE_COLOR);
	    		   right.setBackground(DARK_MODE_COLOR);
	    		   btnPause.setIcon(importImage(pathPauseDark));
	    		   btnDarkMode.setIcon(importImage(pathSun));
	    		   btnSetting.setIcon(importImage(pathSettingDark));
	    		   btnHelp.setIcon(importImage(pathHelpDark));
	    		   
	    		   btnHint.setIcon(importImage(pathHintDark));
	    		   btnReset.setIcon(importImage(pathResetDark));
	    		   btnExit.setIcon(importImage(pathExitDark));
	    		   
	    		   lblTimer.setForeground(Color.WHITE);
	    		   lblHint.setForeground(Color.WHITE);
	    		   lblReset.setForeground(Color.WHITE);
	    		   lblExit.setForeground(Color.WHITE);
	    		   Sudoku.setDark(true);
	    	   }
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
			   
			   stopWatch.pause();
			   JDialog helpDialog = new JDialog();
			   
			   //Panel in Dialog
			   JPanel helpPanel = new JPanel();
			   helpPanel.setBackground(Color.WHITE);
			   //Set layout for panel
			   helpPanel.setLayout(new BoxLayout(helpPanel, BoxLayout.Y_AXIS));
			   //Insert components
			   helpPanel.add(Box.createVerticalStrut(20));
			   JLabel lblHelp = new JLabel("Help", JLabel.CENTER);
			   lblHelp.setFont(Poppins_SemiBold_24f);
			   lblHelp.setAlignmentX(Component.CENTER_ALIGNMENT);
			   helpPanel.add(lblHelp);
			   
			  
			   JLabel lblHelpText = new JLabel("", JLabel.CENTER);
			   lblHelpText.setAlignmentX(Component.CENTER_ALIGNMENT);
			   lblHelpText.setAlignmentY(CENTER_ALIGNMENT);
			   lblHelpText.setFont(Poppins_SemiBold_16f);
			   lblHelpText.setText("<html><body style='width: 200px;'>Sudoku is played on a grid of 9 x 9 spaces. Within the rows and columns are 9 â€œsquaresâ€� (made up of 3 x 3 spaces). Each row, column and square (9 spaces each) needs to be filled out with the numbers 1-9, without repeating any numbers within the row, column or square.</body></html>");
			   helpPanel.add(lblHelpText);
			   
			   helpPanel.add(Box.createVerticalStrut(15));
			   
			   String pathResume = "img/resume.png";
			   String pathResumeDark = "img/resume_dark.png";
			   JButton btnResume = new JButton();
			   btnResume.setIcon(importImage(pathResume));
			   btnResume.setBorderPainted(false);
			   btnResume.setAlignmentX(Component.CENTER_ALIGNMENT);
			   helpPanel.add(btnResume);
			   
			   //FOR DARK MODE
			   if (!HomePage.getIsDark()) {
				   helpPanel.setBackground(Color.WHITE);
				   lblHelp.setForeground(Color.BLACK);
				   lblHelpText.setForeground(Color.BLACK);
				   btnResume.setIcon(importImage(pathResume));
			   }
			   else {
				   helpPanel.setBackground(NOT_SO_DARK_MODE_COLOR);
				   lblHelp.setForeground(Color.WHITE);
				   lblHelpText.setForeground(Color.WHITE);
				   btnResume.setIcon(importImage(pathResumeDark));
			   }
			   
			 //Continue is pressed
			   btnResume.addMouseListener(new MouseAdapter() {
				   public void mouseClicked(MouseEvent e)
				   {
					   	  SoundEffect.CLICK.play();

					   helpDialog.dispose();
					   stopWatch.resume();
					   left.setVisible(true);
					   middle.setVisible(true);
					   right.setVisible(true);
				   }
			   });
			   
			   helpDialog.setUndecorated(true);
			   helpDialog.setContentPane(helpPanel);
			   helpDialog.setBackground(Color.WHITE);
			   helpDialog.setTitle("");
			   helpDialog.setSize(325,400);
		       helpDialog.setLocationRelativeTo(cp);
		       helpDialog.setModal(true);
		       helpDialog.setVisible(true);
		   }
	   });
	   
	   //IF SETTING ICON IS CLICKED
	   btnHint.addMouseListener(new MouseAdapter() {
		   public void mouseClicked(MouseEvent e) {
			   	  SoundEffect.HINT.play();
			   	  Sudoku.hint();
		   }
	   });
	   
	   btnReset.addMouseListener(new MouseAdapter() {
		   public void mouseClicked(MouseEvent e) {
			   	  Sudoku.deleteAll();
			   	 
		   }
	   });

	   
	   btnExit.addMouseListener(new MouseAdapter() {
		   public void mouseClicked(MouseEvent e) {
			   	  SoundEffect.CLICK.play();
			   	  Sudoku.resetMask();
			   new HomePage();
			   dispose();
		   }
		   
	   });
	   
	   setUndecorated(true);
	   setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Exit when close button clicked
	   setSize(WINDOW_WIDTH, WINDOW_HEIGHT);  // or pack() the components
	   setVisible(true);   // show it

	   
   }

   public static void isWon (boolean isWon) {
	   if (isWon)
	   stopWatch.pause();
   }
  
   public Font importFont(String path, float size) {
	   Font Poppins = null;
	   try {
		   InputStream myStream = new FileInputStream("bin/fonts/Poppins-SemiBold.ttf");
		   Poppins = Font.createFont(Font.TRUETYPE_FONT, myStream).deriveFont(size);
		   GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(Poppins);
	} catch (FontFormatException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
	   return Poppins;
}
     
   public ImageIcon importImage(String path) {
	   ImageIcon icon = null;
	   java.net.URL imgURL = getClass().getClassLoader().getResource(path);
	   if (imgURL != null) {
	      icon =  new ImageIcon(imgURL);
	   } else {
	      System.err.println("Couldn't find file: " + path);
	   }
	   return icon;
   }
 
   /** The entry main() method */
   public static void main(String[] args) {
      // Run GUI codes in the Event-Dispatching thread for thread safety
      SwingUtilities.invokeLater(new Runnable() {
         public void run() {
            new GamePage();  // Let the constructor do the job
         }
      });
   }
   
   class RoundedCornerBorder extends AbstractBorder {
	   private final Color ALPHA_ZERO = new Color(0x0, true);
	   @Override public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
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
	     int r = h; //h / 2;
	     return new RoundRectangle2D.Double(x, y, w, h, r, r);
	   }
	   @Override public Insets getBorderInsets(Component c) {
	     return new Insets(10, 16, 10, 16);
	   }
	   @Override public Insets getBorderInsets(Component c, Insets insets) {
	     insets.set(10, 16, 10, 16);
	     return insets;
	   }
	 }
   
   
   public class StopWatch {
       private LocalDateTime startTime;
       private Duration totalRunTime = Duration.ZERO;

       public void start() {
           startTime = LocalDateTime.now();
       }

       public void stop() {
           Duration runTime = Duration.between(startTime, LocalDateTime.now());
           totalRunTime = totalRunTime.plus(runTime);
           startTime = null;
       }

       public void pause() {
           stop();
       }

       public void resume() {
           start();
       }

       public void reset() {
           //stop();
           totalRunTime = Duration.ZERO;
           start();
       }

       public boolean isRunning() {
           return startTime != null;
       }

       public Duration getDuration() {
           Duration currentDuration = Duration.ZERO;
           currentDuration = currentDuration.plus(totalRunTime);
           if (isRunning()) {
               Duration runTime = Duration.between(startTime, LocalDateTime.now());
               currentDuration = currentDuration.plus(runTime);
           }
           return currentDuration;
       }
   }

	/*
	 * public static void resetWatch() { stopWatch.reset(); }
	 */
   
}