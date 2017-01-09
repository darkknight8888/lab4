package lab4;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

class act_data {
	int priority;
	String star_date;
	String end_date;
	String activity;

	act_data(String start_d, String end_d, String act, int p) {
		star_date = start_d;
		end_date = end_d;
		activity = act;
		priority = p;
	}

	act_data() {
		star_date = "";
		end_date = "";
		activity = "";
		priority = 0;

	};
}

public class labb {

	public static void main(String[] args) throws IOException {

		ArrayList<act_data> data1 = new ArrayList<>();
		ArrayList<act_data> data2 = new ArrayList<>();

		// File reading
		CSVReader read_line = new CSVReader(
				new FileReader("/Users/Raveel/mars/workspace/lab4/src/lab4/Academic _Schedule.csv"));
		String[] nextLine;

		while ((nextLine = read_line.readNext()) != null) {
			// nextLine[] is an array of values from the line
			// System.out.println(nextLine[0] + " " + nextLine[1] + " " +
			// nextLine[2]);

			// storing values to data structure

			act_data ii = new act_data(nextLine[0].trim(), nextLine[1].trim(), nextLine[2].trim(), 0);

			data1.add(ii);

		}
		read_line.close();
		System.out.println("Data Stored successfully..");
		act_data act_data2 = new act_data();
		act_data act_data3 = new act_data();
		int already = 0;
		int p = 1;
		for (int i = 0; i < data1.size(); i++) {

			act_data2 = data1.get(i);

			for (int j = 0; j < data2.size(); j++) {
				act_data3 = data2.get(j);
				if (act_data2.activity.trim().equals(act_data3.activity.trim())) {

					already = 1;

				}

			}
			if (already == 0) {
				act_data2.priority = p;
				data2.add(act_data2);
				p++;
			}
			already = 0;
		}
		// assigning priority

		for (int j = 0; j < data2.size(); j++) {
			act_data3 = data2.get(j);
			System.out.println("Priority : " + act_data3.priority + " Start Date : " + act_data3.star_date
					+ " End Date: " + act_data3.end_date + " Activities : " + act_data3.activity);

		}
		// writing to csv
		
		CSVWriter write = new CSVWriter(new FileWriter("/Users/Raveel/mars/workspace/lab4/src/lab4/New_file.csv"), '\t');
	     // feed in your array (or convert your data to an array)
		for (int j = 0; j < data2.size(); j++) {
			act_data3 = data2.get(j);
			String[] entries = ("Priority : " + act_data3.priority + " Start Date : " + act_data3.star_date
					+ " End Date: " + act_data3.end_date + " Activities : " + act_data3.activity).split(",");

			write.writeNext(entries);
		}
		 write.close();
		

		

	}
}
