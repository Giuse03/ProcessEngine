package net.giuse.engine;

import lombok.SneakyThrows;

import java.util.concurrent.CompletableFuture;

public class Worker {

    @SneakyThrows
    public static void executeProcess(CompletableFuture<Workload> instanceClass, boolean async) {
        if (async) {
            instanceClass.whenCompleteAsync((instanceKlass, throwable) -> ProcessEngine.getProcessEngine().executeNewProcessAsync(instanceKlass));
        }
        instanceClass.whenComplete((instanceKlass, throwable) -> ProcessEngine.getProcessEngine().executeNewProcessSync(instanceKlass));
    }
}
