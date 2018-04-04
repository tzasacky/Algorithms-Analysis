package clustering;

import java.util.HashMap;
import java.util.Set;

public class Main {

    static FeatureVector[] dataset;

    public static final String DATASET_CHOICE = "DOG";

    public static void main(String args[]) {
        // Choose a dataset
        switch (DATASET_CHOICE) {
            case "DOG":
                dataset = buildDogDataset();
                break;
            default:
                dataset = null;
        }

        // Run K-Means Clustering
        KMeans classifier = new KMeans();
        HashMap<FeatureVector, Set<FeatureVector>> clusters = classifier.cluster(dataset, 3);

        // Print out the resulting clusters
        System.out.println("\nResulting clusters:");
        for (FeatureVector centroid : clusters.keySet()) {
            System.out.println("\tCentroid: " + centroid.toString());
            for (FeatureVector dataPoint : clusters.get(centroid)) {
                System.out.println("\t\t" + dataPoint.toString());
            }
        }

        // Try to classify another data point
        FeatureVector dataPoint = null;
        switch (DATASET_CHOICE) {
            case "DOG":
                dataPoint = new DogFeatureVector("Cocker Spaniel", 31, 16, "Medium");
                break;
            default:
                dataset = null;
        }

        String classification = classifier.classify(clusters.keySet(), dataPoint);
        System.out.println("\nClassified \"" + dataPoint.toString() + "\" as \"" + classification + "\"");
    }

    private static DogFeatureVector[] buildDogDataset() {
        DogFeatureVector[] dogs = {
            new DogFeatureVector("Chihuahua", 4, 5, "Tiny"),
            new DogFeatureVector("Yorkshire Terrier", 7, 6, "Tiny"),
            new DogFeatureVector("Minituare Poodle", 14, 12, "Small"),
            new DogFeatureVector("Beagle", 20, 13, "Small"),
            new DogFeatureVector("Pembroke Welsh Corgi", 24, 11, "Small"),
            new DogFeatureVector("Border Collie", 30, 20, "Medium"),
            new DogFeatureVector("Siberian Husky", 36, 22, "Medium"),
            new DogFeatureVector("Poodle", 55, 22, "Large"),
            new DogFeatureVector("Golden Cocker Retriever", 60, 20, "Large"),
            new DogFeatureVector("Labrador Retriever", 65, 30, "Large"),
            new DogFeatureVector("Bernese Mountain Dog", 110, 27, "Huge"),
            new DogFeatureVector("Great Pyrenese", 115, 28, "Huge"),
            new DogFeatureVector("Saint Bernard", 120, 35, "Huge"),
            new DogFeatureVector("Great Dane", 120, 40, "Huge")
        };

        return dogs;
    }
}
