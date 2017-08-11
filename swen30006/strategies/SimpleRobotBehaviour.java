package strategies;
import automail.Clock;
import automail.PriorityMailItem;
import automail.StorageTube;
import exceptions.TubeFullException;

public class SimpleRobotBehaviour implements IRobotBehaviour {
	
	private static final int MAX_TAKE = 4;
	private boolean newPriority; // Used if we are notified that a priority item has arrived. 
		
	public SimpleRobotBehaviour() {
		newPriority = false;
	}

	@Override
	public boolean fillStorageTube(IMailPool mailPool, StorageTube tube) {
		// Priority items are important;
		// if there are some, grab one and go, otherwise take as many items as we can and go
		try{
			// Start afresh
			newPriority = false;
			while(!tube.isEmpty()) {
				mailPool.addToPool(tube.pop());
			}
			// Check for a top priority item
			if (mailPool.getPriorityPoolSize() > 0) {
				// Add priority mail item
				tube.addItem(mailPool.getHighestPriorityMail());
				// Go deliver that item
				return true;
			}
			else{
				// Get as many nonpriority items as available or as fit
				while(tube.getSize() < MAX_TAKE && mailPool.getNonPriorityPoolSize() > 0) {
					tube.addItem(mailPool.getNonPriorityMail());
				}
				return (tube.getSize() > 0);
			}
		}
		catch(TubeFullException e){
			e.printStackTrace();
		}
		return false;
	}
	
	@Override
    public void priorityArrival(int priority) {
    	// Record that a new one has arrived
    	newPriority = true;
    	System.out.println("T: "+Clock.Time()+" | Priority arrived");
    }
 
	@Override
	public boolean returnToMailRoom(StorageTube tube) {
		// Only return if we don't have a priority item and a new one came in
		if (tube.getSize() > 0) {
			Boolean priority = (tube.peek() instanceof PriorityMailItem);
			return !priority && newPriority;
		}
		else {
			return false;
		}
	}
	
	

}
