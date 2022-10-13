package tower.components;

import tower.core.Component;

public record Vitality(int value) implements Component {
    public Vitality {
        value = Math.max(0, value);
    }
}
