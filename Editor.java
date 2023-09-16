// Here we are creating a text editor using java, but we will also be using the
// GUI to
// make the buttons for this project

// First we will import other necessary libraries before we insert the
// swing library
import java.awt.*;// for the frame and border layout
import java.awt.event.*;// we are taking the event listener (for when we click on the button)
import java.io.*; // we are grabbing the file reader and writer from this library and exception

// Now we import the swing library for the frame, buttons, panel, textarea, etc.
import javax.swing.*;
import javax.swing.filechooser.*;

public class Editor extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;// this is used to ensure compatibility when serializing and
                                                    // deserializing objects
    private JTextArea textArea; // this will allow us to display and edit the text
    private JButton openButton, saveButton;// these buttons will allow us to open and save files

    public Editor() { // my constructor for this class
        super("Text Editor"); // this allows us to name the frame as "Text Editor"
        setSize(600, 400); // this makes the width and height for the frame
        setDefaultCloseOperation(EXIT_ON_CLOSE); // this exits the application when the window is closed
        setLocationRelativeTo(null); // this will set the frame for us in the center of the screen

        textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea); // this will allow the text area to be "scrollable" if the
                                                            // text content exceeds the visible area
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(); // this holds the buttons
        getContentPane().add(buttonPanel, BorderLayout.PAGE_START);

        openButton = new JButton("Open");
        openButton.addActionListener(this);
        buttonPanel.add(openButton);

        saveButton = new JButton("Save");
        saveButton.addActionListener(this);
        buttonPanel.add(saveButton);

        setVisible(true);
    }

    /**
     * this method (below) will handle the actions for when the open or save button
     * is clicked on.
     */
    public void actionPerformed(ActionEvent e) {
        // first we will check to see if the "Open" button was clicked
        // if it was clicked then it will create a dialog for the user to select a file
        // from their computer
        // and ensure that only text files can be selected
        if (e.getSource() == openButton) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileFilter(new FileNameExtensionFilter("Text files", "txt"));
            int returnVal = fileChooser.showOpenDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                try {
                    FileReader reader = new FileReader(fileChooser.getSelectedFile());
                    BufferedReader br = new BufferedReader(reader);
                    String line;
                    StringBuilder sb = new StringBuilder();
                    while ((line = br.readLine()) != null) {
                        sb.append(line).append("\n");
                    }
                    textArea.setText(sb.toString());
                    br.close();
                    reader.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }

            // if the source of the action event is the save button being clicked, then this
            // will activate
        } else if (e.getSource() == saveButton) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileFilter(new FileNameExtensionFilter("Text files", "txt"));
            int returnVal = fileChooser.showSaveDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                try {
                    FileWriter writer = new FileWriter(fileChooser.getSelectedFile());
                    writer.write(textArea.getText());
                    writer.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        new Editor();
    }

}