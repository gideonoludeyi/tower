package tower.components;

import tower.core.Component;

public record Damage(int value) implements Component {
    public Damage {
        value = Math.max(0, value);
    }
}
