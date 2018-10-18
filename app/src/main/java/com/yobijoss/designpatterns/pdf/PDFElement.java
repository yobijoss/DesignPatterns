package com.yobijoss.designpatterns.pdf;

import android.graphics.Canvas;

public interface PDFElement {
    int write(int startX, int endX, int top, Canvas canvas);
}
