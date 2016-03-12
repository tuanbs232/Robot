package org.firestorm.demosecurities.servlet;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.bouncycastle.util.encoders.Base64;
import org.firestorm.demosecurities.database.User;
import org.firestorm.demosecurities.pdf.PDFUtils;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

/**
 * Servlet implementation class SignatureServlet
 */
public class SignatureServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	final String VIEW = "/home/index.jsp";
//	final String VIEW = "/transaction/index.jsp";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SignatureServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		User user = (User) session.getAttribute("user");
		if(user != null){
			request.setAttribute("username", user.getUserName());
			DecimalFormat df = new DecimalFormat("###,###,###");
			request.setAttribute("fullname", user.getFullName());
			request.setAttribute("sodutienmat", df.format(user.getSoDuTienMat()));
			request.setAttribute("tienungtruoc", df.format(user.getTienUngTruoc()));
			request.setAttribute("soducothegiaodich", df.format(user.getSoDuCoTheGiaoDich()));
			request.setAttribute("khoiluongcothemua", df.format(user.getKhoiLuongCoTheMua()));
			request.setAttribute("tientreogoc", df.format(user.getTienTreoGoc()));
			request.setAttribute("listCert", user.getListCertificate());
		}
		
		RequestDispatcher view = request.getRequestDispatcher(VIEW);
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String para = request.getParameter("para");
		response.setContentType("json/application");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();

		HashMap<String, String> map = new HashMap<>();
		JSONObject result = null;
		if (para == null || "".equals(para)) {
			map.put("type", "ERROR");
			map.put("message", "Lỗi truyền dữ liệu lên server");
			result = new JSONObject(map);
			out.print(result);
			out.flush();

			return;
		}

		JSONObject rawData = (JSONObject) JSONValue.parse(para);

		String signedDataEncoded = rawData.get("data").toString();

		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		Base64.decode(signedDataEncoded, outStream);
		byte[] data = outStream.toByteArray();

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		int verifyCode = PDFUtils.transactionProcess(data, user);
		
		String message = "";
		String type = "";
		if (verifyCode != 0) {
			message = "Giao dịch không thành công, chữ ký số không hợp lệ.\n[ERROR] "
					+ getVerifyErrorName(verifyCode);
			type = "ERROR";
			map.put("type", type);
			map.put("message", message);
			result = new JSONObject(map);
			out.print(result);
			out.flush();
		} else{
			message = "Giao dịch thành công. Chữ ký số hợp lệ";
			type = "OK";
			
			map.put("type", type);
			map.put("message", message);
			result = new JSONObject(map);
			out.print(result);
			out.flush();
		}

		return;
	}

	private String getVerifyErrorName(int code) {
		String result = "";
		switch (code) {
		case -1:
			result = "Không load được dữ liệu";
			break;
		case -2:
			result = "Không tìm thấy chữ ký trong tài liệu";
			break;
		case -3:
			result = "Chữ ký không hợp lệ";
			break;
		case -4:
			result = "Không thể kiểm tra được đường dẫn tin tưởng";
			break;
		case -5:
			result = "Không đủ thông tin để kiểm tra. Không tìm thấy chứng thư số của CA";
			break;
		default:
			result = "UNDEFINDED";
			break;
		}

		return result;
	}
	

}