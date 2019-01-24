package a5.sethl;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Test;

import a6novice.*;

public class A6NoviceTest {

	static public String[] getTestNames() {
		String[] test_names = new String[4];

		test_names[0] = "testCoordinateGetters";
		test_names[1] = "testsSetPixel";
		test_names[2] = "testExtract";
		test_names[3] = "testIterator";

		return test_names;
	}

	@Test
	public void testCoordinateGetters() {
		Coordinate c = new Coordinate(2, 5);
		assertEquals(2, c.getX(), 0.001);
		assertEquals(5, c.getY(), 0.001);

		c = new Coordinate(1, 8);
		assertEquals(1, c.getX(), 0.001);
		assertEquals(8, c.getY(), 0.001);
	}

	@Test
	public void testSetPixel() {
		Picture pic = new PictureImpl(5, 5);
		Pixel p = null;
		Coordinate c = new Coordinate(0, 1);

		c = null;
		p = new GrayPixel(0.5);
		try {
			pic.setPixel(c, p);
			fail("Should have thrown an exception");
		} catch (IllegalArgumentException e) {
		} catch (RuntimeException e) {
			fail("An IllegalArgumentException not just a RuntimeException");
		}

		c = new Coordinate(0, 3);
		pic.setPixel(c, p);
		assertEquals(p, pic.getPixel(c));
	}

	@Test
	public void testExtract() {
		Picture pic = new PictureImpl(5, 5);
		Coordinate a = null;
		Coordinate b = new Coordinate(1, 4);
		try {
			pic.extract(a, b);
			fail("should have thrown an exception");
		} catch (IllegalArgumentException e) {
		} catch (RuntimeException e) {
			fail("An IllegalArgumentException not just a RuntimeException");
		}

		a = new Coordinate(1, 1);
		b = null;
		try {
			pic.extract(a, b);
			fail("should have thrown an exception");
		} catch (IllegalArgumentException e) {
		} catch (RuntimeException e) {
			fail("An IllegalArgumentException not just a RuntimeException");
		}

		b = new Coordinate(7, 7);
		try {
			pic.extract(a, b);
			fail("should have thrown an exception");
		} catch (RuntimeException e) {
		}

		b = new Coordinate(3, 3);
		SubPicture sub = new SubPictureImpl(pic, 1, 1, 3, 3);
		Picture ext = pic.extract(a, b);
		assertEquals(sub.getWidth(), ext.getWidth());
		assertEquals(sub.getHeight(), ext.getHeight());
		for (int i = 0; i < sub.getHeight(); i++) {
			for (int j = 0; j < sub.getWidth(); j++) {
				assertEquals(sub.getPixel(i, j), ext.getPixel(new Coordinate(j, i)));
			}
		}

		b = new Coordinate(2, 4);
		SubPicture sub2 = new SubPictureImpl(pic, 1, 1, 2, 4);
		Picture ext2 = pic.extract(a, b);
		assertEquals(sub2.getWidth(), ext2.getWidth());
		assertEquals(sub2.getHeight(), ext2.getHeight());
		for (int i = 0; i < sub2.getHeight(); i++) {
			for (int j = 0; j < sub2.getWidth(); j++) {
				assertEquals(sub2.getPixel(i, j), ext2.getPixel(new Coordinate(j, i)));
			}
		}
	}

	@Test
	public void testIterator() {
		Picture pic = new PictureImpl(5, 5);
		Iterator<Pixel> iter = pic.iterator();
		while (iter.hasNext()) {
			assertEquals(true, iter.hasNext());
			assertEquals(0.5, iter.next().getIntensity(), 0.001);
		}

		try {
			iter.next();
			fail("Thrown an excpetion for calling the next method when there are no next");
		} catch (NoSuchElementException e) {
		} catch (Exception e) {
			fail("The exception thrown should be of the type NoSuchElementException");
		}

	}

}
