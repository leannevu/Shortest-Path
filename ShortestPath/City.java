package ShortestPath;

import java.util.Objects;

/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *<pre>
 * Class        City.java
 * Description  
 * Project      Shortest Path Application
 * Platform     jdk 1.8.0_214; NetBeans IDE 11.3; Windows 10
 * Course       CS 143, EdCC
 * Hourse       2 hours and 45 minutes
 * Date         6/4/2021
 * Histoly log  5/7/2020, 3/6/2021
 * @author	<i>Leanne Vu</i>
 * @version 	%1% %4%
 * @see     	javax.swing.JDialog
 *</pre>
*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/

public class City implements Comparable
{
    //Attributes of city
    private String city; 
    private String state;
    private String population;
    private double area;
    private int code;
    
     /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * <pre>
     * Method       compareTo()
     * Description  Required overridden compareTo() method for comparison of two
     *              players by name (and if names are equal, then by age). 
     *              Allow the TreeSet data type to sort the players by name.
     * @author      <i>Leanne Vu</i>
     * @param       obj Object
     * Date         3/5/2021
     *</pre>   
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    @Override
    public int compareTo(Object obj) {
        City otherCity = (City) obj;
        //If name equal, compare by age
        if((this.getCity()).equalsIgnoreCase(otherCity.getCity()))
            return this.code - otherCity.code;
        else    //otherwise, compare by name
            return (this.getCity()).compareTo(otherCity.getCity());
        //this will eliminate duplicate players with the same age only: TreeSet
        //return this.getAge() - otherPerson.getAge();
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Constructor  City()-default constructor
     * Description  Creates default (empty) city.
     * @author      <i>Leanne Vu</i>
     * Date         4/3/2020
     * History Log  7/18/2018   
    *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/ 
    public City()
    {
        city = "";
        state = "";
        population = "";
        area = 0;
        code = 0;
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Constructor  City()-overloaded constructor
     * Description  Assigs parameters to instance variables
     * @param       cityName String
     * @author      <i>Leanne Vu</i>
     * Date         4/3/2020
     * History Log  7/18/2018  
    *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public City(String cityName, String state, String population,
            double area, int code)
    {
        this.city = cityName;
        this.state = state;
        this.population = population;
        this.area = area;
        this.code = code;
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       getCity()
     * Description  Getter method to return name of city.
     * @return      city String
     * @author      <i>Leanne Vu</i>
     * Date         4/3/2020
     * History Log  7/18/2018  
    *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public String getCity()
    {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getPopulation() {
        return population;
    }

    public double getArea() {
        return area;
    }

    public int getCode() {
        return code;
    }

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       setCity()
     * Description  Setter method to set city name
     * @param       city String
     * @author      <i>John Heppard</i>
     * Date         3/7/2021
     * History Log  3/7/2021
    *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public void setCity(String city)
    {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setPopulation(String population) {
        this.population = population;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public void setCode(int code) {
        this.code = code;
    }
    
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Constructor  toString()
     * Description  Prints parameters as String
     * @author      <i>Leanne Vu</i>
     * Date         4/3/2020
     * History Log  7/18/2018   
    *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    @Override
    public String toString()
    {
        return  city + ", State: " + state + ", Population: " +
                population + ", Area in Square Miles: " + area + ", Area Code: "
                + code;
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       equals()
     * Description  Overridden method to check equality between cities.
     * @return      true or flase boolean
     * @author      <i>Leanne Vu</i>
     * Date         4/3/2020
     * History Log  7/18/2018   
    *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    @Override
    public boolean equals(Object obj) 
    {
        if (this == obj) 
        {
            return true;
        }
        if (obj == null) 
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        final City other = (City) obj;
        if (!Objects.equals(this.city, other.city))
        {
            return false;
        }
        return true;
    }
}
