package org.openwebflow.ctrl.command;

import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.impl.pvm.runtime.AtomicOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StartActivityCmd implements Command<java.lang.Void> {
  private static final Logger logger = LoggerFactory.getLogger(StartActivityCmd.class);

  private ActivityImpl _activity;

  private String _executionId;

  public StartActivityCmd(String executionId, ActivityImpl activity) {
    _activity = activity;
    _executionId = executionId;
  }

  @Override
  public Void execute(CommandContext commandContext) {
    // 创建新任务
    logger.debug(String.format("executing activity: %s", _activity.getId()));

    ExecutionEntity execution = commandContext.getExecutionEntityManager()
        .findExecutionById(_executionId);
    execution.setActivity(_activity);

    execution.performOperation(AtomicOperation.ACTIVITY_START);
    return null;
  }
}