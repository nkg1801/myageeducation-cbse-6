package com.myAgeEducation.cbseClass6New.maths.symmetry;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;

public class SymmetryImageGenerator {
    public static Bitmap generate(String imageCode) {
        if (imageCode.equals("SYMMETRY_STAR_4")) {
            return generateStar4();
        } else if (imageCode.equals("SYMMETRY_ARROW")) {
            return generateArrow();
        } else if (imageCode.equals("SYMMETRY_PLUS")) {
            return generatePlus();
        } else if (imageCode.equals("SYMMETRY_DIAMOND")) {
            return generateDiamond();
        } else if (imageCode.equals("SYMMETRY_HEART")) {
            return generateHeart();
        } else if (imageCode.equals("SYMMETRY_RECT_LINES")) {
            return generateRectangleWithLines();
        } else if (imageCode.equals("SYMMETRY_SQUARE_LINES")) {
            return generateSquareWithLines();
        } else if (imageCode.equals("SYMMETRY_TRIANGLE_LINES")) {
            return generateTriangleWithLines();
        }
        return null;
    }

    private static Bitmap generateStar4() {
        int size = 500;
        Bitmap bitmap = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        canvas.drawColor(Color.WHITE);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.rgb(33, 150, 243));
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(8);
        Path path = new Path();
        float center = size / 2f;
        float outer = size * 0.45f;
        float inner = size * 0.15f;
        path.moveTo(center, center - outer);
        path.lineTo(center + inner, center - inner);
        path.lineTo(center + outer, center);
        path.lineTo(center + inner, center + inner);
        path.lineTo(center, center + outer);
        path.lineTo(center - inner, center + inner);
        path.lineTo(center - outer, center);
        path.lineTo(center - inner, center - inner);
        path.close();
        canvas.drawPath(path, paint);
        return bitmap;
    }

    private static Bitmap generateArrow() {
        int size = 500;
        Bitmap bitmap = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        canvas.drawColor(Color.WHITE);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(8);
        Path path = new Path();
        path.moveTo(100, 200);
        path.lineTo(300, 200);
        path.lineTo(300, 100);
        path.lineTo(450, 250);
        path.lineTo(300, 400);
        path.lineTo(300, 300);
        path.lineTo(100, 300);
        path.close();
        canvas.drawPath(path, paint);
        return bitmap;
    }

    private static Bitmap generatePlus() {
        int size = 500;
        Bitmap bitmap = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        canvas.drawColor(Color.WHITE);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(8);
        Path path = new Path();
        path.moveTo(200, 50);
        path.lineTo(300, 50);
        path.lineTo(300, 200);
        path.lineTo(450, 200);
        path.lineTo(450, 300);
        path.lineTo(300, 300);
        path.lineTo(300, 450);
        path.lineTo(200, 450);
        path.lineTo(200, 300);
        path.lineTo(50, 300);
        path.lineTo(50, 200);
        path.lineTo(200, 200);
        path.close();
        canvas.drawPath(path, paint);
        return bitmap;
    }

    private static Bitmap generateDiamond() {
        int size = 500;
        Bitmap bitmap = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        canvas.drawColor(Color.WHITE);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.MAGENTA);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(8);
        Path path = new Path();
        path.moveTo(250, 50);
        path.lineTo(450, 250);
        path.lineTo(250, 450);
        path.lineTo(50, 250);
        path.close();
        canvas.drawPath(path, paint);
        return bitmap;
    }

    private static Bitmap generateHeart() {
        int size = 500;
        Bitmap bitmap = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        canvas.drawColor(Color.WHITE);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.rgb(255, 64, 129));
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(8);
        Path path = new Path();
        path.moveTo(250, 150);
        path.cubicTo(250, 100, 100, 100, 100, 250);
        path.cubicTo(100, 350, 250, 450, 250, 450);
        path.cubicTo(250, 450, 400, 350, 400, 250);
        path.cubicTo(400, 100, 250, 100, 250, 150);
        canvas.drawPath(path, paint);
        return bitmap;
    }

    private static Bitmap generateRectangleWithLines() {
        int width = 600;
        int height = 400;
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        canvas.drawColor(Color.WHITE);

        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(6);

        // Rectangle
        canvas.drawRect(100, 100, 500, 300, paint);

        // Line A (Vertical Symmetry)
        Paint lineAPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        lineAPaint.setColor(Color.BLUE);
        lineAPaint.setStrokeWidth(4);
        canvas.drawLine(300, 50, 300, 350, lineAPaint);
        drawLabel(canvas, "Line A", 300, 40, Color.BLUE);

        // Line B (Diagonal - Not Symmetry)
        Paint lineBPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        lineBPaint.setColor(Color.rgb(255, 165, 0)); // Orange
        lineBPaint.setStrokeWidth(4);
        canvas.drawLine(50, 350, 550, 50, lineBPaint);
        drawLabel(canvas, "Line B", 550, 40, Color.rgb(255, 165, 0));

        return bitmap;
    }

    private static Bitmap generateSquareWithLines() {
        int size = 500;
        Bitmap bitmap = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        canvas.drawColor(Color.WHITE);

        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(6);

        // Square
        canvas.drawRect(100, 100, 400, 400, paint);

        // Line A (Diagonal Symmetry)
        Paint lineAPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        lineAPaint.setColor(Color.RED);
        lineAPaint.setStrokeWidth(4);
        canvas.drawLine(50, 50, 450, 450, lineAPaint);
        drawLabel(canvas, "Line A", 50, 40, Color.RED);

        // Line B (Vertical Symmetry)
        Paint lineBPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        lineBPaint.setColor(Color.GREEN);
        lineBPaint.setStrokeWidth(4);
        canvas.drawLine(250, 50, 250, 450, lineBPaint);
        drawLabel(canvas, "Line B", 250, 40, Color.GREEN);

        return bitmap;
    }

    private static Bitmap generateTriangleWithLines() {
        int size = 500;
        Bitmap bitmap = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        canvas.drawColor(Color.WHITE);

        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(6);

        // Equilateral Triangle
        Path path = new Path();
        path.moveTo(250, 100);
        path.lineTo(100, 400);
        path.lineTo(400, 400);
        path.close();
        canvas.drawPath(path, paint);

        // Line A (Median Symmetry)
        Paint lineAPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        lineAPaint.setColor(Color.MAGENTA);
        lineAPaint.setStrokeWidth(4);
        canvas.drawLine(250, 50, 250, 450, lineAPaint);
        drawLabel(canvas, "Line A", 250, 40, Color.MAGENTA);

        // Line B (Base line - Not Symmetry)
        Paint lineBPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        lineBPaint.setColor(Color.CYAN);
        lineBPaint.setStrokeWidth(4);
        canvas.drawLine(50, 300, 450, 300, lineBPaint);
        drawLabel(canvas, "Line B", 50, 290, Color.CYAN);

        return bitmap;
    }

    private static void drawLabel(Canvas canvas, String text, float x, float y, int color) {
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(color);
        paint.setTextSize(30);
        paint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText(text, x, y, paint);
    }
}
