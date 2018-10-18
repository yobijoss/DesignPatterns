package com.yobijoss.designpatterns.pdf;

import android.graphics.Canvas;
import android.graphics.Typeface;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;

public class PDFPhrase implements PDFElement {

    private String text;
    private Layout.Alignment alignment;
    private int padding;
    private final int[] color;
    private final int textSize;

    public PDFPhrase(String text, int textSize, Layout.Alignment alignment, int padding,
                     int[] color) {
        this.text = text;
        this.textSize = textSize;
        this.alignment = alignment;
        this.padding = padding;
        this.color = color;
    }

    @Override
    public int write(int startX, int endX, int top, Canvas canvas) {
        TextPaint paint = new TextPaint();

        if (color != null && color.length >= 2) {
            paint.setARGB(255, color[0], color[1], color[2]);
        }

        paint.setTextSize(textSize);
        paint.setAntiAlias(true);

        StaticLayout staticLayout = new StaticLayout(text, paint, (endX - startX - padding),
                alignment, 1.0f, 0.0f, false);

        canvas.save();
        canvas.translate(startX + padding, top);
        staticLayout.draw(canvas);
        canvas.restore();

        return staticLayout.getHeight();
    }
}
