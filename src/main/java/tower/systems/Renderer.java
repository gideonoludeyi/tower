package tower.systems;

import tower.components.Appearance;
import tower.core.Component;
import tower.core.EntityManager;
import tower.core.System;

import java.io.PrintStream;
import java.util.Map;

public class Renderer implements System {
    private final PrintStream out;

    public Renderer() {
        this(java.lang.System.out);
    }

    public Renderer(PrintStream out) {
        this.out = out;
    }

    @Override
    public void update(EntityManager manager) {
        manager.getCompositeEntities(Appearance.class)
            .map(pair -> new Composite(pair.first(), pair.second()))
            .forEach(composite ->
                out.printf("%s", composite.appearance()));
    }

    private record Composite(String entityId, Appearance appearance) {
        Composite(String entityId, Map<Class<? extends Component>, ? extends Component> components) {
            this(entityId, (Appearance) components.get(Appearance.class));
        }
    }
}
