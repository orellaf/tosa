package fr.lta.tosa.icecream;
import java.util.*;
import java.util.Map.Entry;

public class IsoContest {
public static void main( String[] argv ) throws Exception {
		String  line;
		Scanner sc = new Scanner(System.in);
		
		Map<String,Integer> map = new HashMap<>();
		while(sc.hasNextLine()) {
			line = sc.nextLine();
			if(map.get(line) == null) {
			    map.put(line, 1);
			} else {
			    int count = map.get(line);
			     map.put(line, count + 1);
			}
			
			/* Lisez les données et effectuez votre traitement */
		}
		
		List<Integer> values = new ArrayList<>(map.values());
		Collections.sort(values);
		
		String key1 = "";
		String key2 = "";
		for (Entry<String, Integer> entry : map.entrySet()) {
			if(values.get(values.size()-1) == entry.getValue()) {
				key1 = entry.getKey();
			}
			if(values.get(values.size()-2) == entry.getValue()) {
				key2 = entry.getKey();
			}
		}
		
		System.out.println(key1+" "+key2);
			
		
		
	/* Vous pouvez aussi effectuer votre traitement une fois que vous avez lu toutes les données.*/
	}
}