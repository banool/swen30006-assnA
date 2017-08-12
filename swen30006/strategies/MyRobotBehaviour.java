package strategies;
import automail.Clock;
import automail.StorageTube;
import exceptions.TubeFullException;

public class MyRobotBehaviour implements IRobotBehaviour {
	
	private static final int MAX_TAKE = 4;

	@Override
	public boolean fillStorageTube(IMailPool mailPool, StorageTube tube) {
		// Priority items are important, but other mail is also important.
		// It is inefficient to leave with only one mail item.
		// As such, we always take MAX_TAKE items and always deliver them all.
		try{
			// We don't need to empty the mail tube upon returning because we
			// always deliver all MAX_TAKE items on each mail run.
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
	    	// Record that a new priority item has arrived.
		// Beyond that we don't do anything special with this information.
	    	System.out.println("T: " + Clock.Time() + " | Priority arrived");
    }
 
	@Override
	public boolean returnToMailRoom(StorageTube tube) {
		// Only return when the mail tube is empty.
		// Priority items aren't so valuable that we should go back if one arrives
		// and we're not holding one, instead always just deliver all MAX_TAKE items.
		if (tube.getSize() > 0) {
			return false;
		}
		return false;
	}

}
