import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ProduceWordCombinations {

	static String[] dictSpilt;
	static String[] inputSpilt;
	static ArrayList<String> result = new ArrayList<String>();
	
	public static void main(String[] args) throws IOException {
		//readFile
		String dict = readFile("txt/wordSeg_testDictionary_1061.txt");
		String input = readFile("txt/wordSeg_testInput_1061.txt");
		
		//spilt
		dictSpilt = dict.split("\\n");
		inputSpilt = input.split("\\n");
			
		//produce
		for(int i = 0; i < inputSpilt.length; i++){
			produceOrder(2, inputSpilt[i].length(), inputSpilt[i].substring(0, 1), inputSpilt[i]);
			System.out.println("produceOrder END");
		}
		
		System.out.println("START");

		
		//write
		writeFile();
		
		System.out.println("END");
	}
		
	//產生順序
	public static void produceOrder(Integer i, Integer n, String s, String input) {
		if (i == n) {
			//System.out.print(s + "\n");
			//System.out.print(s + " " + input.charAt(i-1) + "\n");
			//System.out.print(s + input.charAt(i-1) + "\n");
			printOrder(s + " " + input.charAt(i-1));
			printOrder(s + input.charAt(i-1));
			return ;
		}
		produceOrder(i + 1, n, s + " " + input.charAt(i-1), input);
		produceOrder(i + 1, n, s + input.charAt(i-1), input);
	}
	
	//只有字典裡含有的才印順序出來
	public static void printOrder(String s){
		String[] temp = s.split(" ");
		for(int i = 0; i < temp.length; i++){
			if(!searchDictionary(temp[i]))	//不在字典裡就不印，直接回去了
				return;
		}
		System.out.print(s + "\n");
		result.add(s);
	}
	
	//搜尋是否在字典裡
	public static boolean searchDictionary(String part){
		for(int i=0;i<dictSpilt.length;i++){
			if(dictSpilt[i].equals(part)){
				return true;
			}
		}
		return false;
	}
	
	//讀檔案
	public static String readFile(String fileName) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		try {
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();
			
			while (line != null) {
				sb.append(line);
				sb.append("\n");
				line = br.readLine();
			}
			return sb.toString();
		} finally {
			br.close();
		}
	}

	//寫檔
	public static void writeFile() throws IOException{
		System.out.println("writeFile1");

		
		FileWriter fw = new FileWriter("txt/result.txt");
		BufferedWriter bw = new BufferedWriter(fw);
		System.out.println("writeFile2");

		System.out.println("result.size() : " + result.size());
		System.out.println("result.size() : " + result.get(0));
		for(int i = 0; i < result.size(); i++){
			bw.write(result.get(i));
			bw.newLine();
			bw.flush();	//將緩衝寫到文件中
		}
		bw.close();
	}

}
