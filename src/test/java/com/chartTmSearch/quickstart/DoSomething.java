package com.chartTmSearch.quickstart;

public class DoSomething implements Runnable{
	private String name; 

    public DoSomething(String name) { 
        this.name = name; 
    } 
	@Override
	public void run() {
		for (int i = 0; i < 5; i++) { 
            System.out.println(name + ": " + i); 
        } 
	}

}
