package ru.spbu.mas;

import java.util.Objects;

import jade.core.AID;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;

import javax.swing.*;


public class FindAverageBehaviour extends TickerBehaviour {
    private final FindAverageAgent agent;
    private int currentStep;
    private final int MAX_STEPS = 10;

    FindAverageBehaviour(FindAverageAgent agent, long period) {
        super(agent, period);
        this.setFixedPeriod(true);
        this.agent = agent;
        this.currentStep = 0;
    }

    @Override
    protected void onTick() {

        float sum = this.agent.getNumber();

        if(currentStep < MAX_STEPS){

        if (currentStep+1 == Integer.parseInt(this.agent.getLocalName())) {

            ACLMessage r_msg = this.agent.receive();
            if (r_msg != null) {
                System.out.println("Agent №" + this.agent.getLocalName() + " received " + r_msg.getContent() +
                        " from agent №" +
                        r_msg.getSender().getLocalName());
                float sum_previous = Float.parseFloat(r_msg.getContent());
                sum += sum_previous;
                System.out.println("Average = " + sum / Float.parseFloat(this.agent.getLocalName()));
            }


//            System.out.println("Message queue size of Agent №" +
//                    this.agent.getLocalName() +
//                    " is " +this.agent.getCurQueueSize());

        }
            this.currentStep++;
        } else {
            this.stop();
        }
    }

}
