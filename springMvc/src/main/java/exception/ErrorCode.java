package exception;

public enum ErrorCode {
	 ID_EXISTENCE(1, "ID ALREADY EXISTS"),
	TASKS_NULL(2, "TASKS CANNOT BE NULL OR EMPTY"),
	STATUS_NULL(3, "STATUS CANNOT BE NULL OR EMPTY"),
	DUE_DATE(4, "DUE_DATE CANNOT BE NULL OR EMPTY"),
	ID_NOT_FOUND(204, "ID DOESNT EXIST"),
    INTERNAL_SERVER_ERROR(504, "INTERNAL SERVER ERROR");
	
	private final String error;

    ErrorCode (int errorCode, String error) {
        this.error = error;
    }

    public String getMessage() {
        return error;
    }
}
