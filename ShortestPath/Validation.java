package ShortestPath;


import java.util.ArrayList;

/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Class:       Validation
 * File:        Validation.java
 * Description: A class that validates information passed to it.
 * Environment: PC, Windows 10 jdk 1.8, Netbeans 8.0.2
 * Date:        5/8/16
 * History log: 
 * @author      Leanne Vu
 * @version     1.0
 *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
public class Validation 
{
    //constants
    public final double DOUBLE_RANGE = 0.000025;
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * <pre>
     * Constructor:     Default Constructor
     * Description:     Validates information passed to the class
     * Date:            5/9/16
     * @author          Leanne Vu
     * </pre>
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public Validation()
    {
        
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * <pre>
     * Method: isLettersOnly()
     * Description:  Returns true if the String is made of only letters
     * Date:     5/12/16
     * @author Leanne Vu
     * @param  myString String
     * @return boolean
     * </pre>
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public boolean isLettersOnly(String myString)
    {
        // Check if the String is empty
        if(myString.isEmpty())
            return false;
        // Make a char array for the string and check each character
        char[] charArray = myString.toCharArray();
        for(int i = 0; i < charArray.length; i++)
        {
            if(!Character.isLetter(charArray[i]) && !Character.isSpaceChar(charArray[i]))
                    return false;
        }
        // It is letters only!
        return true;
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * <pre>
     * Method: isNumeric()
     * Description:  Returns true if the String is made of only numbers
     * Date:     5/12/16
     * @author Leanne Vu
     * @param  myString String
     * @return boolean
     * </pre>
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public boolean isNumeric(String myString)
    {
        // Check if the string contains only numbers ( or a decimal or negative sign)
        return myString.matches("-?\\d+(\\.\\d+)?");
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * <pre>
     * Method: isPhoneNumber()
     * Description:  Returns true if the String is a valid phone number
     * Date:     5/12/16
     * @author Leanne Vu
     * @param  phoneNum String
     * @return boolean
     * </pre>
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public boolean isPhoneNumber(String phoneNum)
    {
        // Check if string is of format "1234567890"
        if(phoneNum.matches("\\d{10}"))
            return true;
        // Check if string is phone number with -, ., or spaces
        else if(phoneNum.matches("\\d{3}[1\\.\\s]\\d{3}[-\\.\\s]\\d{4}"))  
            return true;
        // Check if string is phone number with extension length
        else if(phoneNum.matches("\\d{3}-\\d{3}-\\d{4}\\s(x|(ext))\\d{3,5}"))
            return true;
        // Check if string is phone number with braces around area code
        else if(phoneNum.matches("\\(\\d{3}\\)\\d{3}-\\d{4}"))
            return true;
        // It is not valid
        else 
            return false;
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * <pre>
     * Method: isEmail()
     * Description:  Returns true if the String is a valid email
     * Date:     5/12/16
     * @author Leanne Vu
     * @param  myString String
     * @return boolean
     * </pre>
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public boolean isEmail(String myString)
    {
        // Check if the string contains only numbers ( or a decimal or negative sign)
        return myString.matches("^[\\w-_\\.+]*[\\w-_\\.]@([\\w]+\\.)+[\\w]+[\\w]$");
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * <pre>
     * Method: toNameFormat()
     * Description:  Returns a string in proper name format
     * Date:     5/12/16
     * @author Susanna Byun
     * @param  str String
     * @return String
     * </pre>
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public String toNameFormat(String str)
    {
        str = str.trim();
        str = str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
        char[] charArray = new char[str.length()];
        charArray = str.trim().toCharArray();
        for(int i = 0; i < charArray.length; i++)
        {
            if(charArray[i] == (' '))
            {
                charArray[i+1] = Character.toUpperCase(charArray[i+1]);
            }
        }
        return new String(charArray);
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * <pre>
     * Method: removeSpaces()
     * Description:  Returns string with all spaces removed
     * Date:     5/12/16
     * @author Susanna Byun
     * @param  str String
     * @return String
     * </pre>
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public String removeSpaces(String str)
    {
        return str.replace("\\s", "");
    }
    
    public String toPhoneFormat(String str)
    {
        if(str.length() == 10 )
        {
            char[] c = str.toCharArray();
            char[] phoneFormat = {'(',c[0],c[1],c[2],')',c[3],c[4],c[5],'-',
                c[6],c[7],c[8],c[9]};
            return new String(phoneFormat);
        }
        return str;
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * <pre>
     * Method: isDuplicateDancer()
     * Description: Returns true if the dancer is already in the database
     * Date:     5/8/16
     * @author Leanne Vu
     * @param  myDancer Dancer
     * @return boolean
     * </pre>
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
//    public boolean isDuplicateDancer(Employee myDancer)
//    {
//        ManageDatabase myManageDatabase = new ManageDatabase();
//        Dancer otherDancer;
//        ArrayList<Dancer> dancerArray = myManageDatabase.findDancerID(myDancer.getFirstName(),myDancer.getLastName(),
//                myDancer.getStyle(), myDancer.getLevel(), myDancer.getYears(),"", "");
//        if(dancerArray.size() > 0)
//        {
//            otherDancer = dancerArray.get(0);
//            
//            if(myDancer.getFirstName().equalsIgnoreCase(otherDancer.getFirstName()))
//            {
//                if(myDancer.getLastName().equalsIgnoreCase(otherDancer.getLastName()))
//                {
//                    if(myDancer.getStyle().equalsIgnoreCase(otherDancer.getStyle()))
//                    {
//                        if(myDancer.getLevel().equalsIgnoreCase(otherDancer.getLevel()))
//                        {
//                            // The dancers are the 
//                            return true;
//                        }
//                    }
//                }
//            }
//        }
//        
//        
//        return false;
//    }
    
    /**
     * <pre>
     * Method : isCloseEnough()
     * Description : Determines if a double value is equal to another value considering 
     *               the inaccuracy of a double value.
     * Date: 4/06/2016
     * @author Susanna Byun
     * @param checkedVal  value being compared
     * @param closeToVal value being compared to
     * @return boolean
     * </pre>
     **/
    public boolean isCloseEnough(double checkedVal, double closeToVal)
    {
        return (Math.abs(checkedVal - closeToVal) < DOUBLE_RANGE);
    }
    
}
