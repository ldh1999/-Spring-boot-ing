package com.ldh.usePage;

import com.ldh.Factor.FactorImpl.FactorEasy;
import com.ldh.Factor.InstanceFactor;
import com.ldh.usePage.entity.Pop;

public class run {
    public static void main(String args[]){
        InstanceFactor instanceFactor = new FactorEasy();
        Pop pop = (Pop)instanceFactor.getInstance(Pop.class);
        System.out.println(pop);
    }
}
