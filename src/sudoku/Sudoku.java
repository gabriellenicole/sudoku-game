package sudoku;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

import sudoku.GamePage.StopWatch;

import java.util.Random;

public class Sudoku extends JFrame {
	// Size of the SUDOKU template
	public static final int GRID_SIZE = 9;
	public static final int SUBGRID_SIZE = 3;

	// Size of SUDOKU cells
	public static final int CELL_SIZE = 60;
	public static final int CANVAS_WIDTH = CELL_SIZE * GRID_SIZE;
	public static final int CANVAS_HEIGHT = CELL_SIZE * GRID_SIZE;

	x

	// cells of the SUDOKU
	static private JTextField[][] tfCells = new JTextField[GRID_SIZE][GRID_SIZE];

	// variabel saved
	private static int saved[][] = new int[9][9];

	// hard coded for now! HOMEWORK TO FIND AUTOMATION WAY
	private static int[][] puzzle = { { 5, 3, 4, 6, 7, 8, 9, 1, 2 }, { 6, 7, 2, 1, 9, 5, 3, 4, 8 },
			{ 1, 9, 8, 3, 4, 2, 5, 6, 7 }, { 8, 5, 9, 7, 6, 1, 4, 2, 3 }, { 4, 2, 6, 8, 5, 3, 7, 9, 1 },
			{ 7, 1, 3, 9, 2, 4, 8, 5, 6 }, { 9, 6, 1, 5, 3, 7, 2, 8, 4 }, { 2, 8, 7, 4, 1, 9, 6, 3, 5 },
			{ 3, 4, 5, 2, 8, 6, 1, 7, 9 } };
	// For testing, open only 2 cells.
	private static boolean[][] masks = { { false, false, false, false, false, false, false, false, false },
			{ false, false, false, false, false, false, false, false, false },
			{ false, false, false, false, false, false, false, false, false },
			{ false, false, false, false, false, false, false, false, false },
			{ false, false, false, false, false, false, false, false, false },
			{ false, false, false, false, false, false, false, false, false },
			{ false, false, false, false, false, false, false, false, false },
			{ false, false, false, false, false, false, false, false, false },
			{ false, false, false, false, false, false, false, false, false } };
	
	private static boolean[][] hints = new boolean[9][9];

	public void setSudokuBorder(int row, int col, Color color) {
		if (row == 0 && col == 0)
			tfCells[row][col].setBorder(BorderFactory.createMatteBorder(6, 6, 1, 1, color));
		else if (row == GRID_SIZE - 1 && col == GRID_SIZE - 1)
			tfCells[row][col].setBorder(BorderFactory.createMatteBorder(1, 1, 6, 6, color));
		else if (row == GRID_SIZE - 1 && col == 0)
			tfCells[row][col].setBorder(BorderFactory.createMatteBorder(1, 6, 6, 1, color));
		else if (row == 0 && col == GRID_SIZE - 1)
			tfCells[row][col].setBorder(BorderFactory.createMatteBorder(6, 1, 1, 6, color));

		else if (row == 0 && col % 3 == 0)
			tfCells[row][col].setBorder(BorderFactory.createMatteBorder(6, 2, 1, 1, color));
		else if (row == 0 && col % 3 == 2)
			tfCells[row][col].setBorder(BorderFactory.createMatteBorder(6, 1, 1, 2, color));
		else if (row % 3 == 0 && col == 0)
			tfCells[row][col].setBorder(BorderFactory.createMatteBorder(2, 6, 1, 1, color));
		else if (row % 3 == 2 && col == 0)
			tfCells[row][col].setBorder(BorderFactory.createMatteBorder(1, 6, 2, 1, color));
		else if (row == GRID_SIZE - 1 && col % 3 == 0)
			tfCells[row][col].setBorder(BorderFactory.createMatteBorder(1, 2, 6, 1, color));
		else if (row == GRID_SIZE - 1 && col % 3 == 2)
			tfCells[row][col].setBorder(BorderFactory.createMatteBorder(1, 1, 6, 2, color));
		else if (row % 3 == 0 && col == GRID_SIZE - 1)
			tfCells[row][col].setBorder(BorderFactory.createMatteBorder(2, 1, 1, 6, color));
		else if (row % 3 == 2 && col == GRID_SIZE - 1)
			tfCells[row][col].setBorder(BorderFactory.createMatteBorder(1, 1, 2, 6, color));

		else if (col == 0)
			tfCells[row][col].setBorder(BorderFactory.createMatteBorder(1, 6, 1, 1, color));
		else if (row == 0)
			tfCells[row][col].setBorder(BorderFactory.createMatteBorder(6, 1, 1, 1, color));
		else if (row == GRID_SIZE - 1)
			tfCells[row][col].setBorder(BorderFactory.createMatteBorder(1, 1, 6, 1, color));
		else if (col == GRID_SIZE - 1)
			tfCells[row][col].setBorder(BorderFactory.createMatteBorder(1, 1, 1, 6, color));

		else if (row % 3 == 0 && col % 3 == 0)
			tfCells[row][col].setBorder(BorderFactory.createMatteBorder(2, 2, 1, 1, color));
		else if (row % 3 == 1 && col % 3 == 0)
			tfCells[row][col].setBorder(BorderFactory.createMatteBorder(1, 2, 1, 1, color));
		else if (row % 3 == 2 && col % 3 == 0)
			tfCells[row][col].setBorder(BorderFactory.createMatteBorder(1, 2, 2, 1, color));

		else if (row % 3 == 0 && col % 3 == 1)
			tfCells[row][col].setBorder(BorderFactory.createMatteBorder(2, 1, 1, 1, color));
		else if (row % 3 == 2 && col % 3 == 1)
			tfCells[row][col].setBorder(BorderFactory.createMatteBorder(1, 1, 2, 1, color));

		else if (row % 3 == 0 && col % 3 == 2)
			tfCells[row][col].setBorder(BorderFactory.createMatteBorder(2, 1, 1, 2, color));
		else if (row % 3 == 1 && col % 3 == 2)
			tfCells[row][col].setBorder(BorderFactory.createMatteBorder(1, 1, 1, 2, color));
		else if (row % 3 == 2 && col % 3 == 2)
			tfCells[row][col].setBorder(BorderFactory.createMatteBorder(1, 1, 2, 2, color));

		else
			tfCells[row][col].setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, color));

		// Beautify all the cells
		tfCells[row][col].setHorizontalAlignment(JTextField.CENTER);
		tfCells[row][col].setFont(FONT_NUMBERS);
	}
	public JPanel Sudoku() {
		// COPY TO NEW FILE
		int difficulty = HomePage.getDifficulty();
		//System.out.println(difficulty);
		newMask(difficulty);

		JPanel cp = new JPanel();
		cp.setLayout(new GridLayout(GRID_SIZE, GRID_SIZE));

		InputListener listener = new InputListener();

		if (!HomePage.getIsDark()) {
			for (int row = 0; row < GRID_SIZE; ++row) {
				for (int col = 0; col < GRID_SIZE; ++col) {
					tfCells[row][col] = new JTextField(); // Allocate element of array
					cp.add(tfCells[row][col]); // ContentPane adds JTextField
					if (masks[row][col]) {
						tfCells[row][col].setText(""); // set to empty string
						tfCells[row][col].setEditable(true);
						tfCells[row][col].setBackground(OPEN_CELL_BGCOLOR);
						/* tfCells[row][col].addActionListener(listener); */
						tfCells[row][col].addKeyListener(listener);
					} else {
						tfCells[row][col].setText(puzzle[row][col] + "");
						tfCells[row][col].setEditable(false);
						tfCells[row][col].setBackground(CLOSED_CELL_BGCOLOR);
						tfCells[row][col].setForeground(CLOSED_CELL_TEXT);
					}

					// COPY TO NEW FILE
					setSudokuBorder(row,col,Color.BLACK);
				}
			}
		} else {
			for (int row = 0; row < GRID_SIZE; ++row) {
				for (int col = 0; col < GRID_SIZE; ++col) {
					tfCells[row][col] = new JTextField(); // Allocate element of array
					cp.add(tfCells[row][col]); // ContentPane adds JTextField
					if (masks[row][col]) {
						tfCells[row][col].setText(""); // set to empty string
						tfCells[row][col].setEditable(true);
						tfCells[row][col].setBackground(OPEN_CELL_BGCOLOR_DARK);
						/* tfCells[row][col].addActionListener(listener); */
						tfCells[row][col].addKeyListener(listener);
					} else {
						tfCells[row][col].setText(puzzle[row][col] + "");
						tfCells[row][col].setEditable(false);
						tfCells[row][col].setBackground(DARK_MODE_COLOR);
						tfCells[row][col].setForeground(Color.WHITE);
					}

					// COPY TO NEW FILE
					setSudokuBorder(row,col,Color.WHITE);
				}
			}
		}
		
		cp.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
		
		setUndecorated(true);
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("");
		setVisible(false);

		// EDIT IN NEW FILE
		return cp;

	}
	
	public static void deleteAll() {
		for(int row =0;row<GRID_SIZE; row++) {
			for(int col =0; col<GRID_SIZE; col++) {
				if(!masks[row][col]) {
					if(HomePage.getIsDark()) {
						tfCells[row][col].setBackground(Color.BLACK);
						tfCells[row][col].setForeground(Color.WHITE);
					}
					else {
						tfCells[row][col].setBackground(Color.WHITE);
						tfCells[row][col].setForeground(Color.BLACK);
					}
				}
				else {
					if(HomePage.getIsDark()) {
						if(!hints[row][col]) {
							tfCells[row][col].setBackground(OPEN_CELL_BGCOLOR_DARK);
							tfCells[row][col].setForeground(Color.WHITE);
							tfCells[row][col].setText("");
						}
					}
					else {
						if(!hints[row][col]) {
							tfCells[row][col].setBackground(OPEN_CELL_BGCOLOR);
							tfCells[row][col].setForeground(Color.BLACK);
							tfCells[row][col].setText("");
						}
					}
				}
			}
		}
	}
	
	
	//function to show hint
	public static void hint() {
		boolean notFound=true;
		Random rand = new Random();
		int upperbound=GRID_SIZE;
		int random_row;
		int random_col;
		while(notFound) {
			//to find a cell that is still wrong or unanswered randomly
			random_row = rand.nextInt(upperbound);
			random_col = rand.nextInt(upperbound);
			//to set the cell as hinted
			if(masks[random_row][random_col] && !hints[random_row][random_col]) {
					if(tfCells[random_row][random_col].getBackground()!=Color.GREEN) {
						tfCells[random_row][random_col].setText(puzzle[random_row][random_col]+"");
						tfCells[random_row][random_col].setEditable(false);
						tfCells[random_row][random_col].setBackground(HINTCOLOR);
						hints[random_row][random_col]=true;
						notFound = false;
						if (!HomePage.getIsDark())
							check(random_row, random_col, saved[random_row][random_col], CLOSED_CELL_BGCOLOR, false);
						else
							check(random_row, random_col, saved[random_row][random_col], DARK_MODE_COLOR, false);
					}
			}
			
		}
	}
	public static void reset() {
		for(int row =0;row<GRID_SIZE; row++) {
			for(int col =0; col<GRID_SIZE; col++) {
				if(!masks[row][col]) {
					if(HomePage.getIsDark()) {
						tfCells[row][col].setBackground(Color.BLACK);
						tfCells[row][col].setForeground(Color.WHITE);
					}
					else {
						tfCells[row][col].setBackground(Color.WHITE);
						tfCells[row][col].setForeground(Color.BLACK);
					}
				}
				else {
					if(HomePage.getIsDark()) {
						hints[row][col]=false;
						tfCells[row][col].setBackground(OPEN_CELL_BGCOLOR_DARK);
						tfCells[row][col].setForeground(Color.WHITE);
						tfCells[row][col].setText("");
						
					}
					else {
						hints[row][col]=false;
						tfCells[row][col].setBackground(OPEN_CELL_BGCOLOR);
						tfCells[row][col].setForeground(Color.BLACK);
						tfCells[row][col].setText("");
						
					}
				}
				//System.out.println("here");
			}
		}
	}
	
	//Function to reset back mask to all false
	public static void resetMask() {
		for(int row = 0;row<GRID_SIZE;row++) {
			for(int col =0; col<GRID_SIZE; col++) {
				masks[row][col]=false;
				hints[row][col]=false;
			}
		}
	}
	
	// function to randomize the number in the puzzle and also the number of clues
	// given
	public static void newPuzzle() {
		Random rand = new Random();
		int upperbound = 9;
		int int_random_shifter = rand.nextInt(upperbound);

		for (int i = 0; i < GRID_SIZE; i++) {
			for (int j = 0; j < GRID_SIZE; j++) {
				puzzle[i][j] = ((puzzle[i][j] + int_random_shifter) % 9) + 1;
			}
		}
	}

	public static void newMask(int holes) {
		Random rand = new Random();
		int row;
		int col;
		int upperbound = 9;
		while (holes > 0) {
			row = rand.nextInt(upperbound);
			col = rand.nextInt(upperbound);
			if (!masks[row][col]) {
				masks[row][col] = true;
				holes--;
			}
		}
		// EDIT IN NEW FILE
		HomePage.setDifficulty(0);
	}



	public static boolean isNumeric(String strNum) {
		if (strNum == null) {
			return false;
		}
		try {
			double d = Integer.parseInt(strNum);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}

	public class InputListener implements KeyListener {
		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			int res = -1;
			int rowSelected = -1;
			int colSelected = -1;
			JTextField source = (JTextField) e.getSource();
			// Scan JTextFileds for all rows and columns, and match with the source object

			boolean found = false;
			for (int row = 0; row < GRID_SIZE && !found; ++row) {
				for (int col = 0; col < GRID_SIZE && !found; ++col) {
					if (tfCells[row][col] == source) {
						rowSelected = row;
						colSelected = col;
						found = true; // break the inner/outer loops
					}
				}
			}
			String selectedBoxContent = tfCells[rowSelected][colSelected].getText();
			if (isNumeric("" + e.getKeyChar())) {
				selectedBoxContent = selectedBoxContent + e.getKeyChar();
			}
			// System.out.println(selectedBoxContent);
			// System.out.println(saved[rowSelected][colSelected]);
			if (!selectedBoxContent.isEmpty()) {
				if (isNumeric(selectedBoxContent)) {
					res = Integer.parseInt(selectedBoxContent);
					// System.out.println(res);
					//System.out.println(saved[rowSelected][colSelected]);

					if (!HomePage.getIsDark())
						check(rowSelected, colSelected, saved[rowSelected][colSelected], CLOSED_CELL_BGCOLOR, true);
					else
						check(rowSelected, colSelected, saved[rowSelected][colSelected], DARK_MODE_COLOR, true);
					saved[rowSelected][colSelected] = res;
					// check if the answer doesn't do any conflict
					check(rowSelected, colSelected, res, CONFLICT, true);
				}
				/*
				 * else { tfCells[rowSelected][colSelected].setBackground(Color.RED); }
				 */
			}

			if (res == puzzle[rowSelected][colSelected]) {
				tfCells[rowSelected][colSelected].setBackground(Color.GREEN);
				SoundEffect.CORRECT.play();
			} else {
				tfCells[rowSelected][colSelected].setBackground(Color.RED);
				SoundEffect.WRONG.play();
			}

			boolean done = true;
			for (int row = 0; row < GRID_SIZE; ++row) {
				for (int col = 0; col < GRID_SIZE; ++col) {
					if (masks[row][col]) {
						if (tfCells[row][col].getBackground() == Color.RED
								|| tfCells[row][col].getBackground() == OPEN_CELL_BGCOLOR || tfCells[row][col].getBackground()== OPEN_CELL_BGCOLOR_DARK) {
							done = false;
							break;
						}
					}
					/*
					 * if (tfCells[row][col].getText().isEmpty()) { done = false; break; } else if
					 * (Integer.parseInt(selectedBoxContent) != puzzle[row][col]) { //
					 * System.out.println(Integer.parseInt(tfCells[row][col].getText())); done =
					 * false; break; }
					 */
				}
			}
			if (done) {
				// EDIT IN NEW FILE
				
				JOptionPane.showMessageDialog(null, "Congratulation!");
				createFrame();
				dispose();
				resetMask();
				
				// insert reset mask
			}
		}

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub

		}
	}
	// create page after winning the game

	public static void check(int rowSelected, int colSelected, int res, Color background, boolean isFirst) {
		int forCheck2;
		for (int row = 0; row < GRID_SIZE; ++row) {
			if (tfCells[row][colSelected].isEditable()) {
				if (isFirst && !tfCells[row][colSelected].getText().isEmpty()) {
					forCheck2 = Integer.parseInt(tfCells[row][colSelected].getText());
					check(row, colSelected, forCheck2, background, false);
				}
				continue;
			} else if (res == Integer.parseInt(tfCells[row][colSelected].getText()) &&!hints[row][colSelected]) {
				tfCells[row][colSelected].setBackground(background);

			}
		}
		for (int col = 0; col < GRID_SIZE; ++col) {
			if (tfCells[rowSelected][col].isEditable()) {
				if (isFirst && !tfCells[rowSelected][col].getText().isEmpty()) {
					forCheck2 = Integer.parseInt(tfCells[rowSelected][col].getText());
					check(rowSelected, col, forCheck2, background, false);
				}
				continue;
			} else if (res == Integer.parseInt(tfCells[rowSelected][col].getText()) &&!hints[rowSelected][col]) {
				tfCells[rowSelected][col].setBackground(background);
			}
		}
		// to check the 3x3 box

		for (int row = (rowSelected / 3) * 3; row < (rowSelected / 3) * 3 + 3; row++) {
			for (int col = (colSelected / 3) * 3; col < (colSelected / 3) * 3 + 3; col++) {
				if (tfCells[row][col].isEditable()) {
					if (isFirst && !tfCells[row][col].getText().isEmpty()) {
						forCheck2 = Integer.parseInt(tfCells[row][col].getText());
						check(row, col, forCheck2, background, false);
					}
					continue;
				} else if (res == Integer.parseInt(tfCells[row][col].getText()) &&!hints[row][col]) {
					tfCells[row][col].setBackground(background);
				}
			}
		}
	}

	// copy to new file
	public static void setDark(boolean dark) {
		if (!dark) {
			for (int row = 0; row < GRID_SIZE; ++row) {
				for (int col = 0; col < GRID_SIZE; ++col) {
					if (masks[row][col]) {
						if(tfCells[row][col].getBackground()==OPEN_CELL_BGCOLOR_DARK) {
							tfCells[row][col].setBackground(OPEN_CELL_BGCOLOR);
						}
					} else {
						if(tfCells[row][col].getBackground()==DARK_MODE_COLOR) {
							tfCells[row][col].setBackground(Color.WHITE);
						}
						
					}
					tfCells[row][col].setForeground(Color.BLACK);


					// COPY TO NEW FILE
					if (row == 0 && col == 0)
						tfCells[row][col].setBorder(BorderFactory.createMatteBorder(6, 6, 1, 1, Color.BLACK));
					else if (row == GRID_SIZE - 1 && col == GRID_SIZE - 1)
						tfCells[row][col].setBorder(BorderFactory.createMatteBorder(1, 1, 6, 6, Color.BLACK));
					else if (row == GRID_SIZE - 1 && col == 0)
						tfCells[row][col].setBorder(BorderFactory.createMatteBorder(1, 6, 6, 1, Color.BLACK));
					else if (row == 0 && col == GRID_SIZE - 1)
						tfCells[row][col].setBorder(BorderFactory.createMatteBorder(6, 1, 1, 6, Color.BLACK));

					else if (row == 0 && col % 3 == 0)
						tfCells[row][col].setBorder(BorderFactory.createMatteBorder(6, 2, 1, 1, Color.BLACK));
					else if (row == 0 && col % 3 == 2)
						tfCells[row][col].setBorder(BorderFactory.createMatteBorder(6, 1, 1, 2, Color.BLACK));
					else if (row % 3 == 0 && col == 0)
						tfCells[row][col].setBorder(BorderFactory.createMatteBorder(2, 6, 1, 1, Color.BLACK));
					else if (row % 3 == 2 && col == 0)
						tfCells[row][col].setBorder(BorderFactory.createMatteBorder(1, 6, 2, 1, Color.BLACK));
					else if (row == GRID_SIZE - 1 && col % 3 == 0)
						tfCells[row][col].setBorder(BorderFactory.createMatteBorder(1, 2, 6, 1, Color.BLACK));
					else if (row == GRID_SIZE - 1 && col % 3 == 2)
						tfCells[row][col].setBorder(BorderFactory.createMatteBorder(1, 1, 6, 2, Color.BLACK));
					else if (row % 3 == 0 && col == GRID_SIZE - 1)
						tfCells[row][col].setBorder(BorderFactory.createMatteBorder(2, 1, 1, 6, Color.BLACK));
					else if (row % 3 == 2 && col == GRID_SIZE - 1)
						tfCells[row][col].setBorder(BorderFactory.createMatteBorder(1, 1, 2, 6, Color.BLACK));

					else if (col == 0)
						tfCells[row][col].setBorder(BorderFactory.createMatteBorder(1, 6, 1, 1, Color.BLACK));
					else if (row == 0)
						tfCells[row][col].setBorder(BorderFactory.createMatteBorder(6, 1, 1, 1, Color.BLACK));
					else if (row == GRID_SIZE - 1)
						tfCells[row][col].setBorder(BorderFactory.createMatteBorder(1, 1, 6, 1, Color.BLACK));
					else if (col == GRID_SIZE - 1)
						tfCells[row][col].setBorder(BorderFactory.createMatteBorder(1, 1, 1, 6, Color.BLACK));

					else if (row % 3 == 0 && col % 3 == 0)
						tfCells[row][col].setBorder(BorderFactory.createMatteBorder(2, 2, 1, 1, Color.BLACK));
					else if (row % 3 == 1 && col % 3 == 0)
						tfCells[row][col].setBorder(BorderFactory.createMatteBorder(1, 2, 1, 1, Color.BLACK));
					else if (row % 3 == 2 && col % 3 == 0)
						tfCells[row][col].setBorder(BorderFactory.createMatteBorder(1, 2, 2, 1, Color.BLACK));

					else if (row % 3 == 0 && col % 3 == 1)
						tfCells[row][col].setBorder(BorderFactory.createMatteBorder(2, 1, 1, 1, Color.BLACK));
					else if (row % 3 == 2 && col % 3 == 1)
						tfCells[row][col].setBorder(BorderFactory.createMatteBorder(1, 1, 2, 1, Color.BLACK));

					else if (row % 3 == 0 && col % 3 == 2)
						tfCells[row][col].setBorder(BorderFactory.createMatteBorder(2, 1, 1, 2, Color.BLACK));
					else if (row % 3 == 1 && col % 3 == 2)
						tfCells[row][col].setBorder(BorderFactory.createMatteBorder(1, 1, 1, 2, Color.BLACK));
					else if (row % 3 == 2 && col % 3 == 2)
						tfCells[row][col].setBorder(BorderFactory.createMatteBorder(1, 1, 2, 2, Color.BLACK));

					else
						tfCells[row][col].setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
				}
			}
		} else {
			for (int row = 0; row < GRID_SIZE; ++row) {
				for (int col = 0; col < GRID_SIZE; ++col) {
					if (masks[row][col]) {
						if(tfCells[row][col].getBackground()==OPEN_CELL_BGCOLOR) {
							tfCells[row][col].setBackground(OPEN_CELL_BGCOLOR_DARK);
						}
					} else {
						if(tfCells[row][col].getBackground()==Color.WHITE) {
							tfCells[row][col].setBackground(DARK_MODE_COLOR);
						}
						
					}
					tfCells[row][col].setForeground(Color.WHITE);
					// COPY TO NEW FILE
					if (row == 0 && col == 0)
						tfCells[row][col].setBorder(BorderFactory.createMatteBorder(6, 6, 1, 1, Color.WHITE));
					else if (row == GRID_SIZE - 1 && col == GRID_SIZE - 1)
						tfCells[row][col].setBorder(BorderFactory.createMatteBorder(1, 1, 6, 6, Color.WHITE));
					else if (row == GRID_SIZE - 1 && col == 0)
						tfCells[row][col].setBorder(BorderFactory.createMatteBorder(1, 6, 6, 1, Color.WHITE));
					else if (row == 0 && col == GRID_SIZE - 1)
						tfCells[row][col].setBorder(BorderFactory.createMatteBorder(6, 1, 1, 6, Color.WHITE));

					else if (row == 0 && col % 3 == 0)
						tfCells[row][col].setBorder(BorderFactory.createMatteBorder(6, 2, 1, 1, Color.WHITE));
					else if (row == 0 && col % 3 == 2)
						tfCells[row][col].setBorder(BorderFactory.createMatteBorder(6, 1, 1, 2, Color.WHITE));
					else if (row % 3 == 0 && col == 0)
						tfCells[row][col].setBorder(BorderFactory.createMatteBorder(2, 6, 1, 1, Color.WHITE));
					else if (row % 3 == 2 && col == 0)
						tfCells[row][col].setBorder(BorderFactory.createMatteBorder(1, 6, 2, 1, Color.WHITE));
					else if (row == GRID_SIZE - 1 && col % 3 == 0)
						tfCells[row][col].setBorder(BorderFactory.createMatteBorder(1, 2, 6, 1, Color.WHITE));
					else if (row == GRID_SIZE - 1 && col % 3 == 2)
						tfCells[row][col].setBorder(BorderFactory.createMatteBorder(1, 1, 6, 2, Color.WHITE));
					else if (row % 3 == 0 && col == GRID_SIZE - 1)
						tfCells[row][col].setBorder(BorderFactory.createMatteBorder(2, 1, 1, 6, Color.WHITE));
					else if (row % 3 == 2 && col == GRID_SIZE - 1)
						tfCells[row][col].setBorder(BorderFactory.createMatteBorder(1, 1, 2, 6, Color.WHITE));

					else if (col == 0)
						tfCells[row][col].setBorder(BorderFactory.createMatteBorder(1, 6, 1, 1, Color.WHITE));
					else if (row == 0)
						tfCells[row][col].setBorder(BorderFactory.createMatteBorder(6, 1, 1, 1, Color.WHITE));
					else if (row == GRID_SIZE - 1)
						tfCells[row][col].setBorder(BorderFactory.createMatteBorder(1, 1, 6, 1, Color.WHITE));
					else if (col == GRID_SIZE - 1)
						tfCells[row][col].setBorder(BorderFactory.createMatteBorder(1, 1, 1, 6, Color.WHITE));

					else if (row % 3 == 0 && col % 3 == 0)
						tfCells[row][col].setBorder(BorderFactory.createMatteBorder(2, 2, 1, 1, Color.WHITE));
					else if (row % 3 == 1 && col % 3 == 0)
						tfCells[row][col].setBorder(BorderFactory.createMatteBorder(1, 2, 1, 1, Color.WHITE));
					else if (row % 3 == 2 && col % 3 == 0)
						tfCells[row][col].setBorder(BorderFactory.createMatteBorder(1, 2, 2, 1, Color.WHITE));

					else if (row % 3 == 0 && col % 3 == 1)
						tfCells[row][col].setBorder(BorderFactory.createMatteBorder(2, 1, 1, 1, Color.WHITE));
					else if (row % 3 == 2 && col % 3 == 1)
						tfCells[row][col].setBorder(BorderFactory.createMatteBorder(1, 1, 2, 1, Color.WHITE));

					else if (row % 3 == 0 && col % 3 == 2)
						tfCells[row][col].setBorder(BorderFactory.createMatteBorder(2, 1, 1, 2, Color.WHITE));
					else if (row % 3 == 1 && col % 3 == 2)
						tfCells[row][col].setBorder(BorderFactory.createMatteBorder(1, 1, 1, 2, Color.WHITE));
					else if (row % 3 == 2 && col % 3 == 2)
						tfCells[row][col].setBorder(BorderFactory.createMatteBorder(1, 1, 2, 2, Color.WHITE));

					else
						tfCells[row][col].setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.WHITE));

				}
			}
		}

	}

	public void createFrame() {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				JMenu menu = new JMenu("File");
				JMenuBar mb = new JMenuBar();
				menu.add(new JMenuItem("New Game"));
				menu.add(new JMenuItem("Reset Game"));
				menu.add(new JMenuItem("Exit"));
				mb.add(menu);
				Button resetBtn = new Button("Reset");
				Button newGameBtn = new Button("New Game");
				JFrame frame = new JFrame("done");
				frame.setJMenuBar(mb);
				frame.setLayout(new FlowLayout());
				frame.setTitle("end game");
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				frame.setVisible(true);
				frame.setSize(250, 150);
				frame.add(resetBtn);
				resetBtn.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent evt) {
						frame.dispose();
						new Sudoku();
					}
				});
				frame.add(newGameBtn);
				newGameBtn.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent evt) {
						frame.dispose();
						newPuzzle();
						new Sudoku();
					}
				});
			}
		});
	}

}