import java.util.LinkedList;
import java.util.List;

public class Labyrinth {
    private Step[][] tabStep;

    public Labyrinth(Step[][] tabStep) {
        this.tabStep = tabStep;
    }

    public List getNext(Step position) {

        List<Step> listNext = new LinkedList();
        //can go up
        if (!(tabStep[position.getX()][position.getY() + 1].isMarked()))
        {
            listNext.add(tabStep[position.getX()][position.getY() - 1]);
        }
        //can go down
        if (!(tabStep[position.getX()][position.getY() - 1].isMarked()))
        {
            listNext.add(tabStep[position.getX()][position.getY() - 1]);
        }
        //can go right
        if (!(tabStep[position.getX()+1][position.getY()].isMarked()))
        {
            listNext.add(tabStep[position.getX()][position.getY() - 1]);
        }
        //can go left
        if (!(tabStep[position.getX()-1][position.getY()].isMarked()))
        {
            listNext.add(tabStep[position.getX()][position.getY() - 1]);
        }

        return listNext;
    }
}
