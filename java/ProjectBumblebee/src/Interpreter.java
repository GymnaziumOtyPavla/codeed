
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author thomasfurst99
 */
public class Interpreter {
    
    private static final String HOME = "HOME";
    private static final String COLOR = "COLOR";
    private static final String TURNLEFT = "TURNLEFT";
    private static final String TURNRIGHT = "TURNRIGHT";
    private static final String FORWARD = "FORWARD";
    
    private Head head;
    
    public Interpreter(Graphics g)
    {
        head = new Head(g);
    }
    
    public void runScript(String fn) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(fn));
            
            String line;
            while ((line = br.readLine()) != null) {
                process(line);
            }
            
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public void process(String line) {
        String[] split = line.split(" ");
        
        if (split.length == 0) return;
        
        switch (split[0]) {
            case HOME:
                head.home();
                break;
            case COLOR:
                processColor(split);
                break;
            case TURNLEFT:
                processTurn(split, false);
                break;
            case TURNRIGHT:
                processTurn(split, true);
                break;
            case FORWARD:
                processForward(split);
                break;
        }
    }
    
    private void processColor(String... args) {
        if (args.length < 4) return;
        
        try {
            double[] lvls = new double[3];
            for (int i = 1; i < 4; i++) {
                lvls[i - 1] = Double.parseDouble(args[i]);
            }
            
            head.color(lvls[0], lvls[1], lvls[2]);
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
        }
    }
    
    private void processTurn(String[] args, boolean right) {
        if (args.length < 2) return;
        
        try {
            double deg = Double.parseDouble(args[1]);
            if (right) head.turnRight(deg);
            else head.turnLeft(deg);
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
        }
    }
    
    private void processForward(String... args) {
        if (args.length < 2) return;
        
        try {
            double len = Double.parseDouble(args[1]);
            head.forward(len);
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
        }
    }
    
}