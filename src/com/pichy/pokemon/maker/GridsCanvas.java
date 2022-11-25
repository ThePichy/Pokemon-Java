package com.pichy.pokemon.maker;

import javax.swing.*;
import java.awt.*;

@Deprecated
class GridsCanvas extends JPanel {
    int width, height;

    int rows;

    int cols;

    GridsCanvas(int w, int h, int r, int c) {
        setSize(width = w, height = h);
        rows = r;
        cols = c;
    }

    public void paint(Graphics g) {
        int i;
        width = getSize().width;
        height = getSize().height;


        g.setColor(Color.RED);
        // draw the rows

        int rowHt = height / (rows);
        for (i = 0; i < rows; i++) {

            //  g.drawString("Y: "+i * rowHt,0,i * rowHt);
            g.drawLine(0, i * rowHt, width, i * rowHt);
        }

        g.setColor(Color.GREEN);
        // draw the columns
        int rowWid = width / (cols);
        for (i = 0; i < cols; i++) {
            //    g.drawString("X: "+i * rowWid,0,i * rowHt);
            g.drawLine(i * rowWid, 0, i * rowWid, height);
        }
    }
}