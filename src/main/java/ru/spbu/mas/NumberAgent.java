package ru.spbu.mas;

import jade.core.Agent;

import java.util.Random;
import java.util.concurrent.TimeUnit;


public class NumberAgent extends Agent {
    private String[] linkedAgents;
    private float number;

    protected void setup() {


        int id = Integer.parseInt(getAID().getLocalName());

        Random randomizer = new Random();
        number = randomizer.nextFloat();

        System.out.println("Agent №" + id + " has number " + number);

        String[][] graph = {{"2"},
                {"3"},
                {"4"},
                {"5"},
                {"6"},
                {"7"},
                {"8"},
                {"9"},
                {"10"},
                {"1"}
        };

        this.linkedAgents = graph[id-1];

        addBehaviour(new NumberSendingBehaviour(this, TimeUnit.SECONDS.toMillis(1)));
    }

    public float getNumber() {
        return number;
    }

    public String[] getLinkedAgents() {
        return linkedAgents;
    }

    public void setNumber(float number) {
        this.number = number;
    }

    public void setLinkedAgents(String[] linkedAgents) {
        this.linkedAgents = linkedAgents.clone();
    }
}