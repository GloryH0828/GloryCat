/**
 * @author GloryH
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Server is starting...");
        HttpServer httpServer = new HttpServer();
        httpServer.receiving();
    }
}
