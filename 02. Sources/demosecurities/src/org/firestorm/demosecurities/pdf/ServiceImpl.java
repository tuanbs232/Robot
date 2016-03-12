package org.firestorm.demosecurities.pdf;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.rmi.RemoteException;

import javax.activation.DataHandler;

import org.apache.axis2.AxisFault;
import org.apache.log4j.Logger;
import org.firestorm.demosecurities.utils.FileUtil;
import org.firestorm.demosecurities.utils.SSLHandler;
import org.signserver.clientws.ClientWSServiceStub;
import org.signserver.clientws.ClientWSServiceStub.DataResponse;
import org.signserver.clientws.ClientWSServiceStub.Sign;
import org.signserver.clientws.ClientWSServiceStub.SignData;
import org.signserver.clientws.ClientWSServiceStub.SignE;
import org.signserver.clientws.ClientWSServiceStub.SignResponse;
import org.signserver.clientws.ClientWSServiceStub.SignResponseE;
import org.signserver.clientws.ClientWSServiceStub.ValidationDataResponse;
import org.signserver.clientws.ClientWSServiceStub.Verify;
import org.signserver.clientws.ClientWSServiceStub.VerifyE;
import org.signserver.clientws.ClientWSServiceStub.VerifyResponse;
import org.signserver.clientws.ClientWSServiceStub.VerifyResponseE;
import org.signserver.clientws.InternalServerExceptionException;
import org.signserver.clientws.RequestFailedExceptionException;

public class ServiceImpl {
	private static final String ENPOINT = "https://10.2.32.116:83/signserver/"
			+ "ClientWSService/ClientWS?wsdl";
	private static final String PDF_SIGNER = "4";
	private static final String PDF_VALIDATOR = "5";
	
	private static final Logger LOG = Logger.getLogger(ServiceImpl.class);
	
	public static void main(String[] args) {
		String inputPath = "S:/WORK/2016/03-2016/03001+0106538422+TVAN_BKAV_00000008-sign1.xml";
		String pathname = "S:/WORK/2016/03-2016/signed.xml";
		try {
			byte[] input = FileUtil.readBytesFromFile(inputPath);
			FileUtil.writeToFile(sign(input), pathname);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Ham goi service cua SignServer de kiem tra chu ky tren giao dich tu
	 * client
	 * 
	 * @param clientSigned
	 *            Du lieu giao dich gui tu clint
	 * @return Integer
	 */
	public static int verify(byte[] clientSigned) {
		if (clientSigned == null) {
			return 406;
		}

		try {
			SSLHandler ssl = new SSLHandler();
			ssl.setSSL();
			ClientWSServiceStub stub = new ClientWSServiceStub(ENPOINT);

			Verify verify = new Verify();
			verify.setData(
					new DataHandler(clientSigned, "application/octet-stream"));
			verify.setWorker(PDF_VALIDATOR);

			VerifyE verifyE = new VerifyE();
			verifyE.setVerify(verify);

			VerifyResponseE responseE = stub.verify(verifyE);
			VerifyResponse response = responseE.getVerifyResponse();
			ValidationDataResponse dataResponse = response.get_return();
			return dataResponse.getValidateCode();
		} catch (AxisFault e) {
			LOG.error("AxisFault: " + e.getMessage());
		} catch (RemoteException e) {
			LOG.error("RemoteException: " + e.getMessage());
		} catch (InternalServerExceptionException e) {
			LOG.error("InternalServerExceptionException: " + e.getMessage());
		} catch (RequestFailedExceptionException e) {
			LOG.error("RequestFailedExceptionException: " + e.getMessage());
		}

		return 404;
	}

	/**
	 * Ham goi service SignServer de ky xac nhan len giao dich sau khi kiem tra
	 * thanh cong
	 * 
	 * @param clientSigned
	 *            Du lieu giao dich tu client
	 * @return mang bytes du lieu da ky
	 */
	public static byte[] sign(byte[] clientSigned) {
		try {
			SSLHandler ssl = new SSLHandler();
			ssl.setSSL();
			ClientWSServiceStub stub = new ClientWSServiceStub(ENPOINT);

			SignData signData = new SignData();
			signData.setDataToSign(
					new DataHandler(clientSigned, "application/octet-stream"));
			signData.setWorkerName(PDF_SIGNER);
			
			//Test for Tvan
			//---------------------------------------
			signData.setReference("NDUNG_CTU");
			signData.setNodeContainSignature("CHUNGTU");
			//---------------------------------------
			
			Sign sign = new Sign();
			sign.setSigndata(signData);
			
			

			SignE signE = new SignE();
			signE.setSign(sign);

			SignResponseE responseE = stub.sign(signE);
			SignResponse response = responseE.getSignResponse();
			DataResponse dataResponse = response.get_return();

			ByteArrayOutputStream outStream = new ByteArrayOutputStream();
			dataResponse.getData().writeTo(outStream);

			byte[] signed = outStream.toByteArray();

			return signed;
		} catch (AxisFault e) {
			LOG.error("AxisFault: " + e.getMessage());
		} catch (RemoteException e) {
			LOG.error("RemoteException: " + e.getMessage());
		} catch (InternalServerExceptionException e) {
			LOG.error("InternalServerExceptionException: " + e.getMessage());
		} catch (RequestFailedExceptionException e) {
			LOG.error("RequestFailedExceptionException: " + e.getMessage());
		} catch (IOException e) {
			LOG.error("IOException: " + e.getMessage());
		}

		return null;
	}
}
