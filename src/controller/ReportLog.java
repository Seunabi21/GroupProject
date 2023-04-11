package controller;

import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class ReportLog {
	private static ReportLog instance;
	private static FileWriter loggingFile;
	
	public ReportLog(){ 
		try {
			loggingFile = new FileWriter("log.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static ReportLog getInstance(){
		if (instance == null) 
			synchronized (ReportLog.class) {
				if (instance == null)
					instance = new ReportLog();
			}
		return instance;
	}
	
	public void log(String message){
        try {
			loggingFile.write(message + "\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			loggingFile.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
	public void close() {
        try {
            // Close the log file
        	loggingFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
}
