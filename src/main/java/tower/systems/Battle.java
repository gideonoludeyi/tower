package tower.systems;

import tower.components.Attack;
import tower.components.Damage;
import tower.components.Strength;
import tower.core.Component;
import tower.core.EntityManager;
import tower.core.System;
import tower.util.Pair;

import java.util.Map;

public class Battle implements System {
    @Override
    public void update(EntityManager manager) {
        manager.getCompositeEntities(Attack.class, Strength.class)
            .map(entity -> Hostile.of(entity.first(), entity.second()))
            .map(hostile -> new Pair<>(hostile, Target.of(hostile.targetId(), manager.getEntity(hostile.targetId()))))
            .forEach(conflict -> {
                var hostile = conflict.first();
                var target = conflict.second();
                manager.addComponent(
                    target.id(),
                    hostile.attack(target)
                );
            });
    }

    private record Hostile(String id, Attack atk, Strength strength) {
        public Damage attack(Target target) {
            return target.inflict(strength);
        }

        public String targetId() {
            return atk.targetId();
        }

        public static Hostile of(String entityId, Map<Class<? extends Component>, Component> components) {
            return new Hostile(entityId, (Attack) components.get(Attack.class), (Strength) components.get(Strength.class));
        }
    }

    private record Target(String id, Damage damage) {
        public Damage inflict(Strength strength) {
            return new Damage(damage.value() + strength.value());
        }

        public static Target of(String entityId, Map<Class<? extends Component>, Component> components) {
            return new Target(entityId, (Damage) components.get(Damage.class));
        }
    }
}
