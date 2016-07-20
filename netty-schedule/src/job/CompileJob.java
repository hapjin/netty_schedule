package job;

public class CompileJob extends Job{
	String kjbHdfsPath;
	
	public CompileJob() {
		
	}
	
	public CompileJob(String userId, String modelId,
			String modelName, String kjbHdfsPath, String jobConfig){
		super(0, userId, modelId, modelName, jobConfig);
		this.kjbHdfsPath = kjbHdfsPath;
	}
}
