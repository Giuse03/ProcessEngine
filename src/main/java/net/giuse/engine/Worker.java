package net.giuse.engine;

import lombok.SneakyThrows;

import java.util.concurrent.CompletableFuture;

public class Worker {

    @SneakyThrows
    public static void executeProcess(CompletableFuture<Workload> instanceClass, boolean async) {
        if(async){
            ProcessEngine.getProcessEngine().executeNewProcessAsync(instanceClass.get());
        }else {
            ProcessEngine.getProcessEngine().executeNewProcessSync(instanceClass.get());
        }
    }
}
