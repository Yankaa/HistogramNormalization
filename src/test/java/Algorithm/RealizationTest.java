package Algorithm;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class RealizationTest {

  private Realization realization = new Realization();

  @Before
  public void setUp() {
    try {
      realization.setBufferedImage(ImageIO.read(new File("C:\\3.jpg")));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @After
  public void tearDown() {
    realization = null;
  }

  @Test
  public void getMinAndMax() {
    MinMax actual = realization.getMinAndMax();
    MinMax expected = new MinMax(59, 255);

    assertEquals(actual.getMin(), expected.getMin());
    assertEquals(expected.getMin(), expected.getMin());
  }

  @Test
  public void normalization() {
    MinMax minAndMax = realization.getMinAndMax();
    realization.normalization(minAndMax.getMin(), minAndMax.getMax());

    MinMax actual = realization.getMinAndMax();
    MinMax expected = new MinMax(0, 255);

    assertEquals(actual.getMin(), expected.getMin());
    assertEquals(expected.getMin(), expected.getMin());
  }
}