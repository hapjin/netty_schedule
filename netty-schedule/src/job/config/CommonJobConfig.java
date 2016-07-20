package job.config;

public class CommonJobConfig extends JobConfigBuilder{

	private String appPath;
	
	public CommonJobConfig(String appPath) {
		this.appPath = appPath;
	}
	
	@Override
	public StringBuilder customeConfig() {
		StringBuilder sb = new StringBuilder();
		sb.append(      "<property>");
		sb.append(           "<name>oozie.wf.application.path</name>");
		sb.append(          "<value>" + appPath + "</value>");
		sb.append(      "</property>");
		
		sb.append(      "<property>");
		sb.append(           "<name>oozie.use.system.libpath</name>");
		sb.append(          "<value>" + "true" + "</value>");
		sb.append(      "</property>");
		
		sb.append("</configuration>");
		
		return sb;
	}
}
