package com.vinod.test;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.converter.crypto.PGPDataFormat;
import org.apache.camel.main.Main;

public class EncryptionTest {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		Main main = new Main();
		main.enableHangupSupport();
		main.addRouteBuilder(new MyRouteBuilder());
		main.run(args);

	}

}

class MyRouteBuilder extends RouteBuilder {

	@Override
	public void configure() {

		try {
			System.out.println("My Encryption/Decryption route started");
			// Encryption
			PGPDataFormat encrypt = new PGPDataFormat();
			encrypt.setKeyFileName("vinodpublickeyfile.pgp");
			encrypt.setKeyUserid("vinod");

			from("file:tobeencrypt?noop=true;delete=true").marshal(encrypt).to("file:encrypted");

			// Decryption

			PGPDataFormat decrypt = new PGPDataFormat();
			decrypt.setKeyFileName("vinodprivatekeyfile.pgp");
			decrypt.setKeyUserid("vinod");
			decrypt.setPassword("vinod@123");

			from("file:tobedecrypt").unmarshal(decrypt).to("file:decrypted");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}