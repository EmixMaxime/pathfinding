public class TestBFS {
    public static void main(String[] args) {
        //Create my tab of steps
        Step [][] tabStep = new Step [4][4];

        //Create all my steps
        for(int abscissa = 0;abscissa <5; abscissa++)
        {
            for(int ordinate = 0;ordinate <5; ordinate++)
            {
                Step myStep = new Step(abscissa, ordinate);
                tabStep[abscissa][ordinate] = myStep;
            }
        }

        //Create my labyrinth
        Labyrinth myLabyrinth = new Labyrinth(tabStep);

        //BreachFirstSearch
        BreadthFirstSearch.BFS(myLabyrinth);
    }
}
