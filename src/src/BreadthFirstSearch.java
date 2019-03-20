import java.util.*;

public class BreadthFirstSearch {
    public static void BFS(Labyrinth labyrinth) {
        Step position = new Step(0,0);
        position.mark();
        Queue<Step> F = new LinkedList<>();

        F.add(position);

        Map trackPath = new HashMap();

        addPredecessor(trackPath, position, null);
        while (F.size()!=0) {
            Step predecessor = position;
            position = F.remove();

            addPredecessor(trackPath, position, predecessor);
            List nextList = labyrinth.getNext(position);
            for (int i = 0; i < nextList.size(); i++) {
                 position = (Step) nextList.remove(i);
                if (!position.isMarked()) {
                    position.mark();
                    System.out.println("Position : " + position);
                    F.add(position);
                }
            }

        }
    }

    public static void addPredecessor(Map map, Step position, Step predecessor)
    {
        map.put(position, predecessor);
    }


}
