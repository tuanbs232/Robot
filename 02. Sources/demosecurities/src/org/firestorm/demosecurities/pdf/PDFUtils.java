package org.firestorm.demosecurities.pdf;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.apache.log4j.Logger;
import org.firestorm.demosecurities.database.TransactionLog;
import org.firestorm.demosecurities.database.TransactionLogDAO;
import org.firestorm.demosecurities.database.User;
import org.firestorm.demosecurities.utils.FileUtil;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;

public class PDFUtils {
	private final static Logger LOG = Logger.getLogger(PDFUtils.class);
	private static final String TRANSATION_LOG_DIR = "C:/BkavCA/bkavcoreca/transactionlog/";
	private static final String FILE_TYPE = ".pdf";

	public static int transactionProcess(byte[] clientData, User user) {
		int verifyCode = ServiceImpl.verify(clientData);
		if(verifyCode == 0){
			byte[] serverSigned = ServiceImpl.sign(clientData);
			if(serverSigned != null){
				saveTransaction(serverSigned, user);
			}
		}
		
		return 0;
	}

	public static boolean saveTransaction(byte[] signedData, User user) {
		PdfReader reader;
		try {
			reader = new PdfReader(signedData);

			String str = PdfTextExtractor.getTextFromPage(reader, 1);
			String[] data = str.split("\n");
			String signingTime = data[1].substring("Date and time: ".length(),
					data[1].length());
			String fileUrl = "";
			Random random = new Random();
			int tranId = random.nextInt();
			for (int i = 4; i < data.length; i++) {
				String[] tran = data[i].split(" ");
				String tranTyep = tran[1];
				String stockName = tran[2];
				int weight = Integer.parseInt(tran[3]);
				int price = Integer.parseInt(tran[4]);
				String status = tran[5];

				SimpleDateFormat df = new SimpleDateFormat(
						"dd/MM/yyyy HH:mm:ss");

				Date signTime = null;
				try {
					signTime = df.parse(signingTime);
				} catch (ParseException e) {
					signTime = new Date();
				}
				String fileName = "" + user.getUserName() + "_"
						+ signTime.getTime();
				fileUrl = TRANSATION_LOG_DIR + fileName + FILE_TYPE;

				TransactionLog log = new TransactionLog(user.getId(), tranId,
						signTime, tranTyep, stockName, weight, price, status,
						fileName, 0);
				TransactionLogDAO.insertUserLog(log);
			}

			FileUtil.writeToFile(signedData, fileUrl);

			return true;
		} catch (IOException e) {
			LOG.error("CANNOT SAVE TRANSACTION FILE " + e.getMessage());

			return false;
		}
	}
}
