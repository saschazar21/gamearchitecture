package at.fhooe.im440.workbench.services.Messenger;

public interface Subscribeable {

	public void message(Message message);
	public void subscribe();
	public void unsubscribe();

	
}
