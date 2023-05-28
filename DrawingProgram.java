import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class DrawingProgram extends JFrame
        implements MouseMotionListener, MouseListener, ChangeListener, ActionListener {
    private final static int CURSOR_OFFSET_X = 8;
    private final static int CURSOR_OFFSET_Y = 30;
    private final JSlider sliderBrushSize = new JSlider(JSlider.HORIZONTAL, 1, 30, 4);
    private final ToolBarButton circleButton, rectangleButton, triangleButton, squareButton;
    private final JPanel toolbar;
    private boolean dragging = false;
    private Color brushColor = Color.BLACK;
    private int brushSize = 4;
    private Drawable drawable = new Circle();
    private Point p1, p2;

    public DrawingProgram() {
        super("Painter");

        toolbar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        toolbar.setSize(new Dimension(800, 32));
        toolbar.setBackground(Color.WHITE);
        add(toolbar, BorderLayout.SOUTH);

        toolbar.add(new Label("Drag mouse to draw"));

        sliderBrushSize.addChangeListener(this);
        sliderBrushSize.setBackground(Color.WHITE);
        toolbar.add(sliderBrushSize);

        circleButton = new ToolBarButton("icons/circle.png", "Circle", this);
        toolbar.add(circleButton);

        rectangleButton = new ToolBarButton("icons/rectangle.png", "Rectangle", this);
        toolbar.add(rectangleButton);

        triangleButton = new ToolBarButton("icons/triangle.png", "Triangle", this);
        toolbar.add(triangleButton);

        squareButton = new ToolBarButton("icons/square.png", "Square", this);
        toolbar.add(squareButton);

        JPanel drawingArea = new JPanel();
        drawingArea.addMouseMotionListener(this);
        drawingArea.addMouseListener(this);
        add(drawingArea, BorderLayout.CENTER);

        addMouseListener(this);
        setSize(800, 600);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void setPoints(Point p) {
        p.x += CURSOR_OFFSET_X;
        p.y += CURSOR_OFFSET_Y;
        if (p1 == null)
            p1 = p;
        else
            p2 = p;
    }

    private void clearPoints() {
        p1 = null;
        p2 = null;
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(brushColor);
        if (dragging) {
            g.fillOval(p1.x, p1.y, brushSize, brushSize);
            clearPoints();
        } else if (drawable != null && p1 != null && p2 != null) {
            if (Math.max(p1.y, p2.y) < getHeight() - toolbar.getHeight()) {
                drawable.draw(g, p1, p2);
            }
            clearPoints();
        }
        toolbar.repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        dragging = true;
        setPoints(e.getPoint());
        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getModifiers() == MouseEvent.BUTTON3_MASK) {
            brushColor = JColorChooser.showDialog(null, "Choose Colour", getBackground());
            clearPoints();
        } else {
            setPoints(e.getPoint());
            repaint();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        dragging = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        JSlider source = (JSlider) e.getSource();
        if (source.getValueIsAdjusting()) {
            brushSize = (int) source.getValue();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == circleButton) {
            drawable = new Circle();
        } else if (e.getSource() == rectangleButton) {
            drawable = new Rectangle();
        } else if (e.getSource() == triangleButton) {
            drawable = new Triangle();
        } else if (e.getSource() == squareButton) {
            drawable = new Square();
        }
    }

    public static void main(String[] args) {
        new DrawingProgram();
    }
}