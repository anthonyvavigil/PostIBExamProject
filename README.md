# PostIBExamProject

# Change notes - 5/25 - Tony

-Teller
	-change timeToProcess
		-now an int for type inconsistencies with transTime
		-called "speed" because that seemed more logical
	-removed String id - redundancy
		
-Customer
	-removed String id - redundancy
	-change timeToProcess
		-now an int for type inconsistencies with transTime
		-called "speed" because that seemed more logical
		
-Line
	-removed String id - redundancy
	
-RunLoop
	
	-added initialization method
                -setMaxTime
	
	
-Miscellaneous
	
	-new RunLoop parameters are, in order (added simulationTime, which addresses the quit issue)
		-assignmentType: String
		-numCustomers: int
		-numLines: int
		-isFixedTellerSpeeds: boolean
		-isFixedCustomerSpeeds: boolean
		-spawnType: String
		-spawnRate: int
                -simulationTime: int
	-Settings options needed are as follows
                -length of simulation desired - jSpinner
                        -send as int
		-type of assignment - random, simple, or time - PARTIALLY IMPLEMENTED
			-if random, send String "RANDOM"
			-if simple, send String "SIMPLE"
			-if time, send String "TIME"
		-number of customers - JSpinner
			-send as int
		-number of lines - JSpinner - ALREADY IMPLEMENTED
			-send as int
		-check-box for whether teller speeds are random or fixed
			-boolean isFixedTellerSpeeds
		-check-box for whether customer speeds are random or fixed
			-boolean isFixedCustomerSpeeds
		-drop-down for whether the customers should all appear at once, come in waves, or randomly enter. If it's randomly enter, need to know how often 		customers should spawn (maybe use a Slider for this, and at the max it'll be like 25% of the ticks, at the minimum like 5%)
			-if all at once, send String "INIT"
			-if waves, send String "WAVES"
			-if random, send String "RAND"
				-send int at end as the percent value that the slider corresponds to
				-if it's one of the other two options, send the slider int as -1
			