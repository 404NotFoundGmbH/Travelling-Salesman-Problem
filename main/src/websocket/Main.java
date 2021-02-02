package websocket;

import spark.ModelAndView;
import spark.Spark;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
        Spark.webSocket("/websocket/echo", EchoWebSocket.class);

        Spark.get("/", (request, response) -> "Hello World");

        Spark.get("/echo", (request, response) -> {
            HashMap<String, Object> model = new HashMap<>();
g
            return new ThymeleafTemplateEngine().render(new ModelAndView(model,"echoview"));
        }) ;

        Spark.init(); // Needed if you don't define any HTTP routes after your WebSocket routes
    }
}