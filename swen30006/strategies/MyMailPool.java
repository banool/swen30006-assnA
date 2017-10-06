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
		// Both of the pools start empty.
		// Because nonPriorityPool interfaces from Queue, this can only use
		// Queue methods and as such will act like a Queue.
		nonPriorityPool = new LinkedList<MailItem>();
		// Comparator.comparing will generate a comparator by calling the
		// getPriorityLevel method of PriorityMailItem and comparing the values.
		// This saves us from having to create a whole Comparator class.
		// Fortunately it orders it the right way without having to change anything.
		priorityComparator = Comparator.comparing(PriorityMailItem::getPriorityLevel);
		priorityPool = new PriorityQueue<PriorityMailItem>(priorityComparator);
	}
	
	public int getPriorityPoolSize(){
		return priorityPool.size();
	}

	public int getNonPriorityPoolSize() {
		return nonPriorityPool.size();
	}

	public void addToPool(MailItem mailItem) {
		// Check whether them item is a priority item or not.
		if(mailItem instanceof PriorityMailItem){
			// Add to priority items. The PriorityQueue will keep 
			// it sorted by priority automatically.
			priorityPool.add((PriorityMailItem)mailItem);
		}
		else{
			// Add to nonpriority items. The queue maintains a FIFO system
			// such that items that came in least recently will be taken first.
			nonPriorityPool.add(mailItem);
		}
	}
	
	public MailItem getNonPriorityMail(){
		if(getNonPriorityPoolSize() > 0){
			// This returns the non-pri item that has been waiting longest.
			return nonPriorityPool.poll();
		}
		else{
			return null;
		}
	}
	
	public MailItem getHighestPriorityMail(){
		if(getPriorityPoolSize() > 0){
			// This returns the highest priority item.
			return priorityPool.poll();
		}
		else{
			return null;
		}
		
	}
	
	public MailItem getBestMail(int FloorFrom, int FloorTo) {
		// No thanks! :)
		return null;
	}
	
}

