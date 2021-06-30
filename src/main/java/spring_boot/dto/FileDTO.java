package spring_boot.dto;

public class FileDTO {
	private String filename;

	public FileDTO(String filename) {
		super();
		this.filename = filename;
	}

	public FileDTO() {
		super();
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}
}
