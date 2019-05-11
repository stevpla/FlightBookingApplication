/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connections.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ThreadPool offers the creating a as needed thread functionality from a
 * dynamic thread pool. Usage of Executors interface is present. And every Task
 * object is passed to execute the run method as separate thread.
 *
 */
public class ThreadPool {

    private static final ExecutorService EXECUTOR = Executors.newCachedThreadPool();

    /**
     * Creates a new thread from thread pool, and executes the run method of the
     * Runnable task.
     *
     * @param task
     */
    public static void useCachedThread(Runnable task) {
        EXECUTOR.execute(task);
    }
}
