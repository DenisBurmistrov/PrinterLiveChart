package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import sample.service.chart.ChartFillService;
import sample.service.chart.ChartPointService;

import java.util.Optional;


public class Main extends Application {

    public static Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("/view.fxml"));
        primaryStage.getIcons().add(new Image("/line-chart-icon.png"));
        primaryStage.setTitle("Printer Charts");
        primaryStage.setScene(new Scene(root));
        stage = primaryStage;

        stage.setOnCloseRequest(confirmCloseEventHandler);

        Button closeButton = new Button("Close Application");
        closeButton.setOnAction(event ->
                stage.fireEvent(
                        new WindowEvent(
                                stage,
                                WindowEvent.WINDOW_CLOSE_REQUEST
                        )
                )
        );

        StackPane layout = new StackPane(closeButton);
        layout.setPadding(new Insets(10));

        primaryStage.show();
    }

    private EventHandler<WindowEvent> confirmCloseEventHandler = event -> {
        Alert closeConfirmation = new Alert(
                Alert.AlertType.CONFIRMATION,
                "Are you sure you want to exit?"
        );
        Button exitButton = (Button) closeConfirmation.getDialogPane().lookupButton(
                ButtonType.OK
        );
        exitButton.setText("Exit");
        closeConfirmation.setHeaderText("Confirm Exit");
        closeConfirmation.initModality(Modality.APPLICATION_MODAL);
        closeConfirmation.initOwner(stage);

        // normally, you would just use the default alert positioning,
        // but for this simple sample the main stage is small,
        // so explicitly position the alert so that the main window can still be seen.
        closeConfirmation.setX(stage.getX());
        closeConfirmation.setY(stage.getY() + stage.getHeight());

        Optional<ButtonType> closeResponse = closeConfirmation.showAndWait();
        if (!ButtonType.OK.equals(closeResponse.get())) {
            event.consume();
        }
    };

    public static void main(String[] args) {
        ChartFillService.initPatterns();
        ChartPointService.initDimensions();
        launch(args);
    }

}
