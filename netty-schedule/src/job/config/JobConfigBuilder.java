package job.config;

public abstract class JobConfigBuilder {

	private static final String USER_NAME = "cdhfive";
	private static final String JOB_TRACKER = "datanode1:8032";
	private static final String NAME_NODE = "hdfs://datanode1:8020";
	
	//环境配置
	public StringBuilder versionConfig(){
		StringBuilder sb = new StringBuilder();
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		sb.append("    <configuration>");
		return sb;
	}
	
	//集群配置
	public StringBuilder clusterConfig(){
		StringBuilder sb = new StringBuilder();
		
		sb.append("        <property>");
		sb.append("            <name>" + "user.name" + "</name>");
		sb.append("            <value>" + USER_NAME + "</value>");
		sb.append("        </property>");
		
		sb.append(      "<property>");
		sb.append(          "<name>jobTracker</name>");
		sb.append(          "<value>" + JOB_TRACKER + "</value>");
		sb.append(      "</property>");
		sb.append(      "<property>");
		sb.append(          "<name>nameNode</name>");
		sb.append(          "<value>" + NAME_NODE + "</value>");
		sb.append(      "</property>");
		
		return sb;
	}
	
	//自定义配置
	public abstract StringBuilder customeConfig();
}
