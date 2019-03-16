package devproblem;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.junit.Test;

public class KeyValueSwapSortMapUtilsTest {
	
	@Test
	public void shouldSwapKeyValueAndSortForIntegerBigDecimal() {
		//Given
		Map<Integer, BigDecimal> map = new HashMap<>();
		map.put(2011, new BigDecimal(85));
		map.put(2012, new BigDecimal(5));
		map.put(2013, new BigDecimal(10));
		//When
		TreeMap<BigDecimal, Integer> result = KeyValueSwapSortMapUtils.keyValueSwapSort(map);
		//Then
		assertThat(result.size(), is(equalTo(3)));
		List<Entry<BigDecimal, Integer>> list = new ArrayList<>(result.entrySet());
		assertThat(list.get(0).getKey(), is(equalTo(new BigDecimal(5))));
		assertThat(list.get(1).getKey(), is(equalTo(new BigDecimal(10))));
		assertThat(list.get(2).getKey(), is(equalTo(new BigDecimal(85))));
	}
	
	@Test
	public void shouldSwapKeyValueAndSortForStringLong() {
		//Given
		Map<String, Long> map = new HashMap<>();
		map.put("2011", 85l);
		map.put("2012", 5l);
		map.put("2013", 10l);
		//When
		TreeMap<Long, String> result = KeyValueSwapSortMapUtils.keyValueSwapSort(map);
		//Then
		assertThat(result.size(), is(equalTo(3)));
		List<Entry<Long, String>> list = new ArrayList<>(result.entrySet());
		assertThat(list.get(0).getKey(), is(equalTo(new Long(5))));
		assertThat(list.get(1).getKey(), is(equalTo(new Long(10))));
		assertThat(list.get(2).getKey(), is(equalTo(new Long(85))));
	}
	
	@Test
	public void shouldSwapKeyValueAndSortForIntegerString() {
		//Given
		Map<Integer, String> map = new HashMap<>();
		map.put(85, "2012");
		map.put(5, "2013");
		map.put(10, "2011");
		//When
		TreeMap<String, Integer> result = KeyValueSwapSortMapUtils.keyValueSwapSort(map);
		//Then
		assertThat(result.size(), is(equalTo(3)));
		List<Entry<String, Integer>> list = new ArrayList<>(result.entrySet());
		assertThat(list.get(0).getKey(), is(equalTo("2011")));
		assertThat(list.get(1).getKey(), is(equalTo("2012")));
		assertThat(list.get(2).getKey(), is(equalTo("2013")));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void shouldRaiseExceptionWhneMapIsNull() {
		//Given
		Map<Integer, BigDecimal> map = null;
		//When
		KeyValueSwapSortMapUtils.keyValueSwapSort(map);
		fail("Program reached unexpected point!");
	}

}
