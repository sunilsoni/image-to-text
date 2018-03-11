package com.image;

import java.io.File;

import com.image.util.CommonUtils;

public class SarchTextFiles {

	public static void main(String[] args) {
		for (File f : CommonUtils.getResourceFolderFiles("data")) {
			System.out.println(f);
		}
	}

}
