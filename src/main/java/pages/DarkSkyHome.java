package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import static stepdefinitions.SharedSD.getDriver;

public class DarkSkyHome extends  Base{

    By mainTextTempRow = By.xpath("//span[@class='summary swap']") ;
    By timeLineTempRow = By.xpath("//span[@class='first']//span");
    By timeTextRow = By.xpath("//span[@class='hour']/span");
    By todayExpander = By.xpath("//a[@data-day='0']//span[@class='toggle']");

    By barTempMin =By.xpath("//a[@class='day revealed']//span[@class='minTemp']");
    By barTempMax =By.xpath("//a[@class='day revealed']//span[@class='maxTemp']");


    By timeLineTempMin =By.xpath("//div[@class='dayDetails revealed']//span[@class='highTemp swip']//span[@class='temp']");
    By timeLineTempMax =By.xpath("//div[@class='dayDetails revealed']//span[@class='lowTemp swap']//span[@class='temp']");

   // 102˚

    By darkSkyAPILnk  =By.xpath("//a[normalize-space()='Dark Sky API']");

    public void clickDarkSkyAPILnk()
    {
        clickOn(darkSkyAPILnk);
    }

    public ArrayList<String> getBarTempList()
    {
        String minTemp = getTextFromElement(barTempMin).split("˚")[0];
        String maxTemp = getTextFromElement(barTempMax).split("˚")[0];

        ArrayList<String> tempList = new ArrayList<>();
        tempList.add(minTemp);
        tempList.add(maxTemp);

        return  tempList;
    }


    public ArrayList<String> getTimelineTempList()
    {
        String minTemp = getTextFromElement(timeLineTempMin).split("˚")[0];
        String maxTemp = getTextFromElement(timeLineTempMax).split("˚")[0];

        ArrayList<String> tempList = new ArrayList<>();
        tempList.add(minTemp);
        tempList.add(maxTemp);

        return  tempList;
    }




    public void clickTodayExpander()
    {
        getDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("window.scrollBy(0,1000)");

        //clickOn(todayExpander);

        js.executeScript("arguments[0].click()",webAction(todayExpander));

    }


    public ArrayList<Integer> getTimeList()
    {
        ArrayList<String> timeRowList = getElementTextList(timeTextRow);
        // [10am, 12pm, 2pm, 4pm, 6pm, 8pm, 10pm, 12am, 2am, 4am, 6am]
        System.out.println(timeRowList);

        ArrayList<Integer> timeList = new ArrayList<>();

        for(int i=0;i<timeRowList.size();i++)
        {
            String timeRawStr = timeRowList.get(i); // 10am
            int l = timeRawStr.length();

            String timeStr = timeRawStr.substring(0,l-2); // 10 - String

            int time = Integer.parseInt(timeStr); // 10 - int

            timeList.add(time);
        }

        System.out.println(timeList);

        return timeList;
    }


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
