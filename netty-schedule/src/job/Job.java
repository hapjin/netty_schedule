package job;

public abstract class Job {
	protected int type;
	protected String userId;
	protected String modelId;
	protected String modelName;
	
	protected String jobConfig;
	
	
	public Job() {
		
	}
	
	public Job(int type, String userId, String mdoelId, String modelName, String jobConfig){
		this.type = type;
		this.userId = userId;
		this.modelId = mdoelId;
		this.modelName = modelName;
		this.jobConfig = jobConfig;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getModelId() {
		return modelId;
	}

	public void setModelId(String modelId) {
		this.modelId = modelId;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public String getJobConfig() {
		return jobConfig;
	}

	public void setJobConfig(String jobConfig) {
		this.jobConfig = jobConfig;
	}
	
	@Override
	public String toString(){
		return modelName;
	}
}
