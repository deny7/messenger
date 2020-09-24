import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import java.util.Properties;

public class JschConnector {
	public static void connectSSH() throws Exception{
		final String id = "opc";
		final String address = "140.238.0.57";
		final int port = 22;
		JSch jsch = new JSch();
		jsch.addIdentity("C:\\Messenger\\w\\key.ppk");
		Session session = jsch.getSession(id, address, port);
		session.setConfig("PreferredAuthentications", "publickey");
		Properties config = new Properties();
		config.put("StrictHostKeyChecking", "no");
		session.setConfig(config);
		session.connect();  //연결 시도
		int forwarding_port = session.setPortForwardingL(5432, "localhost", 5432);
		System.out.println(forwarding_port);
	}
}
