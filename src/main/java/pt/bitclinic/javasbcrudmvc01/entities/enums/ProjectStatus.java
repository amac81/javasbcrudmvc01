package pt.bitclinic.javasbcrudmvc01.entities.enums;

public enum ProjectStatus {
	PLANNING(1), IN_PROGRESS(2), ON_HOLD(3), COMPLETED(4), CANCELED(5);

	private Integer code;

	private ProjectStatus(int i) {
		this.code = i;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public static ProjectStatus valueOf(Integer code) {
		for (ProjectStatus os : ProjectStatus.values()) {
			if (os.getCode() == code) {
				return os;
			}
		}

		throw new IllegalArgumentException("Invalid Project Status Code");
	}
}
