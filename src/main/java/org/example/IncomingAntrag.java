package org.example;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class IncomingAntrag implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        Boolean bool = (Boolean) delegateExecution.getVariable("aVariable");
        delegateExecution.setVariable("works", bool);

    }
}
