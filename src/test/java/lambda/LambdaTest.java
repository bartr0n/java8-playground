package lambda;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

/**
 * Created by joseam on 05/03/2017.
 */
public class LambdaTest {


    @Test
    public void filterTest() {

        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));

        List<Integer> resultList = list.stream().filter(x -> x > 3).collect(Collectors.toList());
        assertNotNull(resultList);

        assertEquals("Wrong size", 2, resultList.size());
    }
    
    @Test
    public void mapTest() {
    	
    	List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
    	List<String> result = list.stream().map(x -> x.toString()).collect(Collectors.toList());
    	
    	assertEquals("Wrong size", list.size(), result.size());
    }
    
    @Test
    public void findTest() {
    	List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
    	Optional<Integer> result = list.stream().filter(x -> x == 4).findFirst();
    	
    	assertTrue(result.isPresent());
    	assertEquals(Integer.valueOf(4), result.get());
    }
    
    @Test
    public void reduceTest() {
    	
    	List<Integer> list = new ArrayList<>();
    	for (int i=1; i<101; i++) {
    		list.add(i);
    	}
    	
    	// Integer result = list.stream().reduce(0, (a, b) -> a + b);
    	Integer result = list.stream().reduce(0, Integer::sum);
    	assertEquals(Integer.valueOf(5050), result);
    }
    
    @Test
    public void reduceMaxTest() {
    	List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
    	
    	Optional<Integer> result = list.parallelStream().reduce(Integer::max);
    	assertTrue(result.isPresent());
    	assertEquals(Integer.valueOf(5), result.get());
    }
}
