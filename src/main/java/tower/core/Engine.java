package tower.core;

import java.util.ArrayList;
import java.util.List;

public class Engine {

    private final List<System> systems;
    private final EntityManager manager;

    public Engine() {
        this(new EntityManager(), new ArrayList<>());
    }

    public Engine(EntityManager manager, List<System> systems) {
        this.manager = manager;
        this.systems = systems;
    }

    public void update() {
        for (System system : systems)
            system.update(manager);
    }
}


