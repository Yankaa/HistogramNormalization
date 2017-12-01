import Algorithm.MinMax;
import Algorithm.Realization;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class MainForm {

  private JPanel mainPanel;
  private JButton button_openImage;
  private JPanel afterPanel;
  private JPanel beforePanel;

  private Realization realization = new Realization();

  private MainForm() {
    button_openImage.addActionListener(e -> downloadImage());
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

  private void downloadImage() {
    JFileChooser fileChooser = new JFileChooser();
    int showDialog = fileChooser.showDialog(null, "Выберите картинку");

    if (showDialog == JFileChooser.APPROVE_OPTION) {
      try {
        BufferedImage bufferedImage = ImageIO.read(fileChooser.getSelectedFile());
        realization.setBufferedImage(bufferedImage);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    drawImage(beforePanel, realization.getBufferedImage());

    MinMax minAndMax = realization.getMinAndMax();
    realization.normalization(minAndMax.getMin(), minAndMax.getMax());

    drawImage(afterPanel, realization.getBufferedImage());

  }

  private void drawImage(JPanel panel, BufferedImage image) {
    panel.getGraphics().drawImage(image, 1, 1, panel.getWidth() - 2, panel.getHeight() - 2, null);
  }
}