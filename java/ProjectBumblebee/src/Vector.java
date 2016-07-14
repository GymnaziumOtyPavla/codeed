/**
 *
 * @author tomaspavlov
 * @edit OndrejTkaczyszyn
 */
public class Vector {

    private Point point = new Point();

    public Vector() {
        point = new Point(0, 0);
    }

    public Vector(double x, double y) {
        point = new Point(x, y);
    }
    public double getX(){
        return point.getX();
    }
    public double getY(){
        return point.getY();
    }
    public void rotate(double angle){
        double x = point.getX() * Math.cos(Math.toRadians(angle)) - point.getY() * Math.sin(Math.toRadians(angle));
        double y = point.getX() * Math.sin(Math.toRadians(angle)) + point.getY() * Math.cos(Math.toRadians(angle));
        
        this.point.setX(x);
        this.point.setY(y);
    }
    public Vector add(Vector vector) {
        return new Vector(point.getX() + vector.point.getX(),
            point.getY() + vector.point.getY());
    }

    public Vector mult(double num) {
        return new Vector(point.getX() * num, point.getY() * num);
    }

    public double getLen() {
        return Math.sqrt((point.getX() * point.getX()) / (point.getY() * point.getY()));
    }

    public Vector normalize() {
        return new Vector(point.getX() / getLen(), point.getY() / getLen());
    }

}
