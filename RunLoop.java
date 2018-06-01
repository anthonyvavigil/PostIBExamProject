
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
	public static final int customerSpeedsLowerBound = 10;

	// Maximum and minimum simulation times
	public static final int minSimulationTime = 10;
	public static final int maxSimulationTime = 1000000000;

	public RunLoop(String assignmentType, int numCustomers, int numLines, boolean isFixedTellerSpeeds,
			boolean isFixedCustomerSpeeds, String spawnType, int spawnRate, int simulationTime) {
		this.assignmentType = assignmentType;
		this.numLines = numLines;
		this.numCustomers = numCustomers;
		this.numLines = numLines;
		this.isFixedTellerSpeeds = isFixedTellerSpeeds;
		this.isFixedCustomerSpeeds = isFixedCustomerSpeeds;
		this.spawnType = spawnType;
		this.spawnRate = spawnRate;
		this.simulationTime = 100000000; // not changeable by user because that'd be stupid
		bugTesting();
	}

	public void bugTesting() {
		this.assignmentType = "SIMPLE";
		this.numLines = 15;
		this.numCustomers = 10000;
		this.isFixedCustomerSpeeds = false;
		this.isFixedTellerSpeeds = false;
		this.simulationTime = 1000000000;
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

	public void runSim() {
		tick = 0;
		initializationMethods();

		// The constructor requires the random generation for teller speed to be
		// done
		// before item generation

		while (simulationRunning) {
			System.out.println("running while");
			/*try {
				Thread.sleep(20);
			} catch (InterruptedException ex) {
				Logger.getLogger(RunLoop.class.getName()).log(Level.SEVERE, null, ex);
			}*/
			tick += 1;
			updateLines();
			checkTime(tick);
		}
		endSimulation();
	}

	/*
	 * extra methods
	 */

	public void initializationMethods() {
		addTellers(numLines, isFixedTellerSpeeds);
		addCustomers(numCustomers, isFixedCustomerSpeeds);
		addLines(numLines);
		assignCustomers(assignmentType); //addCustomersToLineObjects(); // need to be merged into one method
		firstCustomers();

		setMaxTime();
		System.out.println("initialized");
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
			System.out.println("adding line: " + i);
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
			temp.setTransTime(temp.calcTransTime());
			
			allCustomers.add(i, temp);
			addCustomersToLineObjects();
		}
	}

	public void addCustomersToLineObjects() { // does what the method's called
		for (int i = 0; i < allCustomers.size(); i++) {
			Customer temp = allCustomers.get(i);
			Line tempL = temp.getLine();
			if (allLines.indexOf(tempL) >= 0) {
				
				if(!allLines.get(allLines.indexOf(tempL)).customers.contains(temp)) {
					System.out.println("Adding customer: " + temp.getIdInt() + " to line: " + tempL.teller.getIdInt());
					allLines.get(allLines.indexOf(tempL)).addCustomer(temp);
				}
			}
		}
	}

	public void setMaxTime() {
		if (simulationTime > maxSimulationTime) {
			simulationTime = maxSimulationTime;
		} else if (simulationTime < minSimulationTime) {
			simulationTime = minSimulationTime;
		}
	}

	// checks if the simulation has hit its time limit
	public void checkTime(long tock) {
		if ((long) simulationTime * 1000 <= tock) {
			simulationRunning = false;
		}
	}

	public void firstCustomers() {
		for (int i = 0; i < allLines.size(); i++) {
			if (!allLines.get(i).customers.isEmpty()) {
				allLines.get(i).setCurrentCustomer(0);
				allLines.get(i).currentCustomer.setTickStart(0);
				allLines.get(i).getCustomerQueue().get(0).setTickStart(0);
			}
		}
	}
	
	public void endSimulation() {
		
	}

	public void updateLines() {
		// update each line, checking to see which actions need to be performed
		int numLinesFinished = 0;
		for (int i = 0; i < allLines.size(); i++) {
			Line tempL = allLines.get(i);
			Teller tempT = tempL.getTeller();
			Customer tempC = tempL.getCurrentCustomer();
			System.out.println("processing customer: " + tempC.getIdInt());

			int custInd = tempL.getCustomerQueue().indexOf(tempC);
			System.out.println("current tick: " + tick);
			if(tick >= simulationTime) { // if out of time
				simulationRunning = false;
			}			
			else if(!tempL.hasNextCustomer()){ // if at end of line
				numLinesFinished++;
				System.out.println("at end of line: " + (tempL.getIdInt()+1));
			}
			else if (tick - tempC.getTickStart() >= tempC.getTransTime()) { // if the user is done being in the line, switch to the next user
				allLines.get(i).getCurrentCustomer().setTickEnd(tick);
				if (allLines.get(i).getCustomerQueue().indexOf(tempC) < allLines.get(i).getCustomerQueue().size() - 1) { // if there's another customer to get
					allLines.get(i).nextCustomer();
					allLines.get(i).getCurrentCustomer().setTickStart(tick);
					allLines.get(i).getCustomerQueue()
							.get(allLines.get(i).getCustomerQueue().indexOf(allLines.get(i).getCurrentCustomer()))
							.setTickStart(tick);
				}
			}
		}
		if(numLinesFinished >= numLines) {
			simulationRunning = false;
		}
	}
}
