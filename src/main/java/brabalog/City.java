package brabalog;

import graph.Vertex;

public class City extends Vertex {

    private static int idCounter = 0;
    private int id;
    Position pos;

    public City () {
        super("Empty city");
        this.pos = new Position(0, 0);
    }

    public City(String name, int x, int y) {
        super(name);
        this.id = idCounter++;
        this.pos = new Position(x,y);

    }

    public Position getPosition() {
        return this.pos;
    }

    public float calculateDistance (Position pos) {

        int minusX = (this.pos.getX() - pos.getX()) * (this.pos.getX() - pos.getX());
        System.out.println(this.pos.getX() + " - " + pos.getX() + "ˆ2" + " = " + minusX);

        int minusY = (this.pos.getY() - pos.getY()) * (this.pos.getY() - pos.getY());
        System.out.println(this.pos.getY() + " - " + pos.getY() + "ˆ2" + " = " + minusY);

        float distance = (float) Math.sqrt(minusX + minusY);

        return distance;
    }

    @Override
    public String toString()
    {
        return "City{" +
                "\n\tID= '" + id + '\'' +
                "\n\tname= '" + getName() + '\'' +
                "\n\tposition= '" + pos.toString() + '\'' +
                "\n}\n";
    }
}
