package ru.serega6531.bombefuser;

import ru.serega6531.bombefuser.enums.IndicatorLabel;

public class Indicator {

    private IndicatorLabel label;
    private boolean light;

    public Indicator(IndicatorLabel label, boolean light) {
        this.label = label;
        this.light = light;
    }

    public IndicatorLabel getLabel() {
        return label;
    }

    public boolean isLight() {
        return light;
    }
}
