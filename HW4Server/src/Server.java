import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static void main(String[] args){
		System.out.println("Starting server...");
		ServerSocket server;
		Socket client;
		try {
			String currentLine;
			server = new ServerSocket(2121);
			System.out.println("Waiting for connection...");
			client = server.accept();
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(client.getInputStream()));
			System.out.println("Client connected...");
			while((currentLine = bufferedReader.readLine()) != null){
				BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("Equations.txt"));
				bufferedWriter.write(currentLine);
				bufferedWriter.flush();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
