import graph.*;
import brabalog.City;
import brabalog.Position;

import java.util.Scanner;

public class BrabaLog {

    private static GraphController graphController;

    public static void main(String[] args) {

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
        for (int i = 0; i < numberOfCities; i++) {

            String cityName = "Cidade " + i;

            String[] positions = stdin.nextLine().split(",");
            int x1 = Integer.parseInt(positions[0]);
            int y1 = Integer.parseInt(positions[1]);

            graphController.vertices.add(new City(cityName, x1, y1));
        }

        graphController.g = new DigraphMatrix(graphController.vertices);

        // Solicita a entrada do numero de avenidas.
        //
        //
//        System.out.println("Number of highways: ");
        int numberOfHighways = Integer.parseInt(stdin.nextLine());

        // Inicializa as avenidas
        //
        //
//        System.out.println("Enter all of the " + numberOfHighways + " highways: ");
        for (int i = 0; i < numberOfHighways; i++) {

            String[] stringAux1 = stdin.nextLine().split(","); // str1[0](x1): 0, str1[1]: 0:4, str1[2]: 3(y2)
            String[] stringAux2 = stringAux1[1].split(":"); // str2[0](y1): 0, str2[1](x2): 0:4,

            int x1 = Integer.parseInt(stringAux1[0]);
            int y1 = Integer.parseInt(stringAux2[0]);
            int x2 = Integer.parseInt(stringAux2[1]);
            int y2 = Integer.parseInt(stringAux1[2]);

            Position source = new Position(x1, y1);
            Position destination = new Position(x2, y2);

            int minusX = (x1 - x2) * (x1 - x2);
            int minusY = (y1 - y2) * (y1 - y2);

            float weight = (float) Math.sqrt(minusX + minusY); // Distância euclidiana

            if (cityIndexByPosition(source) != -1) {
                if (cityIndexByPosition(destination) != -1) {
                    graphController.g.addEdge(
                            graphController.g.getVertices().get(cityIndexByPosition(source)),
                            graphController.g.getVertices().get(cityIndexByPosition(destination)),
                            weight
                    );
                }
            }
        }

        graphController.traversalStrategy = new FloydWarshallTraversal(graphController.g);

        // Traverse graph
        //
        //
        graphController.traversalStrategy.traverseGraph(graphController.g.getVertices().get(0));

        System.out.println("\n");

//        System.out.println(graphController.g.toString());

        City mostCentralCity = new City();
        City mostPeriphericalCity = new City();
        City mostDistanceFromMostPeripherical = new City();

        // Most center vertex
        //
        //
        if (graphController.traversalStrategy instanceof FloydWarshallTraversal) {
            mostCentralCity = (City) graphController.g.getCenterMostVertex(((FloydWarshallTraversal) graphController.traversalStrategy).getDistanceMatrix());
        }
        
        // Most peripherical vertex 
        //
        //
        if (graphController.traversalStrategy instanceof FloydWarshallTraversal) {
            mostPeriphericalCity = (City) graphController.g.getPerifericalMostVertex(((FloydWarshallTraversal) graphController.traversalStrategy).getDistanceMatrix());
        }

        // Most peripherical vertex
        //
        //
        if (graphController.traversalStrategy instanceof FloydWarshallTraversal) {
            mostDistanceFromMostPeripherical = (City) graphController.g.getMostDistanceFromPerifericalMostVertex(((FloydWarshallTraversal) graphController.traversalStrategy).getDistanceMatrix(), cityIndexByPosition(mostPeriphericalCity.getPosition()));
        }

            
        System.out.println("\n");
        System.out.println(mostCentralCity.getPosition().toString());
        System.out.println(mostPeriphericalCity.getPosition().toString());
        System.out.println(mostDistanceFromMostPeripherical.getPosition().toString());

        stdin.close();
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
