import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import model.Direction;
import model.Obstacle;
import model.Point;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
public class Instructions {

    Point xPosition;
    Point yPosition;
    Direction direction;
    List<Obstacle> obstacles;
    boolean hasObstacle = false;

    public boolean move(Direction direction) {
        int x = xPosition.getLocation();
        int y = yPosition.getLocation();

        switch (direction) {
            case NORTH -> y = yPosition.getForwardLocation();
            case EAST -> x = xPosition.getForwardLocation();
            case SOUTH -> y = yPosition.getBackwardLocation();
            case WEST -> x = xPosition.getBackwardLocation();
        }
        if (!isObstacleExist(x, y)) {
            xPosition.setLocation(x);
            yPosition.setLocation(y);
            return true;
        } else {
            return false;
        }
    }

    public boolean moveForward() {
        return move(direction);
    }

    public boolean moveBackward() {
        return move(direction.getBackwardDirection());
    }

    public void changeDirectionRight() {
        changeDirection(direction, 1);
    }

    public void changeDirectionLeft() {
        changeDirection(direction, -1);
    }

    private boolean isObstacleExist(int x, int y) {
        for (Obstacle obstacle : obstacles) {
            if (obstacle.getX() == x && obstacle.getY() == y) {
                hasObstacle = true;
                return true;
            }
        }
        return false;
    }

    private void changeDirection(Direction directionValue, int directionStep) {
        int directions = Direction.values().length;
        int index = (directions + directionValue.getValue() + directionStep) % directions;
        direction = Direction.values()[index];
    }

    public String printPosition() {
        String status = isHasObstacle() ? "Blocked" : "";
        StringBuilder s = new StringBuilder();
        s.append(getXPosition().getLocation())
                .append(" X " + getYPosition().getLocation()).append(" ").append(getDirection().getShortName()).append(" ").append(status);
        return s.toString();
    }

}
