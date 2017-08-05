package coo.core.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 任务线程池配置属性。
 */
@ConfigurationProperties("coo.task")
public class ExecutorProperties {
  private Integer executorPoolSize = 20;
  private Integer schedulerPoolSize = 20;

  public Integer getExecutorPoolSize() {
    return executorPoolSize;
  }

  public void setExecutorPoolSize(Integer executorPoolSize) {
    this.executorPoolSize = executorPoolSize;
  }

  public Integer getSchedulerPoolSize() {
    return schedulerPoolSize;
  }

  public void setSchedulerPoolSize(Integer schedulerPoolSize) {
    this.schedulerPoolSize = schedulerPoolSize;
  }
}
