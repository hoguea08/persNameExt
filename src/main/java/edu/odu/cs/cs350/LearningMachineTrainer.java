package edu.odu.cs.cs350;

import java.io.FileOutputStream;
import java.io.File;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Random;

import weka.classifiers.Evaluation;
import weka.classifiers.functions.SMO;
import weka.classifiers.functions.supportVector.RBFKernel;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
import weka.core.Attribute;

public class LearningMachineTrainer implements Trainer {
	
	private final double gamma;
	private final double testC;

	// DEFAULT CONSTRUCTOR
	public LearningMachineTrainer() {
		gamma = 0.01;
		testC = 0.1;
	}

	// RETURNS GAMMA VALUE
	public double getGamma() {
		return gamma;
	}

	// RETURNS C VALUE
	public double getC() {
		return testC;
	}

	// CREATES A DATA FILE AND A SMO LEARNING MACHINE MODEL
	// TRAINS AND SAVES THE MODEL
	public void train(final Document document) throws Exception {
		
		ARFFCreator data = new ARFFCreator();
		
		// Create a list of Windows from the TextBlocks
		ArrayList<Window> windows = new ArrayList<Window>();
		for (TextBlock t : document.getBlocks()) {
			t.getTokenTrainingClassification();
			for (int j = 0; j < t.getLength(); j++) {
				Window temp = new Window();
				temp = temp.createWindow(t, j);
				windows.add(temp);
			}
		}
		
		// Create a String array to hold the merged feature sets for each Window
		String[] blockString = new String[windows.size()];
		for (int i = 0; i < windows.size(); i++) {
			blockString[i] = windows.get(i).getMergedFeatureSet();
		}
		
		// Pass the merged feature sets through the createARFF method to generate an .ARFF file
		Instances training = null;
		try {
			data.createARFF(blockString);
			DataSource source = new DataSource("file.arff");
			training = source.getDataSet();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// Create a File Object from the DataSource to delete it later
		File file = new File("file.arff");
		
		// Set class index (Attribute to predict)
		Attributes attrInfo = new Attributes();
		training.setClassIndex(attrInfo.getAttributes().size() - 1);
        
        // Set Pre-Optimized Classifier Statistics 
        final double gamma = .0225;
        final double C = 8.649755859375; 
        
        String[] options = {"-N", "0", "-V", "-1"};

        // Create the classifier
        SMO svm = new SMO();         // new instance of classifier
        svm.setOptions(options);     // set the options
        svm.setKernel(new RBFKernel(training, 25007, gamma));
        svm.setC(C);

        // Train Classifier
        svm.buildClassifier(training);
        
        // Save Learning Machine model
        ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream("svm.model"));
        oos.writeObject(svm);
        oos.flush();
        oos.close();
        
        // Set up Evaluation Object to test model
        Evaluation eval = new Evaluation(training);
        
        // Cross-Validation folds
        final int numberOfCrossClasses = 10;
        
        // Evaluate Classifier through cross-validation
        eval.crossValidateModel(svm, training, numberOfCrossClasses, new Random(1));
        
        // Get the accuracy of the cross-validation evaluation
        double score = eval.pctCorrect();

        System.out.println(eval.toSummaryString(true));
        System.out.println(eval.toMatrixString());
        
        // Create an Attribute Object to represent the Attribute predicted
        Attribute classify = training.attribute(attrInfo.getAttributes().size() - 1);
        
        // Create an ArrayList and store the predicted Classifications for each Window
        ArrayList<String> classifications = new ArrayList<String>();
        classifications = returnClassifications(svm, training, classify);
        System.out.println (classifications);
        
        System.out.println ("Accuracy after cross-validation: " + score);

        file.deleteOnExit();
	}
	
	// This returns the predicted classifications in String form in an ArrayList
	public ArrayList<String> returnClassifications(final SMO svm, final Instances trainingData, 
													final Attribute toClassify) throws Exception {
		ArrayList<String> classifications = new ArrayList<String>();
		
		for (int i = 0; i < trainingData.size(); i++) {
			double predictedCategory = svm.classifyInstance(trainingData.get(i));
			classifications.add(toClassify.value((int) predictedCategory));
		}
		
		return classifications;
	}
}
