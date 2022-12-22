package edu.du;

import edu.du.dudraw.DUDraw;

import java.awt.*;

public class DemoDriver {

    public static void main(String[] args) {

        DUDraw.setCanvasSize(1024, 1024);
        DUDraw.setScale(0, 10);

        DUDraw.setPenColor(DUDraw.BLACK);
        DUDraw.filledTriangle(0, 0, 0, 5, 5, 0);

        DUDraw.setPenColor(DUDraw.BLACK);
        DUDraw.triangle(4.8, 0.5, 4.8, 4.8, 0.5, 4.8);

        DUDraw.setPenColor(DUDraw.YELLOW);
        DUDraw.filledQuadrilateral(5, 0, 6.25, 2.5, 10, 2.5, 8.75, 0);

        DUDraw.setPenColor(DUDraw.YELLOW);
        DUDraw.setPenRadius(7);
        DUDraw.quadrilateral(5, 5, 6.25, 2.5, 10, 2.5, 8.75, 5);

        DUDraw.setPenColor(DUDraw.GREEN);
        DUDraw.setPenRadius(1);
        DUDraw.sector(2.5, 7.5, 2, 45, 60);
        DUDraw.filledSector(2.5, 7.5, 2, 60, 75);

        DUDraw.ellipticalSector(2.5, 7.5, 1, 2, 90, 120);

        DUDraw.filledEllipticalSector(2.5, 7.5, 1, 2, 120, 150);

        DUDraw.annulus(1.70, 6.25, 0.5, 0.75);

        DUDraw.filledAnnulus(3.3, 6.25, 0.5, 0.75);

        DUDraw.setPenColor(DUDraw.BLUE);
        DUDraw.ellipse(8.75, 8.75, 1, 0.5);

        DUDraw.filledEllipse(6.25, 6.25, 1, 0.5);

        DUDraw.arc(5, 5, 4.5, 75, 85);

        DUDraw.ellipticalArc(5, 5, 4, 5, 10,25);

        System.out.println(DUDraw.getPixelColor(1.25, 1.25));
        System.out.println(DUDraw.getPixelColor(6.25, 1.25));
        System.out.println(DUDraw.getPixelColor(6.25, 6.25));
        System.out.println(DUDraw.getPixelColor(7.5, 7.5));

        DUDraw.setPenRadius(2);
        DUDraw.setPenColor(Color.black);

        boolean startSet = false;
        double startX = 0.0, startY = 0.0;

        DUDraw.enableDoubleBuffering();

        while(true)
        {




            //Mouse is released
            if(DUDraw.getMouseReleased() > 0)
            {
                DUDraw.xorOff();
                DUDraw.show();

                startX = 0.0;
                startY = 0.0;
                startSet = false;
            }

            //Mouse is pressed
            if(DUDraw.getMousePressed() > 0)
            {
                DUDraw.xorOn();
                DUDraw.show();

                if(!startSet)
                {
                    startX = DUDraw.mouseX();
                    startY = DUDraw.mouseY();
                    startSet = true;
                }
            }

            //Draw rubber-banding rectangle
            if(startSet)
            {
                double midX = Math.abs((DUDraw.mouseX() + startX) / 2);
                double midY = Math.abs((DUDraw.mouseY() + startY) / 2);
                double lenX = Math.abs((DUDraw.mouseX() - startX) / 2);
                double lenY = Math.abs((DUDraw.mouseY() - startY) / 2);

                DUDraw.rectangle(midX, midY, lenX, lenY);
                DUDraw.show();
                DUDraw.rectangle(midX, midY, lenX, lenY);
            }

            DUDraw.pause(17);
        }


    }
}
