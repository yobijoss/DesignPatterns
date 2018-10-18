package com.yobijoss.designpatterns.pdf;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.RectF;

public class PDFImage implements PDFElement {

    private Bitmap image;

    public PDFImage(Bitmap image) {
        this.image = image;
    }

    @Override
    public int write(int startX, int endX, int top, Canvas canvas) {
        float contentWidth = endX - startX;
        float scale = contentWidth / image.getWidth();
        float bottom = top + (image.getHeight() * scale);

        RectF dst = new RectF(startX, top, endX, bottom);

        canvas.drawBitmap(image, null, dst, null);

        return (int) (image.getHeight() * scale);
    }
}
