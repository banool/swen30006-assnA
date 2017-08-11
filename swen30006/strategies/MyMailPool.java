package strategies;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

import automail.MailItem;
import automail.PriorityMailItem;

public class MyMailPool implements IMailPool{
	// My first job with Robotic Mailing Solutions Inc.!
	// 2 kinds of items so two structures.
	private Queue<MailItem> nonPriorityPool;
	private Comparator<PriorityMailItem> priorityComparator;
	private PriorityQueue<PriorityMailItem> priorityPool;
	
	public MyMailPool(){
		// Start empty
		nonPriorityPool = new LinkedList<MailItem>();  // TODO explain / understand
		priorityComparator = Comparator.comparing(PriorityMailItem::getPriorityLevel);
		// TODO REMOVE priorityComparator = new PriorityMailItemComparator();
		priorityPool = new PriorityQueue<PriorityMailItem>(priorityComparator);
	}
	
	public int getPriorityPoolSize(){
		// This is easy
		return priorityPool.size();
	}

	public int getNonPriorityPoolSize() {
		// So is this
		return nonPriorityPool.size();
	}

	public void addToPool(MailItem mailItem) {
		// Check whether it has a priority or not
		if(mailItem instanceof PriorityMailItem){
			// Add to priority items.
			// The PriorityQueue will keep it sorted by priority automatically.
			priorityPool.add((PriorityMailItem)mailItem);
		}
		else{
			// Add to nonpriority items
			// Maybe I need to sort here as well? Bit confused now
			nonPriorityPool.add(mailItem);
		}
	}
	
	public MailItem getNonPriorityMail(){
		if(getNonPriorityPoolSize() > 0){
			// How am I supposed to know if this is the earliest?
			return nonPriorityPool.poll();
		}
		else{
			return null;
		}
	}
	
	public MailItem getHighestPriorityMail(){
		if(getPriorityPoolSize() > 0){
			// How am I supposed to know if this is the highest/earliest?
			return priorityPool.poll();
		}
		else{
			return null;
		}
		
	}
	
	public MailItem getBestMail(int FloorFrom, int FloorTo) {
		// Are you kidding me!!
		return null;
	}
	
	// Never really wanted to be a programmer any way ...
	
}

// TODO destroy this code.

class PriorityMailItemComparator implements Comparator<PriorityMailItem>
{
	// Since we're not allowed to modify PriorityMailItem...
	public int compare(PriorityMailItem m1, PriorityMailItem m2)
    {
        if (m1.getPriorityLevel() > m2.getPriorityLevel()) {
        		return 1;
        } else if (m1.getPriorityLevel() < m2.getPriorityLevel()) {
        		return -1;
        } else {
        		return 0;
        }
    }
}

