package com.deloitte.tms.pl.workflow.utils;

import java.util.Random;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.StaleStateException;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.deloitte.tms.pl.workflow.command.CommandService;
import com.deloitte.tms.pl.workflow.command.impl.AcquireDbidCommand;

/**
 * @author Jacky.gao
 * @since 2013年8月14日
 */
@Component
public class IDGenerator implements ApplicationContextAware{
	private static Log log = LogFactory.getLog(IDGenerator.class);
	@Resource
	private CommandService commandService;
	private static IDGenerator generator;
	private static Random random = new Random();
	private long nextId;
	private long lastId=-1;
	@Value("${uflo.idBlockSize}")
	private int blockSize=5000;
	private int maxAttempts = 5;

	public synchronized long nextId() {
		if (lastId < nextId) {
			for (int attempts = maxAttempts; (attempts > 0); attempts--) {
				try {
					AcquireDbidCommand command = new AcquireDbidCommand(blockSize);
					nextId = commandService.executeCommandInNewTransaction(command);
					lastId = nextId + blockSize - 1;
					break;
				} catch (StaleStateException e) {
					attempts--;
					if (attempts == 0) {
						throw new IllegalStateException("couldn't acquire block of ids, tried "+ maxAttempts + " times");
					}
					// if there are still attempts left, first wait a bit
					int millis = 20 + random.nextInt(200);
					log.debug("optimistic locking failure while trying to acquire id block.  retrying in "+ millis + " millis");
					try {
						Thread.sleep(millis);
					} catch (InterruptedException e1) {
						log.debug("waiting after id block locking failure got interrupted");
					}
				}
			}
		}
		return nextId++;
	}
	
	public static IDGenerator getInstance(){
		return generator;
	}

	public void setCommandService(CommandService commandService) {
		this.commandService = commandService;
	}

	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		IDGenerator.generator=this;
	}

	public void setBlockSize(int blockSize) {
		this.blockSize = blockSize;
	}
}
