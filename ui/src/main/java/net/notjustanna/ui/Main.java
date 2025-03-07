package net.notjustanna.ui;

import org.teavm.jso.ajax.XMLHttpRequest;
import org.teavm.jso.browser.Window;
import org.teavm.jso.dom.html.HTMLDocument;

public class Main {
    public static void main(String[] args) {
        var document = HTMLDocument.current();
        var div = document.createElement("div");
        div.appendChild(document.createTextNode("TeaVM generated element"));
        document.getBody().appendChild(div);
        System.out.println("Hello from TeaVM!");

        String host = Window.current().getLocation().getHost();

        var xhr = new XMLHttpRequest();
        xhr.addEventListener("load", evt -> System.out.println("Response: " + xhr.getResponseText()));
        xhr.open("GET", "http://" + host + "/greet/Joe");
        xhr.send();
    }
}
