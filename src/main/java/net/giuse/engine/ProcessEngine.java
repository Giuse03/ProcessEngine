package net.giuse.engine;


import com.coreoz.wisp.Scheduler;
import com.coreoz.wisp.schedule.Schedule;
import com.coreoz.wisp.schedule.Schedules;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;


import java.time.Duration;
import java.util.concurrent.*;

public class ProcessEngine  {
    private final ExecuteProcess executeSyncProcess = new ExecuteProcess();

    private final ExecuteProcess executeAsyncProcess = new ExecuteProcess();;

    private final ExecutorService scheduledExecutorService =   Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    private final ListeningExecutorService asyncThread = MoreExecutors.listeningDecorator(scheduledExecutorService);

    public void executeNewProcessSync(Workload workload) {
        executeSyncProcess.addWorkload(workload);
    }

    public void executeNewProcessAsync(Workload workload) {
        executeAsyncProcess.addWorkload(workload);
    }

    public ProcessEngine(JavaPlugin javaPlugin){
        scheduledExecutorService.submit(()->{
            Bukkit.getScheduler().scheduleSyncRepeatingTask(javaPlugin,executeSyncProcess,0,0);
        });

        asyncThread.submit(() ->{
            Scheduler scheduler = new Scheduler();
            scheduler.schedule(
                    executeAsyncProcess,Schedules.fixedDelaySchedule(Duration.ofMillis(1)));  
        });
    }




}
