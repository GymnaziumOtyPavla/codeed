
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
    private Color color;
    private boolean penPos = true;
    private double penWidth = 1;
    public Head(Graphics g){
        System.out.println("Creating head");
        
        this.initVector();
        graphics = g;
        
        home();
        color(0, 0, 0);
    }
    
    public void home() {
        System.out.println("Home");
        pos.setX(graphics.getWidth()/2);
        pos.setY(graphics.getHeight()/2);
    }
    private void initVector(){
        this.dir = new Vector(0,1);
    }
    public void resetDir(){
        this.dir = new Vector(0,1);
    }
    public void setWidth(double width){
        this.penWidth = width;
    }
    public void color(double r, double g, double b) {
        System.out.println("color");
        color = Color.color(r,g,b); 
    }
    
    public void turnLeft(double deg) {
        System.out.println("Turn left");
        dir.rotate(-deg);
    }
    
    public void turnRight(double deg) {
        System.out.println("Turn right");
        dir.rotate(deg); 
    }
     public void moveTo(double x, double y){
        this.pos.setX(x);
        this.pos.setY(y);
        initVector();    
    }
    
    public void penUp(){
        this.penPos = false;
    }
    public void penDown(){
        this.penPos = true;
    }
    public boolean penIsDown(){
        return this.penPos;
    }
    
    public void forward(double len) {
        System.out.println("Forward");
        Vector path = dir.mult(len);
        
        // We have result!
        Vector res = new Vector(pos.getX(), pos.getY()).add(path);
        if(penPos){
        Line line = new Line();
        line.setStartX(this.pos.getX());
        line.setStartY(this.pos.getY());
        line.setEndX(res.getX());
        line.setEndY(res.getY());
        line.setStroke(color);
        line.setStrokeWidth(penWidth);

        
        graphics.addNode(line);
        }
                
        pos.setX(res.getX());
        pos.setY(res.getY());
    }
}
