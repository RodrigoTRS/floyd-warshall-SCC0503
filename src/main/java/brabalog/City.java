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
