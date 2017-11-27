package Algorithm;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

public class Algorithm {

  private BufferedImage bufferedImage;
  private WritableRaster writableRaster;

  public BufferedImage getBufferedImage() {
    return bufferedImage;
  }

  public void setBufferedImage(BufferedImage bufferedImage) {
    this.bufferedImage = bufferedImage;
    this.writableRaster = bufferedImage.getRaster();
  }

  public void start() {
    MinMax minAndMax = getMinAndMax();
    normalization(minAndMax.getMin(), minAndMax.getMax());
  }

  private MinMax getMinAndMax() {
    int max = 0;
    int min = 255;
    int[] pixel = new int[3];

    for (int i = 0; i < writableRaster.getWidth(); i++) {
      for (int j = 0; j < writableRaster.getHeight(); j++) {
        writableRaster.getPixel(i, j, pixel);

        if (pixel[0] > max) {
          max = pixel[0];
        }
        if (pixel[0] < min) {
          min = pixel[0];
        }
      }
    }

    if (min == max) {
      min = 0;
      max = 255;
    }

    return new MinMax(min, max);
  }

  private void normalization(int min, int max) {
    int[] pixel = new int[3];

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
  }
}