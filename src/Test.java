import java.util.Properties;

public class Test {
	public static void main(String[] args) {
		Properties props = System.getProperties(); // 获得系统属性集
		String osName = props.getProperty("os.name"); // 操作系统名称
		System.out.println(osName);
	}
}
