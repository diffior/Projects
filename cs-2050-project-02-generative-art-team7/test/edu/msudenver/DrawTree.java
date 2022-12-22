package edu.msudenver;

import edu.du.dudraw.DUDraw;

import java.awt.*;
import java.util.Random;

public class DrawTree {
    private static final int WIDTH = 1024;
    private static final int HEIGHT = 1024;
    private double baseLength, minLength,
            lengthRatio, angleChange,
            leafDensity;

    private float[] leafColor;

    private Random random;

    private DrawTree() {
        DUDraw.setCanvasSize(WIDTH, HEIGHT);
        DUDraw.setScale(HEIGHT, 0);

        random = new Random();

        baseLength = random.nextDouble(HEIGHT / 8, HEIGHT / 2);
        minLength = random.nextInt(1, 10);
        lengthRatio = random.nextDouble(0.25, 0.75);
        angleChange = random.nextDouble(Math.PI / 32, Math.PI / 3);
        // in setup
        leafDensity = random.nextInt(1, 10);
    }

    private void draw() {
        DUDraw.setPenColor(220, 220, 220);
        DUDraw.filledRectangle(0, 0, WIDTH, HEIGHT);

        drawTree(WIDTH / 2, HEIGHT, Math.PI / 2, baseLength);
    }

    private void drawTree(double x, double y, double angle, double length) {
        DUDraw.setPenColor(Color.BLACK);
        DUDraw.setPenRadius(7);

        double x1 = x, y1 = y;
        double x2 = x1 + Math.cos(angle) * length;
        double y2 = y1 - Math.sin(angle) * length;

        DUDraw.line(x1, y1, x2, y2);

        if (length >= minLength) {
            drawTree(x2, y2, angle + angleChange, length * lengthRatio);
            drawTree(x2, y2, angle - angleChange, length * lengthRatio);
        } else {
            drawLeaves(x2, y2);
        }
    }

    private void drawLeaves(double x, double y) {
        DUDraw.setPenColor(
                new Color(random.nextFloat(0, 1),
                        random.nextFloat(0, 1),
                        random.nextFloat(0, 1)));

        for (int i = 0; i < leafDensity; i++) {
            DUDraw.circle(
                    random.nextGaussian(x, 10),
                    random.nextGaussian(y, 10),
                    random.nextInt(2, 5)
            );
        }
    }

    public static void main(String[] args) {
        new DrawTree().draw();
    }
}
