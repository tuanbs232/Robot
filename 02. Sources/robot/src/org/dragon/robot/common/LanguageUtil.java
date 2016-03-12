package org.dragon.robot.common;

import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

public class LanguageUtil {
	private static final Logger LOG = Logger.getLogger(LanguageUtil.class);
	private static final String GIA_TRI_TRUNG_GIAN_LABEL = "GIA_TRI_TRUNG_GIAN_LABEL";
	private static final String DAI_SO_GIA_TU = "DAI_SO_GIA_TU";
	private static final String GIA_TU_RAT = "GIA_TU_RAT";
	private static final String GIA_TU_HON = "GIA_TU_HON";
	private static final String GIA_TU_KHA_NANG = "GIA_TU_KHA_NANG";
	private static final String GIA_TU_IT = "GIA_TU_IT";
	public static final String DAI_SO_GIA_TU_UPDATE = "DAI_SO_GIA_TU_UPDATE";
	public static final String ERROR_MIDDLE_VALUE_INVALID = "ERROR_MIDDLE_VALUE_INVALID";
	public static final String TITLE_WARNING = "TITLE_WARNING";
	public static final String ERROR_VALUE_INVALID = "ERROR_VALUE_INVALID";
	public static final String ERROR_SUM_NOT_EQUAL_ONE = "ERROR_SUM_NOT_EQUAL_ONE";
	public static final String MESSAGE_SUCCESSFULL = "MESSAGE_SUCCESSFULL";
	public static final String SAMPLE_DATA_DIR = "SAMPLE_DATA_DIR";
	public static final String ERROR_SAMPLE_DATA_NOT_FOUND = "ERROR_SAMPLE_DATA_NOT_FOUND";

	public static final String MAINGUI_START = "MAINGUI_START";
	public static final String MAINGUI_PROGRAM_NAME = "MAINGUI_PROGRAM_NAME";
	public static final String MAINGUI_RESTART = "MAINGUI_RESTART";
	public static final String MAINGUI_PAUSE = "MAINGUI_PAUSE";
	public static final String MAINGUI_CONTINUE = "MAINGUI_CONTINUE";
	public static final String MAINGUI_ENTER_SIZE_OF_WORK_AREA = "MAINGUI_ENTER_SIZE_OF_WORK_AREA";
	public static final String MAINGUI_SIZE_OF_WORK_AREA = "MAINGUI_SIZE_OF_WORK_AREA";
	public static final String MAINGUI_INIT_WORK_AREA = "MAINGUI_INIT_WORK_AREA";
	public static final String MAINGUI_RESET_MAP = "MAINGUI_RESET_MAP";
	public static final String MAINGUI_FILE = "MAINGUI_FILE";
	public static final String MAINGUI_EXIT = "MAINGUI_EXIT";
	public static final String MAINGUI_OPTION = "MAINGUI_OPTION";
	public static final String MAINGUI_HELP = "MAINGUI_HELP";
	public static final String MAINGUI_ABOUT_US = "MAINGUI_ABOUT_US";
	public static final String MAINGUI_MANUAL = "MAINGUI_MANUAL";
	public static final String MAINGUI_COMPLETE = "MAINGUI_COMPLETE";
	public static final String MAINGUI_ERROR_ENTER_SIZE_OF_WORK_FIRST = "MAINGUI_ERROR_ENTER_SIZE_OF_WORK_FIRST";
	public static final String MAINGUI_ERROR_MINIMUM_WORK_SIZE = "MAINGUI_ERROR_MINIMUM_WORK_SIZE";
	public static final String MAINGUI_ROBOT_WORKING_AT = "MAINGUI_ROBOT_WORKING_AT";
	public static final String MAINGUI_WORKING_TIME = "MAINGUI_WORKING_TIME";
	public static final String STANDARD_MODE_TITLE = "STANDARD_MODE_TITLE";
	public static final String STANDARD_MODE_ROBOT_TIME = "STANDARD_MODE_ROBOT_TIME";

	public static final String SELECT_STANDARD_MODE = "SELECT_STANDARD_MODE";
	public static final String SELECT_DSGT_MODE = "SELECT_DSGT_MODE";
	public static final String SELECT_DSGT_DYNAMIC_MODE = "SELECT_DSGT_DYNAMIC_MODE";
	public static final String SELECT_MODE_TITLE = "SELECT_MODE_TITLE";

	public static final String TITLE_DYNAMIC_BARRIER_CONFIG = "TITLE_DYNAMIC_BARRIER_CONFIG";

	public static final String ADD_BARRIER_TITLE = "ADD_BARRIER_TITLE";
	public static final String BARRIER_POS = "BARRIER_POS";
	public static final String BARRIER_SPEED = "BARRIER_SPEED";
	public static final String BARRIER_VECTOR = "BARRIER_VECTOR";
	public static final String BARRIER_ADD_BTN = "BARRIER_ADD_BTN";

	public static final String SHOW_DUST_VALUE = "SHOW_DUST_VALUE";

	public static final String ADD_BARRIER_POS_FIELD = "ADD_BARRIER_POS_FIELD";
	public static final String ADD_BARRIER_SPEED_FIELD = "ADD_BARRIER_SPEED_FIELD";
	public static final String ADD_BARRIER_DIRECTION_FIELD = "ADD_BARRIER_DIRECTION_FIELD";

	public static final String ADD_BARRIER_HORIZONTAL = "ADD_BARRIER_HORIZONTAL";
	public static final String ADD_BARRIER_VERTICAL = "ADD_BARRIER_VERTICAL";
	public static final String ADD_BARRIER_ERROR_STATIC = "ADD_BARRIER_ERROR_STATIC";
	public static final String ADD_BARRIER_ERROR_DUPPLICATE = "ADD_BARRIER_ERROR_DUPPLICATE";
	public static final String ADD_BARRIER_ERROR_UNKNOWN = "ADD_BARRIER_ERROR_UNKNOWN";
	public static final String ADD_BARRIER_ERROR_OK = "ADD_BARRIER_ERROR_OK";
	public static final String MAINGUI_IINIT_DUST = "MAINGUI_IINIT_DUST";

	public static final String REPORT_TITLE = "REPORT_TITLE";
	public static final String REPORT_TIME_PROCESS = "REPORT_TIME_PROCESS";
	public static final String REPORT_NUMBER_OF_STEPS = "REPORT_NUMBER_OF_STEPS";
	public static final String REPORT_DUPLICATE_STEPS = "REPORT_DUPLICATE_STEPS";
	public static final String REPORT_COVERAGE_AREA = "REPORT_COVERAGE_AREA";
	public static final String REPORT_RESULT = "REPORT_RESULT";

	public static void main(String[] args) {
		ProcessUtil.setLanguage("vi");
	}

	public static String getGiaTriTrungGianLabel() {
		return getProperty(GIA_TRI_TRUNG_GIAN_LABEL);
	}

	public static String getDaiSoGiaTuLabel() {
		return getProperty(DAI_SO_GIA_TU);
	}

	public static String getGiaTuIt() {
		return getProperty(GIA_TU_IT);
	}

	public static String getGiaTuHon() {
		return getProperty(GIA_TU_HON);
	}

	public static String getGiaTuKhaNang() {
		return getProperty(GIA_TU_KHA_NANG);
	}

	public static String getGiaTuRat() {
		return getProperty(GIA_TU_RAT);
	}

	public static String getProperty(String key) {
		Properties prop = new Properties();
		String result = "";
		String configFileName = ProcessUtil.getLanguage() + ".conf";
		try {
			prop.load(ProcessUtil.getConfigInputStream(configFileName));
			result = prop.getProperty(key);
			// result = new String(result.getBytes("ISO-8859-1"), "UTF-8");
		} catch (IOException e) {
			LOG.error("IOException: " + e.getMessage());
		}

		return result;
	}

}
