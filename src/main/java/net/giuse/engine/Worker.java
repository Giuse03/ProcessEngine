package net.giuse.engine;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import java.util.concurrent.CompletableFuture;

@RequiredArgsConstructor
public class Worker {

    private final ProcessEngine processEngine;
    @SneakyThrows
    public void executeProcess(CompletableFuture<Workload> instanceClass, boolean async) {
        if (async) {
            instanceClass.whenCompleteAsync((instanceKlass, throwable) -> processEngine.executeNewProcessAsync(instanceKlass));
            return;
        }
        instanceClass.whenComplete((instanceKlass, throwable) -> processEngine.executeNewProcessSync(instanceKlass));
    }
}
