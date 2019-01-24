package a5.sethl;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Test;

import a6jedi;

public class A6JediTest {

	static public String[] getTestNames() {
		String[] test_names = new String[1];

		test_names[0] = "zigzagTest";

		return test_names;
	}

	@Test
	public void zigzagTest() {
		Picture p = new PictureImpl(5, 5);
		for (int i = 0; i < p.getHeight(); i++) {
			for (int j = 0; j < p.getWidth(); j++) {
				p.setPixel(j, i, new GrayPixel(0.2 * i));
			}
		}

		Iterator<Pixel> it = p.zigzag();
		Pixel[] pixelArray = new Pixel[p.getWidth() * p.getHeight()];
		int i = 0;
		int j = 0;
		int count = 0;

		while (count < pixelArray.length && i < p.getHeight() && j < p.getWidth()) {
			pixelArray[count] = p.getPixel(j, i);
			// 1,4 should go to 2, 3
			// 5, 1 to 4, 2
			// 2, 4
			if (j == 0 && i == 0) {
				j = 1;
				i = 0;
			} else if ((j + i) % 2 == 0) {
				if (j == p.getWidth() - 1) {
					i = i + 1;
				} else {
					if (i != 0) {
						i = i - 1;
					}
					if (j != p.getWidth() - 1) {
						j = j + 1;
					}
				}

			} else if ((j + i) % 2 == 1) {
				if (i == p.getHeight() - 1) {
					j = j + 1;
				} else {
					if (i != p.getHeight() - 1) {
						i = i + 1;
					}
					if (j != 0) {
						j = j - 1;
					}
				}

			}
			count++;
		}

		for (int m = 0; m < pixelArray.length; m++) {
			if (it.hasNext()) {
				assertEquals(pixelArray[m].getIntensity(), it.next().getIntensity(), 0.001);
			}
		}

	}
}
