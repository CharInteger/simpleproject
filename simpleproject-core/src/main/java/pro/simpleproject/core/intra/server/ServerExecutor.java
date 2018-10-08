package pro.simpleproject.core.intra.server;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerExecutor {

	private static ExecutorService executor = Executors.newSingleThreadExecutor();

	private static Server server;

	public static void run(int port) {
		if (server == null) {
			executor.execute(() -> {
				try {
					server = new Server(port);
					server.run();
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
		}
	}

	public static void close() {
		if (server != null) {
			server.close();
		}
		executor.shutdown();
	}

}
