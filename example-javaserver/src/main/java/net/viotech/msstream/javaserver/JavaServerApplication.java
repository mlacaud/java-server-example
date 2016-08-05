package net.viotech.msstream.javaserver;

import java.util.Arrays;
import java.util.Date;
import java.util.Properties;

import net.viotech.msstream.javaserver.cli.CliConfArgs;
import net.viotech.msstream.javaserver.configuration.SchedulerConfig;
import net.viotech.msstream.javaserver.timer.LoopJob;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

@SpringBootApplication
@Import({SchedulerConfig.class})
public class JavaServerApplication{
	private static final Logger LOGGER = LoggerFactory
			.getLogger(JavaServerApplication.class);
	
	public static void main(String[] args) {
		
		CliConfArgs.getParametersFromArgs(args);
		
		ApplicationContext ctx = SpringApplication.run(JavaServerApplication.class,
				args);
		LOGGER.debug("Let's inspect the beans provided by Spring Boot:");
		String[] beanNames = ctx.getBeanDefinitionNames();
		Arrays.sort(beanNames);
		for (String beanName : beanNames) {
			LOGGER.debug(beanName);
		}	
	
	}
	
	
	public Scheduler scheduler() throws SchedulerException {
		StdSchedulerFactory sf = new StdSchedulerFactory();

		Properties props = new Properties();
		props.put("org.quartz.threadPool.threadCount", "1");
		sf.initialize(props);

		Scheduler sched = sf.getScheduler();
		{
			JobDetail job = newJob(LoopJob.class).build();
			Trigger trigger = newTrigger()
					.withSchedule(
							simpleSchedule().withIntervalInSeconds(20)
									.repeatForever()).startAt(new Date())
					.build();

			sched.scheduleJob(job, trigger);

		}
		sched.start();
		return sched;
	}
	
}
