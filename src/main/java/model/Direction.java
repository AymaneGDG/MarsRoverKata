package model;

import lombok.Getter;

@Getter
public enum Direction {

    NORTH(0, 'N'),
    EAST(1, 'E'),
    SOUTH(2, 'S'),
    WEST(3, 'W');

    private int value;
    private char shortName;

    private Direction(int newValue, char shortNameValue) {
        value = newValue;
        shortName = shortNameValue;
    }

    public Direction getBackwardDirection() {
        return values()[(this.getValue() + 2) % 4];
    }

}
