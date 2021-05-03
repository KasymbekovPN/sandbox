package org.kpn.ch18;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    @Autowired
    private JobBuilderFactory jobs;

    @Autowired
    private StepBuilderFactory steps;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private SingerItemProcessor itemProcessor;

    @Bean
    public Job job(JobExecutionStatsListener listener){
        return jobs.get("singerJob").listener(listener).flow(step1()).end().build();
    }

    @Bean
    protected Step step1(){
        return steps.get("step1")
                .<Singer, Singer>chunk(10)
                .reader(itemReader())
                .processor(itemProcessor)
                .writer(itemWriter())
                .build();
    }

    @Bean
    public ItemReader<Singer> itemReader(){
        FlatFileItemReader<Singer> itemReader = new FlatFileItemReader<>();
        itemReader.setResource(new ClassPathResource("support/test-data.csv"));
        itemReader.setLineMapper(new DefaultLineMapper<Singer>(){{
            setLineTokenizer(new DelimitedLineTokenizer(){{
                setNames("firstName", "lastName", "song");
            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper<>(){{
                setTargetType(Singer.class);
            }});
        }});

        return itemReader;
    }

    @Bean
    public ItemWriter<Singer> itemWriter(){
        final JdbcBatchItemWriter<Singer> itemWriter = new JdbcBatchItemWriter<>();
        itemWriter.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
        itemWriter.setSql("insert into singer (first_name,last_name,song) values (:firstName, :lastName, :song)");
        itemWriter.setDataSource(dataSource);

        return itemWriter;
    }
}
