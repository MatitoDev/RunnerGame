import greenfoot.core.Simulation;
import greenfoot.export.GreenfootScenarioViewer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;

//nur Util damit ich es besser in InteliJ starten kann
public class RunnerGameLauncher extends Application {
    @Override
    public void start(Stage stage) {
        Platform.setImplicitExit(true);

        GreenfootScenarioViewer.initProperties();
        GreenfootScenarioViewer viewer = new GreenfootScenarioViewer();
        Scene scene = new Scene(viewer);

        stage.setScene(scene);
        stage.show();

        stage.setOnHiding(e -> {
            Simulation.getInstance().abort();

            Thread exiter = new Thread(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ignored) {
                }
                System.exit(0);
            });

            exiter.setDaemon(true);
            exiter.start();
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}