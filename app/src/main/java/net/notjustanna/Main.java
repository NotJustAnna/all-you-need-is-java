package net.notjustanna;

import com.linecorp.armeria.common.HttpResponse;
import com.linecorp.armeria.server.Server;
import com.linecorp.armeria.server.file.FileService;
import dev.webview.Webview;

import java.util.Random;

public final class Main {
    public static void main(String[] args) {
        var port = new Random().nextInt(49152,65535);
        var builder = Server.builder();

        builder.http(port);
        builder.service("/greet", (ctx, req) -> HttpResponse.of("Hello from Armeria!"));
        builder.serviceUnder("/", FileService.of(ClassLoader.getSystemClassLoader(), "/net/notjustanna/ui"));

        var server = builder.build();
        server.start().join();

        Webview view = new Webview(true);

        view.setSize(960, 600);
        view.setTitle("WebView Front-end");
        view.loadURL("http://localhost:" + port + "/");

        view.run();
        view.close();
        server.stop().join();
    }
}
