package clustering;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class KMeans {

    private static final boolean DEBUG = false;

    double[] mins;
    double[] maxes;

    /**
     * Clusters a data set into k clusters using the K-Means Clustering algorithm.
     * @param dataset - the set of data points to cluster
     * @param k - the number of clusters to form
     * @return a map of cluster centroids to cluster data points
     */
    public HashMap<FeatureVector, Set<FeatureVector>> cluster(FeatureVector[] dataset, int k) {
        HashMap<FeatureVector, Set<FeatureVector>> clusters = new HashMap<FeatureVector, Set<FeatureVector>>();
        HashMap<FeatureVector, Set<FeatureVector>> oldClusters;

        // Normalize the dataset
        this.normalizeDataset(dataset);

        // Choose k random initial clusters
        this.chooseFirstCentroids(dataset, k, clusters);

        // Iterate until convergence
        boolean didClustersChange = false;
        boolean isFirstAssignment = true;
        int numIterations = 0;
        do {
            didClustersChange = false;
            numIterations++;

            // Clone the current clusters into oldClusters
            oldClusters = deepCopyClusters(clusters);

            // Clear out the current clusters
            for (FeatureVector centroid : clusters.keySet()) {
                clusters.get(centroid).clear();
            }

            // Cluster the data points
            if (clusterDataPoints(dataset, clusters, oldClusters, isFirstAssignment)) {
                didClustersChange = true;
            }

            // Update the centroids
            this.updateCentroids(clusters, dataset[0].getM());

            // If it was the first assignment, set the flag to false
            isFirstAssignment = false;
        } while (didClustersChange);

        System.out.println("The solution converged in " + numIterations + " iteration(s).");

        return clusters;
    }

    /**
     * Classifies a data point based on the nearest centroid
     * @param centroids - centroids resulting from K-Means Clustering
     * @param dataPoint - the data point to classify
     * @return the label corresponding to the nearest cluster's centroid
     */
    public String classify(Set<FeatureVector> centroids, FeatureVector dataPoint) {
        // First, normalize this data point to our range
        dataPoint.normalize(mins, maxes);

        FeatureVector closestCentroid = getClosestCentroid(centroids, dataPoint);

        return closestCentroid == null ? "" : closestCentroid.getLabel();
    }

    /**
     * Gets the closest centroid to the given data point.
     * @param centroids - the current set of centroids
     * @param dataPoint - the data point for which to find the closest point
     * @return the nearest centroid
     */
    private FeatureVector getClosestCentroid(Set<FeatureVector> centroids, FeatureVector dataPoint) {
        FeatureVector closestCentroid = null;
        double closestDistance = Double.MAX_VALUE;

        for (FeatureVector centroid : centroids) {
            double distance = dataPoint.distance(centroid);
            if (closestCentroid == null || distance < closestDistance) {
                closestCentroid = centroid;
                closestDistance = distance;
            }
        }

        return closestCentroid;
    }
    
    /**
     * Normalized the dataset to have each feature in the range [0,1].  Modifies the
     * feature vectors in place by calling normalize() on them after calculating
     * the minimum and maximum of each feature.  Also updates mins and maxes
     * based on these ranges.
     * @param dataset - the dataset to normalize
     */
    private void normalizeDataset(FeatureVector[] dataset) {
        int m = dataset[0].getM();
        this.mins = new double[m];
        this.maxes = new double[m];
        //Get ranges of data
        for(FeatureVector feat: dataset){
        	for(int i = 0; i < m; i++){
        		mins[i] = Math.min(mins[i], feat.get(i));
        		maxes[i] = Math.max(maxes[i], feat.get(i));
        	}
        }
        //Normalize data
        for(FeatureVector feat: dataset){
        	for(int i = 0; i < m; i++){
        		feat.normalize(mins, maxes);
        	}
        }
    }

    /**
     * Randomly chooses the initial k centroids for the K-Means Clustering algorithm.  Initializes
     * the empty set for each cluster with its centroid.
     * @param dataset - the set of data points to cluster
     * @param k - the number of clusters to form
     */
    private void chooseFirstCentroids(FeatureVector[] dataset, int k, HashMap<FeatureVector, Set<FeatureVector>> clusters) {
        List<FeatureVector> dataCopy = Arrays.asList(dataset);
        Collections.shuffle(dataCopy);
        
        for (int i = 0; i < k; i++) {
            FeatureVector centroid = dataCopy.get(i).clone();
            clusters.put(centroid, new HashSet<FeatureVector>());

            if (DEBUG) {
                System.out.println("Chose initial cluster: " + centroid.toString());
            }
        }
    }

    /**
     * Clusters the data points into the (initially empty) clusters based on which has
     * the nearest centroid, as measured by the getClosestCentroid method.  Returns a
     * boolean indicating whether the cluster assignments changed.
     * @param dataset - the data set of points to cluster
     * @param clusters - the clusters
     * @param oldClusters - the previous iteration's clusters
     * @param isFirstAssignment - whether this is the first iteration
     * @return whether the cluster assignments changed
     */
    private boolean clusterDataPoints(FeatureVector[] dataset,
                                      HashMap<FeatureVector, Set<FeatureVector>> clusters,
                                      HashMap<FeatureVector, Set<FeatureVector>> oldClusters,
                                      boolean isFirstAssignment) {
    	Set<FeatureVector> centroids = oldClusters.keySet();
    	//Use replace method to build sets without storing them outside map
        for(FeatureVector point : dataset){
        	FeatureVector cluster = this.getClosestCentroid(centroids, point);
        	Set<FeatureVector> currCluster = clusters.get(cluster);
        	currCluster.add(point);
        	clusters.replace(cluster, currCluster);
        }
    	if(isFirstAssignment || !clusters.equals(oldClusters)){return true;}
    	return false;
    }

    /**
     * Updates the centroid of each cluster based on the data points contained in the cluster.
     * Updates to the centroids are done using the updateCentroid method of FeatureVector.
     * @param clusters - the new clusters
     * @param m - the number of features
     */
    private void updateCentroids(HashMap<FeatureVector, Set<FeatureVector>> clusters, int m) {
        for(Map.Entry<FeatureVector, Set<FeatureVector>> mapping: clusters.entrySet()){
        	
        	//Initialize cluster info trackers
        	HashMap<String, Integer> labelCount = new HashMap<String, Integer>();
        	double[] averages = new double[m];
        	
        	for(FeatureVector point: mapping.getValue()){
        		
        		//Count label in map
        		if(labelCount.get(point.getLabel()) != null){
        			labelCount.put(point.getLabel(), labelCount.get(point.getLabel()) + 1);
        		}else{labelCount.put(point.getLabel(), 1);}
        		
        		//Build sums of normalized features
        		for(int i =0; i < m; i++){
            		averages[i] += point.getNormalized(i);
            	}
        	}
        	
        	//Divide by size of set to get centroid point
        	int elts = mapping.getValue().size();
        	for(int i =0; i < m; i++){
        		averages[i] /= elts;
        	}
        	
        	//Get most used label in cluster
        	int max = 0;
        	String commonLabel = "";
        	for (Map.Entry<String, Integer> label : labelCount.entrySet()) {
                if(label.getValue() > max) {
                    commonLabel = label.getKey();
                    max = label.getValue();
                }
            }
        	
        	//Finally, update centroid
        	mapping.getKey().updateCentroid(averages, commonLabel);
        	
        	if (DEBUG) {
        		System.out.println("Updated cluster: " + mapping.getKey());
            }
        }
    }

    /**
     * Performs a deep copy of the clusters.
     * @param clusters - the clusters to copy
     * @return a copy of the clusters
     */
    private HashMap<FeatureVector, Set<FeatureVector>> deepCopyClusters(HashMap<FeatureVector, Set<FeatureVector>> clusters) {
        HashMap<FeatureVector, Set<FeatureVector>> copy = new HashMap<FeatureVector, Set<FeatureVector>>();

        for (FeatureVector fv : clusters.keySet()) {
            Set<FeatureVector> contents = new HashSet<FeatureVector>();
            contents.addAll(clusters.get(fv));
            copy.put(fv, contents);
        }

        return copy;
    }
}
