import graph.*;
import brabalog.City;
import brabalog.Position;

import java.util.Scanner;

public class BrabaLog {

    private static GraphController graphController;

    public static void main(String[] args)
    {

        // Inicialização do Scanner que será utilizado para ler a
        // entrada padrão.
        //
        //
        Scanner stdin = new Scanner(System.in);

        // Instanciação do controlador do gráfo, bem como a estratégia de travessia.
        //
        //
        graphController = new GraphController();

        // Solicita a entrada do numero de cidades.
        //
        //
//        System.out.println("Number of cities: ");
        int numberOfCities = Integer.parseInt(stdin.nextLine());

        // Inicializa as cidades
        //
        //
//        System.out.println("Enter all of the " + numberOfCities + " cities: ");
        for(int i = 0 ; i < numberOfCities ; i++){
            String[] positions = stdin.nextLine().split(",");
            String cityName = "Cidade " + i;
            graphController.vertices.add(new City(cityName, Integer.parseInt(positions[0]), Integer.parseInt(positions[1])));
        }

        graphController.g = new DigraphMatrix(graphController.vertices);
        graphController.traversalStrategy = new FloydWarshallTraversal(graphController.g);

        // Solicita a entrada do numero de avenidas.
        //
        //
//        System.out.println("Number of highways: ");
        int numberOfHighways = Integer.parseInt(stdin.nextLine());

        // Inicializa as avenidas
        //
        //
//        System.out.println("Enter all of the " + numberOfHighways + " highways: ");
        for(int i = 0 ; i < numberOfHighways ; i++){

            String[] stringAux1 = stdin.nextLine().split(",");
            String[] stringAux2 = stringAux1[1].split(":");

            Position source = new Position(Integer.parseInt(stringAux1[0]), Integer.parseInt(stringAux2[0]));
            Position destination = new Position(Integer.parseInt(stringAux2[1]), Integer.parseInt(stringAux1[2]));

            if (cityIndexByPosition(source) != -1) {
                if (cityIndexByPosition(destination) != -1) {
                    graphController.g.addEdge(
                            graphController.g.getVertices().get(cityIndexByPosition(source)),
                            graphController.g.getVertices().get(cityIndexByPosition(destination))
                    );
                }
            }
        }

        System.out.println(graphController.g.getVertices());
        System.out.println(graphController.g.toString());

        // Traverse graph
        //
        //
        graphController.traversalStrategy.traverseGraph(
                graphController.g.getVertices().get(0)
        );

        // Most center vertex
        //
        //
        if (graphController.traversalStrategy instanceof FloydWarshallTraversal) {
            City city = (City)graphController.g.getCentermostVertex(((FloydWarshallTraversal)graphController.traversalStrategy).getDistanceMatrix());
            System.out.println(city.getPosition().toString());
        }

        // Most peripherical vertex
        //
        //


    }

    private static int cityIndexByPosition(Position pos) {
        for (int i = 0; i < graphController.g.getVertices().size(); i++) {
            City city = (City)graphController.g.getVertices().get(i);
            if (city.getPosition().getX() == pos.getX()){
                if (city.getPosition().getY() == pos.getY()) {
                    return i;
                }
            }
        }
        return -1;
    }
}
