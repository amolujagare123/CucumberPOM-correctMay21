package pages;

import org.openqa.selenium.By;

public class DarkSkyHome extends  Base{

    By mainTextTempRow = By.xpath("//span[@class='summary swap']") ;
    By timeLineTempRow = By.xpath("//span[@class='first']//span");


    public int getTimelineTemp()
    {
        String rowTemp = getTextFromElement(timeLineTempRow);
        //88° ==> {"88"}
        String tempStr =rowTemp.split("°")[0];
        int temp = Integer.parseInt(tempStr);
        return  temp;
    }

    public int getMainTemp()
    {
        String rowTemp = getTextFromElement(mainTextTempRow);
        // "88˚ Clear." ==> { "88" , " Clear."  }
       String tempStr =  rowTemp.split("˚")[0];
       int temp = Integer.parseInt(tempStr);
       return  temp;
    }

}
