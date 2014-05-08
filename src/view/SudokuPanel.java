package view;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import model.Game;
import controller.SudokuController;

/**
 * This class draws the sudoku panel and reacts to updates from the model.
 * 
 * @author Eric Beijer
 */
public class SudokuPanel extends JPanel{
	// Color constant for candidates.
	private static final Color COLOR_CANDIDATE = new Color(102, 153, 255);

	private Field[][] fields; // Array of fields.
	private JPanel[][] panels; // Panels holding the fields.

	/**
	 * Constructs the panel, adds sub panels and adds fields to these sub
	 * panels.
	 */
	public SudokuPanel() {
		super(new GridLayout(3, 3));

		panels = new JPanel[3][3];
		for (int y = 0; y < 3; y++) {
			for (int x = 0; x < 3; x++) {
				panels[y][x] = new JPanel(new GridLayout(3, 3));
				panels[y][x].setBorder(BorderFactory
						.createLineBorder(Color.DARK_GRAY));
				add(panels[y][x]);
			}
		}

		fields = new Field[9][9];
		for (int y = 0; y < 9; y++) {
			for (int x = 0; x < 9; x++) {
				fields[y][x] = new Field(x, y);
				panels[y / 3][x / 3].add(fields[y][x]);
			}
		}
	}

	/**
	 * Sets the fields corresponding to given game.
	 * 
	 * @param game
	 *            Game to be set.
	 */
	public void setGame(Game game) {
		for (int y = 0; y < 9; y++) {
			for (int x = 0; x < 9; x++) {
				fields[y][x].setBackground(Color.WHITE);
				fields[y][x].setNumber(game.getNumber(x, y), false);
			}
		}
	}

	public void setNumber(Game game) {
		fields[game.getSelectedFieldY()][game.getSelectedFieldX()].setNumber(
				game.getSelectedNumber(), true);
	}

	/**
	 * Adds controller to all sub panels.
	 * 
	 * @param sudokuController
	 *            Controller which controls all user actions.
	 */
	
	public void setController(SudokuController sudokuController) {
		for (int y = 0; y < 3; y++) {
			for (int x = 0; x < 3; x++)
				panels[y][x].addMouseListener(sudokuController);
		}
	} 
}
