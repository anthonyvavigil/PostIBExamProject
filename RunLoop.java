
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Henry Estberg
 */
public class RunLoop {

	// for fixed speed assignments
	public static final int tellerSpeedFixed = 60;
	public static final int customerSpeedFixed = 50;

	// for random speed assignments
	public static final int tellerSpeedsUpperBound = 100;
	public static final int tellerSpeedsLowerBound = 10;
	public static final int customerSpeedsUpperBound = 100;
	public static final int customerSpeedsLowerBound = 100;
        
        //Maximum and minimum simulation times
        public static final int minSimulationTime = 10;
        public static final int maxSimulationTime = 100;

	public RunLoop(String assignmentType, int numCustomers, int numLines, boolean isFixedTellerSpeeds,
			boolean isFixedCustomerSpeeds, String spawnType, int spawnRate, int simulationTime) {
		this.numLines = numLines;
		this.numCustomers = numCustomers;
		this.numLines = numLines;
		this.isFixedTellerSpeeds = isFixedTellerSpeeds;
		this.isFixedCustomerSpeeds = isFixedCustomerSpeeds;
		this.spawnType = spawnType;
		this.spawnRate = spawnRate;
                this.simulationTime = simulationTime;
	}

	private long tick;
	private String assignmentType;
	private int numCustomers;
	private int numLines;
	private boolean isFixedTellerSpeeds;
	private boolean isFixedCustomerSpeeds;
	private String spawnType;
	private int spawnRate;
        private int simulationTime;
	private boolean simulationRunning = true;
	public ArrayList<Teller> allTellers = new ArrayList();
	public ArrayList<Customer> allCustomers = new ArrayList();
	public ArrayList<Line> allLines = new ArrayList();

	public void run() {
		tick = 0;
		initializationMethods();

		// The constructor requires the random generation for teller speed to be done
		// before item generation

		while (simulationRunning) {
			try {
				Thread.sleep(20);
				tick += 1;
				updateLines();
                                checkTime(tick);
			} catch (InterruptedException ex) {
				Logger.getLogger(RunLoop.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}

	/*
	 * extra methods
	 */

	public void initializationMethods() {
		addTellers(numLines, isFixedTellerSpeeds);
		addCustomers(numCustomers, isFixedCustomerSpeeds);
		addLines(numLines);
		assignCustomers(assignmentType);
                setMaxTime();
	}

	// initialize objects

	// adds tellers to arrayList of all tellers
	public void addTellers(int tNum, boolean isFixedSpeed) {
		for (int i = 0; i < tNum; i++) {
			Teller temp = null;
			if (isFixedSpeed) {
				temp = new Teller(i, tellerSpeedFixed);
			} else {
				Random r = new Random();
				int tSp = r.nextInt((tellerSpeedsUpperBound - tellerSpeedsLowerBound)) + tellerSpeedsLowerBound;
				temp = new Teller(i, tSp);
			}
			temp.setTransTime(0); // because no transactions have occurred
			allTellers.add(temp);
		}
	}

	// adds customers to arrayList of all customers
	public void addCustomers(int cNum, boolean isFixedSpeed) {
		for (int i = 0; i < cNum; i++) {
			Customer temp = null;
			if (isFixedSpeed) {
				temp = new Customer(i, customerSpeedFixed);
			} else {
				Random r = new Random();
				int tSp = r.nextInt((customerSpeedsUpperBound - customerSpeedsLowerBound)) + customerSpeedsLowerBound;
				temp = new Customer(i, tSp);
			}
			allCustomers.add(temp);
		}
	}
          

	// adds an empty line for each teller
	public void addLines(int lNum) {
		for (int i = 0; i < lNum; i++) {
			// attaches a teller to this line object
			Teller temp = allTellers.get(i);
			allLines.add(new Line(i, temp));
		}
	}

	public void assignCustomers(String assignType) {
		// assigns customers to lines
		
		for (int i = 0; i < allCustomers.size(); i++) {
			Customer temp = allCustomers.get(i);
			
			if (assignType.toUpperCase().equals("SIMPLE")) {
				temp.simpleAssign(allLines);
			} else if (assignType.toUpperCase().equals("TIME")) {
				temp.timeAssign(allLines);
			} else if (assignType.toUpperCase().equals("RANDOM")) {
				temp.randomAssign(allLines);
			}
			allCustomers.remove(i);
			allCustomers.add(i, temp);
		}
	}
        
        
        public void setMaxTime() {
            if (simulationTime > maxSimulationTime) {
                simulationTime = maxSimulationTime;
            } else if (simulationTime < minSimulationTime) {
                simulationTime = minSimulationTime;
            }
        }
        
        //checks if the simulation has hit its time limit
        public void checkTime(long tock) {
            if((long) simulationTime *1000 <= tock) {
                simulationRunning =false;
            }
        }

	public void updateLines() {
		// update each line, checking to see which actions need to be performed
	}
}
