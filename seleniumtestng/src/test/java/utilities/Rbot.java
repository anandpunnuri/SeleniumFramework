package utilities;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.openqa.selenium.Keys;

public class Rbot {

	Robot robot;
	Rectangle rect;	
	BufferedImage image;
	public void capturescrenshot(String classname, String testcasename)
	{
		try 
		{
		rect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
		robot = new Robot();
		image = robot.createScreenCapture(rect);
		
		//checking and creating classname folder under Screenshots folder
		
		File file = new File("D:\\WorkSpaceTest\\seleniumtestng\\target\\Screenshots\\"+classname);
		if(!(file.exists()))
				{
			System.out.println("Creating a folder with classname");
			System.out.println(file.getAbsolutePath());
			boolean done = file.mkdirs();
			System.out.println(done);
				}
		
		//Checking if old screenshot exists and deleting old one and saving new one
		File targetimage = new File("./target/Screenshots/"+classname+"/"+testcasename+".png");
		if(targetimage.exists())
		{
			System.out.println("Target File is being deleted as old one still exists");
			targetimage.delete();
		}
		try {
			ImageIO.write(image, "png", targetimage);
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_P);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.keyRelease(KeyEvent.VK_P);
			
			//---------------------------Used AUTOIT Script to print--------------------------------------------------------------------
			//---------------------------Used AUTOIT Script to print--------------------------------------------------------------------
			//---------------------------Used AUTOIT Script to print--------------------------------------------------------------------
			Runtime.getRuntime().exec("D:\\WorkSpaceTest\\seleniumtestng\\src\\test\\resources\\AutoITScripts\\PrintDoc.exe");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Excception while saving the screenshot using Robot class: "+e.getMessage());
		}
		} catch (AWTException awte) {
			// TODO Auto-generated catch block
			awte.printStackTrace();
		}

	}
}
