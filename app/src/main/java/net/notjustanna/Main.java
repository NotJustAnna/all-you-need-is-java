package net.notjustanna;

import io.helidon.config.Config;
import io.helidon.logging.common.LogConfig;
import io.helidon.webserver.WebServer;
import io.helidon.webserver.http.HttpRouting;
import io.helidon.webserver.staticcontent.StaticContentFeature;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public final class Main extends Application {
    public static Config config = Config.create();
    public static WebServer server;

    public static void main(String[] args) {
        LogConfig.configureRuntime();

        server = WebServer.builder()
            .config(config.get("server"))
            .routing(Main::routing)
            .addFeature(
                StaticContentFeature.builder().addClasspath(
                        cl -> cl.location("net/notjustanna/ui")
                            .welcome("index.html")
                            .context("/")
                    )
                    .build()
            )
            .build()
            .start();
        launch(args);
    }

    static void routing(HttpRouting.Builder routing) {
        routing.register("/greet", new GreetService(config));
    }

    public void start(Stage primaryStage) {
        primaryStage.setOnCloseRequest(e -> {
            Platform.exit();
            server.stop();
        });

        primaryStage.setTitle("JavaFX Front-end");

        WebView webView = new WebView();

        var engine = webView.getEngine();

        engine.load("http://localhost:" + server.port() + "/");

        VBox vBox = new VBox(webView);
        VBox.setVgrow(webView, Priority.ALWAYS);
        Scene scene = new Scene(vBox, 960, 600);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
