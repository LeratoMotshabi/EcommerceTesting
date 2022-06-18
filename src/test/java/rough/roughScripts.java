package rough;

import org.apache.commons.codec.binary.Base64;

public class roughScripts {

	public static void main(String[] args) {
		
			
			String password = "Lemo5472081";
			byte[]  decodedBytes = Base64.encodeBase64(password.getBytes());
			System.out.println(" decodedBytes "+ new String(decodedBytes));

			byte[] encodedBytes = Base64.decodeBase64(decodedBytes);
			System.out.println("encodedBytes "+ new String(encodedBytes));

			
			
		

	}

}
