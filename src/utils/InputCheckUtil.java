package utils;

public class InputCheckUtil {
	private static String[] dangerTag = new String[] { "<script>", "</script>", "<script type=\"text/javascript\">",
			"<script type='text/javascript'>" };

	public static boolean notEmpty(String str) {
		return str != null && str.length() > 0;
	}

	public static boolean isOK(String str) {
		if (!notEmpty(str))
			return false;
		return !containJS(str);
	}

	public static boolean containJS(String str) {
		StringBuffer tmp = new StringBuffer();
		String[] frags = str.split(" ");
		for (String frag : frags) {
			tmp.append(frag);
		}
		String inputStr = tmp.toString();
		System.out.println(inputStr);
		for (String tag : dangerTag) {
			if (inputStr.contains(tag))
				return true;
		}
		return false;
	}
}
