package net.giuse.engine;


import com.google.common.util.concurrent.ListeningScheduledExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ProcessEngine  {
    private final ExecuteProcess executeSyncProcess = new ExecuteProcess();
    private final ExecuteProcess executeAsyncProcess = new ExecuteProcess();

    public void executeNewProcessSync(Workload workload) {
        executeSyncProcess.addWorkload(workload);
    }

    public synchronized void executeNewProcessAsync(Workload workload) {
        executeAsyncProcess.addWorkload(workload);
    }

    public ProcessEngine(JavaPlugin javaPlugin){

        Bukkit.getScheduler().runTaskTimer(javaPlugin, executeSyncProcess,0L,0L);
        Bukkit.getScheduler().runTaskTimerAsynchronously(javaPlugin,executeAsyncProcess,0L,0L);

    }




}
