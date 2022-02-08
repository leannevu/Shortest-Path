package ShortestPath;

import ShortestPath.ShowGraphs;
import ShortestPath.Splash;
import ShortestPath.WeightedEdge;
import ShortestPath.WeightedGraph;
import java.awt.Toolkit;
import java.awt.print.PrinterException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;

/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
*<pre>
* Class        ShortestPathGUI.java
* Description  Allows the user to select any two cities and finds and displays
*              the shortest path between the two cities with the total miles for
*              the shortest path. Contains Interface Graph<V>, classes AbstractGraph<V>,
*              WeightedGraph<V> with inner classes MST and ShortestPathTree.
* Platform     jdk 1.8.0_291; NetBeans IDE 11.3; PC Windows 10
* Course       CS 143
* Hours        4 hours and 17 minutes
* Date         6/3/2021
* History Log  7/18/2018, 5/7/2020
* @author      <i>Leanne Vu</i>
* @version     %1% %2%
* @see         javax.swing.JFrame
* @see         java.awt.Toolkit 
 *</pre>
 *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
public class ShortestPathGUI extends javax.swing.JFrame 
{
    //File names 
    private String fileName = "src/ShortestPath/WeightedGraphSample1.txt";
    final private String cityFileName = "src/ShortestPath/Cities.txt";
    final private String cityDataFileName = "src/ShortestPath/CityData.txt";
    //Variable names
    private StringBuffer output = new StringBuffer();
    private ArrayList<String> cities = new ArrayList<String>();
     List<ArrayList<Integer>> edges = new ArrayList<>(); 
    
    private TreeSet<City> cityData = new TreeSet<City>();    
    
    private List<WeightedEdge> list;
    private WeightedGraph<Integer> graph;
    private int numberOfVertices = 0;
    private boolean isConnected = false;
    double shortestTotal;
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Constructor  ShortestPathGUI()-default constructor
     * Description  Create an instance of the GUI form, set icon image, set
     *              default button, and instantiate list and graph.
     * Date         6/3/2021
     * History Log  7/18/2018, 5/7/2020
     * @author      <i>Leanne Vu</i>
    *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/ 
    public ShortestPathGUI() 
    {
        initComponents();
        setDate();
        //set image
        this.setIconImage(Toolkit.getDefaultToolkit().getImage("src/Images/Cities_small.png"));
        //set default button
        this.getRootPane().setDefaultButton(goJButton);
        //Read the city files that will be used as either objects or combo list
        readCitiesFromFile(cityFileName);
        readCityDataFromFile(cityDataFileName);
        displayCities();
        //instantiate list and graph
        list = new ArrayList<>();
        graph = new WeightedGraph<Integer>(list, numberOfVertices);
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *<pre>
    * Method        setDate()
    * Description   Private method to set current date in the setTitle of
    *               ShortestPathGUI form in mm/dd/yyyy style
    * @see          java.text.DateFormat
    * @see          java.textSimpleDateFormat
    * @see          java.util.Date
    * @author       <i>6/3/2021</i>
    * Date          6/5/2021
    * History Log   
    *</pre>
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/ 
    private void setDate()
    {
        SimpleDateFormat date = new SimpleDateFormat("M/d/yyyy");
        Date now = new Date();
        this.setTitle("Shortest Path " + date.format(now));
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       readFromFile()
     * Description  Read text file and create ArrayList of the chosen text
     *              file.
     * Date:        6/5/2021
     * @author      <i>Leanne Vu</i>
     * @param       fileName String
     *</pre>
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void readFromFile(String fileName)
    {        
        try
        {
            File file = new File(fileName);
            Scanner inFile = new java.util.Scanner(file);
            list = new ArrayList();
            
            // Read the number of vertices--first line in file
            String line = inFile.nextLine();
            numberOfVertices = Integer.parseInt(line);
            
            while (inFile.hasNext())
            {
                line = inFile.nextLine();
                //split line at "|"
                String[] triplets = line.split("[\\|]");
                
                for (String triplet: triplets)
                {
                    String[] tokens = triplet.split("[,]");
                    int u = Integer.parseInt(tokens[0].trim());
                    int v = Integer.parseInt(tokens[1].trim());
                    double w = Double.parseDouble(tokens[2].trim());
                    
                    list.add(new WeightedEdge(u, v, w));
                    list.add(new WeightedEdge(v, u, w));
                }
            }
        }   
        catch(FileNotFoundException fnfexp)
        {
            JOptionPane.showMessageDialog(null, "Input error -- File not found.",
                    "File Not Found Error!", JOptionPane.ERROR_MESSAGE);
            fnfexp.printStackTrace();
        }        
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       readEdgesFromFile()
     * Description  Reads edges text file and creates a 2d arraylist with vertices.
     * Date:        6/5/2021
     * @author      <i>Leanne Vu</i>
     * @param       textFile String
     *</pre>
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void readEdgesFromFile(String fileName)
    {        
            ArrayList<Integer> a1 = new ArrayList<Integer>();
            ArrayList<Integer> a2 = new ArrayList<Integer>();
            ArrayList<Integer> a3 = new ArrayList<Integer>();
        try
        {
            File file = new File(fileName);
            Scanner inFile = new java.util.Scanner(file);
            list = new ArrayList();
            
            // Read the number of vertices--first line in file
            String line;
//            String line = inFile.nextLine();
//            numberOfVertices = Integer.parseInt(line);
//            System.out.println(numberOfVertices);
            
            while (inFile.hasNext())
            {
                line = inFile.nextLine();
                //split line at "|"
                System.out.println(line);
                String[] triplets = line.split("[|]");
                for (String triplet: triplets)
                {
                    System.out.println(triplets);
                    String[] tokens = triplet.split("[,]");
                    int u = Integer.parseInt(tokens[0].trim());
                    int v = Integer.parseInt(tokens[1].trim());
                    int w = Integer.parseInt(tokens[2].trim());
                    
                    a1.add(u);
                    a2.add(v);
                    a3.add(w);
                                        
                }
            }
            
            edges.add(a1);
            edges.add(a2);
            edges.add(a3);

        }   
        catch(FileNotFoundException fnfexp)
        {
            JOptionPane.showMessageDialog(null, "Input error -- File not found.",
                    "File Not Found Error!", JOptionPane.ERROR_MESSAGE);
            fnfexp.printStackTrace();
        }        
    }
    
//    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//     *<pre>
//     * Method       createEdgesArray()
//     * Description  Creates an array based off of the edges arrayList.
//     * Date:        6/3/2021
//     * @author      <i>Leanne Vu</i>
//     * @param       cities ArrayList<String> cities
//     * @return      cityArray String[]
//     *</pre>
//     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
//    private Integer[][] createEdgesArray() {
//        Integer[][] edgesArray;
////        for (int i = 0; i < edges.size(); i++) {
////            ArrayList<Integer> row = edges.get(i);
////            edges[i] = row.toArray(new Integer[row.size()]);
////        }
//
//for (int i = 0; i < edges.size(); i++) {
//    ArrayList<Integer> row = edges.get(i);
//    Integer[] rowArray = row.toArray(new Integer[row.size()]);
//    Integer[] unboxedRow = new Integer[rowArray.length];
//    for (int j = 0; j < rowArray.length; j++)
//        unboxedRow[j] = rowArray[j];
//    edgesArray[i] = unboxedRow;
//}
//        return edgesArray; //Return an array
//    }
    
    
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       readCityDataFromFile()
     * Description  Reads text file and creates an arraylist with City objects.
     * Date:        6/5/2021
     * @author      <i>Leanne Vu</i>
     * @param       textFile String
     *</pre>
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
   private void readCityDataFromFile(String textFile)
    {        
        //clear previous arraylist
        cityData.clear();
        //check if there is data
        try
        {            
          //read while there is data
          FileReader freader = new FileReader(textFile);
          BufferedReader input = new BufferedReader(freader);
          String line = input.readLine();
          while(line != null) //While there are lines to read, continue adding into array list
          {
            City city = new City();
            StringTokenizer token = new StringTokenizer(line, "|");
            city.setCity(token.nextToken());
            city.setState(token.nextToken());
            city.setPopulation(token.nextToken());
            city.setArea(Double.parseDouble(token.nextToken()));
            city.setCode(Integer.parseInt(token.nextToken()));
            line = input.readLine();
            cityData.add(city);
          }
          input.close(); //Close the BufferedReader
        }
        catch(FileNotFoundException fnfexp)
        {
            JOptionPane.showMessageDialog(null, "Input error -- File not found.",
                    "File Not Found Error!", JOptionPane.ERROR_MESSAGE);
        }
        catch(IOException | NumberFormatException exp)
        {
            JOptionPane.showMessageDialog(null, "Input error -- File could not be read.",
                    "File Read Error!", JOptionPane.ERROR_MESSAGE);
        }
    } 

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       readCitiesFromFile()
     * Description  Reads text of city names and create the ArrayList.
     * Date:        6/5/2021
     * @author      <i>Leanne Vu</i>
     * @param       fileName String
     *</pre>
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void readCitiesFromFile(String fileName)
    {
        String city;
        cities.clear();
        try
        {
            File file = new File(fileName);
            Scanner inFile = new java.util.Scanner(file);
            list = new ArrayList();

            // Read the number of vertices--first line in file
            String line = inFile.nextLine();
            city = line;
            cities.add(city);

            while (inFile.hasNext())
            {
                line = inFile.nextLine();
                city = line;
                cities.add(city);
            }
        }
        catch(FileNotFoundException fnfexp)
        {
            JOptionPane.showMessageDialog(null, "Input error -- File not found.",
                    "File Not Found Error!", JOptionPane.ERROR_MESSAGE);
            fnfexp.printStackTrace();
        }
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       createCitiesArray()
     * Description  Creates an array based off of the cities arrayList. Used in
     *              the shortPath method and the displayCities method.
     * Date:        6/3/2021
     * @author      <i>Leanne Vu</i>
     * @param       cities ArrayList<String> cities
     * @return      cityArray String[]
     *</pre>
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private String[] createCitiesArray(ArrayList<String> cities) {
        String[] cityArray = new String[cities.size()]; //Create the array 
        for ( int index = 0; index < cities.size(); index++){ 
              cityArray[index] = cities.get(index); //Set the elements of the array
            }
        return cityArray; //Return an array
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       displayCities()
     * Description  Calls the createCitiesArray to set the model of the current
     *              and destination combo box.
     * Date:        6/5/2021
     * @author      <i>Leanne Vu</i>
     *</pre>
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void displayCities()
    {
        DefaultComboBoxModel modelCurrent = new DefaultComboBoxModel(createCitiesArray(cities));
        DefaultComboBoxModel modelDestination = new DefaultComboBoxModel(createCitiesArray(cities));
        currentCityJComboBox.setModel(modelCurrent);
        destinationCityJComboBox.setModel(modelDestination);
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       searchCityObject
     * Description  Search city by name and return the City object. Will be
     *              called in printResult and createGraph.
     * @param       cityName String
     * @author      <i>Leanne Vu</i>
     * Date         6/5/2021
     * </pre>
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/ 
    private City searchCityObject(String cityName) {
        if ((cityName != null) && (cityName.length() > 0)) {
            boolean found = false;
            City aCity = null;
            //iterate through the TreeSet to find city searching by names only
            Iterator iterator = cityData.iterator();
            while (iterator.hasNext() && !found) {
                aCity = (City)iterator.next(); //object assigned to Player variable each iteration
                if(aCity.getCity().toLowerCase().contains(cityName.toLowerCase())) {
                   found = true; //if name passed via parameter equals to object cityName, then the object has been found
                   return aCity;
                }
            }
        } 
        return null;
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       searchCityVertex
     * Description  Search city by name and return Player index. Will be used 
     *              in cityExists and ShortestPath
     * @param       cityName String
     * @author      <i>Leanne Vu</i>
     * Date         6/5/2021
     * </pre>
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/ 
    private int searchCityVertex(String cityName) {
        int foundVertex = 0;
        for (int index = 0 ; index < cities.size(); index++){ 
                //check if any of the cities in combobox match the parameter variable
                if (cities.get(index).equalsIgnoreCase(cityName)) {
                    //return the index of the array list (as it matches the combo box index)
                    foundVertex = index;
                    break;
                } 
            }
        return foundVertex;
    }
    
     /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       displayEdges()
     * Description  Edges of the text file attached displayed in a 2d array.
     * @author      <i>Leanne Vu</i>
     * @return      edges int[][]
     * Date         6/5/2021
     * </pre>
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/ 
    private int[][] displayEdges() {
        int[][] edges = {
            {0, 1, 807}, {0, 3, 1331}, {0, 5, 2097}, {1, 0, 807}, {1, 2, 381}, {1, 3, 1267},
{2, 1, 381}, {2, 3, 1015}, {2, 4, 1663}, {2, 10, 1435}, {3, 0, 1331}, {3, 1, 1267}, {3, 2, 1015}, {3, 4, 599}, {3, 5, 1003},
{4, 2, 1663}, {4, 3, 599}, {4, 5, 533}, {4, 7, 1260}, {4, 8, 864}, {4, 10, 496},
{5, 0, 2097}, {5, 3, 1003}, {5, 4, 533}, {5, 6, 983}, {5, 7, 787}, {6, 5, 983}, {6, 7, 214},
{7, 4, 1260}, {7, 5, 787}, {7, 6, 214}, {7, 8, 888}, {8, 4, 864}, {8, 7, 888}, {8, 9, 661}, {8, 10, 781}, {8, 11, 810}, {9, 8, 661}, {9, 11, 1187},
{10, 2, 1435}, {10, 4, 496}, {10, 8, 781}, {10, 11, 239}, {11, 8, 810}, {11, 9, 1187}, {11, 10, 239}
};
        return edges;
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToggleButton1 = new javax.swing.JToggleButton();
        titleJLabel = new javax.swing.JLabel();
        currentCityJComboBox = new javax.swing.JComboBox<>();
        currentCityJLabel = new javax.swing.JLabel();
        destinationCityJLabel = new javax.swing.JLabel();
        destinationCityJComboBox = new javax.swing.JComboBox<>();
        goJButton = new javax.swing.JButton();
        exitJButton = new javax.swing.JButton();
        displayJScrollPane = new javax.swing.JScrollPane();
        resultsJTextArea = new javax.swing.JTextArea();
        selectFileJLabel = new javax.swing.JLabel();
        filesJComboBox = new javax.swing.JComboBox<>();
        jProgressBar1 = new javax.swing.JProgressBar();
        shortestPathJMenuBar = new javax.swing.JMenuBar();
        fileJMenu = new javax.swing.JMenu();
        newJMenuItem = new javax.swing.JMenuItem();
        printJSeparator = new javax.swing.JPopupMenu.Separator();
        saveJMenuItem = new javax.swing.JMenuItem();
        clearJMenuItem = new javax.swing.JMenuItem();
        exitJSeparator = new javax.swing.JPopupMenu.Separator();
        printFormJMenuItem = new javax.swing.JMenuItem();
        printResultJMenuItem = new javax.swing.JMenuItem();
        exitJMenuItem = new javax.swing.JMenuItem();
        helpJMenu1 = new javax.swing.JMenu();
        shortestPathJMenuItem = new javax.swing.JMenuItem();
        allPathsJMenuItem = new javax.swing.JMenuItem();
        showGraphJMenuItem = new javax.swing.JMenuItem();
        helpJMenu = new javax.swing.JMenu();
        aboutJMenuItem = new javax.swing.JMenuItem();

        jToggleButton1.setText("jToggleButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Shortest Path");
        setResizable(false);

        titleJLabel.setFont(new java.awt.Font("Tempus Sans ITC", 2, 36)); // NOI18N
        titleJLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Cities_small.png"))); // NOI18N
        titleJLabel.setText("  Shortest Path");

        currentCityJComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                currentCityJComboBoxActionPerformed(evt);
            }
        });

        currentCityJLabel.setText("Select Current City:");

        destinationCityJLabel.setText("Select Destination City:");

        goJButton.setBackground(new java.awt.Color(0, 153, 153));
        goJButton.setMnemonic('g');
        goJButton.setText("Go");
        goJButton.setToolTipText("Results here");
        goJButton.setEnabled(false);
        goJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                goJButtonActionPerformed(evt);
            }
        });

        exitJButton.setBackground(new java.awt.Color(0, 153, 153));
        exitJButton.setMnemonic('e');
        exitJButton.setText("Exit");
        exitJButton.setToolTipText("Exit application");
        exitJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitJButtonActionPerformed(evt);
            }
        });

        resultsJTextArea.setEditable(false);
        resultsJTextArea.setColumns(20);
        resultsJTextArea.setLineWrap(true);
        resultsJTextArea.setRows(5);
        resultsJTextArea.setWrapStyleWord(true);
        displayJScrollPane.setViewportView(resultsJTextArea);

        selectFileJLabel.setText("Select File: ");

        filesJComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "<Select>", "WeightedGraphSample0", "WeightedGraphSample1", "WeightedGraphSample2" }));
        filesJComboBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                filesJComboBoxItemStateChanged(evt);
            }
        });
        filesJComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filesJComboBoxActionPerformed(evt);
            }
        });

        fileJMenu.setMnemonic('f');
        fileJMenu.setText("File");
        fileJMenu.setToolTipText("Main Program Functions");

        newJMenuItem.setMnemonic('n');
        newJMenuItem.setText("New");
        newJMenuItem.setToolTipText("Add a new text file");
        newJMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newJMenuItemActionPerformed(evt);
            }
        });
        fileJMenu.add(newJMenuItem);
        fileJMenu.add(printJSeparator);

        saveJMenuItem.setMnemonic('s');
        saveJMenuItem.setText("Save");
        saveJMenuItem.setToolTipText("Save files");
        saveJMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveJMenuItemActionPerformed(evt);
            }
        });
        fileJMenu.add(saveJMenuItem);

        clearJMenuItem.setMnemonic('c');
        clearJMenuItem.setText("Clear");
        clearJMenuItem.setToolTipText("Clear form");
        clearJMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearJMenuItemActionPerformed(evt);
            }
        });
        fileJMenu.add(clearJMenuItem);
        fileJMenu.add(exitJSeparator);

        printFormJMenuItem.setMnemonic('p');
        printFormJMenuItem.setText("Print Form");
        printFormJMenuItem.setToolTipText("Prints form as GUI");
        printFormJMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printFormJMenuItemActionPerformed(evt);
            }
        });
        fileJMenu.add(printFormJMenuItem);

        printResultJMenuItem.setMnemonic('x');
        printResultJMenuItem.setText("Print Result");
        printResultJMenuItem.setToolTipText("");
        printResultJMenuItem.setEnabled(false);
        printResultJMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printResultJMenuItemActionPerformed(evt);
            }
        });
        fileJMenu.add(printResultJMenuItem);

        exitJMenuItem.setMnemonic('x');
        exitJMenuItem.setText("Exit");
        exitJMenuItem.setToolTipText("");
        exitJMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitJMenuItemActionPerformed(evt);
            }
        });
        fileJMenu.add(exitJMenuItem);

        shortestPathJMenuBar.add(fileJMenu);

        helpJMenu1.setMnemonic('h');
        helpJMenu1.setText("Graphs");
        helpJMenu1.setToolTipText("Program Info");

        shortestPathJMenuItem.setMnemonic('a');
        shortestPathJMenuItem.setText("Shortest Path");
        shortestPathJMenuItem.setToolTipText("Click for shortest path");
        shortestPathJMenuItem.setEnabled(false);
        shortestPathJMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                shortestPathJMenuItemActionPerformed(evt);
            }
        });
        helpJMenu1.add(shortestPathJMenuItem);

        allPathsJMenuItem.setText("All Short Paths");
        allPathsJMenuItem.setToolTipText("Get all short paths from certain city");
        allPathsJMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                allPathsJMenuItemActionPerformed(evt);
            }
        });
        helpJMenu1.add(allPathsJMenuItem);

        showGraphJMenuItem.setMnemonic('g');
        showGraphJMenuItem.setText("Show Graph of selected text file");
        showGraphJMenuItem.setToolTipText("Show pictures of graph");
        showGraphJMenuItem.setEnabled(false);
        showGraphJMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showGraphJMenuItemActionPerformed(evt);
            }
        });
        helpJMenu1.add(showGraphJMenuItem);

        shortestPathJMenuBar.add(helpJMenu1);

        helpJMenu.setMnemonic('h');
        helpJMenu.setText("Help");
        helpJMenu.setToolTipText("Program Info");

        aboutJMenuItem.setMnemonic('a');
        aboutJMenuItem.setText("About");
        aboutJMenuItem.setToolTipText("Show About form");
        aboutJMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutJMenuItemActionPerformed(evt);
            }
        });
        helpJMenu.add(aboutJMenuItem);

        shortestPathJMenuBar.add(helpJMenu);

        setJMenuBar(shortestPathJMenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(113, 113, 113)
                        .addComponent(titleJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 383, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(displayJScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 541, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(112, 112, 112)
                        .addComponent(goJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(77, 77, 77)
                        .addComponent(exitJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(77, 77, 77)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(currentCityJComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(selectFileJLabel)
                                    .addComponent(currentCityJLabel)
                                    .addComponent(destinationCityJLabel))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(111, 111, 111)
                                        .addComponent(filesJComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(destinationCityJComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addContainerGap(42, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(titleJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(selectFileJLabel)
                    .addComponent(filesJComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(currentCityJLabel)
                    .addComponent(currentCityJComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(destinationCityJLabel)
                    .addComponent(destinationCityJComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(displayJScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(goJButton)
                    .addComponent(exitJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       aboutJMenuItemActionPerformed()
     * Description  Create an About form and show it. 
     * @param       evt java.awt.event.KeyEvent
     * @author      <i>Leanne Vu</i>
     * Date         6/3/2021
    *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void aboutJMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutJMenuItemActionPerformed
        About aboutWindow = new About(this, false);
        aboutWindow.setVisible(true);
    }//GEN-LAST:event_aboutJMenuItemActionPerformed
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       quitJMenuItemActionPerformed()
     * Description  Event handler to end the application. Calls the quitJButton
     * @author      <i>Leanne Vu</i>
     * Date         6/5/2021
     * History Log  7/18/2018     
    *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void exitJMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitJMenuItemActionPerformed
        exitJButton.doClick();
    }//GEN-LAST:event_exitJMenuItemActionPerformed
   
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       quitJButtonActionPerformed()
     * Description  Event handler to end the application.
     * @author      <i>Leanne Vu</i>
     * Date         6/5/2021
     * History Log  7/18/2018     
    *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void exitJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitJButtonActionPerformed
        System.exit(0);
    }//GEN-LAST:event_exitJButtonActionPerformed
   
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       printJMenuItemActionPerformed()
     * Description  Event handler to print the for as a GUI. 
     * @author      <i>Leanne Vu</i>
     * Date         6/3/2021
     * History Log  7/18/2018, 6/5/2021
    *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void printFormJMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printFormJMenuItemActionPerformed
        PrintUtilities.printComponent(null);
    }//GEN-LAST:event_printFormJMenuItemActionPerformed
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       goJButtonActionPerformed()
     * Description  Event handler that passes the selected text file chosen to be
     *              read and calls the createGraph() method.
     * @author      <i>Leanne Vu</i>
     * Date         6/3/2020
    *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void goJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_goJButtonActionPerformed
        try
        {
           resultsJTextArea.setText(""); //clear the resultsJTextArea
           output = new StringBuffer();
           String fileComboBox = filesJComboBox.getSelectedItem().toString(); //get selected text
           fileName = "src/ShortestPath/" + fileComboBox + ".txt";
           readFromFile(fileName); //read the selected file
           createGraph();       //create the graph and decide if connected
        }
        catch(Exception exp)
        {
            JOptionPane.showMessageDialog(null, exp.getMessage(),
                "Cannot create graph", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_goJButtonActionPerformed

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       newJMenuItemActionPerformed
     * Description  Event handler to choose a separate file for the graph and
     *              adds the text file into the combo box for the user to choose.
     * @param       evt java.awt.event.KeyEvent
     * @author      <i>Leanne Vu</i>
     *</pre>
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void newJMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newJMenuItemActionPerformed
        try
        {
            JFileChooser chooser = new JFileChooser("src/ShortestPath");
            //Filter only txt files
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "Txt Files", "txt");
            chooser.setFileFilter(filter);
            int choice = chooser.showOpenDialog(null);
            if (choice == JFileChooser.APPROVE_OPTION)
            {
                File chosenFile = chooser.getSelectedFile();
                fileName = "src/ShortestPath/" + chosenFile.getName();
                filesJComboBox.addItem(chosenFile.getName()); //adds the chosen file into combo
                
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Cannot find file",
                    "File Input Error", JOptionPane.WARNING_MESSAGE);
            }
        }
        catch(java.lang.IllegalArgumentException exp)
        {
            JOptionPane.showMessageDialog(null, "File is not in correct format.",
                "File Input Error", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_newJMenuItemActionPerformed
   
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       showPictureOfGraphsMenuItemActionPerformed()
     * Description  Display picture of the file
     * @author      <i>Leanne Vu</i>
     * Date         6/5/2021
     * History Log  7/18/2018     
    *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void showGraphJMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showGraphJMenuItemActionPerformed
         String textName = filesJComboBox.getItemAt(filesJComboBox.getSelectedIndex()); //get the selected file
         ShowGraphs graph = new ShowGraphs(textName); //instance of the ShowGraphs
                  
         graph.setVisible(true); //set the graph visible
    }//GEN-LAST:event_showGraphJMenuItemActionPerformed
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       clearJMenuItemActionPerformed()
     * Description  Clears the GUI back to the default when it opened.
     * @param       evt ActionEvent
     * @author      <i>Leanne Vu</i>
     * Date         6/5/2021
     * History Log  7/18/2018  
    *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void clearJMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearJMenuItemActionPerformed
        filesJComboBox.setSelectedIndex(0); //files to select
        currentCityJComboBox.setSelectedIndex(0); //current to select
        destinationCityJComboBox.setSelectedIndex(0); //destination to select
        resultsJTextArea.setText(""); //clear the resultsJTextArea
    }//GEN-LAST:event_clearJMenuItemActionPerformed
   
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       shortestPathJMenuItemActionPerformed()
     * Description  Display the shortest path between the two cities by calling
     *              the goJButton.
     * @author      <i>Leanne Vu</i>
     * @param       evt java.awt.event.ActionEvent
     * Date         6/5/2021
     * History Log  7/18/2018   
    *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void shortestPathJMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_shortestPathJMenuItemActionPerformed
//        try
//        {
//            goJButtonActionPerformed(evt);
//            if(!isConnected)
//            {
//                resultsJTextArea.setText("Graph is not connected.");
//            }
//            else
//            {
//                int v1 = Integer.parseInt(JOptionPane.showInputDialog("Enter the first vertex."));
//                int v2 = Integer.parseInt(JOptionPane.showInputDialog("Enter the second vertex."));
//                goJButton.doClick();
//                System.out.println("1");
//                AbstractGraph<Integer>.Tree tree = graph.bfs(v1);
//                List<Integer> path = tree.getPath(v2);
//                output.append("\nThe path is ");
//                System.out.println("2");
//                for (int i = 0; i < path.size(); i++)
//                    output.append(path.get(i) + " ");
//                    System.out.println("3");
//                resultsJTextArea.setText(output.toString());
//                System.out.println("4");
//            }
//        }
//        catch(Exception exp)
//        {
//            JOptionPane.showMessageDialog(null, "Cannot find path",
//                "Path Error", JOptionPane.INFORMATION_MESSAGE);
//        }

    goJButton.doClick();
    }//GEN-LAST:event_shortestPathJMenuItemActionPerformed

   /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       filesJComboBoxItemStateChanged()
     * Description  Enables/Disabled menu items (showGraph/print/shortestPath/go)
     *              based on whether it can be used without a text file chosen.
     * @author      <i>Leanne Vu</i>
     * @param       evt java.awt.event.ActionEvent
     * Date         6/5/2021
     * History Log  7/18/2018   
    *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void filesJComboBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_filesJComboBoxItemStateChanged
        if(filesJComboBox.getSelectedIndex() > 0) {
            showGraphJMenuItem.setEnabled(true);
            printResultJMenuItem.setEnabled(true);
            shortestPathJMenuItem.setEnabled(true);
            goJButton.setEnabled(true);
        } else {
            showGraphJMenuItem.setEnabled(false);
            printResultJMenuItem.setEnabled(false);
            shortestPathJMenuItem.setEnabled(false);
            goJButton.setEnabled(false);
        }
    }//GEN-LAST:event_filesJComboBoxItemStateChanged

    private void filesJComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filesJComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_filesJComboBoxActionPerformed
   
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>>
     * Method       printResultJMenuItem()
     * Description  Event handler to print details of the current/destination city/
     *              shortest path of the two cities/weight by calling the 
     *              shortestPath and shortestPathWeight method.
     * @parem       evt ActionEvent
     * @author      <i>Leanne Vu</i>
     * Date         6/5/2021
     * </pre>
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/ 
    private void printResultJMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printResultJMenuItemActionPerformed
        //get the current and destination city names
        String currentCity = currentCityJComboBox.getItemAt(currentCityJComboBox.getSelectedIndex());
        String destinationCity = destinationCityJComboBox.getItemAt(destinationCityJComboBox.getSelectedIndex());
        //get the objects of the cities to print data
        City currentCityObject = searchCityObject(currentCity);
        City destinationCityObject = searchCityObject(destinationCity);
        
        JTextArea printResult = new JTextArea(); //the JTextArea creation that will be printed 
            try{
                //City INFO, path/miles
                String output = "Current City: " + currentCityObject.toString() +
                        "\nDestination City: " + destinationCityObject.toString()+
                        "\nThe shortest path from " + currentCity + 
                        " to " + destinationCity + ": " + 
                        shortestPath(currentCityJComboBox.getSelectedIndex(), destinationCityJComboBox.getSelectedIndex())
                        + "\nShortest path distance (weight): " + shortestPathWeight(currentCityJComboBox.getSelectedIndex(), 
                        destinationCityJComboBox.getSelectedIndex()) + " miles";
                
                printResult.setText(output); //sets printPlayer to the created output
                printResult.print();
            } 
            catch(PrinterException ex){
            JOptionPane.showMessageDialog(null,"Player not printed", "Print Error",
                    JOptionPane.WARNING_MESSAGE);
            }
        
    }//GEN-LAST:event_printResultJMenuItemActionPerformed

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       cityExists
     * Description  Checks if the city exists in the combo box. Will be used
     *              in allPathsJMenuItem.
     * @param       cityName String
     * @author      <i>Leanne Vu</i>
     * Date         6/5/2021
     * </pre>
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/ 
    private boolean cityExists(String cityName) {
        boolean result = false;
        for (int index = 0 ; index < cities.size(); index++){ 
                //check if any of the cities in combobox match the parameter variable
                if (cities.get(index).equalsIgnoreCase(cityName)) {
                    //return the index of the array list (as it matches the combo box index)
                    result = true;
                    break;
                } 
            }
        return result;
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>>
     * Method       allPathsJMenuItemActionPerformed()
     * Description  Finds all paths from the city entered by the user while also
     *              checking whether the city is valid.
     * @parem       evt ActionEvent
     * @author      <i>Leanne Vu</i>
     * Date         6/5/2021
     * </pre>
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/ 
    private void allPathsJMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_allPathsJMenuItemActionPerformed
        printResultJMenuItem.setEnabled(false);
        String inputName = JOptionPane.showInputDialog ("Enter name of the chosen city: ");
        
        if (cityExists(inputName)) { //Check if the inputName is valid
            WeightedGraph<String> graph1 = new WeightedGraph<>(createCitiesArray(cities), displayEdges());
            WeightedGraph<String>.ShortestPathTree tree1 = graph1.getShortestPath(searchCityVertex(inputName));
            resultsJTextArea.setText(tree1.appendAllPaths()); //call method created (that can be put in the textbox)
        } else { // A null object means that the searched item does not match database records-
                JOptionPane.showMessageDialog(null, "Philosopher " + inputName +
                        " not found ", " Search Result", JOptionPane.WARNING_MESSAGE);
        }        
    }//GEN-LAST:event_allPathsJMenuItemActionPerformed

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>>
     * Method       saveJMenuItemActionPerformed()
     * Description  Saves the resultsJTextArea into the results text file and
     *              also saves any new text files into the combo list.
     * @parem       evt ActionEvent
     * @author      <i>Leanne Vu</i>
     * Date         6/5/2021
     * </pre>
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/ 
    private void saveJMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveJMenuItemActionPerformed
        try {
            FileWriter filePointer = new FileWriter("src/ShortestPath/Results.txt", false);
            PrintWriter outputResult = new PrintWriter(filePointer, true);
            outputResult.write(resultsJTextArea.getText()); //writes the resultJTextArea into the file Results.txt
            outputResult.close();
            
            int filesSize = filesJComboBox.getItemCount(); //find size of the filesJComboBox
            String[] filesArray = new String[filesSize]; //create temporary array
            for (int i = 1; i < filesSize; i ++) { //copy the filesJComboBox values into the temporary array
                filesArray[i] = filesJComboBox.getItemAt(i); 
            }
            DefaultComboBoxModel modelFiles = new DefaultComboBoxModel(filesArray); //set the combo box with the newly array
            filesJComboBox.setModel(modelFiles);
   
        } catch (IOException exp)
        {
            exp.printStackTrace();
        }
    }//GEN-LAST:event_saveJMenuItemActionPerformed

    private void currentCityJComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_currentCityJComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_currentCityJComboBoxActionPerformed
   
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       createGraph()
     * Description  Created UnweightedGraph and associated tree and find the 
     *              shortest path using the current and destination vertex.
     * @author      <i>Leanne Vu</i>
     * @param       current int
     * @param       destination int
     * Date         6/5/2021
     * History Log  7/18/2018     
    *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public String shortestPath(int current, int destination) {
        shortestTotal = 0;
        //Instantiate associated tree
        StringBuffer outputShort = new StringBuffer();
        WeightedGraph<String> graph1 = new WeightedGraph<>(createCitiesArray(cities), displayEdges());
        WeightedGraph<String>.ShortestPathTree tree1 = graph1.getShortestPath(destination);
        java.util.List<String> path = tree1.getPath(current); 
      
        String[] cityArray = new String[path.size()]; //create array for the path
        for ( int index = 0; index < path.size(); index++){ //which will be used to determine the last city of the path
                cityArray[index] = path.get(index);
            }
        
        for (String s: path) {
            if (s.equals(cityArray[path.size()-1])) //determine which is the last city for path
                outputShort.append("(" + searchCityVertex(s) + ") " + s); //don't include the last arrow
            else
                outputShort.append("(" + searchCityVertex(s) + ") " + s + " -> "); //include the arrow for the next path city
            
        }
        //return the final result of path
        return(outputShort.toString());
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       shortestPathWeight()
     * Description  Created UnweightedGraph and associated tree and determines
     *              the weight of the shortest path between the current and
     *              destination vertex
     * @param       current int
     * @param       destination int
     * @author      <i>Leanne Vu</i>
     * Date         6/5/2021
     * History Log  7/18/2018     
    *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public Double shortestPathWeight(int current, int destination) {
        shortestTotal = 0;
        StringBuffer outputShort = new StringBuffer();
        WeightedGraph<String> graph1 = new WeightedGraph<>(createCitiesArray(cities), displayEdges());
        WeightedGraph<String>.ShortestPathTree tree1 = graph1.getShortestPath(current);
        //tree1.printAllPaths();
        
        shortestTotal = tree1.getCost(destination); 
        
        
        return(shortestTotal);
    }
    
    //        java.util.List<String> path = tree1.getPath(current); 
//
//        String[] cityArray = new String[path.size()]; //create array for the path
//        for ( int index = 0; index < path.size(); index++){ 
//            try {
//                cityArray[index] = path.get(index);
//                if(index < (path.size() - 1)) {
//                    searchCityVertex(cityArray[index]);
//                   searchCityVertex(cityArray[index+1]);
//                    shortestTotal += graph1.getWeight(searchCityVertex(cityArray[index]),searchCityVertex(cityArray[index+1]));
//                }
//            } catch (Exception ex) {
//                Logger.getLogger(ShortestPathGUI.class.getName()).log(Level.SEVERE, null, ex);
//            }
//
//            }
    
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       createGraph()
     * Description  Outputs the shortest path/shortest path weight/city information
     *              of the selected file and selected cities. Calls the
     *              shortestPathWeight and shortestPath
     * @author      <i>Leanne Vu</i>
     * Date         6/5/2021
     * History Log  7/18/2018     
    *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public void createGraph()
    {
        //Variables using selected cities/file that will be used when calling for the necessary methods
        String currentCity = currentCityJComboBox.getItemAt(currentCityJComboBox.getSelectedIndex());
        String destinationCity = destinationCityJComboBox.getItemAt(destinationCityJComboBox.getSelectedIndex());
        int currentCityIndex = currentCityJComboBox.getSelectedIndex();
        int destinationCityIndex = destinationCityJComboBox.getSelectedIndex(); 
        City currentCityObject = searchCityObject(currentCity);
        City destinationCityObject = searchCityObject(destinationCity);
        
        resultsJTextArea.setText("");
        output = new StringBuffer();
        
        output.append("Current City: " + currentCityObject.toString());
        output.append("\n\nDestination City: " + destinationCityObject.toString());
        
//        graph = new WeightedGraph(list, numberOfVertices);
      
        output.append("\n\nThe shortest path from " + currentCity + 
                " to " + destinationCity + " is: " + 
                shortestPath(currentCityIndex, destinationCityIndex));
        
        output.append("\n\nShortest path distance (weight): " + shortestPathWeight(currentCityIndex, destinationCityIndex)
                + " miles");
        
        output.append("\n\nOF THE SELECTED FILE- The number of vertices is " + numberOfVertices);
        
        WeightedGraph<Integer> graph = new WeightedGraph<Integer>(list, numberOfVertices);
        WeightedGraph<Integer>.MST tree = graph.getMinimumSpanningTree();
//        output.append(graph.displayWeightedEdges()+ "\n");
        output.append("\n\nOF THE SELECTED FILE-the total weight in MST: " + tree.getTotalWeight());

        //check if connected
        if (tree.getNumberOfVerticesFound() == numberOfVertices)
        {
            output.append("\n\nOF THE SELECTED FILE- the graph is: connected");
            isConnected = true;
        }
        else
        {
            output.append("\n\nOF THE SELECTED FILE- the graph is: not connected");
            isConnected = false;
        }
        
        
//        output.append("\n" + tree.printTree(new StringBuffer()));
        resultsJTextArea.setText(output.toString());
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *<pre>
     * Method       main()
     * Description  Displays splash screen and the main GUI form.
     * @param       args are the command line strings
     * @author      <i>Leanne Vu</i>
     * Date         6/3/2021
     * History Log  7/18/2018, 5/7/2020
     *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public static void main(String args[]) 
    {
//        Splash mySplash = new Splash(4000);
//        mySplash.showSplash();
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ShortestPathGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ShortestPathGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ShortestPathGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ShortestPathGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ShortestPathGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem aboutJMenuItem;
    private javax.swing.JMenuItem allPathsJMenuItem;
    private javax.swing.JMenuItem clearJMenuItem;
    private javax.swing.JComboBox<String> currentCityJComboBox;
    private javax.swing.JLabel currentCityJLabel;
    private javax.swing.JComboBox<String> destinationCityJComboBox;
    private javax.swing.JLabel destinationCityJLabel;
    private javax.swing.JScrollPane displayJScrollPane;
    private javax.swing.JButton exitJButton;
    private javax.swing.JMenuItem exitJMenuItem;
    private javax.swing.JPopupMenu.Separator exitJSeparator;
    private javax.swing.JMenu fileJMenu;
    private javax.swing.JComboBox<String> filesJComboBox;
    private javax.swing.JButton goJButton;
    private javax.swing.JMenu helpJMenu;
    private javax.swing.JMenu helpJMenu1;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JMenuItem newJMenuItem;
    private javax.swing.JMenuItem printFormJMenuItem;
    private javax.swing.JPopupMenu.Separator printJSeparator;
    private javax.swing.JMenuItem printResultJMenuItem;
    private javax.swing.JTextArea resultsJTextArea;
    private javax.swing.JMenuItem saveJMenuItem;
    private javax.swing.JLabel selectFileJLabel;
    private javax.swing.JMenuBar shortestPathJMenuBar;
    private javax.swing.JMenuItem shortestPathJMenuItem;
    private javax.swing.JMenuItem showGraphJMenuItem;
    private javax.swing.JLabel titleJLabel;
    // End of variables declaration//GEN-END:variables
}
