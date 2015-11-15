package at.fhooe.im440.workbench.services.Messenger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import at.fhooe.im440.workbench.services.Service;

public class Messenger implements Service {

	private HashMap<MessageType, ArrayList<Subscribeable>> subscribers = new HashMap<MessageType, ArrayList<Subscribeable>>();
	
	private ArrayList<Message> queue = new ArrayList<Message>();
	
	public void subscribe(Subscribeable subscriber, MessageType messageType) {
		ArrayList<Subscribeable> list = this.subscribers.get(messageType);
		
		if (list != null) {
			list.add(subscriber);	
		} else {
			ArrayList<Subscribeable> temp = new ArrayList<Subscribeable>();
			temp.add(subscriber);
			this.subscribers.put(messageType, temp);
		}
	}
	
	public void subscribe(Subscribeable subscriber, MessageType... messageTypes) {
		for (MessageType messageType : messageTypes) {
			this.subscribe(subscriber, messageType);
		}	
	}
	
	public void unsubscribe(Subscribeable subscriber) {
		for (Map.Entry<MessageType, ArrayList<Subscribeable>> entry : this.subscribers.entrySet()) {
			ArrayList<Subscribeable> list = entry.getValue();
			if (list != null) {
				for (int i = 0; i < list.size(); i++) {
					if(list.get(i).equals(subscriber)) {
						list.remove(i);
					}
				}
			}
		}
	}
	
	public void unsubscribe(Subscribeable subscriber, MessageType... messageTypes) {
		for (MessageType messageType : messageTypes) {
			this.unsubscribe(subscriber, messageType);
		}	
	}
	
	public void unsubscribe(Subscribeable subscriber, MessageType messageType) {
		ArrayList<Subscribeable> list = this.subscribers.get(messageType);
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				if(list.get(i).equals(subscriber)) {
					list.remove(i);
				}
			}
		}
	}
	
	public void fire(Message message) {
		MessageType messageType = message.getType();
		ArrayList<Subscribeable> list = this.subscribers.get(messageType);
		if (list != null) {
			for (Subscribeable e : list) {
				e.message(message);
			}
		}
	}
	
	public void send(Message message) {
		this.queue.add(message);
	}
	
	@Override
	public void update() {
		for (Message message : this.queue) {
			this.fire(message);
		}
		this.queue.clear();
	}

}