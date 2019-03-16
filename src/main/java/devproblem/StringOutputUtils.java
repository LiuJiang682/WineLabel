package devproblem;

import java.io.PrintStream;
import java.util.List;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;

public class StringOutputUtils {

	public static final <K, V> void printListEntryPerLine(final List<Entry<K, V>> list, PrintStream out, String format) {
		if (null == list) {
			throw new IllegalArgumentException("Parameter list cannot be null!");
		}
		if (null == out) {
			throw new IllegalArgumentException("Parameter out cannot be null!");
		}
		if (StringUtils.isBlank(format)) {
			throw new IllegalArgumentException("Parameter format cannot be null or empty!");
		}
		for (Entry<K, V> elm : list) {
			String string = String.format(format, elm.getKey(), elm.getValue());
			out.println(string);
		}
	}
}
