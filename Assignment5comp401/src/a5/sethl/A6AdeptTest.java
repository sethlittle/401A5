package a5.sethl;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Test;

import a6adept.*;

public class A6AdeptTest {

	static public String[] getTestNames() {
		String[] test_names = new String[3];

		test_names[0] = "sampleTest";
		test_names[1] = "windowTest";
		test_names[2] = "tileTest";

		return test_names;
	}

	@Test
	public void sampleTest() {
		Picture p = new PictureImpl(15, 10);

		try {
			Iterator<Pixel> it = p.sample(16, 9, 1, 2);
			fail("No exception thrown");
		} catch (IllegalArgumentException e) {
		} catch (Exception e) {
			fail("Should have caught an IllegalArgumentException");
		}

		try {
			Iterator<Pixel> it = p.sample(12, 11, 1, 2);
			fail("No exception thrown");
		} catch (IllegalArgumentException e) {
		} catch (Exception e) {
			fail("Should have caught an IllegalArgumentException");
		}

		try {
			Iterator<Pixel> it = p.sample(10, 5, -1, 2);
			fail("No exception thrown");
		} catch (IllegalArgumentException e) {
		} catch (Exception e) {
			fail("Should have caught an IllegalArgumentException");
		}

		try {
			Iterator<Pixel> it = p.sample(10, 5, 1, -4);
			fail("No exception thrown");
		} catch (IllegalArgumentException e) {
		} catch (Exception e) {
			fail("Should have caught an IllegalArgumentException");
		}

		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 15; j++) {
				p.setPixel(j, i, new GrayPixel(i * 0.06));
			}
		}
		Iterator<Pixel> sampleIter = p.sample(2, 3, 3, 4);
		Pixel pix;
		for (int j = 0; j < (p.getHeight() - 3) / 4; j++) {
			for (int i = 0; i < (p.getWidth() - 2) / 3; i++) {
				if (sampleIter.hasNext()) {
					pix = sampleIter.next();
					assertEquals(p.getPixel(2 + i * 3, 3 + j * 4), pix);
				}
			}
		}

		p = new PictureImpl(10, 10);
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				p.setPixel(i, j, new GrayPixel(0.08 * i));
			}
		}
		Iterator<Pixel> sampleIter1 = p.sample(0, 0, 1, 1);
		Pixel pix1;
		for (int i = 0; i < (p.getHeight() - 0) / 1; i++) {
			for (int j = 0; j < (p.getWidth() - 0) / 1; j++) {
				if (sampleIter1.hasNext()) {
					pix1 = sampleIter1.next();
					assertEquals(p.getPixel(j, i), pix1);
				}
			}
		}

		Iterator<Pixel> sampleIter2 = p.sample(4, 6, 3, 2);
		Pixel pix2;
		for (int i = 0; i < (p.getHeight() - 6) / 2; i++) {
			for (int j = 0; j < (p.getWidth() - 4) / 3; j++) {
				if (sampleIter2.hasNext()) {
					pix2 = sampleIter2.next();
					assertEquals(p.getPixel(4 + j * 3, 6 + i * 2), pix2);
				}
			}
		}

	}

	@Test
	public void windowTest() {
		Picture p = new PictureImpl(5, 5);

		try {
			Iterator<SubPicture> iter = p.window(2, 6);
			fail("An Exception should have been thrown");
		} catch (IllegalArgumentException e) {
		} catch (Exception e) {
			fail("An IllegalArgumentException should have been thrown");
		}

		try {
			Iterator<SubPicture> iter = p.window(7, 3);
			fail("An Exception should have been thrown");
		} catch (IllegalArgumentException e) {
		} catch (Exception e) {
			fail("An IllegalArgumentException should have been thrown");
		}

		try {
			Iterator<SubPicture> iter = p.window(2, -1);
			fail("An Exception should have been thrown");
		} catch (IllegalArgumentException e) {
		} catch (Exception e) {
			fail("An IllegalArgumentException should have been thrown");
		}

		try {
			Iterator<SubPicture> iter = p.window(-3, 3);
			fail("An Exception should have been thrown");
		} catch (IllegalArgumentException e) {
		} catch (Exception e) {
			fail("An IllegalArgumentException should have been thrown");
		}

		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				p.setPixel(i, j, new GrayPixel(0.19 * i));
			}
		}
		SubPicture sub;
		SubPicture sub1;
		Iterator<SubPicture> it = p.window(3, 2);
		for (int i = 0; i < p.getHeight() - 1; i++) {
			for (int j = 0; j < p.getWidth() - 2; j++) {
				if (it.hasNext()) {
					sub = it.next();
					sub1 = p.extract(j, i, 3, 2);
					if (sub.getWidth() != sub1.getWidth() || sub.getHeight() != sub1.getHeight()) {
						fail("The subpicture did not have correct dimensions");
					}
					for (int m = 0; m < sub.getHeight(); m++) {
						for (int n = 0; n < sub.getWidth(); n++) {
							assertEquals(sub.getPixel(n, m), sub1.getPixel(n, m));
						}
					}

				}
			}
		}
	}

	@Test
	public void tileTest() {
		Picture p = new PictureImpl(5, 5);

		try {
			Iterator<SubPicture> iter = p.tile(2, 6);
			fail("An Exception should have been thrown");
		} catch (IllegalArgumentException e) {
		} catch (Exception e) {
			fail("An IllegalArgumentException should have been thrown");
		}

		try {
			Iterator<SubPicture> iter = p.tile(7, 3);
			fail("An Exception should have been thrown");
		} catch (IllegalArgumentException e) {
		} catch (Exception e) {
			fail("An IllegalArgumentException should have been thrown");
		}

		try {
			Iterator<SubPicture> iter = p.tile(2, -1);
			fail("An Exception should have been thrown");
		} catch (IllegalArgumentException e) {
		} catch (Exception e) {
			fail("An IllegalArgumentException should have been thrown");
		}

		try {
			Iterator<SubPicture> iter = p.tile(-3, 3);
			fail("An Exception should have been thrown");
		} catch (IllegalArgumentException e) {
		} catch (Exception e) {
			fail("An IllegalArgumentException should have been thrown");
		}

		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				p.setPixel(i, j, new GrayPixel(0.19 * i));
			}
		}
		SubPicture sub;
		SubPicture sub1;
		Iterator<SubPicture> iter = p.tile(2, 2);
		for (int i = 0; i < p.getHeight() / 2; i = i + 2) {
			for (int j = 0; j < p.getWidth() / 2; j = j + 2) {
				if (iter.hasNext()) {
					sub = iter.next();
					sub1 = p.extract(j, i, 2, 2);
					if (sub.getWidth() != sub1.getWidth() || sub.getHeight() != sub1.getHeight()) {
						fail("The subpicture did not have correct dimensions");
					}
					for (int m = 0; m < sub.getHeight(); m++) {
						for (int n = 0; n < sub.getWidth(); n++) {
							assertEquals(sub.getPixel(n, m), sub1.getPixel(n, m));
						}
					}

				}
			}
		}
	}
}
