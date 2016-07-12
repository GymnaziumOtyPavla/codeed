import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class Demo extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Abakus");
        
        GridPane grid = new GridPane();
        
        Circle circle = new Circle();
        circle.setCenterX(20);
        circle.setCenterY(20);
        circle.setRadius(20);
        circle.setFill(Color.SADDLEBROWN);
        
        Line line = new Line();
        line.setStartX(0);
        line.setStartY(0);
        line.setStartX(200);
        line.setStartY(100);
                
        StackPane root = new StackPane();
        //root.getChildren().add(grid);
        root.getChildren().addAll(circle, line);
        
        stage.setScene(new Scene(root, 700, 300));
        stage.show();
    }
    
}
