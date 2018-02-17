package Tinity.Nodes;

import Tinity.MyFirstNodeScript;
import Tinity.Node;
import org.rspeer.runetek.adapter.scene.SceneObject;
import org.rspeer.runetek.api.component.Interfaces;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.movement.position.Position;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.runetek.api.scene.SceneObjects;

import java.sql.Time;


import static Tinity.MyFirstNodeScript.SPINNING_WHEEL_AREA;
import static Tinity.MyFirstNodeScript.SPIN_AREA;

public class SpinNode extends Node {
    private Position SPIN_POSITION = new Position(3209, 3213);


    public SpinNode(MyFirstNodeScript main) {
        super(main);
    }

    @Override
    public boolean validate() {
        return SPIN_AREA.contains(Players.getLocal().getPosition()) && Inventory.contains("Flax");
    }

    @Override
    public int execute() {

        if (SPINNING_WHEEL_AREA.contains(Players.getLocal().getPosition())) {
            if (Interfaces.isOpen(459)) {
                if (Interfaces.getComponent(459, 4).interact("Spin-All")) {
                    org.rspeer.runetek.api.commons.Time.sleepUntil(() -> !Inventory.contains("Flax") || Interfaces.canContinue() && Interfaces.processContinue(), 5000, 6000);
                }
            } else SceneObjects.getNearest("Spinning wheel").interact("Spin");
        } else Movement.walkTo(SPIN_POSITION);

        return 1000;
    }
}
