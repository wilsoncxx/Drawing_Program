import java.awt.*;

public interface Drawable {
    public void draw(Graphics graphics, Point p1, Point p2);
}

class Circle implements Drawable {
    @Override
    public void draw(Graphics graphics, Point p1, Point p2) {
        if (p1 != null && p2 != null) {
            Point midpoint = new Point((p1.x + p2.x) / 2, (p1.y + p2.y) / 2);
            int diameter = (int) Math.round(p1.distance(p2));
            int radius = diameter / 2;
            graphics.drawOval(midpoint.x - radius, midpoint.y - radius, diameter, diameter);
        }
    }
}

class Rectangle implements Drawable {
    @Override
    public void draw(Graphics graphics, Point p1, Point p2) {
        if (p1 != null && p2 != null) {
            int x = Math.min(p1.x, p2.x);
            int y = Math.min(p1.y, p2.y);
            int width = Math.abs(p1.x - p2.x);
            int height = Math.abs(p1.y - p2.y);
            graphics.drawRect(x, y, width, height);
        }
    }
}

class Triangle implements Drawable {
    @Override
    public void draw(Graphics graphics, Point p1, Point p2) {
        if (p1 != null && p2 != null) {
            Polygon triangle = new Polygon();
            int width = Math.abs(p1.x - p2.x);
            int height = Math.abs(p1.y - p2.y);
            int startX = Math.min(p1.x, p2.x);
            int startY = Math.min(p1.y, p2.y);
            triangle.addPoint(startX + width / 2, startY);
            triangle.addPoint(startX, startY + height);
            triangle.addPoint(startX + width, startY + height);
            graphics.drawPolygon(triangle);
        }
    }
}

class Square implements Drawable {
    @Override
    public void draw(Graphics graphics, Point p1, Point p2) {
        if (p1 != null && p2 != null) {
            int width = Math.abs(p1.x - p2.x);
            int height = Math.abs(p1.y - p2.y);
            int startX = Math.min(p1.x, p2.x);
            int startY = Math.min(p1.y, p2.y);
            int size = Math.max(width, height);
            graphics.drawRect(startX, startY, size, size);
        }
    }
}