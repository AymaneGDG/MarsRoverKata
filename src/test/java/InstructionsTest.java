import model.Direction;
import model.Obstacle;
import model.Point;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class InstructionsTest {

    private Instructions instructions;
    private Point x;
    private Point y;
    private List<Obstacle> obstacles;
    private final Direction direction = Direction.NORTH;

    @BeforeEach
    public void setUp() {
        x = new Point(1, 50);
        y = new Point(2, 50);
        obstacles = Arrays.asList(new Obstacle(10, 10), new Obstacle(20, 20));
        instructions = new Instructions(x, y, direction, obstacles, false);
    }

    @Test
    void should_set_up_new_instance_with_right_values() {
        assertThat(instructions.getXPosition()).isEqualTo(x);
        assertThat(instructions.getYPosition()).isEqualTo(y);
        assertThat(instructions.getDirection()).isEqualTo(direction);
        assertThat(instructions.getObstacles()).hasSameElementsAs(obstacles);
    }

    @Test
    void should_increase_y_when_direction_is_north_and_the_move_is_forward() {
        Point expectedY = new Point(y.getLocation() + 1, y.getLimitLocation());
        instructions.setDirection(Direction.NORTH);
        instructions.moveForward();
        assertThat(instructions.getYPosition()).usingRecursiveComparison().isEqualTo(expectedY);
    }

    @Test
    void should_increase_x_when_direction_is_east_and_the_move_is_forward() {
        Point expectedX = new Point(x.getLocation() + 1, x.getLimitLocation());
        instructions.setDirection(Direction.EAST);
        instructions.moveForward();
        assertThat(instructions.getXPosition()).usingRecursiveComparison().isEqualTo(expectedX);
    }

    @Test
    void should_decrease_y_when_direction_is_south_and_the_move_is_forward() {
        Point expectedY = new Point(y.getLocation() - 1, y.getLimitLocation());
        instructions.setDirection(Direction.SOUTH);
        instructions.moveForward();
        assertThat(instructions.getYPosition()).usingRecursiveComparison().isEqualTo(expectedY);
    }

    @Test
    void should_decrease_x_when_direction_is_west_and_the_move_is_forward() {
        Point expectedX = new Point(x.getLocation() - 1, x.getLimitLocation());
        instructions.setDirection(Direction.WEST);
        instructions.moveForward();
        assertThat(instructions.getXPosition()).usingRecursiveComparison().isEqualTo(expectedX);
    }

    @Test
    void should_decrease_y_when_direction_is_north_and_the_move_is_backward() {
        Point expectedY = new Point(y.getLocation() - 1, y.getLimitLocation());
        instructions.setDirection(Direction.NORTH);
        instructions.moveBackward();
        assertThat(instructions.getYPosition()).usingRecursiveComparison().isEqualTo(expectedY);
    }

    @Test
    void should_decrease_x_when_direction_is_east_and_the_move_is_backward() {
        Point expectedX = new Point(x.getLocation() - 1, x.getLimitLocation());
        instructions.setDirection(Direction.EAST);
        instructions.moveBackward();
        assertThat(instructions.getXPosition()).usingRecursiveComparison().isEqualTo(expectedX);
    }

    @Test
    void should_increase_y_when_direction_is_south_and_the_move_is_backward() {
        Point expectedY = new Point(y.getLocation() + 1, y.getLimitLocation());
        instructions.setDirection(Direction.SOUTH);
        instructions.moveBackward();
        assertThat(instructions.getYPosition()).usingRecursiveComparison().isEqualTo(expectedY);
    }

    @Test
    void should_increase_x_when_direction_is_west_and_the_move_is_backward() {
        Point expected = new Point(x.getLocation() + 1, x.getLimitLocation());
        instructions.setDirection(Direction.WEST);
        instructions.moveBackward();
        assertThat(instructions.getXPosition()).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    void should_not_change_position_when_obstacle_found() {
        int expected = x.getLocation();
        instructions.setDirection(Direction.EAST);
        instructions.setObstacles(Arrays.asList(new Obstacle(x.getLocation() + 1, y.getLocation())));
        instructions.move(instructions.getDirection());
        assertThat(instructions.getXPosition().getLocation()).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    void should_print_current_position() {
        String expectedPosition = x.getLocation() + " X " + y.getLocation() + " " + direction.getShortName() + " ";
        assertThat(instructions.printPosition()).isEqualTo(expectedPosition);
    }

}
