import static java.lang.System.out;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class project4main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String inputFileName = args[0];
		File myInputFile = new File(inputFileName);
		ArrayList<String[]> myInputArray = new ArrayList<String[]>();
		try {
			Scanner myReader = new Scanner(myInputFile);
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				String[] splitted = data.split("\\s+");
				myInputArray.add(splitted);
			}
			myReader.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		int numGreenTrains = Integer.parseInt(myInputArray.get(0)[0]);
		int numRedTrains = Integer.parseInt(myInputArray.get(2)[0]);
		int numGreenReindeers = Integer.parseInt(myInputArray.get(4)[0]);
		int numRedReindeers = Integer.parseInt(myInputArray.get(6)[0]);

		int numVertices = numGreenReindeers+numRedTrains+numGreenTrains+numRedReindeers;

		Vertex start = new Vertex();
		Vertex end = new Vertex();
		BipartiteGraph bipartiteGraph = new BipartiteGraph(start, end, numVertices);
		bipartiteGraph.vertices.add(bipartiteGraph.source);
		bipartiteGraph.vertices.add(bipartiteGraph.sink);


		for (int i=0; i<numGreenTrains; i++) {
			Vertex myGreenTrain = new Vertex();
			int capacityOfTrain = Integer.parseInt(myInputArray.get(1)[i]);
			bipartiteGraph.vertices.add(myGreenTrain);
			bipartiteGraph.greenTrains.add(myGreenTrain);
			bipartiteGraph.addEdge(myGreenTrain, bipartiteGraph.sink, capacityOfTrain);
		}
		for (int i=0; i<numRedTrains; i++) {
			Vertex myRedTrain = new Vertex();
			int capacityOfTrain = Integer.parseInt(myInputArray.get(3)[i]);
			bipartiteGraph.vertices.add(myRedTrain);
			bipartiteGraph.redTrains.add(myRedTrain);
			bipartiteGraph.addEdge(myRedTrain, bipartiteGraph.sink, capacityOfTrain);
		}
		for (int i=0; i<numGreenReindeers; i++) {
			Vertex myGreenReindeer = new Vertex();
			int capacityOfReindeer = Integer.parseInt(myInputArray.get(5)[i]);
			bipartiteGraph.vertices.add(myGreenReindeer);
			bipartiteGraph.greenReindeers.add(myGreenReindeer);
			bipartiteGraph.addEdge(myGreenReindeer, bipartiteGraph.sink, capacityOfReindeer);
		}

		for (int i=0; i<numRedReindeers; i++) {
			Vertex myRedReindeer = new Vertex();
			int capacityOfReindeer = Integer.parseInt(myInputArray.get(7)[i]);
			bipartiteGraph.vertices.add(myRedReindeer);
			bipartiteGraph.redReindeers.add(myRedReindeer);
			bipartiteGraph.addEdge(myRedReindeer, bipartiteGraph.sink, capacityOfReindeer);
		}

		int numOfBags = Integer.parseInt(myInputArray.get(myInputArray.size()-2)[0]);
		int totalNumOfGifts = 0;

		int capOfB = 0;
		int capOfC = 0;
		int capOfD = 0;
		int capOfE = 0;


		int capOfBD = 0;
		int capOfBE = 0;
		int capOfCD = 0;
		int capOfCE = 0;

		for (int i=0; i<numOfBags*2; i+=2) {
			String[] myList = myInputArray.get(myInputArray.size()-1);
			int capacity = Integer.parseInt(myList[i+1]);
			totalNumOfGifts += capacity;

			String myBagType = myList[i];

			if (myBagType.substring(0, 1).equals("a")) {
				Vertex myBag = new Vertex();
				bipartiteGraph.addEdge(bipartiteGraph.source, myBag, capacity);
				bipartiteGraph.vertices.add(myBag);


				if (myBagType.substring(0, 1).equals("a")&&myBagType.length()!=1) {
					capacity = 1;
					myBagType = myBagType.substring(1);
				}
				ArrayList<Vertex> iterList = new ArrayList<Vertex>();

				if (myBagType.equals("a")) {
					iterList.addAll(bipartiteGraph.greenReindeers);
					iterList.addAll(bipartiteGraph.redReindeers);
					iterList.addAll(bipartiteGraph.greenTrains);
					iterList.addAll(bipartiteGraph.redTrains);
					for (Vertex ver : iterList) {
						bipartiteGraph.addEdge(myBag, ver, 1);
					}
				}

				else if (myBagType.equals("b")) {
					iterList.addAll(bipartiteGraph.greenReindeers);
					iterList.addAll(bipartiteGraph.greenTrains);
					for (Vertex ver : iterList) {
						bipartiteGraph.addEdge(myBag, ver, capacity);
					}
				}

				else if (myBagType.equals("c")) {
					iterList.addAll(bipartiteGraph.redReindeers);
					iterList.addAll(bipartiteGraph.redTrains);
					for (Vertex ver : iterList) {
						bipartiteGraph.addEdge(myBag, ver, capacity);
					}
				}

				else if (myBagType.equals("d")) {
					iterList.addAll(bipartiteGraph.greenTrains);
					iterList.addAll(bipartiteGraph.redTrains);
					for (Vertex ver : iterList) {
						bipartiteGraph.addEdge(myBag, ver, capacity);
					}
				}

				else if (myBagType.equals("e")) {
					iterList.addAll(bipartiteGraph.greenReindeers);
					iterList.addAll(bipartiteGraph.redReindeers);
					for (Vertex ver : iterList) {
						bipartiteGraph.addEdge(myBag, ver, capacity);
					}
				}

				else if (myBagType.equals("bd")) {
					iterList.addAll(bipartiteGraph.greenTrains);
					for (Vertex ver : iterList) {
						bipartiteGraph.addEdge(myBag, ver, capacity);
					}
				}

				else if (myBagType.equals("be")) {
					iterList.addAll(bipartiteGraph.greenReindeers);
					for (Vertex ver : iterList) {
						bipartiteGraph.addEdge(myBag, ver, capacity);
					}
				}

				else if (myBagType.equals("cd")) {
					iterList.addAll(bipartiteGraph.redTrains);
					for (Vertex ver : iterList) {
						bipartiteGraph.addEdge(myBag, ver, capacity);
					}
				}

				else if (myBagType.equals("ce")) {
					iterList.addAll(bipartiteGraph.redReindeers);
					for (Vertex ver : iterList) {
						bipartiteGraph.addEdge(myBag, ver, capacity);
					}
				}
			}

			else if (myBagType.equals("b")) {
				capOfB+= capacity;
			}
			else if (myBagType.equals("c")) {
				capOfC+= capacity;
			}

			else if (myBagType.equals("d")) {
				capOfD+= capacity;
			}

			else if (myBagType.equals("e")) {
				capOfE+= capacity;
			}

			else if (myBagType.equals("be")) {
				capOfBE+= capacity;
			}
			else if (myBagType.equals("bd")) {
				capOfBD+= capacity;
			}
			else if (myBagType.equals("ce")) {
				capOfCE+= capacity;
			}
			else if (myBagType.equals("cd")) {
				capOfCD+= capacity;
			}

		}

		Vertex myBBag = new Vertex();
		bipartiteGraph.addEdge(bipartiteGraph.source, myBBag, capOfB);
		bipartiteGraph.vertices.add(myBBag);
		for (Vertex ver : bipartiteGraph.greenReindeers )
			bipartiteGraph.addEdge(myBBag, ver, capOfB);
		for (Vertex ver : bipartiteGraph.greenTrains )
			bipartiteGraph.addEdge(myBBag, ver, capOfB);


		Vertex myCBag = new Vertex();
		bipartiteGraph.addEdge(bipartiteGraph.source, myCBag, capOfC);
		bipartiteGraph.vertices.add(myCBag);
		for (Vertex ver : bipartiteGraph.redReindeers )
			bipartiteGraph.addEdge(myCBag, ver, capOfC);
		for (Vertex ver : bipartiteGraph.redTrains )
			bipartiteGraph.addEdge(myCBag, ver, capOfC);

		Vertex myDBag = new Vertex();
		bipartiteGraph.addEdge(bipartiteGraph.source, myDBag, capOfD);
		bipartiteGraph.vertices.add(myDBag);
		for (Vertex ver : bipartiteGraph.redTrains )
			bipartiteGraph.addEdge(myDBag, ver, capOfD);
		for (Vertex ver : bipartiteGraph.greenTrains )
			bipartiteGraph.addEdge(myDBag, ver, capOfD);

		Vertex myEBag = new Vertex();
		bipartiteGraph.addEdge(bipartiteGraph.source, myEBag, capOfE);
		bipartiteGraph.vertices.add(myEBag);
		for (Vertex ver : bipartiteGraph.greenReindeers )
			bipartiteGraph.addEdge(myEBag, ver, capOfE);
		for (Vertex ver : bipartiteGraph.redReindeers )
			bipartiteGraph.addEdge(myEBag, ver, capOfE);

		Vertex myBDBag = new Vertex();
		bipartiteGraph.addEdge(bipartiteGraph.source, myBDBag, capOfBD);
		bipartiteGraph.vertices.add(myBDBag);
		for (Vertex ver : bipartiteGraph.greenTrains )
			bipartiteGraph.addEdge(myBDBag, ver, capOfBD);

		Vertex myBEBag = new Vertex();
		bipartiteGraph.addEdge(bipartiteGraph.source, myBEBag, capOfBE);
		bipartiteGraph.vertices.add(myBEBag);
		for (Vertex ver : bipartiteGraph.greenReindeers )
			bipartiteGraph.addEdge(myBEBag, ver, capOfBE);

		Vertex myCDBag = new Vertex();
		bipartiteGraph.addEdge(bipartiteGraph.source, myCDBag, capOfCD);
		bipartiteGraph.vertices.add(myCDBag);
		for (Vertex ver : bipartiteGraph.redTrains )
			bipartiteGraph.addEdge(myCDBag, ver, capOfCD);

		Vertex myCEBag = new Vertex();
		bipartiteGraph.addEdge(bipartiteGraph.source, myCEBag, capOfCE);
		bipartiteGraph.vertices.add(myCEBag);
		for (Vertex ver : bipartiteGraph.redReindeers )
			bipartiteGraph.addEdge(myCEBag, ver, capOfCE);
		
		int numGiftsCanNotDistrubuted = totalNumOfGifts-bipartiteGraph.DinicsAlgorithm(start,end);
		String outputFileName = args[1];   //Writes to output file.
		File myOutputFile = new File(outputFileName);
		try {
			if (myOutputFile.createNewFile()) {
				out.println("File created: " + outputFileName);
			} else {
				out.println("File already exists.");
			}

			FileWriter myWriter = new FileWriter(outputFileName);
			
			myWriter.write(String.valueOf(numGiftsCanNotDistrubuted));
			
			myWriter.close();

		} catch (IOException e) {
			out.println("Catch - An error occurred.");
			e.printStackTrace();
		}
	}
}


