package devproblem;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

public class WineTest {

	public static void main(String[] args) {

		Wine w = new Wine("11YVCHAR001", 1000);
		w.setDescription("2011 Yarra Valley Chardonnay");
		w.setTankCode("T25-01");
		w.setProductState("Ready for bottling");
		w.setOwnerName("YV Wines Pty Ltd");
		
		
		w.getComponents().add(new GrapeComponent(80D, 2011, "Chardonnay", "Yarra Valley"));
		w.getComponents().add(new GrapeComponent(10D, 2010, "Chardonnay", "Macedon"));
		w.getComponents().add(new GrapeComponent(5D, 2011, "Pinot Noir", "Mornington"));
		w.getComponents().add(new GrapeComponent(5D, 2010, "Pinot Noir", "Macedon"));
		
		printYearBreakdown(w);
		printVarietyBreakdown(w);
		printRegionBreakdown(w);
		printYearAndVarietyBreakdown(w);
		
	}

	private static void printVarietyBreakdown(Wine w) {
		
		try {
			Map<String, BigDecimal>  varietyMap = populateMapFromSet(w.getComponents(), "variety");
			@SuppressWarnings("unchecked")
			TreeMap<BigDecimal, String> sortedMap = KeyValueSwapSortMapUtils.keyValueSwapSort(varietyMap);
				
			List<Entry<BigDecimal, String>> sortedList = new ArrayList<>(sortedMap.descendingMap().entrySet());
			StringOutputUtils.printListEntryPerLine(sortedList, System.out, "%2.0f%% %s");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void printYearBreakdown(Wine w) {
		Map<Integer, BigDecimal> yearMap = new HashMap<>();
		Set<GrapeComponent> components = w.getComponents();
		for(GrapeComponent component : components) {
			BigDecimal percentage = new BigDecimal(component.getPercentage());
			Integer year = component.getYear();
			BigDecimal yearPercentage = yearMap.get(year);
			if (null == yearPercentage) {
				yearMap.put(year, percentage);
			} else {
				yearMap.put(year, yearPercentage.add(percentage));
			}
		}
		
		@SuppressWarnings("unchecked")
		TreeMap<BigDecimal, Integer> sortedMap = KeyValueSwapSortMapUtils.keyValueSwapSort(yearMap);
		List<Entry<BigDecimal, Integer>> sortedList = new ArrayList<>(sortedMap.descendingMap().entrySet());
		StringOutputUtils.printListEntryPerLine(sortedList, System.out, "%2.0f%% %d");
	}
	
	private static void printRegionBreakdown(Wine w) {
		Map<String, BigDecimal> regionMap;
		try {
			regionMap = populateMapFromSet(w.getComponents(), "region");
			@SuppressWarnings("unchecked")
			TreeMap<BigDecimal, String> sortedMap = KeyValueSwapSortMapUtils.keyValueSwapSort(regionMap);
				
			List<Entry<BigDecimal, String>> sortedList = new ArrayList<>(sortedMap.descendingMap().entrySet());
			StringOutputUtils.printListEntryPerLine(sortedList, System.out, "%2.0f%% %s");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static Map<String, BigDecimal> populateMapFromSet(final Set<GrapeComponent> components, final String fieldName) throws Exception {
		Map<String, BigDecimal> map = new HashMap<>();
		for(GrapeComponent component : components) {
			BigDecimal percentage = new BigDecimal(component.getPercentage());
			Field field = GrapeComponent.class.getDeclaredField(fieldName);
			field.setAccessible(true);
			String string = (String) field.get(component); 
			BigDecimal varietyPercentage = map.get(string);
			if (null == varietyPercentage) {
				map.put(string, percentage);
			} else {
				map.put(string, varietyPercentage.add(percentage));
			}
		}	
		return map;
	}
	
	private static void printYearAndVarietyBreakdown(Wine w) {
		Map<String, BigDecimal> map = new HashMap<>();
		Set<GrapeComponent> components = w.getComponents();
		for(GrapeComponent component : components) {
			BigDecimal percentage = new BigDecimal(component.getPercentage());
			Integer year = component.getYear();
			String variety = component.getVariety();
			String label = year + "-" + variety;
			BigDecimal varietyPercentage = map.get(label);
			if (null == varietyPercentage) {
				map.put(label, percentage);
			} else {
				map.put(label, varietyPercentage.add(percentage));
			}
		}	
		@SuppressWarnings("unchecked")
		TreeMap<BigDecimal, List<String>> sortedMap = new TreeMap<>();
		for (Entry<String, BigDecimal> elm :  map.entrySet()) {
			List<String> strings = sortedMap.get(elm.getValue());
			if (null == strings) {
				strings = new ArrayList<>();
			}
			strings.add(elm.getKey());
			sortedMap.put(elm.getValue(), strings);
		}
			
		List<Entry<BigDecimal, List<String>>> sortedList = new ArrayList<>(sortedMap.descendingMap().entrySet());
		for(Entry<BigDecimal, List<String>> elm : sortedList) {
			List<String> strings = elm.getValue();
			for (String string : strings) {
				String ouptString = String.format("%2.0f%% %s", elm.getKey(), string);
				System.out.println(ouptString);
			}
		}
	}

}
