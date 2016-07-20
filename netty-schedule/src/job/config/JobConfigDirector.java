package job.config;

public class JobConfigDirector {

	private JobConfigBuilder jobConfig;

	public JobConfigDirector(JobConfigBuilder jobConfig) {
		this.jobConfig = jobConfig;
	}

	public String createJobConfig() {
		return jobConfig.versionConfig().append(jobConfig.clusterConfig())
				.append(jobConfig.customeConfig()).toString();
	}
}
