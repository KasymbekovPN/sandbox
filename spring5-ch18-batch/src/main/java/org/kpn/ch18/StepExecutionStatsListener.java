package org.kpn.ch18;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.listener.StepExecutionListenerSupport;

public class StepExecutionStatsListener extends StepExecutionListenerSupport {

    private static final Logger logger = LoggerFactory.getLogger(StepExecutionStatsListener.class);

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        logger.info("--> Wrote: {} items in step : {}", stepExecution.getWriteCount(), stepExecution.getStepName());
        return null;
    }
}
