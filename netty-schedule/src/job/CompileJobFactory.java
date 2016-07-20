package job;

import util.ParseJsonStr;
import net.sf.json.JSONObject;
import job.config.CompileJobConfig;
import job.config.JobConfigDirector;

public class CompileJobFactory implements JobFactory {

	//prevents instantiation
	private CompileJobFactory() {
		
	}
	
	private static volatile CompileJobFactory compileJobFactory;
	
	public static CompileJobFactory createInstance(){
		if(compileJobFactory == null){
			synchronized (CompileJobFactory.class) {
				if(compileJobFactory == null)
					compileJobFactory = new CompileJobFactory();
			}
		}
		return compileJobFactory;
	}
	
	
	@Override
	public Job createJob(String jsonRequest) {

		JSONObject jsonObj = ParseJsonStr.createJsonObj(jsonRequest);
		
		// parse Httprequest
		String userId = jsonObj.getString("userId");
		String modelId = jsonObj.getString("modelId");
		String modelName = jsonObj.getString("modelName");
		String kjbHdfsPath = jsonObj.getString("kjbHdfsPath");

		JobConfigDirector director = new JobConfigDirector(
				new CompileJobConfig(kjbHdfsPath));
		String jobConfig = director.createJobConfig();

		return new CompileJob(userId, modelId, modelName, kjbHdfsPath,
				jobConfig);
	}

}
