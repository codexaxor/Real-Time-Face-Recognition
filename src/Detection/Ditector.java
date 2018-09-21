/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Detection;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

/**
 * This class for detecting the face
 * Here we use CascadeClassifier to detect face
 * 
 * PARAMETER for Face Detection......
 * cascadeClassifier.detectMultiScale(gray image, detected image,scaleFactor, minNeighbors, flags, minSize, maxSize)
 * 
 * 1.) scaleFactor: Science faces may be closer to camera , they would appear bigger than
 *     others faces behind it --> the scaleFactor compensates for this Specifying how much the 
 *     image size is reduced at each image scale
 * 
 *     The model has a fixed size defined during training : in the .xml file 
 *     By rescaling  the input image, we  resize the larger face to smaller one, 
 *      making it detectable by algorithm
 *   
 *  Value: 1.1 - 1.4
 *        Small --> Algorithm will be slow science it more through
 *        High --> Faster detection with the risk of missing some face
 * 
 * 2.) minNeighbors: specifying how many neighbors each candidate rectangle should have to retain it
 *    value interval: ~3 to 6
 *       High values --> less detection but high quality
 * 
 * 3.) flags : Kind of heuristic
 *     Reject some image regions that have too few or too much edges and thus
 *      can not contain the searched object
 * 
 * 4.) minSize : Objects smaller than that are ignored !!!
 *         We can specified what is the smallest object we want to recognized 
 *            or detected [ 30 x 30 ] is the standard 
 * 5.) maxSize : Object larger than that are ignored
 * 
 */
class Ditector {
    
    private final CascadeClassifier cascadeClassifier;
    private final MatOfRect detectedFace;
    private final Mat coloredImage;
    private final Mat grayImage;
    /*
    *Constractor
    *Initialing the variables
    */
    public  Ditector(){
        this.detectedFace = new MatOfRect();
        this.coloredImage = new Mat();
        this.grayImage = new Mat();
        this.cascadeClassifier = new CascadeClassifier("C:\\opencv\\sources\\data\\haarcascades_cuda\\haarcascade_frontalface_alt2.xml");
    }
    /*
    *It detecte the face with casecadeclassifire
    */
    public Mat detect(Mat inputframe){
        
        inputframe.copyTo(coloredImage);
        inputframe.copyTo(grayImage);
        
        Imgproc.cvtColor(coloredImage, grayImage, Imgproc.COLOR_BGR2GRAY);
        
        Imgproc.equalizeHist(grayImage, grayImage);
        
        cascadeClassifier.detectMultiScale(grayImage, detectedFace,1.2,6,10, new Size(10,10), new Size(500,500));
        
        showFaceOnScreen();
        
        return coloredImage;
    }
    /*
    * to indicate the detected face
    */
   private void showFaceOnScreen(){
        for(Rect rect: detectedFace.toArray()){
            Imgproc.rectangle(coloredImage, new Point(rect.x,rect.y), new Point(rect.x + rect.width, rect.y+rect.height), new Scalar(100,100,250),3);
            Imgproc.putText(coloredImage, "Human Face", new Point(rect.x - 10 , rect.y), Core.FONT_HERSHEY_SIMPLEX, 1, new Scalar(55, 250, 20));
        }
    }
    
    
}
