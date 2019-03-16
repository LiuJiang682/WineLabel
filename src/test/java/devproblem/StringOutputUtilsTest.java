package devproblem;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.io.PrintStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

public class StringOutputUtilsTest {

	@Test
	public void shouldPrint() {
		//Given
		List<Entry<BigDecimal, Integer>> list = getTestList();
		PrintStream mockPrintStream = Mockito.mock(PrintStream.class);
		//When
		StringOutputUtils.printListEntryPerLine(list, mockPrintStream, "%2.0f%% %d");
		//Then
		ArgumentCaptor<String> stringCaptor = ArgumentCaptor.forClass(String.class);
		verify(mockPrintStream, times(2)).println(stringCaptor.capture());
		List<String> lines = stringCaptor.getAllValues();
		assertThat(lines.size(), is(equalTo(2)));
		assertThat(lines.get(0), is(equalTo("90% 5")));
		assertThat(lines.get(1), is(equalTo("10% 6")));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void shouldRaiseExceptionWhenListIsNull() {
		//Given
		List<Entry<String, Long>> list = null;
		//When
		StringOutputUtils.printListEntryPerLine(list, System.out, "%s%% %d");
		fail("Program reached unexpected point!");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void shouldRaiseExceptionWhenPrintStreamIsNull() {
		//Given
		List<Entry<BigDecimal, Integer>> list = getTestList();
		PrintStream outStream = null;
		//When
		StringOutputUtils.printListEntryPerLine(list, outStream, "%s%% %d");
		fail("Program reached unexpected point!");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void shouldRaiseExceptionWhenFormatIsNull() {
		//Given
		List<Entry<BigDecimal, Integer>> list = getTestList();
		PrintStream outStream = System.out;
		String format = null;
		//When
		StringOutputUtils.printListEntryPerLine(list, outStream, format);
		fail("Program reached unexpected point!");
	}
	
	private List<Entry<BigDecimal, Integer>> getTestList() {
		Map<BigDecimal, Integer> map = new HashMap<>();
		map.put(new BigDecimal(90), 5);
		map.put(new BigDecimal(10), 6);
		List<Entry<BigDecimal, Integer>> list = new ArrayList<>(map.entrySet());
		return list;
	}
}
