/**
 *
 * @author thomasfurst
 */
public class Main {
    public static void main(String... args) {
        Graphics.launch(args);
        
        Interpreter i = new Interpreter(Graphics.instance);
        
        if (args.length > 2) {
            i.runScript(args[1]);
        } else {
            i.runScript("DrawScripts/spiral.ds");
        }
    }
}
