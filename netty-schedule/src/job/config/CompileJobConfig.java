package job.config;

public class CompileJobConfig extends JobConfigBuilder{

	private String kjbHdfsPath;
	private static final String EXPLAIN_PATH = "hdfs://datanode1:8020/user/cdhfive/Yun/ModelExplain";
	
	public CompileJobConfig(String kjbHdfsPath) {
		this.kjbHdfsPath = kjbHdfsPath;
	}
	
	@Override
	public StringBuilder customeConfig() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("        <property>");
		sb.append("            <name>" + "oozie.wf.application.path" + "</name>");
		sb.append("            <value>" + EXPLAIN_PATH + "</value>");
		sb.append("        </property>");
		
		sb.append("        <property>");
		sb.append("            <name>" + "kjbHdfsPath" + "</name>");
		sb.append("            <value>" + kjbHdfsPath + "</value>");
		sb.append("        </property>");
		sb.append("    </configuration>");
		
		return sb;
	}

}
