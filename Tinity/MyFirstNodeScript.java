package Tinity;

import Tinity.Nodes.BankingNode;
import Tinity.Nodes.BankToSpinNode;
import Tinity.Nodes.SpinNode;
import Tinity.Nodes.WalkToBankNode;
import org.rspeer.runetek.adapter.scene.SceneObject;
import org.rspeer.runetek.api.component.tab.Skill;
import org.rspeer.runetek.api.movement.position.Area;
import org.rspeer.runetek.api.movement.position.Position;
import org.rspeer.runetek.api.scene.SceneObjects;
import org.rspeer.script.Script;
import org.rspeer.script.ScriptMeta;

import java.util.function.Predicate;

@ScriptMeta(developer = "Tinity",version = 1.0,name = "tFlaxer",desc = "Spins flax to bowstrings in Lumbridge castle",skill = Skill.AGILITY)
public class MyFirstNodeScript extends Script {
    private Node[] nodes;


    public static final Area BANK_AREA = Area.rectangular(3203, 3230, 3213, 3206, 2);
    public static final Area SPIN_AREA = Area.rectangular(3203, 3230, 3216, 3206, 1);
    public static final Area SPINNING_WHEEL_AREA = Area.rectangular(3211, 3212, 3208, 3214, 1);
    public static final Area BANK_BOOTH_AREA = Area.rectangular(3207, 3220, 3210, 3219, 2);
    public static final Position BANK_BOOTH_POSITION = new Position(3208, 3220, 2);

    @Override
    public void onStart() {
        nodes = new Node[]{
                new BankingNode(this),
                new BankToSpinNode(this),
                new SpinNode(this),
                new WalkToBankNode(this),

        };
    }

    @Override
    public int loop() {
        for (Node node : nodes){
            if (node.validate()){
                return node.execute();
            }
        }
        return 1000;
    }



}
