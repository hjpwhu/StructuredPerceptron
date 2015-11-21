/**
 * Project Name: StructuredPerceptron.
 * File Name: FeatureVector.java.
 * Date: Nov 14, 2015.
 * Copyright (c) 2015 hjp@whu.edu.cn All Rights Reserved.
 */

package org.hjp.model.sp.perceptron;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Class: FeatureVector.
 * 
 * @author: hjp.
 * @version: v1.0.
 * @since: JDK 1.8.
 */

public class FeatureVector {
	
	public static HashMap<String, Integer> pos = new HashMap<String, Integer>();

	public static void main(String[] args) {
		if (args.length == 0)
			args = new String[] { "/Users/hjp/Workshop/Model/perceptron/trial/" };
		listFile(args[0]);
	
		//Map map = new HashMap();
		Iterator iter = pos.entrySet().iterator();
		while(iter.hasNext()) {
			Map.Entry entry = (Map.Entry) iter.next();
			System.out.println(entry.getKey());
		}
		for(int i = 0; i < pos.size(); i++) {
			System.out.println("The type of pos: " + pos.size() );
		}
	}

	public static void listFile(String dirName) {
		try {
			File pathName = new File(dirName);
			String[] fileNames = pathName.list();

			for (int i = 0; i < fileNames.length; i++) {
				File f = new File(pathName.getPath(), fileNames[i]);

				if (f.isDirectory()) {
					System.out.println(f.getCanonicalPath());
					listFile(f.getPath());
				} else {
					// System.out.println(f.getAbsolutePath());
					readFile(f.getAbsolutePath());
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void readFile(String filePath) {
		// The data must be prepared with .txt format.
		if (filePath.endsWith(".txt")) {
			BufferedReader in;
			try {
				in = new BufferedReader(new FileReader(filePath));

				String line;
				String sent = "";
				while ((line = in.readLine()) != null) {
					if (line.length() != 0) {
						sent = sent + line + "#";
					} else {
						extractFeature(sent);
						sent = "";
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void extractFeature(String sent) {
		System.out.println(sent);
		String[] terms = sent.split("#");
		for (int i = 0; i < terms.length; i++) {
			System.out.println(terms[i]);
			String[] tokens = terms[i].split(" ");
			for (int j = 0; j < tokens.length; j++) {
				if(j == 1) {
					System.out.println("POS: " + tokens[j]);
					if(!pos.containsKey(tokens[j])) {
						pos.put(tokens[j], 1);
					}
				}
			}
		}

	}

}
