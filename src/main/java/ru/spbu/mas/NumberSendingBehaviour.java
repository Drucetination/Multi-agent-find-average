package ru.spbu.mas;

import java.util.Objects;

import jade.core.AID;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;

import javax.swing.*;


public class NumberSendingBehaviour extends TickerBehaviour {
    private final NumberAgent agent;
    private int currentStep;
    private final int MAX_STEPS = 10;

    NumberSendingBehaviour(NumberAgent agent, long period) {
        super(agent, period);
        this.setFixedPeriod(true);
        this.agent = agent;
        this.currentStep = 0;
    }


    @Override
    public void onTick() {

        if (currentStep < MAX_STEPS) {

//            System.out.println(this.getTickCount());

            float sum = this.agent.getNumber();
            AID receiver = new AID(this.agent.getLinkedAgents()[0], jade.core.AID.ISLOCALNAME);
            ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
            msg.setSender(this.agent.getAID());
            msg.addReceiver(receiver);

            if (currentStep == 0 && this.agent.getLocalName().equals("1")) {
//            System.out.println("AAAAAAAAAAAA");
                msg.setContent(Objects.toString(sum));
                this.agent.send(msg);
            } else if (currentStep+1 == Integer.parseInt(this.agent.getLocalName())) {
//                System.out.println("");

                ACLMessage r_msg = this.agent.receive();
                if (r_msg != null) {
                    System.out.println("Agent №" + this.agent.getLocalName() + " received " + r_msg.getContent() +
                            " from agent №" +
                            r_msg.getSender().getLocalName());
                    float sum_previous = Float.parseFloat(r_msg.getContent());
                    sum += sum_previous;
                }
                ACLMessage msg1 = new ACLMessage(ACLMessage.INFORM);
                msg1.setSender(this.agent.getAID());
                msg1.addReceiver(receiver);
                msg1.setContent(Objects.toString(sum));
                this.agent.send(msg1);
            }
            currentStep++;
        } else {
            this.stop();
        }
    }
}
