package edu.msudenver;

import edu.du.dudraw.DUDraw;

import java.awt.*;
import java.util.Random;

public class RecursiveArt {
    private static final int WIDTH = 1024;
    private static final int HEIGHT = 1024;

    private Random random;

    public RecursiveArt() {
        DUDraw.setCanvasSize(WIDTH, HEIGHT);
        DUDraw.setScale(HEIGHT, 0);

        random = new Random();
    }

    public void draw() {
        DUDraw.setPenColor(0, 0, 220);
        DUDraw.filledRectangle(0, 0, WIDTH, HEIGHT);

        drawTree(WIDTH / 2, HEIGHT, Math.PI / 2, 100);

    }
 /*Where I implemented the tree's color and size while also giving tree's lean through length and radius size to
    make the coconuts appear*/
    private void drawTree(double x, double y, double angle, double length) {
        DUDraw.setPenColor(166, 42, 42);
        DUDraw.setPenRadius(10);

        double x1 = x, y1 = y;
        double x2 = x1 + Math.cos(angle) * length;
        double y2 = y1 - Math.sin(angle) * length;

        DUDraw.line(x1, y1, x2, y2);

        if (length >= 1) {
            drawTree(x2, y2, angle + Math.PI / 40, length * 0.75);
            drawTree(x2, y2, angle - Math.PI / 8, length * 0.75);



        }
        if(length >= 1) drawLeaves(x2, y2);
            }





// Where I draw the leaves and created the 360 line to make the leaves appear and turn green
    private void drawLeaves(double x, double y) {
        DUDraw.setPenColor(0, 220, 0);
        DUDraw.setPenRadius(1);

        for (int i = 0; i < 10; i++) {
            DUDraw.line(x, y, x + random.nextInt(-100, 100), y + random.nextInt(-100, 100));
        }
    }

    //draw clouds
    private void drawClouds(double x, double y, double radius) {
        DUDraw.setPenColor(220, 220, 220);
        DUDraw.setPenRadius(1);
        DUDraw.filledCircle(x, y, radius);
    }










    public static void main(String[] args) {
        new RecursiveArt().draw();
    }
}
