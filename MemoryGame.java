
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.*;

public class MemoryGame extends JFrame {

  int score = 0; // Declare and initialize the score variable
  JPanel mainContents = new JPanel(); // Create a JPanel to hold the main contents of the game
  JPanel buttonPanel = new JPanel(); // Create a JPanel to hold the buttons
  final String scoreText = "Welcome new player. Your current score is: "; // Create a constant string for the initial score text
  JLabel mainLabel = new JLabel(scoreText + score); // Create a JLabel to display the score
  final int rows = 3; // Define the number of rows in the grid
  final int column = 4; // Define the number of columns in the grid
  final int totalRounds = (rows * column) / 2; // Calculate the total number of rounds based on rows and columns

  List<JButton> buttonList = new ArrayList<JButton>(); // Create a List to store JButton objects

  List<Color> colorList = new ArrayList<Color>(); // Create a List to store Color objects for the tiles

  JButton lastClickedButton = null; // Create a reference to the last clicked button, initially null

  int totalMatched = 0; // Initialize the variable to keep track of total matched pairs

  // Constructor for the MemoryGame class
  public MemoryGame() {
    super("Memory Game");
    // Set up main window components
    add(mainContents);
    mainContents.setLayout(new BorderLayout());
    mainContents.add(mainLabel, BorderLayout.NORTH);
    mainContents.add(buttonPanel, BorderLayout.CENTER);

    mainContents.setVisible(true);

    // Set up the grid layout for buttons
    GridLayout glout = new GridLayout(rows, column);
    buttonPanel.setLayout(glout);

    // Set cross-platform look and feel
    setCrossPlatformLook();

    // Create buttons and add action listeners
    for (int i = 0; i < rows * column; i++) {
      JButton btn = new JButton();
      btn.addActionListener(e -> ButtonClicked(e));
      buttonList.add(btn);
    }

    // Add buttons to the button panel
    for (JButton b : buttonList) {
      buttonPanel.add(b);
    }

    // Initialize the colors for the tiles
    InitColors();

    // Set the size and visibility of the window
    setSize(300, 400);
    setVisible(true);
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
  }

  // Initialize the colors for the tiles
  public void InitColors() {
    // Add colors to the list
    colorList.add(Color.BLACK);
    colorList.add(Color.BLACK);
    colorList.add(Color.RED);
    colorList.add(Color.RED);
    colorList.add(Color.MAGENTA);
    colorList.add(Color.MAGENTA);
    colorList.add(Color.CYAN);
    colorList.add(Color.CYAN);
    colorList.add(Color.YELLOW);
    colorList.add(Color.YELLOW);
    colorList.add(Color.PINK);
    colorList.add(Color.PINK);

    // Shuffle the colorList to randomize tile colors
    Collections.shuffle(colorList);
  }

  // Action listener for button clicks
  public void ButtonClicked(ActionEvent e) {
    // Get the clicked button
    Object btnObj = e.getSource(); // Get the button object that triggered the action event
    int index = buttonList.indexOf(btnObj); // Find the index of the clicked button in the buttonList
    JButton currentBtn = ((JButton) (btnObj)); // Cast the button object to a JButton to work with it
    currentBtn.setBackground(colorList.get(index)); // Set the background color of the current button to the color from the list

    // Check if its the first or second click
    if (lastClickedButton == null) { // if first button click
      lastClickedButton = currentBtn;
    } else { // if second button click
      // Check if the colors match
      boolean isMatch = IsColorMatch(lastClickedButton, currentBtn);

      if (isMatch) {
        // Disable the buttons and update score
        currentBtn.setEnabled(false);
        lastClickedButton.setEnabled(false);
        score += 10;
        totalMatched++;

        mainLabel.setText(scoreText + score);

        if (totalMatched == totalRounds) {
          // Show the winning message when all matches are found
          JOptionPane.showMessageDialog(
            this,
            "You've won! The total score is  " + score
          );
        }
      } else {
        // Handle the mismatched colors
        int tempScore = score - 1;
        score = tempScore < 0 ? 0 : tempScore;
        JOptionPane.showMessageDialog(
          this,
          "The two colors did not match " + score
        );
        mainLabel.setText(scoreText + score);

        currentBtn.setEnabled(true);
        lastClickedButton.setEnabled(true);
        currentBtn.setBackground(null);
        lastClickedButton.setBackground(null);
      }

      lastClickedButton = null;
    }
  }

  // Check the colors of two buttons to seeif they match each other
  private boolean IsColorMatch(
    JButton lastClickedButton,
    JButton currentButton
  ) {
    int index1 = buttonList.indexOf(lastClickedButton);
    int index2 = buttonList.indexOf(currentButton);
    return colorList.get(index1).equals(colorList.get(index2));
  }

  // Set the cross-platform look and feel
  public void setCrossPlatformLook() {
    try {
      UIManager.setLookAndFeel(
        UIManager.getCrossPlatformLookAndFeelClassName()
      );
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
