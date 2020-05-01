package creatures;

import huglife.Action;
import huglife.Creature;
import huglife.Direction;
import huglife.Occupant;

import java.awt.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

import static huglife.HugLifeUtils.randomEntry;

public class Clorus extends Creature {
    /**
     * red color.
     */
    private int r;
    /**
     * green color.
     */
    private int g;
    /**
     * blue color.
     */
    private int b;
    /**
     * name
     */
    private String name = "clorus";

    public Clorus (double e) {
        super("clorus");
        r = 0;
        g = 0;
        b = 0;
        energy = e;
    }



    @Override
    public void move() {
        energy -= 0.03;
    }

    @Override
    public void attack(Creature c) {
        energy += c.energy();
    }

    @Override
    public Clorus replicate() {
        energy = energy/2;
        Clorus offSpring = new Clorus(energy);
        return offSpring;
    }

    @Override
    public void stay() {
        energy -= 0.03;
    }

    @Override
    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        Deque<Direction> emptyNeighbors = new ArrayDeque<>();
        Deque<Direction> plipNeighbors = new ArrayDeque<>();
        boolean anyPlips = false;
        for (Map.Entry<Direction, Occupant> space: neighbors.entrySet()) {
            if (space.getValue().name().equals("empty")) {
                emptyNeighbors.add(space.getKey());
            }
            if (space.getValue().name().equals("plip")) {
                anyPlips = true;
                plipNeighbors.add(space.getKey());
            }
        }

        //RULE1
        if (emptyNeighbors.isEmpty()) {
            return new Action(Action.ActionType.STAY);
        }

        //RULE2
        if (anyPlips) {
            Direction avaiSpace = randomEntry(plipNeighbors);
            return new Action(Action.ActionType.ATTACK, avaiSpace);
        }

        //RULE3
        if (energy >= 1.0) {
            Direction avaiSpace = randomEntry(emptyNeighbors);
            return new Action(Action.ActionType.REPLICATE, avaiSpace);
        }

        //RULE4
        Direction avaiSpace = randomEntry(emptyNeighbors);
        return new Action(Action.ActionType.MOVE, avaiSpace);
    }

    @Override
    public Color color() {
        r = 34;
        g = 0;
        b = 231;
        return color(r, g, b);
    }
}
