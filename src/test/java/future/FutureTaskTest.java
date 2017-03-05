package future;

import org.junit.Test;

import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static org.junit.Assert.*;

/**
 * Created by joseam on 05/03/2017.
 */

public class FutureTaskTest {


    @Test
    public void simpleFutureTest() throws Exception {

        String result = Thread.currentThread().getName();

        // Creation de la task. On crée un nouveau thread pour lancer la tâche
        // Si on ne fait pas ça, la tâche est lancé sur le même thread (attention run != start)
        FutureTask<String> futureTask = getInstance(3000L);
        new Thread(futureTask).start();

        assertFalse(futureTask.isCancelled());

        // On bloc jusqu'à la réponse de la tâche
        assertTrue("Wrong response", !result.equals(futureTask.get())); // Récupération de la valeur de retour
    }

    @Test
    public void stoppingFutureTask() throws Exception {

        // Creation de la task. On crée un nouveau thread pour lancer la tâche
        // Si on ne fait pas ça, la tâche est lancé sur le même thread (attention run != start)
        FutureTask<String> futureTask = getInstance(3000L);
        new Thread(futureTask).start();

        assertFalse(futureTask.isCancelled());

        futureTask.cancel(true);

        assertTrue("Task is running yet", futureTask.isCancelled());
    }

    @Test(expected = TimeoutException.class)
    public void timeoutTest() throws Exception {

        FutureTask<String> futureTask = getInstance(5000L);
        new Thread(futureTask).start();

        assertFalse(futureTask.isCancelled());

        futureTask.get(3000L, TimeUnit.MILLISECONDS); // On attend 3 secondes
    }

    @Test
    public void reuseTaskTest() throws Exception {

        FutureTask<String> futureTask = getInstance(1000L);
        new Thread(futureTask).start();

        String result1 = futureTask.get();
        assertTrue(futureTask.isDone());
        assertNotNull(result1);

        new Thread(futureTask).start();

        String result2 = futureTask.get();
        assertTrue(futureTask.isDone());
        assertNotNull(result2);
    }

    private FutureTask<String> getInstance(Long timeout) {
        return new FutureTask<>(() -> {
            Thread.sleep(timeout);
            return Thread.currentThread().getName();
        });
    }
}
