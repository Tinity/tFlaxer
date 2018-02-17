package Tinity.Nodes;

import Tinity.MyFirstNodeScript;
import Tinity.Node;
import org.rspeer.runetek.adapter.scene.SceneObject;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.runetek.api.scene.SceneObjects;

import java.util.function.Predicate;

import static Tinity.MyFirstNodeScript.BANK_AREA;


public class BankToSpinNode extends Node {

    static final Predicate<SceneObject> GATE_PREDICATE = (object) -> object.getName().contains("Staircase") && object.containsAction("Climb-down");


    public BankToSpinNode(MyFirstNodeScript main) {
        super(main);
    }

    @Override
    public boolean validate() {
        // return Inventory.getCount(x -> true) <= 27;
        return Inventory.contains("Flax") && BANK_AREA.contains(Players.getLocal().getPosition());
    }

    @Override
    public int execute() {
        SceneObject STAIRCASE = SceneObjects.getNearest(GATE_PREDICATE);
        if (STAIRCASE != null && STAIRCASE.interact("Climb-down")) {
            Time.sleep(200, 300);
        }


        return 1000;
    }
}
