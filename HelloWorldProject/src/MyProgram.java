/*
 * TODO: Liam Gillaspy
 * TODO: 1/8/26
 * TODO: 6
 * TODO: Aprogram that sorts train cars based on their destination and condition
 */
import java.util.Scanner;
import java.io.File;
import java.util.Stack;
import java.util.Queue;
import java.util.LinkedList;

public class MyProgram {
	public static int val = 0;

	
	// track 0 - main line queue
	public static Queue<Train> mainLine = new LinkedList<>();
	
	// track 1 - inspection queue for cars over 700 miles
	public static Queue<Train> inspectionTrack = new LinkedList<>();
	
	// destination track stacks
	public static Stack<Train> trackA = new Stack<>();  // trenton
	public static Stack<Train> trackB = new Stack<>();  // charlotte
	public static Stack<Train> trackC = new Stack<>();  // baltimore
	public static Stack<Train> trackD = new Stack<>();  // other
	
	// current weight on each track
	public static int weightTrackA = 0;
	public static int weightTrackB = 0;
	public static int weightTrackC = 0;
	public static int weightTrackD = 0;
	
	public static void main(String[] args) {

		int limitTrackA = 100000, limitTrackB = 100000, limitTrackC = 100000;
	
		Scanner x = new Scanner(System.in);
		try{
			File f = new File("HelloWorldProject/src/data.txt");
			x = new Scanner (f);
			while (x.hasNextLine()) {
				String line = x.nextLine().trim();
				
				if (line.equals("END")) {
					break;
				} // break if at end
				
				// parse weight limits
				if (line.startsWith("limitTrackA")) {
					limitTrackA = Integer.parseInt(line.split("=")[1].trim());
					continue;
				} 
				if (line.startsWith("limitTrackB")) {
					limitTrackB = Integer.parseInt(line.split("=")[1].trim());
					continue;
				}
				if (line.startsWith("limitTrackC")) {
					limitTrackC = Integer.parseInt(line.split("=")[1].trim());
					continue;
				}
				
				// engine 
				if (line.startsWith("ENG")) {
					String engineId = line;
					String destination = x.nextLine().trim();
					Train engineMarker = new Train(engineId, "ENGINE", "", destination, 0, 0);
					mainLine.add(engineMarker);
					continue;
				} 
				
				// read car data
				if (line.startsWith("CAR")) {
					String carName = line;
					String product = x.nextLine().trim();
					String origin = x.nextLine().trim();
					String destination = x.nextLine().trim();
					int weight = Integer.parseInt(x.nextLine().trim());
					int miles = Integer.parseInt(x.nextLine().trim());
					
					Train car = new Train(carName, product, origin, destination, weight, miles);
					mainLine.add(car);
				}
			} // end while
			
			x.close();
			
			// process main line
			processMainLine(limitTrackA, limitTrackB, limitTrackC);
			
			// process inspection track
			processInspectionTrack(limitTrackA, limitTrackB, limitTrackC);
			
			// print status before final departures
			printStationStatusBeforeFinal();
			
			// send final trains
			sendFinalTrains();
			
			// print final status
			printStationStatus();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	} // end main

	public static void processMainLine(int limitA, int limitB, int limitC) {
		while (!mainLine.isEmpty()) {
			Train item = mainLine.poll();
			
			// check for engine marker
			if (item.getProduct().equals("ENGINE")) {
				departTrack(item.getName(), item.getDestination());
				continue;
			} 
			
			// cars over 700 miles go to inspection
			if (item.getMiles() > 700) {
				inspectionTrack.add(item);
			} else {
				routeToDestination(item, limitA, limitB, limitC);
			} 
		} 
	} // processMainLine
	
	public static void routeToDestination(Train car, int limitA, int limitB, int limitC) {
		String dest = car.getDestination();
		int carWeight = car.getWeight();
		
		if (dest.equals("Trenton")) {
			if (weightTrackA + carWeight > limitA) {
				departTrackWithAuto("Trenton", trackA);
				weightTrackA = 0;
			}
			trackA.push(car);
			weightTrackA += carWeight;
		} else if (dest.equals("Charlotte")) {
			if (weightTrackB + carWeight > limitB) {
				departTrackWithAuto("Charlotte", trackB);
				weightTrackB = 0;
			} 
			trackB.push(car);
			weightTrackB += carWeight;
		} else if (dest.equals("Baltimore")) {
			if (weightTrackC + carWeight > limitC) {
				departTrackWithAuto("Baltimore", trackC);
				weightTrackC = 0;
			} 
			trackC.push(car);
			weightTrackC += carWeight;
		} else {
			trackD.push(car);
			weightTrackD += carWeight;
		}
	} // routeToDestination
	
	public static void processInspectionTrack(int limitA, int limitB, int limitC) {
		while (!inspectionTrack.isEmpty()) {
			Train car = inspectionTrack.poll();
			car.resetMiles();
			routeToDestination(car, limitA, limitB, limitC);
		}
	} // processInspectionTrack
	
	public static void departTrack(String engineId, String destination) {
		Stack<Train> track;
		
		switch (destination) {
			case "Trenton":
				track = trackA;
				break;
			case "Charlotte":
				track = trackB;
				break;
			case "Baltimore":
				track = trackC;
				break;
			default:
				track = trackD;
		  		break;
		} 
		
		if (!track.isEmpty()) {
			System.out.println(engineId + " leaving for " + destination + " with the following cars:");
			
			// reverse stack to print in arrival order
			Stack<Train> temp = new Stack<>();
			while (!track.isEmpty()) {
				temp.push(track.pop());
			} 
			while (!temp.isEmpty()) {
				Train car = temp.pop();
				System.out.println(car.toString());
			} 
			System.out.println();
			
			// reset weight
			switch (destination) {
				case "Trenton":
					weightTrackA = 0;
					break;
				case "Charlotte":
					weightTrackB = 0;
					break;
				case "Baltimore":
					weightTrackC = 0;
					break;
				default:
					weightTrackD = 0;
					break;
			} 
		} 
	} // departTrack
	
	public static void departTrackWithAuto(String destination, Stack<Train> track) {
		if (!track.isEmpty()) {
			System.out.println("ENG00000 leaving for " + destination + " with the following cars:");
			
			Stack<Train> temp = new Stack<>();
			while (!track.isEmpty()) {
				temp.push(track.pop());
			} 
			while (!temp.isEmpty()) {
				Train car = temp.pop();
				System.out.println(car.toString());
			} 
			System.out.println();
		} 
	} // departTrackWithAuto
	
	public static void sendFinalTrains() {
		if (!trackC.isEmpty()) {
			departTrackWithAuto("Baltimore", trackC);
			weightTrackC = 0;
		} 
		
		if (!trackB.isEmpty()) {
			departTrackWithAuto("Charlotte", trackB);
			weightTrackB = 0;
		} 
		
		if (!trackA.isEmpty()) {
			departTrackWithAuto("Trenton", trackA);
			weightTrackA = 0;
		}
	} // sendFinalTrains
	
	public static void printStationStatusBeforeFinal() {
		System.out.println("=== STATION STATUS (Before Final Departures) ===");
		System.out.println();
		
		System.out.printf("%-15s %-15s %-15s %-20s %-15s%n", 
		                  "Baltimore", "Charlotte", "Trenton", "Other Destinations", "Overweight");
		System.out.println("-".repeat(80));
		
		int maxSize = Math.max(Math.max(trackC.size(), trackB.size()), 
		                       Math.max(trackA.size(), Math.max(trackD.size(), inspectionTrack.size())));
		
		Object[] baltArr = trackC.toArray();
		Object[] charlArr = trackB.toArray();
		Object[] trentArr = trackA.toArray();
		Object[] otherArr = trackD.toArray();
		Object[] inspArr = inspectionTrack.toArray();
		
		if (maxSize == 0) {
			System.out.println("(all tracks empty)");
		} else {
			for (int i = 0; i < maxSize; i++) {
				String balt = (i < baltArr.length) ? ((Train)baltArr[i]).getName() : "";
				String charl = (i < charlArr.length) ? ((Train)charlArr[i]).getName() : "";
				String trent = (i < trentArr.length) ? ((Train)trentArr[i]).getName() : "";
				String other = (i < otherArr.length) ? ((Train)otherArr[i]).getName() : "";
				String insp = (i < inspArr.length) ? ((Train)inspArr[i]).getName() : "";
				
				System.out.printf("%-15s %-15s %-15s %-20s %-15s%n", balt, charl, trent, other, insp);
			}
		} 
		System.out.println();
	} // printStationStatusBeforeFinal
	
	public static void printStationStatus() {
		System.out.println("=== FINAL STATION STATUS ===");
		System.out.println();
		
		System.out.printf("%-15s %-15s %-15s %-20s %-15s%n", 
		                  "Baltimore", "Charlotte", "Trenton", "Other Destinations", "Overweight");
		System.out.println("-".repeat(80));
		
		if (trackD.isEmpty() && inspectionTrack.isEmpty()) {
			System.out.println("(empty)        (empty)        (empty)        (empty)              (empty)");
		} else {
			int maxSize = Math.max(trackD.size(), inspectionTrack.size());
			Object[] otherArr = trackD.toArray();
			Object[] inspArr = inspectionTrack.toArray();
			
			for (int i = 0; i < maxSize; i++) {
				String other = (i < otherArr.length) ? ((Train)otherArr[i]).getName() : "";
				String insp = (i < inspArr.length) ? ((Train)inspArr[i]).getName() : "";
				
				System.out.printf("%-15s %-15s %-15s %-20s %-15s%n", "", "", "", other, insp);
			}
		}
		System.out.println();
	} // printStationStatus
}

