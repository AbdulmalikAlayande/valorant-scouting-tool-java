package app.bola.esportsscoutingtool.config;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
@EnableAsync
public class AsyncConfig implements AsyncConfigurer {
	
	/**
	 * This is the executor Spring will use for methods annotated with @Async
	 * (unless you reference another executor by name in @Async("...")).
	 *
	 * You can align these numbers with the properties you already have:
	 * spring.task.execution.pool.core-size / max-size
	 */
	@Bean(name = "applicationTaskExecutor")
	public AsyncTaskExecutor applicationTaskExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setThreadNamePrefix("async-");
		executor.setCorePoolSize(5);
		executor.setMaxPoolSize(10);
		
		// Good defaults; tune based on workload
		executor.setQueueCapacity(200);
		executor.setAllowCoreThreadTimeOut(true);
		executor.setKeepAliveSeconds(60);
		
		// When saturated, run on caller thread (backpressure instead of dropping)
		executor.setRejectedExecutionHandler(new java.util.concurrent.ThreadPoolExecutor.CallerRunsPolicy());
		
		executor.initialize();
		return executor;
	}
	
	@Override
	public Executor getAsyncExecutor() {
		return applicationTaskExecutor();
	}
	
	/**
	 * Handles exceptions thrown from @Async methods with void return type.
	 * (For CompletableFuture, exceptions are captured in the future.)
	 */
	@Override
	public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
		return (ex, method, params) -> {
			System.err.println("Uncaught async error in " + method + ": " + ex.getMessage());
			ex.printStackTrace(System.err);
		};
	}
}