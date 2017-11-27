import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.IOException;

public class MainForm {
    private JPanel mainPanel;
    private JButton button_openImage;
    private JPanel afterPanel;
    private JPanel beforePanel;

    private BufferedImage bufferedImage;

    private MainForm() {
        button_openImage.addActionListener(e -> downloadImage());
    }

    private void downloadImage() {
        JFileChooser fileChooser = new JFileChooser();
        int showDialog = fileChooser.showDialog(null, "Выберите картинку");

        if (showDialog == JFileChooser.APPROVE_OPTION) {
            try {
                bufferedImage = ImageIO.read(fileChooser.getSelectedFile());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        drawImage(beforePanel, bufferedImage);

        start();
    }

    private void start() {
        WritableRaster writableRaster = bufferedImage.getRaster();

        int max = 0;
        int min = 255;
        int[] pixel = new int[3];

        for (int i = 0; i < writableRaster.getWidth(); i++) {
            for (int j = 0; j < writableRaster.getHeight(); j++) {
                writableRaster.getPixel(i, j, pixel);
                if (pixel[0] > max)
                    max = pixel[0];
                if (pixel[0] < min)
                    min = pixel[0];
            }
        }

        for (int i = 0; i < writableRaster.getWidth(); i++) {
            for (int j = 0; j < writableRaster.getHeight(); j++) {
                writableRaster.getPixel(i, j, pixel);

                int value = (pixel[0] - min) * 255 / (max - min);

                pixel[0] = value;
                pixel[1] = value;
                pixel[2] = value;

                writableRaster.setPixel(i, j, pixel);
            }
        }

        drawImage(afterPanel, bufferedImage);
    }

    private void drawImage(JPanel panel, BufferedImage image) {
        panel.getGraphics().drawImage(image, 1, 1, panel.getWidth() - 2, panel.getHeight() - 2, null);
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