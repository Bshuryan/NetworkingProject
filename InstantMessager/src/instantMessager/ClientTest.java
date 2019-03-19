package instantMessager;

import java.io.IOException;

public class ClientTest {
	
	public static void main(String args[]) throws IOException {
		
		Client testClient = new Client("testU", 6000, "127.0.0.1");
		testClient.run();
		
		
	}

}
