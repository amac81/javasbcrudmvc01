package pt.bitclinic.javasbcrudmvc01.entities.enums;

public enum ProjectStatus {
	PLANNING(1), IN_PROGRESS(2), ON_HOLD(3), COMPLETED(4), CANCELED(5);

	private Integer code;

	private ProjectStatus(Integer i) {
		this.code = i;
	}

	public int getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public static ProjectStatus valueOf(Integer code) {
		for (ProjectStatus ps : ProjectStatus.values()) {
			if (ps.getCode() == code) {
				return ps;
			}
		}

		throw new IllegalArgumentException("Invalid Project Status Code");
	}
}
