package com.paner.controller.workflow;

import org.activiti.engine.*;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.spring.ProcessEngineFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by www-data on 16/6/6.
 */


public class Demo {



    @Autowired
    private ProcessEngineFactoryBean processEngine;

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private HistoryService historyService;

    @Autowired
    private IdentityService identityService;

    @RequestMapping(value = "/testEvent")
    public void testEvent() throws InterruptedException {
        try {
            repositoryService.createDeployment()
                    .addClasspathResource("workflow/demo.bpmn20.xml")
                    .deploy();
            System.out.println("Number of process definitions: " + repositoryService.createProcessDefinitionQuery().count());

            identityService.setAuthenticatedUserId("Jeff Dean");

            ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("helloProcess");
            System.out.println(processInstance.getId());
            System.out.println(processInstance.getProcessDefinitionId());
            System.out.println(processInstance.getProcessDefinitionKey());

            List<Task> tasks = taskService.createTaskQuery().taskAssignee("kermit").list();
            for (Task task : tasks) {
                System.out.println(task.getName() + " : " + task.getAssignee());

                taskService.claim(task.getId(), "kermit");
            }

            tasks = taskService.createTaskQuery().taskAssignee("kermit").list();
            for (Task task : tasks) {

                taskService.complete(task.getId());
                System.out.println(task.getName() + " : " + task.getId() + " completed ");
            }

            Thread.sleep(10 * 1000);

            tasks = taskService.createTaskQuery().taskAssignee("stone").list();
            for (Task task : tasks) {
                System.out.println(task.getName() + " : " + task.getAssignee());
                taskService.claim(task.getId(), "stone");
            }

            tasks = taskService.createTaskQuery().taskAssignee("stone").list();
            for (Task task : tasks) {
                taskService.complete(task.getId());
                System.out.println(task.getName() + " : " + task.getId() + " completed ");
            }

            tasks = taskService.createTaskQuery().taskAssignee("kermit").list();
            for (Task task : tasks) {
                System.out.println(task.getName() + " : " + task.getAssignee());
                taskService.claim(task.getId(), "kermit");
            }


            HistoricProcessInstance hpInstance =
                    historyService.createHistoricProcessInstanceQuery()
                            .processInstanceId(processInstance.getId()).singleResult();
            System.out.println("end time: " + hpInstance.getEndTime());
        } catch(Exception ex){
                ex.printStackTrace();
            }

    }
}
