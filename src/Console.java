import java.util.Calendar;
import java.util.TimeZone;

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
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("America/Sao_Paulo"));
		String date = cal.get(Calendar.DAY_OF_MONTH) + "/" + (cal.get(Calendar.MONTH) + 1) + "/"
				+ cal.get(Calendar.YEAR) + " " + cal.get(Calendar.HOUR_OF_DAY) + ":" + cal.get(Calendar.MINUTE) + ":"
				+ cal.get(Calendar.SECOND);
		return date;
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
