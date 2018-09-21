/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Detection;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import javax.swing.JPanel;
import org.opencv.core.Mat;

/**
 *This class to take image with web camera and show it in image panel
 * 
 */
class CameraPanel extends JPanel{
    
    private static final long serialVersionUID = 1L;
    private BufferedImage image;
    
    public CameraPanel(){   
        
    }
    /*
    * PRAMETER FOR convertMatToImage(mat )
    */
    public boolean convertMatToImage(Mat matBGR){
        int width = matBGR.width(), height = matBGR.height(), channels = matBGR.channels();
        byte[] sourcePixel = new byte[width*height*channels];
        matBGR.get(0, 0, sourcePixel);
        
        image = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
        final byte[] targetPixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
        System.arraycopy(sourcePixel, 0, targetPixels, 0, sourcePixel.length);
        
        return  true;
    }
    
    @Override
    public  void paintComponent(Graphics g){
        super.paintComponent(g);
        
        if(this.image == null){
            return;
        }
        g.drawImage(this.image, 10, 10, this.image.getWidth(),this.image.getHeight(),null);
    }
    
}
