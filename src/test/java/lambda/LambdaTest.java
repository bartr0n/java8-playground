package lambda;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
}
