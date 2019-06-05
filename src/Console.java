import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Console {
	private static String console = "";
	private static boolean debug = false;
	private static MainController mc;

	public static void log(String text) {
		String newText = text;
		console = "[" + formattedDate() + "] " + newText + "\n" + console;
		mc.updateConsole(console);
	}

	public static void debug(String text) {
		if (!debug)
			return;
		String newText = text;
		console = "[" + formattedDate() + "] [DEBUG] " + newText + "\n" + console;
		mc.updateConsole(console);
	}

	public static String formattedDate() {
		LocalDateTime ldt = LocalDateTime.now();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		return dtf.format(ldt);
	}

	public static void setDebug(boolean debug) {
		Console.debug = debug;
	}

	public static boolean isDebug() {
		return debug;
	}

	public static void reset() {
		Console.console = "";
	}

	public static void debug(Object obj) {
		Console.debug(obj.toString());
	}

	public static void log(Object obj) {
		Console.log(obj.toString());
	}

	public static void setMainController(MainController controller) {
		mc = controller;
	}
}
