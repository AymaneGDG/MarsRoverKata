import model.Direction;
import model.Obstacle;
import model.Point;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RoverTest {

    private Point x;
    private Point y;
    private Instructions instructions;
    private Rover rover;
    private final Direction direction = Direction.NORTH;
    private List<Obstacle> obstacles;

    @BeforeEach
    void setUp() {
        x = new Point(1, 5);
        y = new Point(2, 5);
        obstacles = new ArrayList<Obstacle>();
        instructions = new Instructions(x, y, direction, obstacles, false);
        rover = new Rover(instructions);
    }

    @Test
    void should_compute_commands_stop_when_obstacle_is_found() throws Exception {
        int expectedX = x.getLocation() + 1;
        rover.getInstructions().setObstacles(Arrays.asList(new Obstacle(expectedX + 1, y.getLocation())));
        rover.getInstructions().setDirection(Direction.EAST);
        rover.computeCommands("FFFRF");
        assertThat(rover.getInstructions().getXPosition().getLocation()).isEqualTo(expectedX);
        assertThat(rover.getInstructions().getDirection()).isEqualTo(Direction.EAST);
    }

    @Test
    void should_print_position_and_direction() throws Exception {
        rover.computeCommands("LFFFRFF");
        assertThat(rover.getPosition()).isEqualTo("4 X 4 N ");
    }

    @Test
    void should_print_last_position_with_blocked_when_obstacle_is_found() throws Exception {
        rover.getInstructions().setObstacles(Arrays.asList(new Obstacle(x.getLocation() + 1, y.getLocation())));
        rover.getInstructions().setDirection(Direction.EAST);
        rover.computeCommands("F");
        assertThat(rover.getPosition()).endsWith(" Blocked");
    }

    @Test
    void should_turn_left_when_command_is_L() throws Exception {
        rover.computeSingleCommand('L');
        assertThat(rover.getInstructions().getDirection()).isEqualTo(Direction.WEST);
    }

    @Test
    void  should_move_backward_when_command_is_B() throws Exception {
        int expected = y.getLocation() - 1;
        rover.computeSingleCommand('B');
        assertThat(rover.getInstructions().getYPosition().getLocation()).isEqualTo(expected);
    }

    @Test
    void should_turn_right_when_command_is_R() throws Exception {
        rover.computeSingleCommand('R');
        assertThat(rover.getInstructions().getDirection()).isEqualTo(Direction.EAST);
    }

    @Test
    void should_move_forward_when_command_is_F() throws Exception {
        int expected = y.getLocation() + 1;
        rover.computeSingleCommand('F');
        assertThat(rover.getInstructions().getYPosition().getLocation()).isEqualTo(expected);
    }

    @Test
    void should_throw_exception_when_command_is_unknown() throws Exception {
        Exception exception = assertThrows(Exception.class, () -> {
            rover.computeSingleCommand('X');
        });

        String expectedMessage = "Unknown command : X";
        String actualMessage = exception.getMessage();

        assertThat(actualMessage.contains(expectedMessage));

    }

    @Test
    void should_compute_single_command_lower_case() throws Exception {
        rover.computeSingleCommand('r');
        assertThat(rover.getInstructions().getDirection()).isEqualTo(Direction.EAST);
    }

    @Test
    void should_compute_multiple_commands() throws Exception {
        int expectedX = x.getLocation() + 1;
        rover.computeCommands("RFR");
        assertThat(rover.getInstructions().getXPosition().getLocation()).isEqualTo(expectedX);
        assertThat(rover.getInstructions().getDirection()).isEqualTo(Direction.SOUTH);
    }

}
