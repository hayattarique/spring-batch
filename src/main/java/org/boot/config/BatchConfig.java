package org.boot.config;

import org.boot.listener.JobNotificationListener;
import org.boot.model.Product;
import org.boot.processor.ProductProcessor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
public class BatchConfig {
    @Bean
    public TaskExecutor taskExecutor() {
        SimpleAsyncTaskExecutor executor = new SimpleAsyncTaskExecutor();
        executor.setConcurrencyLimit(20);
        return executor;
    }

    @Bean
    public Step step1(JobRepository repository, DataSourceTransactionManager transactionManager, ItemReader<Product> reader, ItemWriter<Product> writer,
                      ItemProcessor<Product, Product> processor) {
        return new StepBuilder("step1", repository).<Product, Product>chunk(50, transactionManager).
                reader(reader).processor(processor).writer(writer).build();

    }

    @Bean
    public ItemWriter<Product> writer(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<Product>().sql("insert into product (product_id,title,description,price,discount_percent)" +
                " values (:productId,:title,:description,:price,:discount)").dataSource(dataSource).beanMapped().build();

    }

    @Bean
    public ItemProcessor<Product, Product> processor() {
        return new ProductProcessor();
    }

    @Bean
    FlatFileItemReader<Product> reader() {
        return new FlatFileItemReaderBuilder<Product>()
                .resource(new ClassPathResource("data.csv"))

                .name("data").delimited().names("productId", "title", "description", "price", "discount")
                .fieldSetMapper(new BeanWrapperFieldSetMapper<Product>()).targetType(Product.class)
                .beanMapperStrict(false).linesToSkip(1).build();
    }



    @Bean
    public Job job(JobRepository repository, Step step1, JobNotificationListener listener) {
        return new JobBuilder("job", repository).start(step1).listener(listener).build();
    }

}
