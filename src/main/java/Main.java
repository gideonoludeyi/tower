import tower.components.*;
import tower.core.Engine;
import tower.core.EntityManager;
import tower.core.System;
import tower.systems.Battle;
import tower.systems.Renderer;

import java.util.ArrayList;
import java.util.List;

public class Main {

    Main() {
        var playerId = "gid://Player/0";
        EntityManager mgr = new EntityManager() {{
            addComponent(playerId, new Appearance("Player"));
            addComponent(playerId, new Vitality(100)); // health is a derivative of vitality and damage; max health is vitality
            addComponent(playerId, new Damage(0));
            addComponent(playerId, new Strength(10));
            addComponent(playerId, new Attack("gid://Fighter/0"));
            addComponent("gid://Fighter/0", new Vitality(25));
            addComponent("gid://Fighter/0", new Damage(0));
            addComponent(playerId, new Attack("gid://Fighter/0"));
        }};

        List<System> systems = new ArrayList<>() {{
            add(new Renderer());
            add(new Battle());
        }};

        Engine engine = new Engine(mgr, systems);

        engine.update();
    }

    public static void main(String[] args) {
        new Main();
    }
}
