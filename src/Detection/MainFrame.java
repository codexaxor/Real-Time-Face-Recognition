/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Detection;

import javax.swing.JFrame;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;

/**
 *Main frame show the detected image on video panel 
 * as it's a real time face detector 
 * it takes its content from camera panel and show it 
 * 
 */
public class MainFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private final Ditector detector;
    private final CameraPanel cameraPanel; 

    public MainFrame() {
        super("Face Detection");
        
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        
        detector = new Ditector();
        cameraPanel = new CameraPanel();
        
        setContentPane(cameraPanel);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setVisible(true);
        
    }
    /*
    *it checks if the video capture open then 
    *show the image on screen 
    */
    public void displayScreen() {
        Mat webcamImage = new Mat();
        VideoCapture videoCapture = new VideoCapture(0);
        
        if(videoCapture.isOpened()){
            while(true){
                videoCapture.read(webcamImage);
                
                if(!webcamImage.empty()){
                    setSize(webcamImage.width() + 50, webcamImage.height() + 70);
                    webcamImage = detector.detect(webcamImage);
                    cameraPanel.convertMatToImage(webcamImage);
                    cameraPanel.repaint();
                }else {
                    System.out.println("Problem");
                    break;
                }
                
            }
        }
    }

}
