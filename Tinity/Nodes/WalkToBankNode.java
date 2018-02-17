package Tinity.Nodes;

import Tinity.MyFirstNodeScript;
import Tinity.Node;
import org.rspeer.runetek.adapter.scene.SceneObject;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.runetek.api.scene.SceneObjects;

import java.util.function.Predicate;

import static Tinity.MyFirstNodeScript.SPIN_AREA;

public class WalkToBankNode extends Node{

    static final Predicate<SceneObject> GATE_PREDICATE = (object) -> object.getName().contains("Staircase") && object.containsAction("Climb-up");


    public WalkToBankNode(MyFirstNodeScript main) {
        super(main);
    }

    @Override
    public boolean validate() {
        return Inventory.containsAll("Bow string") && SPIN_AREA.contains(Players.getLocal().getPosition());
    }

    @Override
    public int execute() {
        SceneObject STAIRCASE = SceneObjects.getNearest(GATE_PREDICATE);
        if (SPIN_AREA.contains(Players.getLocal().getPosition())) {
            STAIRCASE.interact("Climb-up");
        }
        return 1000;
    }
}
