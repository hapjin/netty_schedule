package job;

import job.config.CommonJobConfig;
import job.config.JobConfigDirector;

public class CommonJobFactory implements JobFactory {

	private static volatile CommonJobFactory commonJobFactory;
	
	private CommonJobFactory() {
		// TODO Auto-generated constructor stub
	}
	
	public static CommonJobFactory createInstance(){
		if(commonJobFactory == null){
			synchronized (CommonJobFactory.class) {
				if(commonJobFactory == null)
					commonJobFactory = new CommonJobFactory();
			}
		}
		return commonJobFactory;
	}
	
	
	@Override
	public Job createJob(String jsonRequest) {

		// parse Httprequest
		String userId = null;
		String modelId = null;
		String modelName = null;
		String appPath = null;

		JobConfigDirector director = new JobConfigDirector(new CommonJobConfig(
				appPath));
		String jobConfig = director.createJobConfig();

		return new CommonJob(userId, modelId, modelName, appPath, jobConfig);
	}
}
