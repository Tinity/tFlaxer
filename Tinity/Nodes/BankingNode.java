package Tinity.Nodes;

import Tinity.MyFirstNodeScript;
import Tinity.Node;
import org.rspeer.runetek.api.component.Bank;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.runetek.api.scene.SceneObjects;

import static Tinity.MyFirstNodeScript.*;


public class BankingNode extends Node {


    public BankingNode(MyFirstNodeScript main) {
        super(main);
    }

    @Override
    public boolean validate() {
        return BANK_AREA.contains(Players.getLocal().getPosition()) && Inventory.containsAll("Bow string") || Inventory.getCount() == 0;
    }

    @Override
    public int execute() {
        if (BANK_BOOTH_AREA.contains(Players.getLocal().getPosition())) {
            if (Bank.isOpen()) {
                if (Inventory.getCount() == 0) {
                    if (Bank.withdrawAll("Flax")) {
                        if (Inventory.contains("Flax") && Inventory.isFull()) {
                            Bank.close();
                        }
                    }
                } else Bank.depositInventory();
            } else
                SceneObjects.getNearest("Bank booth").interact("Bank");
        } else Movement.walkTo(BANK_BOOTH_POSITION);

        return 1000;
    }
}
