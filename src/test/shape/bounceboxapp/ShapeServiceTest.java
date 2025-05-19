
package test.shape.bounceboxapp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.SystemMenuBar;

import java.lang.reflect.Field;
import org.junit.Before;

import shape.bouncebox.*;
import shape.bounceboxapp.ShapeService;
import shape.bounceboxframework.Shape;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import java.io.File;
import java.io.IOException;

import java.nio.file.Path;

public class ShapeServiceTest {

    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();

    @Before
    public void resetSingleton() throws Exception {
        Field instance = ShapeService.class.getDeclaredField("instance");
        instance.setAccessible(true);
        instance.set(null, null);
    }

    @Test
    public void testSingletonInstanceUniqueness() {
        ShapeService instance1 = ShapeService.getInstance();
        ShapeService instance2 = ShapeService.getInstance();
        assertSame(instance1, instance2);
    }

    @Test
    public void testCountByType() {
        ShapeService service = ShapeService.getInstance();
        ArrayList<Shape> shapes = new ArrayList<>();
        shapes.add(new Circle(0, 0, 5));
        shapes.add(new Square(10, 10, 8));
        shapes.add(new Circle(20, 20, 5));
        shapes.add(new Square(30, 30, 8));
        shapes.add(new Circle(40, 40, 5));
        HashMap<String, Integer> count = service.countByType(shapes);
        assertEquals(3, count.get("Circle").intValue());
        assertEquals(2, count.get("Square").intValue());
    }

    @Test
    public void testTotalArea() {
        ShapeService service = ShapeService.getInstance();
        ArrayList<Shape> shapes = new ArrayList<>();
        shapes.add(new Square(10, 10, 8));
        shapes.add(new Square(30, 30, 8));
        shapes.add(new Circle(0, 0, 5));
        shapes.add(new Circle(20, 20, 5));
        double totalArea = service.totalArea(shapes);
        assertEquals(285.075, totalArea, 0.01);
    }

    @Test
    public void testCreateFile() throws IOException {
        File tempFile = tempFolder.newFile("test.txt");
        Files.write(tempFile.toPath(), Arrays.asList("Circle 10 10 5", "Square 20 20 8"));
        ShapeService service = ShapeService.getInstance();
        service.preWork();
        ArrayList<Shape> shapes = service.parseFile(tempFile.toString());
        assertEquals(2, shapes.size());

    }

    @Test
    public void testWriteToFile() throws IOException {
        File tempFile = tempFolder.newFile("test.txt");

        Files.write(tempFile.toPath(), Arrays.asList("Circle 10 10 5", "Square 20 20 8"));

        ShapeService service = ShapeService.getInstance();
        service.preWork();
        ArrayList<shape.bounceboxframework.Shape> shapes = service.parseFile(tempFile.toString());
        assertEquals(2, shapes.size());
        service.displayShapes(shapes);
        File tempOutFile = tempFolder.newFile("out.txt");
        service.writeToFile(tempOutFile.toString());
        String content = new String(Files.readAllBytes(tempOutFile.toPath()));
        assertFalse(content.contains("\u001B[31m"));
        assertFalse(content.contains("\u001B[0m"));

        String expectContent = "Circle: 1\r\nSquare: 1\r\nTotal Area is " + (service.totalArea(shapes) + "\r\n");
        assertEquals(expectContent, content);
    }

    @Test
    public void testExistTempFile() throws IOException {
        File tempFile = tempFolder.newFile("test.txt");
        assertTrue(tempFile.exists());
    }

    @Test
    public void testParseFile_InvalidShapeType() throws IOException {
        File testFile = tempFolder.newFile("test.txt");
        Files.write(testFile.toPath(), Arrays.asList("UnknownShape 1 2 3"));

        ShapeService service = ShapeService.getInstance();
        service.preWork();
        ArrayList<Shape> shapes = service.parseFile(testFile.toString());

        assertTrue(shapes.isEmpty());
    }

}