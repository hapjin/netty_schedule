package job;

public class CommonJob extends Job {

	private String appPath;

	public CommonJob() {
		// TODO Auto-generated constructor stub
	}

	public CommonJob(String userId, String modelId, String modelName,
			String appPath, String jobConfig) {
		
		super(1, userId, modelId, modelName, jobConfig);
		this.appPath = appPath;
	}
}
