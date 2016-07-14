/**
 *
 * @author tomaspavlov
 * @edit OndrejTkaczyszyn
 */
public class Vector {

    private Point point = new Point();

    public Vector() {

    }

    public Vector(double x, double y) {
        this.point.setX(x);
        this.point.setY(y);
    }
    public double getX(){
        return this.point.getX();
    }
    public double getY(){
        return this.point.getY();
    }
    public void rotate(double angle){
        double xs = this.point.getX();
        double ys = this.point.getY();
        double xr = xs*Math.cos(angle) - ys*Math.sin(angle);
        double yr = xs*Math.cos(angle) + ys*Math.sin(angle);
        this.point.setX(xr);
        this.point.setY(yr);
    }
    public Vector add(Vector vector) {
        Vector res = new Vector();
        res.point.setX(point.getX() + vector.point.getX());

        res.point.setY(point.getY() + vector.point.getY());

        return res;
    }

    public Vector mult(double num) {

        Vector res = new Vector();

        res.point.setX(point.getX() * num);
        res.point.setY(point.getY() * num);

        return res;

    }

    public double getLen() {

        return Math.sqrt((point.getX() * point.getX()) / (point.getY() * point.getY()));

    }

    public Vector normalize() {

        double len = getLen();

        Vector res = new Vector();
        res.point.setX(point.getX() / len);
        res.point.setY(point.getY() / len);
        return res;

    }

}
