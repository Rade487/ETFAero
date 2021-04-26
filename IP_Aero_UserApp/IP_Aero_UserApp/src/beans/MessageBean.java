package beans;

import java.io.Serializable;

import dao.MessageDAO;
import dto.Message;

public class MessageBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8830884385551106098L;
	Message message = new Message();
	
	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public boolean add() {
		return MessageDAO.insert(message);
	}
}
