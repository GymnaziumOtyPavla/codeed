
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;



/**
 *
 * @author OndrejTkaczyszyn
 */
public class Head {
    
    
    private Point pos = new Point();
    private Vector dir = new Vector();
    private Graphics graphics;
    private Color usedColor;

    public void home() {
        this.pos.setX(graphics.getWidth()/2);
        this.pos.setY(graphics.getHeight()/2);
    }
    public Head(Graphics g){
        home();
        graphics = g;
    }
    public void color(double r, double g, double b) {
       this.usedColor = Color.color(r,g,b); 
    }
    
    public void turnLeft(double deg) {
       this.dir.rotate(-deg);
    }
    
    public void turnRight(double deg) {
       this.dir.rotate(deg); 
    }
    
    public void forward(double len) {
        double eX,eY,oriX,oriY;
        int intLen;
        oriX = this.pos.getX();
        oriY = this.pos.getY();
        intLen = (int)len;
        Vector rotatedVector = dir.mult(intLen);
        eX = oriX + rotatedVector.getX();
        eY = oriY + rotatedVector.getY();
        Line line = new Line();
        line.setStartX(this.pos.getX());
        line.setStartY(this.pos.getY());
        line.setEndX(eX);
        line.setEndY(eY);
        this.pos.setX(eX);
        this.pos.setY(eY);
        graphics.addNode(line);
    }
}
