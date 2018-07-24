package com.example.quartz.cluster.basics.scheduler;

import com.example.quartz.cluster.config.AutoWiringSpringBeanJobFactory;

import org.quartz.JobDetail;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;

import javax.sql.DataSource;

@Configuration
public class SpringQrtzScheduler {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Bean
    public SpringBeanJobFactory springBeanJobFactory() {
        return new AutoWiringSpringBeanJobFactory();
    }

    @Bean
    public SchedulerFactoryBean scheduler(final SpringBeanJobFactory springBeanJobFactory, final DataSource dataSource, final Trigger trigger, final JobDetail job) {

        final SchedulerFactoryBean schedulerFactory = new SchedulerFactoryBean();
        schedulerFactory.setConfigLocation(new ClassPathResource("quartz.properties"));
        schedulerFactory.setDataSource(dataSource);
     
        this.logger.debug("Setting the Scheduler up");
        schedulerFactory.setJobFactory(springBeanJobFactory);
        schedulerFactory.setJobDetails(job);
        schedulerFactory.setTriggers(trigger);

        return schedulerFactory;
    }

    @Bean
    public JobDetailFactoryBean jobDetail() {
        final JobDetailFactoryBean jobDetailFactory = new JobDetailFactoryBean();
        jobDetailFactory.setJobClass(SampleJob.class);
        jobDetailFactory.setName("Qrtz_Job_Detail");
        jobDetailFactory.setDescription("Invoke Sample Job service...");
        jobDetailFactory.setDurability(true);
        return jobDetailFactory;
    }

    @Bean
    public SimpleTriggerFactoryBean trigger(final JobDetail job) {

        final int frequencyInSec = 10;
        this.logger.info("Configuring trigger to fire every {} seconds", frequencyInSec);

        final SimpleTriggerFactoryBean trigger = new SimpleTriggerFactoryBean();
        trigger.setJobDetail(job);
        trigger.setRepeatInterval(frequencyInSec * 1000);
        trigger.setRepeatCount(SimpleTrigger.REPEAT_INDEFINITELY);
        trigger.setName("Qrtz_Trigger");

        return trigger;
    }
}
