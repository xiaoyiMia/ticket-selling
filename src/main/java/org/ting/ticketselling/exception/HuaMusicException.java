package org.ting.ticketselling.exception;

public abstract class HuaMusicException extends RuntimeException {

	private static final long serialVersionUID = -6461781799632133716L;

	private ExceptionLevel level;
	
	public HuaMusicException(ExceptionLevel level) {
		super();
		this.level = level;
	}

	public ExceptionLevel getLevel() {
		return level;
	}

	public void setLevel(ExceptionLevel level) {
		this.level = level;
	}
	
}
