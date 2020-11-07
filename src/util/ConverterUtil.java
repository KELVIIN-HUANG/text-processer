package util;

import java.util.ArrayList;
import java.util.List;

public class ConverterUtil {
	public static String firstUppserCase(String string) {
		string = string.substring(0, 1).toUpperCase() + string.substring(1);
		return string;
	}

	public static String firstLowerCase(String string) {
		string = string.substring(0, 1).toLowerCase() + string.substring(1);
		return string;
	}

	// 添加前缀
	public static String addPrefix(final String text, final String prefix) {
		if (prefix != null) {
			return firstLowerCase(prefix) + firstUppserCase(text);
		} else {
			return text;
		}

	}

	// 添加后缀
	public static String addSuffix(final String text, final String suffix) {
		if (suffix != null) {
			return firstLowerCase(text) + firstUppserCase(suffix);
		} else {
			return text;
		}
	}

	// 转成jquery
	public static String toJQuery(final String id) {
		final String pre = ":$('#";
		final String back = "').val()";
		return pre + id + back;
	}

	// 转成ajax
	public static String toAjax(final String text, final String prefix, final String suffix) {
		String newText = addPrefix(text, prefix);
		newText = addSuffix(newText, suffix);
		return "\"" + text + "\"" + toJQuery(newText);
	}

	// 转成getparameter
	public static String toGetParameter(final String text) {
		return "String" + " " + text + " = " + "req.getParameter(\"" + text + "\");";
	}
	// 生成序列
	public static List<String> toSequencesWith100(List<String> strings) {
		List<String> sequences = new ArrayList<>();
		for (String string : strings) {
			String sequence = "CREATE SEQUENCE "+"SEQ_"+string + " START WITH 100; ";
			sequences.add(sequence);
		}
		return sequences;
	}
	// 根据换行符拆分
	public static List<String> splitByLineBreak(String string) {
		List<String> sList = new ArrayList<>();
		String[] strings = string.split("\n");
		for (String string2 : strings) {
			string2.replace("\n", "string");
			sList.add(string2);
		}
		return sList;
	}

}