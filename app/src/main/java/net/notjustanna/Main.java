package net.notjustanna;

import dev.webview.Webview;
import io.helidon.config.Config;
import io.helidon.logging.common.LogConfig;
import io.helidon.webserver.WebServer;
import io.helidon.webserver.http.HttpRouting;
import io.helidon.webserver.staticcontent.StaticContentFeature;

public final class Main {
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

        Webview view = new Webview(true);

        view.setSize(960, 600);
        view.setTitle("WebView Front-end");
        view.loadURL("http://localhost:" + server.port() + "/");

        view.run();
        view.close();
        server.stop();
    }

    static void routing(HttpRouting.Builder routing) {
        routing.register("/greet", new GreetService(config));
    }
}
