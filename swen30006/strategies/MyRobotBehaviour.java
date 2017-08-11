package strategies;
import automail.Clock;
import automail.PriorityMailItem;
import automail.StorageTube;
import exceptions.TubeFullException;

public class MyRobotBehaviour implements IRobotBehaviour {
	
	// Why do we need a MAX_TAKE if tube has a isFull method?
	private static final int MAX_TAKE = 4;
	private boolean newPriority; // Used if we are notified that a priority item has arrived. 
		
	public MyRobotBehaviour() {
		newPriority = false;
	}

	@Override
	public boolean fillStorageTube(IMailPool mailPool, StorageTube tube) {
		// Priority items are important, but other mail is also important.
		// It is inefficient to leave with only one mail item.
		// As such, we always take MAX_TAKE items.
		try{
			newPriority = false;
			// TODO what if we always just delivered all 4 items?
			// Currently we return if we don't have a priority item and a new one comes in.
			// TODO even then, you could get rid of this. But as it is currently I think keep it.
			while(!tube.isEmpty()) {
				mailPool.addToPool(tube.pop());
			}
			while (tube.getSize() < MAX_TAKE) {
				// Try to take a priority item first.
				if (mailPool.getPriorityPoolSize() > 0) {
					tube.addItem(mailPool.getHighestPriorityMail());
				// Otherwise try to take a regular item.
				} else if (mailPool.getNonPriorityPoolSize() > 0) {
					tube.addItem(mailPool.getNonPriorityMail());
				// If the tube is full we break and return.
				} else {
					break;
				}
			}
		}
		catch(TubeFullException e){
			e.printStackTrace();
		}
		return (tube.getSize() > 0);
	}
	
	@Override
    public void priorityArrival(int priority) {
	    	// Record that a new one has arrived.
	    	newPriority = true;
	    	System.out.println("T: " + Clock.Time() + " | Priority arrived");
    }
 
	@Override
	public boolean returnToMailRoom(StorageTube tube) {
		// Only return if we don't have a priority item and a new one came in.
		if (tube.getSize() > 0) {
			Boolean priority = (tube.peek() instanceof PriorityMailItem);
			return !priority && newPriority;
		}
		else {
			return false;
		}
	}

}
