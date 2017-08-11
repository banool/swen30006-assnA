package strategies;

import java.util.Stack;

import automail.MailItem;
import automail.PriorityMailItem;

public class SimpleMailPool implements IMailPool{
	// My first job with Robotic Mailing Solutions Inc.!
	// 2 kinds of items so two structures
	// Remember stack from 1st year - easy to use, not sure if good choice
	private Stack<MailItem> nonPriorityPool;
	private Stack<MailItem> priorityPool;
	
	public SimpleMailPool(){
		// Start empty
		nonPriorityPool = new Stack<MailItem>();
		priorityPool = new Stack<MailItem>();
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
			// Add to priority items
			// Kinda feel like I should be sorting or something
			priorityPool.push(mailItem);
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
			return nonPriorityPool.pop();
		}
		else{
			return null;
		}
	}
	
	public MailItem getHighestPriorityMail(){
		if(getPriorityPoolSize() > 0){
			// How am I supposed to know if this is the highest/earliest?
			return priorityPool.pop();
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
