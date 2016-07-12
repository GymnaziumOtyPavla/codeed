
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class Abakus extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Abakus");

        int radius = 20;
        
        int rows = 8;
        int cols = 17;

        Pane pane = new Pane();

        for (int i = 0; i < rows; i++) {

            for (int j = 0; j < cols; j++) {
                final Circle circle = new Circle();
                circle.setCenterX(2 * j * radius + radius);
                circle.setCenterY(2 * i * radius + radius);
                circle.setRadius(radius);
                
                circle.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    public void handle(MouseEvent mouseEvent) {
                        circle.setCenterX(circle.getCenterX() + 20);
                    }
                });
                
                if (j < cols / 2) {
                    circle.setFill(Color.BLUE);
                } else {
                    circle.setFill(Color.RED);
                }
                pane.getChildren().add(circle);
            }
            
            Line line = new Line();
            line.setStartX(0);
            line.setStartY(2 * i * radius + radius);
            line.setEndX(10000);
            line.setEndY(2 * i * radius + radius);
            line.setStroke(Color.BLACK);
            pane.getChildren().add(line);
        }
        
        

        stage.setScene(new Scene(pane, 900, 350));
        stage.show();
    }

}

