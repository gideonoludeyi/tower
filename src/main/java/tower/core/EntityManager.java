package tower.core;

import tower.util.Pair;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class EntityManager {
    Map<String, Map<Class<? extends Component>, Component>> entities = new HashMap<>();

    @SafeVarargs
    public final Stream<Pair<String, Map<Class<? extends Component>, Component>>> getCompositeEntities(Class<? extends Component>... componentTypes) {
        return entities.entrySet().stream()
            .filter(entry -> entry.getValue().keySet().containsAll(Arrays.stream(componentTypes).toList()))
            .map(stringMapEntry -> new Pair<>(stringMapEntry.getKey(), stringMapEntry.getValue()));
    }

    public void addComponent(String entityId, Component component) {
        var components = entities.getOrDefault(entityId, new HashMap<>());
        components.put(component.getClass(), component);
        entities.put(entityId, components);
    }

    public void removeComponent(String entityId, Class<? extends Component> componentType) {
        var components = entities.get(entityId);
        components.remove(componentType);
    }

    public Map<Class<? extends Component>, Component> getEntity(String entityId) {
        return entities.get(entityId);
    }
}
