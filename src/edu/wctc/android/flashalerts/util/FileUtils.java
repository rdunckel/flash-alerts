package edu.wctc.android.flashalerts.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.content.Context;

public class FileUtils {

	public static String readRawTextFile(Context ctx, int resId) {
		InputStream inputStream = ctx.getResources().openRawResource(resId);

		InputStreamReader inputreader = new InputStreamReader(inputStream);
		BufferedReader buffreader = new BufferedReader(inputreader);
		String line;
		StringBuilder text = new StringBuilder();

		try {
			while ((line = buffreader.readLine()) != null) {
				text.append(line);
				text.append('\n');
			}
		} catch (IOException e) {
			return null;
		}
		return text.toString();
	}

	public static String[] readLinesFromRawTextFile(Context ctx, int resId) {
		String fileText = readRawTextFile(ctx, resId);
		return fileText.split("\\r?\\n");
	}

}
