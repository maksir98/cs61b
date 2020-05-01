package creatures;

import huglife.*;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class TestClorus {
    @Test
    public void testReplicate() {
        Clorus clorus = new Clorus(2);
        assertEquals(2, clorus.energy(), 0.01);
        Clorus clorus1 = clorus.replicate();
        assertEquals(1.0, clorus.energy(), 0.01);
        assertEquals(1.0, clorus1.energy(), 0.01);
    }

    @Test
    public void testChoose() {
        //RULE1
        Clorus c = new Clorus(1.2);
        HashMap<Direction, Occupant> surrounded = new HashMap<Direction, Occupant>();
        surrounded.put(Direction.TOP, new Impassible());
        surrounded.put(Direction.BOTTOM, new Impassible());
        surrounded.put(Direction.LEFT, new Impassible());
        surrounded.put(Direction.RIGHT, new Impassible());

        Action actual = c.chooseAction(surrounded);
        Action expected = new Action(Action.ActionType.STAY);

        assertEquals(expected, actual);

        //RULE2
        c = new Clorus(1.2);
        Plip p = new Plip();

        HashMap<Direction, Occupant> topEmpty = new HashMap<Direction, Occupant>();
        topEmpty.put(Direction.TOP, new Empty());
        topEmpty.put(Direction.BOTTOM, p);
        topEmpty.put(Direction.LEFT, new Impassible());
        topEmpty.put(Direction.RIGHT, new Impassible());

        actual = c.chooseAction(topEmpty);
        expected = new Action(Action.ActionType.ATTACK, Direction.BOTTOM);
        assertEquals(expected, actual);

        //RULE3


    }
}
