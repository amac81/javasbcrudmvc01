package pt.bitclinic.javasbcrudmvc01.entities.enums;

public enum Status {
	PLANNING(1), IN_PROGRESS(2), ON_HOLD(3), COMPLETED(4), CANCELED(5);

	private Integer code;
	
	private Status(Integer i) {
		this.code = i;
	}

	public int getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public static Status valueOf(Integer code) {
		for (Status ps : Status.values()) {
			if (ps.getCode() == code) {
				return ps;
			}
		}

		throw new IllegalArgumentException("Invalid Status Code");
	}

	
}
