import javax.swing.*;

public class MainForm {
    private JPanel mainPanel;
    private JButton button_openImage;
    private JPanel afterPanel;
    private JPanel beforePanel;

    private MainForm() {
        button_openImage.addActionListener(e -> openImage());
    }

    private void openImage() {
        JFileChooser fileChooser = new JFileChooser();
        int showDialog = fileChooser.showDialog(null, "Выберите картинку");

        if (showDialog == JFileChooser.APPROVE_OPTION) {
            button_openImage.setText(fileChooser.getSelectedFile().getAbsolutePath());
        }
    }

    /**
     * Running the GUI.
     *
     * @param args arguments
     */
    public static void main(String[] args) {
        JFrame jFrame = new JFrame("Histogram normalization");
        jFrame.setContentPane(new MainForm().mainPanel);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.pack();
        jFrame.setVisible(true);
    }
}