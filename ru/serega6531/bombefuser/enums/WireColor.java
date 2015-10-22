package ru.serega6531.bombefuser.enums;

public enum WireColor {

    WHITE, BLUE, RED, YELLOW, BLACK;

    public static WireColor byName(String name){
        for(WireColor wire : values())
            if(wire.toString().equalsIgnoreCase(name))
                return wire;

        return null;
    }

}
