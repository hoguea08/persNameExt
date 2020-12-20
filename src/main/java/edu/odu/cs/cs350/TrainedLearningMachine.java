/**
 * 
 */
package edu.odu.cs.cs350;

import java.util.ArrayList;
import java.util.List;

import weka.core.SerializationHelper;
import weka.classifiers.functions.SMO;
import weka.core.Instances;
import weka.core.Attribute;

/**
 * @author ahogue
 * 
 * Loads a saved Learning Machine Model and returns predictions
 *
 */
public class TrainedLearningMachine {
	
	// Trained Learning Machine Model
	SMO svm;
	
	// Set of Windows
	List<Window> windows;
	
	// Default Constructor - loads a model
	public TrainedLearningMachine() {
		this.loadLearningMachineModel();
	}
	
	// Loads a saved SMO Model
	public void loadLearningMachineModel() {
		// Load trained learning machine model
		try {
			this.svm = (SMO) weka.core.SerializationHelper.read("svm.model");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// Returns a SMO Classifier
	public SMO getSMO() {
		return this.svm;
	}
		
	// Returns the predicted classifications for each Instance
	public ArrayList<String> returnClassifications(final SMO svm, final Instances toPredict, final Attribute toClassify) throws Exception {
		ArrayList<String> classifications = new ArrayList<String>();
			
		for (int i = 0; i < toPredict.size(); i++) {
			double predictedCategory = svm.classifyInstance(toPredict.get(i));
			classifications.add(toClassify.value((int) predictedCategory));
		}
		return classifications;
	}
}


