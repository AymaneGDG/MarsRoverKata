package model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class Point {

    int location;
    int limitLocation;

    public int getForwardLocation() {
        return (getLocation() + 1) % (getLimitLocation() + 1);
    }

    public int getBackwardLocation() {
        return getLocation() > 0 ? getLocation() - 1 : getLimitLocation();
    }
}
