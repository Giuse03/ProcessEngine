package net.giuse.engine;


import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ProcessEngine  {

    @Getter
    private final ExecutorService forkJoinPool = Executors.newWorkStealingPool(Runtime.getRuntime().availableProcessors()/2);
    private final ExecuteProcess executeSyncProcess = new ExecuteProcess();
    private final ExecuteProcess executeAsyncProcess = new ExecuteProcess();;

    public void executeNewProcessSync(Workload workload) {
        executeSyncProcess.addWorkload(workload);
    }

    public void executeNewProcessAsync(Workload workload) {
        executeAsyncProcess.addWorkload(workload);
    }

    public ProcessEngine(JavaPlugin javaPlugin){

        Bukkit.getScheduler().scheduleAsyncRepeatingTask(javaPlugin, executeAsyncProcess,0L,0L);
        Bukkit.getScheduler().scheduleSyncRepeatingTask(javaPlugin, executeSyncProcess,0L,0L);
    }




}
