package net.viotech.msstream.javaserver.timer;

import javax.inject.Inject;

import net.viotech.msstream.javaserver.service.ContentService;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

//@Service
public class LoopJob implements Job{
	
	@Inject
	ContentService contentService;
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {

	}

}
