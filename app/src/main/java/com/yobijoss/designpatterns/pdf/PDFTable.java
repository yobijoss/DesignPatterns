package com.yobijoss.designpatterns.pdf;

import android.graphics.Canvas;

import java.util.ArrayList;
import java.util.List;

public class PDFTable implements PDFElement {

	private int columns;

	private List<PDFElement> elements;

	public PDFTable(int columns) {
		this.columns = columns;
		this.elements = new ArrayList<>();
	}

	@Override
	public int write(int startX, int endX, int y, Canvas canvas) {
        int width = (endX - startX) / columns;
        int currentWidth = startX;
        int height = 0;
        int rowHeight  = 0;

        for (int i = 0; i < elements.size(); i++) {
            int currentHeight = elements.get(i).
                    write(currentWidth, currentWidth + width, y + height, canvas);

            if (currentHeight > rowHeight) {
                rowHeight = currentHeight;
            }

            if ((i + 1) % columns == 0) {
                currentWidth = startX;
                height += rowHeight;
                rowHeight = 0;
            } else {
                currentWidth += width;
            }
        }

        return height;
	}

    public void add(List<PDFElement> elements) {
        this.elements.addAll(elements);
    }

	public void add(PDFElement element) {
	    this.elements.add(element);
    }

}
