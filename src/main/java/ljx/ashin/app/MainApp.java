package ljx.ashin.app;

import ljx.ashin.client.Client;
import ljx.ashin.server.Server;

/**
 * Created by Ashin Liang on 2017/9/22.
 */
public class MainApp {
    public static void main(String[] args) {
        Server server = new Server(8765);
        Client client = new Client(8765,"127.0.0.1");

    }
}
