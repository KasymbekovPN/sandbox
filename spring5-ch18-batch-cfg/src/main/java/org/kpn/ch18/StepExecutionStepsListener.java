package org.kpn.ch18;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.listener.StepExecutionListenerSupport;
import org.springframework.stereotype.Component;

@Component
public class StepExecutionStepsListener extends StepExecutionListenerSupport {

    private static final Logger logger = LoggerFactory.getLogger(StepExecutionStepsListener.class);

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        logger.info("--> wrote: {} items in step : {}", stepExecution.getWriteCount(), stepExecution.getStepName());
        return null;
    }
}
