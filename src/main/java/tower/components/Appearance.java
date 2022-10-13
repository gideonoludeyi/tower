package tower.components;

import tower.core.Component;

import java.util.Formattable;
import java.util.Formatter;

public record Appearance(String s) implements Component, Formattable {
    @Override
    public void formatTo(Formatter formatter, int flags, int width, int precision) {
        formatter.format("%s", s);
    }
}
