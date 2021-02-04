package websocket;

import spark.ModelAndView;
import spark.Spark;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

import java.util.HashMap;

/**
 * The Main of the @see EchoWebSocket
 */
public class Main {

    /**
     * This is the main
     * @param args manual input
     */
    public static void main(String[] args) {
        Spark.webSocket("/websocket/echo", EchoWebSocket.class);

        Spark.get("/", (request, response) -> "Hello World");

        Spark.get("/echo", (request, response) -> {
            HashMap<String, Object> model = new HashMap<>();

            return new ThymeleafTemplateEngine().render(new ModelAndView(model,"echoview"));
        }) ;

        Spark.init(); // Needed if you don't define any HTTP routes after your WebSocket routes
    }
}