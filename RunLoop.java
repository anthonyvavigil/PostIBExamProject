
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;
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
			boolean isFixedCustomerSpeeds, String spawnType, int spawnRate, int simulationTime, int numTellers) {
		this.assignmentType = assignmentType;
		this.numLines = numLines;
		this.numCustomers = numCustomers; // total number of customers that will be spawned
		this.numLines = numLines;
		this.isFixedTellerSpeeds = isFixedTellerSpeeds;
		this.isFixedCustomerSpeeds = isFixedCustomerSpeeds;
		this.spawnType = spawnType;
		this.spawnRate = spawnRate;
		this.simulationTime = 100000000; // not changeable by user because
											// that'd be stupid
		this.numTellers = numTellers;
		bugTesting();
                initializeArrays();
	}

	public void bugTesting() {
		this.assignmentType = "RANDOM";
		this.numLines = 3;
		this.numCustomers = 100;
		this.isFixedCustomerSpeeds = false;
		this.isFixedTellerSpeeds = false;
		this.simulationTime = 1000000000;
		this.numTellers = 3;
		this.spawnRate = 1;
		this.spawnType = "RAND";
	}
        
        public void initializeArrays() {
            startTick = new long[numCustomers];
            endTick = new long[numCustomers];
            tickTime = new long[numCustomers];
            
        }

    public int customersInLine = 0;
	private long tick;
	private String assignmentType;
	private int numCustomers;
	private int numLines;
	private int numTellers;
	private boolean isFixedTellerSpeeds;
	private boolean isFixedCustomerSpeeds;
	private String spawnType;
	private int spawnRate;
	private int simulationTime;
	private boolean simulationRunning = true;
	public ArrayList<Teller> allTellers = new ArrayList();
	public ArrayList<Customer> allCustomers = new ArrayList();
	public ArrayList<Line> allLines = new ArrayList();
        private long[] startTick;
        private long[] endTick;
        private long[] tickTime;
        private int cCount = 0;
        private int cDone = 0;
        private double percentCutoff = .05;

	public void runSim() {
		tick = 0;
		initializationMethods();

		// The constructor requires the random generation for teller speed to be
		// done
		// before item generation

		while (simulationRunning) {
			//System.out.println("running while");
			/*
			 * try { Thread.sleep(20); } catch (InterruptedException ex) {
			 * Logger.getLogger(RunLoop.class.getName()).log(Level.SEVERE, null,
			 * ex); }
			 */
			tick += 1;
			updateLines();
			checkTime(tick);
		}
		endSimulation();
                
                System.out.println("CCount: " + cCount);
	}

	/*
	 * extra methods
	 */

	public void initializationMethods() {
		addLines(numLines);
		addTellers(numTellers, isFixedTellerSpeeds);
		assignTellers();
		addCustomers(numCustomers, isFixedCustomerSpeeds);
		assignCustomersWhileRunning(0, (int) (numCustomers*15)/100, assignmentType); // adds 15% of the customers, the rest will be added later
		firstCustomers();

		setMaxTime();
		//System.out.println("initialized");
	}

	// initialize objects

	// assigns tellers to line objects
	public void assignTellers() {
		int count = 0;
		while (count < allTellers.size()) {
			int lineindex = count % allLines.size();
			allLines.get(lineindex).addTeller(allTellers.get(count));
			//System.out.println("assigning teller " + allTellers.get(count).getIdInt() + " to line "
					//+ allLines.get(lineindex).idInt);
			count++;
		}
	}

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
				temp = new Customer(cCount, customerSpeedFixed);
                                cCount++;
			} else {
				Random r = new Random();
				int tSp = r.nextInt((customerSpeedsUpperBound - customerSpeedsLowerBound)) + customerSpeedsLowerBound;
				temp = new Customer(cCount, tSp);
                                cCount++;
			}
			allCustomers.add(temp);
		}
	}

	// adds an empty line for each number of lines
	public void addLines(int lNum) {
		for (int i = 0; i < lNum; i++) {
			// attaches a teller to this line object
			allLines.add(new Line(i));
			//System.out.println("adding line: " + i);
		}
	}

	public void assignCustomers(String assignType) {
		// assigns customers to lines

		for (int i = 0; i < allCustomers.size(); i++) {
			Customer temp = allCustomers.get(i);

			if (assignType.toUpperCase().equals("SIMPLE")) {
				temp.simpleAssign(allLines);
                                startTick[i] = tick;
			} /*
				 * else if (assignType.toUpperCase().equals("TIME")) {
				 * temp.timeAssign(allLines); }
				 */else if (assignType.toUpperCase().equals("RANDOM")) {
				temp.randomAssign(allLines);
                                startTick[i] = tick;
                                
			}
			allCustomers.remove(i);
			// temp.setTransTime(temp.calcTransTime());

			allCustomers.add(i, temp);
			addCustomersToLineObjects();
			customersInLine++;
		}
	}
	public void assignCustomersWhileRunning(int lowerInd, int upperInd, String assignType) {
		// assigns customers to lines
		//System.out.println(lowerInd + ", " + upperInd);
		for (int i = lowerInd; i < upperInd; i++) {
			Customer temp = allCustomers.get(i);

			if (assignType.toUpperCase().equals("SIMPLE")) {
				temp.simpleAssign(allLines);
                                startTick[temp.getIdInt()] = tick;
			} /*
				 * else if (assignType.toUpperCase().equals("TIME")) {
				 * temp.timeAssign(allLines); }
				 */else if (assignType.toUpperCase().equals("RANDOM")) {
				temp.randomAssign(allLines);
                                startTick[temp.getIdInt()] = tick;
			}
			allCustomers.remove(i);
			// temp.setTransTime(temp.calcTransTime());

			allCustomers.add(i, temp);
			addCustomersToLineObjects();
			customersInLine++;
		}
	}


	public void addCustomersToLineObjects() { // does what the method's called
		for (int i = 0; i < allCustomers.size(); i++) {
			Customer temp = allCustomers.get(i);
			Line tempL = temp.getLine();
			if (allLines.indexOf(tempL) >= 0) {

				if (!allLines.get(allLines.indexOf(tempL)).customers.contains(temp)) {
					//System.out.println("Adding customer: " + temp.getIdInt() + " to line: " + tempL.getIdInt());
					allLines.get(allLines.indexOf(tempL)).addCustomer(temp);
				}
			}
		}
	}
	
	public void addNewCustomersToLineOjbects(int lowerInd, int upperInd) {
		for (int i = lowerInd; i < upperInd; i++) {
			Customer temp = allCustomers.get(i);
			Line tempL = temp.getLine();
			if (allLines.indexOf(tempL) >= 0) {

				if (!allLines.get(allLines.indexOf(tempL)).customers.contains(temp)) {
					System.out.println("Adding customer: " + temp.getIdInt() + " to line: " + tempL.getIdInt());
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
		int count = 0;
		for (int i = 0; i < allLines.size(); i++) {
			if (!allLines.get(i).customers.isEmpty()) {
				for (int j = 0; j < allLines.get(i).getTellers().size(); j++) { // loop through all tellers for that line
					allLines.get(i).getTellers().get(j).setCurrentCustomer(count, allCustomers);
					allLines.get(i).getTellers().get(j).currentCustomer.setTickStart(0);
					allLines.get(i).getTellers().get(j).currentCustomer.calcTransTime(allLines.get(i).getTellers().get(j));
					allLines.get(i).getCustomerQueue().get(0).setTickStart(0);
					allLines.get(i).setIndex(allLines.get(i).getIndex()+1); // holds spot in customer array list
					count++;
				}
			}
		}
	}

	public void endSimulation() {
            WriteFile writer = new WriteFile("output10.txt", true);
		for(int i = 0; i < numCustomers; i++) {
                    tickTime[i] = endTick[i] - startTick[i];
                    System.out.println("Customer " + i + " started at: " + startTick[i]);
                    System.out.println("Customer " + i + " ended at: " + endTick[i]);
                    
                    if(tickTime[i] >= 0) {
                    writer.write(Long.toString(tickTime[i]));
                    //System.out.println("Customer " + i + " started at: " + startTick[i]);
                    //System.out.println("Customer " + i + " ended at: " + endTick[i]);
                    
                    
                    }
                
		}
                writer.write("----------------END-OF-DATA----------------");
                System.out.println("cDone: " + cDone);
	}
	
	public void dynamicCustomers() {
		if(!spawnType.toUpperCase().equals("INIT")) {
			if(spawnType.toUpperCase().equals("WAVES")) {
				if((cDone / numCustomers) >= percentCutoff) { // accesses after every 5% of customers are processed
					// adds 5% of the customers to the line
                                        percentCutoff += .05;
					int a = customersInLine;
					if((((int) (numCustomers*5)/100) + customersInLine) >= numCustomers-1){
						assignCustomersWhileRunning(customersInLine, customersInLine+(numCustomers-customersInLine), assignmentType);
					} else {
						assignCustomersWhileRunning(customersInLine, (((int) (numCustomers*5)/100) + customersInLine), assignmentType); 
					}
					int b = customersInLine;
					addNewCustomersToLineOjbects(b-a, b);
					//System.out.println("added customers while running");
				}
			} else if (spawnType.toUpperCase().equals("RAND")) {
				Random r = new Random();
				int tempR = r.nextInt(spawnRate)+1;
				if(tick%(100/tempR) == 0) { // accesses every 100/temporary random int ticks
					if(customersInLine < numCustomers) {
						int a = customersInLine;
						assignCustomersWhileRunning(customersInLine, customersInLine + 1, assignmentType);
						int b = customersInLine;
						addNewCustomersToLineOjbects(b-a, b);
						//System.out.println("added customers while running");
					} else {
						simulationRunning = false;
					}
				}
			} else {
				//System.out.println("Could not find spawn type");
			}
		}
	}

	public void updateLines() {
		// update each line, checking to see which actions need to be performed
		dynamicCustomers();
		int numLinesFinished = 0;
		//System.out.println("current tick: " + tick);
		//System.out.println("cust in line: " + customersInLine);
		//System.out.println("num cust: " + numCustomers);
		for (int i = 0; i < allLines.size(); i++) {
			Line tempL = allLines.get(i);

			if (tick >= simulationTime) { // if out of time
				simulationRunning = false;
			} else if (tempL.index >= tempL.customers.size()) { // if at end of line
				numLinesFinished++;
			} else {
			for(int j = 0; j < tempL.getTellers().size(); j++) {
				Teller tempT = allLines.get(i).getTellers().get(j);
				Customer tempC = tempT.currentCustomer;
				if (tick - tempC.getTickStart() >= tempC.getTransTime()) { // if the user at this teller is done being in line switch to the next user
                                    endTick[tempC.getIdInt()] = tick;
                                    cDone++;
                                    System.out.println("0000 Customer: " + tempC.getIdInt() + " processed. 0000");
                                    allLines.get(i).getTellers().get(j).currentCustomer.setTickEnd(tick);
						
					if (allLines.get(i).getCustomerQueue().indexOf(tempC) < allLines.get(i).getCustomerQueue().size() - 1) { // if there's another user to get
							allLines.get(i).getTellers().get(j).setCurrentCustomer(allLines.get(i).getIndex(), allCustomers); // sets customer at index that the line is at
							allLines.get(i).getTellers().get(j).currentCustomer.setTickStart(tick); // sets that customer's tick
							allLines.get(i).getTellers().get(j).currentCustomer.calcTransTime(allLines.get(i).getTellers().get(j)); // calculates time for that customer
							allLines.get(i).setIndex(allLines.get(i).getIndex() + 1); // updates index
					}
				}
			}
		}
		}
		
		if (numLinesFinished >= numLines && customersInLine >= numCustomers-1) {
			simulationRunning = false;
		}                     
	}
}

