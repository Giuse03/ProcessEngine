package net.giuse.engine;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import java.util.concurrent.CompletableFuture;

@RequiredArgsConstructor
public class Worker {

    private final ProcessEngine processEngine;
    @SneakyThrows
    public void executeProcess(Workload workload, boolean async) {
        if (async) {
             processEngine.executeNewProcessAsync(workload);
            return;
        }
        processEngine.executeNewProcessSync(workload);
    }
}
