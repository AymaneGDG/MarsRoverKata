import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Rover {

    private Instructions instructions;

    public boolean computeSingleCommand(char command) throws Exception {
        switch(Character.toUpperCase(command)) {
            case 'F':
                return getInstructions().moveForward();
            case 'B':
                return getInstructions().moveBackward();
            case 'L':
                getInstructions().changeDirectionLeft();
                return true;
            case 'R':
                getInstructions().changeDirectionRight();
                return true;
            default:
                throw new Exception("Unknown command : " + command);
        }
    }

    public void computeCommands(String commands) throws Exception {
        for (char command : commands.toCharArray()) {
            if (!computeSingleCommand(command)) {
                break;
            }
        }
    }

    public String getPosition() {
        return getInstructions().printPosition();
    }

}
