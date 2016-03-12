package org.firestorm.demosecurities.servlet;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.firestorm.demosecurities.database.TransactionLog;
import org.firestorm.demosecurities.database.TransactionLogDAO;
import org.firestorm.demosecurities.database.TransactionSession;
import org.firestorm.demosecurities.database.TransactionSessionDAO;
import org.firestorm.demosecurities.database.User;
import org.firestorm.demosecurities.utils.FileUtil;

/**
 * Servlet implementation class TransactionLogServlet
 */
public class TransactionLogServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	final String VIEW = "/history/index.jsp";
	private final String TRANSATION_LOG_DIR = "C:/BkavCA/bkavcoreca/transactionlog/";
	private final String FILE_TYPE = ".pdf";
	final Logger LOG = Logger.getLogger(TransactionLogServlet.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TransactionLogServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		if (id == null || "".equals(id)) {
			forward(request, response);
		}

		if ("download".equals(id)) {
			String para = request.getParameter("file");
			if (para != null && !"".equals(para)) {
				String filePath = TRANSATION_LOG_DIR + para + FILE_TYPE;
				byte[] data = FileUtil.readBytesFromFile(filePath);
				if (data != null) {
					response.setContentType("APPLICATION/OCTET-STREAM");
					response.setHeader("Content-Disposition",
							"attachment; filename=\"transactionlog.pdf\"");

					ByteArrayInputStream inStream = new ByteArrayInputStream(
							data);
					PrintWriter out = response.getWriter();
					int i;
					while ((i = inStream.read()) != -1) {
						out.write(i);
					}
					inStream.close();
					out.close();
				}
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private void forward(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		User user = (User) session.getAttribute("user");
		if (user != null) {
			DecimalFormat df = new DecimalFormat("###,###,###");
			request.setAttribute("username", user.getUserName());
			request.setAttribute("fullname", user.getFullName());
			request.setAttribute("sodutienmat",
					df.format(user.getSoDuTienMat()));
			request.setAttribute("tienungtruoc",
					df.format(user.getTienUngTruoc()));
			request.setAttribute("soducothegiaodich",
					df.format(user.getSoDuCoTheGiaoDich()));
			request.setAttribute("khoiluongcothemua",
					df.format(user.getKhoiLuongCoTheMua()));
			request.setAttribute("tientreogoc",
					df.format(user.getTienTreoGoc()));

			List<TransactionLog> userLogs = TransactionLogDAO
					.listUserLog(user.getId(), 0, 10);
			List<TransactionSession> transactionLogs = TransactionSessionDAO
					.formatTransactionLogs(userLogs);
			request.setAttribute("logs", transactionLogs);
		}

		RequestDispatcher view = request.getRequestDispatcher(VIEW);
		view.forward(request, response);
	}

}