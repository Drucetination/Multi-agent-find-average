package ru.spbu.mas;

import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.domain.FIPAAgentManagement.ExceptionOntology;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;

public class MainController {
    private static final int numberOfAgents = 10;

    void initAgents() {
        // Retrieve the singleton instance of the JADE Runtime
        Runtime rt = Runtime.instance();

        // Create a container to host the Default Agent
        Profile p = new ProfileImpl();
        p.setParameter(Profile.MAIN_HOST, "localhost");
        p.setParameter(Profile.MAIN_PORT, "8080");
        p.setParameter(Profile.GUI, "false");
        ContainerController cc = rt.createMainContainer(p);

        try{
            for (int i=1; i <= MainController.numberOfAgents - 1; i++) {
                AgentController agent = cc.createNewAgent(Integer.toString(i), "ru.spbu.mas.NumberAgent", null);
                agent.start();
            }

            AgentController fa_agent = cc.createNewAgent(Integer.toString(numberOfAgents), "ru.spbu.mas.FindAverageAgent",null);
            fa_agent.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
