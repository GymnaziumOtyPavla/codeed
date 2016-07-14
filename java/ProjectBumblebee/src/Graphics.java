import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author thomasfurst
 */
public class Graphics extends Application {
    
    public static Graphics instance = null;
    
    private static final double delay = 500;
    
    private double currentTime = -1;
    
    /**
     * Set this variable to open your script
     */
    private static String fn;
    
    private static double initWidth;
    private static double initHeight;
    
    private Pane pane;
            
    public static void main(String... args) {
        
        if (args.length == 2) fn = args[1];
        //else fn = "DrawScripts/rect.ds";
        else fn = "demo.jpg";
        
        if (args.length == 3 || args.length == 4) 
        {
            try {
                initWidth = Double.parseDouble(args[1]);
                initHeight = Double.parseDouble(args[2]);
            } catch (NumberFormatException ex) {
                initWidth = 400;
                initHeight = 400;
            }
            
            if (args.length == 4) fn = args[3];
            else fn = "DrawScripts/rect.ds";
        }
        else
        {
            initWidth = 400;
            initHeight = 400;
        }
        
        launch(args);
    }
    
    public Graphics() {
        instance = this;
    }
    
    public static void addNode(Node node) {
        instance._addNode(node);
    }
    
    private void _addNode(Node node) {
        
        pane.getChildren().add(node);
    }
   
    public static double getWidth() {
        return instance._getWidth();
    }
    
    private double _getWidth() {
        return initWidth;
    }
    
    public static double getHeight() {
        return instance._getHeight();
    }
    
    private double _getHeight() {
        return initHeight;
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        instance = this;  
        
        pane = new Pane();
        
        
        
        Interpreter i = new Interpreter(Graphics.instance);
        i.runImage(fn);
        
        stage.setTitle(fn);
        stage.setScene(new Scene(pane, initWidth, initHeight));
        stage.show();
    }
    
}
