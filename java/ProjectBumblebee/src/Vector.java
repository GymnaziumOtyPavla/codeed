/**
 *
 * @author tomaspavlov
 */
public class Vector {

    private Point point;

    public Vector() {

    }

    public Vector(double x, double y) {

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
