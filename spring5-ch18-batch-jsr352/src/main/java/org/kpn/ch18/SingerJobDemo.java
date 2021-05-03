package org.kpn.ch18;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.jsr.launch.JsrJobOperator;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.batch.runtime.BatchStatus;
import javax.batch.runtime.JobExecution;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

public class SingerJobDemo {

    private static final Logger logger = LoggerFactory.getLogger(SingerJobDemo.class);

    public static void main(String[] args) throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, IOException, JobRestartException {
        m0();
//        m1();
    }

    private static void m1() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException, IOException {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("META-INF/batch-jobs/singerJob.xml");

        Job job = ctx.getBean(Job.class);
        JobLauncher jobLauncher = ctx.getBean(JobLauncher.class);
        JobParameters params = new JobParametersBuilder()
                .addDate("date", new Date())
                .toJobParameters();
        jobLauncher.run(job, params);

        System.in.read();
        ctx.close();
    }

    private static void m0(){

//        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("META-INF/batch-jobs/singerJob.xml");

        JsrJobOperator jobOperator = new JsrJobOperator();
        long executionId = jobOperator.start("singerJob", new Properties());
        JobExecution jobExecution = jobOperator.getJobExecution(executionId);

        waitForJob(jobOperator, jobExecution);
    }

    private static void waitForJob(JsrJobOperator jobOperator, JobExecution jobExecution){
        BatchStatus batchStatus = jobExecution.getBatchStatus();
        while (true){
            //<
//            logger.info("{}", batchStatus);
            //<
            if (batchStatus == BatchStatus.STOPPED ||
                batchStatus == BatchStatus.COMPLETED ||
                batchStatus == BatchStatus.FAILED){
                return;
            }

            jobOperator.getJobExecution(jobExecution.getExecutionId());
            batchStatus = jobExecution.getBatchStatus();
        }
    }
}
